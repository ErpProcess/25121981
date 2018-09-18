 <%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
$(document).ready(function () {LoadAutoCompletAjax_with_marGin("clt_id"  ,"clt_lib","depot_id","list_client_for_facture","250","100");});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
 
 
   <tr>  
   <td width="7%"><label>${avoir_id}</label></td>  
   <td width="93%"  >  
   <input id="avoir_id"   name="avoir_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.avoir_id}"    nextElement="clt_id"    autofocus         />  
  </td> 
  
  
  
   <tr>  
   <td width="7%"><label>${fact_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_clt_id"   name="factclient.fact_clt_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.factclient.fact_clt_id}"    nextElement="clt_id"    autofocus         />  
  </td>  
  
  <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.avoir_date}"   var="searchat_date"/>
	     <input id="avoir_date"             compareTo="avoir_date2"          name="avoir_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"   /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.avoir_date}"   var="searchvente_date2"/>
	     <input id="avoir_date2"            comparedTo="avoir_date"          name="avoir_date2"       type="datepicker"    size="13"       maxlength="13"        value="${searchvente_date2}"  /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${clt_id}</label></td>  
   <td   >  
   <input id="clt_id"  name="factclient.client.clt_id"         type="text"    size="10"          value="${searchBean.factclient.client.clt_id}"    /> 
   <input id="clt_lib" name="factclient.client.clt_lib"        type="text"    size="30"          value="${searchBean.factclient.client.clt_lib}"                   />
  </td>  
   </tr>  
    
    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
