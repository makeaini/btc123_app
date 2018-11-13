package com.btc123.app.sms;

import com.alibaba.fastjson.JSONObject;
import com.btc123.app.sms.request.SmsSendRequest;
import com.btc123.app.sms.response.SmsSendResponse;
import com.btc123.app.utils.HttpClientUtils;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;

public class SmsSendUtils {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(SmsSendUtils.class);
    // 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
    public static final String ACCOUNT = "N0636467";

    // 请登录zz.253.com 获取创蓝API密码(非登录密码)
    public static final  String PASSWORD = "HqumAgtTk83db9";
    // 短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
    private static final String SMS_URL = "http://smssh1.253.com/msg/send/json";
    private static String message = "【成都链一网络科技】您的验证码:%s，10分钟内有效。如非本人操作请忽略本短信。";


    //国际
    private static final String url="http://intapi.253.com/send/json";
    public static String int_account="I4052666";
    public static String int_password="ByHCrQmNJcf66f";

    public static SmsSendResponse sendSms(String phone, String code) {
        // 状态报告
        String report = "true";
        String message1 = String.format(message, code);
        SmsSendRequest smsSingleRequest = new SmsSendRequest(ACCOUNT, PASSWORD, message1, phone, report);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        LOG.info("发送短信前:{} ", requestJson);
        String response = HttpClientUtils.sendPost(SMS_URL, requestJson);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        LOG.info("发送短信响应:{} ", smsSingleResponse);
        return smsSingleResponse;

    }
    public static SmsSendResponse sendIntSms(String phone, String code) {
        String message1 = String.format(message, code);
        SmsSendRequest smsSingleRequest = new SmsSendRequest(int_account, int_password, message1);
        smsSingleRequest.setMobile(phone);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        LOG.info("发送短信前:{} ", requestJson);
        String response = HttpClientUtils.sendPost(url, requestJson);
        JSONObject object =  JSON.parseObject(response);
        SmsSendResponse smsSendResponse = new SmsSendResponse();
        smsSendResponse.setCode(object.getString("code"));
        smsSendResponse.setErrorMsg(object.getString("error"));
        smsSendResponse.setMsgId(object.getString("msgid"));
        LOG.info("smsSendResponse:{}",smsSendResponse);
        return smsSendResponse;
    }
}
