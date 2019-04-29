<script >  
 var mapColumsbean=[  
  { "sTitle": "${fact_id}"          ,"sName": "fact_clt_id"                     ,"sWidth": "10%"   , "bSortable": true  ,  "bVisible": true  }, 
  { "sTitle": "Code.Fact"           ,"sName": "fact_ref_id"                     ,"sWidth": "10%"   , "bSortable": true ,  "bVisible": false  }, 
  { "sTitle": "${fact_date}"        ,"sName": "fact_date"                     ,"sWidth": "5%"   , "bSortable": "true"   },   
  { "sTitle": "Heure"               ,"sName": "time_cre"                     ,"sWidth": "5%"   , "bSortable": "true"   },
  { "sTitle": "${clt_id}"           ,"sName": "client.clt_lib"                     ,"sWidth": "30%"   , "bSortable": "true"   }, 

  { "sName": "devise.dev_id"     ,"bVisible": false   }, 
  { "sTitle": "Total TTC"         ,"sName": "total_facture"      ,"sWidth": "15%"    ,"sClass" : "alignRight"   , "bSortable": "true"  ,
	  "mRender": function (data, type, full) {   if( full[5]=="191"  ||  full[5]=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
	  
   
  { "sTitle": "etat.reg"            ,"sName": "etat_reg.data_libelle"                     ,"sWidth": "12%"   , "bSortable": "true"   },  
  { "sTitle": "${_mode}"            ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "8%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
