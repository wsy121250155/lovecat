package com.ailk.ec.unitdesk.models.http.param;

import java.util.List;

public class ArrGroupParam {

	public long categoryId;
	public List<FuncTmpInst> funcTmpInstList;
	public long groupId;
	public long instId;
	public String androidLocation;

	public ArrGroupParam(long categoryId, List<FuncTmpInst> funcTmpInstList,
			long groupId, long instId, String androidLocation) {
		super();
		this.categoryId = categoryId;
		this.funcTmpInstList = funcTmpInstList;
		this.groupId = groupId;
		this.instId = instId;
		this.androidLocation = androidLocation;
	}

	public ArrGroupParam() {
		super();
	}

}
