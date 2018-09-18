package com.googlecode.exttag.tags.ext.grid;

public class SynchronizationAction extends GridAction {

	@Override
	protected String getParamsName() {
		return "synchronized";
	}

	@Override
	protected String getDataFunction() {
		return "getStore().getRange()";
	}

}
