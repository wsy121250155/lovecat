package com.mobile.zsdx.location;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.mobile.api.proto.MSystem.MCourse;
import com.mobile.api.proto.MSystem.MCourseList;
import com.mobile.api.proto.MSystem.MRet;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.Helper;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.Mode;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.OnPullEventListener;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.State;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;
import com.mobile.zsdx.location.BaseLocationManager.LocationListener;

public class NearUserFragment extends MFragment {
	private PullToRefreshListView listView;
	private List<MCourse> list = new ArrayList<MCourse>();
	private NearUserAdapter adapter;
	private int page = 1;
	
	private double latitude;
	private double longitude;
	private double altitude;
	
	private MCourseList users;
	
	private BaseLocationManager blm;
	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(AMapLocation amapLocation) {
			// TODO Auto-generated method stub
			if(amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0){
				//获取位置信息
				latitude = amapLocation.getLatitude();
				longitude = amapLocation.getLongitude();
				altitude = amapLocation.getAltitude();
				ApiClientFactory.getNearUsers(getContext(), NearUserFragment.this, "setList", 1000000, page, 20,
						latitude, longitude, altitude);
			}
		}
	};
	
	@Override
	protected void create(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frg_near_user);
		
		listView = (PullToRefreshListView) findViewById(R.id.nearuserlist);
		adapter = new NearUserAdapter(list, getContext());

		listView.setAdapter(adapter);
		listView.setOnPullEventListener(new OnPullEventListener<ListView>() {

			@Override
			public void onPullEvent(PullToRefreshBase<ListView> refreshView,
					State state, Mode direction) {
				if(state.equals(State.RELEASE_TO_REFRESH)){
				}
			}
		});
		
		blm = new BaseLocationManager(getContext());
	}
	
	public void onPause() {  
        super.onPause(); 
        blm.removeUpdates(locationListener); 
    }   
	
	public void onResume() { 
        super.onResume();   
        blm.requestLocationData(locationListener);
    } 

	
//	@SuppressWarnings("rawtypes")
//	public void setLocation(Bean2Json data) {
//		MRet ret = (MRet)data;
//		if(ret.getCode() == 1) {
//			Helper.toast(getContext(), "同步位置信息成功");
//			ApiClientFactory.getNearUsers(getContext(), NearUserFragment.this, "setList", 1000000, page, 20);
//		} else {
//			Helper.toast(getContext(), "同步位置信息失败");
//		}
//	}
	
	@SuppressWarnings("rawtypes")
	public void setList(Bean2Json data) {
		users = (MCourseList)data;
		if(page == 1) {
			list.clear();
			page++;
		}
		list.addAll(users.getCourse());
		Helper.toast(getContext(), list.toString() + " && " + adapter.getCount());
		adapter.notifyDataSetChanged();
	}
	
	public void setActionBar(ActionBar actionBar, Context context) {
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.actb_head_control, null);
		
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.RIGHT;
		
		actionBar.setCustomView(view, layoutParams);
		
		TextView title = (TextView)view.findViewById(R.id.tv_title);
		title.setText("用户位置信息");
		
		TextView subtitle = (TextView)view.findViewById(R.id.tv_subTitle);
		subtitle.setText("附近的人");		
		
		Button btn_back = (Button)view.findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
	}
}
