<%@ include file="/Aceuil/esProcess.jsp"     %> 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${fct_id}</label></td>  
   <td width="93%"  >  
   <input id="fct_id" name="fct_id"     type="text"    size="20"    value="${detailBean.fct_id}"    nextElement="fct_libelle"    autofocus   required     />  
   </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fct_libelle}</label></td>  
   <td width="93%"  >  
   <input id="fct_libelle" name="fct_libelle"     type="text"    size="20"    value="${detailBean.fct_libelle}"    nextElement="fct_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fct_obs}</label></td>  
   <td width="93%"  >  
   <input id="fct_obs" name="fct_obs"     type="text"    size="20"    value="${detailBean.fct_obs}"    nextElement="fct_action"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fct_action}</label></td>  
   <td width="93%"  >  
   <input id="fct_action" name="fct_action"     type="text"    size="20"    value="${detailBean.fct_action}"    nextElement="fct_icon"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fct_icon}</label></td>  
   <td width="93%"  >  
   <input id="fct_icon" name="fct_icon"     type="text"    size="20"    value="${detailBean.fct_icon}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
