package com.googlecode.exttag.tags.ext;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractMapTag;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("* Provides high performance selector/xpath processing by compiling queries into reusable functions. New pseudo classes and matchers can be plugged. It works on HTML and XML documents (if a content node is passed in)."
		+ "DomQuery supports most of the CSS3 selectors spec, along with some custom selectors and basic XPath."
		+ "All selectors, attribute filters and pseudos below can be combined infinitely in any order. For example 'div.foo:nth-child(odd)[@foo=bar].bar:first' would be a perfectly valid selector. Node filters are processed in the order in which they appear, which allows you to optimize your queries for your document structure. ")
public class DomQuery extends AbstractMapTag {

}
