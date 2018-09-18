 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${nat_lieu_id}</label></td>  
   <td width="93%"  >  
   <input id="nat_lieu_id" name="nat_lieu_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.nat_lieu_id}"    nextElement="nat_lieu_libelle"     libre readonly="readonly"         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${nat_lieu_libelle}</label></td>  
   <td width="93%"  >  
   <input id="nat_lieu_libelle" name="nat_lieu_libelle"     type="text"    size="50"       maxlength="50"        value="${detailBean.nat_lieu_libelle}"    nextElement="ordre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ordre}</label></td>  
   <td width="93%"  >  
   <input id="ordre" name="ordre"     type="number"    size="10"       maxlength="10"        value="${detailBean.ordre}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
