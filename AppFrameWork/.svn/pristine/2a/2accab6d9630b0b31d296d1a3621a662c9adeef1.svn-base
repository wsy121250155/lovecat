package com.mobile.base.util;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonUtils {
	/**
	 * 解析byte数组
	 * @param bytes
	 * @return
	 * @throws JSONException
	 */
	public static byte[] getByteArray(JSONArray bytes) throws JSONException {
		byte[] data = new byte[0];
		
		if(bytes != null) {
			int len = bytes.length();
			data = new byte[len];
			for(int i = 0; i < len; i++) {
				data[i] = (byte)(bytes.getInt(i));
			}
		}
		MLog.d(Arrays.toString(data));
		return data;
	}
}
