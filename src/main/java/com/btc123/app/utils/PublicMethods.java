package com.btc123.app.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.GZIPInputStream;

/**
 * @Auther: lc
 * @Date: 2018/10/22 17:31
 * @Description:
 */
public class PublicMethods {

    public static void main(String[] args) throws Exception {
    }

    // 获取天气预报
//    public static float getWeatherByHttp(String city) {
//        String url = "http://wthrcdn.etouch.cn/weather_mini?";
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("city", city));
//        String param = URLEncodedUtils.format(params, "utf-8");
//        HttpGet httpGet = new HttpGet(url + param);
//        HttpClient httpClient = new DefaultHttpClient();
//
//        HttpResponse httpResponse = null;
//        float low = 0;
//        try {
//            httpResponse = httpClient.execute(httpGet);
//            String result = getJsonStringFromGZIP(httpResponse);// 获取到解压缩之后的字符串
//            // System.out.println(result);
//            // 输出
//            JSONObject jsonObject = JSONObject.fromObject(result);
//            JSONObject jsonData = jsonObject.getJSONObject("data");
//            String forecast = jsonData.getString("forecast");
//            JSONArray array = JSONArray.fromObject(forecast);
//            String json = array.getString(0);
//            JSONObject Object = JSONObject.fromObject(json);
//
//            String Slow = Object.getString("low");
//            low = NumberUtils.toFloat(Slow.substring(2, Slow.length() - 1).trim(), 0);// 最低温
//            // String Shigh = Object.getString("high");// float
//            // high=NumberUtils.toFloat(Shigh.substring(2,Shigh.length()-1).trim(), 0);//最高温
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return low;
//    }
//
//    private static String getJsonStringFromGZIP(HttpResponse response) {
//        String jsonString = null;
//        try {
//            InputStream is = response.getEntity().getContent();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            bis.mark(2);
//            // 取前两个字节
//            byte[] header = new byte[2];
//            int result = bis.read(header);
//            // reset输入流到开始位置
//            bis.reset();
//            // 判断是否是GZIP格式
//            int headerData = getShort(header);
//            if (result != -1 && headerData == 0x1f8b) {
//                is = new GZIPInputStream(bis);
//            } else {
//                is = bis;
//            }
//            InputStreamReader reader = new InputStreamReader(is, "utf-8");
//            char[] data = new char[100];
//            int readSize;
//            StringBuffer sb = new StringBuffer();
//            while ((readSize = reader.read(data)) > 0) {
//                sb.append(data, 0, readSize);
//            }
//            jsonString = sb.toString();
//            bis.close();
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return jsonString;
//    }

    private static int getShort(byte[] data) {
        return (int) ((data[0] << 8) | data[1] & 0xFF);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


}
