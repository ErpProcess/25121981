package com.googlecode.exttag.tags.ext;

public class ToolbarButton extends Button {

	@Override
	public String getCls() { // 取消按钮默认样式
		String cls = super.getCls();
		if ("x-btn-normal".equals(cls))
			return null;
		return cls;
	}

	@Override
	protected String getComponentName() {
		return null;
	}

}
