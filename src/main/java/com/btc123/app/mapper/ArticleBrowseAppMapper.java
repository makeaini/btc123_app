package com.btc123.app.mapper;

import com.btc123.app.entity.ArticleBrowseApp;

import java.util.List;

public interface ArticleBrowseAppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleBrowseApp record);

    int insertSelective(ArticleBrowseApp record);

    ArticleBrowseApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleBrowseApp record);

    int updateByPrimaryKey(ArticleBrowseApp record);

    List<ArticleBrowseApp> selectByTokenAndArticleId(ArticleBrowseApp articleBrowseApp);
}