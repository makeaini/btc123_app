package com.btc123.app.mapper;

import com.btc123.app.entity.Advert;

import java.util.List;

public interface AdvertMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Advert record);

    int insertSelective(Advert record);

    Advert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Advert record);

    int updateByPrimaryKey(Advert record);

    List<Advert> queryAdvertBySourceId(Long positionId);
}