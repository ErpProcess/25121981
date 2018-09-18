<script >  
 var mapColumsbean=[  
  { "sTitle": "${pack_id}"           ,"sName": "pack_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${pack_libelle}"        ,"sName": "pack_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${pack_ordre}"             ,"sName": "pack_ordre"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />   
 <table id="${nameGrid}"   class="display"  style="width: 100%;"  ><thead></thead></table>   
