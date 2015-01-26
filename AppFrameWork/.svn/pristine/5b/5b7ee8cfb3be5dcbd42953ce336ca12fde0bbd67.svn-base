package com.mobile.zsdx.schedule;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.base.R;
import com.mobile.base.dialog.MDialog;
import com.mobile.base.widget.wheel.ArrayWheelAdapter;
import com.mobile.base.widget.wheel.OnWheelChangedListener;
import com.mobile.base.widget.wheel.WheelView;

public class ScheduleJieSelectDialog extends MDialog {
    private WheelView weekWheel, fromWheel, toWheel;
    
    private TextView title;
    
    private Button submit;
    
    public static String jies[] = new String[] { "第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节", "第9节", "第10节",
            "第11节", "第12节" ,"第13节","第14节","第15节"};
    
    public static String weeks[] = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
    
    private int sWeek = 0, sfrom = 0, sto = 0, sInd = 0;
    
    public OnSelected onSelected;
    
    public ScheduleJieSelectDialog(Context context) {
        super(context, R.style.Dialog);
        
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setGravity(Gravity.CENTER | Gravity.BOTTOM);
        getWindow().setAttributes(lp);
        
        Window window = getWindow(); // 得到对话框
        window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
        WindowManager.LayoutParams wl = window.getAttributes();
        window.setAttributes(wl);
    }
    
    public ScheduleJieSelectDialog(Context context, int theme) {
        super(context, theme);
    }
    
    @Override
    public void create(Bundle savedInstanceState) {
        this.setContentView(R.layout.dialog_schedule_jie_sel);
        title = (TextView) findViewById(R.id.title);
        submit = (Button) findViewById(R.id.submit);
        weekWheel = (WheelView) findViewById(R.id.week);
        fromWheel = (WheelView) findViewById(R.id.from);
        toWheel = (WheelView) findViewById(R.id.to);
        
        {
            ArrayWheelAdapter<String> ampmAdapter = new ArrayWheelAdapter<String>(weeks);
            weekWheel.setAdapter(ampmAdapter);
        }
        
        {
            ArrayWheelAdapter<String> ampmAdapter = new ArrayWheelAdapter<String>(jies);
            fromWheel.setAdapter(ampmAdapter);
        }
        
        {
            ArrayWheelAdapter<String> ampmAdapter = new ArrayWheelAdapter<String>(jies);
            toWheel.setAdapter(ampmAdapter);
        }
        
        weekWheel.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				sWeek = newValue;
                title.setText(getText());
			}
        });
        
        fromWheel.addChangingListener(new OnWheelChangedListener() {
            
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                sfrom = newValue;
                title.setText(getText());
                settowheel(newValue);
            }
        });
        
        toWheel.addChangingListener(new OnWheelChangedListener() {
            
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                sto = sInd+newValue;
                title.setText(getText());
            }
        });
        title.setText(getText());
        
        submit.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (onSelected != null) {
                    onSelected.onSelected(ScheduleJieSelectDialog.this, sWeek, sfrom, sto);
                } 
                dismiss();
            }
        });
    }
    
    private void settowheel(int ind) {
        int ni = toWheel.getCurrentItem();
        String str[] = new String[jies.length - ind];
        for (int i = ind; i < jies.length; i++) {
            str[i - ind] = jies[i];
        }
        ArrayWheelAdapter<String> ampmAdapter = new ArrayWheelAdapter<String>(str);
//        ampmAdapter.setItemResource(R.layout.item_wheel_text_centered);
//        ampmAdapter.setItemTextResource(R.id.text);
        toWheel.setAdapter(ampmAdapter);
        ni = ni + (sInd - ind);
        sInd = ind;
        sto=toWheel.getCurrentItem()+sInd;
        toWheel.setCurrentItem(ni);
    }
    
    public String getText() {
        return weeks[sWeek] + "  " + jies[sfrom] + "~" + jies[sto];
    }
    
    public interface OnSelected {
        public void onSelected(Dialog dia, int week, int from, int to);
    }
}
