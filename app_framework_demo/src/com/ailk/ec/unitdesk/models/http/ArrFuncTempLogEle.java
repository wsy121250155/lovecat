package com.ailk.ec.unitdesk.models.http;

import java.io.Serializable;

public class ArrFuncTempLogEle implements Serializable {

	public String areaCode;
	public String logState;
	public String userId;
	public String logTime;
	public String logId;
	public String devicetypeName;
	public String funcId;

	public ArrFuncTempLogEle(String areaCode, String logState, String userId,
			String logTime, String logId, String devicetypeName, String funcId) {
		super();
		this.areaCode = areaCode;
		this.logState = logState;
		this.userId = userId;
		this.logTime = logTime;
		this.logId = logId;
		this.devicetypeName = devicetypeName;
		this.funcId = funcId;
	}

}
