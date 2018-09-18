<%@ include file="/Aceuil/esProcess.jsp"     %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${fam_id}</label></td>  
   <td width="93%"  >  
   <input id="fam_id" name="fam_id"     type="text"    size="20"    value="${detailBean.fam_id}"    nextElement="fam_lib"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fam_lib}</label></td>  
   <td width="93%"  >  
   <input id="fam_lib" name="fam_lib"     type="text"    size="20"    value="${detailBean.fam_lib}"    nextElement="fam_ordre"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fam_ordre}</label></td>  
   <td width="93%"  >  
   <input id="fam_ordre" name="fam_ordre"     type="text"    size="20"    value="${detailBean.fam_ordre}"    nextElement="fam_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fam_type}</label></td>  
   <td width="93%"  > 
   <script type="text/javascript">
   $(document).ready(function (){LoadAutoCompletAjax_with_marGin("typefam_id","typefam_lib","fam_obs","typeFamilleArticle","400","500");});
   </script>
   <input id="typefam_id"  name="type.typefam_id"     type="text"    size="20"    value="${detailBean.type.typefam_id}"    nextElement="fam_obs"              /> 
   <input id="typefam_lib" name="type.typefam_lib"     type="text"    size="20"    value="${detailBean.type.typefam_lib}"    nextElement="fam_obs"              />  
  </td>  
  
   </tr>   
   <tr>  
   <td width="7%"><label>${fam_obs}</label></td>  
   <td width="93%"  >  
   <input id="fam_obs" name="fam_obs"     type="text"    size="20"    value="${detailBean.fam_obs}"    nextElement="tvacod"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${tvacod}</label></td>  
   <td width="93%"  >  
   <input id="tvacod" name="tvacod"     type="text"    size="20"    value="${detailBean.tvacod}"    nextElement="sitcod"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${sitcod}</label></td>  
   <td width="93%"  >  
   <input id="sitcod" name="sitcod"     type="text"    size="20"    value="${detailBean.sitcod}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
