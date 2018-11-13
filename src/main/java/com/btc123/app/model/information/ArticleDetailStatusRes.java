package com.btc123.app.model.information;

import com.btc123.app.entity.Article;
import com.btc123.app.utils.DateUtils;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

@ApiObject(name = "文章详情是否收藏和点赞状态",description = "文章详情是否收藏和点赞状态")
public class ArticleDetailStatusRes {

    @ApiObjectField(name = "isLike",description = "当前用户是否点赞")
    private boolean isLike=false;

    @ApiObjectField(name = "isCollect",description = "当前用户是否收藏")
    private boolean isCollect=false;

    @ApiObjectField(name = "likeNum", description = "文章分点赞量")
    private Long likeNum;

    @ApiObjectField(name = "collectNum", description = "文章分收藏量")
    private Long collectNum;

    public ArticleDetailStatusRes() {
    }

    public ArticleDetailStatusRes(Article article) {
        this.likeNum = article.getLikeNum();
        this.collectNum = article.getCollectNum();
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
}