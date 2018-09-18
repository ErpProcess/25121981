 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"><label>${nature_mvt_id}</label></td>  
   <td width="90%"  >  
   <input id="nature_mvt_id"   name="nature_mvt_id"     type="text"    size="5"       maxlength="5"        value="${searchBean.nature_mvt_id}"    nextElement="nature_mvt_libelle"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>${nature_mvt_libelle}</label></td>  
   <td width="90%"  >  
   <input id="nature_mvt_libelle"   name="nature_mvt_libelle"     type="text"    size="50"       maxlength="50"        value="${searchBean.nature_mvt_libelle}"    nextElement="nature_mvt_ordre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>${nature_mvt_ordre}</label></td>  
   <td width="90%"  >  
   <input id="nature_mvt_ordre"   name="nature_mvt_ordre"     type="text"    size="10"       maxlength="10"        value="${searchBean.nature_mvt_ordre}"    nextElement="nature_operation"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>${nature_operation}</label></td>  
   <td width="90%"  >  
   <input id="nature_operation"   name="nature_operation"     type="text"    size="1"       maxlength="1"        value="${searchBean.nature_operation}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
