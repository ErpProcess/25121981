<script >  
var mapColumsbean=[  
  { "sTitle": "${retour_date}"       ,"sName": "retour_date"                   ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${retour_id}"         ,"sName": "retour_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${depot_id}"          ,"sName": "achat.depot.depot_libelle"  ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${frsref}"            ,"sName": "achat.frsBean.frsref"          ,"sWidth": "10%"   , "bSortable": "true"   },
    
  { "sTitle": "${achat_date}"        ,"sName": "achat.achat_date"           ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  { "sTitle": "${achat_id}"          ,"sName": "achat.achat_id"             ,"sWidth": "10%"   , "bSortable": "true"   },
  
  { "sTitle": "${_mode}"             ,"sName": "modeBean.fct_libelle"          ,"sWidth": "10%"   , "bSortable": "true"   },   
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
