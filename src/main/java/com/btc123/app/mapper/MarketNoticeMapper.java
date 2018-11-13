package com.btc123.app.mapper;

import com.btc123.app.entity.MarketNotice;
import com.btc123.app.model.market.MarketNoticeRes;

import java.util.List;

public interface MarketNoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MarketNotice record);

    int insertSelective(MarketNotice record);

    MarketNotice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarketNotice record);

    int updateByPrimaryKey(MarketNotice record);

    List<MarketNoticeRes> queryMarketNotice();
}