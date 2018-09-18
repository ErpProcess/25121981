 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${dev_abrv}</label></td>  
   <td width="93%"  >  
   <input id="dev_abrv"   name="dev_abrv"     type="text"    size="3"       maxlength="3"        value="${searchBean.dev_abrv}"    nextElement="dev_libelle"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${dev_libelle}</label></td>  
   <td width="93%"  >  
   <input id="dev_libelle"   name="dev_libelle"     type="text"    size="50"       maxlength="50"        value="${searchBean.dev_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
