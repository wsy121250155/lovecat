package com.ailk.ec.unitdesk.models.http;

import java.util.List;

public class CategoryContent {

	public long categoryId;
	public long categoryDefaultId;
	public String categoryName;
	public String categoryDesc;
	public int sortNum;
	public String iosLocation;
	public String androidLocation;
	public List<PostsGroup> groupList;

	public CategoryContent(long categoryId, String categoryName, int sortNum) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.sortNum = sortNum;
	}

	public CategoryContent() {
		super();
	}

}
