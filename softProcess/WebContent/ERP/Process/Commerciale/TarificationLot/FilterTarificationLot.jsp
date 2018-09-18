 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${num_serie}</label></td>  
   <td width="93%"  >  
   <input id="num_serie"   name="pk.lot.pk.num_serie"     type="text"    size="50"       maxlength="50"        value="${searchBean.pk.lot.pk.num_serie}"    nextElement="depot_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id"   name="pk.lot.pk.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.pk.lot.pk.depot.depot_id}"    nextElement="tarif_vente_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${tarif_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="tarif_vente_id"   name="pk.vente.tarif_vente_id"     type="text"    size="25"       maxlength="25"        value="${searchBean.pk.vente.tarif_vente_id}"                 />  
  </td>  
   </tr>   
    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
