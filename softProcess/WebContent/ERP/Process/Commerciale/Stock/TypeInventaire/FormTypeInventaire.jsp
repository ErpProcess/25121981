 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${type_inv_id}</label></td>  
   <td width="93%"  >  
   <input id="type_inv_id" name="type_inv_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.type_inv_id}"    nextElement="type_inv_libelle"     libre       readonly="readonly"  />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${type_inv_libelle}</label></td>  
   <td width="93%"  >  
   <input id="type_inv_libelle" name="type_inv_libelle"     type="text"    size="250"       maxlength="250"        value="${detailBean.type_inv_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
