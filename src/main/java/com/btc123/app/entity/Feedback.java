package com.btc123.app.entity;

import java.util.Date;

public class Feedback implements IEntity{
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long userId;

    private String email;

    private String mobile;

    private String sourceLink;

    private Integer readStatus;

    private String content;

    private String processRecord;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink == null ? null : sourceLink.trim();
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getProcessRecord() {
        return processRecord;
    }

    public void setProcessRecord(String processRecord) {
        this.processRecord = processRecord == null ? null : processRecord.trim();
    }

    public Feedback(Long userId, String mobile, String content) {
        this.userId = userId;
        this.mobile = mobile;
        this.content = content;
    }

    public Feedback() {
    }
}