package com.ailk.ec.unitdesk.models.http;

import java.io.Serializable;
import java.util.List;

public class TemplateType implements Serializable{
	public int tempTypeId;
	public String tempTypeName;
	public String tempTypeDesc;
	public String newFuncTempNum;
	public List<DeskTopFuncTmp> deskTopFuncTmpList;
}
