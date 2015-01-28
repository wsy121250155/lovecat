package com.example.syspics;

import com.example.global.ViewPicSize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initGloable();
		findViews();
		setListener();
	}
	
	private void setListener(){
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this, DemoActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void findViews(){
		button1=(Button)findViewById(R.id.button1);
	}
	
	private void initGloable(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int intScreenWidth = dm.widthPixels;
		int intScreenHeight = dm.heightPixels;
		ViewPicSize.getInstance().init(intScreenWidth, intScreenHeight);
	}
}
