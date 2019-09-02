<script >  
 var mapColumsbean=[  
  
  { "sTitle": "demande"           ,"sName": "demande_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_id}"       ,"sName": "achat_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${achat_date}"     ,"sName": "achat_date"                   ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_time}"     ,"sName": "time_cre"                        ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${frs_id}"         ,"sName": "frsBean.frsref"                  ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  
  { "sTitle": "Total"       ,"sName": "achat_mnt_total"     ,"sClass" : "alignRight"   ,"sWidth": "15%"   , "bSortable": "true"   ,
	   "mRender": function (data, type, full) {return formatNumberJs(data,3);}  },
	   
	   
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"             ,"sWidth": "5%"   , "bSortable": "true"   },
     
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
	 