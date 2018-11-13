package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 深度数据
 * @author shining
 *
 */
@ApiObject(name = "深度数据",description = "深度数据")
public class DepthDataBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5017377391566399855L;
	/**
	 * 唯一标识
	 */
	@ApiObjectField(name = "symbol",description = "唯一标识")
	private String symbol;
	/**
	 * 询价
	 */
	@ApiObjectField(name = "asks",description = "询价")
	private List<List<BigDecimal>> asks;
	/**
	 * 挂出价
	 */
	@ApiObjectField(name = "bids",description = "挂出价")
	private List<List<BigDecimal>> bids;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public List<List<BigDecimal>> getAsks() {
		return asks;
	}
	public void setAsks(List<List<BigDecimal>> asks) {
		this.asks = asks;
	}
	public List<List<BigDecimal>> getBids() {
		return bids;
	}
	public void setBids(List<List<BigDecimal>> bids) {
		this.bids = bids;
	}


}
