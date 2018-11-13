package com.btc123.app.mapper;

import com.btc123.app.entity.Article;
import com.btc123.app.entity.Flash;

import java.util.List;

public interface FlashMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Flash record);

    int insertSelective(Flash record);

    Flash selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Flash record);

    int updateByPrimaryKey(Flash record);

    List<Flash> queryFlash();

    Flash selectByPrimaryKey1(Long id);
}