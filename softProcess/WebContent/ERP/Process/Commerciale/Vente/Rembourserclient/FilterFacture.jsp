 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${fact_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_clt_id"   name="fact_clt_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.fact_clt_id}"    nextElement="clt_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_id}</label></td>  
   <td width="93%"  >  
   <input id="clt_id"   name="client.clt_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.client.clt_id}"    nextElement="fact_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_date}</label></td>  
   <td width="93%"  >  
   <input id="fact_date"   name="fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.fact_date}"    nextElement="fact_date_edition"              />  
  </td>  
   </tr>   
    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
