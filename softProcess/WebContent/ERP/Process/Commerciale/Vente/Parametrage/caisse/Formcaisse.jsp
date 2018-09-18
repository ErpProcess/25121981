 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${caisse_id}</label></td>  
   <td width="93%"  >  
   <input id="caisse_id" name="caisse_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.caisse_id}"    nextElement="caisse_libelle"    autofocus       libre  readonly="readonly"   />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${caisse_libelle}</label></td>  
   <td width="93%"  >  
   <input id="caisse_libelle" name="caisse_libelle"     type="text"    size="100"       maxlength="100"        value="${detailBean.caisse_libelle}"    nextElement="caisse_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${caisse_type}</label></td>  
   <td width="93%"  >  
   <input id="caisse_type" name="caisse_type"     type="text"    size="10"       maxlength="10"        value="${detailBean.caisse_type}"    nextElement="caisse_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${caisse_obs}</label></td>  
   <td width="93%"  >  
   <input id="caisse_obs" name="caisse_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.caisse_obs}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
