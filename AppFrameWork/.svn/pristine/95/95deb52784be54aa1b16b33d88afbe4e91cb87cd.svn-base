package com.mobile.zsdx.treehole;

import java.util.ArrayList;
import java.util.List;

import com.mobile.api.proto.MAppTreeHole.MTopic;
import com.mobile.api.proto.MAppTreeHole.MTreeHole;

import android.content.Context;
import android.content.ClipData.Item;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class THMainAdapter extends BaseAdapter{

	private Context mContext;
	private List<MTopic> mList = new ArrayList<MTopic>();
	public THMainAdapter(Context context, MTreeHole treeHole){
		this.mContext = context;
		mList = treeHole.getTopics();
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
		return position;
	}

	@Override
	public View getView(int position , View convertView, ViewGroup parent) {
		MTopic data= mList.get(position);
		
		if(convertView == null){
			convertView = new TreeHoleItem(mContext);
		}
		TreeHoleItem item = (TreeHoleItem) convertView;
		item.setdata(data);
		
		return convertView;
	}

}
