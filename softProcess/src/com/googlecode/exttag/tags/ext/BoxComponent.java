package com.googlecode.exttag.tags.ext;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Base class for any visual Ext.Component that uses a box container. BoxComponent provides automatic box model adjustments for sizing and positioning and will work correctly withnin the Component rendering model. All container classes should subclass BoxComponent so that they will work consistently when nested within other Ext layout containers. ")
public class BoxComponent extends Component {
	private Boolean autoHeight;
	private Boolean autoWidth;
	private String height;
	private String margins;
	private Integer pageX;
	private Integer pageY;
	private String region;
	private String width;
	private Integer x;
	private Integer y;

	public Boolean isAutoHeight() {
		return autoHeight;
	}

	@Description("autoHeight : Boolean  "
			+ "True to use height:'auto', false to use fixed height (defaults to false). ")
	public void setAutoHeight(Boolean autoHeight) {
		this.autoHeight = autoHeight;
	}

	public Boolean isAutoWidth() {
		return autoWidth;
	}

	@Description("autoWidth : Boolean  "
			+ "True to use width:'auto', false to use fixed width (defaults to false). ")
	public void setAutoWidth(Boolean autoWidth) {
		this.autoWidth = autoWidth;
	}

	@Variable
	public String getHeight() {
		return height;
	}

	@Description("height : Number  "
			+ "The height of this component in pixels (defaults to auto). ")
	public void setHeight(String height) {
		this.height = height;
	}

	@Variable
	public String getWidth() {
		return width;
	}

	@Description("width : Number  "
			+ "The width of this component in pixels (defaults to auto). ")
	public void setWidth(String width) {
		this.width = width;
	}

	public String getMargins() {
		return margins;
	}

	@Description("margins : Object  "
			+ "Note: this config is only used when this BoxComponent is rendered by a Container which has been configured to use the... "
			+ "Note: this config is only used when this BoxComponent is rendered by a Container which has been configured to use the BorderLayout or one of the two BoxLayout subclasses. "
			+ " "
			+ "An object containing margins to apply to this BoxComponent in the format: "
			+ " "
			+ "{ "
			+ "    top: (top margin), "
			+ "    right: (right margin), "
			+ "    bottom: (bottom margin), "
			+ "    left: (left margin) "
			+ "} "
			+ "May also be a string containing space-separated, numeric margin values. The order of the sides associated with each value matches the way CSS processes margin values: "
			+ " "
			+ " "
			+ "If there is only one value, it applies to all sides.  "
			+ "If there are two values, the top and bottom borders are set to the first value and the right and left are set to the second.  "
			+ "If there are three values, the top is set to the first value, the left and right are set to the second, and the bottom is set to the third.  "
			+ "If there are four values, they apply to the top, right, bottom, and left, respectively.  "
			+ " " + "Defaults to: " + " "
			+ "{top:0, right:0, bottom:0, left:0} ")
	public void setMargins(String margins) {
		this.margins = margins;
	}

	public Integer getPageX() {
		return pageX;
	}

	@Description("pageX : Number  "
			+ "The page level x coordinate for this component if contained within a positioning container.")
	public void setPageX(Integer pageX) {
		this.pageX = pageX;
	}

	public Integer getPageY() {
		return pageY;
	}

	@Description("pageY : Number  "
			+ "The page level y coordinate for this component if contained within a positioning container.")
	public void setPageY(Integer pageY) {
		this.pageY = pageY;
	}

	public String getRegion() {
		return region;
	}

	@Description("region : String  "
			+ "Note: this config is only used when this BoxComponent is rendered by a Container which has been configured to use the BorderLayout layout manager (e.g. specifying layout:'border')."
			+ "See Ext.layout.BorderLayout also.")
	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getX() {
		return x;
	}

	@Description("x : Number  "
			+ "The local x (left) coordinate for this component if contained within a positioning container.")
	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	@Description("y : Number  "
			+ "The local y (top) coordinate for this component if contained within a positioning container.")
	public void setY(Integer y) {
		this.y = y;
	}

}
