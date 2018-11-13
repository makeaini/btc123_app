package com.btc123.app.model.information;

import com.btc123.app.entity.Article;
import com.btc123.app.entity.IEntity;
import com.btc123.app.utils.DateUtils;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

@ApiObject(name = "文章详情", description = "文章详情")
public class ArticleDetailRes implements IEntity {
    @ApiObjectField(name = "id", description = "文章id")
    private Long id;

    @ApiObjectField(name = "gmtModified", description = "文章更新时间")
    private Date gmtModified;

    @ApiObjectField(name = "gmtModifiedTime", description = "文章更新具体时间")
    private String gmtModifiedTime;

    @ApiObjectField(name = "title", description = "文章标题")
    private String title;

    @ApiObjectField(name = "summary", description = "文章摘要")
    private String summary;

    @ApiObjectField(name = "content", description = "文章内容")
    private String content;

    @ApiObjectField(name = "picUrl", description = "封面图路径")
    private String picUrl;

    @ApiObjectField(name = "likeNum", description = "文章分点赞量")
    private Long likeNum;

    @ApiObjectField(name = "collectNum", description = "文章分收藏量")
    private Long collectNum;

    @ApiObjectField(name = "source", description = "文章来源")
    private String source;

    @ApiObjectField(name = "sourceLink", description = "文章来源链接")
    private String sourceLink;

    @ApiObjectField(name = "isLike", description = "当前用户是否点赞")
    private boolean isLike = false;

    @ApiObjectField(name = "isCollect", description = "当前用户是否收藏")
    private boolean isCollect = false;

    public ArticleDetailRes() {
    }

    public ArticleDetailRes(Article article) {
        if(article!=null){
            this.id = article.getId();
            this.gmtModified = article.getGmtModified();
            this.gmtModifiedTime = DateUtils.getTime(article.getGmtModified());
            this.title = article.getTitle();
            this.picUrl = article.getPicUrl();
            this.likeNum = article.getLikeNum();
            this.collectNum = article.getCollectNum();
            this.source = article.getSource();
            this.sourceLink = article.getSourceLink();
            this.summary = article.getSummary();
            this.content=article.getContent();
        }
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getGmtModifiedTime() {
        return DateUtils.getTime(getGmtModified());
    }

    public void setGmtModifiedTime(String gmtModifiedTime) {
        this.gmtModifiedTime = gmtModifiedTime;
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
        this.source = source;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}