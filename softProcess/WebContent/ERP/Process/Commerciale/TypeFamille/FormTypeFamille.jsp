 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${typefam_id}</label></td>  
   <td width="93%"  >  
   <input id="typefam_id" name="typefam_id"     type="text"    size="6"       maxlength="6"        value="${detailBean.typefam_id}"    nextElement="typefam_lib"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${typefam_lib}</label></td>  
   <td width="93%"  >  
   <input id="typefam_lib" name="typefam_lib"     type="text"    size="100"       maxlength="100"        value="${detailBean.typefam_lib}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
