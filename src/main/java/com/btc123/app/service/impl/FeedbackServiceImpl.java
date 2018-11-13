package com.btc123.app.service.impl;

import com.btc123.app.entity.Feedback;
import com.btc123.app.mapper.FeedbackMapper;
import com.btc123.app.service.FeedbackServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shining on 2018/5/16.
 */
@Service
public class FeedbackServiceImpl implements FeedbackServiceI {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveFeedback(String content, Long userId, String mobile) {
        Feedback feedback = new Feedback(userId, mobile, content);
        feedbackMapper.insertSelective(feedback);
    }
}
