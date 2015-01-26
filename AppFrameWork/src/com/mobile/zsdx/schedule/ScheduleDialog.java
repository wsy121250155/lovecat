package com.mobile.zsdx.schedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

import com.mobile.api.proto.MSystem.MClass;
import com.mobile.base.R;
import com.mobile.base.dialog.MDialog;
import com.mobile.base.widget.fancyCoverFlow.FancyCoverFlow;

public class ScheduleDialog extends MDialog{

	private FancyCoverFlow fancyCoverFlow;
	private List<MClass> mlist=new ArrayList<MClass>();
	private int now_week;
	
	public ScheduleDialog(Context context,List<MClass> mlist, int now_week){
		super(context,R.style.Dialog);
		this.mlist=mlist;
		this.now_week = now_week;
    }
	
	public ScheduleDialog(Context context, int theme) {
		super(context, theme);
	}
    
    @SuppressWarnings("deprecation")
    @Override
	public void create(Bundle savedInstanceState) {
//        setId("ScheduleFragment");
        this.setContentView(R.layout.dialog_schedule);
        fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.fancyCoverFlow);
        fancyCoverFlow.setSpacing(-(int) getContext().getResources().getDimension(R.dimen.fancyCovers));
        fancyCoverFlow.setAdapter(new ScheduleAda(getContext(),mlist, now_week, this));
        if(mlist.size()>2){
            fancyCoverFlow.setSelection(1);
        }
    }

}
