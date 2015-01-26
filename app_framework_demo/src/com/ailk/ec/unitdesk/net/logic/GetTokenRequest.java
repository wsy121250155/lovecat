package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.Token;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.EncodeUtil;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.StringUtils;

public class GetTokenRequest extends PortalRequest {

	public final String TAG = "GetTokenRequest";

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
			param.put("uATicket", uATicket);
			if (!StringUtils.isEmpty(areaCode)) {
				param.put("areaCode", areaCode);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String uATicket;
	private String areaCode;
	private String actionCode;

	public GetTokenRequest(Handler handler, int wwhat, String uATicket,
			String areaCode, String actionCode) {
		super(handler, wwhat);
		this.uATicket = uATicket;
		this.areaCode = areaCode;
		this.actionCode = actionCode;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			Log.d(TAG, " reusltStrObj.result: " + reusltStrObj.result);
			Log.d(TAG, EncodeUtil.decodeURL(reusltStrObj.result));
			Token token = gson.fromJson(
					EncodeUtil.decodeURL(reusltStrObj.result), Token.class);
			Message msg = Message.obtain();
			msg.what = wwhat;
			msg.obj = token;
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
