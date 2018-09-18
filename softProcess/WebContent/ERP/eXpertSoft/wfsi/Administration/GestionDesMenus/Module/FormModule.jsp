<%@    include file="/Aceuil/esProcess.jsp"     %>  
 
<script  type="text/javascript">
$(document).ready(function (){
LoadAutoCompletAjax("spack_id","spack_libelle","mod_libelle","listSouPakformOdule");       
});
</script>
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   
   <tr>  
   <td width="7%"><label>${spack_id}</label></td>  
   <td width="93%"  >  
   <input id="spack_id" name="sousPackBean.spack_id"     type="text"    size="20"    value="${detailBean.sousPackBean.spack_id}"    nextElement="mod_libelle"     autofocus   required        /> 
   <input id="spack_libelle" name="sousPackBean.spack_libelle"     type="text"    size="20"    value="${detailBean.sousPackBean.spack_libelle}"    nextElement="mod_libelle"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mod_libelle}</label></td>  
   <td width="93%"  >  
   <input id="mod_libelle" name="mod_libelle"     type="text"    size="20"    value="${detailBean.mod_libelle}"    nextElement="mod_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mod_obs}</label></td>  
   <td width="93%"  >  
   <input id="mod_obs" name="mod_obs"     type="text"    size="20"    value="${detailBean.mod_obs}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
