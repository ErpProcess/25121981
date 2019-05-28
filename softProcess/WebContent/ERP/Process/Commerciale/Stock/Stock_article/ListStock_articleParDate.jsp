<script >  
 var mapColumsbean=[  
  {  "sTitle": "Date"  ,   "sName": "pk.date_stock"                         ,"sWidth": "3%"    , "bSortable": "true"    },
  {  "sTitle": "Code"  ,  "sName": "pk.fkCode_barre.pk.code_barre"         ,"sWidth": "3%"   , "bSortable": "true"   }, 
  {  "sTitle": "Famille"  ,  "sName": "pk.fkCode_barre.pk.ar_bean.fam_art.fam_lib"   ,"sWidth": "10%"   , "bSortable": "true"   },  
  {  "sTitle": "Désignation"  ,  "sName": "pk.fkCode_barre.designation_libelle"   ,"sWidth": "20%"   , "bSortable": "true"   },  
  {  "sTitle": "Qte stock"  ,   "sName": "solde_stock"                  ,"sWidth": "15%"   , "bSortable": "true"  ,"sClass" : "alignLeft" },  
  ];
var dataGridConfig_width="100%";
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />  
<table id="${nameGrid}"   class="display"  style="width: 100%;"  ></table> 
