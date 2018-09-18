 <%@include file="/Aceuil/esProcess.jsp" %>  
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   
    <tr>  
   <td width="7%"   ><label>${date_achat}</label></td>  
   <td width="93%"  > 
   
   <input id="date_achat"   name="date_achat"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_achat}"    nextElement="etab_id"              />  
  </td>  
   </tr>   
   
   <tr>  
   <td width="7%"><label>${libelle_achat}</label></td>  
   <td width="93%"  >  
   <input id="libelle_achat"   name="libelle_achat"     type="text"    size="250"       maxlength="250"        value="${searchBean.libelle_achat}"    nextElement="prix_achat"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${prix_achat}</label></td>  
   <td width="93%"  >  
   <input id="prix_achat"   name="prix_achat"     type="text"    size="17"       maxlength="17"        value="${searchBean.prix_achat}"    nextElement="date_achat"              />  
  </td>  
   </tr>   
  
    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
