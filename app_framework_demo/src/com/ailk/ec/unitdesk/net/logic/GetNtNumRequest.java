/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.ailk.ec.unitdesk.models.desktop.GetNtNum;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.google.gson.JsonSyntaxException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date 2013-11-11
 */

public class GetNtNumRequest extends PortalRequest {

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
			param.put("pracct", pracct);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String pracct;

	public GetNtNumRequest(Handler handler, int wwhat, String pracct,
			long instId, String actionCode) {
		super(handler, wwhat, instId);

		this.actionCode = actionCode;
		this.pracct = pracct;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		GetNtNum result = null;
		try {
			result = gson.fromJson(reusltStrObj.result, GetNtNum.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message msg = handler.obtainMessage();
		msg.what = wwhat;
		Bundle data = new Bundle();
		data.putLong("instId", instId);
		msg.setData(data);
		msg.obj = result;
		handler.sendMessage(msg);

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}

}
