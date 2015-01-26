package com.ailk.ec.unitdesk.net.logic;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.models.http.SimpleResult;
import com.ailk.ec.unitdesk.net.portal.PortalRequest;
import com.ailk.ec.unitdesk.utils.JSONRequest;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.StringUtils;

/**
 * 配置信息(更新,删除)
 * 
 * @author spoon
 * 
 */
public class UpdateOrDelCFGRequest extends PortalRequest {

	public final String TAG = "UpdateOrDelCFGRequest";

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
			param.put("areaCode", areaCode);
			param.put("deviceType", deviceType);
			if (!StringUtils.isEmpty(ArrCategoryForDelete)) {
				param.put("ArrCategoryForDelete", ArrCategoryForDelete);
			}
			if (!StringUtils.isEmpty(ArrCategoryForUpdate)) {
				param.put("ArrCategoryForUpdate", ArrCategoryForUpdate);
			}
			if (!StringUtils.isEmpty(ArrGroupForDelete)) {
				param.put("ArrGroupForDelete", ArrGroupForDelete);
			}
			if (!StringUtils.isEmpty(ArrGroupForUpdate)) {
				param.put("ArrGroupForUpdate", ArrGroupForUpdate);
			}
			if (!StringUtils.isEmpty(ArrInstForDelete)) {
				param.put("ArrInstForDelete", ArrInstForDelete);
			}
			if (!StringUtils.isEmpty(ArrInstForUpdate)) {
				param.put("ArrInstForUpdate", ArrInstForUpdate);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setParam(param);
		return request.toString();
	}

	private String actionCode;
	private String userId;
	private String deviceType;
	private String areaCode;
	/**
	 * 删除分类
	 */
	private String ArrCategoryForDelete;

	/**
	 * 更新分类信息(位置)
	 */
	private String ArrCategoryForUpdate;
	/**
	 * 删除分组
	 */
	private String ArrGroupForDelete;

	/**
	 * 更新分组信息(位置,父分类)
	 */
	private String ArrGroupForUpdate;

	/**
	 * 删除磁贴
	 */
	private String ArrInstForDelete;

	/**
	 * 更新磁贴信息(位置,父分组，父分类)
	 */
	private String ArrInstForUpdate;

	public UpdateOrDelCFGRequest(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String ArrCategoryForDelete,
			String ArrCategoryForUpdate, String ArrGroupForDelete,
			String ArrGroupForUpdate, String ArrInstForDelete,
			String ArrInstForUpdate, String actionCode) {
		super(handler, wwhat);
		this.actionCode = actionCode;

		this.userId = userId;
		this.areaCode = areaCode;
		this.deviceType = deviceType;
		this.ArrCategoryForDelete = ArrCategoryForDelete;
		this.ArrCategoryForUpdate = ArrCategoryForUpdate;
		this.ArrGroupForDelete = ArrGroupForDelete;
		this.ArrGroupForUpdate = ArrGroupForUpdate;
		this.ArrInstForDelete = ArrInstForDelete;
		this.ArrInstForUpdate = ArrInstForUpdate;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		super.onSuccess(statusCode, headers, responseBody);
		try {
			Log.d(TAG, " resultStr: " + reusltStrObj.result);
			SimpleResult result = gson.fromJson(reusltStrObj.result,
					SimpleResult.class);
			Message msg = Message.obtain();
			msg.what = wwhat;
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
