 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${tva_abrv}</label></td>  
   <td width="93%"  >  
   <input id="tva_abrv" name="tva_abrv"     type="text"    size="3"       maxlength="3"        value="${detailBean.tva_abrv}"    nextElement="tva_libelle"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${tva_libelle}</label></td>  
   <td width="93%"  >  
   <input id="tva_libelle" name="tva_libelle"     type="text"    size="50"       maxlength="50"        value="${detailBean.tva_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
