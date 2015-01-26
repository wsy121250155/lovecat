package com.ailk.ec.unitdesk.models.desktop;

import java.io.Serializable;

public class SysAcctInfo implements Serializable {

	/**
	 * 统一桌面id
	 */
	public static final int UN_DESK = 1001;
	/**
	 * 微代理id
	 */
	public static final int WE_AGENT = 1002;
	/**
	 * 手机开店id
	 */
	public static final int MOBILE_SOTRE = 1003;
	/**
	 * OA
	 */
	public static final int OA = 1004;

	public String clientUri;

	public String sysName;
	public int sysId;
	public boolean isChoose;

	public int iconId;

	public String acctName;

	public String acctPwd;

	public String sid;

	public String relaUniAcct;

	public String bindAccountServiceCode;
	public String appDownloadAddress;

	public String iconName;

	public SysAcctInfo(String sysName, int sysId, boolean isChoose, int iconId,
			String relaUniAcct) {
		this(null, sysName, sysId, isChoose, iconId, relaUniAcct, null, null,
				null);
	}

	public SysAcctInfo(String clientUri, String sysName, int sysId,
			boolean isChoose, int iconId, String relaUniAcct,
			String bindAccountServiceCode, String appDownloadAddress,
			String iconName) {
		super();
		this.clientUri = clientUri;
		this.sysName = sysName;
		this.sysId = sysId;
		this.isChoose = isChoose;
		this.iconId = iconId;
		this.relaUniAcct = relaUniAcct;
		this.bindAccountServiceCode = bindAccountServiceCode;
		this.appDownloadAddress = appDownloadAddress;
		this.iconName = iconName;
	}

	public SysAcctInfo() {
		super();
	}
}
