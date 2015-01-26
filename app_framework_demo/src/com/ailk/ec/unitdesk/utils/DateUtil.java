package com.ailk.ec.unitdesk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类 User: Noah Date: 12-12-8 Time: 下午2:14 To change this template use File
 * | Settings | File Templates.
 */
public final class DateUtil {

	/**
	 * 获取昨日日期
	 * 
	 * @return
	 */
	public static String getLastDate() {
		String lastDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);// 减一天
		lastDate = sdf.format(calendar.getTime());
		return lastDate;
	}

	/**
	 * 获取上个月份
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		String lastMonth = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		lastMonth = sdf.format(lastDate.getTime());
		return lastMonth;
	}

	/**
	 * 将日期转换成YYYY-MM-DD
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getNormalFormatDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return "";

		StringBuffer formatDate = new StringBuffer();
		formatDate.append(dateStr.substring(0, 4)).append("-")
				.append(dateStr.substring(4, 6)).append("-")
				.append(dateStr.substring(6, 8));

		return formatDate.toString();
	}

	/**
	 * 将日期转换成MM-DD
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getNoYearFormatDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return "";

		StringBuffer formatDate = new StringBuffer();
		formatDate.append(dateStr.substring(4, 6)).append("-")
				.append(dateStr.substring(6, 8));

		return formatDate.toString();
	}

	/**
	 * 获取日期的中文格式显示
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getNormalFormatCNDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return "";

		StringBuffer formatDate = new StringBuffer();
		formatDate.append(dateStr.substring(0, 4)).append("年")
				.append(dateStr.substring(4, 6)).append("月")
				.append(dateStr.substring(6, 8)).append("日");

		return formatDate.toString();
	}

	/**
	 * 获取月份和日期的中文显示
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getMonthFormatCNDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return "";

		StringBuffer formatDate = new StringBuffer();
		formatDate.append(dateStr.substring(4, 6)).append("月")
				.append(dateStr.substring(6, 8)).append("日");

		return formatDate.toString();
	}

	/**
	 * 简单日期格式
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getSimpleFormatDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return "";

		StringBuffer formatDate = new StringBuffer();
		formatDate.append(dateStr.substring(4, 6)).append(
				dateStr.substring(6, 8));

		return formatDate.toString();
	}

	public static String getSimpleFormatDate() {
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		return matter.format(new Date());
	}

	/**
	 * yyyy-MM-DD hh-mm-ss -> yyyy/MM/DD
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getSubFormatDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return "";
		String[] dateArr = dateStr.split(" ");
		if (dateArr != null && dateStr.length() > 0) {
			StringBuffer formatDate = new StringBuffer();
			formatDate.append(dateArr[0].replace("-", "/"));

			return formatDate.toString();
		} else {
			return dateStr;
		}
	}

}
