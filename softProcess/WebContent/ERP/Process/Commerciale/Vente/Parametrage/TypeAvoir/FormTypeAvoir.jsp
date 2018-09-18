 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${type_avoir_id}</label></td>  
   <td width="93%"  >  
   <input id="type_avoir_id" name="type_avoir_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.type_avoir_id}"    nextElement="type_avoir_lib"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${type_avoir_lib}</label></td>  
   <td width="93%"  >  
   <input id="type_avoir_lib" name="type_avoir_lib"     type="text"    size="50"       maxlength="50"        value="${detailBean.type_avoir_lib}"    nextElement="type_avoir_res"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${type_avoir_res}</label></td>  
   <td width="93%"  >  
   <input id="type_avoir_res" name="type_avoir_res"     type="text"    size="10"       maxlength="10"        value="${detailBean.type_avoir_res}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
