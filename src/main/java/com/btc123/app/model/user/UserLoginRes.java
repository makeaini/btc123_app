package com.btc123.app.model.user;

import com.btc123.app.entity.IEntity;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Created by shining on 2018/5/15.
 */
@ApiObject(name = " 登录成功后返回用户信息", description = "登录成功后返回用户信息")
public class UserLoginRes implements IEntity {
    private static final long serialVersionUID = 1L;
    @ApiObjectField(name = "nickname", description = "昵称")
    private String nickname;
    @ApiObjectField(name = "mobile", description = "手机号")
    private String mobile;
    @ApiObjectField(name = "token", description = "登录token")
    private String token;
    @ApiObjectField(name = "avatar", description = "头像")
    private String avatar;
    @ApiObjectField(name = "id", description = "用户id")
    private Long userId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserLoginRes(String nickname, String mobile, String token, String avatar, Long userId) {
        this.nickname = nickname;
        this.mobile = mobile;
        this.token = token;
        this.avatar = avatar;
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public UserLoginRes(String nickname, String mobile, String avatar, Long userId) {
        this.nickname = nickname;
        this.mobile = mobile;
        this.avatar = avatar;
        this.userId = userId;
    }

    public UserLoginRes() {
    }
}
