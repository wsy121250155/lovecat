package com.mobile.base.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.base.util.MLog;

public abstract class Bean2Json<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient JSONObject data;
	
	
	public abstract T build() throws Exception;
	
	public abstract void build(Bean2Json data) throws Exception;
	
	public void setResponseData(JSONObject response) throws Exception {
		data = response.getJSONObject("data");
		this.build();
	}
	
	public void setData(JSONObject data) throws Exception {
		this.data = data;
		this.build();
	}
}
