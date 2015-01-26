/**
 * 
 */
package com.ailk.ec.unitdesk.net.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.net.ApiClient;
import com.ailk.ec.unitdesk.net.asyncClient.AsyncHttpResponseHandler;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.utils.CommonUtil;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.ToolUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description:
 * @version V1.0
 * @date 2013-11-8
 */

public abstract class HttpBaseRequest extends AsyncHttpResponseHandler {
	protected Handler handler;
	protected int wwhat;
	protected Gson gson;
	// protected ResultStr reusltStrObj;
	// protected Response response;
	public String url;
	protected long instId;

	public HttpBaseRequest(Handler handler, int wwhat) {
		this.handler = handler;
		this.wwhat = wwhat;
		gson = new GsonBuilder().create();
	}

	public HttpBaseRequest(Handler handler, int wwhat, long instId) {
		this(handler, wwhat);
		this.instId = instId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.al.android.somsdesk.net.asyncClient.AsyncHttpResponseHandler#onSuccess
	 * (int, org.apache.http.Header[], byte[])
	 */
	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.al.android.somsdesk.net.asyncClient.AsyncHttpResponseHandler#onFailure
	 * (int, org.apache.http.Header[], byte[], java.lang.Throwable)
	 */
	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		String errorMsg = "";
		// TODO Auto-generated method stub
		if (error instanceof IllegalArgumentException) {
			errorMsg = "连接错误,请稍候再试";
		} else if (error instanceof ConnectTimeoutException) {
			errorMsg = "连接超时,请稍候再试";
		} else if (error instanceof SocketTimeoutException) {
			errorMsg = "请求超时,请稍候再试";
		} else if (error instanceof UnsupportedEncodingException) {
			errorMsg = "编码错误,请稍候再试";
		} else if (error instanceof ClientProtocolException) {
			errorMsg = "协议异常,请稍候再试";
		} else if (error instanceof IOException) {
			errorMsg = "数据读取异常,请稍候再试";
		} else if (error instanceof RequestException) {
			errorMsg = ((RequestException) error).getMessage();
		} else {
			errorMsg = "数据读取异常,请稍候再试";
		}
		// Log.e("onFail: ", errorMsg, error);
		Log.i("this is fail errorMsg", errorMsg);
		Bundle data = new Bundle();
		data.putString("errorMsg", errorMsg);
		Message msg = handler.obtainMessage();
		msg.what = 0;
		msg.setData(data);
		handler.sendMessage(msg);
	}

	/**
	 * 拼装请求体
	 */
	protected abstract RequestParams appendMainBody();

	public void sendPostRequest() {

		if (CommonUtil.isNetworkAvailable(CommonApplication.getInstance())) {
			RequestParams p = new RequestParams();
			PortalClient.post(url, appendMainBody(), this);
		} else {
			// 网络不可用
			// onFail("","","",);

		}
	}

	public void sendGetRequest() {

		if (CommonUtil.isNetworkAvailable(CommonApplication.getInstance())) {
			RequestParams p = new RequestParams();
			PortalClient.client.get(url, appendMainBody(), this);
		} else {
			// 网络不可用
			// onFail("","","",);

		}
	}

	//
	public void downRequest() {

		if (CommonUtil.isNetworkAvailable(CommonApplication.getInstance())) {
			RequestParams p = new RequestParams();
			PortalClient.post(
					CommonApplication.getInstance().getString(
							R.string.PORTAL_YIURL), appendMainBody(), this);
		} else {
			// 网络不可用
			// onFail("","","",);

		}
	}

	public void upLoadFile(RequestParams paths) {

		if (CommonUtil.isNetworkAvailable(CommonApplication.getInstance())) {
			PortalClient.post(
					CommonApplication.getInstance().getString(
							R.string.PORTAL_YIURL), paths, this);
		} else {
			// 网络不可用
			onFailure(
					0,
					new Header[0],
					new byte[0],
					new RequestException(
							RequestException.NET_INAVAILABLE_EXCEPTION,
							CommonApplication.getInstance().getString(
									R.string.netLinkErrorText)));

		}
	}

}
