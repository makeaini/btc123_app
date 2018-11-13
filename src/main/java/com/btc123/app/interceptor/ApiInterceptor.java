package com.btc123.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc123.app.bean.ReturnBean;
import com.btc123.app.constants.Constants;
import com.btc123.app.enumeration.BusinessEnum;
import com.btc123.app.enumeration.ErrorEnum;
import com.btc123.app.utils.Algorithm;
import com.btc123.app.utils.FastJsonUtils;
import com.btc123.app.utils.MessageDigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.*;

import static com.btc123.app.constants.Constants.USER_INFO_KEY;


/**
 * 接口拦截器
 *
 * @author liuwenzhong
 * @create 2018-02-10 15:39
 **/
@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(ApiInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            ReturnBean returnBean = ReturnBean.returnBeanBuild();
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String token = null;// 登录token
            String sig = null; // 验证签名
            List<String> paramValue = new ArrayList<String>();
            // 获取所有请求参数进行解析
            Map<String, String[]> map = request.getParameterMap();
            for (String key : map.keySet()) {
                if ("token".equals(key)) {
                    token = request.getParameter(key);
                    paramValue.add(token);
                    continue;
                } else if ("sign".equals(key)) {
                        sig = request.getParameter(key);
                    LOG.debug("sign:{}", sig);
                    //图片不进行签名
                } else {
                    String[] values = (String[]) map.get(key);
                    for (String val : values) {
                        paramValue.add(val);
                    }
                }
                LOG.debug("参数名：" + key + "  参数值:"
                        + Arrays.toString((String[]) map.get(key)));
            }
            if (token == null || sig == null) {
                LOG.info("签名验证失败，含有空参数");
                returnBean.setReturnCode(BusinessEnum.USER_AUTH_SIGNATURE_ERROR.getCode())
                        .setReturnMsg(BusinessEnum.USER_AUTH_SIGNATURE_ERROR.getMessage());
                response.getWriter().print(FastJsonUtils.beanToJson(returnBean));
                return false;
            }
            Object userInfo = request.getAttribute(USER_INFO_KEY);
            // 判断用户提交的token和服务器缓存的token是否一致
            if (userInfo == null) {
                LOG.info("登录超时，请重新登录");
                returnBean.setReturnCode(
                        BusinessEnum.USER_AUTH_FAIL_LOGIN_TIME_OUT.getCode()).setReturnMsg(
                        BusinessEnum.USER_AUTH_FAIL_LOGIN_TIME_OUT.getMessage());
                response.getWriter().print(FastJsonUtils.beanToJson(returnBean));
                return false;
            }
            // 添加一个私钥
            paramValue.add(Constants.APP_PRIVATE_KEY);
            // 对解析参数排序
            Collections.sort(paramValue);
            LOG.debug("需要加密的参数排序后:{}", paramValue);
            StringBuilder temp = new StringBuilder();
            // 对字符串用户id+token+所有参数值进行MD5加密
            for (String value : paramValue) {
                temp.append(value);
            }
            String newSig =MessageDigestUtils.encrypt(temp.toString(), Algorithm.MD5);
            // 前后加密串进行比对，成功验证通过否则验证失败
            if (newSig.equalsIgnoreCase(sig)) {
                return true;
            }
            LOG.info("签名验证失败，签名验证不通过");
            returnBean.setReturnCode(
                    BusinessEnum.USER_AUTH_SIGNATURE_ERROR.getCode()).setReturnMsg(
                    BusinessEnum.USER_AUTH_SIGNATURE_ERROR.getMessage());
            response.getWriter().print(FastJsonUtils.beanToJson(returnBean));
            return false;
        }
        return false;
    }
}
