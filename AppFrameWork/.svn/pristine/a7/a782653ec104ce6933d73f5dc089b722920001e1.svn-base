package com.mobile.zsdx.treehole;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mobile.api.proto.MAppTreeHole.MComment;
import com.mobile.base.R;
import com.mobile.zsdx.util.F;

public class THCommons extends FrameLayout{

    private TextView num, callback, detail, time;
    private View icon;
    private MComment mdata;
    private Context mcontext;
	public THCommons(Context context) {
		super(context);
		this.mcontext = context;
		init();
	}
	
	private void init(){
		LayoutInflater lf = LayoutInflater.from(getContext());
		lf.inflate(R.layout.treeholecommons, this);
        num=(TextView) findViewById(R.id.num);
        callback=(TextView) findViewById(R.id.callback);
        detail=(TextView) findViewById(R.id.detail);
        time=(TextView) findViewById(R.id.time);
        icon=findViewById(R.id.icon);
	}
	
	@SuppressWarnings("deprecation")
	public void setdata(MComment data, int position){
		this.mdata = data;
	        this.num.setText(data.getFloor()+"");
	        this.num.setBackgroundDrawable(F.getColorDrawable(position));
	        if((Integer)data.getReplyFloor() != null){
	            callback.setVisibility(View.VISIBLE);
	            if(data.getIsLz()==1){
	                callback.setText("楼主 回复 "+data.getReplyFloor()+"楼");
	            }else if(data.getReplyFloor() > 0){
	                callback.setText("回复 "+data.getReplyFloor()+"楼");
	            }else {
					callback.setVisibility(View.GONE);
				}
	        }else{
	            if(data.getIsLz()==1){
	                callback.setVisibility(View.VISIBLE);
	                callback.setText("楼主");
	            }else{
	                callback.setVisibility(View.GONE);
	            }
	        }
	        
	        this.detail.setText(data.getContent());
	        this.time.setText(data.getTime());
	        if((Integer)data.getIsLz() != null){
	            if(data.getIsLz()==1){
	                this.detail.setTextColor(0xFF6E0F6D);
	                callback.setTextColor(0xFF6E0F6D);
	            }else{
	                this.detail.setTextColor(0xFF525252);
	                callback.setTextColor(0xFFa4a4a4);
	            }
	        }
	}

}
