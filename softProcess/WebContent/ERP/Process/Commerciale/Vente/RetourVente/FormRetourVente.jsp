<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
width_tabbJQuey="120%";
 
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_RETOUR",
                               "list"   :"list_editable_RetourVente",
                               "id_name":"pk.detv.pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									  
									                  
										   {      "sTitle":"Référence"    ,"sName": "pk.detv.pk.fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sTitle":"Désignation"    ,"sName": "pk.detv.pk.fkcode_barre.designation_libelle"    ,"sWidth": "25%"   },       
										    
										   {      "sTitle":"Qte.SRV"    ,"sName": "pk.detv.quantite_confirmer"                    , "sWidth": "5%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },
										                 
										   {      "sTitle":"Qte.RET"    ,"sName": "quantite_retourne"                             , "sWidth": "5%"    },
										  
										    {     "sTitle":"Unite"    , "sName": "pk.detv.pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "10%"     ,"bSearchable": true },
										    
										    {     "sTitle":"cause"    , "sName": "cause.nature_incident_lib"       ,"sWidth": "20%"     ,"bSearchable": true },
										    
										    {     "sTitle":"Stock"    , "sName": "sens.data_libelle"       ,"sWidth": "10%"     ,"bSearchable": true },
										           
										           
										   {      "sTitle":"Prix U"    , "sName": "pk.detv.tarif.tarif_unit_vente"   ,"sWidth": "15%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_retour"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": "true"   
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
									 
	                                            ]
 
                               };
 


 
                           
$(document).ready(function (){

LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
});                                     

</script>


  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
 <ext:panel    bodyStyle="background: none;"    border="false"      >   
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${ret_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="ret_vente_id" name="ret_vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.ret_vente_id}"    nextElement="vente_id"    autofocus    readonly="readonly"  libre     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id" name="vente.vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.vente.vente_id}"    nextElement="ret_vente_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ret_vente_date}</label></td>  
   <td width="93%"  >  
   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.ret_vente_date}"   var="dateInputString"/>   
   <input id="ret_vente_date" name="ret_vente_date"     type="datepicker"    size="13"       maxlength="13"        value="${dateInputString}"    nextElement="re_vente_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${re_vente_obs}</label></td>  
   <td width="93%"  >  
   <input id="re_vente_obs" name="re_vente_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.re_vente_obs}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>  
</ext:panel> 
 
  <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille retour vente"    > 
	   <table id="GRID_SAISIE_DETAIL_RETOUR" class="display" width="100%" >
	   </table>
  </ext:panel> 
  
</ext:panel>


</ext:body>
