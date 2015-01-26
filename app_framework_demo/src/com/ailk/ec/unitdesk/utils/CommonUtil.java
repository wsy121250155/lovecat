package com.ailk.ec.unitdesk.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ailk.ec.unitdesk.R;

/* =========================================================
 * @date 2011-05-17  15:55
 * @Description: 公用方法
 * @version V1.0
 * =========================================================
 * 
 */
public class CommonUtil {

	/**
	 * 页面上显示:没有相关数据
	 * 
	 * @param ly
	 *            (在ly的正中间显示)
	 * @param ctx
	 * @param topMargin
	 *            内容
	 */
	public static void showNOData(LinearLayout ly, Context ctx, int topMargin) {
		showDataMessage(ly, ctx, topMargin, "没有相关数据!");
	}

	public static void showDataMessage(LinearLayout ly, Context ctx,
			int topMargin, String strMessage) {
		// 本来想让提示居中，但由于我们是外层 ScrollView
		// 需要对ScrollView里面包含ly的LinearLayout设成
		// android:layout_gravity="center_vertical"
		// 这样才能使里面的信息居中，但由于页面调用数据展现不是居中，所以控制很麻烦，只能设topMargin

		LinearLayout.LayoutParams lpOuter = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams lpData = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		lpOuter.topMargin = topMargin;

		lpData.gravity = Gravity.CENTER;

		LinearLayout lyOuter = new LinearLayout(ctx);
		lyOuter.setLayoutParams(lpOuter);
		lyOuter.setGravity(Gravity.CENTER);
		lyOuter.setOrientation(LinearLayout.VERTICAL);

		ImageView imageData = new ImageView(ctx);
		imageData.setLayoutParams(lpData);
		imageData.setBackgroundResource(R.drawable.telecom);

		TextView tvData = new TextView(ctx);
		tvData.setLayoutParams(lpData);
		tvData.setTextSize(14);
		tvData.setTextColor(Color.rgb(92, 92, 92));
		tvData.setText(strMessage);

		lyOuter.addView(imageData);
		lyOuter.addView(tvData);

		ly.addView(lyOuter);
	}

