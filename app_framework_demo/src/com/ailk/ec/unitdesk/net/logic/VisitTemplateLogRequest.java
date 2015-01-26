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
import com.ailk.ec.unitdesk.utils.Log;

/**
 * @author spoon
 * @Description:
 * @version V1.0
 */

public class VisitTemplateLogRequest extends PortalRequest {

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
			param.put("userId", userId);
			param.put("areaId", areaId);
			param.put("tempInstId", tempInstId);
			param.put("modelId", modelId);
			param.put("tempName", tempName);
			param.put("serviceCode", serviceCode);
			param.put("serviceParam", serviceParam);
			param.put("instTypeId", instTypeId);

			param.put("deviceType", deviceType);
			param.put("systemVersion", systemVersion);
			param.put("appVersionCode", appVersionCode);
			param.put("appVersionName", appVersionName);
			param.put("model", model);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 区域id
	 */
	private String areaId;
	/**
	 * 磁贴id
	 */
	private String tempInstId;
	/**
	 * 模版id
	 */
	private String modelId;
	/**
	 * 磁贴名称
	 */
	private String tempName;
	/**
	 * 服务码
	 */
	private String serviceCode;
	/**
	 * 参数
	 */
	private String serviceParam;
	/**
	 * 实例类型id
	 */
	private String instTypeId;
	/**
	 * android.
	 */
	private String deviceType;
	private String systemVersion;
	private String appVersionCode;
	private String appVersionName;
	private String model;

	public VisitTemplateLogRequest(Handler handler, int wwhat, String userId,
			String areaId, String tempInstId, String modelId, String tempName,
			String serviceCode, String serviceParam, String instTypeId,
			String deviceType, String systemVersion, String appVersionCode,
			String appVersionName, String model, String actionCode) {
		super(handler, wwhat);
		this.userId = userId;
		this.areaId = areaId;
		this.actionCode = actionCode;
		this.tempInstId = tempInstId;
		this.modelId = modelId;
		this.tempName = tempName;
		this.serviceCode = serviceCode;
		this.serviceParam = serviceParam;
		this.instTypeId = instTypeId;
		this.deviceType = deviceType;
		this.systemVersion = systemVersion;
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
		this.model = model;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		if (reusltStrObj != null && "1".equals(reusltStrObj.resultCode)) {
			Log.d("VisitTemplateLogRequest", "访问磁铁日志保存成功");
		} else {
			Log.e("VisitTemplateLogRequest", "访问磁铁日志保存失败");
		}
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
