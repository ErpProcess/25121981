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
	name="allowDomMove"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)Whether the component can move the Dom node when rendering (defaults to true).
" %>

<%@ attribute
	name="applyTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element corresponding to a DIV that is already present in the document that specifies some structural markup for this component. When applyTo is used, constituent parts of the component can also be specified by id or CSS class name within the main element, and the component being created may attempt to create its subcomponents from that markup if applicable. Using this config, a call to render() is not required. If applyTo is specified, any value passed for renderTo will be ignored and the target element's parent node will automatically be used as the component's container.
" %>

<%@ attribute
	name="autoCreate"
	type="java.lang.String"
	required="false"
	description="
(String/Object)A DomHelper element spec, or true for a default element spec (defaults to {tag: 'input', type: 'text', size: '20', autocomplete: 'off'})
" %>

<%@ attribute
	name="autoHeight"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to use height:'auto', false to use fixed height (defaults to false).
" %>

<%@ attribute
	name="autoShow"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True if the component should check for hidden classes (e.g. 'x-hidden' or 'x-hide-display') and remove them on render (defaults to false).
" %>

<%@ attribute
	name="autoWidth"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to use width:'auto', false to use fixed width (defaults to false).
" %>

<%@ attribute
	name="boxLabel"
	type="java.lang.String"
	required="false"
	description="
(String)The text that appears beside the checkbox
" %>

<%@ attribute
	name="checked"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True if the the checkbox should render already checked (defaults to false)
" %>

<%@ attribute
	name="clearCls"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class used to provide field clearing (defaults to 'x-form-clear-left')
" %>

<%@ attribute
	name="cls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's Element (defaults to ''). This can be useful for adding customized styles to the component or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="ctCls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's container (defaults to ''). This can be useful for adding customized styles to the container or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="disabled"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to disable the field (defaults to false).
" %>

<%@ attribute
	name="disabledClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class added to the component when it is disabled (defaults to 'x-item-disabled').
" %>

<%@ attribute
	name="fieldClass"
	type="java.lang.String"
	required="false"
	description="
(String)The default CSS class for the field (defaults to 'x-form-field')
" %>

<%@ attribute
	name="fieldLabel"
	type="java.lang.String"
	required="false"
	description="
(String)The label text to display next to this field (defaults to '')
" %>

<%@ attribute
	name="focusClass"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class to use when the field receives focus (defaults to 'x-form-focus')
" %>

<%@ attribute
	name="height"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The height of this component in pixels (defaults to auto).
" %>

<%@ attribute
	name="hideLabel"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to completely hide the label element (defaults to false)
" %>

<%@ attribute
	name="hideMode"
	type="java.lang.String"
	required="false"
	description="
(String)How this component should hidden. Supported values are 'visibility' (css visibility), 'offsets' (negative offset position) and 'display' (css display) - defaults to 'display'.
" %>

<%@ attribute
	name="hideParent"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to hide and show the component's container when hide/show is called on the component, false to hide and show the component itself (defaults to false). For example, this can be used as a shortcut for a hide button on a window by setting hide:true on the button when adding it to its parent container.
" %>

<%@ attribute
	name="id"
	type="java.lang.String"
	required="false"
	description="
(String)The unique id of this component (defaults to an auto-assigned id).
" %>

<%@ attribute
	name="inputType"
	type="java.lang.String"
	required="false"
	description="
(String)The type attribute for input fields -- e.g. radio, text, password (defaults to 'text').
" %>

<%@ attribute
	name="inputValue"
	type="java.lang.String"
	required="false"
	description="
(String)The value that should go into the generated input element's value attribute
" %>

<%@ attribute
	name="invalidClass"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class to use when marking a field invalid (defaults to 'x-form-invalid')
" %>

<%@ attribute
	name="invalidText"
	type="java.lang.String"
	required="false"
	description="
(String)The error text to use when marking a field invalid and no message is provided (defaults to 'The value in this field is invalid')
" %>

<%@ attribute
	name="itemCls"
	type="java.lang.String"
	required="false"
	description="
(String)An additional CSS class to apply to this field (defaults to the container's itemCls value if set, or '')
" %>

<%@ attribute
	name="labelSeparator"
	type="java.lang.String"
	required="false"
	description="
(String)The standard separator to display after the text of each form label (defaults to the value of Ext.layout.FormLayout.labelSeparator, which is a colon ':' by default). To display no separator for this field's label specify empty string ''.
" %>

<%@ attribute
	name="labelStyle"
	type="java.lang.String"
	required="false"
	description="
(String)A CSS style specification to apply directly to this field's label (defaults to the container's labelStyle value if set, or ''). For example, labelStyle: 'font-weight:bold;'.
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="msgFx"
	type="java.lang.String"
	required="false"
	description="
(String)Experimental The effect used when displaying a validation message under the field (defaults to 'normal').
" %>

<%@ attribute
	name="msgTarget"
	type="java.lang.String"
	required="false"
	description="
(String)The location where error text should display. Should be one of the following values (defaults to 'qtip'): 

Value Description----------- ----------------------------------------------------------------------qtipDisplay a quick tip when the user hovers over the fieldtitle Display a default browser title attribute popupunder Add a block div beneath the field containing the error textsideAdd an error icon to the right of the field with a popup on hover[element id]Add the error text directly to the innerHTML of the specified element


" %>

<%@ attribute
	name="name"
	type="java.lang.String"
	required="false"
	description="
(String)The field's HTML name attribute.
" %>

<%@ attribute
	name="plugins"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)An object or array of objects that will provide custom functionality for this component. The only requirement for a valid plugin is that it contain an init method that accepts a reference of type Ext.Component. When a component is created, if any plugins are available, the component will call the init method on each plugin, passing a reference to itself. Each plugin can then call methods or respond to events on the component as needed to provide its functionality.
