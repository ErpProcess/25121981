package com.googlecode.exttag.tags.ext;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractMapTag;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Simple class that can provide a shadow effect for any element. Note that the element MUST be absolutely positioned, and the shadow does not provide any shimming. This should be used only in simple cases -- for more advanced functionality that can also provide the same shadow effect, see the Ext.Layer class. ")
public class Shadow extends AbstractMapTag {
	private String mode;
	private String offset;

	public String getMode() {
		return mode;
	}

	@Description("mode : String  "
			+ "The shadow display mode. Supports the following options:"
			+ "sides: Shadow displays on both sides and bottom only"
			+ "frame: Shadow displays equally on all four sides"
			+ "drop: Traditional bottom-right drop shadow (default) ")
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getOffset() {
		return offset;
	}

	@Description("offset : String  "
			+ "The number of pixels to offset the shadow from the element (defaults to 4) ")
	public void setOffset(String offset) {
		this.offset = offset;
	}
}
