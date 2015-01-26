package com.mobile.zsdx.rss;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.api.proto.MAppNews.MNews;
import com.mobile.api.proto.MAppNews.MNewsList;
import com.mobile.api.proto.MAppNews.MRssList;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;

public class RSSDetailActivity extends ActionBarActivity{

	private String title="";
	private String id;
	private List<MNews> mlist;
	private PullToRefreshListView mlisListView;
	private RSSDetailAdapter adapter;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rssdetail);
		mlisListView=(PullToRefreshListView) findViewById(R.id.rssdetaillist);
		
		mlist=new ArrayList<MNews>();
		
		adapter=new RSSDetailAdapter(mlist, this);
		
		mlisListView.setAdapter(adapter);
		setActionBar();
		
		id=getIntent().getStringExtra("rssid");

		ApiClientFactory.getMRssNews(this, RSSDetailActivity.this, "getRssNews", id, 1, 20);
	}
	@SuppressWarnings("rawtypes")
	public void getRssNews(Bean2Json myRssNews){
		mlist.addAll((((MNewsList)myRssNews).getNews()));
		adapter.notifyDataSetChanged();
	}
	
	public void setActionBar(){
		ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.rssheader, null);
		
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.RIGHT;
		
		actionBar.setCustomView(view, layoutParams);
		
		TextView title = (TextView)view.findViewById(R.id.tv_title);
		title.setText("");
		
		Button btn_back = (Button)view.findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RSSDetailActivity.this.finish();
			}
		});
		
		Button add=(Button) view.findViewById(R.id.btn_control);
		add.setVisibility(View.INVISIBLE);

	}
}
