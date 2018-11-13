package com.btc123.app.model.information;

import com.btc123.app.entity.IEntity;
import com.btc123.app.utils.DateUtils;
import com.btc123.app.utils.PublicMethods;
import com.btc123.app.utils.StringUtils;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by btc on 2018/5/16.
 */
@ApiObject(name = "快讯内容模块", description = "快讯内容模块")
public class FlashRes implements IEntity {

    @ApiObjectField(name = "id",description = "快讯id")
    private Long id;

    @ApiObjectField(name = "gmtModified",description = "快讯更新时间")
    private Date gmtModified;

    @ApiObjectField(name = "gmtModifiedDay",description = "快讯更新日期")
    private String gmtModifiedDay;

    @ApiObjectField(name = "gmtModifiedTime",description = "快讯更新具体时间")
    private String gmtModifiedTime;

    @ApiObjectField(name = "title",description = "快讯标题")
    private String title;

    @ApiObjectField(name = "content",description = "快讯内容")
    private String content;

    @ApiObjectField(name = "displayStatus",description = "快讯显示状态")
    private Integer displayStatus;

    @ApiObjectField(name = "gmtModifiedDayLong",description = "快讯时间时间戳")
    private Long gmtModifiedDayLong;

    @ApiObjectField(name = "weekDays",description = "星期")
    private String weekDays;

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

    public String getContent() {
        if (content!=null){
            return StringUtils.getRegExHtml(content);
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getGmtModifiedDay() {
        String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(gmtModified).toString().split("-");
        Integer year = Integer.parseInt(strNow[0]);
        Integer month = Integer.parseInt(strNow[1]);
        Integer day = Integer.parseInt(strNow[2]);
        return year+"年"+month+"月"+day+"日";
    }

    public void setGmtModifiedDay(String gmtModifiedDay) {
        this.gmtModifiedDay = gmtModifiedDay;
    }

    public String getGmtModifiedTime() {
        return DateUtils.getTime(gmtModified);
    }

    public void setGmtModifiedTime(String gmtModifiedTime) {
        this.gmtModifiedTime = gmtModifiedTime;
    }

    public Long getGmtModifiedDayLong() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        try {
            return  df.parse(DateUtils.getDate(gmtModified)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTitle() {
        if (title!=null){
            return StringUtils.getRegExHtml(title);
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGmtModifiedDayLong(Long gmtModifiedDayLong) {
        this.gmtModifiedDayLong = gmtModifiedDayLong;
    }

    public String getWeekDays() {
        return PublicMethods.getWeekOfDate(new Date());
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }

}
