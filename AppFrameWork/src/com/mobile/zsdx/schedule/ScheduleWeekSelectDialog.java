package com.mobile.zsdx.schedule;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobile.base.R;
import com.mobile.base.dialog.MDialog;

public class ScheduleWeekSelectDialog extends MDialog implements OnCheckedChangeListener {
    private RadioGroup checked;
    
    private LinearLayout weeksLay;
    
    private Button submit, cancle;
    
    private List<CheckBox> listbox = new ArrayList<CheckBox>();
    
    private TextView title;
    
    public OnSelected onSelected;
    
    public List<Integer> list = new ArrayList<Integer>();
    
    public ScheduleWeekSelectDialog(Context context) {
        super(context, R.style.Dialog);
        
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setGravity(Gravity.CENTER | Gravity.BOTTOM); 
        getWindow().setAttributes(lp);
        
        Window window = getWindow(); // 得到对话框
        window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
        WindowManager.LayoutParams wl = window.getAttributes();
        window.setAttributes(wl);
        for (int i = 0; i < 25; i++) {
            list.add(i);
        }
    }
    
    public ScheduleWeekSelectDialog(Context context, int theme) {
        super(context, theme);
    }
    
    @Override
    public void create(Bundle savedInstanceState) {
        this.setContentView(R.layout.dialog_schedule_week_sel);
        checked = (RadioGroup) findViewById(R.id.checked);
        title = (TextView) findViewById(R.id.title);
        submit = (Button) findViewById(R.id.submit);
        cancle = (Button) findViewById(R.id.cancel);
        weeksLay = (LinearLayout) findViewById(R.id.weeks);
        checked.setOnCheckedChangeListener(checklistener);
        setWeeks();
        
        cancle.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        
        submit.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                list.clear();
                for (int i = 0; i < listbox.size(); i++) {
                    if (listbox.get(i).isChecked()) {
                        list.add(i);
                    }
                }
                if (onSelected != null) {
                    onSelected.onSelected(ScheduleWeekSelectDialog.this, list);
                }
                dismiss();
            }
        });
    }
    
    public RadioGroup.OnCheckedChangeListener checklistener = new RadioGroup.OnCheckedChangeListener() {
        
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int i = 0;
            switch (checkedId) {
                case R.id.qb:
                    for (CheckBox cb : listbox) {
                        cb.setOnCheckedChangeListener(null);
                        cb.setChecked(true);
                        cb.setOnCheckedChangeListener(ScheduleWeekSelectDialog.this);
                    }
                    break;
                case R.id.dz:
                    i = 0;
                    for (CheckBox cb : listbox) {
                        cb.setOnCheckedChangeListener(null);
                        cb.setChecked(i % 2 == 0 ? true : false);
                        cb.setOnCheckedChangeListener(ScheduleWeekSelectDialog.this);
                        i++;
                    }
                    break;
                case R.id.sz:
                    i = 0;
                    for (CheckBox cb : listbox) {
                        cb.setOnCheckedChangeListener(null);
                        cb.setChecked(i % 2 == 1 ? true : false);
                        cb.setOnCheckedChangeListener(ScheduleWeekSelectDialog.this);
                        i++;
                    }
                    break;
            }
        }
    };
    
    private void setWeeks() {
        LinearLayout.LayoutParams layoutp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                (int) getContext().getResources().getDimension(R.dimen.m50dp));
        layoutp.setMargins(0, 0, 1, 1);
        layoutp.weight = 1;
        for (int i = 0; i < 5; i++) {
            LinearLayout layout = new LinearLayout(getContext());
            for (int k = 0; k < 5; k++) {
                CheckBox ck = new CheckBox(getContext());
                listbox.add(ck);
                ck.setButtonDrawable(R.drawable.default_bt_tra_background);
                if ((i * 5 + k + 1) < 10) {
                    ck.setText("0" + (i * 5 + k + 1));
                } else {
                    ck.setText("" + (i * 5 + k + 1));
                }
                ck.setChecked(true);
                ck.setOnCheckedChangeListener(this);
                ck.setGravity(Gravity.CENTER);
                ck.setTextColor(getContext().getResources().getColorStateList(R.drawable.ck_grensel_tv));
                ck.setPadding(0, 0, 0, 0);
                ck.setBackgroundResource(R.drawable.ck_grensel);
                layout.addView(ck, layoutp);
            }
            weeksLay.addView(layout);
        }
    }
    
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean isd = true, iss = true, isqb = true;
        for (int i = 0; i < listbox.size(); i++) {
            CheckBox cb = listbox.get(i);
            if (!cb.isChecked()) {
                isqb = false;
                if (i % 2 == 1) {
                    isd = false;
                } else {
                    iss = false;
                }
            } else {
                if (i % 2 == 1) {
                    iss = false;
                } else {
                    isd = false;
                }
            }
        }
        checked.setOnCheckedChangeListener(null);
        checked.clearCheck();
        if (isqb) {
            checked.check(R.id.qb);
        } else if (isd) {
            checked.check(R.id.dz);
        } else if (iss) {
            checked.check(R.id.sz);
        }
        checked.setOnCheckedChangeListener(checklistener);
    }
    
    @Override
    public void show() {
        super.show();
        for (int i = 0; i < listbox.size(); i++) {
            CheckBox ck = listbox.get(i);
            ck.setOnCheckedChangeListener(null);
            ck.setChecked(false);
            for (int ind : list) {
                if (ind == i) {
                    ck.setChecked(true);
                    break;
                }
            }
            ck.setOnCheckedChangeListener(this);
        }
    }
    
    public interface OnSelected {
        public void onSelected(Dialog dia, List<Integer> list);
    }
}
