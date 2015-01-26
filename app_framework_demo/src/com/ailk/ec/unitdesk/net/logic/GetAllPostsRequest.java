package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.GetAllPostsResult;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.net.portal.HttpBaseRequest;
import com.ailk.ec.unitdesk.utils.Log;

public class GetAllPostsRequest extends HttpBaseRequest {

	public final String TAG = "GetAllPostsRequest";

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
		return param;
	}

	private String userId;
	private String areaCode;
	private String deviceType;

	public GetAllPostsRequest(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String url) {
		super(handler, wwhat);
		this.userId = userId;
		this.areaCode = areaCode;
		this.deviceType = deviceType;
		this.url = url;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		String resultStr = new String(responseBody);
		Log.d(TAG, " resultStr: " + resultStr);
		Log.e("所有词帖 ：  "+resultStr, true);
		GetAllPostsResult allPosts = gson.fromJson(resultStr,
				GetAllPostsResult.class);
		Message msg = Message.obtain();
		msg.what = wwhat;
		msg.obj = allPosts;
		handler.sendMessage(msg);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}
}
