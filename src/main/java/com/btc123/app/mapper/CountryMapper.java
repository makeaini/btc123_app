package com.btc123.app.mapper;

import com.btc123.app.entity.Country;

public interface CountryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}