package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.GetAreaResult;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.net.portal.HttpBaseRequest;
import com.ailk.ec.unitdesk.utils.Log;

public class GetUserAreaRequest extends HttpBaseRequest {

	public final String TAG = "GetUserAreaRequest";

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	protected RequestParams appendMainBody() {
		// TODO Auto-generated method stub
		RequestParams param = new RequestParams();

		param.put("staffId", staffId);
		// param.put("pracctName", pracctName);
		// param.put("SystemId", SystemId);
		// param.put("querySystemId", querySystemId);

		return param;
	}

	/**
	 * 大写
	 */
	private String staffId;

	// private String pracctName;
	// private String SystemId;
	// private String querySystemId;

	public GetUserAreaRequest(Handler handler, int wwhat, String staffId,
			String url) {
		super(handler, wwhat);
		this.staffId = staffId;
		this.url = url;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		String resultStr = new String(responseBody);
		Log.d(TAG, " resultStr: " + resultStr);
		GetAreaResult result = gson.fromJson(resultStr, GetAreaResult.class);
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
