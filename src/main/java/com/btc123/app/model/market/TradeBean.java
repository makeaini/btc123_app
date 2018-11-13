package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/****
 * 平台列表
 * @author Administrator
 *
 */
@ApiObject(name = "平台列表",description = "平台列表")
public class TradeBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**交易所标识**/
	@ApiObjectField(name = "platFormSign",description = "交易所标识")
	private String platFormSign;

	/**交易所名称**/
	@ApiObjectField(name = "platFormName",description = "交易所名称")
	private String platFormName;

	/**24H成交额**/
	@ApiObjectField(name = "turnover",description = "24H成交额")
	private BigDecimal turnover;

	/**24H成交额**/
	@ApiObjectField(name = "turnoverStr",description = "24H成交额")
	private String turnoverStr;

    /**24H成交量**/
    @ApiObjectField(name = "vol",description = "24H成交量")
    private BigDecimal vol;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPlatFormSign() {
		return platFormSign;
	}

	public void setPlatFormSign(String platFormSign) {
		this.platFormSign = platFormSign;
	}

	public String getPlatFormName() {
		return platFormName;
	}

	public void setPlatFormName(String platFormName) {
		this.platFormName = platFormName;
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

    public String getTurnoverStr() {
        return String.valueOf(turnover);
    }

    public void setTurnoverStr(String turnoverStr) {
        this.turnoverStr = turnoverStr;
    }
}
