package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "首页指数",description = "首页指数")
public class IndexMarketBean {

    @ApiObjectField(name = "name",description = "简称")
    private String name;
    @ApiObjectField(name = "price",description = "价格")
    private Double price;

    @ApiObjectField(name = "upDown",description = "涨幅率")
    private Double upDown;

    @ApiObjectField(name = "upDownPrice",description = "涨幅值")
    private Double upDownPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getUpDown() {
        return upDown;
    }

    public void setUpDown(Double upDown) {
        this.upDown = upDown;
    }

    public Double getUpDownPrice() {
        return upDownPrice;
    }

    public void setUpDownPrice(Double upDownPrice) {
        this.upDownPrice = upDownPrice;
    }
}