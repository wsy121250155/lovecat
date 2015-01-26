package com.mobile.zsdx.schedule;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mobile.api.proto.MSystem.MAccountInfo;
import com.mobile.api.proto.MSystem.MClassList;
import com.mobile.base.R;
import com.mobile.base.cache.DataCache;
import com.mobile.base.dialog.MDialog;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.Helper;
import com.mobile.base.util.MLog;
import com.mobile.base.widget.views.MImageView;
import com.mobile.zsdx.util.F;

public class JWLoginDialog extends MDialog {
	private MAccountInfo ainfo = new MAccountInfo(); //账户信息
	private String account, password;
		private View tuichu, queren, verifyLayout;
	    
	    private EditText xuehao, mima, verify;
	    
	    private CheckBox zdmi;
	    
	    private MImageView image;
	    
	    private ScheduleFragment mScheduleFragment;

	    public JWLoginDialog(Context context, ScheduleFragment scheduleFragment) {
	        super(context, R.style.Dialog);
	        
	        this.mScheduleFragment = scheduleFragment;
	    }
	    
	    public void setImg(String data){
	        showImageload(data);
	    }
	    
	    public JWLoginDialog(Context context, int theme) {
	        super(context, theme);
	    }
	    
	    public void create(Bundle savedInstanceState) {
	    	JWLoginDialog.this.setContentView(R.layout.dialog_jw_login);
	        tuichu = findViewById(R.id.dialog_tuichu);
	        queren = findViewById(R.id.dialog_chaxun);
	        xuehao = (EditText) findViewById(R.id.dialog_xuehao);
	        mima = (EditText) findViewById(R.id.dialog_mima);
	        verify = (EditText) findViewById(R.id.verify);
	        zdmi = (CheckBox) findViewById(R.id.dialog_zidongim);
	        image = (MImageView) findViewById(R.id.image);
	        verifyLayout = findViewById(R.id.verifyLayout);
	        verifyLayout.setVisibility(View.GONE);
	        //读取账号密码信息缓存
	        DataCache.readData("jwainfo", ainfo, getContext());
	        if(!TextUtils.isEmpty(ainfo.getAccount())) {
	        	xuehao.setText(ainfo.getAccount());
	        	mima.setText(ainfo.getPassword());
	        }
	        
	        tuichu.setOnClickListener(new View.OnClickListener() {
	            
	            @Override
	            public void onClick(View v) {
	                if (context instanceof Activity) {
	                    dismiss();
	                }
	            }
	        });
	        
	        queren.setOnClickListener(new View.OnClickListener() {
	            
	            @Override
	            public void onClick(View v) {
	                dataload();
	            }
	        });
	    }
	    
	    public void showImageload(String data){ //接收base64编码
	        verifyLayout.setVisibility(View.VISIBLE);
	        image.setImageBase64(data);
	    }
	    
	    public void getSchedule(Bean2Json data) {
	    	MClassList mclass = (MClassList)data;
	    	MLog.d(mclass.toString());
	        if (!TextUtils.isEmpty(mclass.getImg1())) {
	            showImageload(mclass.getImg1());
	            return;
	        }
	        F.colorind=new Random().nextInt();

            mScheduleFragment.addScheduleContent(mclass);
            if (zdmi.isChecked()) {
            	ainfo.setAccount(account);
            	ainfo.setPassword(password);
                DataCache.saveData("jwainfo", ainfo, getContext());
            }
            
            dismiss();
	    }
	    
	    private void dataload() {
	    	account = xuehao.getText().toString();
	        password = mima.getText().toString();
	        String ver = verify.getText().toString();
	        if (TextUtils.isEmpty(account)) {
	            Helper.toast(getContext(), "请输入学号");
	            return;
	        }
	        if (TextUtils.isEmpty(password)) {
	            Helper.toast(getContext(), "请输入密码");
	            return;
	        }
	        if (TextUtils.isEmpty(ver) && verifyLayout.getVisibility() == View.VISIBLE) {
	            Helper.toast(getContext(), "请输入校验码");
	            return;
	        }
	        if (TextUtils.isEmpty(ver)) {
	            ver = null;
	        }
	        ApiClientFactory.getScheduleApi(getContext(), this, "getSchedule", account, password, ver);
	    }
	    
}
