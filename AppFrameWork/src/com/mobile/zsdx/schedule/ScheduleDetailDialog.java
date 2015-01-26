package com.mobile.zsdx.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.api.proto.MSystem.MClass;
import com.mobile.api.proto.MSystem.MClassList;
import com.mobile.base.BaseActivity;
import com.mobile.base.R;
import com.mobile.base.cache.DataCache;
import com.mobile.base.dialog.MDialog;
import com.mobile.base.util.Helper;

public class ScheduleDetailDialog extends MDialog {
    private MClass clas;
    
    private View cancle, delete, qd;
    
    private TextView title, teacher, time, address, week;
    private Context context;
    
    private ScheduleDialog parentDialog;
    
    public ScheduleDetailDialog(Context context, MClass clas) {
        super(context, R.style.Dialog);
        this.context= context;
        this.clas = clas;
        if(context instanceof BaseActivity){
            ((BaseActivity) context).isLoaded=true;
        }
    }
    
    public ScheduleDetailDialog(Context context, int theme) {
        super(context, theme);
    }
    
    @Override
	public void create(Bundle savedInstanceState) {
        this.setContentView(R.layout.dialog_schedule_detail);
        cancle = findViewById(R.id.cancle);
        delete = findViewById(R.id.delete);
        qd = findViewById(R.id.qd);
        title = (TextView) findViewById(R.id.title);
        teacher = (TextView) findViewById(R.id.teacher);
        time = (TextView) findViewById(R.id.time);
        address = (TextView) findViewById(R.id.address);
        week = (TextView) findViewById(R.id.week);
        title.setText(clas.getName());
        teacher.setText(clas.getTeacher());
        this.LoadingShow=true;
        time.setText(clas.getTime());
        address.setText(clas.getAddress());
        week.setText(clas.getWeek());
        cancle.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        
        delete.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
            	//删除缓存
            	MClassList classes = new MClassList();
                DataCache.readData("schedule", classes, getContext());
                MClassList classes2 = new MClassList(); 
                classes2.setWeek(classes.getWeek());
                classes2.setImg1(classes.getImg1());
                for(int i = 0 ,len = classes.getClass_().size(); i < len; i++) {
                	MClass mc = classes.getClass_().get(i);
                	
                	if(!mc.getId().equals(clas.getId())) {
                		classes2.getClass_().add(mc);
                	}
                }
                DataCache.saveData("schedule", classes2, getContext());
            	Intent intent=new Intent();
     			intent.setAction("SET_CLASS_DATA");
     			ScheduleDetailDialog.this.getContext().sendBroadcast(intent);
     			Helper.toast(getContext(), "删除成功");
                dismiss();
                if(parentDialog != null)
                	parentDialog.dismiss();
            }
        });
        qd.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                dismiss();
                if(parentDialog != null)	
                	parentDialog.dismiss();
            }
        });
    }
    
    
    
    @Override
    public void dismiss() {
        super.dismiss();
        
        if(context instanceof BaseActivity){
            ((BaseActivity) context).isLoaded=false;
        }
    }

    //读数据库的旧方法
    public void delete(){
    	Intent intent=new Intent();
		intent.setAction("SET_CLASS_DATA");
		ScheduleDetailDialog.this.getContext().sendBroadcast(intent);
        Helper.toast(getContext(), "删除成功");
        dismiss();
    }

	public ScheduleDialog getParentDialog() {
		return parentDialog;
	}

	public void setParentDialog(ScheduleDialog parentDialog) {
		this.parentDialog = parentDialog;
	}
}
