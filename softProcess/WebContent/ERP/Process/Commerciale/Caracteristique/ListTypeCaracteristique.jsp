<script >  
 var mapColumsbean=[  
  { "sTitle": "${carac_id}"            ,"sName": "carac_id"                                ,"sWidth": "300px"   , "bSortable": "true"   },  
  { "sTitle": "${carac_libelle}"       ,"sName": "carac_libelle"                           ,"sWidth": "800px"   , "bSortable": "true"   },  
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />   
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table>