/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ailk.ec.unitdesk.models.http.UpgradeInfo;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

/**
 * @author spoon
 * @Description:
 * @version V1.0
 */

public class UpgradeRequest extends PortalRequest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected String appendMainBody() {
		JSONRequest request = new JSONRequest("", "1111", actionCode);
		JSONObject param = new JSONObject();
		try {
			param.put("userid", userid);
			param.put("packageCd", packageCd);
			param.put("softVersion", softVersion);
			param.put("deviceCode", deviceCode);
			param.put("deviceToken", deviceToken);
			param.put("sysVersion", sysVersion);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String userid;
	private String packageCd;
	private String softVersion;
	/**
	 * deviceCode: 设备类型 1表示android ,2 表示ios ,3 表示ipad
	 */
	private String deviceCode;
	private String deviceToken;
	private String sysVersion;

	public UpgradeRequest(Handler handler, int wwhat, String userid,
			String packageCd, String softVersion, String deviceCode,
			String deviceToken, String sysVersion, String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.userid = userid;
		this.packageCd = packageCd;
		this.softVersion = softVersion;
		this.deviceCode = deviceCode;
		this.deviceToken = deviceToken;
		this.sysVersion = sysVersion;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		UpgradeInfo result = null;
		try {
			if (reusltStrObj != null && reusltStrObj.result != null) {
				Log.d("UpgradeRequest", reusltStrObj.result);
				result = gson.fromJson(reusltStrObj.result, UpgradeInfo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Message msg = handler.obtainMessage();
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
