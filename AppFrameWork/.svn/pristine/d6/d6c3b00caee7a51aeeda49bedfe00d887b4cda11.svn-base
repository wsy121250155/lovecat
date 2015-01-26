package com.mobile.zsdx.rss;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.mobile.api.proto.MAppNews.MRss;
import com.mobile.api.proto.MAppNews.MRssList;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;

public class RSSSelectActivity extends ActionBarActivity{
	private List<MRss> mlist;
	private PullToRefreshListView listView;
	private RSSSelectAdapter adapter;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rssselected);
		listView=(PullToRefreshListView) findViewById(R.id.rssselectedlist);
		adapter=new RSSSelectAdapter(mlist, this);
		listView.setAdapter(adapter);
		ApiClientFactory.getMyRss(this, RSSSelectActivity.this, "getMyRss", 1, 20);
		setActionBar();
	}
	public void getMyRss(Bean2Json list){
		mlist.addAll(((MRssList)list).getList());
		adapter.notifyDataSetChanged();
	}
	
	private void setActionBar(){
		ActionBar actionBar=getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setTitle("全部订阅");
	}
}
