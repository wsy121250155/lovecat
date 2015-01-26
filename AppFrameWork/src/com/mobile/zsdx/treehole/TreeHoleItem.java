package com.mobile.zsdx.treehole;

import java.util.HashMap;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobile.api.proto.MAppTreeHole.MTopic;
import com.mobile.api.proto.MSystem.MRet;
import com.mobile.base.R;
import com.mobile.base.http.ApiClientFactory;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.Helper;
import com.mobile.base.widget.views.MImageView;
import com.mobile.zsdx.util.F;

public class TreeHoleItem extends FrameLayout{

	
	private TextView mtag, mdetail, newCommons;
	
	private MImageView mimage, topimage;
	
    private Button bcomments;
    
    private ImageButton bsecretMail, bmore;
    
    private CheckBox bsaygood;
    
    private View devidview, allcommons, devidviewend, layout;
    
    private MTopic mdata;
	
    private Context mcontext;
	public TreeHoleItem(Context context) {
		super(context);
		this.mcontext = context;
		init();
	}
	
	public void init(){
		LayoutInflater lf = LayoutInflater.from(getContext());
		lf.inflate(R.layout.treeholeitem, this);
        mtag = (TextView) findViewById(R.id.tag);
        mdetail = (TextView) findViewById(R.id.detail);
        mimage = (MImageView) findViewById(R.id.image);
        topimage = (MImageView) findViewById(R.id.topimage);
        bsaygood = (CheckBox) findViewById(R.id.saygood);
        bcomments = (Button) findViewById(R.id.comments);
        bsecretMail = (ImageButton) findViewById(R.id.secretMail);
        bmore = (ImageButton) findViewById(R.id.more);
        devidview = findViewById(R.id.dvidview);
        allcommons = findViewById(R.id.allCommons);
        devidviewend = findViewById(R.id.dvidviewend);
        layout = findViewById(R.id.layout);
        newCommons = (TextView) findViewById(R.id.newCommons);
		
	}
	
	public void setdata(final MTopic data){
		
		mdata = data;
		
		if(!TextUtils.isEmpty(data.getTag())){
			this.mtag.setVisibility(View.VISIBLE);
			this.mtag.setText("#"+data.getTag());
		}else{
			this.mtag.setVisibility(View.GONE);
		}
		
		if(data.getCommentCnt() > 99){
			this.bcomments.setText(99+"+");
		}else {
			this.bcomments.setText(data.getCommentCnt() + "");
		}
		
		this.mdetail.setText(data.getContent());
		this.bsaygood.setOnCheckedChangeListener(null);
		this.bsaygood.setChecked(data.getHasPraise() == 1 ? true : false);
		this.bsaygood.setText(data.getPraiseCnt() + "");
		bsaygood.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean state) {
				ApiClientFactory.treeHolePraise(getContext(),
						TreeHoleItem.this,
						"sayGood",
						data.getId(),
						state ? 1 : 2);
			}
		});
		
		if(!TextUtils.isEmpty(data.getImg())){
			this.mimage.setVisibility(View.VISIBLE);
			this.mimage.setImage(data.getImg(),640,440);
		}else{
			this.mimage.setVisibility(View.GONE);
		}
		
		bcomments.setClickable(false);
		bcomments.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("thitem", data);
 				
				try {
					TreeHoleActivity.showFragment(THDetailFragment.class.newInstance(), param);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bsecretMail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(data.getAuthor().equals(F.USERID)){
					Helper.toast(getContext(), "不能私信自己");
					return;
				}
				bsecretMail.setEnabled(false);
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	public void sayGood(Bean2Json ret){
		MRet mret = (MRet)ret;
		Helper.toast(mcontext, "code为" + mret.getCode());
		if(1 == mret.getCode()){
			if(mdata.getHasPraise() == 0){
				mdata.setHasPraise(1);
				bsaygood.setText((Integer.valueOf(bsaygood.getText().toString()) + 1) + "");
			} else {
	            mdata.setHasPraise(0);
	            bsaygood.setText((Integer.valueOf(bsaygood.getText().toString()) - 1) + "");
			}
				
		}	
	}

}
