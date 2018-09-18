<%@    include file="/Aceuil/esProcess.jsp"     %> 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
     <td width="7%"><label>code</label></td>  
     <td width="93%"  >  
       <input id="pack_id" name="pack_id"     type="text" size="20"  value="${searchBean.pack_id}"/>  
     
     </td>  
   </tr> 
   
   <tr>  
     <td width="7%"><label>Libelle</label></td>  
     <td width="93%"  >  
       <input id="pack_libelle" name="pack_libelle"     type="text" size="20"  value="${searchBean.pack_libelle}"/>  
     </td>  
   </tr> 
     
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
 <script> Ext.onReady(function(){   try {	         <c:if test="${not empty dataListAajx}">            Ext.get('RET_GRID').setStyle('display', 'block');      </c:if>           } catch(e){   		}	         });               </script>        
