/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.ailk.ec.unitdesk.models.http.ReturnValue;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * @Description:
 * @version V1.0
 */

public class GetMsgNumRequest extends PortalRequest {

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
			param.put("Uname", Uname);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String Uname;

	public GetMsgNumRequest(Handler handler, int wwhat, String Uname,
			long instId, String actionCode) {
		super(handler, wwhat, instId);

		this.actionCode = actionCode;
		this.Uname = Uname;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		ReturnValue result = null;
		try {
			result = gson.fromJson(reusltStrObj.result, ReturnValue.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Message msg = handler.obtainMessage();
		msg.what = wwhat;
		Bundle data = new Bundle();
		data.putLong("instId", instId);
		msg.obj = result;
		msg.setData(data);
		handler.sendMessage(msg);

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}

}
