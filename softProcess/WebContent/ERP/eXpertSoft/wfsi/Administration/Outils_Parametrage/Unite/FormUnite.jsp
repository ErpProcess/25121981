 <%@include file="/Aceuil/esProcess.jsp" %>  
 <script type="text/javascript">
 $(document).ready(function(){
     LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","unite_coef","list_articleWithUnit","400","500");
   });
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${unite_abrv}</label></td>  
   <td width="93%"  >  
   <input id="unite_abrv" name="unite_abrv"     type="text"    size="50"       maxlength="100"        value="${detailBean.unite_abrv}"    nextElement="unite_valeur"    autofocus   required     />  
  </td>  
  
   </tr> 
   
    <tr>  
	   <td width="7%"><label>${unite_lib}</label></td>  
	   <td width="93%"  >  
	   <input id="unite_lib" name="unite_lib"     type="text"    size="50"       maxlength="100"        value="${detailBean.unite_lib}"    nextElement="unite_valeur"        required     />  
	  </td>  
   </tr>  
   
   <tr>  
      <td width="7%"><label>Operation</label></td>  
      <td width="93%"  >  
      <table>
	   <tr> 
	   
	     <td  >
	     <input id="opreration" name="opreration"     type="text"    size="5"       maxlength="1"  value="${detailBean.opreration}"   
	      nextElement="unite_coef"              /> 
	      </td> 
	     <td ><label>${unite_coef}</label></td>  
	     <td >  
	     <input id="unite_coef" name="unite_coef"     type="text"    size="10"       maxlength="150"        value="${detailBean.unite_coef}"              />  
	     </td>  
	   </tr>
      </table>
      
      </td>  
   </tr>
   
   <tr> 
	<td  ><label>référence</label></td>  
	<td   >  
     <input id="pk.code_barre" name="code_barre"     type="text"    size="10"       maxlength="100"                  />
     <input id="designation_libelle" name="designation_libelle"     type="text"    size="55"       maxlength="100"                    />
	</td>  
 </tr>  
 
    
 </table>   
</ext:panel>
</ext:body>
