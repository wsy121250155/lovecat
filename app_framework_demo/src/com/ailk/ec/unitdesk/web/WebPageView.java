/**
 * 
 */
package com.ailk.ec.unitdesk.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.web.jsinterface.JavaScriptInterface;

/**
 * 
 * @author Spoon
 * @date 2014年6月24日
 * @version 1.0
 */

public class WebPageView extends WebView {
	public static final String TAG = "WebPageView";
	private WebSettings settings;
	LocalWebViewClient localViewClient;
	// 默认的超时 时间
	int loadUrlTimeout = 50000;
	private String url;
	private Context context;
	private StringBuffer currentUrl = new StringBuffer();

	public JavaScriptInterface javaScriptInterface;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public WebPageView(Context context) {
		super(context);
		constructView(context);
		addJavascript();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public WebPageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		constructView(context);
		addJavascript();
	}

	public WebPageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		constructView(context);
		addJavascript();
	}

	private void constructView(Context context) {
		this.context = context;
		WebSettings webSettings = getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		webSettings.setAppCacheEnabled(true);
		webSettings.setAppCacheMaxSize(1024 * 1024 * 5);
		webSettings.setRenderPriority(RenderPriority.HIGH);
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);

		String databasePath = context.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		webSettings.setDatabaseEnabled(true);
		webSettings.setDatabasePath(databasePath);
		webSettings.setGeolocationDatabasePath(databasePath);

		// Enable DOM storage
		webSettings.setDomStorageEnabled(true);

		// Enable built-in geolocation
		webSettings.setGeolocationEnabled(true);

		String pathToCache = context.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		webSettings.setAppCachePath(pathToCache);

		// try {
		// Method method = WebSettings.class.getDeclaredMethod(
		// "setPageCacheCapacity", Integer.class);
		// method.setAccessible(true);
		// method.invoke(webSettings, 5);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// set the webview has no ScrollBar
		setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
		// Set the chrome handler. This is an implementation
		// of WebChromeClient for use in handling Javascript dialogs,
		// favicons, titles, and the progress. This will replace the current
		// handler.
		// setWebChromeClient(new ChromeCallback());

		// Set the WebViewClient that will receive various
		// notifications and requests. This will replace the current
		// handler.
	}

	private void addJavascript() {
		javaScriptInterface = new JavaScriptInterface(this);
		addJavascriptInterface(javaScriptInterface, "GetLocationPlugin");
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
	 * @param time
	 */
	public void loadUrl(String url, int time) {
		final String urlF = url;
		final WebPageView me = this;
		final int currentLoadUrlTimeout = time;

		// Timeout error method
		final Runnable loadError = new Runnable() {
			public void run() {
				me.stopLoading();
				Log.e(TAG, ": TIMEOUT ERROR!");
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
				// if (WebPageView.this.getProgress() < 100) {
				// ((Activity) context).runOnUiThread(loadError);
				// }
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

}
