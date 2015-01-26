/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.PortalLoginVerifyResult;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.google.gson.JsonSyntaxException;

/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date 2013-11-11
 */

public class PortalLoginVerifyRequest extends PortalRequest {

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
			param.put("username", username);
			param.put("password", password);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String username;
	private String password;

	public PortalLoginVerifyRequest(Handler handler, int wwhat,
			String username, String password, String actionCode) {
		super(handler, wwhat);

		this.actionCode = actionCode;
		this.username = username;
		this.password = password;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		PortalLoginVerifyResult result = null;
		try {
			result = gson.fromJson(reusltStrObj.result,
					PortalLoginVerifyResult.class);
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
