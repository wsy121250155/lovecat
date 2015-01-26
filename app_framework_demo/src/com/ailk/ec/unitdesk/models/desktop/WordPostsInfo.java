/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ailk.ec.unitdesk.models.desktop;

import java.io.Serializable;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Represents a launchable application. An application is made of a name (or
 * title), an intent and an icon.
 */
public class WordPostsInfo implements Comparable, Serializable {

	/**
	 * The application screen index.
	 */
	public int screenIndex;

	/**
	 * The application time 1. standard , 2. two * standard
	 */
	public int time;

	/**
	 * The application col.
	 */
	public int col;

	/**
	 * The application row.
	 */
	public int row;

	/**
	 * The application index in queue.
	 */
	public int index;

	/**
	 * The application bg color resource id.
	 */
	public int colorId;

	/**
	 * The application icon resouce id.
	 */
	public int iconId;

	public int iconId2;

	public int iconId3;

	/**
	 * Jump to the url when click the application.
	 */
	public String toUrl;

	public String currentUrl;

	/**
	 * The application name.
	 */
	public String title;

	public String title2;

	public String title3;

	/**
	 * The application description.
	 */
	public String description;

	/**
	 * The intent used to start the application.
	 */
	Intent intent;

	/**
	 * The application icon.
	 */
	Drawable icon;

	/**
	 * When set to true, indicates that the icon has been resized.
	 */
	boolean filtered;

	boolean isSigleCol;

	public int postsId;

	public WordPostsInfo(int screenIndex, int time, int col, int row,
			int index, int colorId, int iconId, int iconId2, int iconId3,
			String toUrl, String title, String title2, String title3,
			String description, int postsId) {
		this(screenIndex, time, col, row, index, colorId, iconId, toUrl, title,
				description, postsId);
		this.iconId2 = iconId2;
		this.iconId3 = iconId3;
		this.title2 = title2;
		this.title3 = title3;
	}

	public WordPostsInfo(int screenIndex, int time, int col, int row,
			int index, int colorId, int iconId, String toUrl,
			String currentUrl, String title, String description, int postsId) {

		this(screenIndex, time, col, row, index, colorId, iconId, toUrl, title,
				description, postsId);
		this.currentUrl = currentUrl;
	}

	public WordPostsInfo(int screenIndex, int time, int col, int row,
			int index, int colorId, int iconId, String toUrl, String title,
			String description, int postsId) {
		super();
		this.screenIndex = screenIndex;
		this.time = time;
		this.col = col;
		this.row = row;
		this.index = index;
		this.colorId = colorId;
		this.iconId = iconId;
		this.toUrl = toUrl;
		this.title = title;
		this.description = description;
		this.postsId = postsId;
	}

	public WordPostsInfo(int screenIndex, int time, int col, int row,
			int index, int colorId, int iconId, String toUrl, String title,
			String description) {
		super();
		this.screenIndex = screenIndex;
		this.time = time;
		this.col = col;
		this.row = row;
		this.index = index;
		this.colorId = colorId;
		this.iconId = iconId;
		this.toUrl = toUrl;
		this.title = title;
		this.description = description;
	}

	public WordPostsInfo() {
		super();
	}

	/**
	 * Creates the application intent based on a component name and various
	 * launch flags.
	 * 
	 * @param className
	 *            the class name of the component representing the intent
	 * @param launchFlags
	 *            the launch flags
	 */
	final void setActivity(ComponentName className, int launchFlags) {
		intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setComponent(className);
		intent.setFlags(launchFlags);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WordPostsInfo)) {
			return false;
		}

		WordPostsInfo that = (WordPostsInfo) o;
		return postsId == that.postsId;
	}

	@Override
	public int hashCode() {
		int result;
		result = (title != null ? title.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(Object another) {

		int result = 0;
		if (another instanceof WordPostsInfo) {
			WordPostsInfo posts = (WordPostsInfo) another;
			result = this.index - posts.index;
		}

		return result;
	}
}
