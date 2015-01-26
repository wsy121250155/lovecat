package com.mobile.zsdx.rss;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.api.proto.MAppNews.MNews;
import com.mobile.api.proto.MAppNews.MRss;
import com.mobile.base.R;

public class RSSDetailAdapter extends BaseAdapter{

	private List<MNews> mlist;
	private Context context;
	public RSSDetailAdapter(List<MNews> list, Context context){
		this.mlist=list;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
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
			convertView=LayoutInflater.from(context).inflate(R.layout.rssdetailitem, null);
			holder.title=(TextView) convertView.findViewById(R.id.rssdetailtitle);
			holder.des=(TextView) convertView.findViewById(R.id.rssdetaildes);
			holder.img=convertView.findViewById(R.id.rssdetailimg);
			
			convertView.setTag(holder);
			
		}else {
			holder=(viewHolder) convertView.getTag();
		}
		holder.title.setText(mlist.get(position).getTitle());
		holder.des.setText(mlist.get(position).getContent());
		//holder.img.setBackgroundResource(Integer.parseInt(mlist.get(position).getImg()));
		
		return convertView;
	}
	public class viewHolder{
		private TextView title;
		private TextView des;
		private View img;
	}

}
