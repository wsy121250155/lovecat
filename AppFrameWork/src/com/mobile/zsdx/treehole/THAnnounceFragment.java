package com.mobile.zsdx.treehole;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobile.api.proto.MSystem.MRet;
import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.ConstantResource;
import com.mobile.base.util.Helper;
import com.mobile.base.util.Img2Base64;
import com.mobile.base.util.MLog;
import com.mobile.base.widget.photo.CameraFile;
import com.mobile.base.widget.views.MImageView;

public class THAnnounceFragment extends MFragment{

    private View taglayout, photolayout, getphotobycam, getphotobyfile, deletePhoto, layout;
    private ScrollView scrollview;
    private TextView tag, small;
    
    private String tagid = "", tagtitle = "", img = "", content = "";
    
    private String imgstr = "";
    
    private MImageView image;


    private EditText editText;
    
    private int scrollHeight=0;
	public void create(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treeholeadd);
		setActionBar();
		
        taglayout = findViewById(R.id.taglayout);
        photolayout = findViewById(R.id.photolayout);
        getphotobycam = findViewById(R.id.getphotobycam);
        getphotobyfile = findViewById(R.id.getphotobyfile);
        scrollview=(ScrollView) findViewById(R.id.scrollview);
        
        image = (MImageView) findViewById(R.id.image);
        deletePhoto = findViewById(R.id.deletePhoto);
        layout = findViewById(R.id.layout);
        editText = (EditText) findViewById(R.id.editText);
        
        editText.performClick();
        
        getphotobycam.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startReceive();
                Intent pick = new Intent(getContext(),CameraFile.class);
                pick.putExtra("gettype", "2");
                pick.putExtra("gettype", 2);
                pick.putExtra("aspectX", 16);
                pick.putExtra("aspectY", 11);
                pick.putExtra("outputX", 640);
                pick.putExtra("outputY", 440);
                getContext().startActivity(pick);
			}
		});
        
        getphotobyfile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startReceive();
                Intent pick = new Intent(getContext(), CameraFile.class);
                pick.putExtra("gettype", "1");
                pick.putExtra("gettype", 1);
                pick.putExtra("aspectX", 16);
                pick.putExtra("aspectY", 11);
                pick.putExtra("outputX", 640);
                pick.putExtra("outputY", 440);
                getContext().startActivity(pick);
			}
		});
        
        deletePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				imgstr = "";
                photolayout.setVisibility(View.GONE);
			}
		});
        
	}
	@SuppressWarnings("rawtypes")
	public void addHole(Bean2Json ret){
		MRet mret = (MRet)ret;
		if(mret.getCode() == 1){
			Helper.toast(getContext(), "发布成功");
		}
	}
	
	public void updatePhoto(Bean2Json ret){
		MRet mret = (MRet) ret;
		if(mret.getCode() == 1){
			Log.d("MYAPP", "图片上传成功");		
			img = mret.getMsg();
			Log.d("MYAPP", "imgid:"+img);
			ApiClientFactory.addTreeHole(getContext(), THAnnounceFragment.this, "addHole", content, tagid, tagtitle, img);
		}
		Log.d("MYAPP", mret.toString());
	}
	
    private void setActionBar() {
    	ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        
        ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.gravity = Gravity.RIGHT;
        LayoutInflater f = LayoutInflater.from(getContext());
        View view = f.inflate(R.layout.item_header_title, null);
        actionBar.setCustomView(view, layout);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        Button button = (Button) view.findViewById(R.id.submit);
        button.setText("发表");
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	MLog.d("deviceid:"+ConstantResource.getDeviceid());
                content = editText.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Helper.toast(getContext() , "没有输入任何内容");
                    return;
                }else if(!imgstr.equals("")){
                	String temp = Img2Base64.imgToBase64(imgstr, null); 
                	ApiClientFactory.updatePhoto(getContext(), THAnnounceFragment.this, "updatePhoto", temp);
                } else {
					ApiClientFactory.addTreeHole(getContext(), THAnnounceFragment.this, "addHole", content, tagid, tagtitle, img);
				}
            }
        });
        view.findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        ((TextView) view.findViewById(R.id.title)).setText("南呱");
    }
    /*
     *  注销广播
     */
    private void endReceive() {
        try {
            getContext().unregisterReceiver(receiver);
        }
        catch (Exception e) {
            
        }
    }
    /*
     * 注册广播
     */
    private void startReceive() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(CameraFile.RECEIVER_PHOTO);
        getContext().registerReceiver(receiver, myIntentFilter);
    }
    
    private BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {

            imgstr = intent.getExtras().getString("what");
            photolayout.setVisibility(View.VISIBLE);
            Log.d("imgurl", imgstr);
            image.setImage(imgstr, 640, 440);
            endReceive();
		}
	};
}
