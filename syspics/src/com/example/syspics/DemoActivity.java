package com.example.syspics;

import com.example.fragment.TakeFragment;
import com.example.fragment.ViewFragment;
import com.example.fragment.TakeFragment.Jump2View;
import com.example.tools.BitmapRelease;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

@SuppressLint("Recycle")
public class DemoActivity extends ActionBarActivity implements Jump2View {
	private Bitmap viewBitmap;

	public Bitmap getBitmap() {
		return viewBitmap;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		initFragment();
	}

	// 用于跳转的回调
	@Override
	public void getPic(Bitmap bitmap) {
		// TODO Auto-generated method stub
		BitmapRelease.recycleBitmap(viewBitmap);
		viewBitmap = bitmap;
		jump2View();
	}

	private void jump2View() {
		ViewFragment viewFragment = new ViewFragment();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.FrameLayout1, viewFragment).commit();
	}

	private void initFragment() {
		TakeFragment fragment = new TakeFragment();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment).commit();
	}
}
