 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"><label>${liv_id}</label></td>  
   <td width="90%"  >  
   <input id="liv_id" name="liv_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.liv_id}"    nextElement="liv_libelle"     libre  readonly="readonly"         />  
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>${liv_libelle}</label></td>  
   <td   >  
   <input id="liv_libelle" name="liv_libelle"     type="text"    size="40"               value="${detailBean.liv_libelle}"    nextElement="liv_date"              />  
  </td>  
   </tr>

 <tr>  
   <td width="10%"><label>${trans_id}</label></td>  
   <td   >  
   <script type="text/javascript">     
   $(document).ready(function () {
		LoadAutoCompletAjax_with_marGin("trans_id"  ,"trans_libelle","liv_date","list_transport_liv","250","100");
		LoadAutoCompletAjax_with_marGin("vente_id"  ,"vente_date","null","list_vente_a_livrer","250","250");
	});
   
    </script>
   <input id="trans_id" name="trans.trans_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.trans.trans_id}"    nextElement="btValidx"              />
   <input id="trans_libelle" name="trans.trans_libelle"     type="text"    size="25"               value="${detailBean.trans.trans_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
   
     
   <tr>  
   <td width="10%"><label>${liv_date}</label></td>  
   <td   >  
   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.liv_date}"   var="eddfggliv_date"/>  
   <input id="liv_date" name="liv_date"     type="datepicker"    size="13"       maxlength="13"        value="${eddfggliv_date}"    nextElement="liv_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>Vente N°</label></td>  
   <td   >  
   <input id="vente_id" name="vente.vente_id"     type="text"    size="13"       maxlength="13"        value="${detailBean.vente.vente_id}"                />
   
      <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.vente.vente_date}"   var="xcmd_date"/>  
   <input id="vente_date" name="vente.vente_date"     type="text"    size="13"       maxlength="13"        value="${xcmd_date}"                />
  </td>  
   </tr>   
  
   <tr>  
   <td width="10%"><label>${montant_livraison}</label></td>  
   <td   >  
   
   <input id="montant_livraison" name="montant_livraison"     type="montant3"    size="13"       maxlength="13"        value="${detailBean.montant_livraison}"                />  
  </td>  
   </tr>   
  
  
 </table>   
</ext:panel>
</ext:body>
