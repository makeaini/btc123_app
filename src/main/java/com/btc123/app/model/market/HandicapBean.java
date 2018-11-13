package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Created by shining on 2018/6/29.
 */
@ApiObject(name = "盘口",description = "盘口")
public class HandicapBean {
    /**
     * 成交额
     */
    @ApiObjectField(name = "turnover",description = "成交额")
    private Double turnover;
    /**
     * 成交量
     */
    @ApiObjectField(name = "vol",description = "成交量")
    private Double vol;
    /**
     *买一
     */
    @ApiObjectField(name = "cnybuy",description = "买一")
    private Double cnybuy;
    /**
     * 卖一
     */
    @ApiObjectField(name = "cnysell",description = "卖一")
    private Double cnysell;
    /**
     *涨跌幅
     */
    @ApiObjectField(name = "upDown",description = "涨跌幅")
    private Double upDown;
    /**
     *收盘价
     */
    @ApiObjectField(name = "last",description = "收盘价")
    private Double last;
    /**
     * 委比
     *
     * (委买手数－委卖手数)/(委买手数+委卖手数)×100%
     委买手数：所有个股委托买入下五档的总数量。
     　　委卖手数：所有个股委托卖出上五档的总数量。
     */
    @ApiObjectField(name = "appointment",description = "委比")
    private Double  appointment;
    /**
     *振幅(24小时最高价-24时小最低价）：昨日收盘价)
     */
    @ApiObjectField(name = "amplitude",description = "振幅(24小时最高价-24时小最低价）：昨日收盘价)")
    private Double amplitude;

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    public Double getCnybuy() {
        return cnybuy;
    }

    public void setCnybuy(Double cnybuy) {
        this.cnybuy = cnybuy;
    }

    public Double getCnysell() {
        return cnysell;
    }

    public void setCnysell(Double cnysell) {
        this.cnysell = cnysell;
    }

    public Double getUpDown() {
        return upDown;
    }

    public void setUpDown(Double upDown) {
        this.upDown = upDown;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getAppointment() {
        return appointment;
    }

    public void setAppointment(Double appointment) {
        this.appointment = appointment;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }
}
