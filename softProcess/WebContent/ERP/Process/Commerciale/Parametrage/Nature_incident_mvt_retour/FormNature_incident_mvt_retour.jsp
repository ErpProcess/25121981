 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${cause_ret_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="nature_incident_id" name="nature_incident_id"     type="text"    size="10"    libre readonly="readonly"         value="${detailBean.nature_incident_id}"    nextElement="cause_ret_vente_lib"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cause_ret_vente_lib}</label></td>  
   <td width="93%"  >  
   <input id="nature_incident_lib" name="nature_incident_lib"     type="text"    size="50"       maxlength="50"        value="${detailBean.nature_incident_lib}"    nextElement="cause_ret_vente_sens"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cause_ret_vente_sens}</label></td>  
   <td width="93%"  >  
   <input id="nature_incident_sense" name="nature_incident_sense"     type="text"    size="1"       maxlength="1"        value="${detailBean.nature_incident_sense}"    nextElement="cause_ret_vente_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cause_ret_vente_type}</label></td>  
   <td width="93%"  >  
   <input id="cause_ret_vente_type" name="nature_incident_type"     type="text"    size="50"       maxlength="50"        value="${detailBean.nature_incident_type}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
