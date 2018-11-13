package com.btc123.app.enumeration;

/**
 * Created by shining on 2018/5/11.
 */
public enum ErrorEnum {
    SUCCESS(200,"成功"),
    PARAM_ERROR(400,"参数错误"),
    ERROR(500,"系统错误");


    private int code;
    private String message;

    ErrorEnum(int code, String message) {
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

    public static ErrorEnum stateOf(int index){
        for (ErrorEnum state :values()){
            if(state.getCode() == index){
                return state;
            }
        }
        return null;
    }

}
