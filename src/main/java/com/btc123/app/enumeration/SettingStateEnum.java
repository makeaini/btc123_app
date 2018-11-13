package com.btc123.app.enumeration;

/**
 * Created by shining on 2018/5/17.
 */
public enum SettingStateEnum {
    /**
     * 红涨绿跌
     */
    RED_UP_GREEN_DOWN(1),
    /**
     * 绿涨红跌
     */
    GREEN_UP_RED_DOWN(2),
    /**
     * 人民币
     */
    CURRENCY_CNY(1),
    /**
     * 美元
     */
    CURRENCY_USD(2),
    /**
     * 其它币种
     */
    CURRENCY_ORIGINAL(3);


    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    SettingStateEnum(int status){
        this.status = status;
    }
}
