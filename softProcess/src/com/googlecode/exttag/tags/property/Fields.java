package com.googlecode.exttag.tags.property;

import java.util.List;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractListTag;
import com.googlecode.exttag.util.ClassUtils;

public class Fields extends AbstractListTag {

	private static final long serialVersionUID = 1L;

	// 扩展属性，通过反射填充fields
	private String type;

	@Description("type : java.lang.Class Model class name, eg: com.xxx.domain.User.")
	public void setType(String type) {
		this.type = type;
	}

	@Override
	protected String getComponentBegin() {
		if (type != null && type.trim().length() > 0) {
			type = type.trim();
			try {
				Class<?> typeClass = Class.forName(type);
				List<String> props = ClassUtils.getClassAllProperties(typeClass);
				StringBuilder buf = new StringBuilder();
				for (String prop : props) {
					appendComma(buf);
					buf.append("{name: \"" + prop + "\"}");
				}
				return buf.toString();
			} catch (Exception e) {
				// ignore
			}
		}
		return null;
	}

}
