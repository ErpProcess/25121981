 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >
 
 
  <tr>  
   <td width="7%"><label>${date_achat}</label></td>  
   <td width="93%"  >  
   <input id="date_achat" name="date_achat"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.date_achat}"    nextElement="etab_id"              />  
  </td>  
   </tr> 
     
   <tr>  
   <td width="7%"><label>${libelle_achat}</label></td>  
   <td width="93%"  >  
   <input id="libelle_achat" name="libelle_achat"     type="text"    size="150"       maxlength="250"        value="${detailBean.libelle_achat}"    nextElement="prix_achat"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${prix_achat}</label></td>  
   <td width="93%"  >  
   <input id="prix_achat" name="prix_achat"     type="text"   class="money2"     size="17"       maxlength="17"        value="${detailBean.prix_achat}"    nextElement="observation"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${observation}</label></td>  
   <td width="93%"  >  
   <input id="observation" name="observation"     type="text"    size="150"       maxlength="300"        value="${detailBean.observation}"    nextElement="date_achat"              />  
  </td>  
   </tr>   
    
     
 </table>   
</ext:panel>
</ext:body>
