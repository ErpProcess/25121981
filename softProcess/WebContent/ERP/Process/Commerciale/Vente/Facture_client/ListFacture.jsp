<script >  
 var mapColumsbean=[  
  { "sTitle": "${fact_id}"          ,"sName": "fact_clt_id"                     ,"sWidth": "10%"   , "bSortable": true  ,  "bVisible": true  }, 
  { "sTitle": "Code.Fact"         ,"sName": "fact_ref_id"                     ,"sWidth": "10%"   , "bSortable": "true" }, 
  { "sTitle": "Type"       			,"sName": "avoir_and_reference"                     ,"sWidth": "15%"   , "bSortable": "true"   },  
  { "sTitle": "${clt_id}"           ,"sName": "client.clt_lib"                     ,"sWidth": "30%"   , "bSortable": "true"   },  
  { "sTitle": "${fact_date}"        ,"sName": "fact_date"                     ,"sWidth": "5%"   , "bSortable": "true"   },  
  { "sTitle": "etat.reg"            ,"sName": "etat_reg.data_libelle"                     ,"sWidth": "12%"   , "bSortable": "true"   },  
  { "sTitle": "Heure"               ,"sName": "time_cre"                     ,"sWidth": "5%"   , "bSortable": "true"   },   
  { "sTitle": "${_mode}"            ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "8%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
