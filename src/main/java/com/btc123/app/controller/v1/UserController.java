package com.btc123.app.controller.v1;

import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.ReturnBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.entity.User;
import com.btc123.app.entity.UserSetting;
import com.btc123.app.enumeration.ParamEnum;
import com.btc123.app.enumeration.SuccessEnum;
import com.btc123.app.exception.ProException;
import com.btc123.app.exception.ProParamException;
import com.btc123.app.model.information.ArticleRes;
import com.btc123.app.model.user.UserLoginRes;
import com.btc123.app.service.UserServiceI;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by shining on 2018/5/11.
 */
@RestController
@RequestMapping("/{version}/user/")
@Api(name = "用户中心个人设置", description = "用户中心个人设置")
@ApiVersion(since = "1.0")
public class UserController extends BaseV1Controller {
    @Autowired
    private UserServiceI userServiceI;
    @ApiMethod(summary = "验证码发送验证", description = "验证码发送验证")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "mobile", required = true, clazz = String.class, description = "手机号"),
            @ApiQueryParam(name = "captcha", required = true, clazz = String.class, description = "验证码"),
    })
    @RequestMapping(value = "verificationCaptcha", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean feedback(String mobile, String captcha) throws ProException {
        Optional.ofNullable(mobile).orElseThrow(() -> new ProParamException(ParamEnum.MOBILE.getMessage()));
        Optional.ofNullable(captcha).orElseThrow(() -> new ProParamException(ParamEnum.CAPTCHA.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        userServiceI.verificationCaptcha(mobile, captcha);
        return returnBean;
    }
    @ApiMethod(summary = "修改密码", description = "修改密码")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "oldPass", required = true, clazz = String.class, description = "旧密码"),
            @ApiQueryParam(name = "newPass", required = true, clazz = String.class, description = "新密码"),
    })
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean updatePassword(String oldPass, String newPass) throws ProException {
        Optional.ofNullable(oldPass).orElseThrow(() -> new ProParamException(ParamEnum.OLD_PASSWORD.getMessage()));
        Optional.ofNullable(newPass).orElseThrow(() -> new ProParamException(ParamEnum.NEW_PASSWORD.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        userServiceI.updatePassword(oldPass, newPass,userId);
        return returnBean.setReturnMsg(SuccessEnum.UPDATE_SUCCESS.getMessage());
    }

    @ApiMethod(summary = "修改手机号码", description = "修改手机号码")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "mobile", required = true, clazz = String.class, description = "手机号"),
            @ApiQueryParam(name = "captcha", required = true, clazz = String.class, description = "验证码"),
    })
    @RequestMapping(value = "updateMobile", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean updateMobile(String mobile, String captcha) throws ProException {
        Optional.ofNullable(mobile).orElseThrow(() -> new ProParamException(ParamEnum.MOBILE.getMessage()));
        Optional.ofNullable(captcha).orElseThrow(() -> new ProParamException(ParamEnum.CAPTCHA.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        userServiceI.updateMobile(mobile, captcha,userId);
        return returnBean.setReturnMsg(SuccessEnum.UPDATE_SUCCESS.getMessage());
    }
    @ApiMethod(summary = "获取用户K线颜色和价格设置", description = "获取用户K线颜色和价格设置")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "settingType", required = true, clazz = Integer.class, description = "1:k线，2:价格"),
    })
    @RequestMapping(value = "findUserSetting", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean findUserSetting(@RequestParam(defaultValue = "1") Integer settingType) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        Integer state = userServiceI.findUserSetting(userId,settingType);
        returnBean.setData(state);
        return returnBean;
    }

    @ApiMethod(summary = "修改用户K线颜色和价格", description = "修改用户K线颜色和价格")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "settingType", required = true, clazz = Integer.class, description = "1:k线，2:价格"),
            @ApiQueryParam(name = "state", required = true, clazz = Integer.class, description = "当settingType为1（1:绿涨红跌,2:红涨绿跌）,当settingType为2(1:CNY,2:USD,3:原价格)"),
    })
    @RequestMapping(value = "updateUserSetting", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean updateUserSetting(@RequestParam(defaultValue = "1") Integer settingType,@RequestParam(defaultValue = "1") Integer state) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        userServiceI.updateUserSetting(userId,settingType,state);
        return returnBean.setReturnMsg(SuccessEnum.UPDATE_SUCCESS.getMessage());
    }



    @ApiMethod(summary = "获取用户最新信息", description = "获取用户最新信息")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public @ApiResponseObject
    ReturnBean getUserInfo() throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        UserLoginRes userInfo = userServiceI.findUserInfo(userId);
        return returnBean.setData(userInfo);
    }

    @ApiMethod(summary = "修改用户昵称", description = "修改用户昵称")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "nickname", required = true, clazz = String.class, description = "昵称"),
    })
    @RequestMapping(value = "updateNickName", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean updateNickName(String nickname) throws ProException {

        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        userServiceI.updateUserNickNameOrAvatar(userId,nickname,null);
        return returnBean.setReturnMsg(SuccessEnum.UPDATE_SUCCESS.getMessage());
    }
    @ApiMethod(summary = "修改用户头像", description = "修改用户头像")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "avatar", required = true, clazz = String.class, description = "头像"),
    })
    @RequestMapping(value = "updateAvatar", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean updateAvatar(String avatar) throws ProException {
        Optional.ofNullable(avatar).orElseThrow(() -> new ProParamException(ParamEnum.AVATAR.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        userServiceI.updateUserNickNameOrAvatar(userId,null,avatar);
        return returnBean.setReturnMsg(SuccessEnum.UPDATE_SUCCESS.getMessage());
    }

    @ApiMethod(summary = "查询用户收藏文章", description = "查询用户收藏文章")
    @RequestMapping(value = "userArticleCollect", method = RequestMethod.POST)
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageNumber", required = true, clazz = Integer.class, description = "当前页"),
            @ApiQueryParam(name = "pageSize", required = true, clazz = Integer.class, description = "条数"),
    })
    public @ApiResponseObject
    ReturnBean userArticleCollect(QueryPageBean queryPageBean, HttpServletRequest request) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        PageBean<ArticleRes> articleResPageBean = userServiceI.userArticleCollect(request,queryPageBean);
        returnBean.setData(articleResPageBean);
        return returnBean;
    }

}
