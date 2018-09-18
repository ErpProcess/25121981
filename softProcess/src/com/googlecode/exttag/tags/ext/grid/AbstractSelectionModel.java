package com.googlecode.exttag.tags.ext.grid;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.ext.util.Observable;

@Description("Abstract base class for grid SelectionModels. It provides the interface that should be implemented by descendant classes. This class should not be directly instantiated. ")
public class AbstractSelectionModel extends Observable {

	@Override
	protected String getDefaultKey() {
		return "sm";
	}

}
