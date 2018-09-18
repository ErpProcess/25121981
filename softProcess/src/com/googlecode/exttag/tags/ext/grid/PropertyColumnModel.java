package com.googlecode.exttag.tags.ext.grid;

import com.googlecode.exttag.Description;

@Description("A custom column model for the Ext.grid.PropertyGrid. Generally it should not need to be used directly. ")
public class PropertyColumnModel extends ColumnModel {

	@Override
	protected String getDefaultKey() {
		return null;
	}

	@Override
	protected String getComponentName() {
		return null;
	}

	@Override
	protected boolean isList() {
		return false;
	}

}
