package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/****
 * 历史成交数据
 * @author Administrator
 *
 */
@ApiObject(name = "历史成交数据",description = "历史成交数据")
public class TradesTimeDataBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**id**/
	@Id
	@ApiObjectField(name = "id",description = "id")
	private String id;

	/**时间点**/
	@ApiObjectField(name = "times",description = "时间点")
	private Long times;

	/**最高价**/
	@ApiObjectField(name = "high",description = "最高价")
	private Double high;

	/**最低价**/
	@ApiObjectField(name = "low",description = "最低价")
	private Double low;

	/**中间价**/
	@ApiObjectField(name = "middle",description = "中间价")
	private Double middle;

	/**开盘价**/
	@ApiObjectField(name = "open",description = "开盘价")
	private Double open;

	/**收盘价**/
	@ApiObjectField(name = "close",description = "收盘价")
	private Double close;

	/**成交量**/
	@ApiObjectField(name = "amount",description = "成交量")
	private Double amount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getTimes() {
		return times;
	}
	public void setTimes(Long times) {
		this.times = times;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getMiddle() {
		return middle;
	}

	public void setMiddle(Double middle) {
		this.middle = middle;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double tradesTimeData() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmount() {
		return amount;
	}

	
}
