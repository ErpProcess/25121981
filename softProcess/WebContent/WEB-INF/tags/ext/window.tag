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
	name="activeItem"
	type="java.lang.String"
	required="false"
	description="
(String/Number)A string component id or the numeric index of the component that should be initially activated within the container's layout on render. For example, activeItem: 'item-1' or activeItem: 0 (index 0 = the first item in the container's collection). activeItem only applies to layout styles that can display items one at a time (like Ext.layout.Accordion, Ext.layout.CardLayout and Ext.layout.FitLayout). Related to Ext.layout.ContainerLayout.activeItem.
" %>

<%@ attribute
	name="allowDomMove"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)Whether the component can move the Dom node when rendering (defaults to true).
" %>

<%@ attribute
	name="animCollapse"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to animate the transition when the panel is collapsed, false to skip the animation (defaults to true if the Ext.Fx class is available, otherwise false).
" %>

<%@ attribute
	name="animateTarget"
	type="java.lang.String"
	required="false"
	description="
(String/Element)Id or element from which the window should animate while opening (defaults to null with no animation).
" %>

<%@ attribute
	name="applyTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element corresponding to a DIV that is already present in the document that specifies some structural markup for this component. When applyTo is used, constituent parts of the component can also be specified by id or CSS class name within the main element, and the component being created may attempt to create its subcomponents from that markup if applicable. Using this config, a call to render() is not required. If applyTo is specified, any value passed for renderTo will be ignored and the target element's parent node will automatically be used as the component's container.
" %>

<%@ attribute
	name="autoDestroy"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)If true the container will automatically destroy any contained component that is removed from it, else destruction must be handled manually (defaults to true).
" %>

<%@ attribute
	name="autoHeight"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to use height:'auto', false to use fixed height (defaults to false).
" %>

<%@ attribute
	name="autoLoad"
	type="java.lang.String"
	required="false"
	description="
(Object/String/Function)A valid url spec according to the Updater Ext.Updater.update method. If autoLoad is not null, the panel will attempt to load its contents immediately upon render.

The URL will become the default URL for this panel's body element, so it may be refreshed at any time.

" %>

<%@ attribute
	name="autoScroll"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to use overflow:'auto' on the panel's body element and show scroll bars automatically when necessary, false to clip any overflowing content (defaults to false).
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
	name="baseCls"
	type="java.lang.String"
	required="false"
	description="
(String)The base CSS class to apply to this panel's element (defaults to 'x-window').
" %>

<%@ attribute
	name="bbar"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)The bottom toolbar of the panel. This can be a Ext.Toolbar object, a toolbar config, or an array of buttons/button configs to be added to the toolbar. Note that this is not available as a property after render. To access the bottom toolbar after render, use getBottomToolbar.
" %>

<%@ attribute
	name="bodyBorder"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display an interior border on the body element of the panel, false to hide it (defaults to true). This only applies when border == true. If border == true and bodyBorder == false, the border will display as a 1px wide inset border, giving the entire body element an inset appearance.
" %>

<%@ attribute
	name="bodyStyle"
	type="java.lang.String"
	required="false"
	description="
(String/Object/Function)Custom CSS styles to be applied to the body element in the format expected by Ext.Element.applyStyles (defaults to null).
" %>

<%@ attribute
	name="border"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display the borders of the panel's body element, false to hide them (defaults to true). By default, the border is a 2px wide inset border, but this can be further altered by setting bodyBorder to false.
" %>

<%@ attribute
	name="bufferResize"
	type="java.lang.Object"
	required="false"
	description="
(Boolean/Number)When set to true (100 milliseconds) or a number of milliseconds, the layout assigned for this container will buffer the frequency it calculates and does a re-layout of components. This is useful for heavy containers or containers with a large amount of sub components that frequent calls to layout are expensive.
" %>

<%@ attribute
	name="buttonAlign"
	type="java.lang.String"
	required="false"
	description="
(String)The alignment of any buttons added to this panel. Valid values are 'right,' 'left' and 'center' (defaults to 'right').
" %>

