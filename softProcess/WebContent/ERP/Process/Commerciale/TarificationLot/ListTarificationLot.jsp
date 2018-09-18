<script >  
 var mapColumsbean=[  
  { "sTitle": "${num_serie}"            ,"sName": "pk.lot.pk.num_serie"                ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "date serie"            ,"sName": "pk.lot.date_serie"                ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "article"           ,"sName": "pk.lot.fkCode_barre.pk.code_barre"                ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Libelle"           ,"sName": "pk.lot.fkCode_barre.designation_libelle"                ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "date Util"            ,"sName": "pk.lot.date_utilisation"                ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${depot_id}"             ,"sName": "pk.lot.pk.depot.depot_libelle"      ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Groupe"             ,"sName": "pk.vente.groupe.type_trf_lib"      ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${date_trf}"             ,"sName": "pk.vente.date_trf"                  ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${tarif_vente_id}"       ,"sName": "pk.vente.tarif_unit_vente"          ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${_mode}"                ,"sName": "pk.vente.modeBean.fct_libelle"          ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
