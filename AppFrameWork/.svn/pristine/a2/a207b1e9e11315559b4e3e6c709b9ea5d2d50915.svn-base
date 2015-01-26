package com.mobile.zsdx.schedule;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.api.proto.MSystem.MClass;
import com.mobile.api.proto.MSystem.MClassList;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.cache.DataCache;
import com.mobile.base.util.Helper;

public class ScheduleAddFragment extends MFragment {
	private EditText nameEdit;
	private EditText teacherEdit;
	private EditText roomEdit;
	private TextView jieshuSelector;
	private TextView zhousSelector;
	
	private ScheduleJieSelectDialog jiesDialog;
	private ScheduleWeekSelectDialog weekSelectDialog;
	
	private int mweek = 0, mfrom = 0, mto = 0;
	private List<Integer> weeks;
	
	@Override
	public void create(Bundle savedInstanceState) {
		super.create(savedInstanceState);
		setContentView(R.layout.frg_schedule_add); //填充UI布局
		
		nameEdit = (EditText)findViewById(R.id.mc);
		teacherEdit = (EditText)findViewById(R.id.ls);
		roomEdit = (EditText)findViewById(R.id.jiaos);
		jieshuSelector = (TextView)findViewById(R.id.jies);
		zhousSelector = (TextView)findViewById(R.id.zhous);
		
		jiesDialog = new ScheduleJieSelectDialog(getContext());
		jiesDialog.onSelected = new ScheduleJieSelectDialog.OnSelected() {
            //设置节数
            @Override
            public void onSelected(Dialog dia, int week, int from, int to) {
            	mweek = week;
                mfrom = from;
                mto = to;
                showJies();
            }
        };
        jieshuSelector.setText("周一 第1节~第1节");
		jieshuSelector.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				jiesDialog.show();
			}
		});
		
		zhousSelector.setText("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18");
		weekSelectDialog = new ScheduleWeekSelectDialog(getContext());
		weekSelectDialog.onSelected = new ScheduleWeekSelectDialog.OnSelected() {
			
			@Override
			public void onSelected(Dialog dia, List<Integer> list) {
				// TODO Auto-generated method stub
				weeks = list;
				showWeeks();
			}
		};
		zhousSelector.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				weekSelectDialog.show();
			}
		});
	}
	
	//显示选中的节数
	private void showJies() {
		jieshuSelector.setText(new StringBuilder(ScheduleJieSelectDialog.weeks[mweek])
		.append("  ").append(ScheduleJieSelectDialog.jies[mfrom]).append("~").append(ScheduleJieSelectDialog.jies[mto]).toString());
	}
	
	//显示选中的周数
	public void showWeeks() {
		StringBuilder sb = new StringBuilder();
		String temp = "";
		for(Integer i : weeks) {
			sb.append(temp).append(i + 1);
			temp = ",";
		}
		zhousSelector.setText(sb.toString());
	}
	
	/**
	 * 向缓存中添加课程
	 */
	public void addClass() {
		String className = nameEdit.getText().toString();
		String tearchName = teacherEdit.getText().toString();
		String roomName = roomEdit.getText().toString();
		String weeks = zhousSelector.getText().toString();
		
		if(TextUtils.isEmpty(className)) {
			Helper.toast(getContext(), "请输入课程名称");
		} else if(TextUtils.isEmpty(tearchName)) {
			Helper.toast(getContext(), "请输入教师姓名");
		} else if(TextUtils.isEmpty(roomName)) {
			Helper.toast(getContext(), "请输入教室名称");
		} else {
			MClassList data = new MClassList();
			DataCache.readData("schedule", data, getContext());
			MClass mc = new MClass();
			mc.setId(System.currentTimeMillis() + "");
			mc.setName(className);
			mc.setAddress(roomName);
			mc.setBusyweeks(weeks);
			mc.setWeek(weeks);
			mc.setDay(mweek + 1);
			mc.setBegin(mfrom + 1);
			mc.setEnd(mto + 1);
			data.getClass_().add(mc);
			DataCache.saveData("schedule", data, getContext());
			getActivity().finish();
			
			//重新生成课表UI
			Intent intent=new Intent();
			intent.setAction("SET_CLASS_DATA");
			ScheduleAddFragment.this.getContext().sendBroadcast(intent);
		}

	}
	
	public void setActionBar(ActionBar actionBar, Context context) {
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.actb_head_control_nst, null);
		
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.RIGHT;
		
		actionBar.setCustomView(view, layoutParams);
		
		TextView title = (TextView)view.findViewById(R.id.tv_title);
		title.setText("添加课程");
		
		Button btn_back = (Button)view.findViewById(R.id.btn_back);
		btn_back.setText("取消");
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		
		Button btn_cntl = (Button)view.findViewById(R.id.btn_control);
		btn_cntl.setText("确定");
		btn_cntl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				addClass();
			}
		});
	}
}
