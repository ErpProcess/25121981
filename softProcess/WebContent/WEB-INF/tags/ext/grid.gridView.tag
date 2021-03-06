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
	name="autoFill"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to auto expand the columns to fit the grid when the grid is created.
" %>

<%@ attribute
	name="emptyText"
	type="java.lang.String"
	required="false"
	description="
(String)Default text to display in the grid body when no rows are available (defaults to '').
" %>

<%@ attribute
	name="enableRowBody"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to add a second TR element per row that can be used to provide a row body that spans beneath the data row. Use the getRowClass method's rowParams config to customize the row body.
" %>

<%@ attribute
	name="forceFit"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to auto expand/contract the size of the columns to fit the grid width and prevent horizontal scrolling.
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforerefresh"
	type="java.lang.String"
	required="false"
	description="
( Ext.grid.GridView view )Internal UI Event. Fired before the view is refreshed.
" %>

<%@ attribute
	name="onBeforerowremoved"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.grid.GridView view, Number rowIndex, Ext.data.Record record )Internal UI Event. Fired before a row is removed.
" %>

<%@ attribute
	name="onBeforerowsinserted"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.grid.GridView view, Number firstRow, Number lastRow )Internal UI Event. Fired before rows are inserted.
" %>

<%@ attribute
	name="onRefresh"
	type="java.lang.String"
	required="false"
	description="
( Ext.grid.GridView view )Internal UI Event. Fired after the GridView's body has been refreshed.
" %>

<%@ attribute
	name="onRowremoved"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.grid.GridView view, Number rowIndex, Ext.data.Record record )Internal UI Event. Fired after a row is removed.
" %>

<%@ attribute
	name="onRowsinserted"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.grid.GridView view, Number firstRow, Number lastRow )Internal UI Event. Fired after rows are inserted.
" %>

<%@ attribute
	name="onRowupdated"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.grid.GridView view, Number firstRow, Ext.data.record record )Internal UI Event. Fired after a row has been updated.
" %>

<%-- Events _____________________________END --%>
  

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="This class encapsulates the user interface of an Ext.grid.GridPanel. Methods of this class may be used to access user interface elements to enable special display effects. Do not change the DOM structure of the user interface. This class does not provide ways to manipulate the underlying data. The data model of a Grid is held in an Ext.data.Store." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		exclude="items"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
        
	<%-- Process JSP body --%>
	<c:set var="item">
		    new Ext.grid.GridView({
		  		<c:forEach items="${configMap}" var="config">
		  			${config.key}:${config.value},
		  		</c:forEach>
		  		listeners:{
			  		<c:forEach items="${eventsMap}" var="event" varStatus="status">
			  			<c:if test="${fn:indexOf(event.value,'function(')==-1}">
				  			${event.key}:function(){${event.value}}			  			
			  			</c:if>
		  				<c:if test="${fn:indexOf(event.value,'function(')>-1}">
				  			${event.key}:${event.value}
			  			</c:if>
			  			${status.last?'':','}
			  		</c:forEach>
		  		}
		    })
	</c:set>
    
    <extutil:setParentProperties 
    	tag="<%= this %>"
    	view="${item}" />
			