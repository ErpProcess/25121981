<%@include file="/Aceuil/esProcess.jsp" %>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
height_tabbJQuey="auto";
var lumsbean=[ 
       {      "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {      "sWidth": "2px"       , "sName": "to_check"       ,"bSortable": true     , "mRender": function( data, type, full){
              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk_article.ar_id'+full[0]+'"   >';}}, 
                  
	   {      "sWidth": "10%"    ,"sName": "pk.fkcode_barre.pk.code_barre"  }, 
	         
	   {      "sWidth": "20%"  ,"sName": "info"   ,"bSortable": true ,"bSearchable": false   },       
	          
	   {      "sWidth": "10%"  , "sName": "quantite"    ,"mRender": function( data, type, full){  
	          return '<input   type="number"   style="width: 50px;"       id=quantite'+full[2]+'    name=quantite    value="'+data+'"      onblur=doEnvoiDataV2(this,"'+full[2]+'")    nextElement=observation'+full[2]+'    >'; }},   
	  
	   
	   {       "sWidth": "10%"  , "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"      , "bSortable": "true" ,"bVisible": true  
	    },
	    
	    {       "sTitle":"Prix_U" , "sName": "tarif.tarif_unit_vente"      ,"sClass" : "alignRight"   ,"sWidth": "15%"    , "bSortable": "true" 
	            , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	      
	    {      "sTitle":"remise"     , "sName": "taux_remise_ligne"     ,"sWidth": "7%"    ,"sClass" : "alignCenter"    , "bSortable": true    
	                 , "mRender": function (data, type, full) {  return addPourcentage(data);}  ,"bVisible": true    },  
	                                    
	    {       "sTitle":"Mnt_T_H" , "sName": "montant_ht_vente"  ,"sClass" : "alignRight"   ,"sWidth": "15%"    , "bSortable": "true" ,"bVisible": true  
	            ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
	     
	    {      "sTitle":"Total TTC" , "sName": "montant_ttc_cmd"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	           ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    }, 
	                                              
	                                                                                            
	   {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	         ]; 
	         
	         
mapEditableGen= {"otab":oTable,
"table":"grid_cmd_client",
"list":"listGridEditableCmd",
"id_name":"pk.fkcode_barre.pk.code_barre",
"url":"${urlloadDataTableAjax}",
"action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
"mapCol":lumsbean   };
 
 

 

function ADD(){

 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";
 
 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
 otab_otra.fnAdjustColumnSizing();
 $('input[id="pk.code_barre"]').val('');               
 $('#designation_libelle').val('');
 $('#quantiteX').val('1');
 
}  


function Delete_ROW(){
 
       jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW',
	               dataType: 'text', 
	               success: function(data){
	               otab_otra.fnAdjustColumnSizing();
                      },
                   error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
}

function doCheked_unCheked(element){
		var res="";
		if($(element).attr('type')=="checkbox"){
	    element.value=element.checked==false?"":"checked";
	    res=element.checked==false?"":"checked";
	    }
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_CHEKED_UNCHEKED&statucheked='+res,
	               dataType: 'text', 
	               success: function(data){
	               otab_otra.fnAdjustColumnSizing();
                      },
                 error: function (request, status, error) {
                         alert(request.responseText);
                  } 
    });
}   

 

$(document).ready(function () {
LoadAutoCompletAjax("clt_id","clt_lib","dev_lib","list_client_cmd_clt");  
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","quantite","list_article_CmdVente","500","250");
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","vente_obs","list_depot_stock_cmd","250","100");
});



function getSuiv(){

 if(!teste_required()) return;
 var jsonText=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_TARIF_CLT','text',false);


$("#clt_id").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});


 $("#clt_lib").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});

  
  $('#clt_id').attr("readonly","readonly");
  $('#clt_lib').attr("readonly","readonly");
  $('#grid_cmd_client').css('display','block');
   
 Ext.getCmp('RET_GRID').setTitle(' Détaille commande client ');
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );   
}

function loadGrid(){
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
}


function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
            
          
            var QteNew="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
	      $(this).find('td:eq(8)').html(json[qsdqsqd]) ;
	      //$(this).find('td:eq(3)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	      
	      
	      var xcvvv=json[erreurX];
	      xcvvv=xcvvv.trim();
	      if(   xcvvv  !="" &&  xcvvv.length>0 ){
	           alertExt("",json[erreurX],"4");
	      }
	   });
	   otab_otra.fnAdjustColumnSizing();
 }  
 
 
 
