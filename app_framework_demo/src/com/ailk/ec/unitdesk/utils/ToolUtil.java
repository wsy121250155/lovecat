package com.ailk.ec.unitdesk.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.models.desktop.DeviceInfo;
import com.ailk.ec.unitdesk.models.desktop.DeviceJson;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.telephony.TelephonyManager;

/**
 * 
 * @Description: 复制文件工具类
 * @version V1.0
 * @date 2011-9-20
 */
public class ToolUtil {

	public static String getFileName(String path) {
		String filename = path.substring(path.lastIndexOf('/') + 1);
		return filename;
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(HttpURLConnection conn, String downloadUrl) {
		String filename = downloadUrl
				.substring(downloadUrl.lastIndexOf('/') + 1);
		if (conn == null)
			return filename;
		if (filename == null || "".equals(filename.trim())) {// 如果获取不到文件名称
			for (int i = 0;; i++) {
				String mine = conn.getHeaderField(i);
				if (mine == null)
					break;
				if ("content-disposition".equals(conn.getHeaderFieldKey(i)
						.toLowerCase())) {
					Matcher m = Pattern.compile(".*filename=(.*)").matcher(
							mine.toLowerCase());
					if (m.find())
						return m.group(1);
				}
			}
			filename = UUID.randomUUID() + ".tmp";// 默认取一个文件名
		}
		return filename;
	}

	/**
	 * 将一个文件(sourceFile) 保存到指定的位置下(targetFile)名字仍为原来名字
	 * 
	 * @param sourceFile
	 *            要保存的源文件
	 * @param targetFile
	 *            要保存的位置
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		FileInputStream input = new FileInputStream(sourceFile);
		String fileName = sourceFile.getName();
		copyFile(input, targetFile, fileName);
	}

	/**
	 * 
	 * 将一个流(input)保存到指定的位置下(targetFile)命名为(fileName)
	 * 
	 * @param input
	 *            要保存的文件流
	 * @param targetFile
	 *            要保存的位置
	 * @param fileName
	 *            要保存的文件名
	 * @return
	 * @throws IOException
	 */
	public static File copyFile(InputStream input, File targetFile,
			String fileName) throws IOException {
		// 新建文件输入流并对它进行缓冲
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		if (!targetFile.exists())
			targetFile.mkdirs();
		targetFile = new File(targetFile, fileName);
		targetFile.createNewFile();
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 1];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
		return targetFile;
	}

