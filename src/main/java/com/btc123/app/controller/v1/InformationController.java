package com.btc123.app.controller.v1;

import com.btc123.app.bean.PageBean;
import com.btc123.app.bean.ReturnBean;
import com.btc123.app.bean.request.QueryPageBean;
import com.btc123.app.entity.Article;
import com.btc123.app.enumeration.ParamEnum;
import com.btc123.app.enumeration.SuccessEnum;
import com.btc123.app.exception.ProException;
import com.btc123.app.exception.ProParamException;
import com.btc123.app.model.information.*;
import com.btc123.app.service.InformationService;
import com.btc123.app.service.UserServiceI;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;

/**
 * Created by btc on 2018/5/15.
 */
@RestController
@RequestMapping(("/{version}/information/"))
@Api(name = "资讯", description = "资讯接口")
@ApiVersion(since = "1.0")
public class InformationController extends BaseV1Controller{

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserServiceI userServiceI;

    /**
     * 资讯首页广告列表查询
     * @param positionId
     * @return
     */
    @RequestMapping(value = "advert",method = RequestMethod.POST)
    @ApiMethod(summary = "资讯首页广告列表查询", description = "资讯首页广告列表查询")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "positionId", required = true, clazz = Long.class, description = "广告位置id(app首页广告(111),app资讯首页广告(112),app启动页广告(114))")})
    public ReturnBean queryAdvert(Long positionId) throws ProParamException {
        Optional.ofNullable(positionId).orElseThrow(()->new ProParamException(ParamEnum.POSITION_ID.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        List<AdvertRes> advertResList = informationService.queryAdvertBySourceId(positionId);
        returnBean.setData(advertResList);
        return returnBean;
    }

    @RequestMapping(value = "queryHeadlines", method = RequestMethod.POST)
    @ApiMethod(summary = "头条资讯", description = "头条资讯")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageNumber", required = false, clazz = Integer.class, defaultvalue = "1", description = "当前页"),
            @ApiQueryParam(name = "pageSize", required = false, clazz = Integer.class, defaultvalue = "10", description = "页尺寸") })
    public ReturnBean queryHeadlines(Integer pageSize,Integer pageNumber,HttpServletRequest request) {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        PageBean<ArticleRes> pageInfo = informationService.queryHeadlines(pageSize,pageNumber,request);
        returnBean.setData(pageInfo);
        return returnBean;
    }

    @RequestMapping(value = "checkUpdate",method = RequestMethod.POST)
    @ApiMethod(summary = "检查更新", description = "检查更新")
    public ReturnBean checkUpdate(){
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        return returnBean;
    }

    @RequestMapping(value = "article",method = RequestMethod.POST)
    @ApiMethod(summary = "资讯首页文章列表分页查询", description = "资讯首页文章列表分页查询")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageSize", required = false, clazz = Integer.class, description = "页尺寸"),
            @ApiQueryParam(name = "pageNumber", required = false, clazz = Integer.class, description = "当前页")})
    public ReturnBean queryArticle(Integer pageSize, Integer pageNumber, HttpServletRequest request) throws ProParamException{
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        PageBean<ArticleRes> pageInfo = informationService.queryArticle(pageSize,pageNumber,request);
        returnBean.setData(pageInfo);
        return returnBean;
    }

    @RequestMapping(value = "articleDetail",method =RequestMethod.POST)
    @ApiMethod(summary = "资讯详情", description = "资讯详情")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "articleId", required = true, clazz = long.class, description = "资讯id")})
    public ReturnBean queryArticleDetail(Long articleId,HttpServletRequest request) throws ProParamException{
        Optional.ofNullable(articleId).orElseThrow(()->new ProParamException(ParamEnum.ARTICLEID.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        ArticleDetailRes articleDetailRes = informationService.queryArticleDetail(articleId,request);
        returnBean.setData(articleDetailRes);
        return returnBean;
    }

    @RequestMapping(value = "flashDetail")
    @ApiMethod(summary = "快讯列表分页查询", description = "快讯列表分页查询")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "flashId", required = true, clazz = long.class, description = "快讯id")})
    public ReturnBean flashDetail(Long flashId,HttpServletRequest request) throws ProParamException{
        Optional.ofNullable(flashId).orElseThrow(()->new ProParamException(ParamEnum.FLASHID.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        FlashRes flashRes= informationService.queryFlashDetail(flashId,request);
        returnBean.setData(flashRes);
        return returnBean;
    }

    @RequestMapping(value = "flash")
    @ApiMethod(summary = "快讯列表分页查询", description = "快讯列表分页查询")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageSize", required = true, clazz = Integer.class, description = "页尺寸"),
            @ApiQueryParam(name = "pageNumber", required = true, clazz = Integer.class, description = "当前页")})
    public ReturnBean queryFlash(Integer pageSize,Integer pageNumber) throws ProParamException{
        Optional.ofNullable(pageSize).orElseThrow(()->new ProParamException(ParamEnum.PAGESIZE.getMessage()));
        Optional.ofNullable(pageNumber).orElseThrow(()->new ProParamException(ParamEnum.PAGENUMBER.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        PageBean<FlashRes> flashResPageBean = informationService.queryFlash(pageSize,pageNumber);
        returnBean.setData(flashResPageBean);
        return returnBean;
    }

    @RequestMapping(value = "like",method = RequestMethod.POST)
    @ApiMethod(summary = "资讯点赞和取消点赞", description = "资讯点赞和取消点赞")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "articleId", required = true, clazz = Long.class, description = "资讯id"),
            @ApiQueryParam(name = "isLike", required = true, clazz = String.class, description = "是否点赞")})
    public ReturnBean articleLike(Long articleId, String isLike, HttpServletRequest request) throws ProParamException{
        Optional.ofNullable(isLike).orElseThrow(()->new ProParamException(ParamEnum.ARTICLEISLIKE.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        informationService.articleLike(articleId,isLike,request);
        return returnBean.setReturnMsg(isLike.equals("1")? SuccessEnum.CANCELLIKE.getMessage():SuccessEnum.LIKE.getMessage());
    }

    @RequestMapping(value = "collect",method = RequestMethod.POST)
    @ApiMethod(summary = "资讯收藏和取消收藏", description = "资讯收藏和取消收藏")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "articleId", required = true, clazz = Long.class, description = "资讯id"),
            @ApiQueryParam(name = "isCollect", required = true, clazz = String.class, description = "是否收藏")})
    public ReturnBean articleCollect(Long articleId,String isCollect,HttpServletRequest request) throws ProParamException{
        Optional.ofNullable(isCollect).orElseThrow(()->new ProParamException(ParamEnum.ARTICLEISCOLLECT.getMessage()));
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        informationService.articleCollect(articleId,isCollect,request);
        return returnBean.setReturnMsg(isCollect.equals("1")? SuccessEnum.CANCELCOLLECT.getMessage():SuccessEnum.COLLECT.getMessage());
    }

    @ApiMethod(summary = "查询用户收藏文章", description = "查询用户收藏文章")
    @RequestMapping(value = "userArticleCollect", method = RequestMethod.POST)
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "pageNumber", required = true, clazz = Integer.class, description = "当前页"),
            @ApiQueryParam(name = "pageSize", required = true, clazz = Integer.class, description = "条数"),
    })
    public @ApiResponseObject
    ReturnBean userArticleCollect(QueryPageBean queryPageBean, HttpServletRequest request) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        PageBean<ArticleRes> articleResPageBean = userServiceI.userArticleCollect(request,queryPageBean);
        returnBean.setData(articleResPageBean);
        return returnBean;
    }
}
