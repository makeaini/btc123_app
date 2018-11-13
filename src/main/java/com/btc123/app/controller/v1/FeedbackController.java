package com.btc123.app.controller.v1;

import com.btc123.app.bean.ReturnBean;
import com.btc123.app.enumeration.ParamEnum;
import com.btc123.app.enumeration.SuccessEnum;
import com.btc123.app.exception.ProException;
import com.btc123.app.exception.ProParamException;
import com.btc123.app.service.FeedbackServiceI;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by shining on 2018/5/16.
 */
@RestController
@RequestMapping("/{version}/feedback/")
@Api(name = "意见反馈", description = "意见反馈")
@ApiVersion(since = "1.0")
public class FeedbackController extends BaseV1Controller {
    @Autowired
    private FeedbackServiceI feedBackServiceI;

    @ApiMethod(summary = "意件反馈", description = "意件反馈")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "mobile", required = true, clazz = String.class, description = "手机号"),
            @ApiQueryParam(name = "content", required = true, clazz = String.class, description = "反馈内容"),
    })
    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    public @ApiResponseObject
    ReturnBean feedback(String mobile, String content) throws ProException {
        ReturnBean returnBean = ReturnBean.returnBeanBuild();
        Optional.ofNullable(content).orElseThrow(() -> new ProParamException(ParamEnum.CONTENT.getMessage()));
        Long userId = getUserId();
        feedBackServiceI.saveFeedback(mobile, userId, content);
        return returnBean.setReturnMsg(SuccessEnum.FEED_BACK.getMessage());
    }

}
