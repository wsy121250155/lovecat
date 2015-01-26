package com.mobile.zsdx.schedule;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.api.proto.MAppNews.MNewsList;
import com.mobile.api.proto.MSystem.MClassList;
import com.mobile.api.proto.MSystem.MRet;
import com.mobile.base.MFragment;
import com.mobile.base.MainActivity;
import com.mobile.base.R;
import com.mobile.base.cache.DataCache;
import com.mobile.base.http.ApiClient;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.ConstantResource;
import com.mobile.base.util.MLog;

public class ScheduleFragment extends MFragment {
	private int dayCnt = 7;
	private TextView[] weeks = new TextView[dayCnt]; //日期和星期表头
	private TextView weekFirst; //总表头
	private ScrollView scrollView;
	private ScheduleLayout layout;
	
	private Button addBtn;
	
	private TextView subtitle;
	
  	private BroadcastReceiver b = new BroadcastReceiver(){

  		@Override
  		public void onReceive(Context context, Intent intent) {
  			if(intent.getAction().equals("SET_CLASS_DATA")){
  				//更新UI  				
  				readBuffData();	
  			}
  		}
  		
  	};
	
	private boolean isLoadData = false;
	
	/**
	 * 创建Fragment视图
	 * @param savedInstanceState
	 */
	@Override
	protected void create(Bundle savedInstanceState) {
		super.create(savedInstanceState);
		IntentFilter itf=new IntentFilter();
		itf.addAction("SET_CLASS_DATA");
		this.getActivity().registerReceiver(b, itf);
		
		setContentView(R.layout.frg_schedule); //填充UI布局
		
		weekFirst = (TextView)findViewById(R.id.weekdFirst);
		scrollView = (ScrollView)findViewById(R.id.schedule_scrollView);
		addBtn = (Button)findViewById(R.id.addbutton);
		
		addBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getContext(),MainActivity.class);
				i.putExtra("Active_Fragment", "scheduleadd");
				getContext().startActivity(i);
			}
		});
		
		readBuffData();
		//weekIds长度必须为dayCnt
		int[] weekIds = {R.id.weekd1,R.id.weekd2,R.id.weekd3,R.id.weekd4,R.id.weekd5,R.id.weekd6,R.id.weekd7};
		
		for(int i = 0; i < dayCnt; i++) {
			weeks[i] = (TextView)findViewById(weekIds[i]);
		}
		//
		this.setValue();
		
		//获取最新周数
		ApiClientFactory.getWeeks(getContext(), this, "renewWeek");
	}
	
	private void readBuffData() {
		MClassList data = new MClassList();
		DataCache.readData("schedule", data, getContext());
		addScheduleContent(data);
	}
	
	public void setValue() {
		Calendar mCalendar = Calendar.getInstance();
		mCalendar.setTimeInMillis(System.currentTimeMillis());
		
		int week = mCalendar.get(Calendar.DAY_OF_WEEK);
		
		//根据当前是星期几映射到相应位置上的TextView
		if (week == 1) {
            week = 6;
        } else {
            week -= 2;
        }
		
		//设置表头内容
		weekFirst.setText(mCalendar.get(Calendar.MONTH) + 1 + "月");

		mCalendar.add(Calendar.DAY_OF_MONTH, -week);
		for(int i = 0; i < weeks.length; i++) {
			weeks[i].setText(mCalendar.get(Calendar.DAY_OF_MONTH) + "\n" + ConstantResource.getWeekName(i));
			weeks[i].setBackgroundColor(0xFFFFFFFF);
			mCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		weeks[week].setBackgroundColor(0xffffd7ff);
	}
	
	@SuppressWarnings("rawtypes")
	public void dataload(Bean2Json list) {
		MLog.d("success");
		Toast.makeText(getActivity(), ((MNewsList)list).toString(), Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 在退出的时候，强制关闭请求
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		ApiClient.closeReq(getActivity(), true);
		getActivity().unregisterReceiver(b);
	}
	@Override
	public void onPause() {
		super.onPause();
		ApiClient.closeReq(getActivity(), true);
	}
	@Override
	public void onStop() {
		super.onPause();
		ApiClient.closeReq(getActivity(), true);
	}
	

	public void setActionBar(ActionBar actionBar, Context context) {
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.actb_head_control, null);
		
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.RIGHT;
		
		actionBar.setCustomView(view, layoutParams);
		
		TextView title = (TextView)view.findViewById(R.id.tv_title);
		title.setText("课程表");
		
		subtitle = (TextView)view.findViewById(R.id.tv_subTitle);
		subtitle.setText("第5周");
		
		Button btn_back = (Button)view.findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		
		Button btn_cntl = (Button)view.findViewById(R.id.btn_control);
		btn_cntl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new JWLoginDialog(getContext(), ScheduleFragment.this).show();
				//ApiClientFactory.getScheduleApi(getContext(), ScheduleFragment.this, "addScheduleContent", "111250088", "112985", "");
			}
		});
	}
	
	/**
	 * 添加课表内容
	 */
	@SuppressWarnings("rawtypes")
	public void addScheduleContent(Bean2Json data) {
		MClassList classes = (MClassList)data;
		if(classes != null) {
			int now_week = classes.getWeek();
			subtitle.setText("第" + now_week + "周");
			if(!isLoadData) {
				layout = new ScheduleLayout(getContext(), classes);
				this.scrollView.addView(layout);
				isLoadData = true;
			} else {
				layout.setData(classes);		
				DataCache.saveData("schedule", data, getContext());
			}
		}
		
	}
	
	public void renewWeek(Bean2Json data) {
		MRet ret = (MRet)data;
		int now_week = ret.getCode();
		subtitle.setText("第" + now_week + "周");
	}
}