	/**
	 * 弹出提示
	 * 
	 * @param act
	 * @param content
	 *            内容
	 */
	public static void showMessage(Activity act, String content) {

		Toast.makeText(act, content, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 弹出提示
	 * 
	 * @param ctx
	 * @param content
	 *            内容
	 */
	public static Toast showMessage(Context ctx, String content) {

		Toast toast = Toast.makeText(ctx, content, Toast.LENGTH_SHORT);
		toast.show();
		return toast;
	}

	/**
	 * 弹出提示,指定位置
	 * 
	 * @param ctx
	 * @param content
	 * @param local
	 * @param xOffset
	 * @param yOffset
	 *            内容
	 */
	public static void showMessage(Context ctx, String content, int local,
			int xOffset, int yOffset) {
		Toast toast = Toast.makeText(ctx, content, Toast.LENGTH_SHORT);
		toast.setGravity(local, xOffset, yOffset);
		toast.show();
	}

	/**
	 * 自定义弹出提示框
	 * 
	 * @param act
	 * @param content
	 *            内容
	 */
	public static void showDialog(Activity act, String content) {
		Builder dlg = new Builder(act);
		dlg.setMessage(content);
		dlg.show();
	}

	/**
	 * 弹出提示,居中显示
	 * 
	 * @param act
	 * @param content
	 *            内容
	 */
	public static void showMessageCenter(Activity act, String content) {
		Toast toast = Toast.makeText(act, content, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * 关闭Toast
	 * 
	 * @param toast
	 */
	public static void closeMessage(Toast toast) {
		try {
			// 从Toast对象中获得mTN变量
			Field field = toast.getClass().getDeclaredField("mTN");
			field.setAccessible(true);
			Object obj = field.get(toast);
			// TN对象中获得了show方法
			// Method method = obj.getClass().getDeclaredMethod("show", null);
			// TN对象中获得hide方法
			Method method = obj.getClass().getDeclaredMethod("hide", null);
			// 调用方法
			method.invoke(obj, null);
		} catch (Exception e) {
		}

	}

	/**
	 * 自定义布局弹出框 支持录入文本
	 * 
	 * @param activity
	 * @param view
	 * @param widthPx
	 * @param heightPx
	 * @return
	 */
	public static Dialog showEditDialog(Activity activity, View view,
			int widthPx, int heightPx) {

		// final Builder builder = new AlertDialog.Builder(activity);
		Dialog dialog = new Dialog(activity);
		dialog.show();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		dialog.getWindow().setContentView(view);
		WindowManager.LayoutParams layoutParams = dialog.getWindow()
				.getAttributes();
		layoutParams.width = DensityUtil.dip2px(
				activity.getApplicationContext(), widthPx);
		layoutParams.height = DensityUtil.dip2px(
				activity.getApplicationContext(), heightPx);
		Log.v("showEditDialog", "width:" + layoutParams.width);
		Log.v("showEditDialog", "height:" + layoutParams.height);
		dialog.getWindow().setAttributes(layoutParams);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	/**
	 * 自定义布局弹出框
	 * 
	 * @param activity
	 * @param view
	 * @param widthPx
	 * @param heightPx
	 * @return
	 */
	public static Dialog showDialog(Activity activity, View view, int widthPx,
			int heightPx) {

		final Builder builder = new AlertDialog.Builder(activity);
		Dialog dialog = builder.show();
		dialog.getWindow().setContentView(view);
		WindowManager.LayoutParams layoutParams = dialog.getWindow()
				.getAttributes();
		layoutParams.width = widthPx;
		layoutParams.height = heightPx;
		dialog.getWindow().setAttributes(layoutParams);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	/**
	 * 自定义布局弹出对话框 弹出动画设置
	 * 
	 * @param activity
	 * @param view
	 * @param widthPx
	 * @param heightPx
	 * @param anim
	 * @return
	 */
	public static Dialog showDialog(Activity activity, View view, int widthPx,
			int heightPx, int anim) {

		Dialog dialog = showDialog(activity, view, widthPx, heightPx);
		WindowManager.LayoutParams layoutParams = dialog.getWindow()
				.getAttributes();
		dialog.getWindow().setAttributes(layoutParams);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle);

		return dialog;
	}

	/**
	 * 自定义对话框，可指定x,y
	 * 
	 * @param activity
	 * @param view
	 * @param widthPx
	 * @param heightPx
	 * @param x
	 * @param y
	 * @return
	 */
	public static Dialog showDialog(Activity activity, View view, int widthPx,
			int heightPx, int x, int y) {

		Dialog dialog = showDialog(activity, view, widthPx, heightPx);
		WindowManager.LayoutParams layoutParams = dialog.getWindow()
				.getAttributes();
		dialog.getWindow().setGravity(Gravity.TOP);
		layoutParams.y = DensityUtil
				.px2dip(activity.getApplicationContext(), y);
		layoutParams.x = DensityUtil
				.px2dip(activity.getApplicationContext(), x);
		dialog.getWindow().setAttributes(layoutParams);
		return dialog;
	}

	/**
	 * 关闭程序
	 * 
	 * @param act
	 */
	public static void systemExit(Activity act) {
		// System.exit(1);
		act.finish();
		killProcess();

	}

	/**
	 * 杀掉应用进程
	 */
	public static void killProcess() {
		try {
			Runtime.getRuntime().exec("kill -9 " + android.os.Process.myPid());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取版本信息
	 * 
	 * @param context
	 * @return String
	 */
	public static String getVersion(Activity context) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1.0";
	}

	/**
	 * 弹出数据加载信息
	 * 
	 * @param context
	 * @return String
	 */
	public static ProgressDialog loadingDialog(Activity context) {
		ProgressDialog pd = new ProgressDialog(context);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage(context.getString(R.string.loadingtext));
		return pd;
	}

	/**
	 * 判断wifi网络是否链接
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isWiFiActive(Context context) {
		WifiManager mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
		int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
		if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
			return true;
		} else {
			Log.i("CommonUtil", "WIFI未开启");
			return false;
		}
	}

	/**
	 * 判断3G网络是否链接
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean is3GActive(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.i("CommonUtil", "3G网络关闭");
			return false;
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info == null) {
				Log.i("CommonUtil", "3G网络关闭");
				return false;
			} else {
				if (info.isAvailable()) {
					return true;
				}

			}
		}
		Log.i("CommonUtil", "3G网络关闭");
		return false;
	}

	/**
	 * 判断网络是否链接
	 * 
	 * @param ctx
	 * @return boolean
	 */
	public static boolean isNetworkAvailable(Context ctx) {
		Context context = ctx;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.i("CommonUtil", "无网络连接");
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		Log.i("CommonUtil", "无网络连接");
		return false;
	}

	/**
	 * 是否存在GPS
	 * 
	 * @param context
	 * @return
	 */
	public static boolean haveGPSDevice(Context context) {
		final LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (locationManager == null)
			return false;
		final List<String> providers = locationManager.getAllProviders();
		if (providers == null)
			return false;
		return providers.contains(LocationManager.GPS_PROVIDER);
	}

	/**
	 * 判断GPS是否开启
	 * 
	 * @param ctx
	 * @return true标识开启
	 */
	public static boolean isOpenGPS(Context ctx) {
		LocationManager locationManager = (LocationManager) ctx
				.getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
		// locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * 用于转换服务端返回的json
	 * <p/>
	 * 当返回JsonArray时,由于服务端返回的JSON格式,Android判断无法解析 所以Android端需要进行如下的处理转换json
	 * 
	 * @param resultStr
	 * @return
	 */
	public static String transferJsonForAndroid(String resultStr) {
		resultStr = resultStr.replace("\\\"[", "[");
		resultStr = resultStr.replace("\"[", "[");
		resultStr = resultStr.replace("]\"", "]");
		resultStr = resultStr.replace("\"{", "{");
		resultStr = resultStr.replace("}\"", "}");
		resultStr = resultStr.replace("\\\"", "\"");
		resultStr = resultStr.replace("\\", "");
		return resultStr;
	}

	public static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 计算几天后的日期
	 * 
	 * @param date
	 * @param dayAgo
	 */
	public static String getDateSomeDaysAgo(String date, int dayAgo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ dayAgo);

		return df.format(calendar.getTime());
	}

	/**
	 * 判断版本号,是否需要更新
	 * 
	 * @param currVersion
	 *            当前版本
	 * @param latestVersion
	 *            最新版本
	 * @param sep
	 *            标识符
	 * @return
	 */
	public static boolean isUpdate(String currVersion, String latestVersion,
			String sep) {
		String s1 = normalisedVersion(currVersion, sep, 5);
		String s2 = normalisedVersion(latestVersion, sep, 5);
		int cmp = s1.compareTo(s2);
		if (cmp < 0) { // 当前版本小于最新版本
			return true;
		} else {// 当前版本等于最新版本
			return false;
		}

	}

	private static String normalisedVersion(String version, String sep,
			int maxWidth) {
		String[] split = Pattern.compile(sep, Pattern.LITERAL).split(version);
		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			sb.append(String.format("%" + maxWidth + 's', s));
		}
		return sb.toString();
	}

	/**
	 * 返回上一个月的日期
	 * 
	 * @param str
	 *            yyyyMM
	 * @return 上一个月的日期yyyyMM
	 */
	public static String beforeMonth(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = null;
		try {
			date = sdf.parse(str);
			int year = date.getYear();
			int month = date.getMonth();
			if (month == 1) {
				date.setYear(year - 1);
				date.setMonth(12);
			} else {
				date.setMonth(month - 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return sdf.format(date);
	}

	/**
	 * 返回下一个月的日期
	 * 
	 * @param str
	 *            yyyyMM
	 * @return 下一个月的日期yyyyMM
	 */
	public static String afterMonth(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = null;
		try {
			date = sdf.parse(str);
			int year = date.getYear();
			int month = date.getMonth();
			if (month == 11) {
				date.setYear(year + 1);
				date.setMonth(0);
			} else {
				date.setMonth(month + 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return sdf.format(date);
	}

	/**
	 * 返回系统当前的时间
	 * 
	 * @param format
	 *            需要显示的时间格式
	 * @return time
	 */
	public static String getFormatTime(String format, Date date) {
		SimpleDateFormat tempDate = new SimpleDateFormat(format);
		String datetime = tempDate.format(date);
		return datetime;
	}

	/**
	 * 取得IP地址 在pad上测试不可以，有时间再研究
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}

		} catch (SocketException ex) {

			Log.i("WifiPreference IpAddress", ex.toString());

		}
		return null;

	}

	// 将long转为Ip
	public static String longToIP(long longIp) {
		// linux long是低位在前，高位在后
		StringBuffer sb = new StringBuffer("");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0xFF)));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp >> 8) & 0xFF));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp >> 16) & 0xFF));
		sb.append(".");
		// 直接右移24位
		sb.append(String.valueOf((longIp >> 24) & 0xFF));
		return sb.toString();
	}

	/*
	 * 发送广播数据
	 */
	private static final String ACTION1 = "com.linkage.custqry";

	public static void sendData(final Context ctx, final String name,
			final String identityNum, final String flag) {
		new Thread() {
			public void run() {
				// 发送广播
				Intent intent = new Intent();
				intent.setAction(ACTION1);
				intent.putExtra("name", name);
				intent.putExtra("identityNum", identityNum);
				intent.putExtra("flag", flag);
				ctx.sendBroadcast(intent);
			}
		}.start();
	}
}
