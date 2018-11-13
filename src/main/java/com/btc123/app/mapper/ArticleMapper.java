package com.btc123.app.mapper;

import com.btc123.app.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> queryArticle();

    /**
     * 查询用户收藏 的id
     * @param userId 用户id
     * @return
     */
    List<Article> queryCollectArticleByUserId( Long userId);

    Article selectByPrimaryKey1(Long articleId);

    /**
     * 查询用户收藏 的id
     * @param token 用户token
     * @return
     */
    List<Article> queryCollectArticleByToken(String token);

    List<Article> queryHeadlines();
}