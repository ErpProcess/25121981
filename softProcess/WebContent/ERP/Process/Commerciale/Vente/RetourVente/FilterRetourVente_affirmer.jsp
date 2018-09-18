 <%@include file="/Aceuil/esProcess.jsp" %> 
 
 <script type="text/javascript">
$(document).ready(function () {
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","btValidx","list_depot_stock","250","100");
LoadAutoCompletAjax_with_marGin("clt_id"  ,"clt_lib","depot_id","list_client_for_vente","250","100");
LoadAutoCompletAjax_with_marGin("data_id"  ,"data_libelle","","list_ret_vente","250","100");
LoadAutoCompletAjax_with_marGin("frs_id"  ,"frsref","","list_fournisseurs","250","100");
LoadAutoCompletAjax_with_marGin("nature_incident_id"  ,"nature_incident_lib","","list_cause_retour","250","100");
LoadAutoCompletAjax_with_marGin("src_incident_ret_vente_id"  ,"src_incident_ret_vente_lib","","list_source_cause_retour","250","100");


});
</script>


 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"     bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 
  <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="12%"  ><label>${vente_id}</label></td>  
   <td width="87%"  >  
   <input id="vente_id"   name="pk.r_vente.vente.vente_id"     type="text"    size="15"              value="${searchBean.pk.r_vente.vente.vente_id}"    nextElement="cmd_id"    autofocus         />  
  </td>  
   </tr> 
   
    <tr>  
   <td  ><label>${ret_vente_id}</label></td>  
   <td   >  
   <input id="ret_vente_id"   name="pk.r_vente.ret_vente_id"     type="text"    size="15"            value="${searchBean.pk.r_vente.ret_vente_id}"    nextElement="vente_libelle"              />  
  </td>  
   </tr> 
     
   
  <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.pk.r_vente.ret_vente_date}"   var="searchat_date"/>
	     <input id="ret_vente_date"             compareTo="ret_vente_date2"      name="ret_vente_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="ret_vente_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.pk.r_vente.ret_vente_date2}"   var="searchret_vente_date2"/>
	     <input id="ret_vente_date2"        comparedTo="ret_vente_date"      name="ret_vente_date2"     type="datepicker"    size="13"       maxlength="13"        value="${searchret_vente_date2}"    nextElement="clt_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${clt_id}</label></td>  
   <td   >  
   <input id="clt_id" name="pk.r_vente.vente.client.clt_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.pk.r_vente.vente.client.clt_id}"          nextElement="depot_id"        /> 
   <input id="clt_lib" name="pk.r_vente.vente.client.clt_lib"        type="text"    size="30"          value="${searchBean.pk.r_vente.vente.client.clt_lib}"          nextElement="depot_id"        />
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${depot_id}</label></td>  
   <td    >  
   <input id="depot_id" name="pk.r_vente.vente.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.pk.r_vente.vente.depot.depot_id}"    nextElement="btValidx"                       />  
   <input id="depot_libelle" name="pk.r_vente.vente.depot.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${searchBean.pk.r_vente.vente.depot.depot_libelle}"    nextElement="btValidx"         />  
  </td>  
   </tr>
   
       <tr>  
   <td  ><label>Fournisseur</label></td>  
   <td    >  
   <input id="frs_id" name="recep.frsBean.frs_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.recep.frsBean.frs_id}"    nextElement="btValidx"                       />  
   <input id="frsref" name="recep.frsBean.frsref"     type="text"    size="30"       maxlength="10"        value="${searchBean.recep.frsBean.frsref}"    nextElement="btValidx"         />  
  </td>  
   </tr>
   
       <tr>  
   <td  ><label>Opération Stock</label></td>  
   <td    >  
   <input id="data_id" name="sens.data_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.sens.data_id}"    nextElement="btValidx"                       />  
   <input id="data_libelle" name="sens.data_libelle"     type="text"    size="30"       maxlength="10"        value="${searchBean.sens.data_libelle}"    nextElement="btValidx"         />  
  </td>  
   </tr>
   
   
   
   <tr>  
     <td  ><label>Cause</label></td>  
     <td    >  
      <input id="nature_incident_id" name="cause.nature_incident_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.cause.nature_incident_id}"    nextElement="btValidx"                       />  
      <input id="nature_incident_lib" name="vente.nature_incident_lib"     type="text"    size="30"       maxlength="10"        value="${searchBean.cause.nature_incident_lib}"    nextElement="btValidx"         />  
    </td>  
   </tr>
   
   <tr>  
     <td  ><label>Responsable du retour</label></td>  
     <td  >  
        <input id="src_incident_ret_vente_id"  name="source.src_incident_ret_vente_id"      type="hidden"    size="10"       maxlength="10"        value="${searchBean.source.src_incident_ret_vente_id}"    nextElement="btValidx"                       />  
        <input id="src_incident_ret_vente_lib" name="source.src_incident_ret_vente_lib"     type="text"    size="45"       maxlength="10"        value="${searchBean.source.src_incident_ret_vente_lib}"       nextElement="btValidx"         />
    </td>  
   </tr>
   
   
    
 </table> 
 
 
 
 
 
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="ListRetourVente_affirmer.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
