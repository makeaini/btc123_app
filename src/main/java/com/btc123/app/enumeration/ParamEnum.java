package com.btc123.app.enumeration;

/**
 * Created by shining on 2018/5/11.
 */
public enum ParamEnum {
    POSITION_ID("广告位置id不能为空"),
    USER_NAME("用户名不能为空"),
    PASSWORD("请输入密码（6-20位字母与数字组合"),
    MOBILE("手机号不能为空"),
    DEVICE_TYPE("设备类型不能为空"),
    CAPTCHA("验证码不能为空"),
    CONTENT("内容不能为空"),
    PAGESIZE("页尺寸不能为空"),
    PAGENUMBER("当前页不能为空"),
    NEW_PASSWORD("新密码不能为空"),
    OLD_PASSWORD("旧密码不能为空"),
    VERSION("版本号不能为空"),
    NICKNAME("昵称不能为空"),
    AVATAR("头像不能为空"),
    ARTICLEID("资讯id不能为空"),
    FLASHID("资讯id不能为空"),
    ARTICLEISLIKE("资讯是否点赞不能为空"),
    ARTICLEISCOLLECT("资讯是否收藏不能为空"),
    COUNTRY_CODE("国家编码不能为空"),
    SIGN("币种简称不能为空"),
    TRADE_ID("平台id不能为空"),
    SYMBOL("行情唯一标识不能为空"),
    KEYWORD("搜索关键字不能为空"),
    ID("id不能为空"),
    IDS("id拼接字符串不能为空"),
    SORTS("排序号拼接字符串不能为空"),
    TYPE("k线时间线不能为空");
    private String message;

    ParamEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
