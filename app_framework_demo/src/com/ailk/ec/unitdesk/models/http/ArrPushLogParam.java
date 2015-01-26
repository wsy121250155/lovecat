package com.ailk.ec.unitdesk.models.http;

public class ArrPushLogParam {

	public String funcId;
	public String insttypeId;
	public String deviceType;
	public String userId;
	public String areaCode;
	public String pushLogId;
	public String logTime;
	public String devicetypeId;
	public String pushState;

	public ArrPushLogParam(String funcId, String insttypeId, String deviceType,
			String userId, String areaCode, String pushLogId, String logTime,
			String devicetypeId, String pushState) {
		super();
		this.funcId = funcId;
		this.insttypeId = insttypeId;
		this.deviceType = deviceType;
		this.userId = userId;
		this.areaCode = areaCode;
		this.pushLogId = pushLogId;
		this.logTime = logTime;
		this.devicetypeId = devicetypeId;
		this.pushState = pushState;
	}

	public ArrPushLogParam() {
		super();
	}

}
