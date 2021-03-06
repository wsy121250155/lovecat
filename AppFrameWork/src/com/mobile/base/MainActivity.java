package com.mobile.base;

import com.mobile.zsdx.location.NearUserFragment;
import com.mobile.zsdx.schedule.ScheduleAddFragment;
import com.mobile.zsdx.schedule.ScheduleFragment;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends BaseActivity {
//	//广播
//  	private BroadcastReceiver b =new BroadcastReceiver(){
//
//  		@Override
//  		public void onReceive(Context context, Intent intent) {
//  			if(intent.getAction().equals("LOAD_FRAGMENT")){
//  				intent.getStringExtra("Active_Fragment");
//  			}
//  		}
//  		
//  	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();
		String fragmentName = intent.getStringExtra("Active_Fragment");
		
		if("schedule".equals(fragmentName)) {
			this.setActiveFragment(new ScheduleFragment());
		}
		
		if("scheduleadd".equals(fragmentName)) {
			this.setActiveFragment(new ScheduleAddFragment());
		}
		
		if("userlocation".equals(fragmentName)) {
			this.setActiveFragment(new NearUserFragment());
		}
	}
}
