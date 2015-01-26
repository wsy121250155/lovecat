package com.ailk.ec.unitdesk.models.http.param;

public class ResetPwd1 {

	public String ClientIp;
	public String UserIp;
	public String SystemId;
	public String AccountName;
	public String AreaId;
	public String RandomCode;
	public String Password;

	public ResetPwd1(String clientIp, String userIp, String systemId,
			String accountName, String areaId) {
		super();
		ClientIp = clientIp;
		UserIp = userIp;
		SystemId = systemId;
		AccountName = accountName;
		AreaId = areaId;
	}

	public ResetPwd1(String clientIp, String userIp, String systemId,
			String accountName, String areaId, String randomCode,
			String password) {
		super();
		ClientIp = clientIp;
		UserIp = userIp;
		SystemId = systemId;
		AccountName = accountName;
		AreaId = areaId;
		RandomCode = randomCode;
		Password = password;
	}

}
