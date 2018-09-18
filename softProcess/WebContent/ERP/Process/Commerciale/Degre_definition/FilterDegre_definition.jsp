 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
    
   
   
   
    <tr>  
   <td width="7%"><label>${ar_id}</label></td>  
   <td width="93%"  > 
   <script type="text/javascript"  >
   $(document).ready(function (){
    LoadAutoCompletAjax("pk_article.ar_id","ar_libelle",null,"list_des_article");
    });
   </script> 
   <input id="pk_article.ar_id" name="pkBean.art_Bean.pk_article.ar_id"               type="text"    size="15"      maxlength="15"        value=""         autofocus         />  
   <input id="ar_libelle" name="pkBean.art_Bean.ar_libelle"     type="text"    size="15"       maxlength="15"        value=""                  />
  </td>  
   </tr>  
   
    <tr>  
   <td width="7%"><label>${carac_id}</label></td>  
   <td width="93%"  > 
   <script type="text/javascript"  >
   $(document).ready(function (){
    //LoadAutoCompletAjax("ar_id","ar_libelle",null,"list_des_article");
    });
   </script> 
   <input id="carac_id" name="pkBean.carac_Bean.carac_id"               type="text"    size="15"      maxlength="15"        value=""                   />  
   <input id="carac_libelle" name="pkBean.carac_Bean.carac_libelle"     type="text"    size="15"       maxlength="15"        value=""                  />
  </td>  
   </tr>   
   
    
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
