 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${avoir_frs_id}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_id" name="avoir_frs_id"     type="text"    size="25"       maxlength="25"        value="${detailBean.avoir_frs_id}"    nextElement="avoir_frs_date"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${avoir_frs_date}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_date" name="avoir_frs_date"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.avoir_frs_date}"    nextElement="avoir_frs_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${avoir_frs_obs}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_obs" name="avoir_frs_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.avoir_frs_obs}"    nextElement="avoir_frs_libelle"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${avoir_frs_libelle}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_libelle" name="avoir_frs_libelle"     type="text"    size="30"       maxlength="30"        value="${detailBean.avoir_frs_libelle}"    nextElement="fact_frs_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_frs_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_frs_id" name="fact_frs_id"     type="text"    size="25"       maxlength="25"        value="${detailBean.fact_frs_id}"    nextElement="type_avoir_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${type_avoir_id}</label></td>  
   <td width="93%"  >  
   <input id="type_avoir_id" name="type_avoir_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.type_avoir_id}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
