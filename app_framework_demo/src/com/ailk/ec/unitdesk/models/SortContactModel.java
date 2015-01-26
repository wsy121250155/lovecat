package com.ailk.ec.unitdesk.models;

import java.io.Serializable;

public class SortContactModel implements Serializable {

	public String name; // 显示的数据
	public String number;
	public String sortLetters; // 显示数据拼音的首字母
	public boolean isChecked;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SortContactModel)) {
			return false;
		}

		SortContactModel that = (SortContactModel) o;
		return name.equals(that.name) && number.equals(that.number);
	}
}
