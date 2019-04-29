<script >  
 var mapColumsbean=[  
  { "sTitle": "${vente_id}"       ,"sName": "vente_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${vente_date}"        ,"sName": "vente_date"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "Heure"              ,"sName": "time_cre"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "${clt_id}"         ,"sName": "client.clt_lib"                     ,"sWidth": "20%"   , "bSortable": "true"   },
  { "sName": "devise.dev_id"     ,"bVisible": false   }, 
  { "sTitle": "Total TTC"         ,"sName": "vente_mnt_total"      ,"sWidth": "15%"    ,"sClass" : "alignRight"   , "bSortable": "true"  ,
	  "mRender": function (data, type, full) {   if( full[4]=="191"  ||  full[4]=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },    
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
