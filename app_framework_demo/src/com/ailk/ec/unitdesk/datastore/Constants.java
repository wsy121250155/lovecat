package com.ailk.ec.unitdesk.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;

import com.ailk.ec.unitdesk.security.DES;

/* =========================================================
 * @Description: 数据字典（常量数据配置）
 * @version V1.0
 * =========================================================
 */
public class Constants {

	private static Constants constants = null;

	public static Constants getInstance() {
		if (constants == null) {
			constants = new Constants();
		}
		return constants;
	}

	public List<Activity> activityList = new ArrayList<Activity>();
	// 记录活动的activity name
	public List<String> activityNameList = new ArrayList<String>();

	// 门户层状态码 - 成功
	public final String R_SUCCESS = "0";

	// 接口成功
	public final String RSUCCESS = "POR-0000";
	// 接口系统错误
	public final String RERROR = "POR-0001";
	// 接口失败
	public final String RFAIL = "POR-0002";

	public final String SHAREDPREFERENCES_NAME = "first_pref";

	// 网络相关配置
	public String url;
	public int connTimeout;
	public int soTimeout;

	public String desktopUrl;
	public String desktopApkUrl;

	/**
	 * 忽略更新
	 */
	public static final String IGNORE = "ignore";

	// 应用资源保存根目录
	public static final String BASE_DIR = "/sdcard/UniDesk/";

	// 错误日志保存目录
	public static final String LOG_DIR = "/sdcard/UniDesk/LOG/";

	// 存储文件
	public String PREFS_NAME = "XXX.com";
	public String VERSION_TYPE = "versionType";

	public DES des = new DES();
	/**
	 * 缓存js库
	 */
	public static final HashMap<String, String> jsMap = new HashMap<String, String>() {
		{
			// 任务库
			put("bootstrap-3.0.2.js", "js/bootstrap-3.0.2.js");
			put("bootstrap-3.0.2.css", "js/bootstrap-3.0.2.css");
			put("bootstrap-3.0.2.min.js", "js/bootstrap-3.0.2.min.js");
			put("bootstrap-3.0.2.min.css", "js/bootstrap-3.0.2.min.css");
			put("bootstrap-theme-3.0.2.css", "js/bootstrap-theme-3.0.2.css");
			put("bootstrap-theme-3.0.2.min.css",
					"js/bootstrap-theme-3.0.2.min.css");
			put("echarts-1.3.1.min.js", "js/echarts-1.3.1.min.js");
			put("iscroll-4.2.5.js", "js/iscroll-4.2.5.js");
			put("iscroll-4.2.5.min.js", "js/iscroll-4.2.5.min.js");
			put("iscroll-4.2.js", "js/iscroll-4.2.js");
			put("iscroll-4.2.min.js", "js/iscroll-4.2.min.js");
			put("jquery-1.10.2.min.js", "js/jquery-1.10.2.min.js");
			put("jquery-1.7.2.min.js", "js/jquery-1.7.2.min.js");
			put("jquery-1.7.min.js", "js/jquery-1.7.min.js");
			put("jquery-1.9.1.min.js", "js/jquery-1.9.1.min.js");
			put("jquery-ui-1.9.1.min.js", "js/jquery-ui-1.9.1.min.js");
			put("jquery.mobile-1.3.2.min.css", "js/jquery.mobile-1.3.2.min.css");
			put("jquery.mobile-1.3.2.min.js", "js/jquery.mobile-1.3.2.min.js");
			put("json2.js", "js/json2.js");
			put("less-1.3.0.min.js", "js/less-1.3.0.min.js");
			put("mobiscroll.custom-2.6.2.min.js",
					"js/mobiscroll.custom-2.6.2.min.js");
			put("mobiscroll.custom-2.6.2.min.css",
					"js/mobiscroll.custom-2.6.2.min.css");
			put("pqgrid-1.1.3.min.css", "js/pqgrid-1.1.3.min.css");
			put("pqgrid-1.1.3.min.js", "js/pqgrid-1.1.3.min.js");
			put("pqgrid.mobile-1.1.3.min.css", "js/pqgrid.mobile-1.1.3.min.css");
			put("pqgrid.mobile-1.1.3.min.js", "js/pqgrid.mobile-1.1.3.min.js");
			put("require-2.1.9.min.js", "js/require-2.1.9.min.js");
			put("zepto-1.0.js", "js/zepto-1.0.js");
			put("zepto-1.0.min.js", "js/zepto-1.0.min.js");

			put("jquery.mobile-1.3.2.css", "js/jquery.mobile-1.3.2.css");
			put("jquery-1.8.0.min.js", "js/jquery-1.8.0.min.js");
			put("jquery.wipetouch-0.0.1.js", "js/jquery.wipetouch-0.0.1.js");

			put("jquery.template-1.0.0.js", "js/jquery.template-1.0.0.js");
			put("jquery-ui-1.9.2-ec.min.css", "js/jquery-ui-1.9.2-ec.min.css");

			put("jquery-ui-1.9.2.min.js", "js/jquery-ui-1.9.2.min.js");
			put("jquery-ui-1.9.2.min.css", "js/jquery-ui-1.9.2.min.css");
			put("cordova-android-2.9.0.js", "js/cordova-android-2.9.0.js");
			put("cordova-min.js", "js/cordova-android-2.9.0.js");
			// put("cordova.js", "js/cordova.js");

		}
	};

}
