package com.btc123.app.model.information;

import com.btc123.app.entity.IEntity;
import com.btc123.app.utils.DateUtils;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;
import java.util.List;
@ApiObject(name = "快讯模块", description = "快讯模块")
public class FlashGroupRes implements IEntity {
    @ApiObjectField(name = "isCollect",description = "快讯更新日期")
    private  String gmtModifiedDay;

    @ApiObjectField(name = "flashList",description = "快讯数据")
    private List<FlashRes> flashList;

    public String getGmtModifiedDay() {
        return gmtModifiedDay;
    }

    public void setGmtModifiedDay(String gmtModifiedDay) {
        this.gmtModifiedDay = gmtModifiedDay;
    }

    public List<FlashRes> getFlashList() {
        return flashList;
    }

    public void setFlashList(List<FlashRes> flashList) {
        this.flashList = flashList;
    }
}