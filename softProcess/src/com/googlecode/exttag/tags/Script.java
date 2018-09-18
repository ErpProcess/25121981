package com.googlecode.exttag.tags;

import javax.servlet.jsp.JspException;

import com.googlecode.exttag.Scope;

/**
 * EXT扩展标签
 * 
 * @author 梁飞
 * 
 */
public class Script extends AbstractTag {

	private static final long serialVersionUID = 1L;

	private boolean before;

	public boolean isBefore() {
		return before;
	}

	public void setBefore(boolean before) {
		this.before = before;
	}

	@Override
	public int doStartTag() throws JspException {
		super.pushExtScope(new Scope("script", Scope.IN_ROOT));
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		if (bodyContent != null) {
			if (before)
				defineBeforeScript(filter(bodyContent.getString()));
			else
				defineAfterScript(filter(bodyContent.getString()));
		}
		super.popExtScope();
		return EVAL_PAGE;
	}

}
