package com.ailk.ec.unitdesk.web.plugins;

import java.util.Map;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.models.http.param.CoordinateInfo;
import com.ailk.ec.unitdesk.models.http.param.CoordinateJson;
import com.ailk.ec.unitdesk.utils.Log;
import com.ailk.ec.unitdesk.utils.coord.Gcj02ToBD;
import com.ailk.ec.unitdesk.utils.coord.WgsToGcj02;
import com.ailk.ec.unitdesk.web.plugins.BaiduLocationManager.LocationCallBack;
import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetLocationPlugin extends CordovaPlugin {

	private static final String TAG = "GetLocationPlugin";
	public CallbackContext callbackContext;
	BaiduLocationManager localManager;

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;

		if (action.equals("GetLocation")) {
			localManager = new BaiduLocationManager(
					CommonApplication.getInstance(), new LocationCallBack() {
						@Override
						public void onLocationChange(BDLocation location) {
							if (location == null) {
								Log.e(TAG, "无法获取坐标");
								return;
							}
							Log.d(TAG,
									location.getLongitude() + ","
											+ location.getLatitude());
							if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
								Log.d(TAG, location.getAddrStr());
							}
							final Gson gson = new GsonBuilder().create();
							final CoordinateJson coorJson = new CoordinateJson();
							coorJson.HX = new CoordinateInfo(String
									.valueOf(location.getLongitude()), String
									.valueOf(location.getLatitude()), location
									.getAddrStr());
							Map<String, Double> wgsCoord = WgsToGcj02.gcj2wgs(
									location.getLongitude(),
									location.getLatitude());
							coorJson.DQ = new CoordinateInfo(String
									.valueOf(wgsCoord.get("lon")), String
									.valueOf(wgsCoord.get("lat")), location
									.getAddrStr());
							final double lon = location.getLongitude();
							final double lat = location.getLatitude();
							final String name = location.getAddrStr();
							new Thread() {
								public void run() {
									Map<String, String> bdCoord = Gcj02ToBD
											.changgeXY(String.valueOf(lon),
													String.valueOf(lat));
									coorJson.BD = new CoordinateInfo(bdCoord
											.get("lon"), bdCoord.get("lat"),
											name);
									GetLocationPlugin.this.callbackContext
											.success(gson.toJson(coorJson));

								};
							}.start();
						}
					});
			localManager.openLocationTask();

			PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
			r.setKeepCallback(true);
			callbackContext.sendPluginResult(r);
			return true;
		}

		return false;
	}

}
