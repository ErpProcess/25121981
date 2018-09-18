 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${trans_id}</label></td>  
   <td width="93%"  >  
   <input id="trans_id"   name="trans_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.trans_id}"    nextElement="trans_libelle"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${trans_libelle}</label></td>  
   <td width="93%"  >  
   <input id="trans_libelle"   name="trans_libelle"     type="text"    size="50"       maxlength="50"        value="${searchBean.trans_libelle}"    nextElement="trans_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${trans_type}</label></td>  
   <td width="93%"  >  
   <input id="trans_type"   name="trans_type"     type="text"    size="50"       maxlength="50"        value="${searchBean.trans_type}"    nextElement="trans_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${trans_obs}</label></td>  
   <td width="93%"  >  
   <input id="trans_obs"   name="trans_obs"     type="text"    size="50"       maxlength="50"        value="${searchBean.trans_obs}"    nextElement="time_cre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${time_cre}</label></td>  
   <td width="93%"  >  
   <input id="time_cre"   name="time_cre"     type="text"    size="15"       maxlength="15"        value="${searchBean.time_cre}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
