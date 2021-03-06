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

<%-- Config params _____________________________START --%>

<%@ attribute
	name="text"
	type="java.lang.String"
	required="false"
	description="(String)The option text"
 %>

<%@ attribute
	name="value"
	type="java.lang.String"
	required="true"
	description="Combobox option value"
 %>

<%-- Config params _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="Simple tag providing Ext.ComboboxItems (via SimpleStore)" %>
	
	<c:if test="${empty(text)}">
		<jsp:doBody var="text" scope="page" />
	</c:if>
	
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	<c:set var="item">
		    ['${value}', '${text}'],
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%=this%>" 
		items="${item}" />