package com.ailk.ec.unitdesk.web;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * WebViewClient class helps the WebView class handle all kinds of notices,
 * request event, error code e.g.
 */
public class ViewCallback extends WebViewClient {
	public static final String TAG = "ViewCallback";

	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		super.onReceivedError(view, errorCode, description, failingUrl);
		Log.e(TAG, " errorCode : " + errorCode + " description : "
				+ description);
		if (errorCode == -6) {
			view.stopLoading();
			view.clearHistory();
			view.destroyDrawingCache();
			// view.loadUrl("file:///android_asset/page/connectionErr.html");
		}
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		// if (clearHistoryPending)
		// {
		// clearHistoryPending = false;
		//
		// // Tell the WebView to clear its internal back/forward list
		// clearHistory();
		// }
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		if (!url.contains("javascript:")) {
		}
	}

	/**
	 * Give the host application a chance to take over the control when a new
	 * url is about to be loaded in the current WebView. If WebViewClient is not
	 * provided, by default WebView will ask Activity Manager to choose the
	 * proper handler for the url. If WebViewClient is provided, return true
	 * means the host application handles the url, while return false means the
	 * current WebView handles the url.
	 * 
	 * @param view
	 *            The WebView that is initiating the callback.
	 * @param url
	 *            The url to be loaded.
	 * @return True if the host application wants to leave the current WebView
	 *         and handle the url itself, otherwise return false.
	 */
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// 屏蔽手机自己捕捉电话号码和邮箱地址的事件
		if (url.startsWith("mailto:") || url.startsWith("tel:")) {
			return false;
		}

		return super.shouldOverrideUrlLoading(view, url);
	}

	@SuppressLint("NewApi")
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
		Log.d(url);
		String[] s = url.split("/");
		Log.d(s[s.length - 1]);
		if (s != null && Constants.jsMap.containsKey(s[s.length - 1])) {
			// System.out.println("拦截到");
			// String filePath =
			// url.replace("http://192.168.11.44:7001/release/test/", "");
			// System.out.println(filePath);
			Log.d("拦截到" + url);
			try {
				InputStream is = CommonApplication.getInstance().getAssets()
						.open(Constants.jsMap.get(s[s.length - 1]));
				WebResourceResponse wr = null;
				if (Constants.jsMap.get(s[s.length - 1]).endsWith(".css")) {
					wr = new WebResourceResponse("text/css", "UTF-8", is);
				} else if (Constants.jsMap.get(s[s.length - 1]).endsWith(".js")) {
					wr = new WebResourceResponse("text/javascript", "UTF-8", is);
				}
				return wr;
			} catch (IOException e) {
				return null;
			}
		} else {
			return null;
		}

	}

}
