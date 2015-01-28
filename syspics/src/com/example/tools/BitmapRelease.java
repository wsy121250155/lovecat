package com.example.tools;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class BitmapRelease {
	public static void recycleBitmap(Bitmap bitmap) {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
		}
	}

	public static void recycleBitmap(ImageView imageView) {
		if (imageView != null) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
					.getDrawable();
			if (bitmapDrawable != null) {
				Bitmap bitmap = bitmapDrawable.getBitmap();
				recycleBitmap(bitmap);
			}
		}
	}
}
