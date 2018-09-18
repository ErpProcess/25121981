<script >  
 var mapColumsbean=[  
  { "sTitle": "${liv_id}"             ,"sName": "liv_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${liv_libelle}"        ,"sName": "liv_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "Vente"                 ,"sName": "vente.vente_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${montant_livraison}"  ,"sName": "montant_livraison"                     ,"sWidth": "10%"   , "bSortable": "true",
    "mRender": function (data, type, full) {return formatNumberJs(data,3);}    ,"sClass" : "alignRight"   ,"bVisible": true    },   
  { "sTitle": "${liv_date}"           ,"sName": "liv_date"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${time_cre}"           ,"sName": "time_cre"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
   
  { "sTitle": "${trans_id}"           ,"sName": "trans.trans_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${mode_op}"            ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
 
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
montant_livraison