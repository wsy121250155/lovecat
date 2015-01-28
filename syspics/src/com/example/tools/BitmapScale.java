package com.example.tools;

import com.example.global.ViewPicSize;

import android.graphics.Bitmap;

public class BitmapScale {
	// scale to the width=match_window;height=width
	public static Bitmap scale(Bitmap bitmap) {
		ViewPicSize picSize = ViewPicSize.getInstance();
		int dstWidth = picSize.getPreViewWidth();
		int dstHeight = picSize.getPreViewHeight();
		Bitmap result = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight,
				true);
		BitmapRelease.recycleBitmap(bitmap);
		return result;
	}
}
