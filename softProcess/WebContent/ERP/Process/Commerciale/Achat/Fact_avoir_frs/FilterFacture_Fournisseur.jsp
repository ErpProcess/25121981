 <%@include file="/Aceuil/esProcess.jsp" %>  
 <script type="text/javascript">
$(document).ready(function () {
LoadAutoCompletAjax_with_marGin("frs_id"  ,"frsref","depot_id","list_fournisseur_facture_frs","250","100");
});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${fact_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_frs_id"   name="fact_frs_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.fact_frs_id}"    nextElement="four_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>${frs_id}</label></td>  
   <td width="93%"  >  
   <input id="frs_id" name="frs.frs_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.frs.frs_id}"          nextElement="depot_id"         /> 
   <input id="frsref" name="frs.frsref"        type="text"    size="30"          value="${searchBean.frs.frsref}"          nextElement="depot_id"                        />
  </td>  
   </tr>   
  <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.fact_date}"   var="searchat_date"/>
	     <input id="fact_date"             compareTo="fact_date2"          name="vente_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"   /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.fact_date2}"   var="searchvente_date2"/>
	     <input id="fact_date2"            comparedTo="fact_date"          name="fact_date2"       type="datepicker"    size="13"       maxlength="13"        value="${searchvente_date2}"  /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   
     
   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
