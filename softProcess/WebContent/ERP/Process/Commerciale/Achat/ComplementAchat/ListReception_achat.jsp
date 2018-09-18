<script >  
 var mapColumsbean=[  
  
  { "sTitle": "demande"           ,"sName": "demande_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_id}"       ,"sName": "pk.achat_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${achat_date}"     ,"sName": "pk.achat_date"                   ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_time}"     ,"sName": "time_cre"                        ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${achat_libelle}"  ,"sName": "achat_libelle"                   ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${frs_id}"         ,"sName": "frsBean.frsref"                  ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${depot_id}"       ,"sName": "pk.depot.depot_libelle"          ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"            ,"sWidth": "10%"   , "bSortable": "true"   },    
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
	 