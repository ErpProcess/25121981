 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${code_barre}</label></td>  
   <td width="93%"  >  
   <input id="code_barre"   name="pk.code_barre"     type="text"    size="20"       maxlength="250"        value="${searchBean.pk.code_barre}"    nextElement="ar_id"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ar_id}</label></td>  
   <td width="93%"  > 
   <script type="text/javascript">
      $(document).ready(function (){
LoadAutoCompletAjax("pk_article.ar_id","ar_libelle",null,"list_des_article");
LoadAutoCompletAjax("pk_etab.etab_id","etab_lib",null,"list_des_etab");
});
   </script> 

      <input id="pk_article.ar_id"      name="pk.ar_bean.pk_article.ar_id"          type="text"    size="15"            value=""         />  
      <input id="ar_libelle" name="pk.ar_bean.ar_libelle"     type="text"    size="30"              value=""                  />
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${designation}</label></td>  
   <td width="93%"  >  
   <input id="designation"   name="designation_libelle"     type="text"    size="50"             value="${searchBean.designation_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr> 
   
    <tr>  
   <td width="7%"><label>Etablissement</label></td>  
   <td width="93%"  >  
   <input id="pk_etab.etab_id"   name="pk.ar_bean.pk_article.etabBean.pk_etab.etab_id"     type="text"    size="20"             value="${searchBean.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id}"    nextElement="btValidx"              />
   <input id="etab_lib"     name="pk.ar_bean.pk_article.etabBean.etab_lib"     type="text"    size="45"             value="${searchBean.pk.ar_bean.pk_article.etabBean.etab_lib}"    nextElement="btValidx"              />  
  </td>  
   </tr>
   
   
     
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
