 <%@include file="/Aceuil/esProcess.jsp" %> 
 
 <script type="text/javascript">
$(document).ready(function () {
 LoadAutoCompletAjax_with_marGin("nat_lieu_id","nat_lieu_libelle","etab_lib","NatureLieu-List","250","100");
}); 
</script> 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${depot_id}</label></td>  
   <td width="93%"  >  
   <input id="depot_id"   name="depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.depot_id}"    nextElement="depot_libelle"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${depot_libelle}</label></td>  
   <td width="93%"  >  
   <input id="depot_libelle"   name="depot_libelle"     type="text"    size="100"       maxlength="200"        value="${searchBean.depot_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>  
   
      <tr>  
   <td width="7%"><label>nat_lieu </label></td>  
   <td width="93%"  >  
   <input id="nat_lieu_id"       name="nature.nat_lieu_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.nature.nat_lieu_id}"    nextElement="etab_lib"              />  
   <input id="nat_lieu_libelle"  name="nature.nat_lieu_libelle"     type="text"    size="15"           value="${searchBean.nature.nat_lieu_libelle}"    nextElement="etab_lib"               />
  </td>  
   </tr>    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
