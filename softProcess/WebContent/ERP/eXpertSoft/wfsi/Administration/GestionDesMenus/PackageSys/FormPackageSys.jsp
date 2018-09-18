<%@    include file="/Aceuil/esProcess.jsp"     %>
<ext:body>
  <ext:panel  renderTo="ThePageJsp"        bodyStyle="background: none;"        border="false"               >
    <table    class="tableStyleContent"    cellpadding="5" cellspacing="10"     border="0"  id="tblData"     >
   <tr>  
     <td width="7%"><label>${pack_libelle}</label></td>  
     <td width="93%"  >  
       <input id="pack_libelle" name="pack_libelle"     type="text" size="20"   nextElement="pack_obs" value="${detailBean.pack_libelle}"  autofocus   required   />  
     </td>  
   </tr> 
    <tr>  
     <td width="7%"><label>${pack_obs}</label></td>  
     <td width="93%"  >  
       <input id="pack_obs" name="pack_obs"     type="text" size="20"          nextElement="btValidx"   value="${detailBean.pack_obs}"/>  
     </td>  
   </tr>    
    <tr>  
     <td width="7%"><label>${pack_ordre}</label></td>  
     <td width="93%"  >  
       <input id="pack_ordre" name="pack_ordre"     type="number" size="20"          nextElement="btValidx"   value="${detailBean.pack_ordre}"/>  
     </td>  
   </tr>     
 </table>   
</ext:panel>
</ext:body>