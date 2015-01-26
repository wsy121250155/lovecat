package com.mobile.base.util;

import android.content.Context;

public class Frame {
	public static Context CONTEXT;
	
	public void init(Context context) {
		CONTEXT = context.getApplicationContext(); //整个应用的上下文
	}
}
