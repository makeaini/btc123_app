package com.btc123.app.controller;

import com.btc123.app.model.user.UserLoginRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.btc123.app.constants.Constants.USER_INFO_KEY;

/**
 * Created by shining on 2018/5/11.
 */
@Controller
public class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 获取用户的id
     *
     * @return
     */
    public Long getUserId() {
        Object userInfo = request.getAttribute(USER_INFO_KEY);
        if (StringUtils.isEmpty(userInfo)){
            return null;
        }
        UserLoginRes userLoginResBean = (UserLoginRes) userInfo;
        return userLoginResBean.getUserId();
    }
}
