/**
 * 
 */
package com.ailk.ec.unitdesk.web.jsinterface;

import android.content.Context;

/**
 * @Description:
 * @version V1.0
 * @date 2013-11-8
 */

public class JavaScriptObject {
	Context mContxt;

	public JavaScriptObject(Context mContxt) {
		this.mContxt = mContxt;
	}

	public void syncDailCalendar(String name) {
		// CalendarSyncInstance.instance((Activity) mContxt).syncCal();
	}

	public String upLoadPic(String name) {
		return name;
		// CalendarSyncInstance.instance((Activity) mContxt).syncCal();
	}

}
