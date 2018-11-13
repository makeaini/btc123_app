package com.btc123.app.entity;

public class UserSetting implements IEntity{
    private Long id;

    private Long userId;

    private Integer settingType;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSettingType() {
        return settingType;
    }

    public void setSettingType(Integer settingType) {
        this.settingType = settingType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public UserSetting(Integer state) {
        this.state = state;
    }

    public UserSetting() {
    }

    public UserSetting(Long userId, Integer settingType, Integer state) {
        this.userId = userId;
        this.settingType = settingType;
        this.state = state;
    }
}