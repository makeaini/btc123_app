package com.btc123.app.service;

import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.bean.request.user.UserLoginReq;
import com.btc123.app.exception.ProValiDataException;
import com.btc123.app.model.information.ArticleRes;
import com.btc123.app.model.user.UserLoginRes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shining on 2018/5/11.
 */
public interface UserServiceI {
  //  public PageInfo<User> findPageInfo(Integer pageNumber, Integer pageSize);

    /**
     * 用户登录
     * @param bean 用户例如信息
     * @return
     */
    UserLoginRes login(UserLoginReq bean) throws ProValiDataException;

    /**
     * 发送短信验证码
     * @param code 国家短信码
     * @param mobile 手机号
     * @param
     */
    void sendCaptcha(String mobile,String code,Integer type) throws ProValiDataException;

    /**
     * 用户注册
     * @param mobile 手机号
     * @param password 密码
     * @param captcha 验证码
     */
    void register(String mobile,String password,String captcha,String countryCode) throws ProValiDataException;

    /**
     * 忘记密码，并修改密码
     * @param mobile 手机号
     * @param password 密码
     * @param captcha 验证码
     */
    void forgetPassword(String mobile, String password, String captcha) throws ProValiDataException;

    /**
     * 验证验证码
     * @param mobile 手机号
     * @param captcha 验证码
     */
    void verificationCaptcha(String mobile, String captcha) throws ProValiDataException;

    /**
     * 修改密码
     * @param oldPass 旧密码
     * @param newPass 新密码
     */
    void updatePassword(String oldPass, String newPass,Long userId) throws ProValiDataException;

    /**
     * 修改手机号
     * @param mobile 修改手机号
     * @param captcha 验证码
     * @param userId 用户id
     */
    void updateMobile(String mobile, String captcha,Long userId) throws ProValiDataException;

    /**
     * 获取用户k线颜色和价格设置
     * @param userId 用户id
     * @param settingType 1:k线，2：价格
     * @return
     */
    Integer findUserSetting(Long userId,Integer settingType);

    /**
     * 修改用户K线和价格设置
     * @param userId
     * @param settingType
     * @param state
     */
    void updateUserSetting(Long userId, Integer settingType,Integer state);

    /**
     * 修改用户昵称或者头像
     * @param userId 用户id
     * @param nickname 用户昵称
     */
    void updateUserNickNameOrAvatar(Long userId, String nickname,String avatar);

    /**
     * 查询用户收藏的文章
     * @param request 用户Id
     * @param queryPageBean 分页对象
     * @return
     */
    PageBean<ArticleRes> userArticleCollect(HttpServletRequest request, QueryPageBean queryPageBean);

    UserLoginRes findUserInfo(Long userId);
}
