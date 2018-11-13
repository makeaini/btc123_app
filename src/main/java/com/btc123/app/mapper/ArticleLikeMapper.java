package com.btc123.app.mapper;

import com.btc123.app.entity.ArticleLike;

import java.util.List;
import java.util.Map;

public interface ArticleLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleLike record);

    int insertSelective(ArticleLike record);

    ArticleLike selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleLike record);

    int updateByPrimaryKey(ArticleLike record);

    List<ArticleLike> queryByUserIdAndArticle(ArticleLike articleLike);

    void deleteByUserIdAndArticleId(ArticleLike articleLike);
}