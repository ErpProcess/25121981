<%-- 
	ExtJS Tag Library (ExtTLD)
    Copyright (C) 2008  Jaroslav Benc <jaroslav.benc@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
	
	===========================================================================
	BY USING THIS LIBRARY YOU CONFIRM THAT YOU HAVE READ, UNDERSTOOD AND ACCEPT
	OUR ETHICAL CRITERIA LISTED ON THE EXTTLD WEBSITE (WWW.EXTTLD.COM)
	===========================================================================
--%>

<%@ attribute name="tag" required="true" type="javax.servlet.jsp.tagext.SimpleTagSupport" %>
<%@ attribute name="property" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="true" type="java.lang.String" %>


<%@ tag 
	import="
		org.apache.commons.beanutils.BeanUtils,
		javax.servlet.jsp.tagext.JspTag,
		javax.servlet.jsp.tagext.SimpleTagSupport,
		javax.servlet.jsp.tagext.SimpleTag" %>

	<%
	
		SimpleTagSupport targetTag = (SimpleTagSupport)this.findAncestorWithClass(tag,SimpleTag.class);
		
		String parentValue = (String)BeanUtils.getProperty(targetTag,property);
		if (parentValue!=null) value = parentValue + value;
		BeanUtils.setProperty(targetTag,property,value);

	%>