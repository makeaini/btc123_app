package com.btc123.app.model.market;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/****
 * k线数据和自选状态
 * @author Administrator
 *
 */
@ApiObject(name = "k线数据和自选状态",description = "k线数据和自选状态")
public class TradesTimeDataBeanRes implements Serializable{

	private static final long serialVersionUID = 1L;
	/**id**/
	@ApiObjectField(name = "isUserMarket",description = "是否自选")
	private boolean isUserMarket=false;

	@ApiObjectField(name = "tradesTimeDataBeans",description = "k线数据")
	private List<TradesTimeDataBean> tradesTimeDataBeans;

	public boolean getIsUserMarket() {
		return isUserMarket;
	}

	public void setIsUserMarket(boolean isUserMarket) {
		this.isUserMarket = isUserMarket;
	}

	public List<TradesTimeDataBean> getTradesTimeDataBeans() {
		return tradesTimeDataBeans;
	}

	public void setTradesTimeDataBeans(List<TradesTimeDataBean> tradesTimeDataBeans) {
		this.tradesTimeDataBeans = tradesTimeDataBeans;
	}
}
