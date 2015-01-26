package com.mobile.zsdx.treehole;

import com.mobile.api.proto.MAppTreeHole.MTagList;
import com.mobile.api.proto.MAppTreeHole.MTreeHole;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.Mode;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.OnRefreshListener2;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/*
 * 树洞的主显示页面
 */
public class THMainFragment extends MFragment{

	private PullToRefreshListView listView;
	
	private Button topica, topicb, topicc, topicd;
	
	private TextView title, commons;
	
	private View header;
	
	private MTreeHole treeHoleList = new MTreeHole();
	
	private THMainAdapter adapter;
	
	private LinearLayout layout;
	
	private static int PAGE = 1 , LIMIT = 20 , TYPE = 1;
	@Override
	protected void create(Bundle paramBundle) {
		setContentView(R.layout.treeholefrg);
		setActionBar();
		listView = (PullToRefreshListView) findViewById(R.id.thmainlist);
		
		layout = (LinearLayout) findViewById(R.id.bottomView);
		layout.setVisibility(View.GONE);
		
		listView.setMode(Mode.BOTH);//同时支持上拉和下拉加载操作
		//给listview添加header
		LayoutInflater f = LayoutInflater.from(getContext());
		header = f.inflate(R.layout.treeholeheader, null);
        topica = (Button) header.findViewById(R.id.taga);
        topicb = (Button) header.findViewById(R.id.tagb);
        topicc = (Button) header.findViewById(R.id.tagc);
        topicd = (Button) header.findViewById(R.id.tagd);
		listView.addHeaderView(header);
		
		adapter = new THMainAdapter(getContext(),treeHoleList);
		
		listView.setAdapter(adapter);
		//加载列表
		loadMore(TYPE, PAGE, LIMIT);
		//加载topic
		loadTag();
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				PAGE = 1; LIMIT = 20;
				loadMore(TYPE, PAGE, LIMIT);
				
				new FinishRefresh().execute();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				PAGE++;
				
				loadMore(TYPE, PAGE, LIMIT);
				new FinishRefresh().execute();
			}
		});
		
		//最新or最热
		topica.setText("最新");
		topica.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				PAGE = 1;
				treeHoleList.getTopics().clear();
				if(TYPE == 1){
					TYPE = 2 ;
					topica.setText("最热");
					loadMore(TYPE, PAGE, LIMIT);
				}else if(TYPE == 2){
					TYPE = 1;
					topica.setText("最新");
					loadMore(TYPE, PAGE, LIMIT);
				}
			}
		});
		
		//更多话题
		topicd.setText("更多话题");
		topicd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					TreeHoleActivity.showFragment(TopicAllFragment.class.newInstance(), null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void loadMore(int type, int page, int limit){
		ApiClientFactory.getTreeHoleList(getContext(),
				THMainFragment.this,
				"getData",
				type,
				page,
				limit);
	}
	
	private void loadTag(){
		ApiClientFactory.getTopicList(getContext(),
				THMainFragment.this,
				"getTopic");
	}
	
	@SuppressWarnings("rawtypes")
	public void getTopic(Bean2Json topics){
		topicb.setText(((MTagList)topics).getTags().get(0).getTitle());
		topicc.setText(((MTagList)topics).getTags().get(1).getTitle());
	}
	@SuppressWarnings("rawtypes")
	public void getData(Bean2Json treeHole){
		treeHoleList.getTopics().addAll(((MTreeHole) treeHole).getTopics());
		adapter.notifyDataSetChanged();
	}
	
	public void setActionBar(){
		ActionBar actionBar = ((ActionBarActivity)getContext()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        
        ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.gravity = Gravity.RIGHT;
        LayoutInflater f = LayoutInflater.from(getContext());
        View view = f.inflate(R.layout.item_treehole_actionbar, null);
        actionBar.setCustomView(view, layout);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        this.title = ((TextView) view.findViewById(R.id.title));
        this.commons = (TextView) view.findViewById(R.id.commons);
        this.title.setText("最热");
        view.findViewById(R.id.create).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	try {
					TreeHoleActivity.showFragment(THAnnounceFragment.class.newInstance(), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        this.commons.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                
            }
        });
		
	}
	
	private class FinishRefresh extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
			listView.onRefreshComplete();
		}
	}
	
	
}
