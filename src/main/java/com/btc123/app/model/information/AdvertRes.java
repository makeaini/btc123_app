package com.btc123.app.model.information;

import com.btc123.app.entity.Advert;
import com.btc123.app.entity.IEntity;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Created by btc on 2018/5/15.
 */
@ApiObject(name = "广告模块",description = "广告模块")
public class AdvertRes implements IEntity{
    @ApiObjectField(name = "id",description = "广告位id")
    private Long id;

    @ApiObjectField(name = "title",description = "广告位标题")
    private String title;

    @ApiObjectField(name = "picUrl",description = "广告位图片路径")
    private String picUrl;

    @ApiObjectField(name = "url",description = "广告位链接")
    private String url;

    @ApiObjectField(name = "sourceId",description = "广告位来源id")
    private Long sourceId;

    @ApiObjectField(name = "advertType",description = "广告位类型")
    private Integer advertType;

    @ApiObjectField(name = "sortOrder",description = "广告位排序号")
    private Long sortOrder;

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

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAdvertType() {
        return advertType;
    }

    public void setAdvertType(Integer advertType) {
        this.advertType = advertType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
