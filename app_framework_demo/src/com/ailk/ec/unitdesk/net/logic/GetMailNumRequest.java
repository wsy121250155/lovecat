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

import com.ailk.ec.unitdesk.models.desktop.Mails;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;


/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date  2013-11-11
 */

public class GetMailNumRequest extends PortalRequest
{

	/* (non-Javadoc)
	 * @see com.al.android.somsdesk.net.test.PortalRequest#appendMainBody()
	 */
	@Override
	protected String appendMainBody()
	{
		// TODO Auto-generated method stub
		JSONRequest request = new JSONRequest("", "1111", actionCode);
		JSONObject param = new JSONObject();
		try
		{
			param.put("sid",sid);

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}
	
	private String actionCode;
	private String sid;

	public GetMailNumRequest(Handler handler, int wwhat, String sid,long instId,
			String actionCode)
	{
		super(handler, wwhat,instId);
	
		this.actionCode=actionCode;
		this.sid=sid;

		
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
	{
		super.onSuccess( statusCode, headers,  responseBody);
		Mails result=null;
		try {
			Log.d("GetMailNumRequest", reusltStrObj.result);
			result = gson.fromJson(reusltStrObj.result, Mails.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error)
	{
		super.onFailure(statusCode,headers,responseBody,error);
	}


	

}
