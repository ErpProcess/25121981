package com.googlecode.exttag.tags.ext.menu;

import com.googlecode.exttag.Description;

@Description("Adds a static text string to a menu, usually used as either a heading or group separator. ")
public class TextItem extends BaseItem {

	private static final long serialVersionUID = 1852800271292152526L;

	@Override
	protected String getComponentName() {
		return null;
	}
	
	private String itemCls;
	
	private String text;

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String  "
			+ "The default CSS class to use for text items (defaults to 'x-menu-text') ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public String getText() {
		return text;
	}

	@Description("text : String  "
			+ "The text to display for this item (defaults to '') ")
	public void setText(String text) {
		this.text = text;
	}

}
