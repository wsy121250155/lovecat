package com.mobile.zsdx.treehole;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdx.framework.widget.MPageListView;
import com.mobile.base.MFragment;
import com.mobile.base.R;

public class MyNewsFragment extends MFragment{
    private MPageListView mListViewsx, mListViewpl;
    
    private View msx, mpl;
    
    private TextView sxnum, plnum;
    
	@Override
	protected void create(Bundle savedInstanceState) {
		super.create(savedInstanceState);
	    setContentView(R.layout.frg_talsing);
	        
	    mListViewsx = (MPageListView) findViewById(R.id.listviewsx);
	    mListViewpl = (MPageListView) findViewById(R.id.listviewpl);
	    msx = findViewById(R.id.sx);
	    mpl = findViewById(R.id.pl);
	    sxnum = (TextView) findViewById(R.id.sxnum);
	    plnum = (TextView) findViewById(R.id.plnum);
        msx.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mListViewsx.setVisibility(View.VISIBLE);
                mListViewpl.setVisibility(View.GONE);
                msx.setBackgroundResource(R.drawable.default_bt_background_wight);
                mpl.setBackgroundResource(R.drawable.bt_background_gray);
            }
        });
        
        mpl.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mListViewpl.setVisibility(View.VISIBLE);
                mListViewsx.setVisibility(View.GONE);
                mpl.setBackgroundResource(R.drawable.default_bt_background_wight);
                msx.setBackgroundResource(R.drawable.bt_background_gray);
            }
        });
        
	}

}
