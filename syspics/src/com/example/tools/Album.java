package com.example.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Album {
	public static Bitmap getAlbumBitmap(String picturePath) {
		if (picturePath.equals(""))
			return null;
		Bitmap cont = BitmapFactory.decodeFile(picturePath);
		// 存在如下问题：如果是手机照片，会有90度的角度翻转
		if (picturePath.contains("Camera")) {
			Bitmap result = RotateBitmap.adjustPhotoRotation(cont, 90);
			return result;
		}
		return cont;
	}
}
