package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;
import java.math.BigDecimal;

/****
 * 平台交易简况
 * @author Administrator
 *
 */
@ApiObject(name = "平台交易简况",description = "平台交易简况")
public class TradeDetailSummaryBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**交易所标识**/
	@ApiObjectField(name = "platFormSign",description = "交易所标识")
	private String platFormSign;

	/**24H成交额**/
	@ApiObjectField(name = "turnover",description = "24H成交额")
	private BigDecimal turnover;

	/**24H成交量**/
	@ApiObjectField(name = "vol",description = "24H成交量")
	private BigDecimal vol;

	/**交易对数量**/
	@ApiObjectField(name = "transactionPair",description = "交易对数量")
	private Integer transactionPair;

	/**支持交易**/
	@ApiObjectField(name = "supportTransaction",description = "支持交易")
	private String supportTransaction;

	/**地区国家**/
	@ApiObjectField(name = "country",description = "地区国家")
	private String country;

	/**官网地址**/
	@ApiObjectField(name = "website",description = "官网地址")
	private String website;

	/**简介**/
	@ApiObjectField(name = "summary",description = "简介")
	private String summary;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPlatFormSign() {
		return platFormSign;
	}

	public void setPlatFormSign(String platFormSign) {
		this.platFormSign = platFormSign;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public BigDecimal getVol() {
		return vol;
	}

	public void setVol(BigDecimal vol) {
		this.vol = vol;
	}

	public Integer getTransactionPair() {
		return transactionPair;
	}

	public void setTransactionPair(Integer transactionPair) {
		this.transactionPair = transactionPair;
	}

	public String getSupportTransaction() {
		return supportTransaction;
	}

	public void setSupportTransaction(String supportTransaction) {
		this.supportTransaction = supportTransaction;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
