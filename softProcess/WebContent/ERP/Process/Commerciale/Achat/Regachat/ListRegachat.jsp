<script >  
dataGridConfig_width="100%"; 
 var mapColumsbean=[  
  { "sTitle": "${reg_frs_id}"        ,"sName": "reg_frs_id"              , "sWidth": "20%"          , "bSortable": "true"   },  
  { "sTitle": "${fact_frs_id}"       ,"sName": "fact_frs.fact_frs_id"    , "sWidth": "20%"                    , "bSortable": "true"   },  
  { "sTitle": "frs"              ,"sName": "fact_frs.frs.frsref"       , "sWidth": "10%"              , "bSortable": "true"   },
  { "sTitle": "${reg_date}"       ,"sName": "reg_date"                , "sWidth": "7%"       , "bSortable": "true"   },  
  { "sTitle": "${reg_nbr_echeance}"      ,"sName": "reg_nbr_echeance"  , "sWidth": "5%"    , "bSortable": "true"   },  
  { "sTitle": "${montant_facture}"       ,"sName": "montant_facture"    , "sWidth": "10%"         , "bSortable": "true"  ,"sClass" : "alignRight"  ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }  },  
  { "sTitle": "${montant_avance}"        ,"sName": "montant_avance"      , "sWidth": "10%"       , "bSortable": "true"  ,"sClass" : "alignRight"   ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }  },  
  { "sTitle": "${montant_restant}"       ,"sName": "montant_restant"   , "sWidth": "10%"    , "bSortable": "true"   ,"sClass" : "alignRight"        ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }  },  
  { "sTitle": "${mode_op}"               ,"sName": "modeBean.fct_libelle"      , "sWidth": "5%"     , "bSortable": "true"   },    
  { "sTitle": "${reg_nature}"            ,"sName": "nature.data_libelle"    , "sWidth": "5%"                     , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />  
