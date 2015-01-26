package com.mobile.zsdx.treehole;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.mobile.api.proto.MAppTreeHole.MComment;
import com.mobile.api.proto.MAppTreeHole.MCommentList;
import com.mobile.api.proto.MAppTreeHole.MTopic;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.http.ApiClient;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.Mode;
import com.mobile.base.widget.pullfreshlist.PullToRefreshBase.OnRefreshListener2;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;
import com.mobile.zsdx.util.F;


public class THDetailFragment extends MFragment{

	private PullToRefreshListView listView;
	
	private MTopic treeHoleItem;
	
	private TreeHoleItem header;
	
	private THCommonsAdapter adapter;
	
	private MCommentList mcomments = new MCommentList();
	
	private LinearLayout addlayout;
	
	private InputDialog inputDialog;
	
	private String id , replyid;
	
	private int replyfloor, isLz;
	
	private MComment comment;
	
	private static int PAGE = 1 , LIMIT = 20;
	
	public void create(Bundle savedInstanceState){
		super.create(savedInstanceState);
		setContentView(R.layout.treeholedetail);
		setActionBar();
		listView = (PullToRefreshListView) findViewById(R.id.THDlistview);
		listView.setMode(Mode.BOTH);
		
		inputDialog = new InputDialog(getContext());
		
		addlayout = (LinearLayout) findViewById(R.id.bottomView);
		
		treeHoleItem = (MTopic) getArguments().getSerializable("thitem");
		id = treeHoleItem.getId();
		
		header = new TreeHoleItem(getContext());
		header.setdata(treeHoleItem);
		listView.addHeaderView(header);
		
		adapter = new THCommonsAdapter(getContext(), mcomments, this);
		
		listView.setAdapter(adapter);
		
		loadComments();
		
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				PAGE = 1;
				loadComments();
				new FinishRefresh().execute();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				PAGE++;
				loadComments();
				new FinishRefresh().execute();
			}
			
		});
		
		addlayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				replyid = treeHoleItem.getAuthor();
				replyfloor = 0;
                inputDialog.set(id, replyid, replyfloor, isLz);
                inputDialog.show();
			}
		});
	}
	private void loadComments(){
		ApiClientFactory.getComments(getContext(),
				THDetailFragment.this,
				"getData",
				PAGE,
				LIMIT,
				treeHoleItem.getId());
	}
	
	@SuppressWarnings("rawtypes")
	public void getData(Bean2Json comments){
		mcomments.getComments().addAll(((MCommentList)comments).getComments());
		adapter.notifyDataSetChanged();
		
		if(treeHoleItem.getAuthor().equals(F.USERID)){
			isLz = 1;
		}else {
			isLz = 0;
		}
		
	}
	
    public void setActionBar() {
    	ActionBar actionBar = ((ActionBarActivity)getContext()).getSupportActionBar();
    	actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        
        ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.gravity = Gravity.RIGHT;
        LayoutInflater f = LayoutInflater.from(getContext());
        View view = f.inflate(R.layout.item_header_title, null);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        view.findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        actionBar.setCustomView(view, layout);
        Button button = (Button) view.findViewById(R.id.submit);
        button.setVisibility(View.INVISIBLE);
        ((TextView) view.findViewById(R.id.title)).setText("详情");
    }
    
    public void ShowInputDialog(String replyid, int floor){
    	inputDialog.set(id, replyid, floor, isLz);
    	inputDialog.show();
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
