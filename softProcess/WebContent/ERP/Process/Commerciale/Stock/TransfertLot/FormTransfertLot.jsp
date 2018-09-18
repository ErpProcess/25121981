 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
 
    <tr>  
		   <td ><label>${depot_id_emet}</label></td>  
		   <td   > 
		   <input id="depot_id" name="pk.lot.pk.depot.depot_id"     type="text"    size="10"             value="${detailBean.pk.lot.pk.depot.depot_id}"     required  />
		   <input id="depot_libelle" name="pk.lot.pk.depot.depot_libelle"     type="text"    size="30"             value="${detailBean.pk.lot.pk.depot.depot_libelle}"      required   />
		  </td>  
	 </tr>  
	<tr>  
      <td  ><label>Article</label></td>  
	  <td   >  
      <input id="pk.code_barre" name="pk.lot.fkCode_barre.pk.code_barre"                 type="text"    size="15"     value="${detailBean.pk.lot.fkCode_barre.pk.code_barre}"        required    autofocus    />
      <input id="designation_libelle" name="pk.lot.fkCode_barre.designation_libelle"     type="text"    size="30"     value="${detailBean.pk.lot.fkCode_barre.designation_libelle}"       required            />		  </td>  
    </tr>  
   
	 
   <tr>  
   <td width="7%"><label>${transfert_id}</label></td>  
   <td width="93%"  >  
   <input id="transfert_id" name="pk.transfert_id"     type="text"    size="25"       maxlength="25"        value="${detailBean.pk.transfert_id}"    nextElement="num_serie"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${num_serie}</label></td>  
   <td width="93%"  >  
   <input id="num_serie" name="pk.lot.pk.num_serie"     type="text"    size="25"       maxlength="25"        value="${detailBean.pk.lot.pk.num_serie}"    nextElement="depot_id_emet"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_id_emet}</label></td>  
   <td width="93%"  >  
   <input id="depot_id_emet" name="pk.lot.pk.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.pk.lot.pk.depot.depot_id}"    nextElement="date_transfert"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${date_transfert}</label></td>  
   <td width="93%"  >  
    <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_transfert}"   var="searchat_date"/>
   <input id="date_transfert" name="date_transfert"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="quantite_transfert"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${quantite_transfert}</label></td>  
   <td width="93%"  >  
   <input id="quantite_transfert" name="quantite_transfert"     type="text"    size="17"       maxlength="17"        value="${detailBean.quantite_transfert}"    nextElement="observation"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${observation}</label></td>  
   <td width="93%"  >  
   <input id="observation" name="observation"     type="text"    size="50"       maxlength="50"        value="${detailBean.observation}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
