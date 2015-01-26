package com.mobile.zsdx.treehole;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.api.proto.MAppTreeHole.MComment;
import com.mobile.api.proto.MAppTreeHole.MCommentList;

public class THCommonsAdapter extends BaseAdapter{

	private MCommentList mlist;
	
	private THDetailFragment fragment;
	
	private Context mcontext;
	public THCommonsAdapter(Context context, MCommentList list, THDetailFragment fragment){
		this.mcontext = context;
		this.mlist = list;
		this.fragment = fragment;
	}
	@Override
	public int getCount() {
		return mlist.getComments().size();
	}

	@Override
	public Object getItem(int position) {
		return mlist.getComments().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MComment treeHoleCommon = mlist.getComments().get(position);
		if (convertView == null){
			convertView = new THCommons(mcontext);
		}
		THCommons thCommons = (THCommons) convertView;
		thCommons.setdata(treeHoleCommon,position);
		
		thCommons.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				fragment.ShowInputDialog(treeHoleCommon.getId(), treeHoleCommon.getFloor());
			}
		});
		
		return convertView;
	}

}
