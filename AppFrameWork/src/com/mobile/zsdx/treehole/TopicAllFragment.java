package com.mobile.zsdx.treehole;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.api.proto.MAppTreeHole.MTagList;
import com.mobile.api.proto.MAppTreeHole.MTopic;
import com.mobile.api.proto.MAppTreeHole.MTreeHole;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;

public class TopicAllFragment extends MFragment{

	private PullToRefreshListView listView;
	
	private MTagList list = new MTagList();
	
	private TopicAdapter adapter;
	@Override
	public void create(Bundle savedInstanceState){
		super.create(savedInstanceState);
		setContentView(R.layout.th_topic_frg);
		
		setActionBar();
		
		listView = (PullToRefreshListView) findViewById(R.id.listview);
		{
			TopicHotItem item = new TopicHotItem(getContext());
			item.set(false);
			listView.addHeaderView(item);
		}
		{
			TopicHotItem item2 = new TopicHotItem(getContext());
			item2.set(true);
			listView.addHeaderView(item2);
		}
		
		adapter = new TopicAdapter(getContext(), list);
		
		listView.setAdapter(adapter);
		
		
		loadAllTags();
	}
	private void loadAllTags(){
		ApiClientFactory.getTopicList(getContext(), TopicAllFragment.this, "getData");
	}
	
	@SuppressWarnings("rawtypes")
	public void getData(Bean2Json tags){
		list.getTags().addAll(((MTagList)tags).getTags());
		adapter.notifyDataSetChanged();
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
        ((TextView) view.findViewById(R.id.title)).setText("话题");
    }
}
