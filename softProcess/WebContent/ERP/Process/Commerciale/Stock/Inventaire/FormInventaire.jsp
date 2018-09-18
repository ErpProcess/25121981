 
 
<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>

 <script type="text/javascript">
 contenu_toolbarJQuey="";
 var mapcolnumm ;
if("${bs.fct_id}"=="2" ||  "${bs.fct_id}"=="4" || "${bs.fct_id}"=="10" ){
 
          mapcolnumm = [ 
                                        
                                        { "sTitle":"Réf"         , "sName": "pk.fkCode_barre.pk.ar_bean.pk_article.ar_id"        ,"sWidth": "10%"   , "bSortable": "true"   },       
                                        { "sTitle":"code Barre"  , "sName": "pk.fkCode_barre.pk.code_barre"                      ,"sWidth": "20%"    , "bSortable": "true" ,"bVisible": true   }, 
                                        { "sTitle":"Libelle"     , "sName": "pk.fkCode_barre.designation_libelle"                ,"sWidth": "30%"    , "bSortable": "true" ,"bVisible": true   },
                                        { "sTitle":"Unité"       , "sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"                ,"sWidth": "30%"    , "bSortable": "true" ,"bVisible": true   },
                                       
                                        {  "sTitle":"Lot"       ,    "sWidth": "10%"  , "sName": "pk.lot.num_lot"    }, 
										          
										{  "sTitle":"Libelle"   ,    "sWidth": "10%"  , "sName": "pk.lot.date_peremption" ,  "bVisible": true   },
                                       
                                         
									    { "sTitle":"${quantite}" , "sName": "pk.quantite"         ,"sWidth": "2%"   , "bSortable": "true"      },
	                                           
	                                    { "sTitle":"Prix.U.A"    , "sName": "prix_unit_achat"     ,"sClass" : "alignRight"           ,"sWidth": "10%"    , "bSortable": "true" 
	                                    , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                    { "sTitle":"Mnt.T.H.A"   , "sName": "pk.montant_ht"         ,"sClass" : "alignRight"       ,"sWidth": "10%"    , "bSortable": "true" ,"bVisible": true  
	                                     ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    },
									    ];
    
 } else {
  

     mapcolnumm=[ 
                                        {      "sName": "to_check"      ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")          >';}}, 
                                        { "sTitle":"Réf"         , "sName": "pk.fkCode_barre.pk.ar_bean.pk_article.ar_id"        ,"sWidth": "10%"   , "bSortable": "true"   },       
                                        { "sTitle":"code Barre"  , "sName": "pk.fkCode_barre.pk.code_barre"                      ,"sWidth": "20%"    , "bSortable": "true" ,"bVisible": true   }, 
                                        { "sTitle":"Libelle"     , "sName": "pk.fkCode_barre.designation_libelle"                ,"sWidth": "30%"    , "bSortable": "true" ,"bVisible": true   },
                                        { "sTitle":"Unité"       , "sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"                ,"sWidth": "30%"    , "bSortable": "true" ,"bVisible": true   },
                                       
                                        {  "sTitle":"Lot"       ,    "sWidth": "10%"  , "sName": "pk.lot.num_lot"    ,"mRender": function( data, type, full){  
										      return '<input   type="text"  size="7"     id=num_lot'+full[2]+'            name=pk.lot.num_lot            value="'+data+'"    style="width: 70px;"        idc="num_lot'+full[2]+'"    onblur=doEnvoiDataV2_Corr($(this).attr("idc"),$("#date_peremption'+full[2]+'").attr("id"),"'+full[2]+'")   >'; }}, 
										          
										{  "sTitle":"Libelle"       ,    "sWidth": "10%"  , "sName": "pk.lot.date_peremption" ,  "bVisible": true  ,"mRender": function( data, type, full){  
										      return '<input   type="text"  size="7"     id=date_peremption'+full[2]+'    name=pk.lot.date_peremption    value="'+data+'"    style="width: 70px;"        idc="num_lot'+full[2]+'"    onblur=doEnvoiDataV2_Corr($(this).attr("idc"),$("#date_peremption'+full[2]+'").attr("id"),"'+full[2]+'")   >'; }},
                                       
                                         
									    { "sTitle":"${quantite}" , "sName": "pk.quantite"         ,"sWidth": "2%"   , "bSortable": "true"      ,"mRender": function( data, type, full){   
	                                           return '<input   type="number"  size="7"  style="width:60px;"    id=quantite'+full[2]+'        name=pk.quantite         value="'+data+'"     onblur=doEnvoiDataV2(this,"'+full[2]+'")      >'; }},
	                                           
	                                    { "sTitle":"Prix.U.A"    , "sName": "prix_unit_achat"     ,"sClass" : "alignRight"           ,"sWidth": "10%"    , "bSortable": "true" 
	                                    , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                    { "sTitle":"Mnt.T.H.A"   , "sName": "pk.montant_ht"         ,"sClass" : "alignRight"       ,"sWidth": "10%"    , "bSortable": "true" ,"bVisible": true  
	                                     ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    },
									    ];
 
 
   
 
 } 
 
 
 
 mapEditableGen =             {"otab"   :oTable,
                               "table"  :"grid_InvenTaire_SAISIE",
                               "list"   :"list_data_for_inventaire",
                               "id_name":"pk.fkCode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :mapcolnumm
                               };
  $(document).ready(function (){
       height_tabbJQuey="auto"
	   LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
	   config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
   });  
 
 
 function loadLesListeCorrlee(objeJson){
      for ( var q=0  ; q<objeJson.aaData.length ; q++ ) {
 		  var inde=objeJson.aaData[q][2];
 		  var idInputcode_barre=objeJson.aaData[q][2];
 		  var idInputnum_lot="num_lot"+inde;
          var idInputdate_peremption="date_peremption"+inde;
          var idInputquantite="pk.quantite"+inde;
          LoadOtherAutocompletesAjax_grid(idInputcode_barre,"i$_ACT_LOAD_LOT_ARTICLE",idInputnum_lot,idInputdate_peremption,"num_lot","date_peremption",idInputquantite,false);
      }    
}
  
 
function doEnvoiDataV2_Corr(element,elment2,value_id_de_la_ligne){
 	    
   var idToSendo=value_id_de_la_ligne ;
   var LEvalue=$('input[id="'+element+'"]').val();
   var name_column=$('input[id="'+element+'"]').attr("name");
	 
    var hashmap ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGen["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url:  urlsUpdateURL_def, 
	     data: hashmap,
	     dataType: 'text', 
	     success: function(data){
	     	
	     	  var LEvalue2     =$('input[id="'+elment2+'"]').val();
			  var name_column2 =$('input[id="'+elment2+'"]').attr("name");
 	     
		      var hashmap2 ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue2,"sNameColumn" :name_column2,"sNameList":mapEditableGen["list"]};
			jQuery.ajax({ 
			     type: 'POST',  
			     url: urlsUpdateURL_def, 
			     data:hashmap2,
			     dataType: 'text', 
			     success: function(data){
			  
			       }
		      }); 
			   
           }
      }); 
 
}


function doExcuteFnAfterGrid( dataSS ){
      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
            var codeMap = $(this).find('td:eq(2)').html() ;
	      $(this).find('td:eq(9)').html(json[codeMap]) ;
	   });
 
 }  
 
          
                               
 </script>
 
  
  <ext:body  > 
  
     <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
     
       <ext:panel  border="false"    bodyStyle="background: none;"         > 
			 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
			   <tr>  
			   <td width="6%"><label>${inv_id}</label></td>  
			   <td  width="20%" >  
			   <input id="inv_id" name="inv_id"     type="text"    size="20"       maxlength="20"       value="${detailBean.pk.inv_id}"    nextElement="inv_date"      libre  readonly="readonly"      />  
			  </td>
			  <td  width="10%" ><label>${type_inv_id}</label></td>  
			   <td    >  
			   <script type="text/javascript">
						$(document).ready(function (){
						    LoadAutoCompletAjax_with_marGin("type_inv_id","type_inv_libelle","depot_stock_id","list_TypeInventaire","250","100");
						});
			   </script>
			   <input id="type_inv_id"          name="typeInventaire.type_inv_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.typeInventaire.type_inv_id}"    nextElement="depot_stock_id"              />
			   <input id="type_inv_libelle"     name="typeInventaire.type_inv_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.typeInventaire.type_inv_libelle}"    nextElement="depot_stock_id"              />  
			  </td>  
			    
			   </tr>   
			   <tr>  
			   <td  ><label>${inv_date}</label></td>  
			   <td    > 
			   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.pk.inv_date}"   var="WW"/> 
			   <input id="pk.inv_date" name="pk.inv_date"     type="datepicker"    size="13"       maxlength="13"        value="${WW}"    nextElement="type_inv_id"             required  />  
			  </td>  
			  
			   <td ><label>${depot_stock_id}</label></td>  
			   <td   > 
			   <script type="text/javascript">
						$(document).ready(function (){
						    LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle",null,"list_depot_stock","250","100");
						});
			   </script> 
			   <input id="depot_id" name="pk.depot_stocks.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.pk.depot_stocks.depot_id}"    nextElement="btValidx"                  required    />  
			   <input id="depot_libelle" name="pk.depot_stocks.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.pk.depot_stocks.depot_libelle}"    nextElement="btValidx"   required     />
			  </td>  
			  
			   </tr>   
			    
			    
			 </table>   
          </ext:panel>
           <ext:tabPanel  activeTab="inv1"  title="ssssssssssssss"  >
	         <ext:panel   id="inv1"  border="false"    bodyStyle="background: none;"   title="Inventaire des Articles" > 
			    <table id="grid_InvenTaire_SAISIE" class="display" width="100%" ></table>
	         </ext:panel>
           </ext:tabPanel>

</ext:panel>


</ext:body>
