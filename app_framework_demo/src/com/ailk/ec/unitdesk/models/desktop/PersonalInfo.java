package com.ailk.ec.unitdesk.models.desktop;

import java.io.Serializable;
import java.util.List;

public class PersonalInfo implements Serializable {
	public String realName;
	public String userName;
	public String areaName;
	public List<RoleInfo> roleInfos;
}
