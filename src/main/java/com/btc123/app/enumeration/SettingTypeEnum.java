package com.btc123.app.enumeration;

/**
 * Created by shining on 2018/5/17.
 */
public enum  SettingTypeEnum {
    /**
     * k线颜色设置
     */
    KLINE_COLOR(1),
    /**
     * 币种价格
     */
    CURRENCY_PRICE(2);

    private int type;

    public int getStatus() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    SettingTypeEnum(int type) {
        this.type = type;
    }
}
