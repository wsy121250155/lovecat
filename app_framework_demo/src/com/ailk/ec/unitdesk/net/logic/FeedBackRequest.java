/**
 * 
 */
package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;

import android.os.Handler;
import android.os.Message;

/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date 2013-11-11
 */

public class FeedBackRequest extends PortalRequest {

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
			param.put("feedType", feedType);
			param.put("feedContent", feedContent);
			param.put("summiter", summiter);
			param.put("summiterCon", summiterCon);
			param.put("systemVesion", systemVesion);
			param.put("appVesion", appVesion);
			param.put("statusCd", statusCd);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String feedType;
	private String feedContent;
	private String summiter;
	private String summiterCon;
	private String systemVesion;
	private String appVesion;
	private String statusCd;

	public FeedBackRequest(Handler handler, int wwhat, String feedType,
			String feedContent, String summiter, String summiterCon,
			String systemVesion, String appVesion, String statusCd,
			String actionCode) {
		super(handler, wwhat);
		this.feedType = feedType;
		this.feedContent = feedContent;
		this.actionCode = actionCode;
		this.summiter = summiter;
		this.summiterCon = summiterCon;
		this.systemVesion = systemVesion;
		this.appVesion = appVesion;
		this.statusCd = statusCd;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		Message msg = handler.obtainMessage();
		msg.what = wwhat;
		msg.obj = reusltStrObj;
		handler.sendMessage(msg);

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		super.onFailure(statusCode, headers, responseBody, error);
	}

}
