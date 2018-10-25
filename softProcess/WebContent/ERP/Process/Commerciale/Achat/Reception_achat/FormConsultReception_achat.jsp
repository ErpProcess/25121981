<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
function doPdf_detaille_demande_achat(){
	genericPdfProcess("${tmlx.urlAjax}?HiddenAction=i$_ACT_PRINT_PDF_DETAILLE_RECEPTION_ACHAT");
}

 



function doExcel_detaille_demande_achat(){
$('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_EXPORT_XLS_DETAILLE_RECEPTION_ACHAT");
$('#myformToServeur').attr('target', '_self');
$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
$("#myformToServeur").submit();
}	 
</script>
<ext:body  > 
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"     height="200"  >  
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
		   <tr>  
		      <td width="10%"><label>${achat_id}</label></td>  
		      <td width="40%"  >  
		      <input id="achat_id" name="achat_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		      </td> 
		      <td width="10%"><label>${achat_libelle}</label></td>  
		      <td width="40%"  >  
		      <input id="achat_libelle" name="achat_libelle"     type="text"    size="46"       maxlength="200"        value="${detailBean.achat_libelle}"    nextElement="frs_id"       autofocus       />  
		      </td> 
		    </tr> 
		    
		    <tr>  
		      <td width="10%"><label>${achat_date}</label></td>  
		      <td width="40%"  >
		       <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat_date}"   var="detailachat_date"/>   
		      <input id="achat_date" name="achat_date"     type="datepicker"    size="15"       maxlength="15"        value="${detailachat_date}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		      </td> 
		      <td width="10%"><label>${frs_id}</label></td>  
		      <td width="40%"  >  
                     <input id="frs_id" name="frsBean.frs_id"         type="text"    size="10"       maxlength="10"     value="${detailBean.frsBean.frs_id}"          nextElement="depot_id"   required    /> 
			         <input id="frsref" name="frsBean.frsref"        type="text"    size="30"          value="${detailBean.frsBean.frsref}"          nextElement="depot_id"   required    />
		      </td> 
		    </tr> 
		  
		    <tr>  
		      <td width="10%"><label>avance_montant_achat</label></td>  
		      <td width="40%"  >
		         <input id="avance_montant_achat" name="avance_montant_achat"     type="montant3"    size="45"        maxlength="50"        value="${detailBean.avance_montant_achat}"    nextElement="achat_obs"              />  
		      </td> 
		      <td width="10%"><label>${depot_id}</label></td>  
		      <td width="40%"  >  
                   <input id="depot_id" name="depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="avance_montant_achat"                  required    />  
				   <input id="depot_libelle" name="depot_stocks.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="avance_montant_achat"   required     />
		      </td> 
		    </tr>
		    
		    
		    <tr>  
		      <td width="10%"><label>${achat_obs}</label></td>  
		      <td width="40%"  >
		        <textarea   id="achat_obs" name="achat_obs"   rows="3" cols="47"      onblur="getSuiv()"  >${detailBean.achat_obs}</textarea>  
		      </td> 
		      <td width="10%"><label>Devise</label></td>  
		      <td width="40%"  >
 				<script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",false); })</script>
		        <select   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select> 
		      </td> 
		    </tr>
		    
		   
		 </table> 
	 </ext:panel>
	 <ext:tabPanel  activeTab="RET_GRID"  title="Détaille Achat"  border="true"  style="padding:5px 5px 5px 5px;" >
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Achat"    > 
	         
<script type="text/javascript">
contenu_toolbarJQuey          ="";
mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_AHCAT",
                               "list"   :"list_editable_recep_achat",
                               "id_name":"indx_row",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
										   {      "sTitle":"Référence"    ,  "sWidth": "15%"  ,"sName": "pk.fkCode_barre.pk.code_barre"  }, 
										   
										   {      "sTitle":"Désignation"  ,  "sWidth": "30%"  ,"sName": "pk.fkCode_barre.designation_libelle" },   
										       
										   {      "sTitle":"Quantité"     ,  "sWidth": "5%"  ,"sName": "quantite"   ,"sClass" : "alignCenter"   },  
										    
										   {      "sTitle":"Unite"  ,  "sWidth": "5%"  ,"sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"  },
										   
										   {      "sTitle":"Prix.U.A"    ,"sWidth": "5%"   , "sName": "tarif.tarif_unit_article"      ,"sClass" : "alignRight"    , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },
	                                        
	                                       {      "sTitle":"TVA"  ,  "sWidth": "10%"  , "sName": "tarif.tvaBean.tva_libelle"      },
	                                                   
	                                       {      "sTitle":"Montant.H.A"   ,"sWidth": "10%"  , "sName": "montant_ht_achat"  ,"sClass" : "alignRight"      , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    }, 
	                                                     
										   {      "sTitle":"Observation"  ,  "sWidth": "10%"  , "sName": "observation"      },
										   
										   
										   {      "sTitle":"Mnt.T.V.A"   ,"sWidth": "10%", "sName": "montant_tva_achat"  ,"sClass" : "alignRight"      , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    }, 
	                                              
	                                              
	                                            ]
 
                               };
$(document).ready(function () {     
 LoadDataEditable_AUTO_height( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , 100  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});			
</script>
						
			    <table id="GRID_SAISIE_DETAIL_AHCAT"  class="display" width="100%" >
			    
				 <tfoot>
				   <tr align="right"> 
						<td colspan="2"></td>
						<td align="center"></td>
						<td  colspan="3" ></td>
						<td></td>
						<td></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td colspan="2"></td>
						<td align="center"></td> 
						<td colspan="3" ></td>
						<td></td>
						<td ></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td colspan="2"></td>
						<td align="center"></td> 
						<td colspan="3" ></td>
						<td></td>
						<td ></td>
						<td></td>
				    </tr>
				 </tfoot>
			    </table>
			    <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var qtessS    =json["UU"];
                        var tot_h_tvaS=json["k"];
			            var tot_tvaS  =json["o"];
			            var tot_GenS  =json["p"];
				        var firstitems  = {"0":"Total Qté"    ,"1":qtessS   ,"2":"Total.H.T"  ,"3":tot_h_tvaS  };
				        var secondItems = {"2":"Total TVA"    ,"3":tot_tvaS };
				        var TroisIemIte = {"2":"Total Général","3":tot_GenS };
				        var items       = {"A":firstitems,"B":secondItems,"C":TroisIemIte};
				    return  items; 
				}
               </script>
	    
	         </ext:panel>
     </ext:tabPanel>
		 
		   
</ext:panel>
</ext:body>
