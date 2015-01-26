/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;

import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * @author spoon
 * @Description:
 * @version V1.0
 */

public class UserLoginLogRequest extends PortalRequest {

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
			param.put("areaId", areaId);
			param.put("deviceType", deviceType);
			param.put("systemVersion", systemVersion);
			param.put("appVersionCode", appVersionCode);
			param.put("appVersionName", appVersionName);
			param.put("model", model);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String userId;
	private String areaId;
	private String deviceType;
	private String systemVersion;
	private String appVersionCode;
	private String appVersionName;
	private String model;

	public UserLoginLogRequest(Handler handler, int wwhat, String userId,
			String areaId, String deviceType, String systemVersion,
			String appVersionCode, String appVersionName, String model,
			String actionCode) {
		super(handler, wwhat);
		this.userId = userId;
		this.areaId = areaId;
		this.actionCode = actionCode;
		this.deviceType = deviceType;
		this.systemVersion = systemVersion;
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
		this.model = model;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		if (reusltStrObj != null && "1".equals(reusltStrObj.resultCode)) {
			Log.d("UserLoginLogRequest", "登录日志保存成功");
		} else {
			Log.e("UserLoginLogRequest", "登录日志保存失败");
		}
		// Message msg = handler.obtainMessage();
		// msg.what = wwhat;
		// msg.obj = result;
		// handler.sendMessage(msg);

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}

}
