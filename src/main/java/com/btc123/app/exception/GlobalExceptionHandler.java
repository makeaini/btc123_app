package com.btc123.app.exception;

import com.btc123.app.bean.ReturnBean;
import com.btc123.app.enumeration.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shining on 2017-11-23.
 *
 * @author shining
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ReturnBean> exceptionHandler(Exception e) {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        //记录日志
        ResponseEntity responseEntity = null;
        if (e instanceof ProException) {
            ProException exception = (ProException) e;
            returnBean.setReturnCode(exception.getErrorCode());
            returnBean.setReturnMsg(exception.getErrorMsg());
            responseEntity = new ResponseEntity(returnBean, HttpStatus.OK);
            return responseEntity;
        }
        LOGGER.error(e.getMessage(), e);
        returnBean.setReturnCode(ErrorEnum.ERROR.getCode());
        returnBean.setReturnMsg(ErrorEnum.ERROR.getMessage());
        responseEntity = new ResponseEntity(returnBean, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
