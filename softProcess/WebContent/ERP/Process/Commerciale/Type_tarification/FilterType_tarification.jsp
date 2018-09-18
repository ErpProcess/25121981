 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${type_trf_id}</label></td>  
   <td width="93%"  >  
   <input id="type_trf_id"   name="type_trf_id"     type="number"    size="10"       maxlength="10"        value="${searchBean.type_trf_id}"    nextElement="type_trf_lib"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${type_trf_lib}</label></td>  
   <td width="93%"  >  
   <input id="type_trf_lib"   name="type_trf_lib"     type="text"    size="100"       maxlength="200"        value="${searchBean.type_trf_lib}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
