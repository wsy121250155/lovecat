package com.ailk.ec.unitdesk.utils;

import java.util.Comparator;

import com.ailk.ec.unitdesk.models.SortContactModel;

/**
 * 
 */
public class PinyinComparator implements Comparator<SortContactModel> {

	public int compare(SortContactModel o1, SortContactModel o2) {
		if (o1.sortLetters.equals("@")
				|| o2.sortLetters.equals("#")) {
			return -1;
		} else if (o1.sortLetters.equals("#")
				|| o2.sortLetters.equals("@")) {
			return 1;
		} else {
			return o1.sortLetters.compareTo(o2.sortLetters);
		}
	}

}
