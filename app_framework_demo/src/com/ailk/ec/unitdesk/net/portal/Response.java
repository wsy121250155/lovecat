/**
 * 
 */
package com.ailk.ec.unitdesk.net.portal;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 *  TODO类主功能描述
 *<P>
 *  TODO(类详细主功能描述)
 *<P>
 */
public class Response {


	private String code;
	private String message;
	private String forward;
	private String resultStr;
	private String resultList;
	
	
	public Response(String result) {
		super();
		initResponse(result);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getResultStr() {
		return resultStr;
	}
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	
	
	public String getResultList()
	{
		return resultList;
	}
	public void setResultList(String resultList)
	{
		this.resultList = resultList;
	}
	public void initResponse(String result){
		try {
			JSONObject json = new JSONObject(result);
			
			this.code = json.getString("code");
			this.message = json.getString("message");
			this.resultStr = json.getString("resultParam");
			this.resultList = json.getString("resultList");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
