package com.googlecode.exttag.tags.ext.menu;

import com.googlecode.exttag.Description;

@Description("Adds a separator bar to a menu, used to divide logical groups of menu items. Generally you will add one of these by using '-' in you call to add() or in your items config rather than creating one directly. ")
public class Separator extends BaseItem {
	private String itemCls;

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String "
			+ "The default CSS class to use for separators (defaults to 'x-menu-sep') ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	@Override
	protected String getComponentDefine() {
		return "\'-\'";
	}

}
