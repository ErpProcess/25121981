
<script >  
 var mapColumsbean=[  
  { "sTitle": "${fam_id}"       ,"sName": "fam_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${fam_lib}"       ,"sName": "fam_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${fam_ordre}"       ,"sName": "fam_ordre"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${fam_type}"       ,"sName": "type.typefam_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${tvacod}"       ,"sName": "tvacod"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sitcod}"       ,"sName": "sitcod"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table>
