 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${mvt_stock_id}</label></td>  
   <td width="93%"  >  
   <input id="mvt_stock_id" name="mvt_stock_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.mvt_stock_id}"    nextElement="date_mvt_stock"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${date_mvt_stock}</label></td>  
   <td width="93%"  >  
   <input id="date_mvt_stock" name="date_mvt_stock"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.date_mvt_stock}"    nextElement="document_com_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${document_com_id}</label></td>  
   <td width="93%"  >  
   <input id="document_com_id" name="document_com_id"     type="text"    size="25"       maxlength="25"        value="${detailBean.document_com_id}"    nextElement="inv_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${inv_date}</label></td>  
   <td width="93%"  >  
   <input id="inv_date" name="inv_date"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.inv_date}"    nextElement="depot_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id" name="depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot_id}"    nextElement="ar_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ar_id}</label></td>  
   <td width="93%"  >  
   <input id="ar_id" name="ar_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.ar_id}"    nextElement="code_barre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${code_barre}</label></td>  
   <td width="93%"  >  
   <input id="code_barre" name="code_barre"     type="text"    size="250"       maxlength="250"        value="${detailBean.code_barre}"    nextElement="quantite"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${quantite}</label></td>  
   <td width="93%"  >  
   <input id="quantite" name="quantite"     type="text"    size="17"       maxlength="17"        value="${detailBean.quantite}"    nextElement="nature_mvt_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${nature_mvt_id}</label></td>  
   <td width="93%"  >  
   <input id="nature_mvt_id" name="nature_mvt_id"     type="text"    size="5"       maxlength="5"        value="${detailBean.nature_mvt_id}"    nextElement="montant_ht"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${montant_ht}</label></td>  
   <td width="93%"  >  
   <input id="montant_ht" name="montant_ht"     type="text"    size="17"       maxlength="17"        value="${detailBean.montant_ht}"    nextElement="montant_tva"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${montant_tva}</label></td>  
   <td width="93%"  >  
   <input id="montant_tva" name="montant_tva"     type="text"    size="17"       maxlength="17"        value="${detailBean.montant_tva}"    nextElement="etab_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${etab_id}</label></td>  
   <td width="93%"  >  
   <input id="etab_id" name="etab_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.etab_id}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
