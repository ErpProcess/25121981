package com.googlecode.exttag.tags.ext.menu;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.Variable;
import com.googlecode.exttag.tags.ext.Component;

@Description("The base class for all items that render into menus. BaseItem provides default rendering, activated state management and base configuration options shared by all menu components. ")
public class BaseItem extends Component {
	private String activeClass;
	private Boolean canActivate;
	private String handler;
	private Integer hideDelay;
	private Boolean hideOnClick;
	private String scope;



	public String getActiveClass() {
		return activeClass;
	}

	@Description("activeClass : String  "
			+ "The CSS class to use when the item becomes activated (defaults to 'x-menu-item-active') ")
	public void setActiveClass(String activeClass) {
		this.activeClass = activeClass;
	}

	public Boolean isCanActivate() {
		return canActivate;
	}

	@Description("canActivate : Boolean  "
			+ "True if this item can be visually activated (defaults to false) ")
	public void setCanActivate(Boolean canActivate) {
		this.canActivate = canActivate;
	}

	@Variable
	public String getHandler() {
		return handler;
	}

	@Description("handler : Function  "
			+ "A function that will handle the click event of this menu item (defaults to undefined) ")
	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Integer getHideDelay() {
		return hideDelay;
	}

	@Description("hideDelay : Number  "
			+ "Length of time in milliseconds to wait before hiding after a click (defaults to 100) ")
	public void setHideDelay(Integer hideDelay) {
		this.hideDelay = hideDelay;
	}

	public Boolean isHideOnClick() {
		return hideOnClick;
	}

	@Description("hideOnClick : Boolean  "
			+ "True to hide the containing menu after this item is clicked (defaults to true) ")
	public void setHideOnClick(Boolean hideOnClick) {
		this.hideOnClick = hideOnClick;
	}

	@Variable
	public String getScope() {
		return scope;
	}

	@Description("scope : Object  "
			+ "The scope in which the handler function will be called. ")
	public void setScope(String scope) {
		this.scope = scope;
	}
}
