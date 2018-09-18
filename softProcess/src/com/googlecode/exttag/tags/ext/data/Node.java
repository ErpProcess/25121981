package com.googlecode.exttag.tags.ext.data;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.ext.util.Observable;

/**
 * 
 * @author oscar.xie
 * 
 */
public class Node extends Observable {
	private String id;
	private Boolean leaf;

	public String getId() {
		return id;
	}

	@Description("id:String   The id for this node. If one is not specified, one is generated. ")
	public void setId(String id) {
		this.id = id;
	}

	public Boolean isLeaf() {
		return leaf;
	}

	@Description("isLeaf :boolean   true if this node is a leaf and does not have children ")
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
}
