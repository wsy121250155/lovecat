package com.ailk.ec.unitdesk.security;

import com.ailk.ec.unitdesk.utils.Log;

public class MD5
{

	/**
	 * 给字符串md5加密
	 * 
	 * @param inStr
	 *            要加密的字符串
	 * @return 加密好的字符串
	 */
	public static String md5(String myinfo)
	{
		byte[] digesta = null;
		try
		{
			java.security.MessageDigest alga = java.security.MessageDigest
					.getInstance("MD5");
			alga.update(myinfo.getBytes());
			digesta = alga.digest();

		}
		catch (java.security.NoSuchAlgorithmException ex)
		{
			Log.e("非法摘要算法");
		}
		return byte2hex(digesta);
	}
	
	
	public static String byte2hex(byte[] b)
	{ // 二行制转字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
			{
				hs = hs + "0" + stmp;
			}
			else
			{
				hs = hs + stmp;
			}
			if (n < b.length - 1)
			{
				hs = hs;
			}
		}
		return hs;
	}
}
