/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.ailk.ec.unitdesk.models.desktop.WeAgentTaskNum;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

import android.os.Bundle;
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

public class QueryWeAgentTaskNumRq extends PortalRequest
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
			param.put("userName", userName);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}
	

	private String actionCode;
	private String userName;
	

	public QueryWeAgentTaskNumRq(Handler handler, int wwhat, String userName,long instId,
			String actionCode)
	{
		super(handler, wwhat,instId);
		this.userName = userName;
		this.actionCode=actionCode;
		
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
	{
		super.onSuccess( statusCode, headers,  responseBody);
		WeAgentTaskNum li =null;
		try
		{
			 li = gson.fromJson(reusltStrObj.result, WeAgentTaskNum.class);
			 
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Message msg = handler.obtainMessage();
		msg.what = wwhat;
		Bundle data = new Bundle();
		data.putLong("instId", instId);
		msg.setData(data);
		msg.obj=li;
		handler.sendMessage(msg);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error)
	{
		super.onFailure(statusCode,headers,responseBody,error);
	}


	

}
