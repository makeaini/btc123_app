package com.btc123.app.mapper;

import com.btc123.app.entity.ArticleCollectApp;
import com.btc123.app.entity.ArticleLikeApp;

import java.util.List;

public interface ArticleCollectAppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCollectApp record);

    int insertSelective(ArticleCollectApp record);

    ArticleCollectApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleCollectApp record);

    int updateByPrimaryKey(ArticleCollectApp record);

    int deleteByTokenAndArticleId(ArticleCollectApp articleCollectApp);

    List<ArticleCollectApp> queryByTokenAndArticle(ArticleCollectApp articleCollectApp);
}