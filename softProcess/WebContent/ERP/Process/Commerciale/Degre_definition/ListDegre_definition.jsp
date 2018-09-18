 <script type="text/javascript">
 var mapColumsbean=[  
  { "sTitle": "${_codesys}"       ,"sName": "pkBean.art_Bean.pk_article.ar_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${ar_id}"          ,"sName": "pkBean.art_Bean.ar_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "Code Carac"        ,"sName": "pkBean.carac_Bean.carac_id"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${carac_id}"       ,"sName": "pkBean.carac_Bean.carac_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${ordre}"          ,"sName": "ordre"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script>

  
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table> 
 
 