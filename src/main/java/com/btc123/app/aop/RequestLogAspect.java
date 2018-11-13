package com.btc123.app.aop;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.btc123.app.exception.ProParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

/**
 * @author shining
 */
@Aspect
@Component
public class RequestLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("execution(public * com.btc123.app.controller.*.*.*(..))")
    public void requestLog() {
    }

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) throws ProParamException {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        // 记录下请求内容
        LOGGER.info("URL : {}", url);
        LOGGER.info("HTTP_METHOD : {} ", request.getMethod());
        LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Annotation[][] annotations = method.getParameterAnnotations();
//        for (int j = 0; j < annotations.length; j++) {
//            Annotation[] annotations1 = annotations[j];
//            for (int k = 0; k < annotations1.length; k++) {
//                Annotation annotation = annotations1[k];
//                annotation.annotationType().(Validator.class)
//                System.out.println(clazz);
//            }
//        }
    }

}
