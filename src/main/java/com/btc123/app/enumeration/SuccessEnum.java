package com.btc123.app.enumeration;

/**
 * Created by shining on 2018/5/11.
 */
public enum SuccessEnum {
    SMS("短信发送成功"),
    REGISTER("注册成功"),
    FEED_BACK("意件反馈成功"),
    UPDATE_SUCCESS("修改成功"),
    LIKE("点赞成功"),
    CANCELLIKE("取消点赞"),
    COLLECT("收藏成功"),
    CANCELCOLLECT("取消收藏");
    private String message;

    SuccessEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
