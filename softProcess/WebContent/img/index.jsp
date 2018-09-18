<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>ExtTLD Examples</title>
</head>
<body>
	<ext:body>
		<ext:viewport layout="border">
			<ext:panel region="west" width="100" split="true">
				<ext:tree.treePanel title="Examples" border="false">
					<ext:tree.treeNode text="Layouts">
						<ext:tree.treeNode text="Accordion Layout" onClick="loadExample('layout.accordion')" />
						<ext:tree.treeNode text="Border Layout" onClick="loadExample('layout.border')" />
						<ext:tree.treeNode text="Viewport" onClick="loadExample('layout.viewport')" />
					</ext:tree.treeNode>
					<ext:tree.treeNode text="Tree">
						<ext:tree.treeNode text="Tree Manual Code" onClick="loadExample('tree.manual')" />
						<ext:tree.treeNode text="Tree Loader" onClick="loadExample('tree.loader')" />
						<ext:tree.treeNode text="Tree Editor" onClick="loadExample('tree.editor')" />
						<ext:tree.treeNode text="Tree Sorter" onClick="loadExample('tree.sorter')" />
					</ext:tree.treeNode>
					<ext:tree.treeNode text="Grid">
						<ext:tree.treeNode text="Manual Data" onClick="loadExample('grid.inline')" />
						<ext:tree.treeNode text="Array Loader" onClick="loadExample('grid.array')" />
						<ext:tree.treeNode text="XML Loader" onClick="loadExample('grid.xmlload')" />
						<ext:tree.treeNode text="Grid Editor" onClick="loadExample('grid.editor')" />
						<ext:tree.treeNode text="Grouping Grid" onClick="loadExample('grid.grouping')" />
						<ext:tree.treeNode text="Simple Loader" onClick="loadExample('grid.simpleload')" />
						<ext:tree.treeNode text="Property Grid" onClick="loadExample('grid.propertygrid')" />
					</ext:tree.treeNode>
					<ext:tree.treeNode text="Form">
						<ext:tree.treeNode text="Basic Fields" onClick="loadExample('form.basic')" />
						<ext:tree.treeNode text="Combobox" onClick="loadExample('form.combo')" />
						<ext:tree.treeNode text="Form Tabs" onClick="loadExample('form.tabs')" />
						<ext:tree.treeNode text="Form Fieldsets" onClick="loadExample('form.fieldsets')" />
						<ext:tree.treeNode text="Form HTML Editor" onClick="loadExample('form.editor')" />
						<ext:tree.treeNode text="XML Loader" onClick="loadExample('form.xmlloader')" />
					</ext:tree.treeNode>
					<ext:tree.treeNode text="Menu" onClick="loadExample('menu')" />
					<ext:tree.treeNode text="Tab Panel" onClick="loadExample('tabpanel')" />
					<ext:tree.treeNode text="Toolbar" onClick="loadExample('toolbar')" />
					<ext:tree.treeNode text="Window" onClick="loadExample('window')" />
				</ext:tree.treePanel>
			</ext:panel>
			
			<ext:tabPanel activeItem="0" border="true" region="center" defaults="{autoScroll:true}">
				<ext:panel title="Code preview">
					<ext:toolbar>
						<ext:toolbar.button text="Run code" id="runCodeBtn" />
						<ext:toolbar.button text="Ext 2.0 API" id="runDocsBtn" />
					</ext:toolbar>
					<div id="codePreview"><img src="img/examples/code/window.png" /></div>
				</ext:panel>
				<ext:panel title="Component preview">
					<div id="compPreview"><img src="img/examples/components/window.png" /></div>
				</ext:panel>
			</ext:tabPanel>
		</ext:viewport>
	</ext:body>
	
	<script>
		function loadExample(exId){
			var exampleObj = examplesSrc[exId];
			Ext.getDom("codePreview").innerHTML = "<img src='img/examples/code/"+exampleObj.img+"' />";
			Ext.getDom("compPreview").innerHTML = "<img src='img/examples/components/"+exampleObj.img+"' />";
			
			runCodeBtn.setHandler(function(){
				window.open("examples/"+exampleObj.example);
			})
			
			runDocsBtn.setHandler(function(){
				window.open("${pageContext.request.contextPath}/js/ext-2.0/docs/?class="+exampleObj.docs);
			})
			
		}
		
		examplesSrc = {
			"tree.manual":{
				title:"Tree / Manual Nodes",
				img:"tree/inline.png",
				example:"tree/treeInline.htm",
				docs:"Ext.tree.TreeNode"
			},
			"tree.loader":{
				title:"Tree / Tree Loader",
				img:"tree/loader.png",
				example:"tree/treeLoader.htm",
				docs:"Ext.tree.TreeLoader"
			},
			"tree.sorter":{
				title:"Tree / Tree Sorter",
				img:"tree/sorter.png",
				example:"tree/treeSorter.htm",
				docs:"Ext.tree.TreeSorter"
			},
			"tree.editor":{
				title:"Tree / Tree Editor",
				img:"tree/editor.png",
				example:"tree/treeEditor.htm",
				docs:"Ext.tree.TreeEditor"
			},
			"window":{
				title:"Window",
				img:"window.png",
				example:"window.htm",
				docs:"Ext.Window"
			},
			"toolbar":{
				title:"Toobar",
				img:"toolbar.png",
				example:"toolbar.htm",
				docs:"Ext.Toolbar"
			},
			"tabpanel":{
				title:"Tab Panel",
				img:"tabpanel.png",
				example:"tabpanel.htm",
				docs:"Ext.TabPanel"
			},
			"menu":{
				title:"Menu",
				img:"menu.png",
				example:"menu.htm",
				docs:"Ext.menu.Menu"
			},
			"layout.accordion":{
				title:"Accordion Layout",
				img:"layout/accordion.png",
				example:"layout/accordion.htm",
				docs:"Ext.layout.Accordion"
			},
			"layout.border":{
				title:"Border Layout",
				img:"layout/border.png",
				example:"layout/border.htm",
				docs:"Ext.layout.BorderLayout"
			},
			"layout.viewport":{
				title:"Viewport Layout",
				img:"layout/viewport.png",
				example:"layout/viewport.htm",
				docs:"Ext.Viewport"
			},
			"grid.xmlload":{
				title:"Grid - XML Loader",
				img:"grid/xmlload.png",
				example:"grid/xmlload.htm",
				docs:"Ext.data.XmlReader"
			},
			"grid.simpleload":{
				title:"Grid - Simple Loader",
				img:"grid/simpleload.png",
				example:"grid/simpleload.htm",
				docs:"Ext.data.SimpleStore"
			},
			"grid.propertygrid":{
				title:"Grid - Property Grid",
				img:"grid/propertygrid.png",
				example:"grid/propertygrid.htm",
				docs:"Ext.grid.PropertyGrid"
			},
			"grid.inline":{
				title:"Grid - Manual Data",
				img:"grid/inline.png",
				example:"grid/inline.htm",
				docs:"Ext.grid.GridPanel"
			},
			"grid.grouping":{
				title:"Grid - Grouping Grid",
				img:"grid/grouping.png",
				example:"grid/grouping.htm",
				docs:"Ext.data.GroupingStore"
			},
			"grid.array":{
				title:"Grid - Array Loader",
				img:"grid/array.png",
				example:"grid/array.htm",
				docs:"Ext.data.ArrayReader"
			},
			"grid.editor":{
				title:"Grid - Editor",
				img:"grid/editor.png",
				example:"grid/editor.htm",
				docs:"Ext.grid.EditorGridPanel"
			},
			"form.xmlloader":{
				title:"Form - XML Loader",
				img:"form/xmlloader.png",
				example:"form/xmlloader.htm",
				docs:"Ext.form.FormPanel"
			},
			"form.tabs":{
				title:"Form - Tabs",
				img:"form/tabs.png",
				example:"form/tabs.htm",
				docs:"Ext.TabPanel"
			},
			"form.fieldsets":{
				title:"Form - Fieldsets",
				img:"form/fieldsets.png",
				example:"form/fieldsets.htm",
				docs:"Ext.form.FieldSet"
			},
			"form.editor":{
				title:"Form - HTML Editor",
				img:"form/editor.png",
				example:"form/editor.htm",
				docs:"Ext.form.HtmlEditor"
			},
			"form.combo":{
				title:"Form - Combobox",
				img:"form/combo.png",
				example:"form/combo.htm",
				docs:"Ext.form.FormPanel"
			},
			"form.basic":{
				title:"Form - Basic Fields",
				img:"form/basic.png",
				example:"form/basic.htm",
				docs:"Ext.form.FormPanel"
			}
		}

	</script>
</body>
</html>