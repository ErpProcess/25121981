<%@include file="/Aceuil/esProcess.jsp" %>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">

var lumsbean=[ 
       {      "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {      "sName": "to_check"      ,"sWidth": "10px"     ,"bSortable": true  , "bVisible": false   , "mRender": function( data, type, full){
              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk_article.ar_id'+full[0]+'"   >';}}, 
                  
	   {      "sTitle":"code" ,    "sWidth": "10%"    ,"sName": "pk.fkcode_barre.pk.code_barre"  }, 
	         
	   {       "sTitle":"${ar_id}" ,    "sWidth": "20%"  ,"sName": "pk.fkcode_barre.designation_libelle"     },       
	          
	   {       "sTitle":"${quantite}" ,   "sWidth": "10%"  , "sName": "quantite"     },   
	  
	    
	    
	    
	   {        "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "10%"    , "bSortable": "true" ,"bVisible": true  
},
	    
	   {       "sTitle":"Prix_U" , "sName": "tarif.tarif_unit_vente"      ,"sClass" : "alignRight"   ,"sWidth": "15%"    , "bSortable": "true" 
	            , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	   
	   {       "sTitle":"remise" ,   "sName": "taux_remise_ligne"       ,"sWidth": "10%"     ,"bSearchable": true },
	                 
	                                    
	   {       "sTitle":"Mnt_T_H" , "sName": "montant_ht_vente"  ,"sClass" : "alignRight"   ,"sWidth": "15%"    , "bSortable": "true" ,"bVisible": true  
	            ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
	                                              
	   {      "sTitle":"Total TTC" , "sName": "montant_ttc_cmd"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	           ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },                                             
	                                                    
	   {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	         ]; 
mapEditableGen= {"otab":oTable,"table":"grid_cmd_client","list":"listGridEditableCmd","id_name":"pk.fkcode_barre.pk.code_barre","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
 
$(document).ready(function () {
 height_tabbJQuey="auto";
 contenu_toolbarJQuey="";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});

 

</script>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
  
		  
		  <ext:panel  border="false"    bodyStyle="background: none;"      > 
		<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >  
		   <tr>  
		   <td width="10%"><label>${cmd_id}</label></td>  
		   <td  width="39%" >  
		   <input id="cmd_id" name="cmd_id"     type="text"    size="15"             value="${detailBean.cmd_id}"    nextElement="cmd_libelle"        libre   readonly="readonly"        />		  </td>  
		   <td width="13%"  >  </td>
		   <td width="6%"  ></td>
		   <td width="10%"  ><label>Remise.caisse </label>
		   <input id="taux_remise_alacaisse" name="taux_remise_alacaisse"      style="width: 50px;"     type="text"          value="${detailBean.taux_remise_alacaisse}"    nextelement="avance_montant_cmd"  onblur="loadGrid();" />
		   <label>%</label>
		   </td>
		   <td width="18%" align="right"    ><input id="cmd_remise_alacaisse" name="cmd_remise_alacaisse"   style="height: 30px;font-size: 18px;"     type="montant3"    size="17"           readonly="readonly"       value="${detailBean.cmd_remise_alacaisse}"   ></td>
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${cmd_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.cmd_date}"   var="detailacmd_date"/> 
		   <input id="cmd_date" name="cmd_date"     type="datepicker"    size="13"                 maxlength="13"        value="${detailacmd_date}"    nextElement="depot_id"     required       />		  </td>  
		   <td       >&nbsp;</td>
		   <td ><label></label></td>
		   <td ><label>Total.remise </label></td>
		   <td align="right" ><input id="commande_remise" name="commande_remise"    style="height: 30px;font-size: 18px;"      type="montant3"    size="17"          value="${detailBean.commande_remise}" /></td>
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
	       <td align="right"  ><input id="avance_montant_cmd" name="avance_montant_cmd"        type="montant3"    size="17"      maxlength="50"        value="${detailBean.avance_montant_cmd}"   style="height: 30px;font-size: 18px;"     nextelement="cmd_remise"     onblur="loadGrid();" /></td>
	       </tr> 
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td >  
				 <input id="depot_id" name="depot.depot_id"             type="text"    size="10"           value="${detailBean.depot.depot_id}"    nextElement="cmd_obs"                  required    />  
				 <input id="depot_libelle" name="depot.depot_libelle"   type="text"    size="30"          value="${detailBean.depot.depot_libelle}"    nextElement="cmd_obs"   required     />		  </td>  
		   <td    >&nbsp;</td>
		   <td    >&nbsp;</td>
		   <td    ><label> Net.a payer </label></td>
		   <td align="right"    ><input id="cmd_mnt_net_a_payer" name="cmd_mnt_net_a_payer"     type="montant3"    size="17"     libre  readonly="readonly"    maxlength="15"     style="height: 30px;font-size: 18px;"     value="${detailBean.cmd_mnt_net_a_payer}"       /></td>
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${cmd_obs}</label></td>  
		   <td   >  
		   <input id="cmd_obs" name="cmd_obs"     type="text"    size="45"              value="${detailBean.cmd_obs}"        nextelement="quantiteX"       />		  </td>  
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
	 
	 
	 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title=" Détaille commande client" > 
	 <table       id="grid_cmd_client" class="display"  width="100%"   >
	 <tfoot>
				    <tr><td   height="50px" colspan="10" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
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
					        foot["0"] = "deletio";
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
