    
<script >  
 var mapColumsbean=[  
  { "sTitle": "code sous module"       ,"sName": "sousmod_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sousmod_id}"       ,"sName": "sousmod_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sousmod_table}"       ,"sName": "sousmod_table"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${soumod_schema}"       ,"sName": "soumod_schema"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table> 
	 