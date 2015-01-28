package com.frame.view.util;

import com.frame.data.model.DataBuffer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter<V extends ViewGenerator> extends BaseAdapter {
	private Context context;
	private DataBuffer<V> buffer;

	public MyAdapter(Context context, int dataType) {
		// TODO Auto-generated constructor stub
		buffer = new DataBuffer<V>(dataType);
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// return buffer.getCount();
		return 30;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// ViewGenerator generator = buffer.getDataItem(position);
		// return generator.getBriefView();
		TextView textView = new TextView(context);
		textView.setText("textView: " + position);
		return textView;
	}
}