	/**
	 * 将一个drawable保存到指定的位置下(targetFile)命名为(fileName)
	 * 
	 * @param drawable
	 * @param targetFile
	 * @param fileName
	 * @throws IOException
	 */
	public static void copyFile(Drawable drawable, File targetFile,
			String fileName) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (fileName.toLowerCase().endsWith(".jpg")) {
			((BitmapDrawable) drawable).getBitmap().compress(
					Bitmap.CompressFormat.JPEG, 60, baos);
		} else {
			((BitmapDrawable) drawable).getBitmap().compress(
					Bitmap.CompressFormat.PNG, 100, baos);
		}
		InputStream input = new ByteArrayInputStream(baos.toByteArray());
		copyFile(input, targetFile, fileName);
	}

	public static void copyFile(File sourceFile, File targetFile,
			String newfileName) throws IOException {
		FileInputStream input = new FileInputStream(sourceFile);
		copyFile(input, targetFile, newfileName);
	}

	/**
	 * 获取字符格式当前时间
	 * 
	 * @return
	 */
	public static String getDateTimeStr() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(now);
		return nowTime;
	}

	/**
	 * 清除目录下图片
	 * 
	 * @param context
	 */
	public static void ClineImg(Context context) {
		File fileIconDir = new File("/data/data/" + "" + "/conf/icon");
		File[] fileIcons = fileIconDir.listFiles();
		if (fileIcons != null && fileIcons.length > 0) {
			for (File fileIcon : fileIcons) {
				fileIcon.delete();
			}
		}
	}

	/**
	 * 隐蔽关键信息
	 * 
	 * @param String
	 */
	public static String hideData(String data) {
		String data1;
		if (data.length() == 2) {
			data1 = data.substring(0, 1);
			data = data1 + "*";
		} else if (data.length() == 3) {
			data1 = data.substring(0, 1);
			data = data1 + "**";
		} else if (data.length() > 4 && data.length() < 10) {
			data1 = data.substring(0, 2);
			data = data1 + "**";
		} else if (data.length() > 10) {
			data1 = data.substring(0, 3);
			data = data1 + "**************";
		}
		return data;
	}

	public static boolean isJSONObject(String str) {
		try {
			new JSONObject(str);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	public static boolean isJSONOArray(String str) {
		try {
			new JSONArray(str);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	public static boolean isEmptyStr(String str) {
		return str == null || "".equals(str.trim());
	}

	// 获取未安装的apk版本号
	public static String getUninstallAPKInfo(Context ctx, String archiveFilePath) {
		// archiveFilePath=Environment.getExternalStorageDirectory()+"/"+"TestB.apk"
		String versionName = null;
		String appName = null;
		String pakName = null;
		PackageManager pm = ctx.getPackageManager();
		PackageInfo pakinfo = pm.getPackageArchiveInfo(archiveFilePath,
				PackageManager.GET_ACTIVITIES);
		if (pakinfo != null) {
			ApplicationInfo appinfo = pakinfo.applicationInfo;
			versionName = pakinfo.versionName;
			Drawable icon = pm.getApplicationIcon(appinfo);
			appName = (String) pm.getApplicationLabel(appinfo);
			pakName = appinfo.packageName;
		}
		return versionName;
	}

	// 获取安装的apk版本号
	public static String getInstallAPKInfo(Context ctx, String pak) {
		// pak=com.nmbb.b
		String versionName = null;
		PackageManager pm = ctx.getPackageManager();
		PackageInfo pakinfo;
		try {
			pakinfo = pm.getPackageInfo(pak, 0);
			if (pakinfo != null) {
				versionName = pakinfo.versionName;
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 获取IMEI号
	 * 
	 * @return
	 */
	public static String getDeviceId() {
		TelephonyManager telephonyManager = (TelephonyManager) CommonApplication
				.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	/**
	 * 获取设备信息
	 * 
	 * @return
	 */
	public static DeviceJson getDeviceInfo() {
		TelephonyManager telephonyManager = (TelephonyManager) CommonApplication
				.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
		DeviceJson json = new DeviceJson();
		json.device_info = new DeviceInfo();
		json.device_info.device_id = telephonyManager.getDeviceId();// IMEI
		json.device_info.operator_name = telephonyManager
				.getNetworkOperatorName();// 运营商名称
		json.device_info.os_version = android.os.Build.VERSION.RELEASE;// os版本
		json.device_info.device_model = android.os.Build.MODEL;
		json.device_info.device_brand = android.os.Build.BRAND;
//		json.device_info.device_type = Constants.DEVICE_TYPE;
		return json;
	}

	/**
	 * 静默卸载
	 * 
	 * @param packName
	 */
	public static void silentUninstall(Context ctx, String packName) {
		if (isApkInstalled(ctx, packName)) {
			execCommand("system/bin/pm uninstall " + packName);
		}
	}

	private static boolean execCommand(String cmd) {
		Process process = null;
		DataOutputStream dos = null;
		try {
			process = Runtime.getRuntime().exec("su");
			dos = new DataOutputStream(process.getOutputStream());

			Log.i("execCommand", cmd);
			dos.writeBytes(cmd + "\n");
			dos.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}

	public static boolean isApkInstalled(Context ctx, String packageName) {

		PackageManager pm = ctx.getPackageManager();
		PackageInfo pInfo = null;
		try {
			pInfo = pm.getPackageInfo(packageName, 0);
			if (pInfo == null) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
