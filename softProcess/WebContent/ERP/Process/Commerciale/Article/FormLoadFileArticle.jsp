<%@    include file="/Aceuil/esProcess.jsp"     %>
<script type="text/javascript">
    $(document).ready(function () {  selectOptionvalue("fam_id","${searchBean.fam_art.fam_id}");  })
    var files = [];
	$(document).on("change","#fileLoader",function(event) { files=event.target.files; processUpload();})
	
	
	function processUpload(){
	           
	             var oMyForm = new FormData();
	             oMyForm.append("file", files[0]);
	             oMyForm.append("HiddenAction", "i$_ACT_UPLOADER");
	             oMyForm.append("doc_id_interne",  $('#doc_id_interne').val() );
	             
	            
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


$(function() { 
LoadAutoCompletAjax_with_marGin("clt_id","clt_lib","depot_id","listClientInit","250","100"); 
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle",null,"listDepotStockageInit","250","100");  
});
 
   </script>
 
<ext:body   >

  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
  
 
<ext:tabPanel   border="false"        id="sdsfgrgrgpll"  activeTab="fr"  >
  
	  <ext:panel   id="fr"    bodyStyle="background: none;"       border="false"      title="Francais"    > 
	   
    <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   >
    
    <tr> 
        <td  width="50%">
		     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"   border="0"   >
		     
		     
		      
		       
		      
		      <tr>  
   <td  ><label>File</label></td>  
   <td    >  
   <input  id="fileLoader"  type="file" name="file"       />    
  </td> 
   </tr> 
		      
		       
		      
		      <tr>
		        <td  ><label>${fam_id}</label></td>
		        <td    > 
		         <script  >  $(function() { loadSelectAjax("fam_idX","list_FamilleArticle","fam_id","fam_lib","${detailBean.fam_art.fam_id}",true);})</script>
		            <select  id="fam_idX"      name="fam_art.fam_id"           style="width: 180px;"      nextElement="artyp"        required   ></select>
		        </td>
		      </tr>
		     
		      <tr>
		        <td ><label>${mode_calcul_prix_vente}</label></td>
		        <td > 
		          <script  >$(function() {  loadSelectAjax("mode_calcul_prix_vente","list_mode_calcul","data_id","data_libelle","${detailBean.bean_mode_cal.data_id}",true);  })</script>
		          <select  id="mode_calcul_prix_vente"  name="bean_mode_cal.data_id"           style="width: 180px;"      nextElement="unite_id"        required   ></select>  
		        </td>
		      </tr>
		      
		       <tr>
		        <td  ><label>${unite_id}</label></td>
		         
		        <td  >
		        <script  >$(function() { loadSelectAjax("unite_idX","list_unite_article","unite_id","unite_lib","${detailBean.unitBean.unite_id}",true) ; })</script>
		        <select id="unite_idX"  name="unitBean.unite_id"   nextElement="stock_minimum"  style="width: 180px;"   required  ></select>
		        </td>
		      </tr>
		      
		      
		      <tr>
		        <td  ><label>${stock_minimum}</label></td>
		        <td    ><input id="stock_minimum" name="stock_minimum" style="width: 179px;"  nextElement="btValidx"   value="${detailBean.stock_minimum}"  />
		        </td>
		      </tr>
		      <tr>
		        <td  ><label>${stock_maximum}</label></td>
		        <td    ><input id="stock_maximum" name="stock_maximum"  style="width: 179px;"   nextElement="btValidx"    value="${detailBean.stock_maximum}" />
		        </td>
		      </tr>
		      
		      <tr>
		        <td ><label>${ar_obs}</label></td>
		        <td ><textarea id="ar_obs" name="ar_obs" rows="5" cols="50"     >${detailBean.ar_obs}</textarea>
		        </td>
		      </tr>
		    </table>
        </td> 
      <td  valign="top" >
      		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"   border="0"    >
      		 
      		  <tr  style="display: none;">
		        <td width="150px"><label>${soc_id}</label></td>
		        <td  >
		        <input  idonly  id="soc_idXX"  name="pk_articleXX.etabBean.pk_etab.soc_bean.soc_id"     size="7"  type="text"   value="${detailBean.pk_article.etabBean.pk_etab.soc_bean.soc_id}"         />
		        <input  idonly  id="soc_libXX" name="pk_articleXX.etabBean.pk_etab.soc_bean.soc_lib"   size="30"  type="text"   value="${detailBean.pk_article.etabBean.pk_etab.soc_bean.soc_lib}"         />
		        </td>
		      </tr>
		      
		       <tr>
		        <td width="150px"><label>${etab_id}</label></td>
		        <td    >
		        <script >$(function() {   if( "${bs.fct_id}"=="1") { LoadOtherAutocompletesAjax("soc_idXX","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","possede_date_peremption");}});</script>
		        <input idonly  id="pk_etab.etab_id"  name="pk_article.etabBean.pk_etab.etab_id"  size="7"  type="text"  value="${detailBean.pk_article.etabBean.pk_etab.etab_id}"      required   />
		        <input idonly  id="etab_lib"         name="pk_article.etabBean.etab_lib"   type="text"  size="30"  value="${detailBean.pk_article.etabBean.etab_lib}"                   required />
		        </td>
		      </tr>
		      
		      
		      <tr>
		        <td width="150px"><label>${possede_date_peremption}</label></td>
		        <td ><input isboolean  id="possede_date_peremption" name="possede_date_peremption"   type="checkbox"      value="${detailBean.possede_date_peremption}"         />
		        </td>
		      </tr>
		      <tr>
		        <td ><label>${ar_exonorer}</label></td>
		        <td ><input  isboolean  id="ar_exonorer"              name="ar_exonorer"     type="checkbox"        value="${detailBean.ar_exonorer}"                   />
		        </td>
		      </tr>
		      <tr>
		        <td ><label>${initialiser_au_inv}</label></td>
		        <td ><input isboolean  id="initialiser_au_inv" name="initialiser_au_inv"    type="checkbox"    value="${detailBean.initialiser_au_inv}"   /> 
		        </td>
		      </tr>
		      
		      
		      
		      
		       <tr>
		        <td ><label>Par Lot</label></td>
		        <td ><input isboolean  id="par_lot" name="par_lot"    type="checkbox"    value="${detailBean.par_lot}"   /> 
		        </td>
		      </tr>
		      
		      <tr>
		        <td ><label>Choix du lot</label></td>
		        <td > 
		        <script  >$(function() {  loadSelectAjax("mode_choix_lot","list_choix_lot","data_id","data_libelle","${detailBean.choix.data_id}",false);  })</script>
		        <select  id="mode_choix_lot"    name="choix.data_id"   readonly       style="width: 200px;"       libre     ></select>   
		        </td>
		      </tr>
		      
		      <tr>
		        <td ><label>Cathégorie Produit</label></td>
		        <td > 
		        <script  >$(function() {  loadSelectAjax("art_cath","list_cathegori_article","data_id","data_libelle","${detailBean.cathegorie.data_id}",false);  })</script>
		        <select  id="art_cath"  name="cathegorie.data_id"           style="width: 200px;"           required   ></select>   
		        </td>
		      </tr>
		      
		      <tr>
		        <td ><label>Prix de vente</label></td>
		        <td > 
		        <script  >$(function() { 
		         loadSelectAjax("mode_choix_prix_venteX","list_mode_prix","data_id","data_libelle","${detailBean.mode.data_id}",false); 
		          })</script>
		           <select  id="mode_choix_prix_venteX"  name="mode.data_id"   readonly       style="width: 200px;"       libre      ></select>   
		        </td>
		      </tr>
		      <tr>
		        <td ><label>Tva</label></td>
		        <td > 
		        <script  >$(function() {    loadSelectAjax("tva_idXX","list_tvList","tva_id","tva_libelle","${detailBean.tva.tva_id}",false); })</script>
		        <select  id="tva_idXX"  name="tva.tva_id"           style="width: 200px;"               ></select>   
		        </td>
		      </tr>
		      
		      
		      <tr>
		        <td ><label>Prix_Achat</label></td>
		        <td > 
		        <label>HTV</label><input  id="prix_achat"  name="prix_achat"   type="montant3"    value=""              /> <br>
		        <label>TTC</label><input  id="prix_achatttc"  name="prix_achatttc"   type="montant3"    value=""              /> 
		        </td>
		      </tr>
		      
		      <tr>
		        <td ><label>Prix_Vente</label></td>
		        <td > 
		         <label>HTV</label><input  id="prix_vente"  name="prix_vente"      type="montant3"     value=""              /><br>
		         <label>TTC</label><input  id="prix_ventettc"  name="prix_ventettc"   type="montant3"    value=""              />  
		        </td>
		      </tr>
		      
		       <tr>  
		   		<td  ><label>Dépôt</label></td>  
		   		<td    >  
					   <input id="depot_id"      name="depot_id"       type="text"    size="10"       maxlength="10"        value=""   />  
					   <input id="depot_libelle" name="depot_libelle"      type="text"    size="30"       maxlength="10"        value=""          />	
				</td>  
		       </tr> 
  				<tr>  
				   <td ><label>Client</label></td>  
				   <td  >  
				    <input id="clt_id" name="clt_id"           type="text"     size="10"             value=""   /> 
					<input id="clt_lib" name="clt_lib"        type="text"      size="30"             value=""  />		  </td>  
	           </tr> 
		      
		     </table >
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
