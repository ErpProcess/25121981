<script >  
 var mapColumsbean=[  
  { "sTitle": "${cmd_id}"       ,"sName": "cmd_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${cmd_date}"       ,"sName": "cmd_date"                     ,"sWidth": "5%"   , "bSortable": "true"   },  
  { "sTitle": "Heure"              ,"sName": "time_cre"                     ,"sWidth": "5%"   , "bSortable": "true"   },   
  { "sTitle": "${clt_id}"       ,"sName": "client.clt_lib"                     ,"sWidth": "25%"   , "bSortable": "true"   },  
  { "sTitle": "${cmd_libelle}"       ,"sName": "cmd_libelle"                     ,"sWidth": "20%"   , "bSortable": "true"   },
   { "sTitle": "${_mode}"       ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
