package com.ailk.ec.unitdesk.web.jsinterface;

import android.app.Activity;
import android.webkit.JsResult;

import com.ailk.ec.unitdesk.web.WebPageView;



public interface IWebViewListener 
{
	public void onProgressChange(WebPageView web, int newProgress);
	public void onLoadingFinish(WebPageView web , final Activity activity);
	public boolean onAlert(String message, final JsResult result);
	public boolean onConfirm(String message, final JsResult result);
}
