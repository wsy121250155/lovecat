/**
 *
 */
package com.ailk.ec.unitdesk.models;

/**
 * 
 * @author Spoon
 * @date 2014年7月24日
 * @version 1.0
 */
public class DropBoxOption {

	public DropBoxOption() {

	}

	public DropBoxOption(String optionVal, String optionText, Object object) {
		this.optionVal = optionVal;
		this.optionText = optionText;
		this.object = object;
	}

	public String optionVal;
	public String optionText;
	public Object object;
	public boolean isSelected;

}
