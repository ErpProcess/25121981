<script > 
 var mapColumsbean=[  
  { "sTitle": "${date_trf}"              ,"sName": "date_trf"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "code"                 ,"sName": "fkCode_barre.pk.code_barre"                     ,"sWidth": "25%"   , "bSortable": "true"   }, 
  { "sTitle": "article"                 ,"sName": "fkCode_barre.designation_libelle"                     ,"sWidth": "25%"   , "bSortable": "true"   }, 
  { "sTitle": "${prix_unit_achat}"       ,"sName": "cout.tarif_unit_article"     ,"sClass" : "alignRight"   ,"sWidth": "15%"   , "bSortable": "true"   ,
   "mRender": function (data, type, full) {return formatNumberJs(data,3);}  },  
  { "sTitle": "${type_trf_id}"           ,"sName": "groupe.type_trf_lib"                     ,"sWidth": "15%"   , "bSortable": "true"   },  
  { "sTitle": "${prix_unit_vente}"       ,"sName": "tarif_unit_vente"     , "sClass" : "alignRight"   ,"sWidth": "10%"   , "bSortable": "true"   ,
  "mRender": function (data, type, full) {   if( full[6]=="Eu"  ||  full[6]=="USA") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
//   { "sTitle": "Dv"                       ,"sName": "devise.dev_abrv"                     ,"sWidth": "2%"   , "bSortable": "true"   }, 
  { "sTitle": "${taux_remise}"           ,"sName": "taux_remise"                     ,"sWidth": "5%"   , "bSortable": "true"   },  
  { "sTitle": "${tva_id}"                ,"sName": "tvaBean.tva_libelle"                     ,"sWidth": "5%"   , "bSortable": "true"   },
//   { "sTitle": "Lot"                 ,"sName": "num_serie"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
