 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${cptbanribrib}</label></td>  
   <td width="93%"  >  
   <input id="cptbanribrib" name="cptbanribrib"     type="text"    size="30"       maxlength="100"     
      value="${detailBean.cptbanribrib}"    nextElement="cptbanribrs"    autofocus         />   
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cptbanribrs}</label></td>  
   <td width="93%"  >  
   <input id="cptbanribrs"   name="cptbanribrs"     type="text"    size="100"       maxlength="100"        value="${searchBean.cptbanribrs}"    nextElement="cptbanadr"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${cptbanadr}</label></td>  
   <td width="93%"  >  
   <input id="cptbanadr"   name="cptbanadr"     type="text"    size="100"       maxlength="100"        value="${searchBean.cptbanadr}"    nextElement="bancod"              />  
  </td>  
   </tr>   
   <tr   ${displayNone}   >  
   <td   width="7%"><label>${bancod}</label></td>  
   <td width="93%"  >  
   <input id="bancod"   name="bancod"     type="text"    size="6"       maxlength="6"        value="${searchBean.bancod}"    nextElement="cptbanjoucod"              />  
  </td>  
   </tr>   
   <tr  ${displayNone} >  
   <td   width="7%"><label>${cptbanjoucod}</label></td>  
   <td width="93%"  >  
   <input id="cptbanjoucod"   name="cptbanjoucod"     type="text"    size="8"       maxlength="8"        value="${searchBean.cptbanjoucod}"    nextElement="cptbancptcom"              />  
  </td>  
   </tr>   
   <tr ${displayNone} >  
   <td width="7%"><label>${cptbancptcom}</label></td>  
   <td width="93%"  >  
   <input id="cptbancptcom"   name="cptbancptcom"     type="text"    size="8"       maxlength="8"        value="${searchBean.cptbancptcom}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
