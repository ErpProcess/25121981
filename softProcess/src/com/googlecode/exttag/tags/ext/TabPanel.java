package com.googlecode.exttag.tags.ext;

import com.googlecode.exttag.Description;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A basic tab container. Tab panels can be used exactly like a standard Ext.Panel for layout purposes, but also have special support for containing child Panels that get automatically converted into tabs.")
public class TabPanel extends Panel {
	private String activeTab;
	private Boolean animScroll;
	private String autoTabSelector;
	private Boolean autoTabs;
	private Boolean deferredRender;
	private Boolean enableTabScroll;
	private Boolean layoutOnTabChange;
	private Integer minTabWidth;
	private Boolean plain;
	private Boolean resizeTabs;
	private Float scrollDuration;
	private Integer scrollIncrement;
	private Integer scrollRepeatInterval;
	private Integer tabMargin;
	private String tabPosition;
	private Integer tabWidth;
	private Integer wheelIncrement;

	public String getActiveTab() {
		return activeTab;
	}

	@Description("activeTab : String/Number  "
			+ "A string id or the numeric index of the tab that should be initially activated on render (defaults to none). ")
	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public Boolean isAnimScroll() {
		return animScroll;
	}

	@Description("animScroll : Boolean  "
			+ "True to animate tab scrolling so that hidden tabs slide smoothly into view (defaults to true). Only applies when enableTabScroll = true. ")
	public void setAnimScroll(Boolean animScroll) {
		this.animScroll = animScroll;
	}

	public String getAutoTabSelector() {
		return autoTabSelector;
	}

	@Description("autoTabSelector : String  "
			+ "The CSS selector used to search for tabs in existing markup when autoTabs = true (defaults to 'div.x-tab'). This can be any valid selector supported by Ext.DomQuery.select. Note that the query will be executed within the scope of this tab panel only (so that multiple tab panels from markup can be supported on a page). ")
	public void setAutoTabSelector(String autoTabSelector) {
		this.autoTabSelector = autoTabSelector;
	}

	public Boolean isAutoTabs() {
		return autoTabs;
	}

	@Description("autoTabs : Boolean  "
			+ "True to query the DOM for any divs with a class of 'x-tab' to be automatically converted to tabs and added to this panel (defaults to false). Note that the query will be executed within the scope of the container element only (so that multiple tab panels from markup can be supported via this method)."
			+ "This method is only possible when the markup is structured correctly as a container with nested divs containing the class 'x-tab'. To create TabPanels without these limitations, or to pull tab content from other elements on the page, see the example at the top of the class for generating tabs from markup."
			+ "There are a couple of things to note when using this method: "
			+ "When using the autoTabs config (as opposed to passing individual tab configs in the TabPanel's items collection), you must use applyTo to correctly use the specified id as the tab container. The autoTabs method replaces existing content with the TabPanel components. "
			+ "Make sure that you set deferredRender to false so that the content elements for each tab will be rendered into the TabPanel immediately upon page load, otherwise they will not be transformed until each tab is activated and will be visible outside the TabPanel."
			+ "Example usage: var tabs = new Ext.TabPanel({"
			+ "    applyTo: 'my-tabs',"
			+ "   activeTab: 0,"
			+ "    deferredRender: false,"
			+ "    autoTabs: true"
			+ "});"
			+ "// This markup will be converted to a TabPanel from the code above"
			+ "<div id='my-tabs'>"
			+ "    <div class='x-tab' title='Tab 1'>A simple tab</div>"
			+ "    <div class='x-tab' title='Tab 2'>Another one</div>"
			+ "</div>")
	public void setAutoTabs(Boolean autoTabs) {
		this.autoTabs = autoTabs;
	}

	public Boolean isDeferredRender() {
		return deferredRender;
	}

	@Description("deferredRender : Boolean  "
			+ "Internally, the TabPanel uses a Ext.layout.CardLayout to manage its tabs. This property will be passed on to the layout as its Ext.layout.CardLayout.deferredRender config value, determining whether or not each tab is rendered only when first accessed (defaults to true).")
	public void setDeferredRender(Boolean deferredRender) {
		this.deferredRender = deferredRender;
	}

	public Boolean isEnableTabScroll() {
		return enableTabScroll;
	}

