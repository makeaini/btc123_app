package com.btc123.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Article {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private String picUrl;

    private Long categoryId;

    private String tag;

    private Byte articleType;

    private Byte displayStatus;

    private Boolean isCanComment;

    private Boolean isTiming;

    private Date timingPublishTime;

    private Long creatorId;

    private Byte publishStatus;

    private String refuseReason;

    private Long sortOrder;

    private Boolean isFrontPublish;

    private Long browseNum;

    private Long likeNum;

    private Long collectNum;

    private String source;

    private String sourceLink;

    private BigDecimal weight;

    private Long auditorId;

    private Date auditTime;

    private Long modifierId;

    private Boolean isPrize;

    private String summary;

    private String categoryName;

    private String content;

    private boolean isBrowse;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Byte getArticleType() {
        return articleType;
    }

    public void setArticleType(Byte articleType) {
        this.articleType = articleType;
    }

    public Byte getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Byte displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Boolean getIsCanComment() {
        return isCanComment;
    }

    public void setIsCanComment(Boolean isCanComment) {
        this.isCanComment = isCanComment;
    }

    public Boolean getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(Boolean isTiming) {
        this.isTiming = isTiming;
    }

    public Date getTimingPublishTime() {
        return timingPublishTime;
    }

    public void setTimingPublishTime(Date timingPublishTime) {
        this.timingPublishTime = timingPublishTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Byte getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Byte publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsFrontPublish() {
        return isFrontPublish;
    }

    public void setIsFrontPublish(Boolean isFrontPublish) {
        this.isFrontPublish = isFrontPublish;
    }

    public Long getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Long browseNum) {
        this.browseNum = browseNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Long collectNum) {
        this.collectNum = collectNum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink == null ? null : sourceLink.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Boolean getIsPrize() {
        return isPrize;
    }

    public void setIsPrize(Boolean isPrize) {
        this.isPrize = isPrize;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBrowse() {
        return isBrowse;
    }

    public void setBrowse(boolean browse) {
        isBrowse = browse;
    }
}