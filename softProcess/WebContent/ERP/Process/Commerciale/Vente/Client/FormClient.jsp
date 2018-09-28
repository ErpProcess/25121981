 <%@include file="/Aceuil/esProcess.jsp" %>  
  <script type="text/javascript">
   $(document).ready(function () { 
   LoadAutoCompletAjax("type_trf_id","type_trf_lib","btValidx","list_type_tarif_client");   
   LoadAutoCompletAjax("cptbanribrib","cptbanribrs","btValidx","list_cpt_bank_reg_fact");
   });
   </script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >


<tr>  
   <td width="7%"><label>${clt_id}</label></td>  
   <td width="93%"  >  
   <input id="clt_id" name="clt_id"     type="text"    size="6"       maxlength="60"        value="${detailBean.clt_id}"    nextElement="clt_lib"    autofocus   required     />  
  </td>  
   </tr> 
   
    
   <tr>  
   <td width="7%"><label>${clt_lib}</label></td>  
   <td width="93%"  >  
   <input id="clt_lib" name="clt_lib"     type="text"    size="100"       maxlength="500"        value="${detailBean.clt_lib}"    nextElement="clt_adr"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_adr}</label></td>  
   <td width="93%"  >  
   <input id="clt_adr" name="clt_adr"     type="text"    size="100"       maxlength="500"        value="${detailBean.clt_adr}"    nextElement="clt_tel"              /> 
  </td>  
   </tr>   
   
    <tr>  
   <td width="7%"><label>Region</label></td>  
   <td width="93%"  >  
   <input id="clt_region" name="clt_region"     type="text"    size="40"       maxlength="500"        value="${detailBean.clt_region}"         /><br> 
  </td>  
   </tr>  
   
    <tr>  
   <td width="7%"><label>Pays</label></td>  
   <td width="93%"  >  
    <input id="clt_pays" name="clt_pays"     type="text"    size="40"       maxlength="500"        value="${detailBean.clt_pays}"         /> 
  </td>  
   </tr>  
   
    <tr>
		        <td ><label>Exonérer</label></td>
		        <td ><input  isboolean  id="clt_exonorer"              name="clt_exonorer"     type="checkbox"        value="${detailBean.clt_exonorer}"       />
		        </td>
		      </tr>
		      
		      
     <tr>  
   <td width="7%"><label>Methode Export</label></td>  
   <td width="93%"  >  
  
   <input id="clt_method_export"  name="clt_method_export"      type="text"      size="25"               value="${detailBean.clt_method_export}"                 /> 
            
  </td>  
   </tr>
   
   
      <tr>  
   <td width="7%"><label>Tarification</label></td>  
   <td width="93%"  >  
  
   <input id="type_trf_id"  name="typ_trfBean.type_trf_id"      type="text"      size="8"               value="${detailBean.typ_trfBean.type_trf_id}"                 /> 
   <input id="type_trf_lib" name="typ_trfBean.type_trf_lib"     type="text"    size="25"                value="${detailBean.typ_trfBean.type_trf_lib}"                 />   
  </td>  
   </tr>
   
  <tr>  
   <td width="7%"><label>Compte.Bank.Fact</label></td>  
   <td width="93%" height="40px" >  
   
   <input id="cptbanribrib"  name="compte.cptbanribrib"      type="text"      size="15"            value="${detailBean.compte.cptbanribrib}"        required         /> 
   <input id="cptbanribrs"   name="compte.cptbanribrs"       type="text"    size="25"                value="${detailBean.compte.cptbanribrs}"         required        />   
  </td>  
   </tr> 
   
   
   <tr>  
   <td width="7%"><label>${clt_tel}</label></td>  
   <td width="93%"  >  
   <input id="clt_tel" name="clt_tel"     type="text"    size="20"       maxlength="500"        value="${detailBean.clt_tel}"    nextElement="clt_fax"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_fax}</label></td>  
   <td width="93%"  >  
   <input id="clt_fax" name="clt_fax"     type="text"    size="20"       maxlength="20"        value="${detailBean.clt_fax}"    nextElement="clt_email"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_email}</label></td>  
   <td width="93%"  >  
   <input id="clt_email" name="clt_email"     type="text"    size="20"       maxlength="20"        value="${detailBean.clt_email}"    nextElement="clt_numcpt"              />  
  </td>  
   </tr>   
   <tr style="display: none;"  >  
   <td width="7%"  ><label>${clt_numcpt}</label></td>  
   <td width="93%"  >  
   <input id="clt_numcpt" name="clt_numcpt"     type="text"    size="20"       maxlength="20"        value="${detailBean.clt_numcpt}"    nextElement="clt_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_obs}</label></td>  
   <td width="93%"  >  
   <input id="clt_obs" name="clt_obs"     type="text"    size="100"       maxlength="100"        value="${detailBean.clt_obs}"    nextElement="clt_bank"              />  
  </td>  
   </tr>   
   <tr style="display: none;"  >  
   <td width="7%"     ><label>${clt_bank}</label></td>  
   <td width="93%"  >  
   <input id="clt_bank" name="clt_bank"     type="text"    size="6"       maxlength="6"        value="${detailBean.clt_bank}"    nextElement="clt_typ"              />  
  </td>  
   </tr>   
   <tr style="display: none;"  >  
   <td width="7%"  ><label>${clt_typ}</label></td>  
   <td width="93%"  >  
   <input id="clt_typ" name="clt_typ"     type="text"    size="1"              value="${detailBean.clt_typ}"    nextElement="btValidx"              />  
  </td>  
   </tr>
  
   
  
   
    
   
 </table>   
</ext:panel>
</ext:body>
