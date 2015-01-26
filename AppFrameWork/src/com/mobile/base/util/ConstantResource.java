package com.mobile.base.util;

import android.telephony.TelephonyManager;

public class ConstantResource {
	public static int BUFF_SIZE = 2048;	//文件读写缓存区大小
	private static String[] WEEK_NAMES = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
	public static TelephonyManager tm;
	public static String DEVICEID = "";
	
	public static String URL = "http://192.168.31.114:8081/zsndJ/mobile";

	public static String DOWNLOAD_URL = "http://192.168.31.114:8081/zsndJ/download.do";
	public static String UPLOAD_URL = "http://192.168.31.114:8081/zsndJ/fileUpload.do";

	public static String USERID = "2";
	public static String VERIFY = "1";
	
	//Error for Toast
	public static String NET_ERROR = "无网络，请重新连接";
	public static String INTERFACE_ERROR = "接口处理错误";

	/**
	 * 返回所需周中文名
	 * @param i
	 * @return
	 */
	public static String getWeekName(int i) {
		String result = "其他";
		
		if(i >= 0 && i < WEEK_NAMES.length) {
			result = WEEK_NAMES[i];
		}
		
		return result;
	}
	
	/**
	 * 返回deviceid
	 * @return
	 */
	public static synchronized String getDeviceid() {
		String result = "";
		
		if(tm != null) {
			result = tm.getDeviceId();
			DEVICEID = result;
		} else {
			result = DEVICEID;
			MLog.d(result);
		}
		
		return "1";
	}
}
