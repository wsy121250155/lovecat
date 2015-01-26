package com.mobile.base.util;

import java.io.File;
import java.util.Map;

import android.os.Environment;

public class UrlUtils {
	/**
	 * 获取带参数请求的文件URL
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getFileUrl(String url, Map<String, Object> params) {
		String res = null;
		
		if(url.startsWith("http://")) {
			res = makeURL(url, params);
		} else {
			params.put("id", url);
			res = makeURL(ConstantResource.DOWNLOAD_URL, params);
		}
		
		return res;
	}
	
	/**
	 * 获取不带参数请求文件的URL
	 * @param url
	 * @return
	 */
	public static String getFileUrl(String url) {
		StringBuilder sb = new StringBuilder();
		
		if(url.startsWith("http://")) {
			sb.append(url);
		} else {
			sb.append(ConstantResource.DOWNLOAD_URL).append("?id=").append(url);
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取带尺寸参数的图片文件，url参数为图片文件id
	 * @param url
	 * @param w
	 * @param h
	 * @return
	 */
	public static String getFileUrl(String url, int w, int h) {		
		String res = new StringBuilder(getFileUrl(url)).
				append("&w=").append(w).append("&h=").
				append(h).toString();
		
		return res;
	}
	
	/**
	 * 拼装url
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String makeURL(String url, Map<String, Object> params) {
		StringBuilder mUrl = new StringBuilder(url);
		if (mUrl.indexOf("?") < 0)
			mUrl.append('?');

		for (String name : params.keySet()) {
			mUrl.append('&');
			mUrl.append(name);
			mUrl.append('=');
			mUrl.append(String.valueOf(params.get(name)));
		}

		return url.toString().replace("?&", "?");
	}
}
