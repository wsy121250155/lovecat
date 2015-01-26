package com.mobile.zsdx.util;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class F {
	public static String USERID = "";
	public static int colorind = 0; 
	public static int[] Colors = new int[] { 0xffe37afa, 0xffff6296, 0xffffa9a5, 0xffffca85, 0xffffcd1e, 0xff4ba8ff,
        0xff8f80ff, 0xff00c4e7, 0xff76e9bc, 0xff9cd87b, 0xffCC9999 };
	public static String Account;
	public static Drawable[] colorDrawable = new StateListDrawable[Colors.length];
	
	static {
        for (int i = 0; i < Colors.length; i++) {
            StateListDrawable sd = new StateListDrawable();
            sd.addState(new int[] { android.R.attr.state_pressed }, new ColorDrawable((Colors[i] - 0x00202020)));
            sd.addState(new int[] {}, new ColorDrawable(Colors[i]));
            colorDrawable[i] = sd;
        }
    }
	

	
    public static Drawable getColorDrawable(int ind) {
        return colorDrawable[Math.abs(ind % colorDrawable.length)];
    }
	
	public static int getColor(int ind) {
        return Colors[Math.abs(ind % Colors.length)];
    }
	

}
