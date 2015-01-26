package com.mobile.base.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

import com.mobile.base.commons.Serialize;
import com.mobile.base.model.Bean2Json;
import com.mobile.base.util.MLog;

public class DataCache {
	private static String PATH = "data";
	public static void saveData(String key, Object data, Context context) {
		try {
			File file = CacheUtil.getPath(context, PATH, key);
			
			FileOutputStream fos = new FileOutputStream(file);
			
			Serialize.serialize(data, fos);
		} catch (Exception e) {
			
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void readData(String key, Bean2Json data, Context context) {
		try {
			File file = CacheUtil.getPath(context, PATH, key);
			
			FileInputStream fis;
			
			fis = new FileInputStream(file);

			//这里就用到了对象值拷贝方法
			data.build((Bean2Json)Serialize.unserialize(fis));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}
