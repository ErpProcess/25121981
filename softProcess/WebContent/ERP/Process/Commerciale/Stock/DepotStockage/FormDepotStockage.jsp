 <%@include file="/Aceuil/esProcess.jsp" %>  
 
   <script type="text/javascript">
$(document).ready(function () {
 LoadAutoCompletAjax_with_marGin("nat_lieu_id","nat_lieu_libelle","etab_lib","NatureLieu-List","250","100");
}); 
</script>



  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id" name="depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot_id}"    nextElement="depot_libelle"    autofocus    libre  readonly="readonly"     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_libelle}</label></td>  
   <td width="93%"  >  
   <input id="depot_libelle" name="depot_libelle"     type="text"    size="100"       maxlength="200"        value="${detailBean.depot_libelle}"    nextElement="depot_ordre"              />  
  </td>  
   </tr> 
     
   <tr>  
   <td width="7%"><label>${depot_ordre}</label></td>  
   <td width="93%"  >  
   <input id="depot_ordre" name="depot_ordre"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot_ordre}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
   
    <tr>  
   <td width="7%"><label>nat_lieu </label></td>  
   <td width="93%"  >  
   <input id="nat_lieu_id"       name="nature.nat_lieu_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.nature.nat_lieu_id}"    nextElement="etab_lib"    autofocus   required     />  
   <input id="nat_lieu_libelle"  name="nature.nat_lieu_libelle"     type="text"    size="15"           value="${detailBean.nature.nat_lieu_libelle}"    nextElement="etab_lib"         required     />
  </td>  
   </tr>   
   
   
    <tr>  
   <td width="7%"><label>${etab_id}</label></td>  
   <td width="93%"  > 
    <script type="text/javascript">$(document).ready(function (){LoadAutoCompletAjax("pk_etab.etab_id","etab_lib",null,"list_etablissemment_Gen");});</script>
    <input id="pk_etab.etab_id" name="fk_etab_Bean.pk_etab.etab_id"     type="text"    size="10"    value="${detailBean.fk_etab_Bean.pk_etab.etab_id}"    nextElement="btValidx"              />
    <input id="etab_lib"        name="fk_etab_Bean.etab_lib"           type="text"    size="30"    value="${detailBean.fk_etab_Bean.etab_lib}"           nextElement="btValidx"              />   
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
