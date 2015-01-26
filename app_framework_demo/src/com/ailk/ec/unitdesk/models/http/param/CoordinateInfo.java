package com.ailk.ec.unitdesk.models.http.param;

/**
 * 坐标信息
 * 
 * @author spoon
 * 
 */
public class CoordinateInfo {

	public String lon;
	public String lat;
	public String verAccuracy;
	public String HorAccuracy;
	public String timeStamp;
	public String speed;
	public String country;
	public String administrativeArea;
	public String locality;
	public String subLocality;
	public String name;

	public CoordinateInfo(String lon, String lat, String name) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.name = name;
	}

	public CoordinateInfo() {
		super();
	}

}
