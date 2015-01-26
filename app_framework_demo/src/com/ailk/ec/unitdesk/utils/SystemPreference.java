package com.ailk.ec.unitdesk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ailk.ec.unitdesk.datastore.Constants;

/* =========================================================
 * 
 * @version V1.0
 * =========================================================
 */
public class SystemPreference
{

	public static String getString(Context ctx, String settingName)
	{

		SharedPreferences myPreference = ctx.getSharedPreferences(
				Constants.getInstance().PREFS_NAME, 0);
		String strReturn = myPreference.getString(settingName, null);
		if (strReturn != null)
		{
			strReturn = Constants.getInstance().des.decode(strReturn);
		}

		return strReturn;
	};

	public static int getInt(Context ctx, String settingName)
	{

		SharedPreferences myPreference = ctx.getSharedPreferences(
				Constants.getInstance().PREFS_NAME, 0);

		return myPreference.getInt(settingName, 0);
	};

	public static Boolean getBoolean(Context ctx, String settingName)
	{

		SharedPreferences myPreference = ctx.getSharedPreferences(
				Constants.getInstance().PREFS_NAME, 0);

		return myPreference.getBoolean(settingName, false);
	};

	public static Boolean setString(Context ctx, String settingName,
			String value)
	{
		SharedPreferences myPreference = ctx.getSharedPreferences(
				Constants.getInstance().PREFS_NAME, 0);

		try
		{
			myPreference
					.edit()
					.putString(settingName,
							Constants.getInstance().des.encode(value)).commit();
			return true;
		} catch (Exception E)
		{
			return false;
		}
	};

	public static Boolean setInt(Context ctx, String settingName, int value)
	{
		SharedPreferences myPreference = ctx.getSharedPreferences(
				Constants.getInstance().PREFS_NAME, 0);

		try
		{
			myPreference.edit().putInt(settingName, value).commit();
			return true;
		} catch (Exception E)
		{
			return false;
		}
	};

	public static Boolean setBoolean(Context ctx, String settingName,
			Boolean value)
	{
		SharedPreferences myPreference = ctx.getSharedPreferences(
				Constants.getInstance().PREFS_NAME, 0);
		try
		{
			myPreference.edit().putBoolean(settingName, value).commit();
			return true;
		} catch (Exception E)
		{
			return false;
		}
	};

    public static void remove(Context ctx, String settingName)
    {
        SharedPreferences myPreference = ctx.getSharedPreferences(
                Constants.getInstance().PREFS_NAME, 0);
        myPreference.edit().remove(settingName).commit();
    };

}