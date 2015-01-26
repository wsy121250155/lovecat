package com.mobile.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;

import com.mobile.base.util.ConstantResource;

public class BaseActivity extends ActionBarActivity {
	public boolean isLoaded = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBarInit();
		
		if(ConstantResource.tm == null) {
			ConstantResource.tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		}
	}
	
	public void setActiveFragment(MFragment activeFragment) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		
//		ChatFragment cf = new ChatFragment();
		if(activeFragment != null) {
			fragmentTransaction.add(R.id.ui_container, activeFragment);
			activeFragment.setActionBar(getSupportActionBar(), this);
			isLoaded = true;
		}
		fragmentTransaction.commit();
	}
	
	public void replaceActiveFragment(MFragment activeFragment) {		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		
//		ChatFragment cf = new ChatFragment();
		if(activeFragment != null) {
			if(isLoaded) {
				fragmentTransaction.replace(R.id.ui_container, activeFragment);
				activeFragment.setActionBar(getSupportActionBar(), this);
			} else {
				fragmentTransaction.add(R.id.ui_container, activeFragment);
				activeFragment.setActionBar(getSupportActionBar(), this);
				isLoaded = true;
			}
		}
		
		fragmentTransaction.commit();
	}

	//设置actionbar更多菜单按钮
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			return true;
		}
		
		protected void actionBarInit() {
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setDisplayShowHomeEnabled(false);
	        getSupportActionBar().setDisplayShowCustomEnabled(true); //允许自定义
	    }

}
