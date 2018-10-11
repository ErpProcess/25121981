<%@ include file="/Aceuil/esProcess.jsp"     %>
<script type="text/javascript"  >


$(function() { 
 document.getElementById("Tr_arcodbar").style.display="none";
 if("${detailBean.bean_artyp.data_id}"!=""){
  document.getElementById("Tr_arcodbar").style.display="block";
 }
} );
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
		        <td   width="150px" ><label>${ar_id}</label></td>
		        
		        <td    >
		        <input id="ar_id" name="pk_article.ar_id"   maxlength="15"   type="text"   style="width: 179px;"     value="${detailBean.pk_article.ar_id}"    nextElement="ar_libelle"         readonly="readonly"   libre           />
		        </td>
		      </tr>
		      <tr>
		        <td  ><label>${ar_libelle}</label></td>
		        <td   ><input id="ar_libelle" name="ar_libelle"  maxlength="100"   type="text"    size="48"    value="${detailBean.ar_libelle}"    nextElement="arcodbar"              />
		        </td>
		      </tr>
		      <tr>
		        <td  ><label>${fam_id}</label></td>
		        <td    > 
		         <script  >  $(function() { loadSelectAjax("fam_idX","list_FamilleArticle","fam_id","fam_lib","${detailBean.fam_art.fam_id}",true);})</script>
		            <select  id="fam_idX"      name="fam_art.fam_id"           style="width: 180px;"      nextElement="artyp"        required   ></select>
		        </td>
		      </tr>
		      <tr  >
		        <td  ><label>${artyp}</label></td>
		        <td    ><table>
		            <tr>
		              <td>
		                  <input   name="bean_artyp.data_id"     type="hidden"       value="${detailBean.bean_artyp.data_id}"   />
		                  <input   name="bean_adata_id"          type="text"   libre readonly="readonly"   maxlength="512"  size="20"    value="${detailBean.bean_artyp.data_libelle}"   />
		                </td>
		                 <td      style="margin-left: 20px;"  ><label>${arcodbar}</label></td>
		                 <td   id="Tr_arcodbar"  style="margin-left: 20px;"  > 
		                <input id="arcodbar" name="arcodbar"     type="text"    libre readonly="readonly"    maxlength="512"  size="20"    value="${detailBean.arcodbar}"   />
		              </td>
		            </tr>
		          </table>
		       
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
		        <td ><label>Méthode de vente</label></td>
		        <td > 
		        <script  >$(function() { 
		        
		        
		         loadSelectAjax("mode_choix_prix_venteX","list_mode_prix","data_id","data_libelle","${detailBean.mode.data_id}",false);  })</script>
		         
		         
		         
		        <select  id="mode_choix_prix_venteX"  name="mode.data_id"      readonly     style="width: 200px;"               ></select>   
		        </td>
		      </tr>
		      
		     <tr>
		        <td ><label>Tva</label></td>
		        <td > 
		        <script  >$(function() {    loadSelectAjax("tva_idXX","list_tvList","tva_id","tva_libelle","${detailBean.tva.tva_id}",false); })</script>
		        <select  id="tva_idXX"  name="tva.tva_id"           style="width: 200px;"               ></select>   
		        </td>
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
