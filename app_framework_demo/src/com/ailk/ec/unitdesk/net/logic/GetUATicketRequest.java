package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.LoginResult;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.net.portal.HttpBaseRequest;
import com.ailk.ec.unitdesk.utils.Des3;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.StringUtils;

public class GetUATicketRequest extends HttpBaseRequest {

	public final String TAG = "GetUATicketRequest";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected RequestParams appendMainBody() {
		// TODO Auto-generated method stub
		RequestParams param = new RequestParams();

		JSONObject jsonParam = new JSONObject();
		try {
			jsonParam.put("staffId", staffId);
			jsonParam.put("systemId", systemId);
			if (!StringUtils.isEmpty(validatecode)) {
				jsonParam.put("validatecode", validatecode);
			}
			jsonParam.put("staffPwd",
					Des3.encode(staffPwd, "yljchichengpangzi@asiainfo"));
			if (!StringUtils.isEmpty(areaCode)) {
				jsonParam.put("areaCode", areaCode);
			}
		} catch (Exception e) {
		}
		Log.d(TAG, jsonParam.toString());
		param.put("params", jsonParam.toString());
		return param;
	}

	private String staffId;
	private String systemId;
	private String validatecode;
	private String staffPwd;
	private String areaCode;

	public GetUATicketRequest(Handler handler, int wwhat, String staffId,
			String systemId, String validatecode, String staffPwd,
			String areaCode, String url) {
		super(handler, wwhat);
		this.staffId = staffId;
		this.systemId = systemId;
		this.validatecode = validatecode;
		this.url = url;
		this.staffPwd = staffPwd;
		this.areaCode = areaCode;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		String resultStr = new String(responseBody);
		Log.d(TAG, " resultStr: " + resultStr);
		LoginResult result = gson.fromJson(resultStr, LoginResult.class);
		Message msg = Message.obtain();
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
