  
  <%@ include file="/Aceuil/esProcess.jsp"     %>  
  <ext:body   >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   >  
		 <tr>
		   <td width="50%" >  
		   
		 	 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"    border="0"   >  
				   <tr>  
				   <td width="150px"><label>${ar_id}</label></td>  
				   <td   >  
				   <input id="ar_id" name="pk_article.ar_id"   maxlength="15"   type="text"    size="15"   
				    value="${detailBean.pk_article.ar_id}"    nextElement="fam_id"    autofocus   required     />  
				  </td>  
				   </tr>   
				   <tr>  
				   <td ><label>${fam_id}</label></td>  
				   <td   >  
				   
				    <input id="famlib" name="famlib"     type="text"    size="20"    value="${detailBean.fam_art.fam_lib}"          /> 
				    <input id="fam_id" name="fam_art.fam_id"     type="hidden"    size="20"    value="${detailBean.fam_art.fam_id}"                 />  
				  </td>  
				   </tr>   
				    <tr>  
				   <td ><label>${sitcod}</label></td>  
				   <td   >  
				    <script type="text/javascript">
				   $(document).ready(function () {  selectOptionvalue("sitcod","${detailBean.bean_sitcod.data_id}");  });
				   </script>
				   <select id="sitcod" name="bean_sitcod.data_id"   nextElement="ar_libelle"      >
				     <c:forEach var="etat_bean"    items="${listUsr_etat}"    >
				   <option  value="${etat_bean.data_id}">${etat_bean.data_libelle}</option>
				   </c:forEach>
				   
				   </select>
				     
				  </td>  
				   </tr>   
				   <tr>  
				   <td ><label>${ar_libelle}</label></td>  
				   <td   >  
				   <input id="ar_libelle" name="ar_libelle"     type="text"    size="20"    value="${detailBean.ar_libelle}"    nextElement="arcodbar"              />  
				  </td>  
				   </tr>   
				   <tr>  
				   <td ><label>${arcodbar}</label></td>  
				   <td   >  
				   <input id="arcodbar" name="arcodbar"     type="text"    size="20"    value="${detailBean.arcodbar}"    nextElement="artyp"              />  
				  </td>  
				   </tr>   
				</table>
				
			</td>
			    
				<td  valign="top" >
	      		 
	      <table class="tableStyleContent"  cellpadding="5" cellspacing="10"   border="0"    >
			   <tr>
		        <td width="150px"><label>${soc_id}</label></td>
		        <td  >
		        <script >$(function() {  if( "${bs.fct_id}"=="1") { LoadAutoCompletAjax("soc_id","soc_lib","pk_etab.etab_id","listSocioTa");}});</script>
		        <input  idonly  id="soc_id" name="pk_article.etabBean.pk_etab.soc_bean.soc_id"     size="7"  type="text"   value="${detailBean.pk_article.etabBean.pk_etab.soc_bean.soc_id}"         />
		        <input  idonly  id="soc_lib" name="pk_article.etabBean.pk_etab.soc_bean.soc_lib"   size="30"  type="text"   value="${detailBean.pk_article.etabBean.pk_etab.soc_bean.soc_lib}"         />
		        </td>
		      </tr>
		      
		       <tr>
		        <td width="150px"><label>${etab_id}</label></td>
		        <td    >
		        <script >$(function() {   if( "${bs.fct_id}"=="1") { LoadOtherAutocompletesAjax("soc_id","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","possede_date_peremption");}});</script>
		        <input idonly id="pk_etab.etab_id" name="pk_article.etabBean.pk_etab.etab_id"  size="7"  type="text"  value="${detailBean.pk_article.etabBean.pk_etab.etab_id}"         />
		        <input idonly  id="etab_lib" name="pk_article.etabBean.etab_lib"   type="text"  size="30"  value="${detailBean.pk_article.etabBean.etab_lib}"         />
		        </td>
		      </tr>
		     </table >
		     
		     
		     </td>
		     </tr>
		  </table >
		  
           
</ext:panel>
</ext:body>
