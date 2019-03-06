<script >  
 var mapColumsbean=[  
  { "sTitle": "${config_id}"       ,"sName": "config_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${soc_id}"       ,"sName": "fk_etab_Bean.pk_etab.soc_bean.soc_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${etab_id}"       ,"sName": "fk_etab_Bean.pk_etab.etab_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${sousmod_id}"       ,"sName": "sousMod.sousmod_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${user_list}"       ,"sName": "user_list"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
