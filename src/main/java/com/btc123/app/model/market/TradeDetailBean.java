package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;
import java.math.BigDecimal;

/****
 * 平台交易对列表
 * @author Administrator
 *
 */
@ApiObject(name = "平台交易对列表",description = "平台交易对列表")
public class TradeDetailBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**交易所标识**/
	@ApiObjectField(name = "platFormSign",description = "交易所标识")
	private String platFormSign;

	/**24H涨跌幅**/
	@ApiObjectField(name = "upDown",description = "24H涨跌幅")
	private BigDecimal upDown;

	/**最新成交价**/
	@ApiObjectField(name = "last",description = "最新成交价")
	private BigDecimal last;

	/**最新成交价**/
	@ApiObjectField(name = "lastStr",description = "最新成交价")
	private String lastStr;

	/**币种简称**/
	@ApiObjectField(name = "coinSign",description = "币种简称")
	private String coinSign;

	/**币种兑换类型**/
	@ApiObjectField(name = "moneyType",description = "币种兑换类型")
	private String moneyType;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPlatFormSign() {
		return platFormSign;
	}

	public void setPlatFormSign(String platFormSign) {
		this.platFormSign = platFormSign;
	}

	public BigDecimal getUpDown() {
		return upDown;
	}

	public void setUpDown(BigDecimal upDown) {
		this.upDown = upDown;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public String getCoinSign() {
		return coinSign;
	}

	public void setCoinSign(String coinSign) {
		this.coinSign = coinSign;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

    public String getLastStr() {
        return last.toString();
    }

    public void setLastStr(String lastStr) {
        this.lastStr = lastStr;
    }
}
