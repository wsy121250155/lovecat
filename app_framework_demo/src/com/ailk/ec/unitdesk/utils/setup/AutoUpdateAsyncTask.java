package com.ailk.ec.unitdesk.utils.setup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.ActivityTaskManager;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.ui.activity.login.WelcomeActivity;
import com.ailk.ec.unitdesk.utils.CommonUtil;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.SystemPreference;
import com.al.ec.utils.PatchUtils;

public class AutoUpdateAsyncTask extends AsyncTask<String, Integer, String> {

	private boolean isRun = true;

	public Activity activity = null;

	// 要下载的apk大小
	public Float apkSize = null;

	// 已下载的apk大小
	public Float alreadySize = (float) 0;

	// 将要下载的apk转为的流
	private InputStream is = null;

	// 保存apk的本地流
	private FileOutputStream fos = null;

	// 保存的apk文件
	private File myTempFile = null;
	// 保存的patch文件
	private File myPatchFile = null;
	// 保存的myNewFile
	private File myNewFile = null;

	// 文件后缀
	private String fileEx = "";
	// 文件名
	private String fileNa = "";

	// 绝对路径名
	private String currentTempFilePath = "";

	// 进度条弹窗
	private Dialog updateDialog = null;

	// 进度框布局
	private View textEntryView = null;

	// 进度条
	private ProgressBar pbSchedule = null;

	// 进度值
	private TextView tvSchedule = null;

	private String runException = "init";

	private String updateType = "all";// smart

	private String oldApkPath;
	private String newApkPath;
	private String patchPath;

