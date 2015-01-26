package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;

import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * 标记已读
 * 
 * @author spoon
 * 
 */
public class AddFuncTempLogRequest extends PortalRequest {

	public final String TAG = "AddFuncTempLogRequest";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected String appendMainBody() {
		// TODO Auto-generated method stub
		JSONRequest request = new JSONRequest("", "1111", actionCode);
		JSONObject param = new JSONObject();
		try {
			param.put("userId", userId);
			param.put("areaCode", areaCode);
			param.put("deviceType", deviceType);
			param.put("ArrFuncTempLog", ArrFuncTempLog);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String userId;
	private String deviceType;
	private String areaCode;
	private String ArrFuncTempLog;

	public AddFuncTempLogRequest(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String ArrFuncTempLog,
			String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.userId = userId;
		this.areaCode = areaCode;
		this.deviceType = deviceType;
		this.ArrFuncTempLog = ArrFuncTempLog;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			Log.d(TAG, " resultStr: " + reusltStrObj.result);
			// Log.e("模板实例: " + reusltStrObj.result, true);
			// SearchTemplatesResult result = gson.fromJson(reusltStrObj.result,
			// SearchTemplatesResult.class);
			// Message msg = Message.obtain();
			// msg.what = wwhat;
			// msg.obj = result;
			// handler.sendMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}
}
