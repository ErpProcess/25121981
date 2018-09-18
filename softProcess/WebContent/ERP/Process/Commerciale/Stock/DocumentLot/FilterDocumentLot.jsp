 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 
 
 
 
 
 <table  width="100%"><tr><td  width="50%">
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"    width="100%"     >  
		   <tr>  
		   <td width="20%"><label>${num_lot}</label></td>  
		   <td   >  
		   <input id="pk.num_serie" name="pk.num_serie"     type="text"    size="25"       maxlength="25"        value="${searchBean.pk.num_serie}"    nextElement="date_fabrication"    autofocus         />  
		  </td>  
		   </tr>   
		   
		    <tr>  
		   <td  ><label>date serie</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_serie}"   var="searchBeandateisatdion"/> 
		   <input id="date_serie" name="date_serie"     type="text"    size="10"            value="${searchBeandateisatdion}"              />  
		  </td>  
		   </tr>   
		   
		   
		   
		   <tr>  
		   <td  ><label>${date_fabrication}</label></td>  
		   <td   > 
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_fabrication}"   var="searchBeandate_fabrication"/>  
		   <input id="date_fabrication" name="date_fabrication"     type="text"    size="13"       maxlength="13"        value="${searchBeandate_fabrication}"    nextElement="date_utilisation"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${date_utilisation}</label></td>  
		   <td    >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_utilisation}"   var="searchBeandate_utilisation"/> 
		   <input id="date_utilisation" name="date_utilisation"     type="text"    size="13"       maxlength="13"        value="${searchBeandate_utilisation}"    nextElement="date_peremption"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${date_peremption}</label></td>  
		   <td  > 
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_peremption}"   var="searchBeandate_peremption"/> 
		   <input id="date_peremption" name="date_peremption"     type="text"    size="13"       maxlength="13"        value="${searchBeandate_peremption}"                />  
		  </td>  
		   </tr>    
		 </table>
	   </td><td  width="50%"  >	 
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  width="100%"     >  
		   <tr>  
		   <td width="20%"><label>Article</label></td>  
		   <td    >  
		   <input id="fkCode_barre.pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="10"                value="${searchBean.fkCode_barre.pk.code_barre}"       />  
		   <input id="fkCode_barre.designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="30"             value="${searchBean.fkCode_barre.designation_libelle}"       />
		  </td>  
		   </tr>   
		   <tr>  
		   <td ><label>depot</label></td>  
		   <td   > 
		   <input id="depot_id" name="pk.depot.depot_id"     type="text"    size="10"             value="${searchBean.pk.depot.depot_id}"       />
		   <input id="depot_libelle" name="pk.depot.depot_libelle"     type="text"    size="30"             value="${searchBean.pk.depot.depot_libelle}"       />
		  </td>  
		   </tr>   
		  
		   <tr>  
		   <td ><label>source</label></td>  
		   <td   > 
		    <input id="nature_mvt_libelle" name="nature_mvt.nature_mvt_libelle"     type="text"    size="10"             value="${searchBean.nature_mvt.nature_mvt_libelle}"                /> 
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="30"              value="${searchBean.mvt_com_id}"                />  
		  </td>  
		   </tr> 
		   
		   <tr>  
		   <td ><label>Tarif.Achat</label></td>  
		   <td   > 
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="10"               value="${searchBean.tarif.tarif_unit_article}"  /> 
		    <span  style="float: right;">
		    <label>Qte.Initiale</label>
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="5"               value="${searchBean.quantite_init}"                />
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    </span>   
		  </td>  
		   </tr> 
		   
		    <tr>  
		   <td ><label>Qte.dispo</label></td>  
		   <td   > 
		    <input id="quantite" name="quantite"     type="text"    size="10"               value="${searchBean.quantite}"  /> 
		    <span  style="float: right;">
		    <label>Mode.actuelle</label>
		    <input id="mvt_com_id" name="etat.data_id"     type="hidden"    size="5"               value="${searchBean.etat.data_id}"                />
		    <input id="dataLib" name="etat.data_libelle"     type="text"    size="5"               value="${searchBean.etat.data_libelle}"                />
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    </span>   
		  </td>  
		   </tr> 
		   
		  <tr>  
		   <td ><label>Tarif.Vente</label></td>  
		   <td   > 
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="20"               value=""                /> 
		     <span  style="float: right;">
		     <label>Série bloquée</label> 
		     <input  isboolean  id="serie_bloque"              name="serie_bloque"     type="checkbox"        value="${searchBean.serie_bloque}"   />
		        &nbsp;&nbsp;&nbsp;&nbsp;
		    </span>  
		       
		  </td>  
		  </tr> 
		      
		 </table>
	</td></tr>
</table>
 
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
