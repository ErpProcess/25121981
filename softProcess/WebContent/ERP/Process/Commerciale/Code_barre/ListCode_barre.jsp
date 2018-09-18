<script >  
 var mapColumsbean=[
  { "sTitle": "code"            ,"sName": "pk.ar_bean.pk_article.ar_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "${ar_id}"            ,"sName": "pk.ar_bean.ar_libelle"                     ,"sWidth": "25%"   , "bSortable": "true"   }, 
  { "sTitle": "Référence"       ,"sName": "pk.code_barre"                     ,"sWidth": "20%"   , "bSortable": "true"   },  
  { "sTitle": "${designation}"      ,"sName": "designation_libelle"                     ,"sWidth": "40%"   , "bSortable": "true"   },  
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
