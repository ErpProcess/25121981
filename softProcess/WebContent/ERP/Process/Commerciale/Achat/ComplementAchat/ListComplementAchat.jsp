<script >  
var mapColumsbean=[  
  { "sTitle": "${complet_date}"      ,"sName": "complet_date"                   ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${complet_id}"        ,"sName": "complet_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${depot_id}"          ,"sName": "achat.pk.depot.depot_libelle"  ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${frsref}"            ,"sName": "achat.frsBean.frsref"          ,"sWidth": "10%"   , "bSortable": "true"   },
    
  { "sTitle": "${achat_date}"        ,"sName": "achat.pk.achat_date"           ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  { "sTitle": "${achat_id}"          ,"sName": "achat.pk.achat_id"             ,"sWidth": "10%"   , "bSortable": "true"   },
  
  { "sTitle": "${_mode}"             ,"sName": "modeBean.fct_libelle"          ,"sWidth": "10%"   , "bSortable": "true"   },   
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
