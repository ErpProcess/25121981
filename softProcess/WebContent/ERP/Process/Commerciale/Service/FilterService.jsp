 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${ser_id}</label></td>  
   <td width="93%"  >  
   <input id="ser_id"   name="ser_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.ser_id}"    nextElement="sitcod"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${sitcod}</label></td>  
   <td width="93%"  >  
   <input id="sitcod"   name="bean_sitcod.sitcod"     type="text"    size="10"       maxlength="10"        value="${searchBean.bean_sitcod.data_id}"    nextElement="ser_lib"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ser_lib}</label></td>  
   <td width="93%"  >  
   <input id="ser_lib"   name="ser_lib"     type="text"    size="100"       maxlength="100"        value="${searchBean.ser_lib}"    nextElement="ar_obs"              />  
  </td>  
   </tr>   
    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
