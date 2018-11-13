package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Created by shining on 2018/6/29.
 * 币种详情交易对行情数据
 */
@ApiObject(name = "币种详情交易对行情数据",description = "币种详情交易对行情数据")
public class CoinTradingMarketBean {
    private static final long serialVersionUID = 4637074710822193344L;
    /**
     * 交易所id
     */
    @ApiObjectField(name = "id",description = "交易所id")
    private Long id;
    /**
     * 交易所名称
     */
    @ApiObjectField(name = "platFromName",description = "交易所名称")
    private String platFromName;
    /**
     * 币种标识
     */
    @ApiObjectField(name = "coinSign",description = "币种标识")
    private String coinSign;
    /**
     * 币种中文名
     */
    @ApiObjectField(name = "coinName",description = "币种中文名")
    private String coinName;
    /**
     * 目标币种
     */
    @ApiObjectField(name = "moneyType",description = "目标币种")
    private String moneyType;
    /**
     * 交易所logo
     */
    @ApiObjectField(name = "logo",description = "交易所logo")
    private String logo;
    /**
     * 最新成交价
     */
    @ApiObjectField(name = "cnylast",description = "最新成交价")
    private Double cnylast;
    /**
     * 24小时涨跌幅
     */
    @ApiObjectField(name = "upDown",description = "24小时涨跌幅")
    private Double upDown;
    /**
     *24小时最高人民币
     */
    @ApiObjectField(name = "cnyhigh",description = "24小时最高人民币")
    private Double cnyhigh;
    /**
     *24小时最低人民币
     */
    @ApiObjectField(name = "cnylow",description = "24小时最低人民币")
    private Double cnylow;
    /**
     * 24小时成交额
     */
    @ApiObjectField(name = "turnover",description = "24小时成交额")
    private Double turnover;
    /**
     * 交易对唯一标识
     */
    @ApiObjectField(name = "symbol",description = "交易对唯一标识")
    private String symbol;
    /**
     * 24小时成交量
     */
    @ApiObjectField(name = "vol",description = "24小时成交量")
    private Double vol;

    /**
     * 是否自选
     */
    @ApiObjectField(name = "isUserMarket",description = "是否自选")
    private Boolean isUserMarket=false;

    public String getCoinSign() {
        return coinSign;
    }

    public void setCoinSign(String coinSign) {
        this.coinSign = coinSign;
    }

    public void setUpDown(Double upDown) {
        this.upDown = upDown;
    }

    public void setCnylow(Double cnylow) {
        this.cnylow = cnylow;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatFromName() {
        return platFromName;
    }

    public void setPlatFromName(String platFromName) {
        this.platFromName = platFromName;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Double getCnylast() {
        return cnylast;
    }

    public void setCnylast(Double cnylast) {
        this.cnylast = cnylast;
    }


    public Double getCnyhigh() {
        return cnyhigh;
    }

    public void setCnyhigh(Double cnyhigh) {
        this.cnyhigh = cnyhigh;
    }


    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getUpDown() {
        return upDown;
    }

    public Double getCnylow() {
        return cnylow;
    }

    public Boolean getUserMarket() {
        return isUserMarket;
    }

    public void setUserMarket(Boolean userMarket) {
        isUserMarket = userMarket;
    }
}
