package com.btc123.app.mapper;

import com.btc123.app.entity.ArticleCollect;

import java.util.List;

public interface ArticleCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCollect record);

    int insertSelective(ArticleCollect record);

    ArticleCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleCollect record);

    int updateByPrimaryKey(ArticleCollect record);

    List<ArticleCollect> queryByUserIdAndArticle(ArticleCollect articleCollect);

    void deleteByUserIdAndArticleId(ArticleCollect articleCollect);
}