 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${frs_id}</label></td>  
   <td width="93%"  >  
   <input id="frs_id"   name="frs_id"     type="text"    size="6"       maxlength="6"        value="${searchBean.frs_id}"    nextElement="frstel"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${frstel}</label></td>  
   <td width="93%"  >  
   <input id="frstel"   name="frstel"     type="text"    size="20"       maxlength="20"        value="${searchBean.frstel}"    nextElement="bancod"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${bancod}</label></td>  
   <td width="93%"  >  
   <input id="bancod"   name="bancod"     type="text"    size="6"       maxlength="6"        value="${searchBean.bancod}"    nextElement="frstyp"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${frstyp}</label></td>  
   <td width="93%"  >  
   <input id="frstyp"   name="frstyp"     type="text"    size="1"       maxlength="1"        value="${searchBean.frstyp}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
