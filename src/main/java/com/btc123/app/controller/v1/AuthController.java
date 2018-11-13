package com.btc123.app.controller.v1;

import com.btc123.app.bean.ReturnBean;
import com.btc123.app.bean.request.user.UserLoginReq;
import com.btc123.app.constants.Constants;
import com.btc123.app.entity.User;
import com.btc123.app.enumeration.CaptchaTypeEnum;
import com.btc123.app.enumeration.ParamEnum;
import com.btc123.app.enumeration.SuccessEnum;
import com.btc123.app.exception.ProException;
import com.btc123.app.exception.ProParamException;
import com.btc123.app.model.user.UserLoginRes;
import com.btc123.app.redis.RedisUtil;
import com.btc123.app.service.UserServiceI;
import com.btc123.app.utils.IpUtils;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.btc123.app.constants.Constants.USER_SERVER_AUTH_TOKEN_KEY;

/**
 * Created by shining on 2018/5/11.
 */
@RestController
@RequestMapping("/{version}/auth/")
@Api(name = "用户认证，登录，注册，验证码发送,忘记密码", description = "用户认证，登录，注册，验证码发送，忘记密码")
@ApiVersion(since = "1.0")
public class AuthController extends BaseV1Controller {
    @Autowired
    private UserServiceI userServiceI;

    /**
     * @param user
     * @return
     * @throws ProParamException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiMethod(summary = "登录", description = "登录")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "username", required = true, clazz = String.class, description = "用户名"),
            @ApiQueryParam(name = "password", required = true, clazz = String.class, description = "密码"),
            @ApiQueryParam(name = "deviceType", required = true, clazz = Integer.class, description = "设备类型1:android,2:ios"),
            @ApiQueryParam(name = "deviceId", required = false, clazz = String.class, description = "设备id号")
    })
    public ReturnBean login(UserLoginReq user) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Optional.ofNullable(user.getUsername()).orElseThrow(() -> new ProParamException(ParamEnum.USER_NAME.getMessage()));
        Optional.ofNullable(user.getPassword()).orElseThrow(() -> new ProParamException(ParamEnum.PASSWORD.getMessage()));
        Optional.ofNullable(user.getDeviceType()).orElseThrow(() -> new ProParamException(ParamEnum.DEVICE_TYPE.getMessage()));
        user.setIp(IpUtils.getIpAddr(super.getRequest()));
        UserLoginRes userRes = userServiceI.login(user);
        returnBean.setData(userRes);
        return returnBean;
    }

    @ApiMethod(summary = "获取验证码", description = "获取验证码")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "mobile", required = true, clazz = String.class, description = "手机号"),
            @ApiQueryParam(name = "countryCode", required = false, clazz = String.class, description = "国家编码"),
            @ApiQueryParam(name = "type", required = true, clazz = String.class, description = "1:注册，2:忘记密码,修改密码")})
    @RequestMapping(value = "sendCaptcha", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean sendCaptcha(String mobile,  String countryCode,@RequestParam(value = "type",defaultValue = "1") Integer type) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Optional.ofNullable(mobile).orElseThrow(() -> new ProParamException(ParamEnum.MOBILE.getMessage()));
        if (type == CaptchaTypeEnum.REGISTER.getType()){
            Optional.ofNullable(countryCode).orElseThrow(() -> new ProParamException(ParamEnum.COUNTRY_CODE.getMessage()));
        }
        userServiceI.sendCaptcha(mobile,countryCode,type);
        return returnBean.setReturnMsg(SuccessEnum.SMS.getMessage());
    }

    @ApiMethod(summary = "注册", description = "注册")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "mobile", required = true, clazz = String.class, description = "手机号"),
            @ApiQueryParam(name = "password", required = true, clazz = String.class, description = "密码"),
            @ApiQueryParam(name = "captcha", required = true, clazz = String.class, description = "手机验证码"),
            @ApiQueryParam(name = "countryCode", required = true, clazz = String.class, description = "国家编号")
    })
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean register(String mobile, String password, String captcha,String countryCode) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Optional.ofNullable(mobile).orElseThrow(() -> new ProParamException(ParamEnum.MOBILE.getMessage()));
        Optional.ofNullable(password).orElseThrow(() -> new ProParamException(ParamEnum.PASSWORD.getMessage()));
        Optional.ofNullable(captcha).orElseThrow(() -> new ProParamException(ParamEnum.CAPTCHA.getMessage()));
        userServiceI.register(mobile, password, captcha,countryCode);
        return returnBean.setReturnMsg(SuccessEnum.REGISTER.getMessage());
    }

    @ApiMethod(summary = "忘记密码，并重置密码", description = "忘记密码，并重置密码")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "mobile", required = true, clazz = String.class, description = "手机号"),
            @ApiQueryParam(name = "password", required = true, clazz = String.class, description = "密码"),
            @ApiQueryParam(name = "captcha", required = true, clazz = String.class, description = "手机验证码")
    })
    @RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean forgetPassword(String mobile, String password, String captcha) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Optional.ofNullable(mobile).orElseThrow(() -> new ProParamException(ParamEnum.MOBILE.getMessage()));
        Optional.ofNullable(password).orElseThrow(() -> new ProParamException(ParamEnum.PASSWORD.getMessage()));
        Optional.ofNullable(captcha).orElseThrow(() -> new ProParamException(ParamEnum.CAPTCHA.getMessage()));
        userServiceI.forgetPassword(mobile, password, captcha);
        return returnBean.setReturnMsg(SuccessEnum.UPDATE_SUCCESS.getMessage());
    }
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ApiMethod(summary = "退出登录", description = "退出登录")
    public ReturnBean logout() throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        String appToken = super.getRequest().getParameter(USER_SERVER_AUTH_TOKEN_KEY);
        RedisUtil.del(Constants.AUTH_TOKEN_KEY + appToken);
        return returnBean;
    }
}
