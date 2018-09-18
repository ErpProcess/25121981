<script >  
 var mapColumsbean=[  
  { "sTitle": "Rem_id"       ,"sName": "reg_id"                   , "bSortable": "true"   },  
  { "sTitle": "Avoir N°"       ,"sName": "factclient.fact_clt_id"                     , "bSortable": "true"   },  
  { "sTitle": "client"       ,"sName": "factclient.client.clt_lib"                    , "bSortable": "true"   },
  { "sTitle": "${reg_mod}"       ,"sName": "mode.data_libelle"                    , "bSortable": "true"   },  
  { "sTitle": "${reg_date}"       ,"sName": "reg_date"                      , "bSortable": "true"   },   
  { "sTitle": "${montant_facture}"       ,"sName": "montant_facture"            , "bSortable": "true"   },    
  { "sTitle": "${montant_restant}"       ,"sName": "montant_restant"      , "bSortable": "true"   },  
  { "sTitle": "${mode_op}"       ,"sName": "modeBean.fct_libelle"          , "bSortable": "true"   },  
  { "sTitle": "${num_piece}"       ,"sName": "num_piece"                     , "bSortable": "true"   },  
  { "sTitle": "${reg_nature}"       ,"sName": "nature.data_libelle"                        , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
