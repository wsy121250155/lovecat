package com.mobile.zsdx.location;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

public class BaseLocationManager {
	private Context mContext;
	private LocationManagerProxy mLocationManagerProxy;
	
	public BaseLocationManager(Context mContext) {
		this.mContext = mContext;
		init();
	}
	
	private void init() {
		mLocationManagerProxy = LocationManagerProxy.getInstance(this.mContext);        
	}
	
	public void removeUpdates(LocationListener listener) {
		mLocationManagerProxy.removeUpdates(listener);
	}
	
	public void requestLocationData(LocationListener listener) {
		//此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法     
        //其中如果间隔时间为-1，则定位只定一次
		mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, listener);
		mLocationManagerProxy.setGpsEnable(true);
	}
	
	public static class LocationListener implements AMapLocationListener {

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLocationChanged(AMapLocation amapLocation) {
			// TODO Auto-generated method stub
		}	
	}
}
