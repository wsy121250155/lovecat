package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.SimpleResult;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * 应用磁贴
 * 
 * @author spoon
 * 
 */
public class ApplyPushRequest extends PortalRequest {

	public final String TAG = "ApplyPushRequest";

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
			param.put("ArrGroup", ArrGroup);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		Log.d(TAG, "入参："+request.toString());
		return request.toString();
	}

	private String actionCode;
	private String userId;
	private String deviceType;
	private String areaCode;
	private String ArrGroup;

	public ApplyPushRequest(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String ArrGroup,
			String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.userId = userId;
		this.areaCode = areaCode;
		this.deviceType = deviceType;
		this.ArrGroup = ArrGroup;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			Log.d(TAG, " resultStr: " + reusltStrObj.result);
			SimpleResult result = gson.fromJson(reusltStrObj.result,
					SimpleResult.class);
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
