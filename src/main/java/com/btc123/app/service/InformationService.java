package com.btc123.app.service;

import com.btc123.app.bean.PageBean;
import com.btc123.app.entity.Article;
import com.btc123.app.model.information.*;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by btc on 2018/5/15.
 */
public interface InformationService {

    public List<AdvertRes> queryAdvertBySourceId(Long positionId);

    public PageBean<ArticleRes> queryArticle(Integer pageSize, Integer pageNumber,HttpServletRequest request);

    PageBean<FlashRes> queryFlash(Integer pageSize, Integer pageNumber);

    ArticleDetailRes queryArticleDetail(Long articleId,HttpServletRequest request);

    void articleLike(Long articleId, String isLike, HttpServletRequest request);

    void articleCollect(Long articleId, String isCollect,HttpServletRequest request);

    FlashRes queryFlashDetail(Long flashId, HttpServletRequest request);

    PageBean<ArticleRes> queryHeadlines(Integer pageSize, Integer pageNumber, HttpServletRequest request);
}
