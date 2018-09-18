<script >  
 var mapColumsbean=[  
  { "sTitle": "${inv_id}"               ,"sName": "pk.inv_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${inv_date}"             ,"sName": "pk.inv_date"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${type_inv_id}"          ,"sName": "typeInventaire.type_inv_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${depot_stock_id}"       ,"sName": "pk.depot_stocks.depot_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "Mode"                    ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
