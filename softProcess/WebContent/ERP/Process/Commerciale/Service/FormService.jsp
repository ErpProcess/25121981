 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${ser_id}</label></td>  
   <td width="93%"  >  
   <input id="ser_id" name="ser_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.ser_id}"    nextElement="sitcod"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${sitcod}</label></td>  
   <td width="93%"  >  
   <input id="sitcod" name="bean_sitcod.sitcod"     type="text"    size="10"       maxlength="10"        value="${detailBean.bean_sitcod.data_id}"    nextElement="ser_lib"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ser_lib}</label></td>  
   <td width="93%"  >  
   <input id="ser_lib" name="ser_lib"     type="text"    size="20"       maxlength="100"        value="${detailBean.ser_lib}"    nextElement="ar_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ar_obs}</label></td>  
   <td width="93%"  >  
   <textarea id="ar_obs" name="ar_obs"    rows="5" cols="50"          nextElement="btValidx"              > ${detailBean.ar_obs}</textarea> 
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