" %>

<%@ attribute
	name="readOnly"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to mark the field as readOnly in HTML (defaults to false) -- Note: this only sets the element's readOnly DOM attribute.
" %>

<%@ attribute
	name="renderTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element that will be the container to render this component into. Using this config, a call to render() is not required.
" %>

<%@ attribute
	name="stateEvents"
	type="java.lang.String"
	required="false"
	description="
(Array)An array of events that, when fired, should trigger this component to save its state (defaults to none). These can be any types of events supported by this component, including browser or custom events (e.g., ['click', 'customerchange']).
" %>

<%@ attribute
	name="stateId"
	type="java.lang.String"
	required="false"
	description="
(String)The unique id for this component to use for state management purposes (defaults to the component id).
" %>

<%@ attribute
	name="style"
	type="java.lang.String"
	required="false"
	description="
(String)A custom style specification to be applied to this component's Element. Should be a valid argument to Ext.Element.applyStyles.
" %>

<%@ attribute
	name="tabIndex"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The tabIndex for this field. Note this only applies to fields that are rendered, not those which are built via applyTo (defaults to undefined).
" %>

<%@ attribute
	name="validateOnBlur"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)Whether the field should validate when it loses focus (defaults to true).
" %>

<%@ attribute
	name="validationDelay"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The length of time in milliseconds after user input begins until validation is initiated (defaults to 250)
" %>

<%@ attribute
	name="validationEvent"
	type="java.lang.String"
	required="false"
	description="
(String/Boolean)The event that should initiate field validation. Set to false to disable automatic validation (defaults to 'keyup').
" %>

<%@ attribute
	name="value"
	type="java.lang.String"
	required="false"
	description="
(Mixed)A value to initialize this field with.
" %>

<%@ attribute
	name="width"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The width of this component in pixels (defaults to auto).
" %>

<%@ attribute
	name="xtype"
	type="java.lang.String"
	required="false"
	description="
(String)The registered xtype to create. This config option is not used when passing a config object into a constructor. This config option is used only when lazy instantiation is being used, and a child item of a Container is being specified not as a fully instantiated Component, but as a Component config object. The xtype will be looked up at render time up to determine what type of child Component to create.

 The predefined xtypes are listed here. 

 If you subclass Components to create your own Components, you may register them using Ext.ComponentMgr.registerType in order to be able to take advantage of lazy instantiation and rendering.
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforedestroy"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is destroyed. Return false to stop the destroy.
" %>

<%@ attribute
	name="onBeforehide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is hidden. Return false to stop the hide.
" %>

<%@ attribute
	name="onBeforerender"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is rendered. Return false to stop the render.
" %>

<%@ attribute
	name="onBeforeshow"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is shown. Return false to stop the show.
" %>

<%@ attribute
	name="onBeforestaterestore"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires before the state of the component is restored. Return false to stop the restore.
" %>

<%@ attribute
	name="onBeforestatesave"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires before the state of the component is saved to the configured state provider. Return false to stop the save.
" %>

<%@ attribute
	name="onBlur"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this )Fires when this field loses input focus.
" %>

<%@ attribute
	name="onChange"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this, Mixed newValue, Mixed oldValue )Fires just before the field blurs if the field value has changed.
" %>

<%@ attribute
	name="onCheck"
	type="java.lang.Boolean"
	required="false"
	description="
( Ext.form.Checkbox this, Boolean checked )Fires when the checkbox is checked or unchecked.
" %>

<%@ attribute
	name="onDestroy"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is destroyed.
" %>

<%@ attribute
	name="onDisable"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is disabled.
" %>

<%@ attribute
	name="onEnable"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is enabled.
" %>

<%@ attribute
	name="onFocus"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this )Fires when this field receives input focus.
" %>

<%@ attribute
	name="onHide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is hidden.
" %>

<%@ attribute
	name="onInvalid"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this, String msg )Fires after the field has been marked as invalid.
" %>

<%@ attribute
	name="onMove"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Component this, Number x, Number y )Fires after the component is moved.
" %>

<%@ attribute
	name="onRender"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is rendered.
" %>

<%@ attribute
	name="onResize"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Component this, Number adjWidth, Number adjHeight, Number rawWidth, Number rawHeight )Fires after the component is resized.
" %>

<%@ attribute
	name="onShow"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is shown.
" %>

<%@ attribute
	name="onSpecialkey"
	type="java.lang.Object"
	required="false"
	description="
( Ext.form.Field this, Ext.EventObject e )Fires when any key related to navigation (arrows, tab, enter, esc, etc.) is pressed.You can checkExt.EventObject.getKey to determine which key was pressed.
" %>

<%@ attribute
	name="onStaterestore"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires after the state of the component is restored.
" %>

<%@ attribute
	name="onStatesave"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires after the state of the component is saved to the configured state provider.
" %>

<%@ attribute
	name="onValid"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this )Fires after the field has been validated with no errors.
" %>

<%-- Events _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description=" Single radio field. Same as Checkbox, but provided as a convenience for automatically setting the input type. Radio grouping is handled automatically by the browser if you give each radio in a group the same name." %>
	
	<c:if test="${empty(value)}">
		<jsp:doBody var="value" scope="page" />
	</c:if>
	
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		exclude="items"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	
	<%-- Process JSP body --%>
	<c:set var="item">
		    new Ext.form.Radio({
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
		    }),
	</c:set>
	
	
	<extutil:setParentProperties 
	tag="<%=this%>" 
	items="${item}" />