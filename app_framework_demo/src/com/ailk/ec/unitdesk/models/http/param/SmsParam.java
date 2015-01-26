package com.ailk.ec.unitdesk.models.http.param;

import java.util.List;

public class SmsParam {

	public List<String> accNbr;
	public String messContent;
	public String areaCode;

	public SmsParam(List<String> accNbr, String messContent, String areaCode) {
		super();
		this.accNbr = accNbr;
		this.messContent = messContent;
		this.areaCode = areaCode;
	}

}
