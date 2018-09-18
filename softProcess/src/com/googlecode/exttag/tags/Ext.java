package com.googlecode.exttag.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.googlecode.exttag.Configuration;
import com.googlecode.exttag.Event;
import com.googlecode.exttag.Scope;

public class Ext extends AbstractTag {

	private static final long serialVersionUID = 1L;

	private String title;

	private String styles;

	private String scripts;

	private String onReady;

	public String getTitle() {
		if (title == null)
		    return "";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyles() {
		if (styles == null)
			return "";
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public String getScripts() {
		if (scripts == null)
			return "";
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	@Event
	public String getOnReady() {
		if (onReady == null)
			return "";
		return onReady;
	}

	public void setOnReady(String onReady) {
		this.onReady = onReady;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext
					.getOut()
					.println(
//							"<html><head><title>"
//									+ title!=null ?title:""
//									+ "</title>"
//									+ Configuration.getStyles()
//									+ Configuration.getScripts()
//									+ Configuration.parseStyles(getStyles(), false)
//									+ Configuration.parseScripts(getScripts(), false)
//									+ "<script type=\"text/javascript\" src=\""
//									+ Configuration.getHome()
//									+ "src/locale/ext-lang-"
//									+ pageContext.getRequest().getLocale()
//									+ ".js\"></script>"
//									+ "</head><body>   ""
									" <script type=\"text/javascript\"> Ext.onReady(function() {Ext.QuickTips.init();Ext.state.Manager.setProvider(new Ext.state.CookieProvider());" + getOnReady());
			super.pushExtScope(new Scope(getLowerClassName(), Scope.IN_ROOT));
			return EVAL_BODY_INCLUDE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			super.popExtScope();
			pageContext.getOut().println(
					"}); " + getDefinedBeforeScripts() + getDefinedComponents()
							+ getDefinedAfterScripts()
							+ "</script><style type=\"text/css\">"
							+ getDefinedStyles() + "</style></body></html>");
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

}