 <%@include file="/Aceuil/esProcess.jsp" %>  
  <script type="text/javascript">
$(document).ready(function () {
 
 LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","usr_id","list_depot_stock_aff","250","100");
 LoadAutoCompletAjax_with_marGin("usr_id"  ,"nom_prenom","ordre","Utilisateur-List","250","100");
 
}); 
</script>
  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id"       name="pk.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.pk.depot.depot_id}"    nextElement="usr_id"    autofocus   required     />  
   <input id="depot_libelle"  name="pk.depot.depot_libelle"     type="text"    size="15"           value="${detailBean.pk.depot.depot_libelle}"    nextElement="usr_id"         required     />
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${usr_id}</label></td>  
   <td width="93%"  >  
   <input id="usr_id" name="pk.usr.usr_id"     type="text"    size="5"       maxlength="5"        value="${detailBean.pk.usr.usr_id}"    nextElement="ordre"              />  
   <input id="nom_prenom" name="pk.usr.nom_prenom"     type="text"    size="15"              value="${detailBean.pk.usr.nom_prenom}"    nextElement="ordre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ordre}</label></td>  
   <td width="93%"  >  
   <input id="ordre" name="ordre"     type="number"    size="10"       maxlength="10"        value="${detailBean.ordre}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
