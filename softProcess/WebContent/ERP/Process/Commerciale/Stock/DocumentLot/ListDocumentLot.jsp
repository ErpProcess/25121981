<script >  
 var mapColumsbean=[  
  { "sTitle": "date lot"                  ,"sName": "date_serie"                         ,"sWidth": "5%"   , "bSortable": "true"   }, 
  //{ "sTitle": "${date_utilisation}"       ,"sName": "date_utilisation"                   ,"sWidth": "5%"   , "bSortable": "true"   },
  { "sTitle": "${num_lot}"                ,"sName": "pk.num_serie"                       ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Dépot"                     ,"sName": "pk.depot.depot_libelle"             ,"sWidth": "20%"   , "bSortable": "true"   }, 
  { "sTitle": "référence"                 ,"sName": "fkCode_barre.pk.code_barre"         ,"sWidth": "25%"   , "bSortable": "true"   },  
  { "sTitle": "Designation"               ,"sName": "fkCode_barre.designation_libelle"   ,"sWidth": "30%"   , "bSortable": "true"   },  
 
  { "sTitle": "P.U.A"                  ,"sName": "tarif.tarif_unit_article"          ,"sWidth": "25%"  ,"sClass" : "alignRight" 
	               , "mRender": function (data, type, full) {return formatNumberJs(data,3);}     },
  { "sTitle": "Qte.disp"                  ,"sName": "quantite"                           ,"sWidth": "5%"   , "bSortable": "true"   }, 
  { "sTitle": "Mode"                      ,"sName": "etat.data_libelle"                  ,"sWidth": "5%"   , "bSortable": "true"   } ,
  { "sTitle": "Bloquée"                   ,"sName": "serie_bloque"                       ,"sWidth": "5%"   , "bSortable": "true"   }, 
  ];

</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" /> 
<table id="${nameGrid}"   class="display"     ><thead></thead></table>  
