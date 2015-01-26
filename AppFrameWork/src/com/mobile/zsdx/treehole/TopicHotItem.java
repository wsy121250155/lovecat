package com.mobile.zsdx.treehole;

import com.mobile.base.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TopicHotItem extends FrameLayout{

    public TextView tag;
    
    private View icon,layout,num;
	public TopicHotItem(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater lf = LayoutInflater.from(getContext());
		lf.inflate(R.layout.topichotitem, this);
        tag=(TextView) findViewById(R.id.tag);
        icon=findViewById(R.id.icon);
        num=findViewById(R.id.num);
	}

	public void set(boolean isHot){
		String tag;
		int type;
		if(isHot){
			type =1 ;
            num.setBackgroundResource(R.drawable.ic_th_hot);
            tag=getResources().getString(R.string.rm);
            this.tag.setText(tag);
		}else{
			type =2 ;
            num.setBackgroundResource(R.drawable.ic_th_give);
            tag=getResources().getString(R.string.tj);
            this.tag.setText(tag);
		}
	}
}
