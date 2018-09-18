 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id" name="vente_id"     type="text"    size="17"       maxlength="17"        value="${detailBean.vente_id}"    nextElement="vente_date"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_date}</label></td>  
   <td width="93%"  >  
   <input id="vente_date" name="vente_date"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.vente_date}"    nextElement="etab_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${etab_id}</label></td>  
   <td width="93%"  >  
   <input id="etab_id" name="etab_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.etab_id}"    nextElement="clt_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_id}</label></td>  
   <td width="93%"  >  
   <input id="clt_id" name="clt_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.clt_id}"    nextElement="mode_op"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mode_op}</label></td>  
   <td width="93%"  >  
   <input id="mode_op" name="mode_op"     type="text"    size="131089"       maxlength="131089"        value="${detailBean.mode_op}"    nextElement="depot_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id" name="depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot_id}"    nextElement="usr_confirm"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${usr_confirm}</label></td>  
   <td width="93%"  >  
   <input id="usr_confirm" name="usr_confirm"     type="text"    size="10"       maxlength="10"        value="${detailBean.usr_confirm}"    nextElement="vente_remise"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_remise}</label></td>  
   <td width="93%"  >  
   <input id="vente_remise" name="vente_remise"     type="text"    size="17"       maxlength="17"        value="${detailBean.vente_remise}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
