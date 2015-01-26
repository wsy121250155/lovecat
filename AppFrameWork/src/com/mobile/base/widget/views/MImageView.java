package com.mobile.base.widget.views;


import com.mobile.base.commons.verify.Base64;
import com.mobile.base.util.ConstantResource;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/*
 * 封装了通过路径加载图片的imageView
 */
public class MImageView extends ImageView {

	public MImageView(Context context) {
		super(context);
		finalBitmap = FinalBitmap.create(context);
	}
	
	public MImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		finalBitmap = FinalBitmap.create(context);
	}

	private String totalUrl;
	private FinalBitmap finalBitmap;

	public void setImage(String url){
		if(!url.equals("")){
			if(url.startsWith("/storage/")){
				totalUrl = url;
			}else {
				totalUrl = ConstantResource.DOWNLOAD_URL + "?id="+url;
			}	
			Log.d("MYAPP", totalUrl);
			finalBitmap.display(this, totalUrl);
		}

	}
	
	public void setImage(String url,int width,int height){
		if(url.startsWith("/storage/")){
			totalUrl = url;
		}else {
			totalUrl = ConstantResource.DOWNLOAD_URL + "?id="+url;
		}
		Log.d("MYAPP", totalUrl);
		finalBitmap.display(this, totalUrl, width, height);
	}
	
	public void setImageBase64(String data) {
		byte[] bytes = Base64.decode(data, Base64.DEFAULT);
		this.setImageBitmap(bytes2Bm(bytes));
	}
	
	public Bitmap bytes2Bm(byte[] b) {
		if (b == null) {
			b = new byte[1];
		}
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

}