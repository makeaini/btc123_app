package com.btc123.app.model.market;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;
import java.util.List;

/**
 * Created by shining on 2018/6/29.
 */
@ApiObject(name = "币种详情交易对和信息计算",description = "币种详情交易对和信息计算")
public class CoinDetailBean {
    /**
     * 中文名
     */
    @ApiObjectField(name = "cnName",description = "中文名")
    private String cnName;

    /**
     * 流通市值比特币
     */
    @ApiObjectField(name = "circulationAmountCNY",description = "流通市值人民币")
    private String circulationAmountCNY;

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
     * 换手率
     */
    @ApiObjectField(name = "turnoverRate",description = "换手率")
    private String turnoverRate;

    /**
     * 24小时交易额人民币
     */
    @ApiObjectField(name = "tradeAmountCNY",description = "24小时交易额人民币")
    private String tradeAmountCNY;

    /**
     * 交易对行情
     */
    @ApiObjectField(name = "coinTradingMarketBeans",description = "交易对行情")
    private List<TickerBean> coinTradingMarketBeans;

    /**
     * 发行时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @ApiObjectField(name = "issueDate",description = "发行时间")
    private Date issueDate;

    /**
     * 简介
     */
    @ApiObjectField(name = "summary",description = "简介")
    private String summary;

    /**
     * 是否自选
     */
    @ApiObjectField(name = "isUserMarketApp",description = "是否自选")
    private Boolean isUserMarketApp=false;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getCirculationAmountCNY() {
        return circulationAmountCNY;
    }

    public void setCirculationAmountCNY(String circulationAmountCNY) {
        this.circulationAmountCNY = circulationAmountCNY;
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

    public String getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(String turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public String getTradeAmountCNY() {
        return tradeAmountCNY;
    }

    public void setTradeAmountCNY(String tradeAmountCNY) {
        this.tradeAmountCNY = tradeAmountCNY;
    }

    public List<TickerBean> getCoinTradingMarketBeans() {
        return coinTradingMarketBeans;
    }

    public void setCoinTradingMarketBeans(List<TickerBean> coinTradingMarketBeans) {
        this.coinTradingMarketBeans = coinTradingMarketBeans;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getSummary() {
        return summary.replaceAll("<[.[^<]]*>", "").replaceAll("<br\\s*/?>", "").replaceAll("&nbsp","").trim();
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getUserMarketApp() {
        return isUserMarketApp;
    }

    public void setUserMarketApp(Boolean userMarketApp) {
        isUserMarketApp = userMarketApp;
    }
}
