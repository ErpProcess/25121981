<%@    include file="/Aceuil/esProcess.jsp"     %>
<script type="text/javascript">
$(document).ready(function () { LoadAutoCompletAjax("soc_id","soc_lib","etab_id","listSocioTa"); });
</script>
<ext:body  >
<ext:panel  renderTo="ThePageJsp"             >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  border="0"  id="tblData"    >
<!-- ******************************************************************************************************************************************** -->
      
<!-- ******************************************************************************************************************************************** -->         
         <tr>
             <td width="9%" ><label>Societe
             
             
             </label></td>
	         <td width="33%"> 
             <input id="soc_id"       name="pk_etab.soc_bean.soc_id"       type="text"      size="10"   value="${detailBean.pk_etab.soc_bean.soc_id}"         />
             <input id="soc_lib"      name="pk_etab.soc_bean.soc_lib"  type="text"      size="25"   value="${detailBean.pk_etab.soc_bean.soc_lib}"        />
	         </td>   
             <td width="8%"><label > </label></td>
             <td width="27%">
               
             </td>
             <td width="8%"><label > </label></td>
             <td  width="20%" >
                  
             </td>
         </tr>
<!-- ******************************************************************************************************************************************** -->  


  <tr>
             <td width="9%" ><label>Etab_id
             
             
             </label></td>
	         <td width="33%"> 
             <input id="etab_id"      name="pk_etab.etab_id"  type="text"     libre   readonly="readonly"    value="${detailBean.pk_etab.etab_id}"        />
	         </td>   
           <td width="8%"><label >libelle</label></td>
             <td width="27%">
                 <input id="etab_lib" name="etab_lib"   type="text"  size="35"    value="${detailBean.etab_lib}"    autofocus         required  />
             </td>
             <td width="8%"><label >Ordre</label></td>
             <td  width="20%" >
                 <input id="etab_ordre" name="etab_ordre"   type="number"  min="1" max="10"        value="${detailBean.etab_ordre}"   required   />
             </td>
         </tr>
         
         
         
         
          <tr>
             <td width="9%" ><label>etab_adrs
             </label></td>
	         <td width="33%"> 
             <input id="etab_adrs"      name="etab_adrs"  type="text"      size="30"   value="${detailBean.etab_adrs}"        />
	         </td>   
           <td width="8%"><label >etab_tel</label></td>
             <td width="27%">
                 <input id="etab_tel" name="etab_tel"   type="number"     value="${detailBean.etab_tel}"              required  />
             </td>
             <td width="8%"><label >etab_fax</label></td>
             <td  width="20%" >
                 <input id="etab_fax" name="etab_fax"   type="number"  min="1"   max="20"        value="${detailBean.etab_fax}"   required   />
             </td>
         </tr>
         
         
          <tr>
             <td width="9%" ><label>etab_email
             </label></td>
	         <td width="33%"> 
             <input id="etab_email"      name="etab_email"  type="text"      size="30"   value="${detailBean.etab_email}"        />
	         </td>   
           <td width="8%"><label >etab_code_comptable</label></td>
             <td width="27%">
                 <input id="etab_code_comptable" name="etab_code_comptable"   type="text"  size="35"    value="${detailBean.etab_code_comptable}"    autofocus         required  />
             </td>
             <td width="8%"><label > </label></td>
             <td  width="20%" >
             </td>
         </tr>
         
         
         
         
          <tr>
             <td width="9%" ><label>Devise</label></td>
	         <td width="33%" > 
             <script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",false); })</script>
		                 <select     id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select>
	         </td>   
             <td width="8%"><label >formatage</label></td>
             <td width="27%">
                 <input id="formatage" name="formatage"   type="text"    value="${detailBean.formatage}"                 />
             </td>
             <td width="8%"><label > type etab </label></td>
             <td  width="20%" >
              <script  >$(function() {loadSelectAjax("typeX","list_etab_type","data_id","data_libelle","${detailBean.type.data_id}",true); })</script>
		                 <select     id="typeX"  name="type.data_id"   style="width: 180px;"  ></select>
             </td>
         </tr>
      
         
                 
          </table>
  	  </ext:panel>
</ext:body>





 









