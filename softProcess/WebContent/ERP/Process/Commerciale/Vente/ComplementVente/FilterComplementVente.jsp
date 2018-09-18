 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${comp_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="comp_vente_id"   name="comp_vente_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.comp_vente_id}"    nextElement="vente_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id"   name="vente.vente_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.vente.vente_id}"    nextElement="comp_vente_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${comp_vente_date}</label></td>  
   <td width="93%"  >  
   <input id="comp_vente_date"   name="comp_vente_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.comp_vente_date}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
