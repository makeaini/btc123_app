package com.btc123.app.mapper;

import com.btc123.app.entity.Captcha;
import org.apache.ibatis.annotations.Param;

public interface CaptchaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Captcha record);

    int insertSelective(Captcha record);

    Captcha selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Captcha record);

    int updateByPrimaryKey(Captcha record);

    /**
     * 通过手机号查询今天已经发送的短信数
     * @param mobile
     * @return
     */
    Integer searchCountByMobileNow(String mobile);
}