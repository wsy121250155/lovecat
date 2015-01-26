package com.mobile.zsdx.market;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.mobile.base.MFragment;
import com.mobile.base.widget.pullfreshlist.PullToRefreshListView;

public class MarketFragment extends MFragment {
	private PullToRefreshListView listView;
	
	@Override
	protected void create(Bundle savedInstanceState) {
		ImageView iv = new ImageView(getActivity());
		String url = "http://112.124.12.213:8080/zsndM/download.do?id=69d8b87d46174543aa3fc0f8dbdf5052";
		Bitmap bm = getHttpBitmap(url);
		iv.setImageBitmap(bm);
	}
	
	/* 获取网落图片资源 
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
         
        return bitmap;
         
    }
}
