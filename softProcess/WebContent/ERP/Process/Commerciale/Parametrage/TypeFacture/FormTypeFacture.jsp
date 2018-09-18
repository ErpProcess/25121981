 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${type_fact_id}</label></td>  
   <td width="93%"  >  
   <input id="type_fact_id" name="type_fact_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.type_fact_id}"    nextElement="type_fact_lib"    autofocus   required     />  
  </td>  
   </tr>   
   
   
   <tr>  
   <td width="7%"><label>${type_fact_lib}</label></td>  
   <td width="93%"  >  
   <input id="type_fact_lib" name="type_fact_lib"     type="text"    size="10"       maxlength="10"        value="${detailBean.type_fact_lib}"     required    />  
  </td>  
   </tr>   
   
   
 </table>   
</ext:panel>
</ext:body>
