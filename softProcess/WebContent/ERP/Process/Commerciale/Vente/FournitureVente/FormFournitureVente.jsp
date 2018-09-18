 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${frn_ve_id}</label></td>  
   <td width="93%"  >  
   <input id="frn_ve_id" name="frn_ve_id"     type="text"    size="17"       maxlength="17"        value="${detailBean.frn_ve_id}"    nextElement="vente_id"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id" name="vente_id"     type="text"    size="17"       maxlength="17"        value="${detailBean.vente_id}"    nextElement="frn_ve_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${frn_ve_date}</label></td>  
   <td width="93%"  >  
   <input id="frn_ve_date" name="frn_ve_date"     type="datepicker"    size="13"       maxlength="13"        value="${detailBean.frn_ve_date}"    nextElement="frn_ve_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${frn_ve_obs}</label></td>  
   <td width="93%"  >  
   <input id="frn_ve_obs" name="frn_ve_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.frn_ve_obs}"    nextElement="clt_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_id}</label></td>  
   <td width="93%"  >  
   <input id="clt_id" name="client.clt_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.client.clt_id}"    nextElement="depot_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id" name="depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="cmd_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cmd_id}</label></td>  
   <td width="93%"  >  
   <input id="cmd_id" name="commande.cmd_id"     type="text"    size="16"       maxlength="16"        value="${detailBean.commande.cmd_id}"    nextElement="frn_ve_remise"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${frn_ve_remise}</label></td>  
   <td width="93%"  >  
   <input id="frn_ve_remise" name="frn_ve_remise"     type="text"    size="17"       maxlength="17"        value="${detailBean.frn_ve_remise}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
