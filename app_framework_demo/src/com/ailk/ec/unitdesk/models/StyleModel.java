package com.ailk.ec.unitdesk.models;

public class StyleModel {

	public String styleName;
	public String userName;

	public StyleModel(String userName, String styleName, int styleCode,
			boolean isChoose) {
		super();
		this.userName = userName;
		this.styleName = styleName;
		this.styleCode = styleCode;
		this.isChoose = isChoose;
	}

	public int styleCode;
	public boolean isChoose;
}
