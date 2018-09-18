 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${unite_abrv}</label></td>  
   <td width="93%"  >  
   <input id="unite_abrv" name="unite_abrv"     type="text"    size="10"       maxlength="3"        value="${detailBean.unite_abrv}"    nextElement="unite_valeur"    autofocus   required     />  
  </td>  
  
   </tr> 
   
    <tr>  
	   <td width="7%"><label>${unite_lib}</label></td>  
	   <td width="93%"  >  
	   <input id="unite_lib" name="unite_lib"     type="text"    size="10"       maxlength="3"        value="${detailBean.unite_lib}"    nextElement="unite_valeur"        required     />  
	  </td>  
   </tr>  
   
   <tr>  
      <td width="7%"><label>${unite_valeur}</label></td>  
      <td width="93%"  >  
      <table>
	   <tr> 
	   
	     <td  >
	     <input id="unite_valeur" name="unite_valeur"     type="text"    size="10"       maxlength="50"  value="${detailBean.unite_valeur}"   
	      nextElement="unite_coef"    required          /> 
	      </td> 
	     <td ><label>${unite_coef}</label></td>  
	     <td >  
	     <input id="unite_coef" name="unite_coef"     type="text"    size="10"       maxlength="50"        value="${detailBean.unite_coef}"              />  
	     </td>  
	   </tr>
      </table>
      
      </td>  
   </tr>
    
 </table>   
</ext:panel>
</ext:body>
