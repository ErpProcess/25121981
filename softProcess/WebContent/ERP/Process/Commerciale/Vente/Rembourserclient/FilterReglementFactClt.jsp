 <%@include file="/Aceuil/esProcess.jsp" %>  
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
   <td width="12%" height="32"><label>${reg_mod}</label></td>  
   <td  >
   <script  >$(function() {  loadSelectAjax("reg_mod","list_mode_reglment","data_id","data_libelle","${searchBean.mode.data_id}",true);  })</script>
   
   <select id="reg_mod"   name="mode.data_id"   style="width: 150px;"   /></td>  
   <td  ><label>${reg_date}</label></td>
   <td  ><input id="reg_date"   name="reg_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.reg_date}"    nextelement="reg_nbr_echeance"></td>
   </tr>   
   <tr>  
   <td width="12%" height="32"><label>${reg_nbr_echeance}</label></td>  
   <td  ><input id="reg_nbr_echeance"   name="reg_nbr_echeance"     type="text"    size="10"       maxlength="10"        value="${searchBean.reg_nbr_echeance}"    nextelement="montant_facture"></td>  
   <td  ><label>${reg_nature}</label></td>
   <td  >
   
    <script  >$(function() {  loadSelectAjax("reg_nature","list_nature_reglement","data_id","data_libelle","${searchBean.nature.data_id}",true);  })</script>
   <select id="reg_nature"   name="reg_nature" style="width: 150px;"     /></td>
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
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
