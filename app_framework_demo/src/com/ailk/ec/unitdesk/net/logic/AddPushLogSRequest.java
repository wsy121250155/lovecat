package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.SimpleResult;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.net.portal.HttpBaseRequest;
import com.ailk.ec.unitdesk.utils.Log;

public class AddPushLogSRequest extends HttpBaseRequest {

	public final String TAG = "AddPushLogSRequest";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected RequestParams appendMainBody() {
		// TODO Auto-generated method stub
		RequestParams param = new RequestParams();
		param.put("userId", userId);
		param.put("areaCode", areaCode);
		param.put("deviceType", deviceType);
		param.put("ArrPushLog", ArrPushLog);
		return param;
	}

	private String deviceType;
	private String userId;
	private String areaCode;
	private String ArrPushLog;

	public AddPushLogSRequest(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String ArrPushLog, String url) {
		super(handler, wwhat);
		this.userId = userId;
		this.areaCode = areaCode;
		this.deviceType = deviceType;
		this.ArrPushLog = ArrPushLog;
		this.url = url;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		String resultStr = new String(responseBody);
		Log.d(TAG, " resultStr: " + resultStr);
		SimpleResult result = gson.fromJson(resultStr, SimpleResult.class);
		Message msg = Message.obtain();
		msg.what = wwhat;
		msg.obj = result;
		handler.sendMessage(msg);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}
}
