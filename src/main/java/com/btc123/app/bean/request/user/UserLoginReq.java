package com.btc123.app.bean.request.user;

import com.btc123.app.entity.IEntity;

/**
 * Created by shining on 2018/5/16.
 * 用户登录时所传参数
 */
public class UserLoginReq implements IEntity {

    private String username;
    private String password;
    private Integer deviceType;
    private String deviceId;
    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        this.deviceId = deviceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
