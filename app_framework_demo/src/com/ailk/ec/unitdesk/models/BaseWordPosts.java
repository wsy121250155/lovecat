package com.ailk.ec.unitdesk.models;

import java.io.Serializable;
import java.util.List;

import com.ailk.ec.unitdesk.models.http.ReInstType;

public class BaseWordPosts implements Comparable, Serializable {

	/**
	 * The cate screen index.
	 */
	public int screenIndex;

	/**
	 * The cate time 1(2*2). standard , 2(4*2). two * standard , 4(4*4). four *
	 * standard
	 */
	public int time;

	/**
	 * The cate col.
	 */
	public int col;

	/**
	 * The cate row.
	 */
	public int row;

	/**
	 * The cate index in queue ; the group index in cate.
	 */
	public int index;

	/**
	 * The cate bg color resource id.
	 */
	public String bgColor;

	/**
	 * The cate icon resouce id.
	 */
	public int iconId;

	public String iconName;

	/**
	 * The cate name.
	 */
	public String title;

	/**
	 * The cate description.
	 */
	public String description;

	/**
	 * The cate id.
	 */
	public long postsId;

	/**
	 * The parent id.
	 */
	public long parentId;

	/**
	 * Jump to the url when click the application.
	 */
	public String toUrl;

	public String currentUrl;

	public int startY;

	public int endY;

	public int startX;

	public int endX;

	public String paramFormat;
	public String clickUrl;
	public String defShowUrl;
	public String showSize;
	public String realShowSize;
	public int funcId;
	public String serviceCode;
	public long groupId;
	/**
	 * 门当层入参
	 */
	public String serviceParam;
	public String insttypeId;

	public List<ReInstType> reInstType;

	public BaseWordPosts parentObj;

	/**
	 * The posts index in group.
	 */
	public int postsIndex;

	/**
	 * 推荐分类标识
	 */
	public boolean isRecommend;

	/**
	 * 第三方app接入参数
	 */
	public String clientUri;
	public String bindAccountServiceCode;
	public String appDownloadAddress;
	public String funcName;
	/**
	 * 0和null不可重复，1可重复
	 */
	public String isRepeat;

	public BaseWordPosts(int screenIndex, int time, int col, int row,
			int index, int postsIndex, String bgColor, int iconId,
			String title, String description, long postsId, String paramFormat,
			String clickUrl, String iconName, String defShowUrl, int funcId,
			String showSize, String realShowSize, String serviceCode,
			long groupId, String insttypeId, String serviceParam,
			String clientUri, String funcName, String bindAccountServiceCode,
			String appDownloadAddress, String isRepeat) {
		this(screenIndex, time, col, row, index, bgColor, iconId, title,
				description, postsId, paramFormat, clickUrl, iconName,
				defShowUrl);
		this.postsIndex = postsIndex;
		this.funcId = funcId;
		this.showSize = showSize;
		this.realShowSize = realShowSize;
		this.serviceCode = serviceCode;
		this.groupId = groupId;
		this.insttypeId = insttypeId;
		this.serviceParam = serviceParam;

		if ("41".equals(this.showSize)) {
			this.time = 1;
		} else if ("42".equals(showSize)) {
			this.time = 2;
		} else if ("44".equals(showSize)) {
			this.time = 4;
		}
		this.clientUri = clientUri;
		this.funcName = funcName;
		this.bindAccountServiceCode = bindAccountServiceCode;
		this.appDownloadAddress = appDownloadAddress;
		this.isRepeat = isRepeat;
	}

	public BaseWordPosts(int screenIndex, int time, int col, int row,
			int index, String bgColor, int iconId, String title,
			String description, long postsId, String paramFormat,
			String clickUrl, String iconName, String defShowUrl) {
		this(screenIndex, time, col, row, index, bgColor, iconId, title,
				description, postsId);
		this.paramFormat = paramFormat;
		this.clickUrl = clickUrl;
		this.iconName = iconName;
		this.defShowUrl = defShowUrl;
	}

	/**
	 * 构造分类
	 * 
	 * @param screenIndex
	 * @param time
	 * @param col
	 * @param row
	 * @param index
	 * @param bgColor
	 * @param iconId
	 * @param title
	 * @param description
	 * @param postsId
	 */
	public BaseWordPosts(int screenIndex, int time, int col, int row,
			int index, String bgColor, int iconId, String title,
			String description, long postsId) {
		this(screenIndex, time, col, row, index, bgColor, iconId, title,
				description, postsId, false);
	}

	/**
	 * 构造分类
	 * 
	 * @param screenIndex
	 * @param time
	 * @param col
	 * @param row
	 * @param index
	 * @param bgColor
	 * @param iconId
	 * @param title
	 * @param description
	 * @param postsId
	 * @param isRecommend
	 */
	public BaseWordPosts(int screenIndex, int time, int col, int row,
			int index, String bgColor, int iconId, String title,
			String description, long postsId, boolean isRecommend) {
		super();
		this.screenIndex = screenIndex;
		this.time = time;
		this.col = col;
		this.row = row;
		this.index = index;
		this.bgColor = bgColor;
		this.iconId = iconId;
		this.title = title;
		this.description = description;
		this.postsId = postsId;
		this.isRecommend = isRecommend;
	}

	public BaseWordPosts(int screenIndex, int time, int col, int row,
			int index, String bgColor, int iconId, String title,
			String description, String currentUrl, String toUrl, long postsId) {
		this(screenIndex, time, col, row, index, bgColor, iconId, title,
				description, postsId);
		this.currentUrl = currentUrl;
		this.toUrl = toUrl;
	}

	public BaseWordPosts() {
		super();
	}

	@Override
	public int compareTo(Object another) {
		int result = 0;
		if (another instanceof BaseWordPosts) {
			BaseWordPosts cate = (BaseWordPosts) another;
			if (this.groupId == cate.groupId && this.groupId > 0) {
				result = this.postsIndex - cate.postsIndex;
			} else {
				result = this.index - cate.index;
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BaseWordPosts)) {
			return false;
		}

		BaseWordPosts that = (BaseWordPosts) o;
		return postsId == that.postsId;
	}

}
