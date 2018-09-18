 <%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
$(document).ready(function () {
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","btValidx","list_depot_stock","250","100");
LoadAutoCompletAjax_with_marGin("clt_id"  ,"clt_lib","depot_id","list_client_for_vente","250","100");

});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel   border="false"    bodyStyle="background: none;"      title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"  ><label>${vente_id}</label></td>  
   <td width="90%"  >  
   <input id="vente_id"   name="vente_id"     type="text"    size="15"            value="${searchBean.vente_id}"    nextElement="cmd_id"    autofocus         />  
  </td>  
   </tr> 
   
   
    <tr>  
   <td  ><label>${cmd_id}</label></td>  
   <td   >  
   <input id="cmd_id"   name="commande.cmd_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.commande.cmd_id}"    nextElement="vente_libelle"              />  
  </td>  
   </tr> 
     
   <tr>  
   <td  ><label>${vente_libelle}</label></td>  
   <td   >  
   <input id="vente_libelle"   name="vente_libelle"     type="text"    size="15"        value="${searchBean.vente_libelle}"    nextElement="achat_date"              />  
  </td>  
   </tr>   
  <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.vente_date}"   var="searchat_date"/>
	     <input id="vente_date"             compareTo="vente_date2"      name="vente_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="vente_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.vente_date2}"   var="searchvente_date2"/>
	     <input id="vente_date2"        comparedTo="vente_date"      name="vente_date2"     type="datepicker"    size="13"       maxlength="13"        value="${searchvente_date2}"    nextElement="clt_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${clt_id}</label></td>  
   <td   >  
   <input id="clt_id" name="client.clt_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.client.clt_id}"          nextElement="depot_id"        /> 
   <input id="clt_lib" name="client.clt_lib"        type="text"    size="30"          value="${searchBean.client.clt_lib}"          nextElement="depot_id"        />
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${depot_id}</label></td>  
   <td    >  
 
   
   
   <input id="depot_id" name="depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.depot.depot_id}"    nextElement="btValidx"                       />  
   <input id="depot_libelle" name="depot.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${searchBean.depot.depot_libelle}"    nextElement="btValidx"         />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
