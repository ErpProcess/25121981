 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${mvt_excep_id}</label></td>  
   <td width="93%"  >  
   <input id="mvt_excep_id"   name="mvt_excep_id"     type="text"    size="19"       maxlength="19"        value="${searchBean.mvt_excep_id}"    nextElement="mvt_excep_date"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mvt_excep_date}</label></td>  
   <td width="93%"  >  
   <input id="mvt_excep_date"   name="mvt_excep_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.mvt_excep_date}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
