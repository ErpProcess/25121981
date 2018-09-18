<%@    include file="/Aceuil/esProcess.jsp"     %>
 <script  type="text/javascript">
$(document).ready(function (){
LoadAutoCompletAjax("pack_id","pack_libelle","spack_libelle","listPackgefromPack");       
});
</script>
   
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${pack_id}</label></td>  
   <td width="93%"  >  
   <input id="pack_id"      name="packageSys.pack_id"     type="text" size="20"  value="${detailBean.packageSys.pack_id}"/> 
   <input id="pack_libelle" name="packageSys.pack_libelle"     type="text" size="20"  value="${detailBean.packageSys.pack_libelle}"/>  
  </td>  
   </tr>   
  
   
   <tr>  
   <td width="7%"><label>${spack_libelle}</label></td>  
   <td width="93%"  >  
   <input id="spack_libelle" name="spack_libelle"     type="text" size="20"  value="${detailBean.spack_libelle}"/>  
  </td>  
   </tr>   
   
   
   <tr>  
   <td width="7%"><label>${spack_obs}</label></td>  
   <td width="93%"  >  
   <input id="spack_obs" name="spack_obs"     type="text" size="20"  value="${detailBean.spack_obs}"/>  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${spack_action}</label></td>  
   <td width="93%"  >  
   <input id="spack_action" name="spack_action"     type="text" size="20"  value="${detailBean.spack_action}"/>  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${spack_ordre}</label></td>  
   <td width="93%"  >  
   <input id="spack_ordre" name="spack_ordre"     type="number" size="20"  value="${detailBean.spack_ordre}"/>  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
