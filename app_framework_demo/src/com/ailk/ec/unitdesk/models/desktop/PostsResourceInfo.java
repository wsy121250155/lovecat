package com.ailk.ec.unitdesk.models.desktop;

import java.io.Serializable;

public class PostsResourceInfo implements Serializable {

	public int postsColorId;
	public int postsIconId;
	public int postsIconId2;
	public int postsIconId3;
	public String currentUrl;
	public String toUrl;
	public String title2;
	public String title3;

	public PostsResourceInfo(int postsColorId, int postsIconId) {
		super();
		this.postsColorId = postsColorId;
		this.postsIconId = postsIconId;
	}

	public PostsResourceInfo(int postsColorId, int postsIconId, String toUrl,
			String currentUrl) {
		this(postsColorId, postsIconId);
		this.toUrl = toUrl;
		this.currentUrl = currentUrl;
	}

	public PostsResourceInfo(int postsColorId, int postsIconId,
			int postsIconId2, int postsIconId3, String title2, String title3) {
		this(postsColorId, postsIconId);
		this.postsIconId2 = postsIconId2;
		this.postsIconId3 = postsIconId3;
		this.title2 = title2;
		this.title3 = title3;
	}

	public PostsResourceInfo() {
		super();
	}

}
