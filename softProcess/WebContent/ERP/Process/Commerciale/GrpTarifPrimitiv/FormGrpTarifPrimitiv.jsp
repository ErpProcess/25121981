 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${grp_prim_trf_id}</label></td>  
   <td width="93%"  >  
   <input id="grp_prim_trf_id" name="grp_prim_trf_id"  libre  readonly="readonly"   type="text"    size="10"       maxlength="10"        value="${detailBean.grp_prim_trf_id}"    nextElement="grp_trf_lib"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${grp_trf_lib}</label></td>  
   <td width="93%"  >  
   <input id="grp_trf_lib" name="grp_trf_lib"     type="text"    size="100"       maxlength="200"        value="${detailBean.grp_trf_lib}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
