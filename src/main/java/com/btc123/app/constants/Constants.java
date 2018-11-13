package com.btc123.app.constants;

/**
 * Created by shining on 2018/5/11.
 */
public interface Constants {
    /**
     * 默认缓存时间
     */
    public static final Integer DEFAULT_EXPIRATION = 30 * 60;
    /**
     * 私钥
     */
    public static final String APP_PRIVATE_KEY = "ad1aae6f1f39cb1d1cf0db0b39642fc1";
    /**
     * 服务器的token
     */
    public static final String USER_SERVER_AUTH_TOKEN_KEY = "token";

    /**
     * 登录后用户session信息
     */
    public static final String USER_INFO_KEY = "user_info_key";

    /**
     * app token redis认证的key
     */
    public static final String AUTH_TOKEN_KEY = "auth_token_key";

    /**
     * 缓存1个月
     */
    public static final Integer ONE_MONTH = 60 * 24 * 60 * 30;
    /**
     * 短信验证码key
     */
    public static final String CAPTCHA_KEY = "captcha_key";
    /**
     * 十分钟
     */
    public static final Integer TEN_MINUTES = 5 * 60;
}
