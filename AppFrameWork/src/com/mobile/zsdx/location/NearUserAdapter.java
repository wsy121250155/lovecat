package com.mobile.zsdx.location;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.api.proto.MSystem.MCourse;
import com.mobile.base.R;
import com.mobile.base.util.MLog;

public class NearUserAdapter extends BaseAdapter {
	private List<MCourse> list;
	private Context mContext;
	
	public NearUserAdapter(List<MCourse> list,Context context){
		this.list=list;
		this.mContext=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public MCourse getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		viewholder holder;
		MLog.d("1111111111111");
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_nearuser, null);
			holder=new viewholder();
			holder.name=(TextView) convertView.findViewById(R.id.nearusername);
			holder.distance=(TextView) convertView.findViewById(R.id.nearuserdistance);
			holder.time=(TextView) convertView.findViewById(R.id.nearusertime);
			convertView.setTag(holder);
		}else {
			holder=(viewholder) convertView.getTag();
		}
		
		holder.name.setText(list.get(position).getName());
		holder.distance.setText("距离：" + list.get(position).getPoint());
		holder.time.setText(list.get(position).getGrade());
		
		return convertView;
	}
	
	public class viewholder{
		public TextView name;
		public TextView distance;
		public TextView time;
	}
	
}