	@Description("enableTabScroll : Number  "
			+ "True to enable scrolling to tabs that may be invisible due to overflowing the overall TabPanel width. Only available with tabs on top. (defaults to false). ")
	public void setEnableTabScroll(Boolean enableTabScroll) {
		this.enableTabScroll = enableTabScroll;
	}

	public Boolean isLayoutOnTabChange() {
		return layoutOnTabChange;
	}

	@Description("layoutOnTabChange : Boolean  "
			+ "Set to true to do a layout of tab items as tabs are changed. ")
	public void setLayoutOnTabChange(Boolean layoutOnTabChange) {
		this.layoutOnTabChange = layoutOnTabChange;
	}

	public Integer getMinTabWidth() {
		return minTabWidth;
	}

	@Description("minTabWidth : Number  "
			+ "The minimum width in pixels for each tab when resizeTabs = true (defaults to 30). ")
	public void setMinTabWidth(Integer minTabWidth) {
		this.minTabWidth = minTabWidth;
	}

	public Boolean isPlain() {
		return plain;
	}

	@Description("plain : Boolean  "
			+ "True to render the tab strip without a background container image (defaults to false). ")
	public void setPlain(Boolean plain) {
		this.plain = plain;
	}

	public Boolean isResizeTabs() {
		return resizeTabs;
	}

	@Description("resizeTabs : Boolean  "
			+ "True to automatically resize each tab so that the tabs will completely fill the tab strip (defaults to false). Setting this to true may cause specific widths that might be set per tab to be overridden in order to fit them all into view (although minTabWidth will always be honored). ")
	public void setResizeTabs(Boolean resizeTabs) {
		this.resizeTabs = resizeTabs;
	}

	public Float getScrollDuration() {
		return scrollDuration;
	}

	@Description("scrollDuration : Float  "
			+ "The number of milliseconds that each scroll animation should last (defaults to .35). Only applies when animScroll = true. ")
	public void setScrollDuration(Float scrollDuration) {
		this.scrollDuration = scrollDuration;
	}

	public Integer getScrollIncrement() {
		return scrollIncrement;
	}

	@Description("scrollIncrement : Number  "
			+ "The number of pixels to scroll each time a tab scroll button is pressed (defaults to 100, or if resizeTabs = true, the calculated tab width). Only applies when enableTabScroll = true. ")
	public void setScrollIncrement(Integer scrollIncrement) {
		this.scrollIncrement = scrollIncrement;
	}

	public Integer getScrollRepeatInterval() {
		return scrollRepeatInterval;
	}

	@Description("scrollRepeatInterval : Number  "
			+ "Number of milliseconds between each scroll while a tab scroll button is continuously pressed (defaults to 400). ")
	public void setScrollRepeatInterval(Integer scrollRepeatInterval) {
		this.scrollRepeatInterval = scrollRepeatInterval;
	}

	public Integer getTabMargin() {
		return tabMargin;
	}

	@Description("tabMargin : Number  "
			+ "The number of pixels of space to calculate into the sizing and scrolling of tabs. If you change the margin in CSS, you will need to update this value so calculations are correct with either resizeTabs or scrolling tabs. (defaults to 2) ")
	public void setTabMargin(Integer tabMargin) {
		this.tabMargin = tabMargin;
	}

	public String getTabPosition() {
		return tabPosition;
	}

	@Description("tabPosition : String  "
			+ "The position where the tab strip should be rendered (defaults to 'top'). The only other supported value is 'bottom'. Note that tab scrolling is only supported for position 'top'. ")
	public void setTabPosition(String tabPosition) {
		this.tabPosition = tabPosition;
	}

	public Integer getTabWidth() {
		return tabWidth;
	}

	@Description("tabWidth : Number  "
			+ "The initial width in pixels of each new tab (defaults to 120). ")
	public void setTabWidth(Integer tabWidth) {
		this.tabWidth = tabWidth;
	}

	public Integer getWheelIncrement() {
		return wheelIncrement;
	}

	@Description("wheelIncrement : Number  "
			+ "For scrolling tabs, the number of pixels to increment on mouse wheel scrolling (defaults to 20). ")
	public void setWheelIncrement(Integer wheelIncrement) {
		this.wheelIncrement = wheelIncrement;
	}

}
