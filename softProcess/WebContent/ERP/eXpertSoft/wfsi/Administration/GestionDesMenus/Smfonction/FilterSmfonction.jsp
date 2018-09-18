 <%@ include file="/Aceuil/esProcess.jsp"     %>
<script type="text/javascript">
 $(document).ready(function (){
LoadAutoCompletAjax("sousmod_id","sousmod_libelle","fct_id","listSousModuleForAfection");       
}); 
</script>
<script type="text/javascript">
var dffff_win=$(window).height() - 170;dffff_win=dffff_win+"px";
$(document).ready(function () {   document.getElementById("ThePageJsp").style.maxHeight=dffff_win; height_dTble=250;  });
</script>

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
 <tr>  
		   <td width="7%"><label>${sousmod_id}</label></td>  
		   <td width="93%"  >  
		   <input id="sousmod_id" name="pk.soumBean.sousmod_id"     type="text"    size="5"    value="${searchBean.pk.soumBean.sousmod_id}"    nextElement="fct_id"    autofocus         />  
		   <input id="sousmod_libelle" name="pk.soumBean.sousmod_libelle"     type="text"    size="20"    value="${searchBean.pk.soumBean.sousmod_libelle}"    nextElement="fct_id"              />
		  </td>  
		   </tr> 
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
