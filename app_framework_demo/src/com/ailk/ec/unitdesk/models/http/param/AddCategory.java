package com.ailk.ec.unitdesk.models.http.param;

public class AddCategory {

	public String categoryName;
	public String androidLocation;
	public int sortNum;

	public AddCategory(String categoryName, String androidLocation, int sortNum) {
		super();
		this.categoryName = categoryName;
		this.androidLocation = androidLocation;
		this.sortNum = sortNum;
	}

}
