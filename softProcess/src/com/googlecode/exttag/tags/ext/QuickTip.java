package com.googlecode.exttag.tags.ext;

import com.googlecode.exttag.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("A specialized tooltip class for tooltips that can be specified in markup and automatically managed by the global Ext.QuickTips instance. See the QuickTips class header for additional usage details and examples. ")
public class QuickTip extends ToolTip {
	private Boolean interceptTitles;

	public Boolean isInterceptTitles() {
		return interceptTitles;
	}

	@Description("interceptTitles : Boolean  "
			+ "True to automatically use the element's DOM title value if available (defaults to false). ")
	public void setInterceptTitles(Boolean interceptTitles) {
		this.interceptTitles = interceptTitles;
	}

}
