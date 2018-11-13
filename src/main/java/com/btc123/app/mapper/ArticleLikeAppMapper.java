package com.btc123.app.mapper;

import com.btc123.app.entity.ArticleLikeApp;

import java.util.List;

public interface ArticleLikeAppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleLikeApp record);

    int insertSelective(ArticleLikeApp record);

    ArticleLikeApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleLikeApp record);

    int updateByPrimaryKey(ArticleLikeApp record);

    int deleteByTokenAndArticleId(ArticleLikeApp articleLikeApp);

    List<ArticleLikeApp> queryByTokenAndArticle(ArticleLikeApp articleLikeApp);
}