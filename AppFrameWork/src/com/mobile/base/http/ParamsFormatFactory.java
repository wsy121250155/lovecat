package com.mobile.base.http;

public class ParamsFormatFactory {
	public static ParamsFormat getParamsFormat(String methodno) {
		ParamsFormat pf = new ParamsFormat();
		pf.setMethodno(methodno);
		return pf;
	}
	
	/**
	 * 带分页的数据接口请求
	 * @param methodno
	 * @param page
	 * @param limit
	 * @return
	 */
	public static ParamsFormat getParamsFormat(String methodno, int page, int limit) {
		ParamsFormat pf = new ParamsFormat();
		pf.setMethodno(methodno);
		pf.addParam("page", page);
		pf.addParam("limit", limit);
		
		return pf;
	}
}
