 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${frn_ve_id}</label></td>  
   <td width="93%"  >  
   <input id="frn_ve_id"   name="frn_ve_id"     type="text"    size="17"       maxlength="17"        value="${searchBean.frn_ve_id}"    nextElement="vente_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id"   name="vente_id"     type="text"    size="17"       maxlength="17"        value="${searchBean.vente_id}"    nextElement="frn_ve_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${frn_ve_date}</label></td>  
   <td width="93%"  >  
   <input id="frn_ve_date"   name="frn_ve_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.frn_ve_date}"    nextElement="clt_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_id}</label></td>  
   <td width="93%"  >  
   <input id="clt_id"   name="clt_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.clt_id}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
