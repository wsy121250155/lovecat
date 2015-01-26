package com.ailk.ec.unitdesk.utils;

import org.json.JSONException;
import org.json.JSONObject;





public class JSONRequest extends JSONObject
{

	private String activityId;
	private String key;
	private String actionCode;
	private String operatArea;
	private String operatStaff;
	private String operatChannel;
	private String imei;

	private JSONObject param;

	public JSONRequest(String activityId, String key, String actionCode)
	{
		super();
		this.activityId = activityId;
		this.key = key;
		this.actionCode = actionCode;
		
	}

	// public String getOperator()
	// {
	// return operator;
	// }
	//
	// public void setOperator(String operator)
	// {
	// if (operator == null)
	// {
	// this.operator = "";
	// } else
	// {
	// this.operator = operator;
	// }
	// }

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		if (key == null)
		{
			this.key = "";
		} else
		{
			this.key = key;
		}
	}

	public String getActionCode()
	{
		return actionCode;
	}

	public void setActionCode(String actionCode)
	{
		this.actionCode = actionCode;
	}

	public JSONObject getParam()
	{
		return param;
	}

	public void setParam(JSONObject param)
	{
		this.param = param;
	}

	@Override
	public String toString()
	{
		JSONObject request = new JSONObject();
		try
		{
			request.put("activityId", activityId);
			request.put("key", key);
			request.put("actionCode", actionCode);
			request.put("operatArea", operatArea);
			request.put("operatStaff", operatStaff);
			request.put("operatChannel", operatChannel);
			request.put("imei", imei);
			request.put("systemType", android.os.Build.MODEL);
			request.put("systemVesion", android.os.Build.VERSION.RELEASE);
			request.put("param", param);
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return request.toString();
	}
	
	

}