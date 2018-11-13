package com.btc123.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author shining
 *
 */
public class DateUtils {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
	private static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String FORMAT_TIME = "HH:mm";

	/**
	 * 获取当前系统毫秒数
	 * @return
	 */
	public static long currentTimeMillis(){
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前日期
	 * @return
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}
	public static String formatTime(long time) {
		long nowTime = System.currentTimeMillis();
		long interval = nowTime - time;
		long hours = 3600 * 1000;
		long days = hours * 24;
		long fiveDays = days * 5;
		if (interval < hours) {
			long minute = interval / 1000 / 60;
			if (minute == 0) {
				return "刚刚";
			}
			return minute + "分钟前";
		} else if (interval < days) {
			return interval / 1000 / 3600 + "小时前";
		} else if (interval < fiveDays) {
			return interval / 1000 / 3600 / 24 + "天前";
		} else {
			Date date = new Date(time);
			return parseDate2String(date, "MM-dd");
		}
	}
	public static String parseDate2String(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String formatterCurrentDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
		return formatter.format(date);
	}

	/**
	 * 截取时间日期
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		return formatter.format(date);
	}

	/**
	 * 截取时间
	 * @param date
	 * @return
	 */
	public static String getTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_TIME);
		return formatter.format(date);
	}

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        Date date = new Date();
        date.setTime(1540371538000l);
        System.out.println(simpleDateFormat.format(date));
        String str = getTime(date);
        System.out.println(str);
    }
}
