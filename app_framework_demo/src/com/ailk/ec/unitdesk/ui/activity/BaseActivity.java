package com.ailk.ec.unitdesk.ui.activity;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.ActivityTaskManager;
import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.datastore.DataCleanManager;
import com.ailk.ec.unitdesk.net.portal.PortalClient;
import com.ailk.ec.unitdesk.utils.CommonUtil;
import com.ailk.ec.unitdesk.utils.DensityUtil;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.SystemPreference;

/**
 * 
 * @Description: 基类，用于集合页面的常用操作
 * @modify by spoon.
 */
public abstract class BaseActivity extends Activity {
	// TAG
	public static final String TAG = "BaseActivity";

	public static final int CUSTOM_PROCESS_DIALOG = 1;

	// 页面上下文
	public Context ctx;

	public CommonApplication application;
	// 进度条
	public ProgressDialog pdDialog = null;
	public TextView processMsg = null;

	// 自定义进度条
	public ProgressDialog myPdDialog = null;
	public Dialog dialog = null;

	public Dialog subDialog = null;
	// 是否具有权限请求类型
	public static final int HAS_OPERATIONS_REQUEST_TYPE = 10;
	public static final int IS_SERIAL_OK = 11;
	// 注销请求类型
	public static final int LOGIN_OUT_TYPE = 999;

	// 缓存退出时间
	private long exitTime = 0;

	protected String currentPageTitle;
	protected String fromPageTitle;

	protected Handler handler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 默认隐藏软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		ctx = this.getApplicationContext();
		application = (CommonApplication) getApplication();

		Constants.getInstance().activityList.add(this);
		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	public class CommonHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			closeProgressDialog();
			switch (msg.what) {
			case 0:
				closeMyProgressDialog();
				// 统一的异常处理，以Toast展现，也可以改为对话框
				Bundle result = msg.getData();
				Toast.makeText(ctx, result.getString("errorMsg"),
						Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
		}
	}

