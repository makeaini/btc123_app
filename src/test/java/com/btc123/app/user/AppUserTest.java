package com.btc123.app.user;

import com.btc123.app.Btc123AppApplicationTests;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.mapper.CaptchaMapper;
import com.btc123.app.service.InformationService;
import com.btc123.app.service.MarketService;
import com.btc123.app.service.UserServiceI;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shining on 2018/5/19.
 */
public class AppUserTest extends Btc123AppApplicationTests{
    @Autowired
    private CaptchaMapper captchaMapper;
    @Autowired
    private MarketService marketService;

    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private InformationService informationService;
//    @Test
//    public void aa(){
//        captchaMapper.selectByPrimaryKey(52L);
//    }

    @Test
    public void bb(){
        QueryPageBean queryPageBean = new QueryPageBean();
        queryPageBean.setPageNumber(5);
        queryPageBean.setPageSize(10);
        marketService.save();
//        marketService.queryCurrency(queryPageBean,null,null);
//        marketService.queryHandicap("zbbtcusdt");
//        marketService.queryCurrencyDetail("ETH");
//        marketService.queryTrade(queryPageBean,null,null,"ETH");
//        marketService.queryUserMarket(1l);
//        marketService.queryDepth("binancebtcont");
//        marketService.queryTradeData("zbbtcusdt");
//        marketService.searchMarket("zbbtcusdt",1l);
//        marketService.queryKline("zbbtcusdt",2);
//        informationService.articleLike(1l,"0",null);
    }
}
