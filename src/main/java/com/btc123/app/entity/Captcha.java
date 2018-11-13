package com.btc123.app.entity;

import java.util.Date;

public class Captcha implements IEntity{
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String mobile;

    private String captcha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha == null ? null : captcha.trim();
    }

    public Captcha(String mobile, String captcha) {
        this.mobile = mobile;
        this.captcha = captcha;
    }

    public Captcha() {
    }
}