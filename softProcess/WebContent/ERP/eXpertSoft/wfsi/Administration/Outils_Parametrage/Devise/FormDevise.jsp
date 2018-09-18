 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${dev_abrv}</label></td>  
   <td width="93%"  >  
   <input id="dev_abrv" name="dev_abrv"     type="text"    size="3"       maxlength="3"        value="${detailBean.dev_abrv}"    nextElement="dev_libelle"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${dev_libelle}</label></td>  
   <td width="93%"  >  
   <input id="dev_libelle" name="dev_libelle"     type="text"    size="50"       maxlength="50"        value="${detailBean.dev_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
