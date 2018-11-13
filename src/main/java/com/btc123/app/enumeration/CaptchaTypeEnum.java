package com.btc123.app.enumeration;


/**
 * 广告类型枚举
 *
 * @author liyi
 * @create 2018-01-14 16:34
 **/
public enum CaptchaTypeEnum {

    /**
     * 注册
     */
    REGISTER(1),
    /**
     * 忘记密码，修改密码
     */
    FORGET_PASS(2);

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    CaptchaTypeEnum(int type){
        this.type = type;
    }

}