	public AutoUpdateAsyncTask(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 线程运行前准备工作 方法
	 * **/
	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		String apk_url = Constants.getInstance().desktopApkUrl;
		fileEx = apk_url.substring(apk_url.lastIndexOf(".") + 1,
				apk_url.length()).toLowerCase();
		fileNa = apk_url.substring(apk_url.lastIndexOf("/") + 1,
				apk_url.lastIndexOf("."));

		// 弹出进度窗
		LayoutInflater inflater = LayoutInflater.from(activity);
		textEntryView = inflater.inflate(R.layout.updateprogressbar, null);
		pbSchedule = (ProgressBar) textEntryView.findViewById(R.id.pbSchedule);
		pbSchedule.setMax(100);
		pbSchedule.setProgress(0);

		tvSchedule = (TextView) textEntryView.findViewById(R.id.tvSchedule);

		updateDialog = CommonUtil.showDialog(activity, textEntryView,
				(int) (SystemPreference.getInt(activity, "screenWidth") * 0.8),
				LayoutParams.WRAP_CONTENT);
		Button cancleBtn = (Button) textEntryView
				.findViewById(R.id.cancle_update_btn);
		cancleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRun = false;
				updateDialog.dismiss();
				activity.finish();
			}
		});

	}

	/**
	 * 线程方法 相当于run
	 * **/
	@Override
	protected String doInBackground(String... params) {

		String strPath = params[0];

		if (!URLUtil.isNetworkUrl(strPath)) {
			Log.i(this.getClass().getName(),
					"getDataSource() It's a wrong URL!");
			runException = "error";
			return "error";
		}

		URL myURL;
		try {
			// Log.v("bb", apkSize +"字节");

			// myTempFile = File.createTempFile(fileNa, "." + fileEx);
			// currentTempFilePath = myTempFile.getAbsolutePath();
			// Log.v(activity.getClass().getName(),
			// currentTempFilePath);//保存的apk绝对路径
			String fileEx = strPath.substring(strPath.lastIndexOf(".") + 1,
					strPath.length()).toLowerCase();// 下载文件后缀
			String fileNa = strPath.substring(strPath.lastIndexOf("/") + 1,
					strPath.lastIndexOf("."));// 下载文件名
			if (fileNa.length() < 3) {
				fileNa = fileNa + System.currentTimeMillis();
			}
			String sdPath = Environment.getExternalStorageDirectory() + "";// 或得sd卡路径
			Log.v("sdPath", sdPath);
			File dir = new File(sdPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			myTempFile = new File(sdPath + "/" + fileNa + "." + fileEx);
			oldApkPath = sdPath + "/" + fileNa + "." + fileEx;
			newApkPath = sdPath + "/" + fileNa + "New" + "." + fileEx;
			// sd卡判断旧版本apk是否存在，旧版本apk版本是否与正在运行的运用一致。
			// if (myTempFile.exists()
			// && ToolUtil.getUninstallAPKInfo(activity,
			// sdPath + "/" + fileNa + "." + fileEx).equals(
			// ToolUtil.getInstallAPKInfo(activity,
			// activity.getPackageName())))
			// {
			// myPatchFile=new File(sdPath + "/" + fileNa + "." + "patch");
			// patchPath=sdPath + "/" + fileNa + "." + "patch";
			// updateType="smart";
			// myURL = new URL(activity.getString(R.string.desktop_patch_url));
			// URLConnection conn = myURL.openConnection();
			// conn.connect();
			// is = conn.getInputStream();
			// if (is == null)
			// {
			// throw new RuntimeException("stream is null");
			// }
			//
			// apkSize = (float) conn.getContentLength();
			// myPatchFile.createNewFile();
			// fos = new FileOutputStream(myPatchFile);
			//
			// byte buf[] = new byte[512];
			// do
			// {
			// int numread = is.read(buf);
			// if (numread <= 0)
			// {
			// break;
			// }
			// fos.write(buf, 0, numread);
			// alreadySize = alreadySize + buf.length;
			// NumberFormat nf = NumberFormat.getPercentInstance();
			// String proportion = nf.format(alreadySize / apkSize);
			//
			// // 往UI线程传进度值
			// publishProgress(Integer.valueOf(proportion.substring(0,
			// proportion.length() - 1)));
			//
			//
			// } while (isRun);
			//
			// }else{
			updateType = "all";
			myURL = new URL(strPath);
			URLConnection conn = myURL.openConnection();
			conn.connect();
			is = conn.getInputStream();
			if (is == null) {
				throw new RuntimeException("stream is null");
			}

			apkSize = (float) conn.getContentLength();
			myTempFile.createNewFile();
			fos = new FileOutputStream(myTempFile);

			byte buf[] = new byte[1024];
			do {
				int numread = is.read(buf);
				if (numread <= 0) {
					break;
				}
				fos.write(buf, 0, numread);
				alreadySize = alreadySize + buf.length;
				NumberFormat nf = NumberFormat.getPercentInstance();
				String proportion = nf.format(alreadySize / apkSize);

				// 往UI线程传进度值
				publishProgress(Integer.valueOf(proportion.substring(0,
						proportion.length() - 1)));

			} while (isRun);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			runException = "error";
			return "error";
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		return null;
	}

	/**
	 * 线程进度百分比
	 * **/
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);

		pbSchedule.setProgress(values[0]);

		int val = values[0];
		if (val >= 100) {
			val = 99;
		}
		tvSchedule.setText(val + "%");
	}

	/**
	 * 线程运行结束方法
	 * **/
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		if (runException.equals("error")) {

			AlertDialog.Builder aBuilder = new AlertDialog.Builder(activity)
					.setTitle(R.string.systemUpDateTitle)
					.setIcon(R.drawable.imsgico)
					.setMessage("网络中断,请保持网络稳定后再重新升级");
			aBuilder.setPositiveButton("重新升级",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							AutoUpdateAsyncTask auat = new AutoUpdateAsyncTask(
									activity);
							auat.execute(Constants.getInstance().desktopApkUrl);
						}

					});
			aBuilder.setNegativeButton("放弃升级",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							if (updateDialog != null) {
								updateDialog.dismiss();
							}
							activity.finish();
						}

					});

			aBuilder.show();

			return;
		}

		pbSchedule.setProgress(100);
		tvSchedule.setText("100%");

		if (isRun && myTempFile != null) {
			if (updateType.equals("all")) {
				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setAction(android.content.Intent.ACTION_VIEW);
				String type = getMIMEType(myTempFile);
				intent.setDataAndType(Uri.fromFile(myTempFile), type);
				activity.startActivityForResult(intent, 1);
			} else {
				Toast.makeText(activity, "合成新apk", Toast.LENGTH_LONG).show();
				new Thread(new PatchThread()).start();

			}
		}
	}

	/**
	 * 取消线程时调用的方法
	 * **/
	@Override
	protected void onCancelled() {
		super.onCancelled();

	}

	private String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else if (end.equals("apk")) {
			type = "application/vnd.android.package-archive";
		} else {
			type = "*";
		}
		if (end.equals("apk")) {
		} else {
			type += "/*";
		}
		return type;
	}

	class PatchThread implements Runnable {

		@Override
		public void run() {

			// backupApplication("com.sina.weibo", PATH + "WeAgent1.apk");
			PatchUtils pt = new PatchUtils();
			int ret = pt.patch(oldApkPath, newApkPath, patchPath);
			if (myPatchFile.exists()) {
				myPatchFile.delete();
			}
			/*
			 * int ret = PatchUtils.patch(PATH + oldapk_filepath, PATH +
			 * newapk_savepath, PATH + patchpath);
			 */
			myNewFile = new File(newApkPath);
			if (myNewFile.exists()) {
				myNewFile.renameTo(myTempFile);
			}
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.parse("file://" + oldApkPath),
					"application/vnd.android.package-archive");
			activity.startActivityForResult(intent, 1);
			activity.finish();

		}
	}

	static {
		System.loadLibrary("apkpatch");
	}

}
