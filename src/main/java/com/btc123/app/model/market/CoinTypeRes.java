package com.btc123.app.model.market;

import com.btc123.app.entity.CoinType;
import com.btc123.app.entity.IEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

/**
 * Created by btc on 2018/5/15.
 */
@ApiObject(name = "币种详情简介",description = "币种详情简介")
public class CoinTypeRes implements IEntity{
    @ApiObjectField(name = "id",description = "币种id")
    private Long id;

    @ApiObjectField(name = "cnName",description = "中文名")
    private String cnName;

    @ApiObjectField(name = "enName",description = "英文名")
    private String enName;

    /**
     * 总发行量
     */
    @ApiObjectField(name = "totalValue",description = "总发行量")
    private String totalValue;
    /**
     *流通量
     */
    @ApiObjectField(name = "currencyValue",description = "流通量")
    private String currencyValue;

    /**
     *流通市值人民币
     */
    @ApiObjectField(name = "circulationAmountCNY",description = "流通市值人民币")
    private String circulationAmountCNY;

    /**
     * 24小时交易额人民币
     */
    @ApiObjectField(name = "tradeAmountCNY",description = "24小时交易额人民币")
    private String tradeAmountCNY;

    /**
     * 换手率
     */
    @ApiObjectField(name = "turnoverRate",description = "换手率")
    private String turnoverRate;

    /**
     * 发行时间
     */
    @ApiObjectField(name = "issueDate",description = "发行时间")
    private Date issueDate;

    /**
     * 简介
     */
    @ApiObjectField(name = "summary",description = "简介")
    private String summary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getCirculationAmountCNY() {
        return circulationAmountCNY;
    }

    public void setCirculationAmountCNY(String circulationAmountCNY) {
        this.circulationAmountCNY = circulationAmountCNY;
    }

    public String getTradeAmountCNY() {
        return tradeAmountCNY;
    }

    public void setTradeAmountCNY(String tradeAmountCNY) {
        this.tradeAmountCNY = tradeAmountCNY;
    }

    public String getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(String turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
