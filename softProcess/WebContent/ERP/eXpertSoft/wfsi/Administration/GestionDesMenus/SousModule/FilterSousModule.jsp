<%@ include file="/Aceuil/esProcess.jsp"     %>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${sousmod_id}</label></td>  
   <td width="93%"  >  
   <input id="sousmod_id" name="sousmod_id"     type="text" size="20"  value="${searchBean.sousmod_id}"     nextElement="sousmod_id"    autofocus          />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${sousmod_libelle}</label></td>  
   <td width="93%"  >  
   <input id="sousmod_libelle" name="sousmod_libelle"     type="text" size="20"  value="${searchBean.sousmod_libelle}"     nextElement="sousmod_libelle"               />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
