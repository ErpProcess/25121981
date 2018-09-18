<%@    include file="/Aceuil/esProcess.jsp"     %>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr style="display: none;">  
   <td width="7%"><label>${prf_id}</label></td>  
   <td width="93%"  >  
   <input id="prf_id" name="prf_id"     type="hidden"    size="20"    value="${detailBean.prf_id}"    nextElement="prf_libelle"      readonly="readonly"        />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${prf_libelle}</label></td>  
   <td width="93%"  >  
   <input id="prf_libelle" name="prf_libelle"     type="text"    size="20"    value="${detailBean.prf_libelle}"    nextElement="btValidx"       autofocus  required     />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
