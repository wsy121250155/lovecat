package com.mobile.zsdx.treehole;

import java.util.HashMap;
import java.util.List;

import com.mobile.api.proto.MAppTreeHole.MTag;
import com.mobile.api.proto.MAppTreeHole.MTagList;
import com.mobile.api.proto.MAppTreeHole.MTopic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TopicAdapter extends BaseAdapter{

	private Context mcontext;
	private List<MTag> mList;
	public TopicAdapter(Context context, MTagList tags){
		this.mcontext = context;
		this.mList = tags.getTags();
	}
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MTag topic = mList.get(position);
		if(convertView == null){
			convertView = new TopicItem(mcontext);
		}
		TopicItem topicItem = (TopicItem) convertView;
		topicItem.set(topic);
		topicItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("topicid", topic.getId());
				param.put("topicname", topic.getTitle());
				Log.d("MYAPP","传过去的标题为:"+topic.getTitle());
			    try {
					TreeHoleActivity.showFragment(TreeholeTListFragment.class.newInstance(), param);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return convertView;
	}
}
