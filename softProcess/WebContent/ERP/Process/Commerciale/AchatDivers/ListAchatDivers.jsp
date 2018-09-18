<script >  
 var mapColumsbean=[  
  { "sTitle": "N°"                  ,"sName": "ach_non_id"                     ,"sWidth": "2%"    , "bSortable": "true"   },  
  { "sTitle": "${date_achat}"       ,"sName": "date_achat"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${libelle_achat}"    ,"sName": "libelle_achat"                  ,"sWidth": "30%"   , "bSortable": "true"   },  
  { "sTitle": "${prix_achat}"       ,"sName": "prix_achat"                     ,"sWidth": "10%"   , "bSortable": "true" ,"sAlign":"right"  },  
  { "sTitle": "${observation}"      ,"sName": "observation"                    ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />  