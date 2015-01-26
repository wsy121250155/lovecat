/**
 * 
 */
package com.ailk.ec.unitdesk.net.portal;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.net.asyncClient.AsyncHttpClient;
import com.ailk.ec.unitdesk.net.asyncClient.AsyncHttpResponseHandler;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * @Description:
 * @version V1.0
 * @date 2013-11-8
 * 
 * 
 *       门户请求基类 提供：get，post，上传，下载等请求
 */

public class PortalClient {
	private static final String BASE_URL = "";
	private static String CONTENTTYPE = "application/x-www-form-urlencoded;charset=UTF-8";
	private static String ENCODING = "UTF-8";

	public static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);

	}

	// 门户层post请求，返回出来在responseHandler接口的实现类中进�?
	public static void post(String url, String entity,
			AsyncHttpResponseHandler responseHandler) {
		try {
			Log.d("PortalClient", entity);
			client.post(CommonApplication.getInstance(), getAbsoluteUrl(url),
					new StringEntity(entity, ENCODING), CONTENTTYPE,
					responseHandler);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

	private static void cancle() {
		client.cancelRequests(CommonApplication.getInstance(), true);
	}

	public static void shutdown() {
		cancle();
	}

}
