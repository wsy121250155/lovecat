package com.ailk.ec.unitdesk.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

/**
 * 
 * @author 陈峰
 * @description 资源工具类
 * @date 2012-9-24
 * @project Plugin
 */
public class ResourceUtil {
	/**
	 * 45DP 所对应的px数
	 */
	public static int DP_45;
	public static int DP_75;
	public static int DP_55;
	public static int DP_6;
	/**
	 * 60DP 所对应的px数
	 */
	public static int DP_60;
	
	/**
	 * 145DP 所对应的px数
	 */
	public static int DP_145;
	/**
	 * 180DP 所对应的px数
	 */
	public static int DP_165;
	/**
	 * 190DP 所对应的px数
	 */
	public static int DP_190;
	/**
	 * 状态栏高度
	 */
	public static int STATUS_BAR_HEIGHT = -1;
	/**
	 * 屏幕宽度
	 */
	public static int SCREEN_WIDTH = -1;
	/**
	 * 屏幕高度
	 */
	public static int SCREEN_HEIGHT = -1;
	

	public static void initParam(Context context){
		DP_6 = DensityUtil.dip2px(context, 6.0f);
		DP_45 = DensityUtil.dip2px(context, 45.0f);
		DP_55 = DensityUtil.dip2px(context, 55.0f);
		DP_60 = DensityUtil.dip2px(context, 60.0f);
		DP_75 = DensityUtil.dip2px(context, 75.0f);
		DP_145 = DensityUtil.dip2px(context, 145.0f);
		DP_165 = DensityUtil.dip2px(context, 165.0f);
		DP_190 = DensityUtil.dip2px(context, 190.0f);
		
	}
	
	/**
	 * 计算状态栏高度
	 * @param context
	 */
	public static void cntStatusBarHeight(View view){
		if(STATUS_BAR_HEIGHT == -1){
			Rect outRect = new Rect();
			view.getWindowVisibleDisplayFrame(outRect);
			STATUS_BAR_HEIGHT = outRect.top;
			SCREEN_WIDTH = outRect.width();
			SCREEN_HEIGHT = outRect.height();
		}
	}

	
	/**
	 * COLOR gray_4
	 */
	public static final int gray_4 = Color.rgb(128, 128, 128);
}
