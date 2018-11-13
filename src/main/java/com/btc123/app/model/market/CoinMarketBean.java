package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by shining on 2018/6/29.
 * 币种列表
 */
@ApiObject(name = "币种列表",description = "币种列表")
public class CoinMarketBean implements Serializable {

    /**id**/
    @ApiObjectField(name = "id",description = "id")
    private Long   id;
    /**中文名**/
    @ApiObjectField(name = "cnName",description = "中文名")
    private String cnName;
    /**英文全称**/
    @ApiObjectField(name = "enName",description = "英文全称")
    private String enName;
    /**简称**/
    @ApiObjectField(name = "sign",description = "简称")
    private String sign;
    /**logo地址**/
    @ApiObjectField(name = "logo",description = "logo地址")
    private String logo;
    /**价格**/
    @ApiObjectField(name = "priceCny",description = "价格-cny")
    private BigDecimal priceCny;
    /**价格**/
    @ApiObjectField(name = "priceCnyStr",description = "价格-cnyStr")
    private String priceCnyStr;
    /**价格**/
    @ApiObjectField(name = "priceUsd",description = "价格-usd")
    private BigDecimal priceUsd;
    /**24H涨跌幅**/
    @ApiObjectField(name = "percentChange24h",description = "24H涨跌幅")
    private BigDecimal percentChange24h;
    /**流通市值*/
    @ApiObjectField(name = "cnyVolTotal",description = "流通市值")
    private BigDecimal cnyVolTotal;
    /**流通量*/
    @ApiObjectField(name = "currencyValue",description = "流通量")
    private BigDecimal currencyValue;
    /**24成交额*/
    @ApiObjectField(name = "cnyVol24",description = "24成交额")
    private BigDecimal cnyVol24;

    /**24成交额加单位*/
    @ApiObjectField(name = "cnyVol24Str",description = "24成交额加单位")
    private String cnyVol24Str;
    /**24成交量*/
    @ApiObjectField(name = "vol24",description = "24成交量")
    private BigDecimal vol24;
    /**
     * 是否自选
     */
    @ApiObjectField(name = "isUserMarketApp",description = "是否自选")
    private Boolean isUserMarketApp=false;

    /**排序号*/
    @ApiObjectField(name = "sort",description = "排序号")
    private Long sort;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BigDecimal getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(BigDecimal priceUsd) {
        this.priceUsd = priceUsd;
    }

    public void setCurrencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
    }

    public BigDecimal getPriceCny() {
        return priceCny;
    }

    public void setPriceCny(BigDecimal priceCny) {
        this.priceCny = priceCny;
    }

    public BigDecimal getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(BigDecimal percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public BigDecimal getCnyVolTotal() {
        return cnyVolTotal;
    }

    public void setCnyVolTotal(BigDecimal cnyVolTotal) {
        this.cnyVolTotal = cnyVolTotal;
    }

    public BigDecimal getCurrencyValue() {
        return currencyValue;
    }

    public BigDecimal getCnyVol24() {
        return cnyVol24;
    }

    public void setCnyVol24(BigDecimal cnyVol24) {
        this.cnyVol24 = cnyVol24;
    }

    public BigDecimal getVol24() {
        return vol24;
    }

    public void setVol24(BigDecimal vol24) {
        this.vol24 = vol24;
    }

    public Boolean getUserMarketApp() {
        return isUserMarketApp;
    }

    public void setUserMarketApp(Boolean userMarketApp) {
        isUserMarketApp = userMarketApp;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getCnyVol24Str() {
        return cnyVol24Str;
    }

    public void setCnyVol24Str(String cnyVol24Str) {
        this.cnyVol24Str = cnyVol24Str;
    }

    public String getPriceCnyStr() {
        return String.valueOf(priceCny);
    }

    public void setPriceCnyStr(String priceCnyStr) {
        this.priceCnyStr = priceCnyStr;
    }
}
