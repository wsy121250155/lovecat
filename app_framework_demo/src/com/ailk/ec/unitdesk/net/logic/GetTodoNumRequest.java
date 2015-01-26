package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.models.http.QueryTaskListResult;
import com.ailk.ec.unitdesk.net.asyncClient.RequestParams;
import com.ailk.ec.unitdesk.net.portal.HttpBaseRequest;
import com.ailk.ec.unitdesk.utils.Log;

public class GetTodoNumRequest extends HttpBaseRequest {

	public final String TAG = "GetTodoNumRequest";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected RequestParams appendMainBody() {
		// TODO Auto-generated method stub
		RequestParams param = new RequestParams();

		param.put("Pname1", Pname1);
//		param.put("pName1", CommonApplication.getInstance().Pname1);
//		param.put("statusStr", "0,1,3");
		param.put("statusStr", "0,1");
		param.put("newRecordCount", "1");
		return param;
	}

	private String Pname1;
	private String statusStr;
	private String newRecordCount;

	public GetTodoNumRequest(Handler handler, int wwhat, String Pname1,
			long instId, String url) {
		super(handler, wwhat, instId);
		this.Pname1 = Pname1;
		this.url = url;

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			String resultStr = new String(responseBody);
			Log.d(TAG, " resultStr: " + resultStr);
			QueryTaskListResult result = gson.fromJson(resultStr,
					QueryTaskListResult.class);
			Message msg = Message.obtain();
			msg.what = wwhat;
			Bundle data = new Bundle();
			data.putLong("instId", instId);
			msg.setData(data);
			msg.obj = result;
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
