package com.btc123.app.controller.v1;

import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.ReturnBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.enumeration.ParamEnum;
import com.btc123.app.exception.ProParamException;
import com.btc123.app.model.market.*;
import com.btc123.app.service.MarketService;
import com.github.pagehelper.PageInfo;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("/{version}/market/"))
@Api(name = "行情接口", description = "行情接口")
@ApiVersion(since = "1.0")
public class MarketController extends BaseV1Controller{
    @Autowired
    private MarketService marketService;


    @RequestMapping(value = "currency",method = RequestMethod.POST)
    @ApiMethod(summary = "币种分页列表", description = "币种分页列表")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageSize", required = false, clazz = Integer.class, description = "页尺寸"),
            @ApiQueryParam(name = "pageNumber", required = false, clazz = Integer.class, description = "当前页"),
            @ApiQueryParam(name = "sortField", required = false, clazz = String.class, description = "排序字段"),
            @ApiQueryParam(name = "sortOrder", required = false, clazz = String.class, description = "排序方式(升序:ASC,降序:DESC)"),
            @ApiQueryParam(name = "type", required = false, clazz = Integer.class, description = "涨跌幅排序(1:涨幅;2:跌幅)")})
    public ReturnBean queryCurrency(QueryPageBean queryPageBean,String sortField,String sortOrder,Integer type) throws ProParamException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<CoinMarketBean> coinMarketBeans = marketService.queryCurrency(queryPageBean,sortField,sortOrder,type);
        returnBean.setData(coinMarketBeans);
        return returnBean;
    }

    @RequestMapping(value = "currencyDetail",method = RequestMethod.POST)
    @ApiMethod(summary = "币种详情", description = "币种详情")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "signs", required = true, clazz = String.class, description = "币种简称"),
            @ApiQueryParam(name = "sortField", required = false, clazz = String.class, description = "排序字段"),
            @ApiQueryParam(name = "sortOrder", required = false, clazz = String.class, description = "排序方式(升序:ASC,降序:DESC)")})
    public ReturnBean queryCurrencyDetail(String signs,HttpServletRequest request,String sortField,String sortOrder) throws ProParamException {
        Optional.ofNullable(signs).orElseThrow(()->new ProParamException(ParamEnum.SIGN.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        CoinDetailBean coinDetailBean = marketService.queryCurrencyDetail(signs,request,sortField,sortOrder);
        returnBean.setData(coinDetailBean);
        return returnBean;
    }

    @RequestMapping(value = "trade",method = RequestMethod.POST)
    @ApiMethod(summary = "平台分页列表", description = "平台分页列表")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageSize", required = true, clazz = Integer.class, description = "页尺寸"),
            @ApiQueryParam(name = "pageNumber", required = true, clazz = Integer.class, description = "当前页"),
            @ApiQueryParam(name = "sortOrder", required = false, clazz = String.class, description = "排序方式(升序:ASC,降序:DES)")})
    public ReturnBean queryTrade(QueryPageBean queryPageBean,String sortOrder) throws ProParamException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<TradeBean> tradeBeanList = marketService.queryTrade(queryPageBean,sortOrder);
        returnBean.setData(tradeBeanList);
        return returnBean;
    }

    @RequestMapping(value = "tradeDetail",method = RequestMethod.POST)
    @ApiMethod(summary = "交易所详情页交易对", description = "交易所详情页交易对")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageSize", required = true, clazz = Integer.class, description = "页尺寸"),
            @ApiQueryParam(name = "pageNumber", required = true, clazz = Integer.class, description = "当前页"),
            @ApiQueryParam(name = "platFormSign", required = true, clazz = String.class, description = "交易所标识"),
            @ApiQueryParam(name = "sortField", required = false, clazz = String.class, description = "排序字段"),
            @ApiQueryParam(name = "sortOrder", required = false, clazz = String.class, description = "排序方式(升序:ASC,降序:DESC)")})
    public ReturnBean queryTradeDetail(QueryPageBean queryPageBean,String platFormSign,String sortField,String sortOrder) throws ProParamException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<TradeDetailBean> tradeDetailBeanList = marketService.queryTradeDetail(queryPageBean,platFormSign,sortField,sortOrder);
        returnBean.setData(tradeDetailBeanList);
        return returnBean;
    }

    @RequestMapping(value = "tradeDetailSummary",method = RequestMethod.POST)
    @ApiMethod(summary = "交易所详情页简况", description = "交易所详情页简况")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "platFormSign", required = true, clazz = String.class, description = "交易所标识")})
    public ReturnBean tradeDetailSummary(String platFormSign) throws ProParamException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        TradeDetailSummaryBean tradeDetailSummaryBean = marketService.tradeDetailSummary(platFormSign);
        returnBean.setData(tradeDetailSummaryBean);
        return returnBean;
    }

    @RequestMapping(value = "kline",method = RequestMethod.GET)
    @ApiMethod(summary = "k线", description = "k线")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "symbol", required = true, clazz = String.class, description = "行情唯一标识"),
            @ApiQueryParam(name = "type", required = true, clazz = Integer.class, description = "type :2(1分钟)，3(1小时),4(24小时),5(15分钟),6(30分钟),7(12小时)")})
    public ReturnBean queryKline(String symbol,Integer type) throws ProParamException {
        Optional.ofNullable(symbol).orElseThrow(()->new ProParamException(ParamEnum.SYMBOL.getMessage()));
        Optional.ofNullable(type).orElseThrow(()->new ProParamException(ParamEnum.TYPE.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Long userId = getUserId();
        TradesTimeDataBeanRes tradesTimeDataBeanRes = marketService.queryKline(symbol,type,userId);
        returnBean.setData(tradesTimeDataBeanRes);
        return returnBean;
    }

    @RequestMapping(value = "queryUserMarket",method = RequestMethod.POST)
    @ApiMethod(summary = "自选分页列表", description = "自选分页列表")
    public ReturnBean queryUserMarket(HttpServletRequest request) throws ProParamException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<CoinMarketBean> coinMarketBeans = marketService.queryUserMarket(request);
        returnBean.setData(coinMarketBeans);
        return returnBean;
    }


    @RequestMapping(value = "addUserMarket",method = RequestMethod.POST)
    @ApiMethod(summary = "添加自选", description = "添加自选")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "signs", required = true, clazz = String.class, description = "币种简称")})
    public ReturnBean addUserMarket(String signs, HttpServletRequest request) throws ProParamException {
        Optional.ofNullable(signs).orElseThrow(()->new ProParamException(ParamEnum.SIGN.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        marketService.addUserMarket(signs,request);
        return returnBean;
    }

    @RequestMapping(value = "deleteUserMarket",method = RequestMethod.POST)
    @ApiMethod(summary = "删除自选", description = "删除自选")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "signs", required = true, clazz = String.class, description = "币种简称")})
    public ReturnBean deleteUserMarket(String signs,HttpServletRequest request) throws ProParamException {
        Optional.ofNullable(signs).orElseThrow(()->new ProParamException(ParamEnum.SIGN.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        marketService.deleteUserMarket(signs,request);
        return returnBean;
    }

    @RequestMapping(value = "editUserMarket",method = RequestMethod.POST)
    @ApiMethod(summary = "编辑自选", description = "编辑自选")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "ids", required = true, clazz = String.class, description = "id拼接字符串"),
            @ApiQueryParam(name = "sorts", required = true, clazz = String.class, description = "排序号拼接字符串")})
    public ReturnBean editUserMarket(String ids,String sorts) throws ProParamException {
        Optional.ofNullable(ids).orElseThrow(()->new ProParamException(ParamEnum.IDS.getMessage()));
        Optional.ofNullable(sorts).orElseThrow(()->new ProParamException(ParamEnum.SORTS.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        marketService.editUserMarket(ids,sorts);
        return returnBean;
    }

    @RequestMapping(value = "depth",method = RequestMethod.GET)
    @ApiMethod(summary = "深度", description = "深度")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "symbol", required = true, clazz = String.class, description = "行情唯一标识")})
    public ReturnBean queryDepth(String symbol) throws ProParamException {
        Optional.ofNullable(symbol).orElseThrow(()->new ProParamException(ParamEnum.SYMBOL.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        DepthDataBean depthDataBean = marketService.queryDepth(symbol);
        returnBean.setData(depthDataBean);
        return returnBean;
    }

    @RequestMapping(value = "handicap",method = RequestMethod.GET)
    @ApiMethod(summary = "盘口", description = "盘口")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "symbol", required = true, clazz = String.class, description = "行情唯一标识")})
    public ReturnBean queryHandicap(String symbol) throws ProParamException {
        Optional.ofNullable(symbol).orElseThrow(()->new ProParamException(ParamEnum.SYMBOL.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        HandicapBean handicapBean = marketService.queryHandicap(symbol);
        returnBean.setData(handicapBean);
        return returnBean;
    }

    @RequestMapping(value = "tradeData",method = RequestMethod.GET)
    @ApiMethod(summary = "成交", description = "成交")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "symbol", required = true, clazz = String.class, description = "行情唯一标识")})
    public ReturnBean queryTradeData(String symbol) throws ProParamException {
        Optional.ofNullable(symbol).orElseThrow(()->new ProParamException(ParamEnum.SYMBOL.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        TradeDataBean tradeDataBean = marketService.queryTradeData(symbol);
        returnBean.setData(tradeDataBean);
        return returnBean;
    }

    @RequestMapping(value = "searchMarket",method = RequestMethod.POST)
    @ApiMethod(summary = "搜索", description = "搜索")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "keyword", required = true, clazz = String.class, description = "搜索关键字")})
    public ReturnBean searchMarket(String keyword,HttpServletRequest request) throws ProParamException {
        Optional.ofNullable(keyword).orElseThrow(()->new ProParamException(ParamEnum.KEYWORD.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<CoinMarketBean> coinMarketBeans = marketService.searchMarket(keyword,request);
        returnBean.setData(coinMarketBeans);
        return returnBean;
    }

    @RequestMapping(value = "marketNotice",method = RequestMethod.GET)
    @ApiMethod(summary = "行情公告分页列表", description = "行情公告分页列表")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageSize", required = true, clazz = Integer.class, description = "页尺寸"),
            @ApiQueryParam(name = "pageNumber", required = true, clazz = Integer.class, description = "当前页")})
    public ReturnBean queryMarketNotice(QueryPageBean queryPageBean) throws ProParamException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        PageInfo<MarketNoticeRes> marketNoticeResPageInfo = marketService.queryMarketNotice(queryPageBean);
        returnBean.setData(marketNoticeResPageInfo);
        return returnBean;
    }

    @RequestMapping(value = "marketIndex",method = RequestMethod.POST)
    @ApiMethod(summary = "首页指数", description = "首页指数")
    public ReturnBean queryMarketIndex(){
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<IndexMarketBean> indexMarketBeans =  marketService.queryMarketIndex();
        returnBean.setData(indexMarketBeans);
        return returnBean;
    }

}
