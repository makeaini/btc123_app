package com.btc123.app.service.impl;

import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.bean.request.user.UserLoginReq;
import com.btc123.app.constants.Constants;
import com.btc123.app.entity.*;
import com.btc123.app.enumeration.*;
import com.btc123.app.exception.ProValiDataException;
import com.btc123.app.mapper.*;
import com.btc123.app.model.information.ArticleRes;
import com.btc123.app.model.user.UserLoginRes;
import com.btc123.app.redis.RedisUtil;
import com.btc123.app.service.UserServiceI;
import com.btc123.app.sms.SmsSendUtils;
import com.btc123.app.sms.response.SmsSendResponse;
import com.btc123.app.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author shining
 * @date 2018/5/11
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private CaptchaMapper captchaMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Value("${captchaLimit}")
    private Integer CAPTCHA_LIMIT;
    @Autowired
    private UserSettingMapper userSettingMapper;
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 用户登录
     *
     * @param reqBean
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginRes login(UserLoginReq reqBean) throws ProValiDataException {
        // 查询未锁定的用户
        User user = userMapper.findUserByMobile(reqBean.getUsername());
        Optional.ofNullable(user).orElseThrow(() -> new ProValiDataException(BusinessEnum.USER_NOT_FOUND_ERROR.getCode(),
                BusinessEnum.USER_NOT_FOUND_ERROR.getMessage()));
        String password = MessageDigestUtils.encrypt(reqBean.getPassword(), Algorithm.SHA1);
        if (!user.getPassword().equals(password)) {
            throw new ProValiDataException(BusinessEnum.USER_PASSWORD_ERROR.getCode(), BusinessEnum.USER_PASSWORD_ERROR.getMessage());
        }
        if (user.getLockStatus().equals(LockStatusEnum.LOCK.getStatus())) {
            throw new ProValiDataException(BusinessEnum.USER_LOCK_ERROR.getCode(), BusinessEnum.USER_LOCK_ERROR.getMessage());
        }
        //用户名+密码+userId拼接成字符串，进行md5加密生成app登录token
        StringBuffer stringBuffer = new StringBuffer(user.getMobile()).append(user.getPassword()).append(user.getId()).append(RandomUtils.getRandomIntByLength(10));
        String appToken = MessageDigestUtils.encrypt(stringBuffer.toString(), Algorithm.MD5);
        UserLoginRes userLoginResBean = new UserLoginRes(user.getNickname(), user.getMobile(), appToken, user.getAvatar(), user.getId());
        RedisUtil.set(Constants.AUTH_TOKEN_KEY + appToken, userLoginResBean, Constants.ONE_MONTH);
        //添加登录日志
        UserLoginLog userLoginLog = new UserLoginLog(DateUtils.getCurrentDate(), user.getId(), reqBean.getDeviceType(), null, reqBean.getIp());
        userLoginLogMapper.insertSelective(userLoginLog);
        return userLoginResBean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendCaptcha(String mobile, String countryCode,Integer type) throws ProValiDataException {
        //如果不是注册，就要先验证是否存在，提示他不能发送短信
        if (type != CaptchaTypeEnum.REGISTER.getType()){
            User user = userMapper.findUserByMobile(mobile);
            Optional.ofNullable(user).orElseThrow(()->new ProValiDataException(BusinessEnum.USER_NOT_FOUND_ERROR.getCode(),BusinessEnum.USER_NOT_FOUND_ERROR.getMessage()));
            countryCode = user.getCountryCode().trim();
        }
        long count = captchaMapper.searchCountByMobileNow(mobile);
        if (count > CAPTCHA_LIMIT) {
            throw new ProValiDataException(BusinessEnum.CAPTCHA_OVER_LIMIT_ERROR.getCode(), BusinessEnum.CAPTCHA_OVER_LIMIT_ERROR.getMessage());
        }
        String captcha = RandomUtils.getRandomIntByLength(6);
        // TODO:短信的这里要改回来，
        captcha = "111111";
//        SmsSendResponse smsSendResponse = null;
//        if ("86".equals(countryCode)) {
//            smsSendResponse = SmsSendUtils.sendSms(mobile, captcha);
//        } else {
//            smsSendResponse = SmsSendUtils.sendIntSms(countryCode + mobile, captcha);
//        }
//        if (!smsSendResponse.getCode().equals("0")){
//            throw  new ProValiDataException(BusinessEnum.CAPTCHA_SEND_ERROR.getCode(),smsSendResponse.getErrorMsg());
//        }
        RedisUtil.set(Constants.CAPTCHA_KEY + mobile, captcha, Constants.TEN_MINUTES);
        // 存入数据库,记录当前电话号码发送短信条数
        Captcha captcha1 = new Captcha(mobile, captcha);
        captchaMapper.insertSelective(captcha1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(String mobile, String password, String captcha,String countryCode) throws ProValiDataException {
        String redisCaptcha = (String) RedisUtil.get(Constants.CAPTCHA_KEY + mobile);
        if (StringUtils.isEmpty(redisCaptcha)){
            throw new ProValiDataException(BusinessEnum.CAPTCHA_TIME_OUT_ERROR.getCode(), BusinessEnum.CAPTCHA_TIME_OUT_ERROR.getMessage());
        }
        if (!captcha.equals(redisCaptcha)) {
            throw new ProValiDataException(BusinessEnum.CAPTCHA_ERROR.getCode(), BusinessEnum.CAPTCHA_ERROR.getMessage());
        }
        User user = userMapper.findUserByMobile(mobile);
        Optional<User> optional = Optional.ofNullable(user);
        if (optional.isPresent()) {
            throw new ProValiDataException(BusinessEnum.USER_IS_EXIST_ERROR.getCode(), BusinessEnum.USER_IS_EXIST_ERROR.getMessage());
        }
        // 将密码进行加密
        password = MessageDigestUtils.encrypt(password, Algorithm.SHA1);
        // 随机生成一个nickname
        String nickname = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        String avatar = "https://lppshop.oss-cn-hangzhou.aliyuncs.com/0c823381d01a4364b0e4d388c4f9fba0.png";
        User user1 = new User(DateUtils.getCurrentDate(), nickname, mobile, password, LockStatusEnum.UN_LOCK.getStatus(), avatar,countryCode);
        userMapper.insertSelective(user1);
        // 删除redis中的key
        RedisUtil.del(Constants.CAPTCHA_KEY + mobile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void forgetPassword(String mobile, String password, String captcha) throws ProValiDataException {
        User user = userMapper.findUserByMobile(mobile);
        Optional.ofNullable(user).orElseThrow(() -> new ProValiDataException(BusinessEnum.USER_NOT_FOUND_ERROR.getCode(),
                BusinessEnum.USER_NOT_FOUND_ERROR.getMessage()));
        String redisCaptcha = (String) RedisUtil.get(Constants.CAPTCHA_KEY + mobile);
        if (StringUtils.isEmpty(redisCaptcha)){
            throw new ProValiDataException(BusinessEnum.CAPTCHA_TIME_OUT_ERROR.getCode(), BusinessEnum.CAPTCHA_TIME_OUT_ERROR.getMessage());
        }
        if (!captcha.equals(redisCaptcha)) {
            throw new ProValiDataException(BusinessEnum.CAPTCHA_ERROR.getCode(), BusinessEnum.CAPTCHA_ERROR.getMessage());
        }
        password = MessageDigestUtils.encrypt(password, Algorithm.SHA1);
        User user1 = new User(user.getId(), password);
        userMapper.updateByPrimaryKeySelective(user1);
    }

    @Override
    public void verificationCaptcha(String mobile, String captcha) throws ProValiDataException {
        String redisCaptcha = (String) RedisUtil.get(Constants.CAPTCHA_KEY + mobile);
        if (!captcha.equals(redisCaptcha)) {
            throw new ProValiDataException(BusinessEnum.CAPTCHA_ERROR.getCode(), BusinessEnum.CAPTCHA_ERROR.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(String oldPass, String newPass, Long userId) throws ProValiDataException {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getLockStatus().equals(LockStatusEnum.LOCK.getStatus())) {
            throw new ProValiDataException(BusinessEnum.USER_LOCK_ERROR.getCode(), BusinessEnum.USER_LOCK_ERROR.getMessage());
        }
        oldPass = MessageDigestUtils.encrypt(oldPass, Algorithm.SHA1);
        if (!user.getPassword().equals(oldPass)) {
            throw new ProValiDataException(BusinessEnum.USER_OLD_PASSWORD_ERROR.getCode(), BusinessEnum.USER_OLD_PASSWORD_ERROR.getMessage());
        }
        newPass = MessageDigestUtils.encrypt(newPass, Algorithm.SHA1);
        User user1 = new User(userId, newPass);
        userMapper.updateByPrimaryKeySelective(user1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMobile(String mobile, String captcha, Long userId) throws ProValiDataException {
        String redisCaptcha = (String) RedisUtil.get(Constants.CAPTCHA_KEY + mobile);

        if (StringUtils.isEmpty(redisCaptcha)){
            throw new ProValiDataException(BusinessEnum.CAPTCHA_TIME_OUT_ERROR.getCode(), BusinessEnum.CAPTCHA_TIME_OUT_ERROR.getMessage());
        }
        if (!captcha.equals(redisCaptcha)) {
            throw new ProValiDataException(BusinessEnum.CAPTCHA_ERROR.getCode(), BusinessEnum.CAPTCHA_ERROR.getMessage());
        }
        User user = userMapper.findUserByMobile(mobile);
        Optional.ofNullable(user).orElseThrow(() -> new ProValiDataException(BusinessEnum.USER_NOT_FOUND_ERROR.getCode(),
                BusinessEnum.USER_NOT_FOUND_ERROR.getMessage()));
        if (user.getLockStatus().equals(LockStatusEnum.LOCK.getStatus())) {
            throw new ProValiDataException(BusinessEnum.USER_LOCK_ERROR.getCode(), BusinessEnum.USER_LOCK_ERROR.getMessage());
        }
        User user1 = new User();
        user1.setId(user.getId());
        user1.setMobile(mobile);
        userMapper.updateByPrimaryKeySelective(user1);
    }

    @Override
    public Integer findUserSetting(Long userId, Integer settingType) {
        UserSetting userSetting = userSettingMapper.selectByUserIdAndType(userId, settingType);
        Optional<UserSetting> optional = Optional.ofNullable(userSetting);
        if (!optional.isPresent()) {
            if (settingType.equals(SettingTypeEnum.KLINE_COLOR.getStatus())) {
                return SettingStateEnum.RED_UP_GREEN_DOWN.getStatus();
            } else if (settingType.equals(SettingTypeEnum.CURRENCY_PRICE.getStatus())) {
                return SettingStateEnum.CURRENCY_CNY.getStatus();
            }
        }
        return optional.get().getState();
    }

    @Override
    public void updateUserSetting(Long userId, Integer settingType, Integer state) {
        UserSetting userSetting = userSettingMapper.selectByUserIdAndType(userId, settingType);
        Optional<UserSetting> optional = Optional.ofNullable(userSetting);
        if (optional.isPresent()) {
            userSetting = optional.get();
            userSetting.setUserId(userId);
            userSetting.setSettingType(settingType);
            userSetting.setState(state);
            userSettingMapper.updateByPrimaryKeySelective(userSetting);
        } else {
            userSetting = new UserSetting(userId, settingType, state);
            userSettingMapper.insertSelective(userSetting);
        }
    }

    @Override
    public void updateUserNickNameOrAvatar(Long userId, String nickname, String avatar) {
        User user = new User();
        user.setNickname(nickname);
        user.setAvatar(avatar);
        user.setId(userId);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageBean<ArticleRes> userArticleCollect(HttpServletRequest request, QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getPageNumber(), queryPageBean.getPageSize());
        String token = request.getHeader("token");
        List<Article> articleList = articleMapper.queryCollectArticleByToken(token);
        PageInfo<Article> pageInfoArticle = new PageInfo<Article>(articleList);
        //返回list封装
        List<ArticleRes> articleResList = BeanMapper.mapList(articleList, ArticleRes.class);
        PageBean<ArticleRes> pageInfo = new PageBean<ArticleRes>(pageInfoArticle);
        pageInfo.setData(articleResList);
        return pageInfo;
    }

    @Override
    public UserLoginRes findUserInfo(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserLoginRes userLoginRes = new UserLoginRes();
        BeanUtils.copyProperties(user,userLoginRes);
        return userLoginRes;
    }
}
