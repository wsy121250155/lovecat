package com.ailk.ec.unitdesk.models.http;

import java.io.Serializable;
import java.util.List;

public class DeskTopFuncTmp implements Serializable {
	public int funcId;
	public String funcName;
	public String defShow;
	public String globalVar;
	/**
	 * 1,2,url.
	 */
	public String defShowUrl;
	public String clickUrl;
	public String custemUrl;
	public String bgColor;
	public BelongSys belongSys;
	public String funcDesc;
	public String clientUri;
	public String funcCode;
	public String revDefShowUrl;
	public String showSize;
	public String paramFormat;
	/**
	 * 刷新间隔时间
	 */
	public String countIntervalTime;

	public List<ReInstType> reInstType;
	
	/**
	 * 第三方app接入参数
	 */
	public String bindAccountServiceCode;
	public String appDownloadAddressAndroid;
	/**
	 * 0和null不可重复，1可重复
	 */
	public String isRepeat;

}
