 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${avoir_frs_id}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_id"   name="avoir_frs_id"     type="text"    size="25"       maxlength="25"        value="${searchBean.avoir_frs_id}"    nextElement="avoir_frs_date"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${avoir_frs_date}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_date"   name="avoir_frs_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.avoir_frs_date}"    nextElement="avoir_frs_libelle"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${avoir_frs_libelle}</label></td>  
   <td width="93%"  >  
   <input id="avoir_frs_libelle"   name="avoir_frs_libelle"     type="text"    size="30"       maxlength="30"        value="${searchBean.avoir_frs_libelle}"    nextElement="fact_frs_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${fact_frs_id}</label></td>  
   <td width="93%"  >  
   <input id="fact_frs_id"   name="fact_frs_id"     type="text"    size="25"       maxlength="25"        value="${searchBean.fact_frs_id}"    nextElement="type_avoir_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${type_avoir_id}</label></td>  
   <td width="93%"  >  
   <input id="type_avoir_id"   name="type_avoir_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.type_avoir_id}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
