/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;

import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

/**
 * 
 * @author Spoon
 * @date 2014-4-15
 * @version 1.0
 */

public class SaveUserKeyRequest extends PortalRequest {

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
			param.put("userName", userName);
			param.put("pwd", pwd);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String userName;
	private String pwd;

	public SaveUserKeyRequest(Handler handler, int wwhat, String userName,
			String pwd, String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;
		this.userName = userName;
		this.pwd = pwd;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		// if (reusltStrObj != null && "1".equals(reusltStrObj.resultCode)) {
		// Log.d("ErrorLogRequest", "错误日志保存成功");
		// } else {
		// Log.e("ErrorLogRequest", "错误日志保存失败");
		// }
		// Message msg = handler.obtainMessage();
		// msg.what = wwhat;
		// msg.obj = result;
		// handler.sendMessage(msg);

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}

}
