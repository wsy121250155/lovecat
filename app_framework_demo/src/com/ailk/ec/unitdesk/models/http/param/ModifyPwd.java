package com.ailk.ec.unitdesk.models.http.param;

public class ModifyPwd {

	public String ClientIp;
	public String UserIp;
	public String SystemId;
	public String AccountName;
	public String AreaId;
	public String OldPassword;
	public String Password;

	public ModifyPwd(String clientIp, String userIp, String systemId,
			String accountName, String areaId, String OldPassword,
			String Password) {
		super();
		ClientIp = clientIp;
		UserIp = userIp;
		SystemId = systemId;
		AccountName = accountName;
		AreaId = areaId;
		this.OldPassword = OldPassword;
		this.Password = Password;
	}

}
