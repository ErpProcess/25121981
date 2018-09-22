 <%@include file="/Aceuil/esProcess.jsp" %>
  <script > 
  dataGridConfig_width="120%"; 
 var mapColumsbean=[  
  { "sTitle": "${reg_date}"       ,"sName": "reg_date"       ,"sWidth": "5%"                 , "bSortable": "true"   }, 
  //{ "sTitle": "${reg_id}"       ,"sName": "reg_id"                   , "bSortable": "true"   },  
  { "sTitle": "${fact_id}"       ,"sName": "factclient.fact_clt_id"     ,"sWidth": "10%"                  , "bSortable": "true"   },  
  { "sTitle": "client"       ,"sName": "factclient.client.clt_lib"     ,"sWidth": "20%"                 , "bSortable": "true"   },
  //{ "sTitle": "${reg_mod}"       ,"sName": "mode.data_libelle"                    , "bSortable": "true"   },  
  { "sTitle": "${reg_nature}"       ,"sName": "nature.data_libelle"       ,"sWidth": "10%"                   , "bSortable": "true"   },  
  { "sTitle": "Nbr.Ech"       ,"sName": "reg_nbr_echeance"    ,"sWidth": "5%"   , "bSortable": "true"   },  
  { "sTitle": "${montant_facture}"     ,"sClass" : "alignRight"       ,"sName": "montant_facture"    ,"sWidth": "13%"   ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }     , "bSortable": true   },  
  { "sTitle": "${montant_avance}"      ,"sClass" : "alignRight"       ,"sName": "montant_avance"     ,"sWidth": "13%"   ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }     , "bSortable": true   },  
  { "sTitle": "Montant Reçu"           ,"sClass" : "alignRight"       ,"sName": "montant_recu"       ,"sWidth": "13%"   ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }     , "bSortable": true   }, 
  { "sTitle": "${montant_restant}"     ,"sClass" : "alignRight"       ,"sName": "montant_restant"    ,"sWidth": "11%"   ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }     , "bSortable": true  },  
  //{ "sTitle": "${num_piece}"       ,"sName": "num_piece"                     , "bSortable": "true"   },  
 
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />   
 <script type="text/javascript">
$(document).ready(function () {
$('#${nameGrid}').css('display','none');
$('#RET_GRID').css('display','none');
LoadAutoCompletAjax_with_marGin("clt_id"  ,"clt_lib","depot_id","list_client_for_facture","250","100");
});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table width="100%" height="222"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
   <tr>  
   <td width="12%" height="32"><label>${reg_id}</label></td>  
   <td width="24%"  >  
   <input id="reg_id"   name="reg_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.reg_id}"    nextElement="fact_id"    autofocus         />  </td>  
   <td width="11%"  >${fact_id}</td>
   <td width="53%"  ><input id="fact_id"   name="factclient.fact_clt_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.factclient.fact_clt_id}"    nextelement="reg_mod"></td>
   </tr>  
   
    <tr>  
   <td width="12%" height="32"><label>Code</label></td>  
  
    <td colspan="3"  ><input id="fact_ref"   name="factclient.fact_ref_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.factclient.fact_ref_id}"    nextelement="reg_mod"></td>
   
   </tr>  
    
   <tr>  
   <td width="12%" height="32"><label>${reg_mod}</label></td>  
   <td  >
  
   
     <script  >$(function() {  loadSelectAjax("reg_modXX","list_mode_reglment","mod_id","mod_libelle","${searchBean.modReg.mod_id}",true);  })</script>
        <select    id="reg_modXX"  name="modReg.mod_id"           style="width: 180px;"      nextelement="num_piece"        ></select>
   
   </td>  
   <td ><label>${clt_id}</label></td>
   <td >
   
   <input id="clt_id"  name="factclient.client.clt_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.factclient.client.clt_id}"          nextElement="depot_id"           /> 
   <input id="clt_lib" name="factclient.client.clt_lib"        type="text"    size="30"          value="${searchBean.factclient.client.clt_lib}"          nextElement="depot_id"                      />
   
   </td>
    
   
   </tr>   
   <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td >  
	   <table >
	   <tr>
	     <td width="40%" > 
	     <input id="reg_date"         name="reg_date"    compareTo="reg_date2"  
	        type="datepicker"    size="13"       maxlength="13"        value="${searchBean.reg_date}"    nextElement="reg_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	     <input id="reg_date2"       name="reg_date2"    comparedTo="reg_date"      type="datepicker"    size="13"     
	       maxlength="13"        value="${searchBean.reg_date2}"    nextElement="reg_nbr_echeance"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
    <tr> 
   <td width="12%" height="32"><label>${reg_nbr_echeance}</label></td>  
   <td  ><input id="reg_nbr_echeance"   name="reg_nbr_echeance"     type="text"    size="10"       maxlength="10"        value="${searchBean.reg_nbr_echeance}"    nextelement="montant_facture"></td>  
   <td  ><label>${reg_nature}</label></td>
   <td  >
   
    <script  >$(function() {  loadSelectAjax("natureReg","list_nature_reglement","data_id","data_libelle","${searchBean.nature.data_id}",true);  })</script>
   <select id="natureReg"  name="nature.data_id"     style="width: 150px;"     /></td>
   </tr>   
   <tr>  
   <td width="12%" height="32"><label>${montant_avance}</label></td>  
   <td  ><input id="montant_avance"   name="montant_avance"     type="text"    size="17"       maxlength="17"        value="${searchBean.montant_avance}"    nextelement="montant_restant"></td>  
   <td  ><label>${montant_facture}</label></td>
   <td  ><input id="montant_facture"   name="montant_facture"     type="text"    size="17"       maxlength="17"        value="${searchBean.montant_facture}"    nextelement="montant_avance"></td>
   </tr>   
   <tr>  
   <td width="12%" height="32"><label>${montant_restant}</label></td>  
   <td colspan="3"  ><input id="montant_restant"   name="montant_restant"     type="text"    size="17"       maxlength="17"        value="${searchBean.montant_restant}"    nextelement="num_piece"></td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"  style="display:none;"     >

 <table id="${nameGrid}"   class="display"  style="width: 120%;"  >  
 <tfoot> 
				   <tr align="right"> 
						<td colspan="5"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td  align="right"></td>
						<td  align="right"  ></td>
				    </tr>
				 </tfoot>
			    </table>
			    <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var totMntFacture     =json["totMntFacture"];
                        var totMntAvance      =json["totMntAvance"];
			            var totMntRecu        =json["totMntRecu"];
			            var totMntRestant     =json["totMntRestant"];
				        var firstitems  = {"0":"Total"    ,"1":totMntFacture   ,"2":totMntAvance  ,"3":totMntRecu , "4":totMntRestant};
				        var items       = {"A":firstitems };
				    return  items; 
				}
               </script> 
</ext:panel>
</ext:panel>
</ext:body>
<script>
Ext.onReady(

function(){  
try {	
         
<c:if test="${not empty dataListAajx}">        
Ext.get('RET_GRID').setStyle('display', 'block');    
</c:if>    
       
} catch(e){

}
});               
</script>        
