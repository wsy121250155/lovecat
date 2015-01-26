package com.ailk.ec.unitdesk.web.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.models.desktop.DeviceJson;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.StringUtils;
import com.ailk.ec.unitdesk.utils.ToolUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetDeviceInfoPlugin extends CordovaPlugin {

	private static final String TAG = "GetDeviceInfoPlugin";
	public CallbackContext callbackContext;

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		if (action.equals("GetDeviceID")) {
			callbackContext.success(StringUtils.MD5Encode(getDeviceId()));
			PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
			r.setKeepCallback(true);
			callbackContext.sendPluginResult(r);
			return true;
		} else if (action.equals("GetDeviceInfo")) {
			DeviceJson json = ToolUtil.getDeviceInfo();
			Gson gson = new GsonBuilder().create();
			Log.d(TAG, gson.toJson(json));
			callbackContext.success(StringUtils.MD5Encode(gson.toJson(json)));
			PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
			r.setKeepCallback(true);
			callbackContext.sendPluginResult(r);
			return true;
		}

		return false;
	}

	/**
	 * 获取IMEI号
	 * 
	 * @return
	 */
	private String getDeviceId() {
		TelephonyManager telephonyManager = (TelephonyManager) CommonApplication
				.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

}
