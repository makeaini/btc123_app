package com.btc123.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;

/**
 * Created by shining on 2017-11-23.
 * @author shining
 */
public class FastJsonUtils {
        public static Object toBean(String text) {
            return JSON.parse(text);
        }
        public static <T> T toBean(String text, Class<T> clazz) {
            return JSON.parseObject(text, clazz);
        }

        // 转换为数组
        public static <T> Object[] toArray(String text) {
            return toArray(text, null);
        }

        // 转换为数组
        public static <T> Object[] toArray(String text, Class<T> clazz) {
            return JSON.parseArray(text, clazz).toArray();
        }

        // 转换为List
        public static <T> List<T> toList(String text, Class<T> clazz) {
            return JSON.parseArray(text, clazz);
        }
        /**
         * 将javabean转化为序列化的json字符串
         * @param keyvalue
         * @return
         */
        public static String beanToJson(Object t) {
            String textJson = JSON.toJSONString(t);
            return textJson;
        }

        /**
         * 将string转化为序列化的json字符串
         * @param text
         * @return
         */
        public static Object textToJson(String text) {
            Object objectJson  = JSON.parse(text);
            return objectJson;
        }

        /**
         * json字符串转化为map
         * @param s
         * @return
         */
        public static Map stringToCollect(String s) {
            Map m = JSONObject.parseObject(s);
            return m;
        }

        /**
         * 将map转化为string
         * @param m
         * @return
         */
        public static String collectToString(Map m) {
            String s = JSONObject.toJSONString(m);
            return s;
        }

}