	protected void showTipDialog(String msg) {
		if (dialog != null && dialog.isShowing()) {
			return;
		}
		final View view = getLayoutInflater().inflate(
				R.layout.common_tips_dialog, null);
		view.getBackground().setAlpha(100);
		TextView infoText = (TextView) view.findViewById(R.id.tipText);
		infoText.setText(msg);
		Button btn = (Button) view.findViewById(R.id.ok);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeDialog();
			}
		});
		dialog = CommonUtil.showDialog(this, view,
				DensityUtil.dip2px(ctx, 300), DensityUtil.dip2px(ctx, 260));
	}

	/**
	 * 显示圆型进度条
	 * 
	 * @param msg
	 *            进度条信息
	 */
	public void showProgressDialog(String msg) {
		showProgressDialog(msg, CUSTOM_PROCESS_DIALOG);
	}

	/**
	 * 显示圆型进度条 ,私有方法
	 * 
	 * @param msg
	 * @param type
	 */
	private void showProgressDialog(String msg, int type) {
		// if (pdDialog != null)
		// {
		// pdDialog.dismiss();
		// pdDialog = null;
		// }
		pdDialog = ProgressDialog.show(this, "", msg, true, true);
		pdDialog.setCanceledOnTouchOutside(false);
		if (type == CUSTOM_PROCESS_DIALOG) {
			pdDialog.setContentView(R.layout.loading_process_dialog);
			processMsg = (TextView) pdDialog.getWindow().findViewById(
					R.id.process_msg);
			processMsg.setText(msg);
			ImageView cancelImg = (ImageView) pdDialog.getWindow()
					.findViewById(R.id.process_cancel_img);
			cancelImg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					PortalClient.shutdown();
					if (handler != null) {
						Message msg = Message.obtain();
						msg.what = -1;
						handler.sendMessage(msg);
					}
					closeProgressDialog();
				}
			});

		}
		// 设置返回，取消对话框监听，中断后台网络请求
		pdDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				PortalClient.shutdown();
				if (handler != null) {
					Message msg = Message.obtain();
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		});
	}

	/**
	 * 显示自定义进度条
	 * 
	 * @param msg
	 */

	public void showMyProgressDialog(String msg) {
		showMyProgressDialog(msg, CUSTOM_PROCESS_DIALOG);
	}

	/**
	 * 显示自定义进度条 ,私有方法
	 * 
	 * @param msg
	 * @param type
	 */
	private void showMyProgressDialog(String msg, int type) {
		if (myPdDialog != null) {
			myPdDialog.dismiss();
			myPdDialog = null;
		}
		myPdDialog = ProgressDialog.show(this, "", msg, true, true);
		myPdDialog.setCanceledOnTouchOutside(false);
		if (type == CUSTOM_PROCESS_DIALOG) {
			myPdDialog.setContentView(R.layout.loading_process_dialog);
			processMsg = (TextView) myPdDialog.getWindow().findViewById(
					R.id.process_msg);
			processMsg.setText(msg);
			ImageView cancelImg = (ImageView) myPdDialog.getWindow()
					.findViewById(R.id.process_cancel_img);
			cancelImg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					PortalClient.shutdown();
					if (handler != null) {
						Message msg = Message.obtain();
						msg.what = -1;
						handler.sendMessage(msg);
					}
					closeMyProgressDialog();
				}
			});

		}
		// 设置返回，取消对话框监听，中断后台网络请求
		myPdDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				PortalClient.shutdown();
				if (handler != null) {
					Message msg = Message.obtain();
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		});
	}

	/**
	 * 
	 * @param msg
	 */
	public void setMyProgressMsg(String msg) {

		if (myPdDialog != null) {
			myPdDialog.setMessage(msg);
		}
		if (processMsg != null) {
			processMsg.setText(msg);
		}
	}

	/**
	 * 
	 */
	/**
	 * 显示自定义进度条
	 */
	private void showMyProgressDialog(View view)

	{
		if (myPdDialog != null) {
			myPdDialog.dismiss();
			myPdDialog = null;
		}
		myPdDialog = ProgressDialog.show(this, "", "", true, true);
		myPdDialog.setCanceledOnTouchOutside(false);
		// 设置返回，取消对话框监听，中断后台网络请求
		myPdDialog.setContentView(view);

		myPdDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				PortalClient.shutdown();
				if (handler != null) {
					Message msg = Message.obtain();
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		});
	}

	/**
	 * 显示圆型进度条,值默认为:获取数据中,请稍等...
	 */
	private void showProgressDialog() {
		if (pdDialog != null) {
			pdDialog.dismiss();
			pdDialog = null;
		}
		String msg = "获取数据中,请稍等...";
		pdDialog = ProgressDialog.show(this, "", msg, true, true);
		pdDialog.setCanceledOnTouchOutside(false);
		// pdDialog.setContentView(R.layout.loading_process_dialog);
		// msgText = (TextView) pdDialog.getWindow().findViewById(
		// R.id.message_content);
		// msgText.setText(msg);
		// 设置返回，取消对话框监听，中断后台网络请求
		pdDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				PortalClient.shutdown();
			}
		});
	}

	/**
	 * 错误提示框
	 * 
	 * @param msg
	 */
	public void showMessageTipDialog(String msg) {

	}

	/**
	 * 调用服务统一错误提示框
	 * 
	 * @param msg
	 */
	public void showCommErrorDialog(String msg) {

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
	 * 关闭自定义进度条
	 */
	public void closeMyProgressDialog() {
		if (myPdDialog != null) {
			myPdDialog.dismiss();
			myPdDialog = null;
		}
	}

	public void closeDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	public void closeSubDialog() {
		if (subDialog != null) {
			subDialog.dismiss();
			subDialog = null;
		}
	}

	public int clearCacheFolder(File dir, long numDays) {
		int deletedFiles = 0;
		if (dir != null && dir.isDirectory()) {
			try {
				for (File child : dir.listFiles()) {
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, numDays);
					}
					if (child.lastModified() < numDays) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}

	/**
	 * 退出确认
	 */
	public void confirmExit() {

		if ((System.currentTimeMillis() - exitTime) > 2000) {
			final Toast toast = CommonUtil.showMessage(ctx, "再按一次退出程序");
			exitTime = System.currentTimeMillis();
			handler.postDelayed(new Runnable() {
				public void run() {
					if (toast != null) {
						CommonUtil.closeMessage(toast);
					}
				}
			}, 1000);
		} else {
			try {
				File file = this.getCacheDir();
				clearCacheFolder(file, System.currentTimeMillis());
				CookieSyncManager.createInstance(this);
				CookieSyncManager.getInstance().stopSync();
				CookieManager.getInstance().removeAllCookie();
				CookieManager.getInstance().removeSessionCookie();
				CookieSyncManager.getInstance().sync();
				ctx.deleteDatabase("webview.db");
				ctx.deleteDatabase("webviewCache.db");
				DataCleanManager.cleanApplicationCache(ctx);
			} catch (Exception e) {
				e.printStackTrace();
			}
			releaseUserData();
			ActivityTaskManager.getInstance().closeAllActivity();
			// 清空活动activity列表
			for (Activity act : Constants.getInstance().activityList) {
				act.finish();
			}
			this.finish();
			Constants.getInstance().activityNameList.clear();
			// CommonUtil.killProcess();
		}

		// AlertDialog.Builder ad = new AlertDialog.Builder(BaseActivity.this);
		// ad.setTitle(R.string.systemBackTitle);
		// ad.setMessage(R.string.softwareBackTitle);
		// // 退出按钮
		// ad.setPositiveButton(R.string.yes,
		// new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int i) {
		// //清空活动activity列表
		// for (Activity act : Constants.getInstance().activityList) {
		// act.finish();
		// }
		// Constants.getInstance().activityList.clear();
		// // 关闭程序
		// CommonUtil.systemExit(BaseActivity.this);
		// }
		// });
		// ad.setNegativeButton(R.string.no,
		// new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int i) {
		// // 不退出不用执行任何操作
		// }
		// });
		// // 显示对话框
		// ad.show();
	}

	/**
	 * 跳转到其他界�?
	 * 
	 * @param cls
	 *            跳转页面
	 * @param bundle
	 *            Bundle参数
	 * @param isReturn
	 *            是否返回
	 * @param requestCode
	 *            请求Code
	 * @param isFinish
	 *            是否�?��当前页面
	 */
	public void jumpToPage(Class<?> cls, Bundle bundle, boolean isReturn,
			int requestCode, boolean isFinish) {
		if (cls == null) {
			return;
		}

		Intent intent = new Intent();
		intent.setClass(this, cls);

		if (bundle != null) {
			intent.putExtras(bundle);
		}

		if (isReturn) {
			startActivityForResult(intent, requestCode);
		} else {
			startActivity(intent);
		}
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		// 清除dialog

		closeMyProgressDialog();
		if (isFinish) {
			finish();
		}
	}

	/**
	 * 跳转到其他界�?
	 * 
	 * @param cls
	 *            跳转页面
	 * @param bundle
	 *            Bundle参数
	 * @param isFinish
	 *            是否�?��当前页面
	 */
	public void jumpToPage(Class<?> cls, Bundle bundle, boolean isFinish) {
		jumpToPage(cls, bundle, false, 0, isFinish);
	}

	/**
	 * 跳转到其他界面，不销毁当前页面，也不传参�?
	 * 
	 * @param cls
	 *            跳转页面
	 */
	public void jumpToPage(Class<?> cls) {
		jumpToPage(cls, null, false, 0, false);
	}

	/**
	 * 返回键监听
	 */
	@Override
	public void onBackPressed() {
		Log.i(this.getClass().getName() + " onBackPressed()");
		// if (this instanceof TaskHallActivity)
		// {
		// confirmExit();
		// }
		// backPage();
		finish();
	}

	/**
	 * 初始化组件
	 */
	public abstract void initView();

	/**
	 * 初始化数据
	 */
	public abstract void initData();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Constants.getInstance().activityList.remove(this);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * 清除用户相关信息
	 */
	public void releaseUserData() {

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (getCurrentFocus() != null
					&& getCurrentFocus().getWindowToken() != null) {
				manager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	/**
	 * 
	 * @param activity
	 * @return > 0 success; <= 0 fail
	 */
	public int getStatusHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass
						.getField("status_bar_height").get(localObject)
						.toString());
				statusHeight = activity.getResources()
						.getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

}
