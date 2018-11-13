package com.btc123.app.service;

/**
 * Created by shining on 2018/5/16.
 */
public interface FeedbackServiceI {
    /**
     * 添加意件反馈
     * @param content 意件内容
     * @param userId 用户id
     * @param mobile 联系方式
     */
    public void saveFeedback(String content,Long userId,String mobile);
}
