<script >  
 var mapColumsbean=[  
  { "sTitle": "${prf_id}"       ,"sName": "prf_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${prf_libelle}"       ,"sName": "prf_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "Etabtlissement"       ,"sName": "etablissment.etab_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "Societe"       ,"sName": "etablissment.pk_etab.soc_bean.soc_lib"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />   
 <table id="${nameGrid}"   class="display"  style="width: 100%;"  ><thead></thead></table>   
