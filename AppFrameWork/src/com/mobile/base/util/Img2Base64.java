package com.mobile.base.util;

import java.io.ByteArrayOutputStream;

import com.mobile.base.commons.verify.Base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
/*
 * 将图片转化成base64
 */
public class Img2Base64 {

	public static String imgToBase64(String imgPath, Bitmap bitmap){
		if(imgPath != null && imgPath.length() > 0){
			bitmap = readBitmap(imgPath);
		}
		if(bitmap == null){
			//bitmap not found
		}
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			
			out.flush();out.close();
			byte[] imgBytes = out.toByteArray();
			String str = Base64.encodeToString(imgBytes, Base64.DEFAULT).replace("\r", "").replace("\n", "");
			return str;
		} catch (Exception e) {
			e.printStackTrace();return null;
		} finally {
			try {
				out.flush();out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private static Bitmap readBitmap(String imgPath){
		try {
			return BitmapFactory.decodeFile(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