<%@ attribute
	name="buttons"
	type="java.lang.String"
	required="false"
	description="
(Array)An array of Ext.Button configs used to add buttons to the footer of this panel.
" %>

<%@ attribute
	name="closable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display the 'close' tool button and allow the user to close the window, false to hide the button and disallow closing the window (default to true).
" %>

<%@ attribute
	name="closeAction"
	type="java.lang.String"
	required="false"
	description="
(String)The action to take when the close button is clicked. The default action is 'close' which will actually remove the window from the DOM and destroy it. The other valid option is 'hide' which will simply hide the window by setting visibility to hidden and applying negative offsets, keeping the window available to be redisplayed via the show method.
" %>

<%@ attribute
	name="cls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's Element (defaults to ''). This can be useful for adding customized styles to the component or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="collapseFirst"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to make sure the collapse/expand toggle button always renders first (to the left of) any other tools in the panel's title bar, false to render it last (defaults to true).
" %>

<%@ attribute
	name="collapsed"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to render the panel collapsed, false to render it expanded (defaults to false).
" %>

<%@ attribute
	name="collapsedCls"
	type="java.lang.String"
	required="false"
	description="
(String)A CSS class to add to the panel's element after it has been collapsed (defaults to 'x-panel-collapsed').
" %>

<%@ attribute
	name="collapsible"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to make the panel collapsible and have the expand/collapse toggle button automatically rendered into the header tool button area, false to keep the panel statically sized with no button (defaults to false).
" %>

<%@ attribute
	name="constrain"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to constrain the window to the viewport, false to allow it to fall outside of the viewport (defaults to false). Optionally the header only can be constrained using constrainHeader.
" %>

<%@ attribute
	name="constrainHeader"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to constrain the window header to the viewport, allowing the window body to fall outside of the viewport, false to allow the header to fall outside the viewport (defaults to false). Optionally the entire window can be constrained using constrain.
" %>

<%@ attribute
	name="contentEl"
	type="java.lang.String"
	required="false"
	description="
(String)The id of an existing HTML node to use as the panel's body content (defaults to '').
" %>

<%@ attribute
	name="ctCls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's container (defaults to ''). This can be useful for adding customized styles to the container or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="defaultButton"
	type="java.lang.String"
	required="false"
	description="
(String/Number/Button)The id / index of a button or a button instance to focus when this window received the focus.
" %>

<%@ attribute
	name="defaultType"
	type="java.lang.String"
	required="false"
	description="
(String)The default type of container represented by this object as registered in Ext.ComponentMgr (defaults to 'panel').
" %>

<%@ attribute
	name="defaults"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object that will be applied to all components added to this container either via the items config or via the add or insert methods. The defaults config can contain any number of name/value property pairs to be added to each item, and should be valid for the types of items being added to the container. For example, to automatically apply padding to the body of each of a set of contained Ext.Panel items, you could pass: defaults: {bodyStyle:'padding:15px'}.
" %>

<%@ attribute
	name="disabledClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class added to the component when it is disabled (defaults to 'x-item-disabled').
" %>

<%@ attribute
	name="draggable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to allow the window to be dragged by the header bar, false to disable dragging (defaults to true). Note that by default the window will be centered in the viewport, so if dragging is disabled the window may need to be positioned programmatically after render (e.g., myWindow.setPosition(100, 100);).
" %>

<%@ attribute
	name="expandOnShow"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to always expand the window when it is displayed, false to keep it in its current state (which may be collapsed) when displayed (defaults to true).
" %>

<%@ attribute
	name="footer"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to create the footer element explicitly, false to skip creating it. By default, when footer is not specified, if one or more buttons have been added to the panel the footer will be created automatically, otherwise it will not.
" %>

<%@ attribute
	name="header"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to create the header element explicitly, false to skip creating it. By default, when header is not specified, if a title is set the header will be created automatically, otherwise it will not. If a title is set but header is explicitly set to false, the header will not be rendered.
" %>

<%@ attribute
	name="headerAsText"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display the panel title in the header, false to hide it (defaults to true).
