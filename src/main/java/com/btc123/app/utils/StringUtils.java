package com.btc123.app.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by btc on 2018/5/23.
 */
public class StringUtils {

    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    /**
     *处理html抱歉
     * @return
     */
    public static String getRegExHtml(String htmlStr) {
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        return htmlStr.replaceAll("&nbsp|&ldquo|&rdquo|&mdash|&lt|&gt|&amp|&quot|&apos","");
    }
}
