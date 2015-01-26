package com.ailk.ec.unitdesk.models.http;

public class PostsInst {

	public long instId;
	public int sortNum;
	public String instName;
	public String iosLocation;
	public String androidLocation;
	public String countIntervalTime;
	public String showSize;
	public String serviceCode;
	public FuncTmp funcTmp;
	public long categoryId;
	public long groupId;

	public DesktopInstType desktopInstType;

	public PostsInst(long instId, long groupId, int sortNum, long categoryId) {
		super();
		this.instId = instId;
		this.groupId = groupId;
		this.sortNum = sortNum;
		this.categoryId = categoryId;
	}

	public PostsInst() {
		super();
	}

}
