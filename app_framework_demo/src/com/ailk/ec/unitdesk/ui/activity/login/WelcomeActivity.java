/**
 * 
 */
package com.ailk.ec.unitdesk.ui.activity.login;


import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.models.http.UpgradeInfo;
import com.ailk.ec.unitdesk.ui.activity.BaseActivity;
import com.ailk.ec.unitdesk.utils.CommonUtil;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.SystemPreference;
import com.ailk.ec.unitdesk.utils.setup.AutoUpdate;

/**
 */

public class WelcomeActivity extends BaseActivity {
	public static final String TAG = "WelcomeActivity";

	// TextView msg;
	AutoUpdate autoUpdate;
	// boolean isVersion = false;// true 最新
	private final int UPDATE_ERROR = 19929;
	private final int UPGRADE = 20001;

	@Override
	public void initData() {

		// 获取屏幕宽度、高度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		SystemPreference.setInt(this, "screenWidth", screenWidth);
		SystemPreference.setInt(this, "screenHeight", screenHeight);
		Log.d(dm.density + " " + screenHeight + "   ,  " + screenWidth);
		// 进行网络判断
		// msg.setText("正在检测网络情况");
		if (CommonUtil.isNetworkAvailable(CommonApplication.getInstance())) {
			// msg.setText("正在校验版本信息");

			// 检查软件更新
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
//					autoUpdate = new AutoUpdate(WelcomeActivity.this, handler);
//					WelcomeActivity.this.startUpdateThread();
//					application.isVersionChecked = true;
				}
			}, 500);

		} else {
			showTipDialog(WelcomeActivity.this
					.getString(R.string.netLinkErrorText));
		}
	}

	/**
	 * 版本校验线程
	 */
	private void startUpdateThread() {
		PackageInfo packInfo = autoUpdate.getCurrentVersion();
//		ApiClient.upgrade(handler, UPGRADE, null,
//				Constants.getInstance().packageCd, application.versionCode,
//				Constants.DEVICE_CODE, null, android.os.Build.VERSION.RELEASE,
//				getString(R.string.UNIDESK_ACTION_UPGRADE));
	}

	@Override
	public void initView() {
		setContentView(R.layout.welcome);
		// msg = (TextView) findViewById(R.id.welcome_msg);
		handler = new MyHandler();

	}

	private class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case UPDATE_ERROR:
				showTipDialog(WelcomeActivity.this
						.getString(R.string.netLinkErrorText));
				break;
			case UPGRADE:
				UpgradeInfo result = (UpgradeInfo) msg.obj;
				if (result != null) {
					Constants.getInstance().desktopApkUrl = result.upgradeUrl;
					if ("1".equals(result.forceUpdate)) {
						Log.d(TAG, "发现新版本(" + result.softVersion + ")[强制更新]");
//						if (Constants.getInstance().packageCd == Constants.UPDATE_PACKAGE_CD_TEST) {
//							autoUpdate.update(true, false, result);
//						} else {
//							autoUpdate.update(true, true, result);
//						}
					} else {
						Log.d(TAG, "发现新版本(" + result.softVersion + ")[非强制更新]");
						String ignoreVcode = SystemPreference.getString(ctx,
								Constants.IGNORE);
						if (!result.softVersoinCode.equals(ignoreVcode)) {
							autoUpdate.update(true, false, result);
						} else {
							autoUpdate.update(false, false, null);
						}
					}
				} else {
					autoUpdate.update(false, false, null);
				}
				break;

			default:
				break;
			}
		}

	}

	@Override
	public void onBackPressed() {
		finish();
	}

	public void onClickOk(View view) {
		closeDialog();
		CommonUtil.systemExit(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		destroyData();
		destroyView();
	}

	// 回收全局变量
	private void destroyData() {
		// handler = null;
		// autoUpdate = null;
	}

	// 回收UI控件
	private void destroyView() {
		// msg = null;
	}
}
