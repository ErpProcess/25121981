package com.googlecode.exttag.tags.ext.tree;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.Variable;

public class AsyncTreeNode extends TreeNode {
	private String loader;

	@Override
	protected String getDefaultKey() {
		return "root";
	}

	@Variable
	public String getLoader() {
		return loader;
	}

	@Description("loader : TreeLoader  "
			+ "A TreeLoader to be used by this node (defaults to the loader defined on the tree) ")
	public void setLoader(String loader) {
		this.loader = loader;
	}
}
