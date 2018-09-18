 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${caisse_id}</label></td>  
   <td width="93%"  >  
   <input id="caisse_id"   name="caisse_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.caisse_id}"    nextElement="caisse_libelle"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${caisse_libelle}</label></td>  
   <td width="93%"  >  
   <input id="caisse_libelle"   name="caisse_libelle"     type="text"    size="100"       maxlength="100"        value="${searchBean.caisse_libelle}"    nextElement="caisse_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${caisse_type}</label></td>  
   <td width="93%"  >  
   <input id="caisse_type"   name="caisse_type"     type="text"    size="10"       maxlength="10"        value="${searchBean.caisse_type}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
