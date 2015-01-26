/**
 * 
 */
package com.ailk.ec.unitdesk.web;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.ailk.ec.unitdesk.utils.Log;

/**
 * @Description:
 * @version V1.0
 * @date 2013-10-11
 * 
 *       个性化webview，持有特性: 1.触摸 事件返回到父控件空字符 ，由ownTouch控制 ,默认是true 2.超时捕获 3.载入个性化
 *       webviewcilent
 * 
 */

public class TestWebView extends WebView {
	public static final String TAG = "MyWebView";
	// 控制webview是否具有自己的touch事件
	private boolean ownTouch = true;
	private WebSettings settings;
	private Context context;
	LocalWebViewClient localViewClient;
	// 默认的超时 时间
	int loadUrlTimeout = 20000;
	private String url;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TestWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		initWebViewClient();
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TestWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		initWebViewClient();
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TestWebView(Context context, AttributeSet attrs, boolean ownTouch) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.ownTouch = ownTouch;
		initWebViewClient();
		init();
	}

	// 初始参数
	private void init() {
		settings = this.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);

		// We don't save any form data in the application
		settings.setSaveFormData(false);
		settings.setSavePassword(false);

		// Enable database
		// We keep this disabled because we use or shim to get around
		// DOM_EXCEPTION_ERROR_16
		String databasePath = context.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		settings.setDatabaseEnabled(true);
		settings.setDatabasePath(databasePath);
		settings.setGeolocationDatabasePath(databasePath);

		// Enable DOM storage
		settings.setDomStorageEnabled(true);

		// Enable built-in geolocation
		settings.setGeolocationEnabled(true);

		// Enable AppCache
		// Fix for CB-2282
		settings.setAppCacheMaxSize(5 * 1048576);
		String pathToCache = context.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		settings.setAppCachePath(pathToCache);
		settings.setAppCacheEnabled(true);

	}

	// 初始化
	private void initWebViewClient() {

		this.setWebViewClient(new LocalWebViewClientSimple());

	}

	/**
	 * Load the url into the webview.
	 * 
	 * @param url
	 */
	@Override
	public void loadUrl(String url) {

		loadUrl(url, loadUrlTimeout);
	}

	/**
	 * Load the url into the webview.
	 * 
	 * @param url
	 */
	public void loadUrl(String url, int time) {
		final String urlF = url;
		final TestWebView me = this;
		final int currentLoadUrlTimeout = time;

		// Timeout error method
		final Runnable loadError = new Runnable() {
			public void run() {
				me.stopLoading();
				Log.e(TAG, "CordovaWebView: TIMEOUT ERROR!");
				if (localViewClient != null) {
					localViewClient.onReceivedError(me, -6,
							"The connection to the server was unsuccessful.",
							urlF);
				}
			}
		};

		// Timeout timer method
		final Runnable timeoutCheck = new Runnable() {
			public void run() {
				try {
					synchronized (this) {
						wait(currentLoadUrlTimeout);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// If timeout, then stop loading and handle error
				try {
					if (TestWebView.this.getProgress() < 100) {
						((Activity) context).runOnUiThread(loadError);
					}
				} catch (Exception e) {
				}

			}
		};
		// Load url
		((Activity) context).runOnUiThread(new Runnable() {
			public void run() {
				Thread thread = new Thread(timeoutCheck);
				thread.start();
				me.loadUrlNow(urlF);
			}
		});

	}

	/**
	 * Load URL in webview.
	 * 
	 * @param url
	 */
	public void loadUrlNow(String url) {

		super.loadUrl(url);

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		if (ownTouch) {
			return super.onTouchEvent(ev);
		} else {
			return ((RelativeLayout) getParent()).onTouchEvent(ev);
		}
	}

	public boolean isOwnTouch() {
		return ownTouch;
	}

	public void setOwnTouch(boolean ownTouch) {
		this.ownTouch = ownTouch;
	}

}
