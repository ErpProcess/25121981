 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${cptbanribrs}</label></td>  
   <td width="93%"  >  
   <input id="cptbanribrib" name="cptbanribrib"     type="hidden"    size="40"       maxlength="55"     
      value="${detailBean.cptbanribrib}"    nextElement="cptbanribrs"    autofocus         />  
      
    
      
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cptbanribrib} </label></td>  
   <td width="93%"  >  
   <input id="cptbanribrs" name="cptbanribrs"     type="text"    size="100"       maxlength="100"        value="${detailBean.cptbanribrs}"    nextElement="cptbanadr"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cptbanadr}</label></td>  
   <td width="93%"  >  
   <input id="cptbanadr" name="cptbanadr"     type="text"    size="100"       maxlength="100"        value="${detailBean.cptbanadr}"    nextElement="bancod"              />  
  </td>  
   </tr>   
   
   
    <tr>  
   <td width="7%"><label>${sitcod}</label></td>  
   <td width="93%"  >  
  <script  >$(function() { 
		         loadSelectAjax("etatx","listUsr_etat","data_id","data_libelle","${ean_sitcod.data_id}",false); 
		          })</script>
		           <select  id="etatx"  name="bean_sitcod.data_id"       ></select>    
  </td>  
   </tr>   
   
   
   
		           
		           
		           
   <tr ${displayNone}  >  
   <td width="7%"><label>${bancod}</label></td>  
   <td width="93%"  >  
   <input id="bancod" name="bancod"     type="hidden"    size="6"       maxlength="6"        value="${detailBean.bancod}"    nextElement="cptbanjoucod"              />  
  </td>  
   </tr>   
   <tr  ${displayNone} >  
   <td width="7%"><label>${cptbanjoucod}</label></td>  
   <td width="93%"  >  
   <input id="cptbanjoucod" name="cptbanjoucod"     type="hidden"    size="8"       maxlength="8"        value="${detailBean.cptbanjoucod}"    nextElement="cptbancptcom"              />  
  </td>  
   </tr>   
   <tr  ${displayNone}  >  
   <td width="7%"><label>${cptbancptcom}</label></td>  
   <td width="93%"  >  
   <input id="cptbancptcom" name="cptbancptcom"     type="hidden"    size="8"       maxlength="8"        value="${detailBean.cptbancptcom}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
