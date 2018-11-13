package com.btc123.app.service;


import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.model.market.*;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.sql.Savepoint;
import java.util.List;

/**
 * Created by shining on 2018/5/11.
 */
public interface MarketService {

    void addUserMarket(String sign, HttpServletRequest request);

    void deleteUserMarket(String sign, HttpServletRequest request);

    CoinDetailBean queryCurrencyDetail(String sign,HttpServletRequest request,String sortField,String sortOrder);

    List<CoinMarketBean> queryUserMarket(HttpServletRequest request);

    List<CoinMarketBean> queryCurrency(QueryPageBean queryPageBean, String sortField, String sortOrder,Integer type);

    List<TradeBean> queryTrade(QueryPageBean queryPageBean,String sortOrder);

    DepthDataBean queryDepth(String symbol);

    HandicapBean queryHandicap(String symbol);

    TradeDataBean queryTradeData(String symbol);

    List<CoinMarketBean> searchMarket(String keyword,HttpServletRequest request);

    PageInfo<MarketNoticeRes> queryMarketNotice(QueryPageBean queryPageBean);

    TradesTimeDataBeanRes queryKline(String symbol, Integer type,Long userId);

    List<IndexMarketBean> queryMarketIndex();

    void editUserMarket(String ids, String sorts);

    List<TradeDetailBean> queryTradeDetail(QueryPageBean queryPageBean, String platFormSign,String sortField,String sortOrder);

    TradeDetailSummaryBean tradeDetailSummary(String platFormSign);

    void save();
}
