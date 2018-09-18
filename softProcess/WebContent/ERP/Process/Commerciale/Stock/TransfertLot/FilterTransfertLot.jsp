 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
 
   
   
     
		   
		   
   <tr>  
     <td width="7%"><label>${transfert_id}</label></td>  
     <td width="93%"  >  
     <input id="transfert_id"   name="pk.transfert_id"     type="text"    size="25"       maxlength="25"        value="${searchBean.pk.transfert_id}"    nextElement="num_serie"    autofocus         />  
     </td>  
   </tr>   
   
    
   
   
   
   
  
  
		   
		   
		   
   <tr>  
   <td width="7%"><label>${num_serie}</label></td>  
   <td width="93%"  >  
   <input id="num_serie"   name="pk.lot.pk.num_serie"     type="text"    size="25"       maxlength="25"        value="${searchBean.pk.lot.pk.num_serie}"    nextElement="depot_id_emet"              />  
  </td>  
  
   <tr>  
   <td width="7%"><label>${date_transfert}</label></td>  
   <td width="93%"  >  
    <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_transfert}"   var="searchat_date"/>
   <input id="date_transfert"   name="date_transfert"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="quantite_transfert"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${quantite_transfert}</label></td>  
   <td width="93%"  >  
   <input id="quantite_transfert"   name="quantite_transfert"     type="text"    size="17"       maxlength="17"        value="${searchBean.quantite_transfert}"    nextElement="etat_de_transfert"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${etat_de_transfert}</label></td>  
   <td width="93%"  >  
   <input id="etat_de_transfert"   name="etat_de_transfert"     type="text"    size="15"       maxlength="15"        value="${searchBean.etat_de_transfert}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
