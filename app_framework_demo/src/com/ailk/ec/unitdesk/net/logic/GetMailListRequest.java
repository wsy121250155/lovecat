/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.ailk.ec.unitdesk.models.desktop.GetNtNum;
import com.ailk.ec.unitdesk.models.desktop.Mails;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.google.gson.JsonSyntaxException;


import android.os.Handler;
import android.os.Message;



/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date  2013-11-11
 */

public class GetMailListRequest extends PortalRequest
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
			param.put("sid", sid);
			param.put("num", num);

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}
	
	private String actionCode;
	private String sid;
	private String num;

	public GetMailListRequest(Handler handler, int wwhat, String sid,String pageSize,
			String actionCode)
	{
		super(handler, wwhat);
	
		this.actionCode=actionCode;
		this.sid=sid;
		this.num=pageSize;

		
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
	{
		super.onSuccess( statusCode, headers,  responseBody);
		Mails result=null;
		try {
			result = gson.fromJson(reusltStrObj.result, Mails.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message msg = handler.obtainMessage();
		msg.what = wwhat;
		msg.obj = result;
		handler.sendMessage(msg);

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error)
	{
		super.onFailure(statusCode,headers,responseBody,error);
	}


	

}
