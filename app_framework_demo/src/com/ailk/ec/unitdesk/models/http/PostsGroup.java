package com.ailk.ec.unitdesk.models.http;

import java.util.List;

public class PostsGroup {

	public long groupId;
	public long categoryId;
	public String groupName;
	public String groupDesc;
	public int sortNum;
	public String iosLocation;
	public String androidLocation;
	public long groupDefaultId;
	public List<PostsInst> funcTmpInstList;
}
