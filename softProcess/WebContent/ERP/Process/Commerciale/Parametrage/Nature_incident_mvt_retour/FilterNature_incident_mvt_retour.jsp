 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${cause_ret_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="cause_ret_vente_id"   name="nature_incident_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.nature_incident_id}"    nextElement="cause_ret_vente_lib"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cause_ret_vente_lib}</label></td>  
   <td width="93%"  >  
   <input id="cause_ret_vente_lib"   name="nature_incident_lib"     type="text"    size="50"       maxlength="50"        value="${searchBean.nature_incident_lib}"    nextElement="cause_ret_vente_sens"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cause_ret_vente_sens}</label></td>  
   <td width="93%"  >  
   <input id="cause_ret_vente_sens"   name="nature_incident_sense"     type="text"    size="1"       maxlength="1"        value="${searchBean.nature_incident_sense}"    nextElement="cause_ret_vente_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cause_ret_vente_type}</label></td>  
   <td width="93%"  >  
   <input id="cause_ret_vente_type"   name="nature_incident_type"     type="text"    size="50"       maxlength="50"        value="${searchBean.nature_incident_type}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
