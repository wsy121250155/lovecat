package com.mobile.zsdx.rss;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mobile.api.proto.MAppNews.MRss;
import com.mobile.base.R;

public class RSSSelectAdapter extends BaseAdapter{


	private List<MRss> mlist;
	private Context context;
	public RSSSelectAdapter(List<MRss> list, Context context){
		this.mlist=list;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewHolder holder=new viewHolder();
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.rssselecteditem, null);
			holder.title=(TextView) convertView.findViewById(R.id.rssselectedtitle);
			holder.des=(TextView) convertView.findViewById(R.id.rssselecteddes);
			holder.img=convertView.findViewById(R.id.rssselectedicon);
			holder.state=(CheckBox) convertView.findViewById(R.id.rssstate);
			
			convertView.setTag(holder);
			
		}else {
			holder=(viewHolder) convertView.getTag();
		}
		holder.title.setText(mlist.get(position).getTitle());
		holder.des.setText(mlist.get(position).getContent());
		holder.img.setBackgroundResource(Integer.parseInt(mlist.get(position).getImg()));
		if(mlist.get(position).getState()==1){
			holder.state.setChecked(true);
		}else{
			holder.state.setChecked(false);
		}
		
		return convertView;
	}
	public class viewHolder{
		private TextView title;
		private TextView des;
		private View img;
		private CheckBox state;
	}
}
