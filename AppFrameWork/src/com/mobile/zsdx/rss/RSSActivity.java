package com.mobile.zsdx.rss;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.api.proto.MAppNews.MRss;
import com.mobile.api.proto.MAppNews.MRssList;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.Mode;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.OnPullEventListener;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.State;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;
/**
 * 
 * @author liang
 *
 */
public class RSSActivity extends ActionBarActivity{
	
	private PullToRefreshListView listView;
	private List<MRss> list ;
	private RSSAdapter adapter;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rssmainlist);
		listView=(PullToRefreshListView) findViewById(R.id.rssmainlist);
		list=new ArrayList<MRss>();
		
		adapter=new RSSAdapter(list, this);
		
		listView.setAdapter(adapter);
		listView.setOnPullEventListener(new OnPullEventListener<ListView>() {

			@Override
			public void onPullEvent(PullToRefreshBase<ListView> refreshView,
					State state, Mode direction) {
				if(state.equals(State.RELEASE_TO_REFRESH)){
				}
			}
		});
		setActionBar();
		//请求
		ApiClientFactory.getRssList(this, RSSActivity.this, "getList", 1, 20);
	}
	
	@SuppressWarnings("rawtypes")
	public void getList(Bean2Json list){
		this.list.clear();
		this.list.addAll(((MRssList)list).getList());
		adapter.notifyDataSetChanged();
	}
	
	private void setActionBar(){
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
		title.setText("订阅");
		
		Button btn_back = (Button)view.findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RSSActivity.this.finish();
			}
		});
		
		Button add=(Button) view.findViewById(R.id.btn_control);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RSSActivity.this,RSSSelectActivity.class);
				RSSActivity.this.startActivity(intent);
			}
		});
		
	}
}
