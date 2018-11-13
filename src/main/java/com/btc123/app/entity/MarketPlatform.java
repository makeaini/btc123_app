package com.btc123.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MarketPlatform {
    private Long id;

    private String createOpr;

    private Date createTime;

    private String remark;

    private Long status;

    private String updateOpr;

    private Date updateTime;

    private String country;

    private String faceBookUrl;

    private String logo;

    private String name;

    private Date setupDate;

    private String sign;

    private String tiwwterUrl;

    private String weiboUrl;

    private String website;

    private Long showPosition;

    private Long stock;

    private Long futures;

    private Long lever;

    private BigDecimal lumpSum;

    private Long transPair;

    private String webUrl;

    private Integer sortOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateOpr() {
        return createOpr;
    }

    public void setCreateOpr(String createOpr) {
        this.createOpr = createOpr == null ? null : createOpr.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getUpdateOpr() {
        return updateOpr;
    }

    public void setUpdateOpr(String updateOpr) {
        this.updateOpr = updateOpr == null ? null : updateOpr.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getFaceBookUrl() {
        return faceBookUrl;
    }

    public void setFaceBookUrl(String faceBookUrl) {
        this.faceBookUrl = faceBookUrl == null ? null : faceBookUrl.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getTiwwterUrl() {
        return tiwwterUrl;
    }

    public void setTiwwterUrl(String tiwwterUrl) {
        this.tiwwterUrl = tiwwterUrl == null ? null : tiwwterUrl.trim();
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl == null ? null : weiboUrl.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public Long getShowPosition() {
        return showPosition;
    }

    public void setShowPosition(Long showPosition) {
        this.showPosition = showPosition;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getFutures() {
        return futures;
    }

    public void setFutures(Long futures) {
        this.futures = futures;
    }

    public Long getLever() {
        return lever;
    }

    public void setLever(Long lever) {
        this.lever = lever;
    }

    public BigDecimal getLumpSum() {
        return lumpSum;
    }

    public void setLumpSum(BigDecimal lumpSum) {
        this.lumpSum = lumpSum;
    }

    public Long getTransPair() {
        return transPair;
    }

    public void setTransPair(Long transPair) {
        this.transPair = transPair;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}