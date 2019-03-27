<script>  
 var mapColumsbean=[ 
//   { "sTitle": "code"               ,"sName": "pk_article.ar_id"           ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "Famille"            ,"sName": "fam_art.fam_lib"            ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "référence"          ,"sName": "arcodbar"                   ,"sWidth": "20%"   , "bSortable": "true"   },  
  { "sTitle": "${ar_libelle}"      ,"sName": "designation_libelle"        ,"sWidth": "40%"   , "bSortable": "true"   }, 
  { "sTitle": "Unite"              ,"sName": "unitBean.unite_lib"         ,"sWidth": "10%"   , "bSortable": "true"  },  
  { "sTitle": "${sitcod}"          ,"sName": "bean_sitcod.data_libelle"   ,"sWidth": "5%"   , "bSortable": "true"    },    
//   { "sTitle": "Cathegorie"          ,"sName": "cathegorie.data_libelle"   ,"sWidth": "5%"   , "bSortable": "true"    },    
 
  ];
 
</script>
<!-- standard-grid1 full-width content-scrollable  -->
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp"></jsp:include>
<table 		 id="${nameGrid}"     class="display"   style="width: 100%;"  ></table>
 