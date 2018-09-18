 <%@include file="/Aceuil/esProcess.jsp" %>  
  <script type="text/javascript">
 $(document).ready(function(){ 
   loadSelectAjax("grp_prim_trf_id","listGrpTarificationPrtvArticle","grp_prim_trf_id","grp_trf_lib","${detailBean.groupe.grp_prim_trf_id}",false);
    });
</script>
  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table width="87%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
   <tr>  
   <td width="9%"><label>${frs_id}</label></td>  
   <td width="67%"  >  
   <input id="frs_id" name="frs_id"     type="text"    size="6"       maxlength="6"        value="${detailBean.frs_id}"    nextElement="frsref"    autofocus   required     />  </td>  
   <td width="15%"  ><label>${frsech}</label></td>
   <td width="9%"  ><input id="frsech" name="frsech"     type="text"    size="4"       maxlength="4"        value="${detailBean.frsech}"    nextelement="frsobs"></td>
   </tr>   
   <tr>  
   <td width="9%"><label>${frsref}</label></td>  
   <td width="67%"  >  
   <input id="frsref" name="frsref"     type="text"    size="50"       maxlength="100"        value="${detailBean.frsref}"    nextElement="frsrue"              />  </td>  
   <td width="15%"  ><label>${frsobs}</label></td>
   <td width="9%"  ><input id="frsobs" name="frsobs"     type="text"    size="50"       maxlength="250"        value="${detailBean.frsobs}"    nextelement="bancod"></td>
   </tr>   
   <tr>  
   <td width="9%"><label>${frsrue}</label></td>  
   <td width="67%"  >  
   <input id="frsadr" name="frsadr"     type="text"    size="50"       maxlength="100"        value="${detailBean.frsadr}"    nextElement="frstel"              />  </td>  
   <td width="15%"  ><label>${bancod}</label></td>
   <td width="9%"  ><input id="bancod" name="bancod"     type="text"    size="6"       maxlength="6"        value="${detailBean.bancod}"    nextelement="frstyp"></td>
   </tr>   
   <tr>  
   <td width="9%"><label>${frstel}</label></td>  
   <td width="67%"  >  
   <input id="frstel" name="frstel"     type="text"    size="20"       maxlength="20"        value="${detailBean.frstel}"    nextElement="frsfax"              />  </td>  
   
   
        <td   width="17%" ><label>groupe</label></td>  
   <td    width="83%"  >
   <select   id="grp_prim_trf_id" name="groupe.grp_prim_trf_id"        required      libre              ></select>  
  </td>   
         
         
   </tr>
      
   <tr>  
   <td width="9%"><label>${frsfax}</label></td>  
   <td width="67%"  >  
   <input id="frsfax" name="frsfax"     type="text"    size="20"       maxlength="20"        value="${detailBean.frsfax}"    nextElement="frsemail"              />  </td>  
   <td width="15%"  ><label>${frsemail}</label></td>
   <td width="9%"  ><input id="frsemail" name="frsemail"     type="text"    size="20"       maxlength="20"        value="${detailBean.frsemail}"    nextelement="frsech"></td>
   </tr>   
 </table>   
</ext:panel>
</ext:body>
