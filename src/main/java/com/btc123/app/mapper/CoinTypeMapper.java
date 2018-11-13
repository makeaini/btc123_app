package com.btc123.app.mapper;

import com.btc123.app.entity.CoinType;

import java.util.List;

public interface CoinTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoinType record);

    int insertSelective(CoinType record);

    CoinType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoinType record);

    int updateByPrimaryKeyWithBLOBs(CoinType record);

    int updateByPrimaryKey(CoinType record);

    List<String> searchMarket(String keyword);

    CoinType selectBySign(String sign);

    Integer countTradeNum(String sign);
}