/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.BindingAcctResult;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Spoon
 * @date 2014-4-14
 * @version 1.0
 */

public class BindingAcctRequest extends PortalRequest {

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
			param.put("token", token);
			param.put("SystemId", SystemId);
			param.put("usr", usr);
			param.put("pwd", pwd);
			param.put("AreaId", AreaId);
			param.put("type", type);
			param.put("device_unique_code", device_unique_code);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String token;
	private String SystemId;
	private String usr;
	private String pwd;
	private String AreaId;
	private String type;
	private String device_unique_code;

	public BindingAcctRequest(Handler handler, int wwhat, String token,
			String SystemId, String usr, String pwd, String AreaId,
			String type, String device_unique_code, String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.token = token;
		this.SystemId = SystemId;
		this.usr = usr;
		this.pwd = pwd;
		this.AreaId = AreaId;
		this.type = type;
		this.device_unique_code = device_unique_code;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);

		List<BindingAcctResult> result = null;
		if (reusltStrObj != null && reusltStrObj.result != null) {
			Type listType = new TypeToken<List<BindingAcctResult>>() {
			}.getType();
			result = gson.fromJson(reusltStrObj.result, listType);
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
