
<script >  
 var mapColumsbean=[  
  
  { "sTitle": "ModuleId"       ,"sName": "moduleBean.mod_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Module"       ,"sName": "moduleBean.mod_libelle"                     ,"sWidth": "20%"   , "bSortable": "true"   }, 
 
  { "sTitle": "SModule"       ,"sName": "sousmod_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "Id"       ,"sName": "sousmod_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Code"       ,"sName": "sousmod_code"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  { "sTitle": "Ordre"       ,"sName": "sousmod_ordre"                         ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "module_list"       ,"sName": "module_list"                         ,"sWidth": "10%"   , "bSortable": "true"   },
  ];
</script> 
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table> 
