package com.mobile.zsdx.treehole;

import com.mobile.api.proto.MSystem.MRet;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.Helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

public class InputDialog extends Dialog{

    public View submit;
    
    public CheckBox islz;
    
    public EditText edittext;
    
    private String mid, replayid;
    
    private int floor,islzn;
    
    private Activity context;
    
    private OnCommentedListener onCommentedListener;
    
	private Context mcontext;
	
	public InputDialog(Context context) {
		super(context, R.style.Dialog);
		this.mcontext = context;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setGravity(Gravity.CENTER | Gravity.BOTTOM);
        getWindow().setAttributes(lp);
        
        Window window = getWindow(); // 得到对话框
        window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
        WindowManager.LayoutParams wl = window.getAttributes();
        window.setAttributes(wl);
	}
	
	public InputDialog(Context context, int theme){
		super(context, theme);
	}
	
    public void set(String id, String replayid, int floor,int islz) {
        this.mid = id;
        this.replayid = replayid;
        this.floor = floor ;
        islzn=islz;
        if (edittext != null) {
            if (this.floor == 0) {
                edittext.setHint("说点什么吧");
            } else {
                edittext.setHint("回复" + this.floor + "楼");
            }
        }
    }
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_treehold_comminput);
        edittext = (EditText) findViewById(R.id.edittext);
        submit = findViewById(R.id.submit);
        islz = (CheckBox) findViewById(R.id.islz);
        if (this.floor == 0) {
            edittext.setHint("评论不超过120字");
        } else {
            edittext.setHint("回复" + this.floor + "楼");
        }
        if(islzn==1){
            islz.setVisibility(View.VISIBLE);
        }else{
            islz.setVisibility(View.INVISIBLE);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edittext.getText().toString();
                if (str.length() == 0) {
                    Helper.toast(getContext(),"您没有输入任何东西");
                    return;
                }
                submit.setEnabled(false);
                
                ApiClientFactory.announceComments(mcontext, InputDialog.this,
                		"addComment", mid, str, islzn, replayid, floor);
            }
        });
	}
	
	@SuppressWarnings("rawtypes")
	public void addComment(Bean2Json ret){
		MRet mRet = (MRet) ret;
		if(mRet.getCode() == 1){
			Helper.toast(getContext(), "发布成功");
		}
	}
    
    public void setOnCommentedListener(OnCommentedListener onCommentedListener) {
        this.onCommentedListener = onCommentedListener;
    }
    
    public interface OnCommentedListener {
        public void onCommentedListener();
    }
}
