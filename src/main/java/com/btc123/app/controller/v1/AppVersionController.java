package com.btc123.app.controller.v1;

import com.btc123.app.bean.ReturnBean;
import com.btc123.app.entity.AppVersion;
import com.btc123.app.enumeration.ParamEnum;
import com.btc123.app.exception.ProException;
import com.btc123.app.exception.ProParamException;
import com.btc123.app.service.AppVersionServiceI;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by shining on 2018/5/17.
 */
@RestController
@RequestMapping("{version}/appVersion/")
@Api(name = "app版本管理", description = "app版本管理")
@ApiVersion(since = "1.0")
public class AppVersionController extends BaseV1Controller {
    @Autowired
    private AppVersionServiceI appVersionServiceI;


    @RequestMapping(value =  "findVersion",method = RequestMethod.GET)
    @ApiMethod(summary = "查询当前版本", description = "查询当前版本")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "version", required = true, clazz = Long.class, description = "当前版本号"),
            @ApiQueryParam(name = "deviceType", required = true, clazz = Integer.class, description = "设备类型1:android,2:ios")
    })
    public ReturnBean findVersion(Long version, Integer deviceType) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Optional.ofNullable(version).orElseThrow(() -> new ProParamException(ParamEnum.VERSION.getMessage()));
        Optional.ofNullable(version).orElseThrow(() -> new ProParamException(ParamEnum.DEVICE_TYPE.getMessage()));
        AppVersion appVersion = appVersionServiceI.findByVersion(version, deviceType);
        returnBean.setData(appVersion);
        return returnBean;
    }

//    @RequestMapping(value =  "222",method = RequestMethod.GET)
//    public ReturnBean findVersion(@Validate(validateClass = UserValidator.class) UserLoginReq userLoginReq) throws ProException {
//        ReturnBean returnBean = ReturnBean.returnBeanBuild();
//        return returnBean;
//    }
}


