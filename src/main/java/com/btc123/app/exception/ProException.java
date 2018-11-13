package com.btc123.app.exception;

public class ProException extends Exception {
	
	private static final long serialVersionUID = -4720455406482839391L;
	/**
	 * 错误码
	 */
	private Integer errorCode;
	/**
	 * 错误信息，可选
	 */
	private String errorMsg;
	
	public ProException() {
		super();
	}

	public ProException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProException(String message) {
		super(message);
	}

	public ProException(Throwable cause) {
		super(cause);
	}


	public String getErrorMsg() {
		return errorMsg;
	}

	protected void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	

	public ProException(Integer errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	@Override
	public String toString() {
		return this.getClass().getName()+" [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + "]";
	}

}
