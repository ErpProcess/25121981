 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id"   name="vente_id"     type="text"    size="17"       maxlength="17"        value="${searchBean.vente_id}"    nextElement="vente_date"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_date}</label></td>  
   <td width="93%"  >  
   <input id="vente_date"   name="vente_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.vente_date}"    nextElement="etab_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${etab_id}</label></td>  
   <td width="93%"  >  
   <input id="etab_id"   name="etab_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.etab_id}"    nextElement="clt_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_id}</label></td>  
   <td width="93%"  >  
   <input id="clt_id"   name="clt_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.clt_id}"    nextElement="mode_op"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mode_op}</label></td>  
   <td width="93%"  >  
   <input id="mode_op"   name="mode_op"     type="text"    size="131089"       maxlength="131089"        value="${searchBean.mode_op}"    nextElement="depot_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id"   name="depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.depot_id}"    nextElement="usr_confirm"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${usr_confirm}</label></td>  
   <td width="93%"  >  
   <input id="usr_confirm"   name="usr_confirm"     type="text"    size="10"       maxlength="10"        value="${searchBean.usr_confirm}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
