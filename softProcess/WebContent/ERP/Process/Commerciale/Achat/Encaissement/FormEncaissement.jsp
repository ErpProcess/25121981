 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${reg_frs_id}</label></td>  
   <td width="93%"  >  
   <input id="reg_frs_id" name="reg_frs_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.reg_frs_id}"    nextElement="fact_frs_id"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_frs_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_frs_id" name="fact_frs_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.fact_frs_id}"    nextElement="reg_mod"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${reg_mod}</label></td>  
   <td width="93%"  >  
   <input id="reg_mod" name="reg_mod"     type="text"    size="30"       maxlength="30"        value="${detailBean.reg_mod}"    nextElement="reg_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${reg_date}</label></td>  
   <td width="93%"  >  
   <input id="reg_date" name="reg_date"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.reg_date}"    nextElement="reg_nbr_echeance"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${reg_nbr_echeance}</label></td>  
   <td width="93%"  >  
   <input id="reg_nbr_echeance" name="reg_nbr_echeance"     type="text"    size="10"       maxlength="10"        value="${detailBean.reg_nbr_echeance}"    nextElement="montant_facture"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${montant_facture}</label></td>  
   <td width="93%"  >  
   <input id="montant_facture" name="montant_facture"     type="text"    size="17"       maxlength="17"        value="${detailBean.montant_facture}"    nextElement="montant_avance"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${montant_avance}</label></td>  
   <td width="93%"  >  
   <input id="montant_avance" name="montant_avance"     type="text"    size="17"       maxlength="17"        value="${detailBean.montant_avance}"    nextElement="num_piece"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${num_piece}</label></td>  
   <td width="93%"  >  
   <input id="num_piece" name="num_piece"     type="text"    size="30"       maxlength="30"        value="${detailBean.num_piece}"    nextElement="reg_nature"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${reg_nature}</label></td>  
   <td width="93%"  >  
   <input id="reg_nature" name="reg_nature"     type="text"    size="5"       maxlength="5"        value="${detailBean.reg_nature}"    nextElement="reg_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${reg_type}</label></td>  
   <td width="93%"  >  
   <input id="reg_type" name="reg_type"     type="text"    size="10"       maxlength="10"        value="${detailBean.reg_type}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
