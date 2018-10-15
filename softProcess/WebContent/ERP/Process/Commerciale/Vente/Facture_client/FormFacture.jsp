<%@include file="/Aceuil/esProcess.jsp" %>
<style>
.x-panel-btns x-panel-btns-right print_cert{
float: left;
}
</style> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
width_tabbJQuey="100%";
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
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
										  
										   {      "sTitle":"TVA" , "sName": "tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  },
										           
										   {      "sTitle":"Prix U"    , "sName": "tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                        ,"mRender": function (data, type, full) {   if( "${detailBean.devise.dev_id}"=="191"  ||  "${detailBean.devise.dev_id}"=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
	                                      
	                                       {      "sTitle":"Remise"    , "sName": "taux_remise_ligne"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return   addPourcentage(data);}  ,"bVisible": true   },            
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                         ,"mRender": function (data, type, full) {   if(  "${detailBean.devise.dev_id}"=="191"  ||  "${detailBean.devise.dev_id}"=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
	                                              
	                                       {      "sTitle":"Mnt TTC" , "sName": "montant_ttc_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                        ,"mRender": function (data, type, full) {   if( "${detailBean.devise.dev_id}"=="191"  ||  "${detailBean.devise.dev_id}"=="192" ) return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },               
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };
       
$(document).ready(function (){
   LoadAutoCompletAjax("cptbanribrib","cptbanribrs","btValidx","list_cpt_bank_fact");
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  ); 
 
 

});                                                                                         
 
 
 
 
								                        
 </script>

<ext:body  >
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
    <ext:panel  border="false"    bodyStyle="background: none;"        >
      <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >
        <tr>
          <td width="11%"><label>${fact_id}</label></td>
          <td    >
          <input id="fact_clt_id" name="fact_clt_id"     libre   readonly="readonly"     type="text"      size="20"       maxlength="25"        value="${detailBean.fact_clt_id}"    nextElement="clt_id"              />
           <label style="padding-left: 15px;">code</label>
          <input id="fact_ref_id" name="fact_ref_id"    type="text"      size="20"   required    maxlength="25"        value="${detailBean.fact_ref_id}"    nextElement="clt_id"              />
          </td>
          <td><label>Mode Reg</label></td>
          <td> 
           <script>   $(function() {  loadSelectAjax("reg_modXX","list_mode_reglment_facture","mod_id","mod_libelle","${detailBean.modReg.mod_id}",true);  })</script>
           <select    id="reg_modXX"  name="modReg.mod_id"     required     style="width: 180px;"      nextelement="reg_nbr_echeance"        ></select>
          </td>
        </tr>
        <tr>
          <td width="11%"><label>${clt_id}</label></td>
          <td width="60%"  ><input id="clt_id" name="client.clt_id"     libre   readonly="readonly"    type="text"    size="10"       maxlength="10"        value="${detailBean.client.clt_id}"    nextElement="fact_date"              />
            <input id="clt_lib" name="client.clt_lib"   libre   readonly="readonly"    type="text"    size="40"            value="${detailBean.client.clt_lib}"    nextElement="fact_date"              />
          </td>
          <td width="10%"  ><label>${avance_montant_vente}</label></td>
          <td  width="17%"  ><input id="avance_montant_vente"     name="avance_montant_vente"  style="text-align: right;"    type="montant3"    size="25"    libre="libre"   readonly="readonly"      maxlength="50"        value="${detailBean.avance_montant_vente}"    nextelement="btValidx"></td>
        </tr>
        <tr>
          <td ><label>${fact_date}</label></td>
          <td  ><fmt:formatDate pattern="dd/MM/yyyy"       value="${detailBean.fact_date}"      var="searchat_datefac"/>
            <input id="fact_date" name="fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefac}"    nextElement="fact_date_edition"              />
          </td>
          <td  >remise </td>
          <td   ><input id="facture_remise"     name="facture_remise"  style="text-align: right;"    type="montant3"    size="25"    libre="libre"   readonly="readonly"      maxlength="50"        value="${detailBean.facture_remise}"    nextelement="btValidx"></td>
        </tr>
        <tr>
          <td ><label>${fact_date_edition}</label></td>
          <td  ><input id="fact_date_edition" name="fact_date_edition"   libre   readonly="readonly"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.fact_date_edition}"    nextElement="montant_timbre_fisc"              />
          </td>
          <td  ><label>Total TTC </label></td>
          <td   ><input id="total_facture"     name="total_facture"  style="text-align: right;"    type="montant3"    size="25"    libre="libre"   readonly="readonly"      maxlength="50"        value="${detailBean.total_facture}"    nextelement="btValidx"></td>
        </tr>
        <tr>
          <td  ><label>Virement bancaire</label></td>
          <td   ><input id="cptbanribrib"  name="cpt_bank.cptbanribrib"      type="text"      size="10"               value="${detailBean.cpt_bank.cptbanribrib}"        required="required">
            <input id="cptbanribrs" name="cpt_bank.cptbanribrs"     type="text"    size="40"                value="${detailBean.cpt_bank.cptbanribrs}"         required="required"></td>
          <td   ><label>Net a payer</label></td>
          <td   ><input id="net_a_payer"     name="net_a_payer"  style="text-align: right;"    type="montant3"    size="25"    libre="libre"   readonly="readonly"      maxlength="50"        value="${detailBean.net_a_payer}"    nextelement="btValidx"></td>
        </tr>
        
         <tr style="${detailBean.btnPrintCertificat}">
          <td  colspan="4" align="left" >
          <script type="text/javascript">
          function imprimerExport() {
        	    var url = contexPath+"${tmlx.url}?HiddenAction=i$_ACT_IMPRIMER_EXPORT_KB";
        		genericPdfProcess(url);     
        	}

          </script> <div id="scxxx" class="x-panel-btns x-panel-btns-right print_cert" ></div>
          <ext:button type="button" text="imprimer cerificat"  onClick="imprimerExport()"  style="${detailBean.btnPrintCertificat}"   renderTo="scxxx"><label>imprimer Transit</label></ext:button>
          </td>
        </tr>
        
      </table>
    </ext:panel>
    <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Facture"    >
      <table id="GRID_DETAIL_FACTURE_CLIENT" class="display" width="100%"      >
        <tfoot>
          <tr>
            <td   height="50px" colspan="10" ></td>
          </tr>
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
              <td ></td>
            </tr>
          </c:forEach>
          <c:forEach var="i" begin="1" end="8">
            <tr  align="right">
              <td ></td>
              <td ></td>
              <td ></td>
              <td ></td>
              <td colspan="3" ></td>
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
