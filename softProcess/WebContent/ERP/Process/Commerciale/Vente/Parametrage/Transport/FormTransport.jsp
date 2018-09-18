 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${trans_id}</label></td>  
   <td width="93%"  >  
   <input id="trans_id" name="trans_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.trans_id}"    nextElement="trans_libelle"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${trans_libelle}</label></td>  
   <td width="93%"  >  
   <input id="trans_libelle" name="trans_libelle"     type="text"    size="50"       maxlength="50"        value="${detailBean.trans_libelle}"    nextElement="trans_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${trans_type}</label></td>  
   <td width="93%"  >  
   <input id="trans_type" name="trans_type"     type="text"    size="50"       maxlength="50"        value="${detailBean.trans_type}"    nextElement="trans_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${trans_obs}</label></td>  
   <td width="93%"  >  
   <input id="trans_obs" name="trans_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.trans_obs}"    nextElement="time_cre"              />  
  </td>  
   </tr>   
      
 </table>   
</ext:panel>
</ext:body>
