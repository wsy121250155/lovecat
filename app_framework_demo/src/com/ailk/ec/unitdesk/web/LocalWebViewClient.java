/**
 * 
 */
package com.ailk.ec.unitdesk.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Set;

import org.apache.cordova.CordovaWebViewClient;
import org.apache.cordova.api.CordovaInterface;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.datastore.db.DBManager;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * @Description:
 * @version V1.0
 * @date 2013-11-12
 */

public class LocalWebViewClient extends CordovaWebViewClient {
	private CordovaInterface context;
	private CommonApplication ctx;

	public LocalWebViewClient(CordovaInterface context) {
		super(context);
		this.context = context;
		ctx = CommonApplication.getInstance();
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {

		return super.shouldOverrideUrlLoading(view, url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		Log.d("onPageStarted", url);
		// 加截本地资源。
		if (url.startsWith("https://") || url.startsWith("http://")) {
			try {
				if (Integer.parseInt(android.os.Build.VERSION.SDK) < 11) {
					// loadLocalResouces();
				}
			} catch (Exception e) {
				Log.e("loadLocalResouces", url, e);
			}
		}
		super.onPageStarted(view, url, favicon);

	}

	@Override
	public void onPageFinished(WebView view, String url) {// 页面加载结束监听

		super.onPageFinished(view, url);

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

	/*
	 * private void loadLocalResouces() { Set<String> st =
	 * Constants.jsMap.keySet(); for (Iterator it = st.iterator();
	 * it.hasNext();) { String s = (String) it.next();
	 * copyToCacheDb(Constants.jsMap.get(s), s); }
	 * 
	 * }
	 */

	/*
	 * private void copyToCacheDb(String assetFile, String url) { Cursor cursor
	 * = null; try {
	 * 
	 * cursor = DBManager.getInstance().selectByUrl(url); if
	 * (cursor.moveToNext()) { Log.d("copyFile", url + " is exist."); return; }
	 * } catch (Exception e) { Log.e("Exception", "Exception", e); } finally {
	 * // if ( DBManager.getInstance().dbHelp != null) { //
	 * DBManager.getInstance().dbHelp.close(); // // } if (cursor != null) {
	 * cursor.close();
	 * 
	 * } } String cacheFile = getHash(url); // cacheFile = "500db470";
	 * InputStream inStream = null; FileOutputStream fs = null;
	 * 
	 * String mimetype = ""; if (assetFile.endsWith(".js")) { mimetype =
	 * "application/javascript"; } else if (assetFile.endsWith(".css")) {
	 * mimetype = "text/css"; } else if (assetFile.endsWith(".png")) { mimetype
	 * = "image/png"; } else if (assetFile.endsWith(".gif")) { mimetype =
	 * "image/gif"; }
	 * 
	 * try {
	 * 
	 * int byteread = 0; AssetManager assetManager = ctx.getAssets(); inStream =
	 * assetManager.open(assetFile);
	 * 
	 * File file = new File(ctx.getCacheDir() + "/webviewCache/" + cacheFile);
	 * fs = new FileOutputStream(file); byte[] buffer = new byte[1024]; int
	 * count = 0; while ((byteread = inStream.read(buffer)) != -1) {
	 * fs.write(buffer, 0, byteread); count += byteread; }
	 * 
	 * long result = DBManager.getInstance().insert(url, cacheFile,
	 * "Fri, 23 Nov 2012 06:18:46 GMT", "5315-7b21-4cf238e6a9fc0",
	 * "1359085496770", String.valueOf(count),
	 * "Fri, 25 Jan 2013 03:40:12 GMT200", mimetype); Log.d("copyFile",
	 * "copyFile==" + cacheFile + "  result=" + result);
	 * 
	 * } catch (Exception e) { Log.e("Exception", "Exception", e);
	 * 
	 * } finally {
	 * 
	 * try { if (inStream != null) { inStream.close(); } // if
	 * (DBManager.getInstance().dbHelp != null) { //
	 * DBManager.getInstance().dbHelp.close(); // // } if (fs != null) {
	 * fs.close(); } } catch (Exception e) { Log.e("Exception", "Exception", e);
	 * }
	 * 
	 * }
	 * 
	 * }
	 */

	private static String getHash(String url) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(url.getBytes());
			return new BigInteger(digest.digest()).toString(16);
		} catch (Exception ex) {
			Log.e("Exception", "Exception", ex);
			return url;
		}
	}

	//
	//
	// });

}
