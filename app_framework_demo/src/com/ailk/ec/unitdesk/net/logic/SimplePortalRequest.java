/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.ailk.ec.unitdesk.models.http.ReturnValue;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.StringUtils;

/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date 2013-11-11
 */

public class SimplePortalRequest extends PortalRequest {

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
			if (!StringUtils.isEmpty(serviceParam)) {
				param.put("serviceParam", serviceParam);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String Uname;
	private String serviceParam;

	public SimplePortalRequest(Handler handler, int wwhat, String Uname,
			String serviceParam, long instId, String actionCode) {
		super(handler, wwhat, instId);

		this.actionCode = actionCode;
		this.Uname = Uname;
		this.serviceParam = serviceParam;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);

		if (reusltStrObj == null || reusltStrObj.result == null) {
			return;
		}
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