" %>

<%@ attribute
	name="height"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The height of this component in pixels (defaults to auto).
" %>

<%@ attribute
	name="hideBorders"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to hide the borders of each contained component, false to defer to the component's existing border settings (defaults to false).
" %>

<%@ attribute
	name="hideCollapseTool"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to hide the expand/collapse toggle button when collapsible = true, false to display it (defaults to false).
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
	name="html"
	type="java.lang.String"
	required="false"
	description="
(String/Object)An HTML fragment, or a DomHelper specification to use as the panel's body content (defaults to '').
" %>

<%@ attribute
	name="iconCls"
	type="java.lang.String"
	required="false"
	description="
(String)A CSS class that will provide a background image to be used as the panel header icon (defaults to '').
" %>

<%@ attribute
	name="id"
	type="java.lang.String"
	required="false"
	description="
(String)The unique id of this component (defaults to an auto-assigned id).
" %>

<%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="
(Mixed)A single item, or an array of child Components to be added to this container. Each item can be any type of object based on Ext.Component.

 Component config objects may also be specified in order to avoid the overhead of constructing a real Component object if lazy rendering might mean that the added Component will not be rendered immediately. To take advantage of this 'lazy instantiation', set the Ext.Component.xtype config property to the registered type of the Component wanted.

 For a list of all available xtypes, see Ext.Component. If a single item is being passed, it should be passed directly as an object reference (e.g., items: {...}). Multiple items should be passed as an array of objects (e.g., items: [{...}, {...}]).
" %>

<%@ attribute
	name="keys"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)A KeyMap config object (in the format expected by Ext.KeyMap.addBinding used to assign custom key handling to this panel (defaults to null).
" %>

<%@ attribute
	name="layout"
	type="java.lang.String"
	required="false"
	description="
(String)The layout type to be used in this container. If not specified, a default Ext.layout.ContainerLayout will be created and used. Valid values are: accordion, anchor, border, card, column, fit, form and table. Specific config values for the chosen layout type can be specified using layoutConfig.
" %>

<%@ attribute
	name="layoutConfig"
	type="java.lang.Object"
	required="false"
	description="
(Object)This is a config object containing properties specific to the chosen layout (to be used in conjunction with the layout config value). For complete details regarding the valid config options for each layout type, see the layout class corresponding to the type specified:

    * Ext.layout.Accordion
    * Ext.layout.AnchorLayout
    * Ext.layout.BorderLayout
    * Ext.layout.CardLayout
    * Ext.layout.ColumnLayout
    * Ext.layout.FitLayout
    * Ext.layout.FormLayout
    * Ext.layout.TableLayout


" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="manager"
	type="java.lang.String"
	required="false"
	description="
(Ext.WindowGroup)A reference to the WindowGroup that should manage this window (defaults to Ext.WindowMgr).
" %>

<%@ attribute
	name="maskDisabled"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to mask the panel when it is disabled, false to not mask it (defaults to true). Either way, the panel will always tell its contained elements to disable themselves when it is disabled, but masking the panel can provide an additional visual cue that the panel is disabled.
" %>

<%@ attribute
	name="maximizable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display the 'maximize' tool button and allow the user to maximize the window, false to hide the button and disallow maximizing the window (defaults to false). Note that when a window is maximized, the tool button will automatically change to a 'restore' button with the appropriate behavior already built-in that will restore the window to its previous size.
" %>

<%@ attribute
	name="minButtonWidth"
	type="java.lang.Integer"
	required="false"
	description="
(Number)Minimum width in pixels of all buttons in this panel (defaults to 75)
" %>

<%@ attribute
	name="minHeight"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The minimum height in pixels allowed for this window (defaults to 100). Only applies when resizable = true.
" %>

<%@ attribute
	name="minWidth"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The minimum width in pixels allowed for this window (defaults to 200). Only applies when resizable = true.
" %>

<%@ attribute
	name="minimizable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display the 'minimize' tool button and allow the user to minimize the window, false to hide the button and disallow minimizing the window (defaults to false). Note that this button provides no implementation -- the behavior of minimizing a window is implementation-specific, so the minimize event must be handled and a custom minimize behavior implemented for this option to be useful.
" %>

<%@ attribute
	name="modal"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to make the window modal and mask everything behind it when displayed, false to display it without restricting access to other UI elements (defaults to false).
" %>

<%@ attribute
	name="onEsc"
	type="java.lang.String"
	required="false"
	description="
(Function)Allows override of the built-in processing for the escape key. Default action is to close the Window (performing whatever action is specified in closeAction. To prevent the Window closing when the escape key is pressed, specify this as Ext.emptyFn (See Ext.emptyFn).
" %>

<%@ attribute
	name="plain"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to render the window body with a transparent background so that it will blend into the framing elements, false to add a lighter background color to visually highlight the body element and separate it more distinctly from the surrounding frame (defaults to false).
" %>

<%@ attribute
	name="plugins"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)An object or array of objects that will provide custom functionality for this component. The only requirement for a valid plugin is that it contain an init method that accepts a reference of type Ext.Component. When a component is created, if any plugins are available, the component will call the init method on each plugin, passing a reference to itself. Each plugin can then call methods or respond to events on the component as needed to provide its functionality.
" %>

<%@ attribute
	name="renderTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element that will be the container to render this component into. Using this config, a call to render() is not required.
" %>

<%@ attribute
	name="resizable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to allow user resizing at each edge and corner of the window, false to disable resizing (defaults to true).
" %>

<%@ attribute
	name="resizeHandles"
	type="java.lang.String"
	required="false"
	description="
(String)A valid Ext.Resizable handles config string (defaults to 'all'). Only applies when resizable = true.
" %>

<%@ attribute
	name="shadow"
	type="java.lang.String"
	required="false"
	description="
(Boolean/String)True (or a valid Ext.Shadow Ext.Shadow.mode value) to display a shadow behind the panel, false to display no shadow (defaults to 'sides'). Note that this option only applies when floating = true.
" %>

<%@ attribute
	name="shadowOffset"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The number of pixels to offset the shadow if displayed (defaults to 4). Note that this option only applies when floating = true.
" %>

<%@ attribute
	name="shim"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to disable the iframe shim in browsers which need one (defaults to true). Note that this option only applies when floating = true.
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
	name="tbar"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)The top toolbar of the panel. This can be either an Ext.Toolbar object or an array of buttons/button configs to be added to the toolbar. Note that this is not available as a property after render. To access the top toolbar after render, use getTopToolbar.
