<script >  
 var mapColumsbean=[ 
  { "sTitle": "${spack_id}"       ,"sName": "sousPackBean.spack_id"     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${spack_id}"       ,"sName": "sousPackBean.spack_libelle"     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "${mod_id}"       ,"sName": "mod_id"                           ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  { "sTitle": "${mod_libelle}"       ,"sName": "mod_libelle"                 ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${mod_obs}"       ,"sName": "mod_obs"                         ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "Ordre"       ,"sName": "mod_ordre"                         ,"sWidth": "10%"   , "bSortable": "true"   },
    
  ];
</script> 
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />   
 <table id="${nameGrid}"   class="display"  style="width: 100%;"  ><thead></thead></table>   
