package com.frame.activity;

import com.frame.fragment.EssenseDetailFragment;
import com.frame.fragment.EssenseFragment;
import com.frame.view.util.ViewGenerator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class EssenseActivity extends Activity implements
		EssenseFragment.ShowDetail {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_essense);
		showEssense();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void switchToDetail(ViewGenerator viewGenerator) {
		// TODO Auto-generated method stub
		EssenseDetailFragment fragment = new EssenseDetailFragment(
				viewGenerator);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	// **********for fragment Switch**********

	private void showEssense() {
		EssenseFragment fragment = new EssenseFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment).commit();
	}
}
