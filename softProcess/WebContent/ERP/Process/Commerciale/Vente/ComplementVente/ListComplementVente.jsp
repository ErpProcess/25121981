<script >  
 var mapColumsbean=[  
  { "sTitle": "${comp_vente_id}"         ,"sName": "comp_vente_id"                ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${vente_id}"              ,"sName": "vente.vente_id"               ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "client"              ,"sName": "vente.client.clt_lib"         ,"sWidth": "10%"   , "bSortable": "true"   },    
  { "sTitle": "date"       ,"sName": "comp_vente_date"              ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${time_cre}"              ,"sName": "time_cre"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
