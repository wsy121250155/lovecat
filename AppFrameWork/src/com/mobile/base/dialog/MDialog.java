package com.mobile.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

public class MDialog extends Dialog {
	protected boolean LoadingShow = false;  
	protected Context context;
	
	public MDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	if(!this.LoadingShow) {//如果没有创建UI在这里创建
			this.create(savedInstanceState);
			this.LoadingShow = true;
		}
    	WindowManager.LayoutParams lp = getWindow().getAttributes();
         //When FLAG_DIM_BEHIND is set, this is the amount of dimming to apply. Range is from 1.0 for completely opaque to 0.0 for no dim. 
    	lp.dimAmount = 0.4f;
    	getWindow().setAttributes(lp);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
	
	public void create(Bundle savedInstanceState) {
		
	}
}
