package com.ailk.ec.unitdesk.models.http.param;

public class FuncTmpInst {
	public String showSize;
	public long groupId;
	public String countIntervalTime;
	public String instTypeId;
	public String instName;
	public long instId;
	public String serviceCode;
	public int funcId;
	public String androidLocation;
	public int sortNum;
	public String clientUri;
	public String bindAccountServiceCode;
	public String appDownloadAddress;

	public FuncTmpInst(String showSize, long groupId, String countIntervalTime,
			String instTypeId, String instName, long instId,
			String serviceCode, int funcId, String androidLocation,
			int sortNum, String clientUri, String bindAccountServiceCode,
			String appDownloadAddress) {
		super();
		this.showSize = showSize;
		this.groupId = groupId;
		this.countIntervalTime = countIntervalTime;
		this.instTypeId = instTypeId;
		this.instName = instName;
		this.instId = instId;
		this.serviceCode = serviceCode;
		this.funcId = funcId;
		this.androidLocation = androidLocation;
		this.sortNum = sortNum;

		this.clientUri = clientUri;
		this.bindAccountServiceCode = bindAccountServiceCode;
		this.appDownloadAddress = appDownloadAddress;
	}
}
