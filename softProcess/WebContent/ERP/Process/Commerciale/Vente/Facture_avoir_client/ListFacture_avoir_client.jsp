<script >  
 var mapColumsbean=[  
  { "sTitle": "N°avoir"            ,"sName": "avoir_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "avoir date "        ,"sName": "avoir_date"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "Facture"            ,"sName": "factclient.fact_clt_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${clt_id}"          ,"sName": "factclient.client.clt_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${_mode}"           ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
