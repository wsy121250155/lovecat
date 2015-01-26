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

import com.ailk.ec.unitdesk.models.http.QueryTaskListResult;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

/**
 * 
 * @author Spoon
 * @date 2014-5-6
 * @version 1.0
 */

public class QueryTaskListRequest extends PortalRequest {

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
			param.put("statusStr", statusStr);
			param.put("taskTypeIdStr", taskTypeIdStr);
			param.put("newRecordCount", newRecordCount);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String Uname;
	private String statusStr;
	private String taskTypeIdStr;
	private String newRecordCount;

	public QueryTaskListRequest(Handler handler, int wwhat, String Uname,
			String statusStr, String taskTypeIdStr, String newRecordCount,
			long instId, String actionCode) {
		super(handler, wwhat, instId);
		this.actionCode = actionCode;
		this.Uname = Uname;
		this.statusStr = statusStr;
		this.taskTypeIdStr = taskTypeIdStr;
		this.newRecordCount = newRecordCount;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);

		QueryTaskListResult result = null;
		try {
			result = gson.fromJson(reusltStrObj.result,
					QueryTaskListResult.class);
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
