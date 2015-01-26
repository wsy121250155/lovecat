package com.mobile.base.util;

import android.content.Context;
import android.util.Log;

public class MLog {
	private static String TAG = "com.zsdx";
	private static String ERROR_TAG = "com.zsdx.err";
	
	public static void d(String msg) {
		Log.d(TAG, msg);
	}
	
	public static void e(String message, Throwable e) {
		Log.e(ERROR_TAG, message, e);
	}
	
	public static void e(String message) {
		Log.e(ERROR_TAG, message);
	}
	
	public static void e(Throwable e) {
		Log.e(ERROR_TAG, e.toString(), e);
	}
	
	public static void e(Context context, Throwable e) {
		showErrToast(context, e.toString());
	}
	
	public static void showErrToast(Context context, String msg) {
		if(AppConfig.isShowLog()) {
			Helper.toast(context, msg);
		}
	}
}
