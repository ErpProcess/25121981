<script >  
 var mapColumsbean=[  
  { "sTitle": "${fct_id}"       ,"sName": "fct_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${fct_libelle}"       ,"sName": "fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${fct_icon}"       ,"sName": "fct_icon"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table>
