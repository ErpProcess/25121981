package com.googlecode.exttag.tags;

import javax.servlet.jsp.JspException;

import com.googlecode.exttag.Scope;

public class Style extends AbstractTag {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		super.pushExtScope(new Scope("style", Scope.IN_ROOT));
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		if (bodyContent != null) {
			defineStyle(filter(bodyContent.getString()));
		}
		super.popExtScope();
		return EVAL_PAGE;
	}

}
