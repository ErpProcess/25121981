 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>code</label></td>  
   <td width="93%"  >  
   <input id="full_name"   name="full_name"     type="text"    size="10"       maxlength="10"        value="${searchBean.full_name}"    nextElement="name"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>Compte</label></td>  
   <td width="93%"  >  
   <input id="compte"   name="compte"     type="text"    size="50"       maxlength="50"        value="${searchBean.compte}"    nextElement=""              />  
  </td>  
   </tr>   

 
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
