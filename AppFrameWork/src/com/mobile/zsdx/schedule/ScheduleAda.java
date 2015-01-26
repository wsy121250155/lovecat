/*
 * Copyright 2013 David Schreiber 2013 John Paul Nalog
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.mobile.zsdx.schedule;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobile.api.proto.MSystem.MClass;
import com.mobile.base.R;
import com.mobile.base.widget.fancyCoverFlow.FancyCoverFlow;
import com.mobile.base.widget.fancyCoverFlow.FancyCoverFlowAdapter;
import com.mobile.zsdx.util.F;

public class ScheduleAda extends FancyCoverFlowAdapter {
    private List<MClass> mList;
    
    private Context context;
    
    private LayoutInflater mInflater;
    
    private int colorindex = 0; // Math.abs(new
                                // Random().nextInt()%F.colorDrawable.length);
    private int now_week;
    
    private ScheduleDialog parentDialog;
    
    public ScheduleAda(Context context, List<MClass> list, int now_week, ScheduleDialog parentDialog) {
        mList = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.now_week = now_week;
        this.parentDialog = parentDialog;
    }
    
    @Override
    public int getCount() {
        return mList.size();
    }
    
    @Override
    public MClass getItem(int i) {
        return mList.get(i);
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
	@Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        MClass clas = getItem(i);
        if (reuseableView == null) {
            reuseableView = mInflater.inflate(R.layout.item_schedule_detail, null);
            ViewHolder vh = new ViewHolder();
            vh.title = (TextView) reuseableView.findViewById(R.id.title);
            vh.address = (TextView) reuseableView.findViewById(R.id.address);
            vh.btn = (ImageButton) reuseableView.findViewById(R.id.button);
            reuseableView.setLayoutParams(new FancyCoverFlow.LayoutParams((int) context.getResources()
                    .getDimension(R.dimen.fancyCoverw), (int) context.getResources().getDimension(R.dimen.fancyCoverh)));
            reuseableView.setTag(vh);
            vh.btn.setTag(vh);
            vh.btn.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    ViewHolder vh = (ViewHolder) v.getTag();
                    ScheduleDetailDialog sdd = new ScheduleDetailDialog(context, vh.clas);   
                    sdd.setParentDialog(parentDialog);
                    sdd.show();
                }
            });
        }
        ViewHolder vh = (ViewHolder) reuseableView.getTag();
        //根据课程判断本周是否有课
        if(ScheduleLayout.isBusyWeek(clas.getBusyweeks(), now_week)) {
        	vh.btn.setImageDrawable(F.getColorDrawable(i + colorindex));
        	vh.address.setText(clas.getAddress());
        } else {
        	vh.address.setText(clas.getAddress() + "@本周无课");
        	vh.btn.setBackgroundResource(R.color.bt_gray_l);
        }
        vh.title.setText(clas.getName());
        vh.clas = clas;
        
        return reuseableView;
    }
    
    public class ViewHolder {
        public TextView title;
        
        public ImageButton btn;
        
        public TextView address;
        
        public MClass clas;
    }
}
