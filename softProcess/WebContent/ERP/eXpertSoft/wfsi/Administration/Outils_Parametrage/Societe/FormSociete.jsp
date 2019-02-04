<%@    include file="/Aceuil/esProcess.jsp"     %>

<script type="text/javascript">
var files = [];
$(document).on("change","#fileLoader",function(event) { files=event.target.files; processUpload();})


function processUpload(){
           
             var oMyForm = new FormData();
             oMyForm.append("file", files[0]);
             oMyForm.append("HiddenAction", "i$_ACT_UPLOADER");
             $.ajax({dataType : "text",
                    url : "${tmlx.urlAjax}",
                    data : oMyForm,
                    type : "POST",
                    enctype: "multipart/form-data",
                    processData: false, 
                    contentType:false,
                    scriptCharset: "utf-8",
        			async: false,
        			cache: false,
        			timeout: 600000,
                    success : function(result) {
                    	 alert(result);
                    },
                    error : function(result){
                        alert(result);
                    }
                });
     }
</script>

<ext:body  >
<ext:panel  renderTo="ThePageJsp"             >

 <ext:tabPanel   border="false"        id="sdsfgrgrgpll"  activeTab="fr"  >
	  <ext:panel   id="fr"    bodyStyle="background: none;"       border="false"      title="Francais"    > 
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
             <input id="soc_id"   name="soc_id"     type="text"     size="10"   value="${detailBean.soc_id}"    libre readonly="readonly"   />
             <input id="file_id"  name="file_id"    type="text"        value="${detailBean.file_id}"    libre readonly="readonly"   />
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
   
       <tr>  
   <td  ><label>File</label></td>  
   <td    >  
   <input  id="fileLoader"  type="file" name="file"       />    
  </td> 
   </tr> 
   
          </table>
	  </ext:panel>
	  
	   <ext:panel   id="ar"   bodyStyle="background: none;"       height="500"    border="false"      title="Arabic"    > 
	   <textarea id="dataSocieteLng_ar" name="dataSocieteLng_ar"   rows="10"   style="width: 100%;"><c:forEach var="entry" items="${detailBean.maplang.get('ar')}" >${entry.key}:${entry.value},</c:forEach></textarea>
	  </ext:panel>
	  
	   <ext:panel   id="en"   bodyStyle="background: none;"       height="500"    border="false"      title="English"    > 
	   <textarea id="dataSocieteLng_en" name="dataSocieteLng_en"   rows="10"   style="width: 100%;"><c:forEach var="entry" items="${detailBean.maplang.get('en')}" >${entry.key}:${entry.value},</c:forEach></textarea>
	  </ext:panel>
	  
 </ext:tabPanel>

  	  </ext:panel>
</ext:body>





 









