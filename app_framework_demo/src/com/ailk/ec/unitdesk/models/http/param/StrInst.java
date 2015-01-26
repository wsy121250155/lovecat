package com.ailk.ec.unitdesk.models.http.param;

public class StrInst {

	public String defShowUrl;
	public String showSize;
	public String clickUrl;
	public String serviceCode;
	public String BgColor;
	public int funcId;
	public String iosLocation;
	public int sortNum;
	public String instName;
	public String instTypeId;

	public String clientUri;
	public String bindAccountServiceCode;
	public String appDownloadAddress;

	public StrInst(String showSize, String clickUrl, String serviceCode,
			String bgColor, int funcId, String instName, String iosLocation,
			int sortNum, String defShowUrl, String insttypeId,
			String clientUri, String bindAccountServiceCode,
			String appDownloadAddress) {
		super();
		this.showSize = showSize;
		this.clickUrl = clickUrl;
		this.instName = instName;
		this.serviceCode = serviceCode;
		BgColor = bgColor;
		this.funcId = funcId;
		this.iosLocation = iosLocation;
		this.sortNum = sortNum;
		this.defShowUrl = defShowUrl;
		this.instTypeId = insttypeId;
		this.clientUri = clientUri;
		this.bindAccountServiceCode = bindAccountServiceCode;
		this.appDownloadAddress = appDownloadAddress;
	}

	public StrInst() {
		super();
	}

}
