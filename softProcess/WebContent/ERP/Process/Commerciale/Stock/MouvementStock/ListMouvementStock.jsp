<script >  
 var mapColumsbean=[  
  { "sTitle": "Date Mvt"       ,"sName": "date_mvt_serie"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${document_com_id}"       ,"sName": "pk.document_com_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${nature_mvt_id}"  ,"sName": "pk.nat_mvt.nature_mvt_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${depot_id}"       ,"sName": "pk.serieBean.pk.depot.depot_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${code_barre}"       ,"sName": "pk.serieBean.fkCode_barre.pk.code_barre"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "Designationsss"       ,"sName": "pk.serieBean.fkCode_barre.designation_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },
   
     
  { "sTitle": "${quantite}"       ,"sName": "quantite_operation"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
   
  { "sTitle": "${montant_ht}"     ,"sName": "montant_ht_operation"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${montant_tva}"    ,"sName": "montant_tva_operation"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
    
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
 
