package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.SearchTemplatesResult;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * 搜索磁铁模板
 * 
 * @author spoon
 * 
 */
public class SearchFuncTempRequest extends PortalRequest {

	public final String TAG = "SearchFuncTempRequest";

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
			param.put("key", key);
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
	private String key;

	public SearchFuncTempRequest(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String key, String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.userId = userId;
		this.areaCode = areaCode;
		this.deviceType = deviceType;
		this.key = key;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			Log.d(TAG, " resultStr: " + reusltStrObj.result);
			SearchTemplatesResult result = gson.fromJson(reusltStrObj.result,
					SearchTemplatesResult.class);
			Message msg = Message.obtain();
			msg.what = wwhat;
			msg.obj = result;
			handler.sendMessage(msg);
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
