package com.mobile.zsdx.rss;


import java.util.List;



import com.mobile.base.R;
import com.mobile.base.widget.views.MImageView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mobile.api.proto.MAppNews.MRss;

public class RSSAdapter extends BaseAdapter{

	private List<MRss> list;
	private Context mContext;
	public RSSAdapter(List<MRss> list,Context context){
		super();
		this.list=list;
		this.mContext=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public MRss getItem(int position) {
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
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.rssitem, null);
			holder=new viewholder();
			holder.img=(MImageView) convertView.findViewById(R.id.rss_img);
			holder.title=(TextView) convertView.findViewById(R.id.rss_title);
			holder.des=(TextView) convertView.findViewById(R.id.rss_des);
			convertView.setTag(holder);
		}else {
			holder=(viewholder) convertView.getTag();
		}
		
		holder.img.setImage(list.get(position).getImg(),100,100);
		holder.title.setText(list.get(position).getTitle());
		holder.des.setText(list.get(position).getContent());
		//加每个item的监听
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(mContext,RSSDetailActivity.class);
				intent.putExtra("rssid", list.get(position).getId());
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}
	
	public class viewholder{
		public MImageView img;
		public TextView title;
		public TextView des;
	}
	
}
