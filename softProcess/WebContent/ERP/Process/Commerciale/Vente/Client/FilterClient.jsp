 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${clt_lib}</label></td>  
   <td width="93%"  >  
   <input id="clt_lib"   name="clt_lib"     type="text"    size="100"       maxlength="100"        value="${searchBean.clt_lib}"    nextElement="clt_tel"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_tel}</label></td>  
   <td width="93%"  >  
   <input id="clt_tel"   name="clt_tel"     type="text"    size="20"       maxlength="20"        value="${searchBean.clt_tel}"    nextElement="clt_fax"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_fax}</label></td>  
   <td width="93%"  >  
   <input id="clt_fax"   name="clt_fax"     type="text"    size="20"       maxlength="20"        value="${searchBean.clt_fax}"    nextElement="clt_email"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_email}</label></td>  
   <td width="93%"  >  
   <input id="clt_email"   name="clt_email"     type="text"    size="20"       maxlength="20"        value="${searchBean.clt_email}"    nextElement="clt_numcpt"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${clt_numcpt}</label></td>  
   <td width="93%"  >  
   <input id="clt_numcpt"   name="clt_numcpt"     type="text"    size="20"       maxlength="20"        value="${searchBean.clt_numcpt}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
