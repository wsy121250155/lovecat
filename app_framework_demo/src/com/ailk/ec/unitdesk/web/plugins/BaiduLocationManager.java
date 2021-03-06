package com.ailk.ec.unitdesk.web.plugins;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.utils.Log;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 定时器定位类
 * 
 * @author admini
 */
public class BaiduLocationManager {

	private static final String TAG = "BaiduLocationManager";
	// 时间间隔
	private static int myTime = 15 * 1000;
	// 定位时间间隔
	private int myLocationTime = 10 * 1000;
	// 是否启动了定位API
	private boolean isOpenLocation = false;
	// 是否启动了定位线程
	private boolean isOpenLocationTask = false;
	private LocationClientOption option = null;

	// 定位类
	private LocationClient mLocationClient = null;
	private MyReceiveListener receiveListener = new MyReceiveListener();
	private BDLocation currentLocation;
	LocationCallBack callback;

	// 定时器
	private Timer myLocationTimer = null;
	// 定时线程
	private TimerTask myLocationTimerTask = null;

	/**
	 * 国测局经纬度坐标系(google坐标,火星坐标) coor=gcj02
	 */
	private String coordinateType = "gcj02";

	/**
	 * 百度经纬度坐标系 coor=bd09ll
	 */
	// private String coordinateType = "bd09ll";

	public BaiduLocationManager(Context ctx, LocationCallBack callback) {
		init(ctx, callback);
	}

	public BaiduLocationManager(Context ctx, LocationCallBack callback,
			String coordinateType) {
		init(ctx, callback, coordinateType);
	}

	public void init(Context ctx, LocationCallBack callback) {
		mLocationClient = new LocationClient(ctx);
		mLocationClient.registerLocationListener(receiveListener);
		this.callback = callback;
	}

	public void init(Context ctx, LocationCallBack callback,
			String coordinateType) {
		mLocationClient = new LocationClient(ctx);
		mLocationClient.registerLocationListener(receiveListener);
		this.callback = callback;
		this.coordinateType = (null == coordinateType
				|| coordinateType.length() <= 0 ? this.coordinateType
				: coordinateType);
	}

	private void updateLocation(BDLocation location) {
		currentLocation = location;
	}

	public BDLocation getLocation() {
		return currentLocation;
	}

	public interface LocationCallBack {
		/**
		 * 当前位置
		 * 
		 * @param location
		 */
		void onLocationChange(BDLocation location);
	}

	// 接受定位得到的消息
	private class MyReceiveListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			updateLocation(location);
			callback.onLocationChange(location);
			closeLocationTask();
		}

	}

	/**
	 * start定位
	 */
	public void startLocation() {
		try {
			if (!isOpenLocation) // 如果没有打开
			{
				option = new LocationClientOption();
				// option.setOpenGps(true); // 打开gps
				// option.setPriority(LocationClientOption.NetWorkFirst); //
				// 设置网络优先
				option.setCoorType(coordinateType); // 设置返回的坐标类型
				option.setScanSpan(myLocationTime); // 设置时间
				option.setAddrType("detail"); // 返回地址类型
				option.setTimeOut(10 * 1000); // 设置超时时间
				mLocationClient.setLocOption(option);
				mLocationClient.start(); // 打开定位
				isOpenLocation = true; // 标识为已经打开了定位
			}
		} catch (Exception e) {
			Log.i(TAG, "打开定位异常" + e.toString());
		}
	}

	/**
	 * end 定位
	 */
	private void closeLocation() {
		try {
			mLocationClient.stop(); // 结束定位
			isOpenLocation = false; // 标识为已经结束了定位
		} catch (Exception e) {
			Log.i(TAG, "结束定位异常" + e.toString());
		}
	}

	/**
	 * 定时器的回调函数
	 */
	private Handler handler = new Handler() {
		// 更新的操作
		@Override
		public void handleMessage(Message msg) {
			getLocationInfo(); // 获取经纬度

			Log.i(TAG, "调用了获取经纬度方法");
			super.handleMessage(msg);
		}
	};

	/**
	 * 初始化定时器
	 */
	private void initLocationTime() {
		if (myLocationTimer == null) {
			Log.i(TAG, "myLocationTimer 已经被清空了");
			myLocationTimer = new Timer();
		} else {
			Log.i(TAG, "myLocationTimer 已经存在");
		}
	}

	/**
	 * 初始化 定时器线程
	 */
	private void initLocationTimeTask() {
		myLocationTimerTask = new TimerTask() {
			/***
			 * 定时器线程方法
			 */
			@Override
			public void run() {
				Log.i(TAG, "执行定位定时器 ");
				handler.sendEmptyMessage(1); // 发送消息
			}
		};
	}

	/**
	 * 初始化 time 对象 和 timetask 对象
	 */
	private void initLocationTimeAndTimeTask() {
		initLocationTime();
		initLocationTimeTask();
	}

	/**
	 * 销毁 time 对象 和 timetask 对象
	 */
	private void destroyLocationTimeAndTimeTask() {
		myLocationTimer = null;
		myLocationTimerTask = null;
	}

	/**
	 * 打开定位定时器线程
	 */
	public void openLocationTask() {
		try {

			if (!isOpenLocationTask) // /如果不是打开状态，则打开线程
			{
				startLocation();// 启动定位更新经纬度
				// 开启定时器
				initLocationTimeAndTimeTask(); // 初始化定时器和定时线程
				myLocationTimer.schedule(myLocationTimerTask, myTime, myTime);
				Log.i(TAG, " 打开了定位定时器线程 ");
				isOpenLocationTask = true; // 标记为打开了定时线程
			} else {
				Log.i(TAG, " 已经开启了定位定时器线程 ");
			}
		} catch (Exception e) {
			Log.i(TAG, "打开定位定时器线程 异常" + e.toString());
		}
	}

	/**
	 * 获取经纬度
	 */
	public void getLocationInfo() {
		/**
		 * 0：正常。
		 * 
		 * 1：SDK还未启动。
		 * 
		 * 2：没有监听函数。
		 * 
		 * 6：请求间隔过短。
		 */
		int i = mLocationClient.requestLocation();
		String TAGfont = "requestLocation() : ";
		switch (i) {
		case 0:
			Log.i(TAG, TAGfont + "正常。");
			break;
		case 1:
			Log.i(TAG, TAGfont + "SDK还未启动。");
			break;
		case 2:
			Log.i(TAG, TAGfont + "没有监听函数。 ");
			break;
		case 6:
			Log.i(TAG, TAGfont + "请求间隔过短。 ");
			break;
		default:
			Log.i(TAG, TAGfont + "其他原因	");
		}
	}

	/**
	 * 关闭定位定时器线程
	 */
	public void closeLocationTask() {
		try {
			if (isOpenLocationTask) // 如果是打开状态，则关闭
			{
				closeLocation();
				// 关闭定时器
				if (myLocationTimer != null) {
					myLocationTimer.cancel();
				}
				destroyLocationTimeAndTimeTask();

				Log.i(TAG, " 关闭了定位定时器线程 ");
				isOpenLocationTask = false; // 标记为关闭了定时线程
			} else {
				Log.i(TAG, " 已经关闭了定位定时器线程 ");
			}

		} catch (Exception e) {
			Log.i(TAG, "关闭定位定时器线程异常: " + e.toString());
		}
	}

}
