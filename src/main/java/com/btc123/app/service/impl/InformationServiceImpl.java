package com.btc123.app.service.impl;

import com.btc123.app.bean.PageBean;
import com.btc123.app.entity.*;
import com.btc123.app.mapper.*;
import com.btc123.app.model.information.*;
import com.btc123.app.service.InformationService;
import com.btc123.app.utils.BeanMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by btc on 2018/5/15.
 */
@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private AdvertMapper advertMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private FlashMapper flashMapper;

    @Autowired
    private ArticleLikeAppMapper articleLikeAppMapper;

    @Autowired
    private ArticleCollectAppMapper articleCollectAppMapper;

    @Autowired
    private ArticleBrowseAppMapper articleBrowseAppMapper;

    @Override
    public List<AdvertRes> queryAdvertBySourceId(Long positionId) {
        List<Advert> adverts = advertMapper.queryAdvertBySourceId(positionId);
        //返回list封装
        List<AdvertRes> advertResList=BeanMapper.mapList(adverts,AdvertRes.class);
        return advertResList;
    }

    @Override
    public PageBean<ArticleRes> queryArticle(Integer pageSize, Integer pageNumber,HttpServletRequest request) {
        PageHelper.startPage(pageNumber==null?1:pageNumber,pageSize==null?10:pageSize);
        List<Article> articleList = articleMapper.queryArticle();
        //判断用户是否浏览过
        articleList = checkBrowse(articleList,request);
        PageInfo<Article> pageInfoArticle = new PageInfo<Article>(articleList);
        //返回list封装
        List<ArticleRes> articleResList=BeanMapper.mapList(articleList,ArticleRes.class);
        PageBean<ArticleRes>  pageInfo = new PageBean<ArticleRes>(pageInfoArticle);
        pageInfo.setData(articleResList);
        return pageInfo;
    }

    @Override
    public PageBean<FlashRes> queryFlash(Integer pageSize, Integer pageNumber) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Flash> flashList = flashMapper.queryFlash();
        PageInfo<Flash> flashPageInfo = new PageInfo<Flash>(flashList);
        //返回list封装
        List<FlashRes> flashResList=BeanMapper.mapList(flashList,FlashRes.class);
        PageBean<FlashRes>  flashResPageBean = new PageBean<FlashRes>(flashPageInfo);
        flashResPageBean.setData(flashResList);
        return flashResPageBean;
    }

    @Override
    public ArticleDetailRes queryArticleDetail(Long articleId,HttpServletRequest request) {
        Article article = articleMapper.selectByPrimaryKey1(articleId);
        ArticleDetailRes articleDetailRes = new ArticleDetailRes(article);
        //判断用户是否收藏和点赞
        String token = request.getHeader("token");
        if (token!=null){
            //新增浏览量
            ArticleBrowseApp articleBrowseApp = new ArticleBrowseApp();
            articleBrowseApp.setArticleId(articleId);
            articleBrowseApp.setToken(token);
            articleBrowseApp.setGmtCreate(new Date());
            articleBrowseApp.setGmtModified(new Date());
            articleBrowseAppMapper.insert(articleBrowseApp);
            //根据用户token和文章判断是否点赞()
            ArticleLikeApp articleLikeApp = new ArticleLikeApp();
            articleLikeApp.setArticleId(articleId);
            articleLikeApp.setToken(token);
            List<ArticleLikeApp> articleLikeApps = articleLikeAppMapper.queryByTokenAndArticle(articleLikeApp);
            if(!articleLikeApps.isEmpty()){
                articleDetailRes.setIsLike(true);
            }
            ArticleCollectApp articleCollectApp = new ArticleCollectApp();
            articleCollectApp.setArticleId(articleId);
            articleCollectApp.setToken(token);
            List<ArticleCollectApp> articleCollectApps = articleCollectAppMapper.queryByTokenAndArticle(articleCollectApp);
            if(!articleCollectApps.isEmpty()){
                articleDetailRes.setIsCollect(true);
            }
        }
        return articleDetailRes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void articleLike(Long articleId, String isLike, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token!=null){
            ArticleLikeApp articleLikeApp = new ArticleLikeApp();
            articleLikeApp.setArticleId(articleId);
            articleLikeApp.setToken(token);
            if(isLike.equals("1")){
                //取消点赞，删除数据
                articleLikeAppMapper.deleteByTokenAndArticleId(articleLikeApp);
            }else{
                //点赞，新增数据
                articleLikeApp.setGmtCreate(new Date());
                articleLikeApp.setGmtModified(new Date());
                articleLikeAppMapper.insert(articleLikeApp);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void articleCollect(Long articleId, String isCollect,HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token!=null){
            ArticleCollectApp articleCollectApp = new ArticleCollectApp();
            articleCollectApp.setArticleId(articleId);
            articleCollectApp.setToken(token);
            if(isCollect.equals("1")){
                //取消收藏，删除数据
                articleCollectAppMapper.deleteByTokenAndArticleId(articleCollectApp);
            }else{
                //收藏，新增数据
                articleCollectApp.setGmtModified(new Date());
                articleCollectApp.setGmtCreate(new Date());
                articleCollectAppMapper.insert(articleCollectApp);
            }
        }
    }

    @Override
    public FlashRes
    queryFlashDetail(Long flashId, HttpServletRequest request) {
        Flash flash = flashMapper.selectByPrimaryKey1(flashId);
        FlashRes flashRes = new FlashRes();
        if (flash!=null){
            flashRes.setTitle(flash.getTitle());
            flashRes.setContent(flash.getContent());
            flashRes.setId(flash.getId());
            flashRes.setGmtModified(flash.getGmtModified());
        }else{
            return null;
        }
        return flashRes;
    }

    @Override
    public PageBean<ArticleRes> queryHeadlines(Integer pageSize, Integer pageNumber, HttpServletRequest request) {
        PageHelper.startPage(pageNumber==null?1:pageNumber,pageSize==null?10:pageSize);
        List<Article> articleList = articleMapper.queryHeadlines();
        //判断用户是否浏览过
        articleList = checkBrowse(articleList,request);
        PageInfo<Article> pageInfoArticle = new PageInfo<Article>(articleList);
        //返回list封装
        List<ArticleRes> articleResList=BeanMapper.mapList(articleList,ArticleRes.class);
        PageBean<ArticleRes>  pageInfo = new PageBean<ArticleRes>(pageInfoArticle);
        pageInfo.setData(articleResList);
        return pageInfo;
    }

    public List<Article> checkBrowse(List<Article> articleList,HttpServletRequest request){
        //判断用户是否浏览过
        if (!articleList.isEmpty()){
            for (Article article : articleList) {
                String token = request.getHeader("token");
                if (token!=null){
                    ArticleBrowseApp articleBrowseApp = new ArticleBrowseApp();
                    articleBrowseApp.setToken(token);
                    articleBrowseApp.setArticleId(article.getId());
                    List<ArticleBrowseApp> articleBrowseApps = articleBrowseAppMapper.selectByTokenAndArticleId(articleBrowseApp);
                    if (!articleBrowseApps.isEmpty()){
                        article.setBrowse(true);
                    }else{
                        article.setBrowse(false);
                    }
                }else{
                    article.setBrowse(false);
                }
            }
        }
        return articleList;
    }
}
