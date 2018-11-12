<script >  
 var mapColumsbean=[  
  {   "sName": "pk.date_stock"                         ,"sWidth": "3%"    , "bSortable": "true"   },
  {   "sName": "pk.fkCode_barre.pk.code_barre"         ,"sWidth": "12%"   , "bSortable": "true"   }, 
  {   "sName": "pk.fkCode_barre.designation_libelle"   ,"sWidth": "20%"   , "bSortable": "true"   }, 
  {   "sName": "devise.dev_id"     ,"bVisible": false   }, 
   
   
  
 
  {   "sName": "quantite_recept"               ,"sWidth": "7%"    , "bSortable": "true"  ,"sClass" : "alignCenter"  }, 
  {   "sName": "mnt_ttc_recept"                ,"sWidth": "10%"   , "bSortable": "true"  ,"sClass" : "alignRight"  ,
  "mRender": function (data, type, full) {   if( full[3]=="191"  ||  full[3]=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
  
   
  {   "sName": "quantite_vendu"                ,"sWidth": "7%"    , "bSortable": "true"  ,"sClass" : "alignCenter"  },   
  {   "sName": "mnt_ttc_vendu"                 ,"sWidth": "10%"   , "bSortable": "true"  ,"sClass" : "alignRight"  ,
  "mRender": function (data, type, full) {   if( full[3]=="191"  ||  full[3]=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
  
  {   "sName": "cout_unitaire_moyen_pondere"                  ,"sWidth": "5%"   , "bSortable": "true"  ,"sClass" : "alignCenter" },   
  {   "sName": "solde_stock"                  ,"sWidth": "5%"   , "bSortable": "true"  ,"sClass" : "alignCenter" },  
  
  
  ];
var dataGridConfig_width="130%";
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />  
<table id="${nameGrid}"   class="display"  style="width: 100%;"  >
<thead>
            <tr>
                <th  rowspan="2" >Date</th>
                <th  rowspan="2"  >code</th>
                <th  rowspan="2"  >Libelle</th>
                <th  rowspan="2"  >devise</th>
            
                
                <th colspan="2" align="center"> Entrer </th>
                <th colspan="2" align="center"> sortie</th>
                <th colspan="2" align="center"  >Stock</th>
                
            </tr>
            
            <tr>
                <th align="center" >Qté entr</th>
                <th align="center" >Montant TTC</th>
                <th align="center" >Qté sorti</th>
                <th align="center" >Montant TTC</th>
                
                <th align="center" >CUMP</th>
                <th align="center" >Valeur</th>
            </tr>
            

</thead>


<tbody></tbody> 

 </table> 
