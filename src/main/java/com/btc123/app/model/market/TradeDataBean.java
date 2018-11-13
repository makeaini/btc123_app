package com.btc123.app.model.market;

import java.io.Serializable;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Id;

@ApiObject(name = "历史成交",description = "历史成交")
public class TradeDataBean implements Serializable{
	/**
	 *
	 */
	@ApiObjectField(name = "id",description = "id")
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	/**
	 * 成交量
	 */
	@ApiObjectField(name = "amount",description = "成交量")
	private double amount;
	/**
	 *成交价格
	 */
	@ApiObjectField(name = "price",description = "成交价格")
	private Double price;
	/**
	 *
	 * 成交时间
	 */
	@ApiObjectField(name = "time",description = "成交时间")
	private Long time;
	/**
	 *类型
	 */
	@ApiObjectField(name = "type",description = "类型")
	private String type;
	/**
	 * 交易id
	 */
	@ApiObjectField(name = "tid",description = "交易id")
	private Long tid;


	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
