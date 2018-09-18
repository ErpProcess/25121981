package com.googlecode.exttag.tags.ext;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractMapTag;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("A simple utility class for generically masking elements while loading data. If the element being masked has an underlying Ext.data.Store, the masking will be automatically synchronized with the store's loading process and the mask element will be cached for reuse. For all other elements, this mask will replace the element's Updater load indicator and will be destroyed after the initial load. ")
public class LoadMask extends AbstractMapTag {
	private String msg;
	private String msgCls;
	private Boolean removeMask;

	public String getMsg() {
		return msg;
	}

	@Description("msg : String  "
			+ "The text to display in a centered loading message box (defaults to 'Loading...') ")
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgCls() {
		return msgCls;
	}

	@Description("msgCls : String  "
			+ "The CSS class to apply to the loading message element (defaults to 'x-mask-loading') ")
	public void setMsgCls(String msgCls) {
		this.msgCls = msgCls;
	}

	public Boolean isRemoveMask() {
		return removeMask;
	}

	@Description("removeMask : Boolean  "
			+ "True to create a single-use mask that is automatically destroyed after loading (useful for page loads), False to persist the mask element reference for multiple uses (e.g., for paged data widgets). Defaults to false. ")
	public void setRemoveMask(Boolean removeMask) {
		this.removeMask = removeMask;
	}

}
