package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/****
 * 行情详情数据
 * @author Administrator
 *
 */
@ApiObject(name = "行情详情数据",description = "行情详情数据")
public class TickerBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ApiObjectField(name = "id",description = "id")
	private String id;

	/**交易所名称**/
	@ApiObjectField(name = "platFormName",description = "交易所名称")
	private String platFormName;

	/**币种id**/
	@ApiObjectField(name = "coinId",description = "币种id")
	private Long   coinId;

	/**币种简称**/
	@ApiObjectField(name = "coinSign",description = "币种简称")
	private String coinSign;

	/**币种名称**/
	@ApiObjectField(name = "coinName",description = "币种名称")
	private String coinName;

	/**兑换币种类型**/
	@ApiObjectField(name = "moneyType",description = "兑换币种类型")
	private String moneyType;

	/**24H涨跌幅**/
	@ApiObjectField(name = "upDown",description = "24H涨跌幅")
	private Double upDown;

	/**最新成交价**/
	@ApiObjectField(name = "last",description = "最新成交价")
	private Double last;

	@ApiObjectField(name = "usdlastStr",description = "美元最新成交价(用这个)")
	private String usdlastStr;

	/**人民币最新成交价**/
	@ApiObjectField(name = "cnylast",description = "人民币最新成交价")
	private Double cnylast;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlatFormName() {
		return platFormName;
	}

	public void setPlatFormName(String platFormName) {
		this.platFormName = platFormName;
	}

	public Long getCoinId() {
		return coinId;
	}

	public void setCoinId(Long coinId) {
		this.coinId = coinId;
	}

	public String getCoinSign() {
		return coinSign;
	}

	public void setCoinSign(String coinSign) {
		this.coinSign = coinSign;
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

	public String getUsdlastStr() {
		return usdlastStr;
	}

	public void setUsdlastStr(String usdlastStr) {
		this.usdlastStr = usdlastStr;
	}

	public Double getCnylast() {
		return cnylast;
	}

	public void setCnylast(Double cnylast) {
		this.cnylast = cnylast;
	}
}
