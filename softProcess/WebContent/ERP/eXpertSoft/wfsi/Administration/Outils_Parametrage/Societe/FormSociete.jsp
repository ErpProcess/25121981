<%@    include file="/Aceuil/esProcess.jsp"     %>
<ext:body  >
<ext:panel  renderTo="ThePageJsp"             >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  border="0"  id="tblData"    >
<!-- ******************************************************************************************************************************************** -->
          <tr>
             <td width="100%" colspan="6"    align="center" >
                &nbsp;<span id="errmsg"  style="color: red;font-weight: bold;"></span>
		     </td>
         </tr>
<!-- ******************************************************************************************************************************************** -->         
         <tr>
             <td width="9%" ><label>code</label></td>
	         <td width="33%" > 
             <input id="soc_id"  name="soc_id"    type="text"     size="10"   value="${detailBean.soc_id}"    libre readonly="readonly"   />
	         </td>   
             <td width="8%"><label >libelle</label></td>
             <td width="27%">
                 <input id="soc_lib" name="soc_lib"   type="text"  size="35"    value="${detailBean.soc_lib}"    autofocus         required  />
             </td>
             <td width="8%"><label >Ordre</label></td>
             <td  width="20%" >
                 <input id="soc_ordre" name="soc_ordre"   type="number"  min="1" max="10"        value="${detailBean.soc_ordre}"   required   />
             </td>
         </tr>
<!-- ******************************************************************************************************************************************** -->


<!-- ******************************************************************************************************************************************** -->         
         <tr>
             <td width="9%" ><label>lib_arab</label></td>
	         <td width="33%" > 
             <input id="lib_arab"  name="lib_arab"    type="text"     size="10"   value="${detailBean.lib_arab}"       required  />
	         </td>   
             <td width="8%"><label >adresse</label></td>
             <td width="27%">
                 <input id="adresse" name="adresse"   type="text"  size="35"    value="${detailBean.adresse}"              required  />
             </td>
             <td width="8%"><label >Registre_commerce</label></td>
             <td  width="20%" >
                 <input id="registre_commerce" name="registre_commerce"   type="text"   value="${detailBean.registre_commerce}"       />
             </td>
         </tr>
<!-- ******************************************************************************************************************************************** -->

 
	<!-- ******************************************************************************************************************************************** -->         
         <tr>
             <td width="9%" ><label>matricule_fiscale</label></td>
	         <td width="33%" > 
             <input id="matricule_fiscale"  name="matricule_fiscale"    type="text"   size="10"   value="${detailBean.matricule_fiscale}"         />
	         </td>   
             <td width="8%"><label >telephone</label></td>
             <td width="27%">
                 <input id="telephone" name="telephone"   type="text"  size="35"    value="${detailBean.telephone}"              required  />
             </td>
             <td width="8%"><label >fax</label></td>
             <td  width="20%" >
                 <input id="fax" name="fax"   type="text"  min="1" max="10"        value="${detailBean.fax}"   required   />
             </td>
         </tr>
<!-- ******************************************************************************************************************************************** -->
 
	 
  
	<!-- ******************************************************************************************************************************************** -->         
         <tr>
             <td width="9%" ><label>capital</label></td>
	         <td width="33%" > 
             <input id="capital"  name="capital"    type="number"      size="10"   value="${detailBean.capital}"         />
	         </td>   
             <td width="8%"><label >montant_timbre_fiscal</label></td>
             <td width="27%">
                 <input id="montant_timbre_fiscal" name="montant_timbre_fiscal"   type="montant3"    value="${detailBean.montant_timbre_fiscal}"                 />
             </td>
             <td width="8%"><label >compte_timbre_f</label></td>
             <td  width="20%" >
                 <input id="compte_timbre_f" name="compte_timbre_f"   type="text"  min="1" max="10"        value="${detailBean.compte_timbre_f}"       />
             </td>
         </tr>
<!-- ******************************************************************************************************************************************** -->
  
 
   
   
           <tr>
             <td width="9%" ><label>Devise achat </label></td>
	         <td width="33%" > 
             <script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",true); })</script>
		                 <select   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select>
	         </td>   
             <td width="8%"><label >Devise vente </label></td>
             <td width="27%"> 
             <script  >$(function() {loadSelectAjax("devVente","list_devise","dev_id","dev_libelle","${detailBean.deviseVente.dev_id}",true); })</script>
		                 <select  required   id="devVente"  name="deviseVente.dev_id"   style="width: 180px;"  ></select>
		                 </td>
             <td width="8%"><label >formatage </label></td>
             <td  width="20%" ><input id="formatage" name="formatage"   type="text"  required    value="${detailBean.formatage}">             </td>
         </tr>
   
          </table>
  	  </ext:panel>
</ext:body>





 








