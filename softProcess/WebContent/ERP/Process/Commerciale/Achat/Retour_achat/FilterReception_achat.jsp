 <%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
$(document).ready(function () {
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","achat_obs","list_depot_stock","250","100");
LoadAutoCompletAjax_with_marGin("frs_id"  ,"frsref","depot_id","list_fournisseur_recep_achat","250","100");
});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel   border="false"    bodyStyle="background: none;"      title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"  ><label>${achat_id}</label></td>  
   <td width="90%"  >  
   <input id="achat_id"   name="achat.achat_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.achat.achat_id}"    nextElement="dem_achat_id"    autofocus         />  
  </td>  
   </tr> 
   
   
    <tr>  
   <td  ><label>${dem_achat_id}</label></td>  
   <td   >  
   <input id="dem_achat_id"   name="achat.dem_achat.dem_achat_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.achat.dem_achat.dem_achat_id}"    nextElement="achat_libelle"              />  
  </td>  
   </tr> 
     
   <tr>  
   <td  ><label>${achat_libelle}</label></td>  
   <td   >  
   <input id="achat_libelle"   name="achat.achat_libelle"     type="text"    size="15"       maxlength="15"        value="${searchBean.achat.achat_libelle}"    nextElement="achat_date"              />  
  </td>  
   </tr>   
  <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.achat.achat_date}"   var="searchat_date"/>
	     <input id="achat_date"             compareTo="achat_date2"      name="achat.achat_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="achat_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td><input id="achat_date2"        comparedTo="achat_date"      name="achat.achat_date2"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.achat.achat_date2}"    nextElement="frs_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${frs_id}</label></td>  
   <td   >  
   <input id="frs_id" name="achat.frsBean.frs_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.achat.frsBean.frs_id}"          nextElement="depot_id"        /> 
   <input id="frsref" name="achat.frsBean.frsref"        type="text"    size="30"          value="${searchBean.achat.frsBean.frsref}"          nextElement="depot_id"        />
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${depot_id}</label></td>  
   <td    >  
 
   
   
   <input id="depot_id"      name="achat.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.achat.depot.depot_id}"    nextElement="btValidx"                       />  
   <input id="depot_libelle" name="achat.depot_stocks.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${searchBean.achat.depot.depot_libelle}"    nextElement="btValidx"         />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${list_pdf_excel}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
