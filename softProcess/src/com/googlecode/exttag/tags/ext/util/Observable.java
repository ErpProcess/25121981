package com.googlecode.exttag.tags.ext.util;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.Variable;
import com.googlecode.exttag.tags.AbstractMapTag;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Abstract base class that provides a common interface for publishing events. Subclasses are expected to to have a property 'events' with all the events defined."
		+ "For example: "
		+ "Employee = function(name){"
		+ "    this.name = name;"
		+ "    this.addEvents({"
		+ "        'fired' : true,"
		+ "        'quit' : true"
		+ "    });"
		+ " }" + " Ext.extend(Employee, Ext.util.Observable);")
public class Observable extends AbstractMapTag {
	private String listeners;

	@Variable
	public String getListeners() {
		return listeners;
	}

	@Description("listeners : Object  "
			+ "A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once. ")
	public void setListeners(String listeners) {
		this.listeners = listeners;
	}
}
