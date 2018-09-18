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
  
   <ext:panel  border="false"    bodyStyle="background: none;"       >  
   <table>
	<tr>

   
    <td  width="50%" >
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblDcccata"   width="100%"  >  
		   <tr>  
		   <td width="22%"><label>${dem_achat_id}</label></td>  
		   <td    >  
		   <input id="dem_achat_id"  name="dem_achat.dem_achat_id"     type="text"    size="15"       maxlength="15"     libre  readonly="readonly"        value="${detailBean.dem_achat.dem_achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${dem_date}</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.dem_achat.dem_date}"   var="detailBeandm_date"/>
		   <input id="dem_date" name="dem_achat.dem_date"     type="text"    size="15"       maxlength="15"    libre    value="${detailBeandm_date}"     libre  readonly="readonly"              />  
		  </td>  
		   </tr>  
		   
		   <tr>  
		   <td  ><label>${frs_id}</label></td>  
		   <td    >  
		    <input id="frs_id" name="dem_achat.frsBean.frs_id"       libre    readonly="readonly"      type="text"     size="10"             value="${detailBean.dem_achat.frsBean.frs_id}"   readonly="readonly"       nextElement="depot_id"   required    /> 
			<input id="frsref" name="dem_achat.frsBean.frsref"       libre    readonly="readonly"      type="text"     size="30"             value="${detailBean.dem_achat.frsBean.frsref}"     readonly="readonly"       nextElement="depot_id"   required    />
		  </td>  
		   </tr>   
		    
		   
		   
		    
		    
		   
		 </table> 
    
    
    
    
    </td>
    
    
    
        <td width="50%">
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"   >  
		   <tr>  
		   <td  width="60%"><label>${achat_id}</label></td>  
		   <td  width="40%" >  
		   <input id="achat_id" name="achat_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${achat_libelle}</label></td>  
		   <td   >  
		   <input id="achat_libelle" name="achat_libelle"     type="text"    size="15"       maxlength="15"         value="${detailBean.achat_libelle}"    nextElement="achat_date"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${achat_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat_date}"   var="detailachat_date"/> 
		   <input id="achat_date" name="achat_date"     type="text"    size="13"       maxlength="13"        value="${detailachat_date}"    nextElement="frs_id"      readonly="readonly"         />  
		  </td>  
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td    >  
					   <input id="depot_id" name="depot.depot_id"    readonly="readonly"    type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="btValidx"                  required    />  
					   <input id="depot_libelle" name="depot.depot_libelle"  readonly="readonly"     type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="btValidx"   required     />
		 
		  </td>  
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${achat_obs}</label></td>  
		   <td   >  
		   <input id="achat_obs" name="achat_obs"     type="text"    size="50"    readonly="readonly"      maxlength="50"        value="${detailBean.achat_obs}"    nextElement="btValidx"              />  
		  </td>  
		   </tr> 
		   
		   
		   
		   <tr>  
		   <td  ><label>Devise</label></td>  
		   <td    >
		   
		 <script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",true); })</script>
		        <select   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select> 
		  </td>  
		   </tr>   
		     
		 </table> 
    
    
    
     </td>
   </tr>
   
   </table>
</ext:panel>

<ext:tabPanel  activeTab="RET_GRID"  title="Détaille Achat"  border="true"  style="padding:5px 5px 5px 5px;" >
   <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Achat"    > 
           
<script type="text/javascript">
contenu_toolbarJQuey           ="";

mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_AHCAT",
                               "list"   :"list_editable_recep_achat",
                               "id_name":"indx_row",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
										   {      "sTitle":"Référence"    ,  "sWidth": "15%"  ,"sName": "pk.fkCode_barre.pk.code_barre"  }, 
										   
										   {      "sTitle":"Désignation"  ,  "sWidth": "30%"  ,"sName": "pk.fkCode_barre.designation_libelle" },   
										       
										   {      "sTitle":"Qte.dem"     ,  "sWidth": "10%"  ,"sName": "quantite_demander"   ,"sClass" : "alignCenter"   },
										   
										   {      "sTitle":"Qte.srv"     ,  "sWidth": "10%"  ,"sName": "quantite"   ,"sClass" : "alignCenter"   },  
										    
										   {      "sTitle":"Unite"       ,  "sWidth": "10%"       ,"sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"  },
										   
										   {      "sTitle":"Prix.U"    ,"sWidth": "10%"   , "sName": "tarif.tarif_unit_article"      ,"sClass" : "alignRight"    , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },
	                                        
	                                       {      "sTitle":"TVA"  ,  "sWidth": "10%"  , "sName": "tarif.tvaBean.tva_libelle"      },
	                                                   
	                                       {      "sTitle":"Montant.H.A"   ,"sWidth": "10%"  , "sName": "montant_ht_achat"  ,"sClass" : "alignRight"      , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    }, 
	                                                     
										   {      "sTitle":"Observation"  ,  "sWidth": "10%"  , "sName": "observation"      },
										   
										   
										   {      "sTitle":"Mnt.T.H.A"   ,"sWidth": "10%", "sName": "montant_tva_achat"  ,"sClass" : "alignRight"      , "bSortable": "true" ,"bVisible": false  
	                                              ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    }, 
	                                            ]
 
                               };
                               
                               
$(document).ready(function () {
     
LoadDataEditable_AUTO_height( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , 100  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );

});	

</script>
						
			    <table id="GRID_SAISIE_DETAIL_AHCAT" class="display" width="99%" >
			    
				 <tfoot>
				   <tr align="right"> 
						<td colspan="2"></td>
						<td align="center"></td>
						<td align="center"></td>
						<td  colspan="3" ></td>
						<td></td>
						<td ></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td colspan="2"></td>
						<td align="center"></td> 
						<td align="center"></td>
						<td colspan="3" ></td>
						<td></td>
						<td ></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td colspan="2"></td>
						<td align="center"></td>
						<td align="center"></td> 
						<td colspan="3" ></td>
						<td></td>
						<td></td>
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
			            var tot_avance  =json["avance"];  //json["avance"];
			            
				        var firstitems  = {"0":"Total Qté"    ,"1":qtessS   ,"2":"Total.H.T"  ,"3":tot_h_tvaS  };
				        var secondItems = {"2":"Total TVA"    ,"3":tot_tvaS };
				        var secondItem2 = {"2":"A compte"     ,"3":tot_avance };
				        var TroisIemIte = {"2":"Total Général","3":tot_GenS };
				        var items       = {"A":firstitems,"B":secondItems,"C":secondItem2,"D":TroisIemIte};
				    return  items; 
				}
               </script>
	    
	         </ext:panel>
     </ext:tabPanel>
		 
		   
</ext:panel>
</ext:body>