" %>

<%@ attribute
	name="title"
	type="java.lang.String"
	required="false"
	description="
(String)The title text to display in the panel header (defaults to ''). When a title is specified the header element will automatically be created and displayed unless header is explicitly set to false. If you don't want to specify a title at config time, but you may want one later, you must either specify a non-empty title (a blank space ' ' will do) or header:true so that the container element will get created.
" %>

<%@ attribute
	name="titleCollapse"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to allow expanding and collapsing the panel (when collapsible = true) by clicking anywhere in the header bar, false to allow it only by clicking to tool button (defaults to false).
" %>

<%@ attribute
	name="tools"
	type="java.lang.String"
	required="false"
	description="
(Array)An array of tool button configs to be added to the header tool area. Each tool config may contain the following properties: 

    * id : String

      Required. The type of tool to create. Values may be
          o toggle (Created by default when collapsible is true)
          o close
          o minimize
          o maximize
          o restore
          o gear
          o pin
          o unpin
          o right
          o left
          o up
          o down
          o refresh
          o minus
          o plus
          o help
          o search
          o save

 handler : Function
#

Required. The function to call when clicked. Arguments passed are:

    * event : Ext.EventObject

      The click event.
    * toolEl : Ext.Element

      The tool Element.
    * Panel : Ext.Panel

      The host Panel

 scope : Object
#

The scope in which to call the handler.
 qtip : String/Object
#

A tip string, or a config argument to Ext.QuickTip.register
 hidden : Boolean
#

True to initially render hidden.
 on : Object
#

