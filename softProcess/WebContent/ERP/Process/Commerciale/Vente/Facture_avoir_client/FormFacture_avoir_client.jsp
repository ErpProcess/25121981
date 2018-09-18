 <%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
width_tabbJQuey="100%";
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_DETAIL_FACTURE_CLIENT",
                               "list"   :"list_detaille_fac_client",
                               "id_name":"pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true  , "bVisible": false   , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[2]+'"   >';}}, 
									                   
										   {      "sTitle":"Code"   ,"sName": "pk.fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sTitle":"Designation"    ,"sName": "pk.fkcode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										                 
										   {      "sTitle":"Qte"   ,"sName": "quantite"                             , "sWidth": "5%"       },   
										  
										   {      "sTitle":"Unite"   ,"sName": "pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"                    ,"sWidth": "10%" ,"bSearchable": true },
										           
										           
										   {      "sTitle":"Prix U"    , "sName": "tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                     {        "sTitle":"TVA" , "sName": "tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	                                          }, 
	                                          
	                                          
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };
       
$(document).ready(function (){
 height_tabbJQuey="auto";
 contenu_toolbarJQuey="";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  ); 
});                                                                                         
 
 
 
 
								                        
 </script>
 
  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"        >  
 <table width="100%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
   <tr>  
   <td width="14%"><label>${fact_id}</label></td>  
   <td width="28%"  >  
   <input id="fact_clt_id" name="factclient.fact_clt_id"    libre   readonly="readonly"   type="text"      size="20"       maxlength="20"        value="${detailBean.factclient.fact_clt_id}"    nextElement="clt_id"              />  </td>  
   <td width="8%"  ><label>Avoir N&deg; </label> </td>
   <td width="50%"  ><input id="avoir_id" name="avoir_id"    libre="libre"   readonly="readonly"   type="text"    size="20"  value="${detailBean.avoir_id}"    nextelement="clt_id"></td>
   </tr>   
   
   <tr>  
    <td width="14%"><label>${clt_id}</label></td>  
    <td colspan="3"  >  
     <input id="clt_id" name="factclient.client.clt_id"     libre   readonly="readonly"    type="text"    size="10"            value="${detailBean.factclient.client.clt_id}"    nextElement="fact_date"              />  
     <input id="clt_lib" name="factclient.client.clt_lib"   libre   readonly="readonly"    type="text"    size="30"              value="${detailBean.factclient.client.clt_lib}"    nextElement="fact_date"              />    </td>  
    </tr>
      
   <tr>  
   <td width="14%"><label>${fact_date}</label></td>  
   <td width="28%"  >  
   <fmt:formatDate pattern="dd/MM/yyyy"       value="${detailBean.factclient.fact_date}"      var="searchat_datefac"/>
   <input id="fact_date" name="factclient.fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefac}"    nextElement="fact_date_edition"              />  </td>  
   <td width="8%"  ><label>Avoir date </label></td>
   <td width="50%"  >
   <fmt:formatDate pattern="dd/MM/yyyy"       value="${detailBean.avoir_date}"      var="searchaavoir_date"/>
   <input id="avoir_date" name="avoir_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchaavoir_date}"    nextelement="fact_date_edition"></td>
   </tr> 
     
   <tr>  
   <td width="14%"><label>${fact_date_edition}</label></td>  
   <td width="28%"  >  
   <input id="fact_date_edition" name="factclient.fact_date_edition"   libre   readonly="readonly"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.factclient.fact_date_edition}"    nextElement="montant_timbre_fisc"              />  </td>  
   <td width="8%"  ><label>Avoir edition</label> </td>
   <td width="50%"  >&nbsp;</td>
   </tr>
   
     <tr>  
  <td  ><label>${avance_montant_vente}</label></td>  
		   <td   >  
		   <input id="avance_montant_vente"     name="avance_montant_vente"  style="text-align: right;"    type="montant3"    size="50"    libre   readonly="readonly"      maxlength="50"        value="${detailBean.factclient.avance_montant_vente}"    nextElement="btValidx"              />		  </td>  
		   <td colspan="2"   >&nbsp;</td>
	    </tr>   
 </table> 
 </ext:panel>
  
  
      <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Facture"    > 
	          
	        
			    <table id="GRID_DETAIL_FACTURE_CLIENT" class="display" width="100%"      >
			     
  <tfoot>
				    <tr><td   height="50px" colspan="10" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								 
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td colspan="3" ></td>
								<td ></td>
								<td ></td>
								<td ></td> 
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="7">
		                    <tr align="right"> 
								<td colspan="3"></td>
								<td   ></td>
								<td colspan="3" ></td>
								<td colspan="3"></td>
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
					     
					      for (var p = listTotal.length ; p<8; p++) {
					      var foot = {} ;
					        foot["2"] = "deletio";
					        foot["3"] = "deletio";
			             	footX["XX"+p]=foot;
					     }    
					        
				    return  footX; 
				}
               </script>
			    
			      
			    
	         </ext:panel>
 
 
   
</ext:panel>
</ext:body>
