<script >  
 var mapColumsbean=[  
  { "sTitle": "${vente_id}"       ,"sName": "vente_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${vente_date}"        ,"sName": "vente_date"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "Heure"              ,"sName": "time_cre"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "${clt_id}"         ,"sName": "client.clt_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "A.l'ordre"         ,"sName": "vente_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
