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

import com.ailk.ec.unitdesk.models.http.SmsSendResult;
import com.ailk.ec.unitdesk.models.http.param.SmsParam;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

/**
 * @author spoon
 * @Description:
 * @version V1.0
 */

public class SendSMSRequest extends PortalRequest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected String appendMainBody() {
		JSONRequest request = new JSONRequest("", "1111", actionCode);
		JSONObject param = null;
		try {
			param = new JSONObject(gson.toJson(smsParam));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private SmsParam smsParam;

	public SendSMSRequest(Handler handler, int wwhat, SmsParam smsParam,
			String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.smsParam = smsParam;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);

		SmsSendResult result = null;
		try {
			Log.d("SendSMSRequest", reusltStrObj.result);
			result = gson.fromJson(reusltStrObj.result, SmsSendResult.class);
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
