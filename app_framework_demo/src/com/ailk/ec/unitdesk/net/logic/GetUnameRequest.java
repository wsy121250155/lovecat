package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.Uname;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.Des3;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;

public class GetUnameRequest extends PortalRequest {

	public final String TAG = "GetUnameRequest";

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
		JSONObject temp = new JSONObject();
		try {
			temp.put("personCode", personCode);
			temp.put("personName", personName);
			temp.put("areaCode", areaCode);
			try {
				param.put("param", Des3.encode(temp.toString(),
						"yljchichengpangzi@asiainfo"));
			} catch (Exception e) {
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String personCode;
	private String personName;
	private String areaCode;
	private String actionCode;

	public GetUnameRequest(Handler handler, int wwhat, String personCode,
			String personName, String areaCode, String actionCode) {
		super(handler, wwhat);
		this.personCode = personCode;
		this.personName = personName;
		this.areaCode = areaCode;
		this.actionCode = actionCode;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			Log.d(TAG, " reusltStrObj.result: " + reusltStrObj.result);
			Uname uname = gson.fromJson(reusltStrObj.result, Uname.class);
			Message msg = Message.obtain();
			msg.what = wwhat;
			msg.obj = uname;
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
