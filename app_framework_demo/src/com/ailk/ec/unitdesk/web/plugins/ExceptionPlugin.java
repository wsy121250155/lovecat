package com.ailk.ec.unitdesk.web.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;


public class ExceptionPlugin extends CordovaPlugin {

	private static final String TAG = "ExceptionPlugin";
	public CallbackContext callbackContext;

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;

		if (action.equals("TokenInvalided")) {

			callbackContext.success();
			return true;
		}
		return false;
	}
}