A listener config object specifiying event listeners in the format of an argument to addListener
  Example usage: 

tools:[{id:'refresh',// hidden:true,handler: function(event, toolEl, panel){// refresh logic}}]

 Note that apart from the toggle tool which is provided when a panel is collapsible, these tools only provide the visual button. Any required functionality must be provided by adding handlers that implement the necessary behavior.
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
	name="onActivate"
	type="java.lang.String"
	required="false"
	description="
( Ext.Window this )Fires after the window has been visually activated via setActive.
" %>

<%@ attribute
	name="onAdd"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Container this, Ext.Component component, Number index )Fires after any Ext.Component is added or inserted into the container.
" %>

<%@ attribute
	name="onAfterlayout"
	type="java.lang.String"
	required="false"
	description="
( Ext.Container this, ContainerLayout layout )Fires when the components in this container are arranged by the associated layout manager.
" %>

<%@ attribute
	name="onBeforeadd"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Container this, Ext.Component component, Number index )Fires before any Ext.Component is added or inserted into the container.A handler can return false to cancel the add.
" %>

<%@ attribute
	name="onBeforeclose"
	type="java.lang.String"
	required="false"
	description="
( Ext.Panel p )Fires before the Panel is closed.Note that Panels do not directly support being closed, but somePanel subclasses do (like Ext.Window).This event only applies to such subclasses.A handler can return false to cancel the close.
" %>

<%@ attribute
	name="onBeforecollapse"
	type="java.lang.Boolean"
	required="false"
	description="
( Ext.Panel p, Boolean animate )Fires before the Panel is collapsed.A handler can return false to cancel the collapse.
" %>

<%@ attribute
	name="onBeforedestroy"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is destroyed. Return false to stop the destroy.
" %>

<%@ attribute
	name="onBeforeexpand"
	type="java.lang.Boolean"
	required="false"
	description="
( Ext.Panel p, Boolean animate )Fires before the Panel is expanded.A handler can return false to cancel the expand.
" %>

<%@ attribute
	name="onBeforehide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is hidden. Return false to stop the hide.
" %>

<%@ attribute
	name="onBeforeremove"
	type="java.lang.String"
	required="false"
	description="
( Ext.Container this, Ext.Component component )Fires before any Ext.Component is removed from the container.A handler can returnfalse to cancel the remove.
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
	name="onBodyresize"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Panel p, Number width, Number height )Fires after the Panel has been resized.
" %>

<%@ attribute
	name="onClose"
	type="java.lang.String"
	required="false"
	description="
( Ext.Panel p )Fires after the Panel is closed.Note that Panels do not directly support being closed, but somePanel subclasses do (like Ext.Window).
" %>

<%@ attribute
	name="onCollapse"
	type="java.lang.String"
	required="false"
	description="
( Ext.Panel p )Fires after the Panel has been collapsed.
" %>

<%@ attribute
	name="onDeactivate"
	type="java.lang.String"
	required="false"
	description="
( Ext.Window this )Fires after the window has been visually deactivated via setActive.
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
	name="onExpand"
	type="java.lang.String"
	required="false"
	description="
( Ext.Panel p )Fires after the Panel has been expanded.
" %>

<%@ attribute
	name="onHide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is hidden.
" %>

<%@ attribute
	name="onMaximize"
	type="java.lang.String"
	required="false"
	description="
( Ext.Window this )Fires after the window has been maximized.
" %>

<%@ attribute
	name="onMinimize"
	type="java.lang.String"
	required="false"
	description="
( Ext.Window this )Fires after the window has been minimized.
" %>

<%@ attribute
	name="onMove"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Component this, Number x, Number y )Fires after the component is moved.
" %>

<%@ attribute
	name="onRemove"
	type="java.lang.String"
	required="false"
	description="
( Ext.Container this, Ext.Component component )Fires after any Ext.Component is removed from the container.
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
( Ext.Window this, Number width, Number height )Fires after the window has been resized.
" %>

<%@ attribute
	name="onRestore"
	type="java.lang.String"
	required="false"
	description="
