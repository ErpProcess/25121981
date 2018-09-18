<%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
$(document).ready(function (){
    LoadOtherAutocompletesAjax("pk_article.ar_id","i$_ACT_LOAD_COD_BARRE","pk.code_barre","designation_libelle","type_trf_id");
    loadSelectAjax("mode_choix_prix_venteX","list_mode_prix","data_id","data_libelle","${searchBean.fkCode_barre.pk.ar_bean.mode.data_id}",true); 
});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table width="100%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
   
    
   
  
              <tr  >  
	            <td width="10%"><label>Tarif_id</label></td>  
			    <td width="29%" ><input id="tarif_vente_id" name="tarif_vente_id"     type="text"    size="17"       maxlength="17"        value="${searchBean.tarif_vente_id}"></td> 
	            <td width="38%" style="display: none;" ><label>${ar_id} </label>
                <input id="pk_article.ar_id" name="fkCode_barre.pk.ar_bean.pk_article.ar_id"     type="text"    size="10"       maxlength="25"        value="${searchBean.fkCode_barre.pk.ar_bean.pk_article.ar_id}"    nextelement="pk.code_barre">
                <input  id="ar_libelle"       name="fkCode_barre.pk.ar_bean.ar_libelle"           type="text"    size="25"       maxlength="25"        value="${searchBean.fkCode_barre.pk.ar_bean.ar_libelle}"    nextelement="pk.code_barre"></td>
                <td width="23%" style="display: none;">&nbsp;</td>
              </tr>
	          
	          
	          
			     
	          <tr> 
			    <td  width="10%"><label>${ar_id}</label></td>  
			    <td colspan="3"   >  
			    <table><tr> <td>
			    <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="10"       maxlength="100"        value="${searchBean.fkCode_barre.pk.code_barre}"    nextElement="TosuivTarif"               />
			    </td>
			    <td>
			    <input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="45"       maxlength="100"        value="${searchBean.fkCode_barre.designation_libelle}"    nextElement="TosuivTarif"               />			    </td> </tr> </table>			   </td>  
	          </tr>  
	          <tr>  
	          <tr>  
			   <td width="10%"><label>${dernierPrix}</label></td>  
			   <td colspan="3"  >  
			   <input   isboolean   id="dernierPrix" name="dernierPrix"     type="checkbox"            value="${searchBean.dernierPrix}"    nextElement="type_trf_id"              />
			   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_trf3}"   var="searchdate_trf3"/>
	     <input id="date_trf3"             name="date_trf3"     type="datepicker"    size="13"             value="${searchdate_trf3}"                  />			  </td>  
	    </tr>
			   
			   
			 <tr>
		        <td ><label>Nature Prix de vente</label></td>
		        <td colspan="3" > 
		        <select  id="mode_choix_prix_venteX"   name="fkCode_barre.pk.ar_bean.mode.data_id"           style="width: 200px;"               ></select>		    </td>
		</tr>
			   
			   <tr>
   <td ><label>${_datedebut} </label></td>  
   <td colspan="3"    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_trf}"   var="searchats_date"/>
	     <input id="date_trf"             compareTo="date_trf2"      name="date_trf"        type="datepicker"    size="13"       maxlength="13"        value="${searchats_date}"    nextElement="date_trf2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_trf2}"   var="searchdate_trf2"/>
	     <input id="date_trf2"        comparedTo="date_trf"      name="date_trf2"     type="datepicker"    size="13"       maxlength="13"        value="${searchdate_trf2}"    nextElement="type_trf_id"              /></td>
	   </tr>
	   </table>  </td>  
   </tr>   
	          
	          <tr>
	          
	           <td    ><label>${type_trf_id}</label></td>  
   <td colspan="3"     >
    <script type="text/javascript">
	    $(document).ready(function() {
	     loadSelectAjax("type_trf_idx","list_tyList","type_trf_id","type_trf_lib","${searchBean.groupe.type_trf_id}",true);
	    });
    </script> 
   <select   id="type_trf_idx" name="groupe.type_trf_id"               libre      nextElement="coef_trf"           ></select>  </td>  
	          </tr> 
  
  <tr>  
   <td width="10%"><label>Lot</label></td>  
   <td colspan="3"  >  
   <input id="num_serie" name="num_serie"     type="text"    size="17"       maxlength="17"        value="${searchBean.num_serie}"                    />  </td>  
   </tr> 
   
   <tr>  
   <td width="10%"><label>${prix_unit_achat}</label></td>  
   <td colspan="3"  >  
   <input id="prix_unit_achat" name="cout.tarif_unit_article"     type="text"    size="17"       maxlength="17"        value="${searchBean.cout.tarif_unit_article}"    nextElement="type_trf_id"              />  </td>  
   </tr>   
   
   
   <tr>  
   <td width="10%"><label>${prix_unit_vente}</label></td>  
   <td colspan="3"  >  
   <input id="tarif_unit_vente" name="tarif_unit_vente"     type="text"    size="17"       maxlength="17"        value="${searchBean.tarif_unit_vente}"    nextElement="taux_remise"              />  </td>  
   </tr>  
   
   
    <tr>  
   <td width="10%"><label>Taux remise</label></td>  
   <td colspan="3"  >  
   <input id="operation" name="operation"     type="text"    size="7"   value="${searchBean.operation}"  > 
   <input id="taux_remise" name="taux_remise"     type="text"    size="10"            value="${searchBean.taux_remise}"    nextElement="taux_remise"  />  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
