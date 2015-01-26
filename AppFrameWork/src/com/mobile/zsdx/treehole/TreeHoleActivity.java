package com.mobile.zsdx.treehole;

import java.io.Serializable;
import java.util.HashMap;
import com.mobile.base.R;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class TreeHoleActivity extends ActionBarActivity{

	private static FragmentManager fm;
	
	private static Fragment showingFragment = null;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treehole_main);
		
		fm = getSupportFragmentManager();
		
		init();
		actionBarInit();
	}
	
	public void init(){
		String classname = null ;
		if(showingFragment == null){
			classname = THMainFragment.class.getName();
		}else {
			classname = showingFragment.getClass().getName();
		}
		
		Class<?> clas;
		try {
			clas = Class.forName(classname);
			Object fragment = clas.newInstance();
			showFragment((Fragment)fragment, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * 切换显示的fragment，并且传递一些数据
	 */
	public static void showFragment (Fragment fragment , HashMap<String, Object> param) throws Exception{

		Log.d("MYAPP", "执行到了" + fragment.getClass().getName());
		
     	FragmentTransaction ft = fm.beginTransaction();

		if(param != null){
			Bundle bundle = new Bundle();
			for(String key : param.keySet()){
				Object value = param.get(key);
				if (value instanceof Boolean){
					bundle.putBoolean(key, (Boolean)value);
				} else if (value instanceof Integer){
					bundle.putInt(key, (Integer)value);
				} else if (value instanceof Float){
					bundle.putFloat(key, (Float)value);
				} else if (value instanceof Double){
					bundle.putDouble(key, (Double)value);
				} else if (value instanceof Long){
					bundle.putLong(key, (Long)value);
				} else if (value instanceof String){
					bundle.putString(key, (String)value);
				} else if (value instanceof Serializable){
					bundle.putSerializable(key, (Serializable)value);
				} else if (value instanceof Byte[]){
					bundle.putByteArray(key, (byte[])value);
				} else if (value instanceof String[]){
					bundle.putStringArray(key, (String[])value);
				} else if (value instanceof Parcelable){
					bundle.putParcelable(key, (Parcelable)value);
				}
			}
			fragment.setArguments(bundle);
		}
		
		ft.add(R.id.container, fragment);
		ft.addToBackStack(null);
		ft.commit();
		/*
		FragmentTransaction ft = fm.beginTransaction();
		
		if(showingFragment != null){
			ft.remove(showingFragment);
		}

		fragment.setArguments(bundle);
		
		
		ft.add(R.id.container, fragment);
		showingFragment = fragment;
		
		ft.commit();
		*/
	}
	
	protected void actionBarInit(){
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
	}
}
