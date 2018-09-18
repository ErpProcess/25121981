package com.googlecode.exttag.tags.ext.data;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.Variable;

@Description("Small helper class to make creating Stores from Array data easier. ")
public class SimpleStore extends Store {

	private String fields;

	@Variable
	public String getFields() {
		return fields;
	}

	@Description("fields : Array"
			+ "An array of field definition objects, or field name strings. ")
	public void setFields(String fields) {
		this.fields = fields;
	}

	/*
	 * private Integer id;
	 * 
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 */
}
