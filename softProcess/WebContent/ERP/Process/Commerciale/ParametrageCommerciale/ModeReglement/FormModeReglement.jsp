 <%@include file="/Aceuil/esProcess.jsp" %>  
 
 


  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${mod_id}</label></td>  
   <td width="93%"  >  
   <input id="mod_id" name="mod_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.mod_id}"    nextElement="mod_libelle"    autofocus    libre  readonly="readonly"     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mod_libelle}</label></td>  
   <td width="93%"  >  
   <input id="mod_libelle" name="mod_libelle"     type="text"    size="100"       maxlength="200"        value="${detailBean.mod_libelle}"    nextElement="mod_ordre"              />  
  </td>  
   </tr> 
     
   <tr>  
   <td width="7%"><label>${mod_ordre}</label></td>  
   <td width="93%"  >  
   <input id="mod_ordre" name="mod_ordre"     type="text"    size="10"       maxlength="10"        value="${detailBean.mod_ordre}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
  
    
 </table>   
</ext:panel>
</ext:body>
