package com.ailk.ec.unitdesk.datastore;

import android.app.Application;

import com.ailk.ec.unitdesk.utils.CrashHandler;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * @Description:
 * @version V1.0
 * @date 2012-6-18
 */
public class CommonApplication extends Application {

	private static CommonApplication application;

	public static final String TAG = "CommonApplication";


	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate start");
		super.onCreate();
		// 初始化异常处理控制类
		CrashHandler crashHandler = CrashHandler.getInstance();
		// 初始化异常处理控制类
		crashHandler.init(getApplicationContext());

		// 初始化系统配置
		init();
		application = this;
		Log.d(TAG, "onCreate end");
	}

	private void init() {
		Log.d(TAG, "init start");
		Log.d(TAG, "init end");
	}

	public static CommonApplication getInstance() {
		return application;
	}

}
