package com.btc123.app.entity;

import java.util.Date;

public class UserLoginLog implements IEntity{
    private Long id;

    private Date loginTime;

    private Long userId;

    private Integer deviceType;

    private String deviceId;

    private Integer loginType;

    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public UserLoginLog(Date loginTime, Long userId, Integer deviceType, String deviceId,String ip) {
        this.loginTime = loginTime;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.ip = ip;
    }

    public UserLoginLog() {
    }
}