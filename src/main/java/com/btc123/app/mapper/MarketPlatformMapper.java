package com.btc123.app.mapper;

import com.btc123.app.entity.MarketPlatform;
import com.btc123.app.entity.MarketPlatformWithBLOBs;
import com.btc123.app.model.market.TradeDetailSummaryBean;

import java.util.List;

public interface MarketPlatformMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MarketPlatformWithBLOBs record);

    int insertSelective(MarketPlatformWithBLOBs record);

    MarketPlatformWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarketPlatformWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MarketPlatformWithBLOBs record);

    int updateByPrimaryKey(MarketPlatform record);

    List<MarketPlatform> selectAllPlatform();

    MarketPlatformWithBLOBs selectBySign(String sign);
}