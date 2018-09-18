 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${date_stock}</label></td>  
   <td width="93%"  >  
   <input id="date_stock" name="date_stock"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.date_stock}"    nextElement="depot_id"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id" name="depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot_id}"    nextElement="num_lot"              />  
  </td>  
   </tr>   
    
   <tr>  
   <td width="7%"><label>${quantite_init}</label></td>  
   <td width="93%"  >  
   <input id="quantite_init" name="quantite_init"     type="text"    size="17"       maxlength="17"        value="${detailBean.quantite_init}"    nextElement="quantite_expire"              />  
  </td>  
   </tr>   
  
   <tr>  
   <td width="7%"><label>${solde_stock}</label></td>  
   <td width="93%"  >  
   <input id="solde_stock" name="solde_stock"     type="text"    size="17"       maxlength="17"        value="${detailBean.solde_stock}"    nextElement="prix_unit_achat"              />  
  </td>  
   </tr>   
  
  
 
 
 </table>   
</ext:panel>
</ext:body>
