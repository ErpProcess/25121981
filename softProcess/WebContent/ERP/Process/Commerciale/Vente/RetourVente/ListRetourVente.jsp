<script >  
 var mapColumsbean=[  
  { "sTitle": "${ret_vente_id}"         ,"sName": "ret_vente_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${vente_id}"             ,"sName": "vente.vente_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${vente_id}"             ,"sName": "vente.client.clt_lib"                     ,"sWidth": "30%"   , "bSortable": "true"   },  
  { "sTitle": "${ret_vente_date}"       ,"sName": "ret_vente_date"                     ,"sWidth": "7%"   , "bSortable": "true"   },  
  { "sTitle": "Heure"              ,"sName": "time_cre"                     ,"sWidth": "7%"   , "bSortable": "true"   },   
  { "sTitle": "${_mode}"              ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