( Ext.Window this )Fires after the window has been restored to its original size after being maximized.
" %>

<%@ attribute
	name="onShow"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is shown.
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
	name="onTitlechange"
	type="java.lang.String"
	required="false"
	description="
( Ext.Panel p, String The )Fires after the Panel title has been set or changed.
" %>

<%-- Events _____________________________END --%>


<%-- Tag Body  _____________________________START --%>
<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A specialized panel intended for use as an application window. Windows are floated and draggable by default, and also provide specific behavior like the ability to maximize and restore (with an event for minimizing, since the minimize behavior is application-specific). Windows can also be linked to a Ext.WindowGroup or managed by the Ext.WindowManager to provide grouping, activation, to front/back and other application-specific behavior." %>
	
	<c:if test="${empty(id)}">
		<c:set var="id"><extutil:getTagId tag="<%= this %>" /></c:set>
	</c:if>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		javaScript="autoLoad,keys,tools,manager"
		include="*" 
		tagJspContext="<%= jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />
	
	<%-- Process JSP body --%>
	<jsp:doBody var="jspBody" scope="page" />	
		
		<div class="x-hide-display" id="${id}El">${jspBody}</div>
		
		<c:set var="item">
			    new Ext.Window({
			  		<c:forEach items="${configMap}" var="config">
			  			${config.key}:${config.value},
			  		</c:forEach>
			  		 <% if(BeanUtils.getProperty(this,"items")!=null){ %> 
				        items:[
							<% 
								String items = BeanUtils.getProperty(this,"items");
								jspContext.getOut().write(items.substring(0,items.length()-1));
							 %>
				        ],
			  		 <% } %> 
			  		 
			  		<% if(BeanUtils.getProperty(this,"tbar")!=null){ %> 
				        tbar:[
							<% 
								String tbar = BeanUtils.getProperty(this,"tbar");
								jspContext.getOut().write(tbar.substring(0,tbar.length()-1));
							 %>
				        ],
			  		 <% } %> 
			  	 
			  		
			  		<% if(BeanUtils.getProperty(this,"bbar")!=null){ %> 
			  		
				        bbar:[
							<% 
								String bbar = BeanUtils.getProperty(this,"bbar");
								jspContext.getOut().write(bbar.substring(0,bbar.length()-1));
							 %>
				        ],
			  		<%} %>
			  		
			  		<% if(BeanUtils.getProperty(this,"buttons")!=null){ %>
				        buttons:[
							<% 
								String buttons = BeanUtils.getProperty(this,"buttons");
								jspContext.getOut().write(buttons.substring(0,buttons.length()-1));
							 %>
				        ],
			  		<%} %>
			  		<% if(BeanUtils.getProperty(this,"tools")!=null){ %>
			  		 
				        tools:[
							<% 
								String tools = BeanUtils.getProperty(this,"tools");
								jspContext.getOut().write(tools.substring(0,tools.length()-1));
							 %>
				        ],
			  		<%} %>
			  		listeners:{
				  		<c:forEach items="${eventsMap}" var="event" varStatus="status">
				  			<c:if test="${fn:indexOf(event.value,'function(){')==-1}">
					  			${event.key}:function(){${event.value}}			  			
				  			</c:if>
			  				<c:if test="${fn:indexOf(event.value,'function(){')>-1}">
					  			${event.key}:${event.value}
				  			</c:if>
				  			${status.last?'':','}
				  		</c:forEach>
			  		},
					contentEl:'${id}El'
			    })
		</c:set>
	
	<c:set var="parentClass"><extutil:getParentTagClass tag="<%= this%>"/></c:set>
	<c:if test="${parentClass=='viewport_tag' || parentClass=='body_tag'}">
		<extutil:setExtCommons>${id} = ${item};</extutil:setExtCommons>
	</c:if>
	
	<c:if test="${parentClass!='viewport_tag' && parentClass!='body_tag'}">
		<extutil:setParentProperties tag="<%=this%>" items="${item},"/>
	</c:if>
	 
		
<%-- Tag Body  _____________________________END --%>