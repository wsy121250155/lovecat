package com.frame.activity;

import com.frame.fragment.SquareDetailFragment;
import com.frame.fragment.SquareFragment;
import com.frame.fragment.SquareFragment.PubOrDetail;
import com.frame.fragment.SquareInformFragment;
import com.frame.fragment.SquareInformFragment.InformDetail;
import com.frame.fragment.SquarePublishFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class SquareActivity extends Activity implements PubOrDetail,
		InformDetail {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_square);
		showSquare();
	}

	// 从广场第一页向其他页面跳转
	@Override
	public void publish() {
		// TODO Auto-generated method stub
		SquarePublishFragment fragment = new SquarePublishFragment();
		jumpTo(fragment);
	}

	@Override
	public void detail(int index) {
		// TODO Auto-generated method stub
		SquareDetailFragment fragment = new SquareDetailFragment();
		jumpTo(fragment);
	}

	@Override
	public void inform() {
		// TODO Auto-generated method stub
		SquareInformFragment fragment = new SquareInformFragment();
		jumpTo(fragment);
	}

	private void jumpTo(Fragment fragment) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	// 从广场消息提醒界面向其他界面跳转
	@Override
	public void checkInformDetail(int index) {
		// TODO Auto-generated method stub
		detail(index);
	}

	// ***************init***************

	private void showSquare() {
		SquareFragment fragment = new SquareFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment).commit();
	}

}
