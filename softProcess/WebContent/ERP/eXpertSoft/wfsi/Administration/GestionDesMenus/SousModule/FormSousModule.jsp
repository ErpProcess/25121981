<%@ include file="/Aceuil/esProcess.jsp"      %>
<link   href="<%=request.getContextPath()%>/jQuery/jquery.multiselect.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/jQuery/jquery.multiselect.js"   ></script>
<script  type="text/javascript">
$(document).ready(function (){
LoadAutoCompletAjax("mod_id","mod_libelle","sousmod_libelle","listModulForSouModulCre");       
});
</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"  height="600"  >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
    
     <tr>  
   <td width="7%"><label>${mod_id}</label></td>  
   <td width="93%"  >  
   <input id="mod_id" name="moduleBean.mod_id"     type="text"    size="20"    value="${detailBean.moduleBean.mod_id}"                   nextElement="sousmod_libelle"   autofocus     /> 
   <input id="mod_libelle" name="moduleBean.mod_libelle"     type="text"    size="20"    value="${detailBean.moduleBean.mod_libelle}"    nextElement="sousmod_libelle"  /> 
  S.Module List <input id="module_list" name="module_list"     type="text"    size="20"    value="${detailBean.module_list}"     /> <bR>
  Code  <input id="sousmod_code" name="sousmod_code"     type="text"    size="10"    value="${detailBean.sousmod_code}"     /> <bR>
    
     <script  >
     $(function() { 
     
		         loadSelectAjax("moduSelect","listModulForSouModulCre","mod_id","mod_libelle","${detailBean.module_list}",false);
		         var dataModuleList=$("#module_list").val();
		         const charsModuleList = dataModuleList.split(',');
		         $("#moduSelect").val(charsModuleList);
		          })
		         function  getDataFromMulti(valuoe){
    	          $("#module_list").val($("#moduSelect").val());
                 }
		          </script>
	<select  id="moduSelect"  name="mod_idx"    multiple     onchange="getDataFromMulti(this.value)"    ></select>   
		              
  </td>  
   </tr> 
   
   <tr>  
   <td width="7%"><label>${sousmod_libelle}</label></td>  
   <td width="93%"  >  
   <input id="sousmod_libelle" name="sousmod_libelle"     type="text"    size="20"    value="${detailBean.sousmod_libelle}"    nextElement="sousmod_obs"               />  
  </td>  
   </tr> 
   
     
   <tr>  
   <td width="7%"><label>${sousmod_obs}</label></td>  
   <td width="93%"  >  
   <textarea id="sousmod_obs" name="sousmod_obs"             nextElement="sousmod_action" >${detailBean.sousmod_obs}</textarea>  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${sousmod_action}</label></td>  
   <td width="93%"  >  
   <input id="sousmod_action" name="sousmod_action"     type="text"    size="50"    value="${detailBean.sousmod_action}"    nextElement="ordre"              />  
  </td>  
   </tr>
   
    <tr>  
   <td width="7%"><label>ordre</label></td>  
   <td width="93%"  >  
   <input id="sousmod_ordre" name="sousmod_ordre"     type="text"    size="50"    value="${detailBean.sousmod_ordre}"    nextElement="sousmod_icon"              />  
  </td>  
   </tr>
   
      
   <tr>  
   <td width="7%"><label>${sousmod_icon}</label></td>  
   <td width="93%"  >  
   <input id="sousmod_icon" name="sousmod_icon"     type="text"    size="20"    value="${detailBean.sousmod_icon}"    nextElement="sousmod_table"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${sousmod_table}</label></td>  
   <td width="93%"  >  
   <input id="sousmod_table" name="sousmod_table"     type="text"    size="20"    value="${detailBean.sousmod_table}"    nextElement="soumod_schema"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${soumod_schema}</label></td>  
   <td width="93%"  >  
   <input id="soumod_schema" name="soumod_schema"     type="text"    size="20"    value="${detailBean.soumod_schema}"    nextElement="soumod_schema"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
<script>
$(function () {
    $('select[multiple]').multiselect({
        columns: 4,
        placeholder: 'Select States',
        maxHeight:200,
        search: true,
        searchOptions: {
            'default': 'Search States'
        },
        selectAll: true
    });
});
</script>
