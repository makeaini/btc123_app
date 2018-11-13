package com.btc123.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc123.app.constants.Constants;
import com.btc123.app.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import static com.btc123.app.constants.Constants.AUTH_TOKEN_KEY;
import static com.btc123.app.constants.Constants.USER_SERVER_AUTH_TOKEN_KEY;

/**
 * 登录拦截器
 *
 * @author shining
 */
@Component
public class AuthUserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String appToken = request.getParameter(USER_SERVER_AUTH_TOKEN_KEY);
            if (!StringUtils.isEmpty(appToken)) {
                Object authUser = RedisUtil.get(AUTH_TOKEN_KEY + appToken);
                if (!StringUtils.isEmpty(authUser)) {
                    request.setAttribute(Constants.USER_INFO_KEY,
                            authUser);
                    return true;
                }
            }
        }
        return true;
    }
}
