package com.mobile.zsdx.treehole;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.api.proto.MAppTreeHole.MTreeHole;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.Mode;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.OnRefreshListener2;

public class TreeholeTListFragment extends MFragment{

	private String topicid = "" , titleString = "";
	
	private PullToRefreshListView listView;
	
	private TextView title;
	
	private MTreeHole treeHoleList = new MTreeHole();
	
	private THMainAdapter adapter;
	
	private static int PAGE = 1 , LIMIT = 20 , TYPE = 1 ;

	@Override
	public void create(Bundle savedInstanceState){
		super.create(savedInstanceState);
		setContentView(R.layout.treeholefrg);
		
		listView = (PullToRefreshListView) findViewById(R.id.thmainlist);
		listView.setMode(Mode.BOTH);//同时支持上拉和下拉加载操作
		
		topicid = this.getArguments().getString("topicid");
		titleString = this.getArguments().getString("topicname");
		
		setActionBar();
		
		adapter = new THMainAdapter(getContext(),treeHoleList);
		
		listView.setAdapter(adapter);
		
		loadMore(TYPE, PAGE, LIMIT, topicid);
		
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				PAGE = 1; LIMIT = 20;
				loadMore(TYPE, PAGE, LIMIT, topicid);
				
				new FinishRefresh().execute();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				PAGE++;
				
				loadMore(TYPE, PAGE, LIMIT, topicid);
				new FinishRefresh().execute();
			}
		});
	}
	
	private void loadMore(int type, int page, int limit, String topicId){
		ApiClientFactory.getTTreeHoleList(getContext(),
				TreeholeTListFragment.this,
				"getData",
				type,
				page,
				limit,
				topicId);
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
		this.title.setText(titleString);
		Log.d("MYAPP", "标题为:"+titleString);
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
