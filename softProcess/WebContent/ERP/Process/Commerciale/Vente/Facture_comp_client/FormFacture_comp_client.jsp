 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${fact_comp_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_comp_id" name="fact_comp_id"     type="text"    size="25"       maxlength="25"        value="${detailBean.fact_comp_id}"    nextElement="fact_comp_obs"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_comp_obs}</label></td>  
   <td width="93%"  >  
   <input id="fact_comp_obs" name="fact_comp_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.fact_comp_obs}"    nextElement="fact_comp_libelle"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_comp_libelle}</label></td>  
   <td width="93%"  >  
   <input id="fact_comp_libelle" name="fact_comp_libelle"     type="text"    size="30"       maxlength="30"        value="${detailBean.fact_comp_libelle}"    nextElement="fact_clt_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_clt_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_clt_id" name="fact_clt_id"     type="text"    size="25"       maxlength="25"        value="${detailBean.fact_clt_id}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
