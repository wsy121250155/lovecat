package com.ailk.ec.unitdesk.ui.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.utils.Log;

/* =========================================================
 * @Description: 基类，用于集合页面的常用操作
 * @modify by spoon
 * @version V1.0
 * =========================================================
 */
public class BaseActivityGroup extends ActivityGroup {

	// 页面上下文
	public Context ctx;

	public CommonApplication application;

	// 进度条
	public ProgressDialog pdDialog = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ctx = this.getApplicationContext();
		application = (CommonApplication) getApplication();
	}

	/**
	 * 显示圆型进度条
	 * 
	 * @param msg
	 *            进度条信息
	 */
	public void showProgressDialog(String msg) {
		if (pdDialog != null) {
			pdDialog.dismiss();
			pdDialog = null;
		}
		pdDialog = ProgressDialog.show(this, "", msg, true, true);
		pdDialog.setCanceledOnTouchOutside(false);
		pdDialog.setCancelable(false);
		// pdDialog.setContentView(R.layout.loading_process_dialog);
		// TextView msgText = (TextView) pdDialog.getWindow().findViewById(
		// R.id.message_content);
		// msgText.setText(msg);

	}

	/**
	 * 显示圆型进度条,值默认为:获取数据中,请稍等...
	 */
	public void showProgressDialog() {
		if (pdDialog != null) {
			pdDialog.dismiss();
			pdDialog = null;
		}
		String msg = "获取数据中,请稍等...";
		pdDialog = ProgressDialog.show(this, "", msg, true, true);
	}

	/**
	 * 关闭圆型进度条
	 */
	public void closeProgressDialog() {
		if (pdDialog != null) {
			pdDialog.dismiss();
			pdDialog = null;
		}
	}

	/**
	 * 退出确认
	 */
	public void confirmExit(final Handler handler) {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setTitle(R.string.systemBackTitle);
		ad.setMessage(R.string.softwareBackTitle);
		// 退出按钮
		ad.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int i) {

					}
				});
		ad.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int i) {
						// 不退出不用执行任何操作
					}
				});
		// 显示对话框
		ad.show();
	}

	/**
	 * destroy activity
	 * 
	 * @param id
	 * @param m_ActivityManager
	 * @return
	 */
	protected boolean destroy(String id, LocalActivityManager m_ActivityManager) {
		if (m_ActivityManager != null) {
			m_ActivityManager.destroyActivity(id, false);
			try {
				final Field mActivitiesField = LocalActivityManager.class
						.getDeclaredField("mActivities");
				if (mActivitiesField != null) {
					mActivitiesField.setAccessible(true);
					@SuppressWarnings("unchecked")
					final Map<String, Object> mActivities = (Map<String, Object>) mActivitiesField
							.get(m_ActivityManager);
					if (mActivities != null) {
						mActivities.remove(id);
					}
					final Field mActivityArrayField = LocalActivityManager.class
							.getDeclaredField("mActivityArray");
					if (mActivityArrayField != null) {
						mActivityArrayField.setAccessible(true);
						@SuppressWarnings("unchecked")
						final ArrayList<Object> mActivityArray = (ArrayList<Object>) mActivityArrayField
								.get(m_ActivityManager);
						if (mActivityArray != null) {
							for (Object record : mActivityArray) {
								final Field idField = record.getClass()
										.getDeclaredField("id");
								if (idField != null) {
									idField.setAccessible(true);
									final String _id = (String) idField
											.get(record);
									if (id.equals(_id)) {
										mActivityArray.remove(record);
										break;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/**
	 * 返回键操作
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(" onDestroy BaseActivityGroup ");

	}

	public class CommonHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// closeProgressDialog();
			switch (msg.what) {
			case 0:
				// 统一的异常处理，以Toast展现，也可以改为对话框
				closeProgressDialog();
				Bundle result = msg.getData();
				Toast.makeText(ctx, result.getString("errorMsg"),
						Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
		}

	}

}
