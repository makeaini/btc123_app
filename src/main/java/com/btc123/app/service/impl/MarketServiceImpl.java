package com.btc123.app.service.impl;

import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.entity.*;
import com.btc123.app.mapper.*;
import com.btc123.app.model.information.FlashRes;
import com.btc123.app.model.market.*;
import com.btc123.app.service.MarketService;

import com.btc123.app.utils.BeanMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by shining on 2018/5/11.
 */
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private UserMarketMapper userMarketMapper;

    @Autowired
    private CoinTypeMapper coinTypeMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MarketNoticeMapper marketNoticeMapper;

    @Autowired
    private UserMarketAppMapper userMarketAppMapper;

    @Autowired
    private MarketPlatformMapper marketPlatformMapper;

    @Value("${ip}")
    private String ip;

    @Override
    public List<CoinMarketBean> queryCurrency(QueryPageBean queryPageBean, String sortField, String sortOrder, Integer type) {
        String paramsStr = "pageSize={pageSize}&pageNumber={pageNumber}";
        if (sortField != null) {
            paramsStr += "&sortField={sortField}";
        }
        if (sortOrder != null) {
            paramsStr += "&sortOrder={sortOrder}";
        }
        if (type != null) {
            paramsStr += "&type={type}";
        }
        String url = ip + "/market/findCoinMarket?" + paramsStr;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, queryPageBean.getPageSize(), queryPageBean.getPageNumber(), sortField, sortOrder, type);
        List<CoinMarketBean> coinMarketBeans = BeanMapper.mapList(responseEntity.getBody(), CoinMarketBean.class);
        if (!coinMarketBeans.isEmpty()) {
            for (CoinMarketBean coinMarketBean : coinMarketBeans) {
                coinMarketBean.setCnyVol24(coinMarketBean.getCnyVol24().setScale(2, BigDecimal.ROUND_HALF_UP));
//                if (coinMarketBean.getCnyVol24().compareTo(new BigDecimal(1))>=0){
//                    BigDecimal bigDecimal = coinMarketBean.getCnyVol24().divide(new BigDecimal(10000));
//                    coinMarketBean.setCnyVol24Str(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).toString()+"亿");
//                    coinMarketBean.setCnyVol24(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP));
//                }else{
//                    coinMarketBean.setCnyVol24Str(coinMarketBean.getCnyVol24().setScale(2,BigDecimal.ROUND_HALF_UP).toString()+"万");
//                }
            }
        }
        return coinMarketBeans;
    }

    @Override
    public CoinDetailBean queryCurrencyDetail(String sign, HttpServletRequest request, String sortField, String sortOrder) {
        String paramsStr = "sign={sign}";
        if (sortField != null) {
            paramsStr += "&sortField={sortField}";
        }
        if (sortOrder != null) {
            paramsStr += "&sortOrder={sortOrder}";
        }
        String url = ip + "/market/findCoinDetailBySign?" + paramsStr;
        ResponseEntity<CoinDetailBean> responseEntity = restTemplate.getForEntity(url, CoinDetailBean.class, sign, sortField, sortOrder);
        CoinDetailBean coinDetailBean = responseEntity.getBody();
        //查询简介等基本信息
        CoinType coinType = coinTypeMapper.selectBySign(sign);
        coinDetailBean.setCnName(coinType.getCnName());
        coinDetailBean.setIssueDate(coinType.getIssueDate());
        coinDetailBean.setSummary(coinType.getSummary());
        //判断是否自选

        String token = request.getHeader("token");
        UserMarketApp userMarketApp = new UserMarketApp();
        userMarketApp.setSign(sign);

        userMarketApp.setToken(token);
        Integer count = userMarketAppMapper.selectCountByTokenAndSign(userMarketApp);
        if (count > 0) {
            coinDetailBean.setUserMarketApp(true);
        }
        return coinDetailBean;
    }

    @Override
    public List<TradeBean> queryTrade(QueryPageBean queryPageBean, String sortOrder) {
        //查询所有交易平台
        List<MarketPlatform> platforms = marketPlatformMapper.selectAllPlatform();
        List<TradeBean> tradeBeanList = new ArrayList<>();
        if (!platforms.isEmpty()) {
            for (MarketPlatform platform : platforms) {
                TradeBean tradeBean = new TradeBean();
                String paramsStr = "sign={sign}";
                String url = ip + "/market/queryTrade?" + paramsStr;
                ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, platform.getSign());
                if (!responseEntity.getBody().isEmpty()) {
                    List<TradeBean> tradeBeans = BeanMapper.mapList(responseEntity.getBody(), TradeBean.class);
                    BigDecimal total = new BigDecimal(0);
                    for (TradeBean tradeBean1 : tradeBeans) {
                        total = total.add(tradeBean1.getTurnover() == null ? new BigDecimal(0) : tradeBean1.getTurnover());
                    }
                    if (platform.getSign().equals("gateio")) {
                        total = total.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    tradeBean.setTurnover(total.setScale(2, BigDecimal.ROUND_HALF_UP));
                    tradeBean.setPlatFormName(tradeBeans.get(0).getPlatFormName());
                    tradeBean.setPlatFormSign(platform.getSign());
                    tradeBeanList.add(tradeBean);
                } else {
                    tradeBean.setTurnover(new BigDecimal(0));
                    tradeBean.setPlatFormName(platform.getName());
                    tradeBean.setPlatFormSign(platform.getSign());
                    tradeBeanList.add(tradeBean);
                    tradeBeanList.remove(tradeBean);
                }
            }
        }

        if (sortOrder == null) {
            sortOrder = "DESC";
        }
        if (sortOrder.equals("ASC")) {
            Collections.sort(tradeBeanList, new Comparator<TradeBean>() {
                public int compare(TradeBean arg0, TradeBean arg1) {
                    BigDecimal turnover0 = arg0.getTurnover();
                    BigDecimal turnover1 = arg1.getTurnover();
                    if (turnover0.doubleValue() > turnover1.doubleValue()) {
                        return 1;
                    } else if (turnover0 == turnover1) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        } else {
            Collections.sort(tradeBeanList, new Comparator<TradeBean>() {
                public int compare(TradeBean arg0, TradeBean arg1) {
                    BigDecimal turnover0 = arg0.getTurnover();
                    BigDecimal turnover1 = arg1.getTurnover();
                    if (turnover0.doubleValue() > turnover1.doubleValue()) {
                        return -1;
                    } else if (turnover0.doubleValue() == turnover1.doubleValue()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        return pageList(tradeBeanList, queryPageBean);
    }

    /**
     * 分页
     *
     * @param list
     * @param queryPageBean
     * @return
     */
    public List pageList(List list, QueryPageBean queryPageBean) {
        int count = 0;
        if (list != null && list.size() > 0) {
            count = list.size();
            int fromIndex = (queryPageBean.getPageNumber() - 1) * queryPageBean.getPageSize();
            int toIndex = queryPageBean.getPageNumber() * queryPageBean.getPageSize();
            if (toIndex > count) {
                toIndex = count;
            }
            return list.subList(fromIndex, toIndex);
        }
        return list;
    }

    @Override
    public List<TradeDetailBean> queryTradeDetail(QueryPageBean queryPageBean, String platFormSign, String sortField, String sortOrder) {
        sortField = sortField == null ? "upDown" : sortField;
        sortOrder = sortOrder == null ? "desc" : sortOrder;
        String paramsStr = "sign={sign}&sortField={sortField}&sortOrder={sortOrder}";
        String url = ip + "/market/queryTrade?" + paramsStr;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, platFormSign, sortField, sortOrder);
        List<TradeDetailBean> tradeDetailBeans = new ArrayList<>();
        if (!responseEntity.getBody().isEmpty()) {
            tradeDetailBeans = BeanMapper.mapList(responseEntity.getBody(), TradeDetailBean.class);
        }
        return pageList(tradeDetailBeans, queryPageBean);
    }

    @Override
    public TradeDetailSummaryBean tradeDetailSummary(String platFormSign) {
        MarketPlatformWithBLOBs marketPlatformWithBLOBs = marketPlatformMapper.selectBySign(platFormSign);
        TradeDetailSummaryBean tradeDetailSummaryBean = new TradeDetailSummaryBean();
        if (marketPlatformWithBLOBs != null) {
            tradeDetailSummaryBean.setPlatFormSign(marketPlatformWithBLOBs.getSign());
            tradeDetailSummaryBean.setCountry(marketPlatformWithBLOBs.getCountry());
            tradeDetailSummaryBean.setWebsite(marketPlatformWithBLOBs.getWebsite());
            tradeDetailSummaryBean.setSummary(marketPlatformWithBLOBs.getSummary());
            //拼接支持交易
            String supportTransaction = "";
            if (marketPlatformWithBLOBs.getStock() == 1) {
                supportTransaction += "现货、";
            }
            if (marketPlatformWithBLOBs.getFutures() == 1) {
                supportTransaction += "期货、";
            }
            if (marketPlatformWithBLOBs.getLever() == 1) {
                supportTransaction += "杠杆、";
            }
            if (!supportTransaction.equals("")) {
                String str = supportTransaction.substring(supportTransaction.length() - 1, supportTransaction.length());
                if (str.equals("、")) {
                    supportTransaction = supportTransaction.substring(0, supportTransaction.length() - 1);
                }
            }
            tradeDetailSummaryBean.setSupportTransaction(supportTransaction);
        }
        String paramsStr = "sign={sign}";
        String url = ip + "/market/queryTrade?" + paramsStr;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, platFormSign);
        List<TradeBean> tradeBeans = new ArrayList<>();
        if (!responseEntity.getBody().isEmpty()) {
            tradeBeans = BeanMapper.mapList(responseEntity.getBody(), TradeBean.class);
            BigDecimal total = new BigDecimal(0);
            BigDecimal totalVol = new BigDecimal(0);
            for (TradeBean tradeBean : tradeBeans) {
                total = total.add(tradeBean.getTurnover() == null ? new BigDecimal(0) : tradeBean.getTurnover());
                totalVol = totalVol.add(tradeBean.getVol() == null ? new BigDecimal(0) : tradeBean.getVol());
            }
            BigDecimal bigDecimal = total.divide(new BigDecimal(10000));
            tradeDetailSummaryBean.setTurnover(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));
            tradeDetailSummaryBean.setVol(totalVol);
            tradeDetailSummaryBean.setTransactionPair(tradeBeans.size());

        }
        return tradeDetailSummaryBean;
    }

    @Override
    public List<CoinMarketBean> queryUserMarket(HttpServletRequest request) {
        String token = request.getHeader("token");
        List<UserMarketApp> userMarketApps = userMarketAppMapper.selectByToken(request.getHeader("token"));
        List<CoinMarketBean> coinMarketBeans = new ArrayList<>();
        if (!userMarketApps.isEmpty()) {
            for (UserMarketApp userMarketApp : userMarketApps) {
                String url = ip + "/market/queryUserMarket?sign={sign}";
                ResponseEntity<CoinMarketBean> responseEntity = restTemplate.getForEntity(url, CoinMarketBean.class, userMarketApp.getSign());
                CoinMarketBean coinMarketBean = responseEntity.getBody();
                coinMarketBean.setSort(userMarketApp.getSort());
                coinMarketBean.setId(userMarketApp.getId());
                coinMarketBeans.add(coinMarketBean);
            }
        }
        return coinMarketBeans;
    }

    @Override
    public DepthDataBean queryDepth(String symbol) {
        String url = ip + "/market/findMarketDepthBySymbol?symbol={symbol}";
        ResponseEntity<DepthDataBean> responseEntity = restTemplate.getForEntity(url, DepthDataBean.class, symbol);
        return responseEntity.getBody();
    }

    @Override
    public void addUserMarket(String sign, HttpServletRequest request) {
        UserMarketApp userMarketApp = new UserMarketApp();
        userMarketApp.setSign(sign);
        String token = request.getHeader("token");
        if (token != null) {
            //判断是否重复添加
            userMarketApp.setSign(sign);
            userMarketApp.setToken(token);
            Integer count = userMarketAppMapper.selectCountByTokenAndSign(userMarketApp);
            if (count == 0) {
                userMarketApp.setToken(token);
                //查询最大排序号
                Long sort = userMarketAppMapper.selectByMaxSort(token);
                userMarketApp.setSort(sort + 1);
                userMarketAppMapper.insertSelective(userMarketApp);
            }
        }
    }

    @Override
    public void deleteUserMarket(String sign, HttpServletRequest request) {
        String[] arr = sign.split(",");
        if (arr.length > 0) {
            for (String str : arr) {
                UserMarketApp userMarketApp = new UserMarketApp();
                userMarketApp.setSign(str);
                userMarketApp.setToken(request.getHeader("token"));
                userMarketAppMapper.deleteUserMarket(userMarketApp);
            }
        }
    }

    @Override
    public void editUserMarket(String ids, String sorts) {
        String[] idArr = ids.split(",");
        String[] sortArr = sorts.split(",");
        if (idArr.length > 0) {
            for (int i = 0; i < idArr.length; i++) {
                UserMarketApp userMarketApp = new UserMarketApp();
                userMarketApp.setId(Long.parseLong(idArr[i]));
                userMarketApp.setSort(Long.parseLong(sortArr[i]));
                userMarketAppMapper.updateByPrimaryKey(userMarketApp);
            }
        }
    }

    @Override
    public HandicapBean queryHandicap(String symbol) {
        String url = ip + "/market/HandicapBeanBySymbol?symbol={symbol}";
        ResponseEntity<HandicapBean> responseEntity = restTemplate.getForEntity(url, HandicapBean.class, symbol);
        return responseEntity.getBody();
    }

    @Override
    public TradeDataBean queryTradeData(String symbol) {
        String url = ip + "/market/findTradeDataBySymbol?symbol={symbol}";
        ResponseEntity<TradeDataBean> responseEntity = restTemplate.getForEntity(url, TradeDataBean.class, symbol);
        return responseEntity.getBody();
    }

    @Override
    public List<CoinMarketBean> searchMarket(String keyword, HttpServletRequest request) {
        String url = ip + "/market/searchMarket?sign={sign}";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, keyword);
        List<CoinMarketBean> coinMarketBeans = BeanMapper.mapList(responseEntity.getBody(), CoinMarketBean.class);
        if (!coinMarketBeans.isEmpty()) {
            for (CoinMarketBean coinMarketBean : coinMarketBeans) {
                //判断是否自选
                String token = request.getHeader("token");
                UserMarketApp userMarketApp = new UserMarketApp();
                userMarketApp.setSign(coinMarketBean.getSign());
                userMarketApp.setToken(token);
                Integer count = userMarketAppMapper.selectCountByTokenAndSign(userMarketApp);
                if (count > 0) {
                    coinMarketBean.setUserMarketApp(true);
                } else {
                    coinMarketBean.setUserMarketApp(false);
                }
            }
        }
        return coinMarketBeans;
    }

    @Override
    public PageInfo<MarketNoticeRes> queryMarketNotice(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getPageNumber(), queryPageBean.getPageSize());
        List<MarketNoticeRes> marketNoticeRes = marketNoticeMapper.queryMarketNotice();
        PageInfo<MarketNoticeRes> marketNoticeResPageInfo = new PageInfo<MarketNoticeRes>(marketNoticeRes);
        return marketNoticeResPageInfo;
    }

    @Override
    public TradesTimeDataBeanRes queryKline(String symbol, Integer type, Long userId) {
        String url = ip + "/market/selectKlineByTime?symbol={symbol}&type={type}";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, symbol, type);
        List<TradesTimeDataBean> tradesTimeDataBeans = BeanMapper.mapList(responseEntity.getBody(), TradesTimeDataBean.class);
        TradesTimeDataBeanRes tradesTimeDataBeanRes = new TradesTimeDataBeanRes();
        tradesTimeDataBeanRes.setTradesTimeDataBeans(tradesTimeDataBeans);
        if (userId != null) {
            //判断是否自选
            UserMarket userMarket = new UserMarket();
            userMarket.setSymbol(symbol);
            userMarket.setUserId(userId);
            List<UserMarket> userMarkets = userMarketMapper.isUserMarket(userMarket);


            if (!userMarkets.isEmpty()) {
                tradesTimeDataBeanRes.setIsUserMarket(true);
            }
        }
        return tradesTimeDataBeanRes;
    }

    @Override
    public List<IndexMarketBean> queryMarketIndex() {
        String url = ip + "/market/selectCurrencyConverter";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        List<IndexMarketBean> indexMarketBeans = BeanMapper.mapList(responseEntity.getBody(), IndexMarketBean.class);
        List<IndexMarketBean> indexMarketBeanList = new ArrayList<>();
        //TODO:暂时确定为三个指数
        String[] coins = new String[]{"BTC", "ETH", "BCH"};
        if (!indexMarketBeans.isEmpty()) {
            for (IndexMarketBean indexMarketBean : indexMarketBeans) {
                for (String coin : coins) {
                    if (indexMarketBean.getName().equals(coin)) {
                        //计算涨幅值
                        BigDecimal bPrice = new BigDecimal(Double.toString(indexMarketBean.getPrice()));
                        BigDecimal bUpDown = new BigDecimal(Double.toString(indexMarketBean.getUpDown()));
                        BigDecimal bUpDownPrice = bPrice.multiply(bUpDown).multiply(new BigDecimal(0.01));
                        indexMarketBean.setUpDownPrice(bUpDownPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        indexMarketBeanList.add(indexMarketBean);
                    }
                }
            }
        }
        return indexMarketBeanList;
    }

    //    @Autowired
//    private UserTchatMapper userTchatMapper;
//
//    @Autowired
//    private TuserMapper tuserMapper;
//
//    @Autowired
//    private IdentityTchatMapper identityTchatMapper;
//
//    @Autowired
//    private TuserIdentityMapper tuserIdentityMapper;
//
//    /**
//     * 新添加的获取图片，
//     *
//     * @param path
//     *            图片路径
//     * @param
//     */
//    private static String getImgs(String path) {
//        if (StringUtils.isBlank(path)) {
//            return null;
//        }
//        String url;
//        if (path.startsWith("alioss_")) {
//            url = "https://pro-tchat-oss.oss-cn-beijing.aliyuncs.com/" + path;
//        } else {
//            String suffix = path.substring(path.lastIndexOf("."));
//            String fileName = toMD5(path) + suffix;
//            url = "https://pro-tchat-oss.oss-cn-beijing.aliyuncs.com/" + fileName;
//        }
//        return url;
//    }
//
//    @Override
//    @Transactional
//    public void save(){
//        List<UserTchat> userTchats = userTchatMapper.selectAll();
//        if (!userTchats.isEmpty()){
//            for (int i=0;i<userTchats.size();i++){
//                Tuser tuser = new Tuser();
//                UserTchat userTchat=userTchats.get(i);
//                tuser.setCellphone(userTchat.getPhone());
//                tuser.setNickName(userTchat.getName());
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    tuser.setBirthday(formatter.parse(userTchat.getBirthday()));
//                    tuser.setCreateTime(formatter1.parse(userTchat.getCreatetime()));
//                    if (userTchat.getLogintime()!=null){
//                        tuser.setUpdateTime(formatter1.parse(userTchat.getLogintime()));
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                tuser.setSex(userTchat.getSex());
//                tuser.setEnabled(1);
//                tuser.setOrigin(2);
//                tuser.setApproveStatus(1);
//                Tuser tuser1 = tuserMapper.selectByPhone(userTchat.getPhone());
//                if (tuser1==null){
//                    tuserMapper.insert(tuser);
//                    List<IdentityTchat> identityTchats = identityTchatMapper.selectByPrimaryKey1(userTchat.getId());
//                    if (!identityTchats.isEmpty()){
//                        TuserIdentity tuserIdentity = new TuserIdentity();
//                        IdentityTchat identityTchat =identityTchats.get(0);
//                        String positiveimg = identityTchat.getPositiveimg();
//                        tuserIdentity.setPositiveImg(getImgs(positiveimg.substring(positiveimg.indexOf("=")+1, positiveimg.length())));
//                        String backimg = identityTchat.getBackimg();
//                        tuserIdentity.setBackImg(getImgs(backimg.substring(backimg.indexOf("=")+1, backimg.length())));
//                        String holdimg = identityTchat.getHoldimg();
//                        tuserIdentity.setHoldImg(getImgs(holdimg.substring(holdimg.indexOf("=")+1, holdimg.length())));
//                        if (tuser.getId()==null){
//                            int a=1/0;
//                        }
//                        tuserIdentity.setUserId(tuser.getId());
//                        try {
//                            tuserIdentity.setCreateTime(formatter1.parse(identityTchat.getCreatetime()));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        tuserIdentity.setState(identityTchat.getState());
//                        tuserIdentityMapper.insert(tuserIdentity);
//                        if (identityTchat.getState()==1){
//                            tuser.setApproveStatus(2);
//                            tuserMapper.updateByPrimaryKey(tuser);
//                        }
//                    }
//                }
//            }
//        }
//    }
    @Override
    @Transactional
    public void save() {
    }

    public static String toMD5(String plainText) {
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
