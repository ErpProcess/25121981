package com.googlecode.exttag.tags.ext.grid;

import com.googlecode.exttag.Description;

@Description("Class for creating and editable grid. ")
public class EditorGridPanel extends GridPanel {
	private Integer clicksToEdit;

	public Integer getClicksToEdit() {
		return clicksToEdit;
	}

	@Description("clicksToEdit : Number"
			+ "The number of clicks on a cell required to display the cell's editor (defaults to 2) ")
	public void setClicksToEdit(Integer clicksToEdit) {
		this.clicksToEdit = clicksToEdit;
	}

}
