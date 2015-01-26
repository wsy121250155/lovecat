package com.mobile.base.http;

import java.io.File;

import org.apache.http.Header;

import android.content.Context;

import com.loopj.android.http.FileAsyncHttpResponseHandler;

public class MFileResponseHandler extends FileAsyncHttpResponseHandler {
	

	public MFileResponseHandler(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, File file) {
		// TODO Auto-generated method stub
		
	}
	
}
