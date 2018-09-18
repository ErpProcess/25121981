 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${carac_id}</label></td>  
   <td width="93%"  >  
   <input id="carac_id" name="carac_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.carac_id}"    nextElement="carac_libelle"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${carac_libelle}</label></td>  
   <td width="93%"  >  
   <input id="carac_libelle" name="carac_libelle"     type="text"    size="100"       maxlength="250"        value="${detailBean.carac_libelle}"    nextElement="btValidx"       required       />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
