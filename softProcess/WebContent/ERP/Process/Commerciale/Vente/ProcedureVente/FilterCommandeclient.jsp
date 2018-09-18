 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >
 
   
   <tr>  
    <td width="10%"><label>${cmd_id}</label></td>  
    <td width="90%"  >  
    <input id="cmd_id"   name="commande.cmd_id"     type="text"    size="15"           value="${searchBean.commande.cmd_id}"    nextElement="cmd_date"    autofocus         />  
    </td>  
   </tr>   
   
   
   
    <tr>  
   
    
   <td ><label>${_datedebut} </label></td>  
   <td >  
	   <table >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.commande.cmd_date}"   var="searchat_date"/>
	     <input id="cmd_date"       compareTo="cmd_date2"   name="commande.cmd_date"    type="datepicker"    size="13"   maxlength="13"   value="${searchat_date}"             nextElement="cmd_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.commande.cmd_date2}"   var="searchat_date2"/>
	     <td><input id="cmd_date2"  comparedTo="cmd_date"   name="commande.cmd_date2"   type="datepicker"    size="13"   maxlength="13"   value="${searchat_date2}"    nextElement="clt_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   
     
  <tr>  
	       <td ><label>${clt_id}</label></td>  
	       <td  > 
	       <script type="text/javascript">
	       $(document).ready(function () {LoadAutoCompletAjax("clt_id","clt_lib","dev_lib","list_client_for_vente");  });
	       </script> 
	       <input id="clt_id"  name="commande.client.clt_id"           type="text"     size="10"          value="${searchBean.commande.client.clt_id}"                /> 
	       <input id="clt_lib" name="commande.client.clt_lib"          type="text"    size="30"           value="${searchBean.commande.client.clt_lib}"            />    
	       </td>  
	    </tr>  
	    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
