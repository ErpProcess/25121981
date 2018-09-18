  
<script >  
 var mapColumsbean=[  
  { "sTitle": "${pack_id}"       ,"sName": "packageSys.pack_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${spack_id}"       ,"sName": "spack_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${spack_libelle}"       ,"sName": "spack_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${spack_obs}"       ,"sName": "spack_obs"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${spack_action}"       ,"sName": "spack_action"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${spack_ordre}"       ,"sName": "spack_ordre"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script> 
 



<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table>