package com.ailk.ec.unitdesk.models.desktop;

import java.io.Serializable;

public class MailData implements Serializable {
	public String title;
	public String readurl;
	public String createdate;
	public String creater;
	public String body;

	public MailData(String title, String readurl, String createdate,
			String creater,String body) {
		super();
		this.title = title;
		this.readurl = readurl;
		this.createdate = createdate;
		this.creater = creater;
		this.body = body;
	}

	public MailData() {
		super();
	}

}
