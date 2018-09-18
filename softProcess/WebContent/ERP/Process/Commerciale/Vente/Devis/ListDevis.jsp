<script >  
 var mapColumsbean=[  
  { "sTitle": "${devis_id}"       ,"sName": "devis_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${dev_date}"       ,"sName": "dev_date"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Heure"              ,"sName": "time_cre"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  
  { "sTitle": "${clt_id}"         ,"sName": "client.clt_lib"                     ,"sWidth": "25%"   , "bSortable": "true"   }, 
  { "sTitle": "${dev_lib}"        ,"sName": "dev_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
