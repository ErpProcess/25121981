
<script >  
 var mapColumsbean=[  
  { "sTitle": "${sousmod_id}"       ,"sName": "sousmod_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${mod_id}"       ,"sName": "moduleBean.mod_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sousmod_libelle}"       ,"sName": "sousmod_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sousmod_obs}"       ,"sName": "sousmod_obs"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sousmod_table}"       ,"sName": "sousmod_table"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${soumod_schema}"       ,"sName": "soumod_schema"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "Ordre"       ,"sName": "sousmod_ordre"                         ,"sWidth": "10%"   , "bSortable": "true"   },
  ];
</script> 
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table> 
