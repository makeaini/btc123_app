package com.btc123.app.enumeration;

/**
 * Created by shining on 2018/5/11.
 */
public enum BusinessEnum {
    CAPTCHA_ERROR(1001,"验证码错误"),
    CAPTCHA_OVER_LIMIT_ERROR(1002,"您今天发送验证码次数超出限制，明明再来试试吧！"),
    CAPTCHA_TIME_OUT_ERROR(1003,"验证码已过期"),
    CAPTCHA_SEND_ERROR(1004,"短信发送失败"),

    USER_NOT_FOUND_ERROR(2001,"手机号码未注册"),
    USER_PASSWORD_ERROR(2002,"密码错误"),
    USER_LOCK_ERROR(2003,"帐号已经被锁定"),
    USER_IS_EXIST_ERROR(2004,"帐号已经注册，请直接登录"),
    USER_OLD_PASSWORD_ERROR(2005,"原密码不正确"),
    USER_AUTH_FAIL_LOGIN_TIME_OUT(2006,"登录超时，请重新登录"),
    USER_AUTH_SIGNATURE_ERROR(2007,"签名验证失败，签名验证不通过"),
    VERSION_NEW_ERROR(3001,"当前版本是最新版本");
    private int code;
    private String message;

    BusinessEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BusinessEnum stateOf(int index){
        for (BusinessEnum state :values()){
            if(state.getCode() == index){
                return state;
            }
        }
        return null;
    }

}
