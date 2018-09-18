 <%@include file="/Aceuil/esProcess.jsp" %>
 
 
  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel   border="false"     bodyStyle="background: none;"     title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"><label>${dem_achat_id}</label></td>  
   <td width="90%"  >  
   <input id="dem_achat_id"   name="dem_achat.dem_achat_id"     type="text"    size="25"       maxlength="25"        value="${searchBean.dem_achat.dem_achat_id}"    nextElement="four_id"    autofocus         />  
  </td>  
   </tr>  
   
   
    <tr>  
   <td  ><label>${_datedebut}</label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td  > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.dem_achat.dem_date}"   var="searchat_date"/>
	     <input id="dem_date"             compareTo="dem_date2"      name="dem_achat.dem_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="dem_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.dem_achat.dem_date2}"   var="searchat_date2"/>
	     <td><input id="dem_date2"        comparedTo="dem_date"      name="dem_achat.dem_date2"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_date2}"    nextElement="frs_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   
    
  <tr>  
	       <td ><label>${four_id}
	     <script type="text/javascript">
$(document).ready(function () {
 LoadAutoCompletAjax("frs_id","frsref","dem_obs","list_fournisseur_recep_achat");        
});
	     </script>
	       
	       </label></td>  
	       <td  >
	       
	       <input id="frs_id" name="dem_achat.frsBean.frs_id"        type="text"    size="10"       maxlength="10"     
	          value="${searchBean.dem_achat.frsBean.frs_id}"   nextElement="dem_obs"        /> 
	        <input id="frsref" name="frsBean.frsref"        type="text"    size="30"             
	          value="${searchBean.dem_achat.frsBean.frsref}"          />    
	       </td>  
	    </tr>  
    
      
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
