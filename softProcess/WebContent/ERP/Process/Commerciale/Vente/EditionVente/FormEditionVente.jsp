 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${edition_id}</label></td>  
   <td width="93%"  >  
   <input id="edition_id" name="edition_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.edition_id}"    nextElement="edition_libelle"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${edition_libelle}</label></td>  
   <td width="93%"  >  
   <input id="edition_libelle" name="edition_libelle"     type="text"    size="50"       maxlength="50"        value="${detailBean.edition_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
