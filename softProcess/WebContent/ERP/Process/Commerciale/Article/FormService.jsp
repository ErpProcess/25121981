  
		     
		      
		       <%@ include file="/Aceuil/esProcess.jsp"     %>
<script type="text/javascript"  >

$(function() { 

if("${bs.fct_id}"=="2")  {
document.getElementById("Tr_arcodbar").style.display="block";
document.getElementById("Tr_arcodbar_t").style.display="block";
 } 

});



function doAfficherCodeBar(levalde_type){
    
 document.getElementById("Tr_arcodbar").style.display="none";
 
  document.getElementById("Tr_arcodbar_t").style.display="none";
 
 if(levalde_type=="US"){
  document.getElementById("Tr_arcodbar").style.display="block";
    document.getElementById("Tr_arcodbar_t").style.display="block";
 }
} 
   </script>
 
<ext:body   >

  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
  
  
    <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   >
    
    <tr> 
        <td  width="50%">
		     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"   border="0"   >
		     
		     
		      
		      <tr      >
		        <td   width="150px" ><label>${ar_id}</label></td>
		        <td          >
		        <input id="ar_id" name="pk_article.ar_id"   maxlength="15"   type="text"   style="width: 179px;"     value="${detailBean.pk_article.ar_id}"       libre   readonly="readonly"         />
		        </td>
		      </tr>
		      
		      <tr>
		        <td  ><label>${ar_libelle}</label></td>
		        <td   ><input id="ar_libelle" name="ar_libelle"  maxlength="100"   type="text"    size="48"    value="${detailBean.ar_libelle}"    nextElement="arcodbar"              />
		        </td>
		      </tr>
		      <tr>
		      
		       <tr  >
		        <td  ><label>Type Code</label></td>
		        <td    ><table>
		            <tr>
		              <td>
		                 <script  >$(function() {loadSelectAjax("artypX","list_type_article","data_id","data_libelle","${detailBean.bean_artyp.data_id}",true); })</script>
		                 <select  idonly   id="artypX"  name="bean_artyp.data_id"  onchange="doAfficherCodeBar(this.value)"  style="width: 180px;"  ></select>
		                </td>
		                
		                <td    id="Tr_arcodbar_t"  style="display: none;margin-left: 35px;" valign="top"> 
		                <label>${arcodbar}</label>
		                </td>
		                <td    id="Tr_arcodbar"  style="display: none;margin-left: 35px;"  valign="top"> 
		                 <input id="arcodbar"     name="arcodbar"     type="text"     maxlength="512"  size="20"    value="${detailBean.arcodbar}"   />
		               </td>
		               
		            </tr>
		          </table>
		       
		        </td>
		      </tr>
		      
		      <tr>
		        <td  ><label>${fam_id}</label></td>
		        <td    > 
		         <script  >  $(function() { loadSelectAjax("fam_idX","list_FamilleArticle","fam_id","fam_lib","${detailBean.fam_art.fam_id}",true);})</script>
		            <select  id="fam_idX"      name="fam_art.fam_id"           style="width: 180px;"      nextElement="artyp"            ></select>
		        </td>
		      </tr>
		     
		      <tr>
		        <td ><label>${mode_calcul_prix_vente}</label></td>
		        <td > 
		          <script  >$(function() {  loadSelectAjax("mode_calcul_prix_vente","list_mode_calcul","data_id","data_libelle","${detailBean.bean_mode_cal.data_id}",true);  })</script>
		          <select  id="mode_calcul_prix_vente"  name="bean_mode_cal.data_id"           style="width: 180px;"      nextElement="unite_id"            ></select>  
		        </td>
		      </tr>
		      
		       <tr>
		        <td  ><label>${unite_id}</label></td>
		         
		        <td  >
		        <script  >$(function() { loadSelectAjax("unite_idX","list_unite_article","unite_id","unite_lib","${detailBean.unitBean.unite_id}",true) ; })</script>
		        <select id="unite_idX"  name="unitBean.unite_id"   nextElement="stock_minimum"  style="width: 180px;"      ></select>
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
		        <script >$(function() {   if( "${bs.fct_id}"=="5") { LoadOtherAutocompletesAjax("soc_idXX","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","possede_date_peremption");}});</script>
		        <input idonly  id="pk_etab.etab_id"  name="pk_article.etabBean.pk_etab.etab_id"  size="7"  type="text"  value="${detailBean.pk_article.etabBean.pk_etab.etab_id}"      required   />
		        <input idonly  id="etab_lib"         name="pk_article.etabBean.etab_lib"   type="text"  size="30"  value="${detailBean.pk_article.etabBean.etab_lib}"                   required />
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
</ext:body>
		       