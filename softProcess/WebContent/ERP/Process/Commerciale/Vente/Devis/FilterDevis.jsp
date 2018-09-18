 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${devis_id}</label></td>  
   <td width="93%"  >  
   <input id="devis_id"   name="devis_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.devis_id}"    nextElement="clt_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   
    
   <td ><label>${_datedebut} </label></td>  
   <td >  
	   <table >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.dev_date}"   var="searchat_date"/>
	     <input id="dev_date"       compareTo="dev_date2"   name="dev_date"    type="datepicker"    size="13"   maxlength="13"   value="${searchat_date}"             nextElement="dev_date"              /></td>
	     <td><label>${_datefin}</label></td>
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.dev_date2}"   var="searchat_date2"/>
	     <td><input id="dev_date2"  comparedTo="dev_date"   name="dev_date2"   type="datepicker"    size="13"   maxlength="13"   value="${searchat_date2}"    nextElement="frs_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   
  
   
     <tr>  
	       <td ><label>${clt_id}</label></td>  
	       <td  > 
	       <script type="text/javascript">
	       
	       $(document).ready(function () {
 LoadAutoCompletAjax("clt_id","clt_lib","dev_lib","list_client_devis");  
});
	       
	       </script> 
	       <input id="clt_id"  name="client.clt_id"           type="text"     size="10"          value="${searchBean.client.clt_id}"                /> 
	       <input id="clt_lib" name="client.clt_lib"          type="text"    size="30"           value="${searchBean.client.clt_lib}"            />    
	       </td>  
	    </tr>  
	    
   
   
   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
