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

public class ErrorLogRequest extends PortalRequest {

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
			param.put("brand", brand);
			param.put("systemVersion", systemVersion);
			param.put("deviceId", deviceId);
			param.put("product", product);
			param.put("stackTrace", stackTrace);
			param.put("packageName", packageName);
			param.put("crashDate", crashDate);
			param.put("userName", userName);
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
	private String brand;
	private String systemVersion;
	private String deviceId;
	private String product;
	private String stackTrace;
	private String packageName;
	private String crashDate;
	private String userName;
	private String appVersionCode;
	private String appVersionName;
	private String model;

	public ErrorLogRequest(Handler handler, int wwhat, String brand,
			String systemVersion, String deviceId, String product,
			String stackTrace, String packageName, String crashDate,
			String userName, String appVersionCode, String appVersionName,
			String model, String actionCode) {
		super(handler, wwhat);
		this.brand = brand;
		this.systemVersion = systemVersion;
		this.actionCode = actionCode;
		this.deviceId = deviceId;
		this.product = product;
		this.stackTrace = stackTrace;
		this.packageName = packageName;
		this.crashDate = crashDate;
		this.userName = userName;
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
		this.model = model;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		if (reusltStrObj != null && "1".equals(reusltStrObj.resultCode)) {
			Log.d("ErrorLogRequest", "错误日志保存成功");
		} else {
			Log.e("ErrorLogRequest", "错误日志保存失败");
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
