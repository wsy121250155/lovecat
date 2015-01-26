package com.mobile.zsdx.index;

import java.io.IOException;

import org.jivesoftware.smack.XMPPException;

import com.mobile.base.MainActivity;
import com.mobile.base.R;
import com.mobile.base.im.XmppConnection;
import com.mobile.base.util.ConstantResource;
import com.mobile.base.util.MLog;
import com.mobile.zsdx.rss.RSSActivity;

import com.mobile.zsdx.treehole.TreeHoleActivity;

import com.mobile.zsdx.schedule.ScheduleFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author liang
 *
 */
public class IndexActivity extends Activity {

	private Button button;
	private Button schButton;
	private Button treeHole;
	private Button locaitonBtn;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);

		button = (Button) findViewById(R.id.rss);
		button.setOnClickListener(getListener("rss"));

		schButton = (Button) findViewById(R.id.schedule);
		schButton.setOnClickListener(getListener("schedule"));

		treeHole = (Button) findViewById(R.id.treehole);
		treeHole.setOnClickListener(getListener("treehole"));

		locaitonBtn = (Button) findViewById(R.id.userlocation);
		locaitonBtn.setOnClickListener(getListener("userlocation"));

		// 初始化deviceid
		ConstantResource.tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	}

	// 界面跳转放入监听中统一处理
	public OnClickListener getListener(final String string) {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (string.equals("rss")) {
					Intent i = new Intent(IndexActivity.this, RSSActivity.class);
					IndexActivity.this.startActivity(i);
				}
				if (string.equals("schedule")) {

					Intent i = new Intent(IndexActivity.this,
							MainActivity.class);
					i.putExtra("Active_Fragment", "schedule");
					IndexActivity.this.startActivity(i);
				}
				if (string.equals("treehole")) {
					Intent i = new Intent(IndexActivity.this,
							TreeHoleActivity.class);
					IndexActivity.this.startActivity(i);
				}
				if (string.equals("userlocation")) {
					Intent i = new Intent(IndexActivity.this,
							MainActivity.class);
					i.putExtra("Active_Fragment", "userlocation");
					IndexActivity.this.startActivity(i);
				}
			}
		};
		return listener;
	}
}