</script>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
  
		  <ext:panel     border="false"    bodyStyle="background: none;"      >
			<ext:toolbar         toolbarType="bbar"   > 
		     <ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   onClick="getSuiv()"   ></ext:toolbar.button> </ext:toolbar>
		 
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >  
		   <tr>  
		   <td width="10%"><label>${cmd_id}</label></td>  
		   <td  width="39%" >  
		   <input id="cmd_id" name="cmd_id"     type="text"    size="15"             value="${detailBean.cmd_id}"    nextElement="cmd_libelle"       libre     readonly="readonly"                />		  </td>  
		   <td width="13%"  >  </td>
		   <td width="6%"  ></td>
		   <td width="10%"  ><label>Remise.caisse </label>
		   <input id="taux_remise_alacaisse" name="taux_remise_alacaisse"      style="width: 50px;"     type="text"          value="${detailBean.taux_remise_alacaisse}"    nextelement="avance_montant_cmd"  onblur="loadGrid();" />
		   <label>%</label>
		   </td>
		   <td width="18%" align="right"    ><input id="cmd_remise_alacaisse" name="cmd_remise_alacaisse"   style="height: 30px;font-size: 18px;"     type="montant3"    size="17"    libre      readonly="readonly"       value="${detailBean.cmd_remise_alacaisse}"   ></td>
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${cmd_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.cmd_date}"   var="detailacmd_date"/> 
		   <input id="cmd_date" name="cmd_date"     type="datepicker"    size="13"    libre            maxlength="13"        value="${detailacmd_date}"    nextElement="depot_id"     required       />		  </td>  
		   <td       >&nbsp;</td>
		   <td ><label></label></td>
		   <td ><label>Total.remise </label></td>
		   <td align="right" ><input id="commande_remise" name="commande_remise"    style="height: 30px;font-size: 18px;"      type="montant3"    size="17"    libre      readonly="readonly"      value="${detailBean.commande_remise}" /></td>
		   </tr>   
		   
		    <tr>  
		   <td width="10%"><label>${clt_id}</label></td>  
		   <td  >  
		    <input id="clt_id" name="client.clt_id"           type="text"     size="10"             value="${detailBean.client.clt_id}"         nextElement="cmd_libelle"   required    /> 
			<input id="clt_lib" name="client.clt_lib"        type="text"      size="30"             value="${detailBean.client.clt_lib}"        nextElement="cmd_libelle"   required    />		  </td>  
		   <td  >&nbsp;</td>
		   <td  >&nbsp;</td>
	       <td  ><label>Total T.T.C</label> </td>
	       <td align="right"  ><input id="commande_mnt_total" name="commande_mnt_total"     type="montant3"    size="17"    libre  readonly="readonly"           value="${detailBean.commande_mnt_total}"    nextelement="designation_libelle"   style="height: 30px;font-size: 18px;" /></td>
	       </tr> 
		   
		    <tr>  
		   <td width="10%"><label>${cmd_libelle}</label></td>  
		   <td  >  
		    <input id="cmd_libelle" name="cmd_libelle"           type="text"     size="45"             value="${detailBean.cmd_libelle}"         nextElement="depot_id"   autofocus     />		  </td>  
		   <td  >&nbsp;</td>
		   <td  >&nbsp;</td>
	       <td  ><label>${avance_montant_vente}</label></td>
	       <td align="right"  ><input id="avance_montant_cmd" name="avance_montant_cmd"        type="montant3"    size="17"    libre="libre"   maxlength="50"        value="${detailBean.avance_montant_cmd}"   style="height: 30px;font-size: 18px;"     nextelement="cmd_remise"     onblur="loadGrid();" /></td>
	       </tr> 
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td >  
				 <input id="depot_id" name="depot.depot_id"    libre  type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="cmd_obs"                  required    />  
				 <input id="depot_libelle" name="depot.depot_libelle"  libre    type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="cmd_obs"   required     />		  </td>  
		   <td    >&nbsp;</td>
		   <td    >&nbsp;</td>
		   <td    ><label> Net.a payer </label></td>
		   <td align="right"    ><input id="cmd_mnt_net_a_payer" name="cmd_mnt_net_a_payer"     type="montant3"    size="17"     libre  readonly="readonly"    maxlength="15"     style="height: 30px;font-size: 18px;"     value="${detailBean.cmd_mnt_net_a_payer}"       /></td>
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${cmd_obs}</label></td>  
		   <td   >  
		   <input id="cmd_obs" name="cmd_obs"     type="text"    size="45"    libre         value="${detailBean.cmd_obs}"     onblur="getSuiv();"   nextelement="quantiteX"       />		  </td>  
		   <td   >&nbsp;</td>
		   <td   >&nbsp;</td>
		   <td   >  </td>
		   <td align="right"   ></td>
		   </tr> 
		   
		    <tr>  
		   <td  ><label></label></td>  
		   <td  >&nbsp;</td>  
		   <td   align="right">&nbsp;</td>
	       <td   >&nbsp;</td>
	       <td   > </td>
	       <td align="right"   >   </td>
	       </tr> 
		 </table> 

		</ext:panel>
	 
	 
	 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="&nbsp;" > 
	 <table       id="grid_cmd_client" class="display"  width="100%"   style="display: none;"  >
	  <thead   >
		<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
			<th></th>
			<th><input  type="checkbox"   id="Cheked_unCheked"  name="Cheked_unCheked"    onclick="doCheked_unCheked(this)"     value=""     ></th>
			<th><input type="text" id="pk.code_barre" name="code_barreX"  size="15"  value=""  requiredx ></th>
			<th><input  type="text"     id="designation_libelle"    name="designation_libelle"    size="30"  value=""    requiredx ></th>
			<th><input  type="number"   id="quantiteX"   value="1" min="1"     step="1"  name="quantiteX"  style="width: 70px;"    requiredx ></th>
			<th></th>
			 
			<th></th>
			<th></th>
			 
			<th></th>
			<th></th>
			<th></th>
		</tr>
		 <tr> 
			<th></th>
			<th>X</th>
			<th>code</th>
			<th>${ar_id}</th>
			<th>${quantite}</th>
			<th>${unite}</th>
			 
			<th></th>
			<th></th>
			 
			<th></th>
			<th></th>
			<th></th>
	    </tr>
	 </thead>
	 	<tfoot>
				  
				     <tr><td   height="50px" colspan="10" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
								<td ></td>
								 
								<td ></td>
								<td ></td>
								<td colspan="2" ></td>
								<td ></td>
								<td ></td>
								<td ></td> 
								<td ></td> 
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="7">
		                    <tr align="right"> 
								<td colspan="4"></td>
								<td   ></td>
								<td colspan="2" ></td>
								<td ></td>
								 
								<td ></td> 
								<td ></td> 
						    </tr>
					</c:forEach>	
				 </tfoot>
			    </table>
			    
			     <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					 
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var	listTva= json.list_tva ;
					    var	listTotal = json.list_total ;
					    
					    $('#cmd_remise_alacaisse').val(json.cmd_remise_alacaisse);
					    $('#commande_remise').val(json.commande_remise);
					    $('#cmd_mnt_net_a_payer').val(json.cmd_mnt_net_a_payer);
					    $('#commande_mnt_total').val(json.commande_mnt_total);
					    
					   
					     
					     var footX ={} ;
					     
					     for (var h = 0; h <listTva.length; h++) {
					     var foot ={} ;
						        if(h==0){
						         foot[listTva[h].td1] = listTva[h].value1;
						         footX["AA"+h]=foot;
						         continue;
				                }
				             	foot[listTva[h].td1] = listTva[h].value1;
				             	foot[listTva[h].td2] = listTva[h].value2;
				             	foot[listTva[h].td3] = listTva[h].value3;
				             	footX["AA"+h]=foot;
					     }
					     
					     
					     for (var p = listTva.length ; p<6; p++) {
					      var foot = {} ;
					        foot["0"] = "";
			             	footX["UU"+p]=foot;
					     } 
					     
					     
					    for (var x = 0; x <listTotal.length; x++) {
					     var foot ={} ;
			             foot[listTotal[x].td1] = listTotal[x].value1;
			             foot[listTotal[x].td2] = listTotal[x].value2;
			             footX["BB"+x]=foot;
					     }   
					        
				    return  footX; 
				}
               </script>
			      
			    
	</ext:panel> 
	
	 
		

  
</ext:panel>
</ext:body>
