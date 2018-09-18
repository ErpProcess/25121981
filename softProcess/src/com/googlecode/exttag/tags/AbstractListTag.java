package com.googlecode.exttag.tags;

/**
 * 生成 [xx,yy]风格的JSON
 * 
 * @author 梁飞
 */
public abstract class AbstractListTag extends AbstractMapTag {

	private static final long serialVersionUID = 1L;

	protected boolean isList() {
		return true;
	}

}
