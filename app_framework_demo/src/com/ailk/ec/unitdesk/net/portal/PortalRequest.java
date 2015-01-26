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
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.net.ApiClient;
import com.ailk.ec.unitdesk.net.asyncClient.AsyncHttpResponseHandler;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.utils.CommonUtil;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.StringUtils;
import com.ailk.ec.unitdesk.utils.ToolUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description:
 * @version V1.0
 * @date 2013-11-8
 */

public abstract class PortalRequest extends AsyncHttpResponseHandler {
	protected Handler handler;
	protected int wwhat;
	protected Gson gson;
	protected ResultStr reusltStrObj;
	protected Response response;
	protected long instId;

	public PortalRequest(Handler handler, int wwhat, long instId) {
		this.handler = handler;
		this.wwhat = wwhat;
		this.instId = instId;
		gson = new GsonBuilder().create();
	}

	public PortalRequest(Handler handler, int wwhat) {
		this.handler = handler;
		this.wwhat = wwhat;
		gson = new GsonBuilder().create();
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
		response = new Response(new String(responseBody));
		// 判断门户层返回状态码
		Log.d("this is success resultStr", response.getResultStr());
		if (!StringUtils.isEmpty(response.getCode())
				&& response.getCode().equals(Constants.getInstance().R_SUCCESS)) {
			reusltStrObj = gson.fromJson(response.getResultStr(),
					ResultStr.class);
		} else {
			onFailure(
					0,
					new Header[0],
					new byte[0],
					new RequestException(
							RequestException.CLIENT_PROTOL_EXCEPTION, response
									.getMessage() == null ? CommonApplication
									.getInstance().getString(
											R.string.netLinkErrorText)
									: response.getMessage()));
			Log.e("onFail: " + response.getMessage());
		}

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
		if (handler != null) {
			Bundle data = new Bundle();
			data.putString("errorMsg", errorMsg);
			data.putLong("instId", instId);
			Message msg = handler.obtainMessage();
			msg.what = 0;
			msg.arg1 = wwhat;
			msg.setData(data);
			handler.sendMessage(msg);
		}
	}

	/**
	 * 拼装请求体
	 */
	protected abstract String appendMainBody();

	public void sendPostRequest() {

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

	// public void sendGetRequest()
	// {
	//
	// if (CommonUtil.isNetworkAvailable(CommonApplication.getInstance()))
	// {
	// RequestParams p=new RequestParams();
	// PortalClient.post( CommonApplication.getInstance().getString(
	// R.string.PORTAL_YIURL), appendMainBody(), this);
	// } else
	// {
	// // 网络不可用
	// //onFail("","","",);
	//
	// }
	// }
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
