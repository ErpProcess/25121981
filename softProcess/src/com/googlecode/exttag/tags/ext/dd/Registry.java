package com.googlecode.exttag.tags.ext.dd;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractMapTag;

@Description("Provides easy access to all drag drop components that are registered on a page. Items can be retrieved either directly by DOM node id, or by passing in the drag drop event that occurred and looking up the event target."
		+ "This class is a singleton and cannot be created directly. ")
public class Registry extends AbstractMapTag {

}
