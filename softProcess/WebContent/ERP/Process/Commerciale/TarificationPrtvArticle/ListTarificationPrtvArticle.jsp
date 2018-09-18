<script > 
 var mapColumsbean=[  
  { "sTitle": "Code"                     ,"sName": "tarif_prim_id"                            ,"sWidth": "5%"   , "bSortable": "true"   }, 
  { "sTitle": "date"                     ,"sName": "date_prim_trf"                          ,"sWidth": "5%"   , "bSortable": "true"   },  
  { "sTitle": "référence"                ,"sName": "fkCode_barre.pk.code_barre"             ,"sWidth": "25%"   , "bSortable": "true"   },  
  { "sTitle": "Désignation"              ,"sName": "fkCode_barre.designation_libelle"       ,"sWidth": "30%"   , "bSortable": "true"   }, 
  { "sTitle": "Tarif"           ,"sName": "tarif_unit_article"   ,"sClass" : "alignRight"   ,"sWidth": "5%"   , "bSortable": "true"  
   ,  "mRender": function (data, type, full) {return formatNumberJs(data,3);}  },  
  { "sTitle": "Groupe"                   ,"sName": "groupe.grp_trf_lib"                 ,"sWidth": "15%"   , "bSortable": "true"   },  
  { "sTitle": "TVA"                      ,"sName": "tvaBean.tva_libelle"                ,"sWidth": "5%"    , "bSortable": "true"   },
  { "sTitle": "Remise"                   ,"sName": "taux_remise"                        ,"sWidth": "5%"    , "bSortable": "true"   },  
  { "sTitle": "${_mode}"                 ,"sName": "modeBean.fct_libelle"               ,"sWidth": "5%"    , "bSortable": "true"   },  
  
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
  