package com.btc123.app.exception;


import com.btc123.app.enumeration.ErrorEnum;

/**
 * @author shining
 * @date 2015-12-28
 * @category 数据异常类
 */
public class ProValiDataException extends ProException {

    private static final long serialVersionUID = -7368646284079457427L;

    public ProValiDataException(Integer errorCode) {
        super();
        super.setErrorCode(errorCode);
        super.setErrorMsg(ErrorEnum.ERROR.getMessage());
    }

    public ProValiDataException(String errorMsg) {
        super();
        super.setErrorMsg(errorMsg);
    }

    public ProValiDataException(Integer errorCode, String errorMsg) {
        super();
        super.setErrorCode(errorCode);
        super.setErrorMsg(errorMsg);
    }

    public ProValiDataException() {
        this.setErrorCode(ErrorEnum.ERROR.getCode());
        this.setErrorMsg(ErrorEnum.ERROR.getMessage());
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


}
