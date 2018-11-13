package com.btc123.app.model.market;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

public class MarketNoticeRes {

    @ApiObjectField(name = "id",description = "公告id")
    private Long id;

    @ApiObjectField(name = "gmtCreate",description = "创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date gmtCreate;

    @ApiObjectField(name = "gmtModified",description = "更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date gmtModified;

    @ApiObjectField(name = "platformName",description = "平台名称")
    private Long platformName;

    @ApiObjectField(name = "title",description = "公告标题")
    private String title;

    @ApiObjectField(name = "url",description = "公告连接")
    private String url;

    @ApiObjectField(name = "name",description = "交易所名称")
    private String name;

    @ApiObjectField(name = "logo",description = "交易所logo")
    private String logo;

    @ApiObjectField(name = "publishTime",description = "发布时间")
    private Date publishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getPlatformName() {
        return platformName;
    }

    public void setPlatformName(Long platformName) {
        this.platformName = platformName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}