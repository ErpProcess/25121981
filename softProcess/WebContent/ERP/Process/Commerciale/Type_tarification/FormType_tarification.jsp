 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   
   <tr>  
   <td width="7%"><label>${type_trf_lib}</label></td>  
   <td width="93%"  >  
   <input id="type_trf_lib" name="type_trf_lib"     type="text"    size="100"       maxlength="200"        value="${detailBean.type_trf_lib}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
