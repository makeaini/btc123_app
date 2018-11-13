package com.btc123.app.model.information;

import com.btc123.app.entity.IEntity;
import com.btc123.app.utils.DateUtils;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.math.BigDecimal;
import java.util.Date;

@ApiObject(name = "文章模块", description = "文章模块")
public class ArticleRes implements IEntity {
    @ApiObjectField(name = "id", description = "文章id")
    private Long id;

    @ApiObjectField(name = "gmtModified", description = "文章更新时间")
    private Date gmtModified;

    @ApiObjectField(name = "gmtModifiedTrans", description = "文章更新时间对应的分钟，小时，天等")
    private String gmtModifiedTrans;

    @ApiObjectField(name = "title", description = "文章标题")
    private String title;

    @ApiObjectField(name = "picUrl", description = "封面图路径")
    private String picUrl;

    @ApiObjectField(name = "browseNum", description = "文章浏览量")
    private Long browseNum;

    @ApiObjectField(name = "categoryName", description = "文章分类名称")
    private String categoryName;

    @ApiObjectField(name = "isBrowse", description = "是否浏览")
    private boolean isBrowse;

    @ApiObjectField(name = "displayStatus", description = "显示状态")
    private Integer displayStatus;

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

    public String getGmtModifiedTrans() {
        return DateUtils.formatTime(getGmtModified().getTime());
    }

    public void setGmtModifiedTrans(String gmtModifiedTrans) {
        this.gmtModifiedTrans = gmtModifiedTrans;
    }

    public Long getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Long browseNum) {
        this.browseNum = browseNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isBrowse() {
        return isBrowse;
    }

    public void setBrowse(boolean browse) {
        isBrowse = browse;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }
}