package com.googlecode.exttag.tags.ext.dd;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractMapTag;

@Description("A specialized drag proxy that supports a drop status icon, Ext.Layer styles and auto-repair. This is the default drag proxy used by all Ext.dd components. ")
public class StatusProxy extends AbstractMapTag {
	private String dropAllowed;
	private String dropNotAllowed;

	public String getDropAllowed() {
		return dropAllowed;
	}

	@Description("dropAllowed : String"
			+ "The CSS class to apply to the status element when drop is allowed (defaults to 'x-dd-drop-ok'). ")
	public void setDropAllowed(String dropAllowed) {
		this.dropAllowed = dropAllowed;
	}

	public String getDropNotAllowed() {
		return dropNotAllowed;
	}

	@Description("dropNotAllowed : String"
			+ "The CSS class to apply to the status element when drop is not allowed (defaults to 'x-dd-drop-nodrop'). ")
	public void setDropNotAllowed(String dropNotAllowed) {
		this.dropNotAllowed = dropNotAllowed;
	}
}
