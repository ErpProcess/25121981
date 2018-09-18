package com.googlecode.exttag.tags.ext.grid;

import javax.servlet.jsp.JspException;

import com.googlecode.exttag.tags.ext.form.AjaxAction;

public abstract class GridAction extends AjaxAction {

	protected abstract String getParamsName();

	protected abstract String getDataFunction();

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		String v = this.getCurrentExtScope().getParentName();
		if (v != null && v.trim().length() > 0)
			super.defineAfterScript(v + "." + getName() + " = function() {var arr = new Array();var modifies = "+v+"." + getDataFunction() + ";for (var i = 0; i < modifies.length; i ++)arr.push(modifies[i].data);var params = Ext.util.JSON.encode(arr);Ext.Ajax.request({params: {"+getParamsName()+": params}," + getEntryString() + "});};");
		return EVAL_PAGE;
	}

}
