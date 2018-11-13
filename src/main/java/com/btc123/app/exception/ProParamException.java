package com.btc123.app.exception;


import com.btc123.app.enumeration.ErrorEnum;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author shining
 * @category 参数异常类
 * @date 2015-12-28
 */
public class ProParamException extends ProException {


    private static final long serialVersionUID = -2634813585698684406L;

    public ProParamException() {
        super();
        super.setErrorCode(ErrorEnum.PARAM_ERROR.getCode());
        super.setErrorMsg(ErrorEnum.PARAM_ERROR.getMessage());
    }

    public ProParamException(String errorMsg) {
        super();
        super.setErrorCode(ErrorEnum.PARAM_ERROR.getCode());
        super.setErrorMsg(errorMsg);
    }

    public ProParamException(Integer errorCode, String errorMsg) {
        super();
        super.setErrorCode(errorCode);
        super.setErrorMsg(errorMsg);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
