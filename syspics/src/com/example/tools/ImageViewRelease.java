package com.example.tools;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageViewRelease {
	public static void releaseImageView(ImageView imageView) {
		Drawable d = imageView.getDrawable();
		if (d != null)
			d.setCallback(null);
		imageView.setImageDrawable(null);
		imageView.setBackgroundDrawable(null);
	}
}
