package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.ResetPwd1Result;
import com.ailk.ec.unitdesk.models.http.SimpleResult;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.net.portal.HttpBaseRequest;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.StringUtils;

public class ResetPwd2Request extends HttpBaseRequest {

	public final String TAG = "ResetPwd2Request";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected RequestParams appendMainBody() {
		// TODO Auto-generated method stub
		RequestParams param = new RequestParams();

		param.put("staffId", staffId);
		param.put("staffPwd", staffPwd);
		param.put("validatecode", validatecode);
		if (!StringUtils.isEmpty(areaCode)) {
			param.put("areaCode", areaCode);
		}
		return param;
	}

	private String staffId;
	private String staffPwd;
	private String validatecode;
	private String areaCode;

	public ResetPwd2Request(Handler handler, int wwhat, String staffId,
			String staffPwd, String validatecode, String areaCode, String url) {
		super(handler, wwhat);
		this.staffId = staffId;
		this.staffPwd = staffPwd;
		this.validatecode = validatecode;
		this.areaCode = areaCode;
		this.url = url;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		String resultStr = new String(responseBody);
		Log.d(TAG, " resultStr: " + resultStr);
		SimpleResult resetResult = gson.fromJson(resultStr, SimpleResult.class);
		Message msg = Message.obtain();
		msg.what = wwhat;
		msg.obj = resetResult;
		handler.sendMessage(msg);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}
}
