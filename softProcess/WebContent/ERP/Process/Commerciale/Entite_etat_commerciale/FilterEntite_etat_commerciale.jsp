 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${code_entite}</label></td>  
   <td width="93%"  >  
   <input id="code_entite"   name="code_entite"     type="text"    size="20"       maxlength="20"        value="${searchBean.code_entite}"    nextElement="libelle_entite"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${libelle_entite}</label></td>  
   <td width="93%"  >  
   <input id="libelle_entite"   name="libelle_entite"     type="text"    size="50"       maxlength="50"        value="${searchBean.libelle_entite}"    nextElement="data_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${data_id}</label></td>  
   <td width="93%"  >  
   <input id="data_id"   name="data_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.data_id}"    nextElement="data_libelle"              />  
  </td>   
   </tr>   
   <tr>  
   <td width="7%"><label>${data_libelle}</label></td>  
   <td width="93%"  >  
   <input id="data_libelle"   name="data_libelle"     type="text"    size="150"       maxlength="150"        value="${searchBean.data_libelle}"    nextElement="data_ordre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${data_ordre}</label></td>  
   <td width="93%"  >  
   <input id="data_ordre"   name="data_ordre"     type="text"    size="10"       maxlength="10"        value="${searchBean.data_ordre}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
