<%@    include file="/Aceuil/esProcess.jsp"     %>
 
  <ext:body       >  
  <ext:panel  border="false"    bodyStyle="background: none;"         renderTo="ThePageJsp"   >  
 <ext:panel   border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"      >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"      >  
  <tr>  
   <td width="7%"><label>${fam_id}</label></td>  
   <td width="93%"  >  
  
   <script type="text/javascript">
    $(document).ready(function () {  selectOptionvalue("fam_id","${searchBean.fam_art.fam_id}");  })
   </script>
   
 
   <select id="fam_id" name="fam_art.fam_id"  nextElement="sitcod"    >
    <option  value="">----select-----</option>
     <c:forEach var="famille_arBean"    items="${list_FamilleArticle}"    >
   <option  value="${famille_arBean.fam_id}">${famille_arBean.fam_lib}</option>
   </c:forEach>
   
   </select>
  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ar_id}</label></td>  
   <td width="93%"  >  
   <input id="ar_id" name="pk_article.ar_id"     type="text" size="20"  value="${searchBean.pk_article.ar_id}"     nextElement="arcodbar"    autofocus          />  
  </td>  
   </tr> 
   
   
    <tr>  
   <td width="7%"><label> Référence </label></td>  
   <td width="93%"  >  
   <input id="arcodbar" name="arcodbar"     type="text" size="20"  value="${searchBean.arcodbar}"     nextElement="fam_id"               />  
  </td>  
   </tr> 
   
   
    <tr>  
   <td width="7%"><label>${ar_libelle}</label></td>  
   <td width="93%"  >  
   <input id="ar_libelle" name="ar_libelle"     type="text" size="20"  value="${searchBean.ar_libelle}"    nextElement="btValidx"               />  
  </td>  
   </tr> 
   
              <tr style="display: none;">
              
		        <td width="150px"><label>${soc_id}</label></td>
		        <td  >
		        <script >$(function() { LoadAutoCompletAjax("soc_id","soc_lib","pk_etab.etab_id","listSocioTa");});</script>
		        <input    id="soc_id" name="pk_article.etabBean.pk_etab.soc_bean.soc_id"     size="7"  type="text"   value="${searchBean.pk_article.etabBean.pk_etab.soc_bean.soc_id}"         />
		        <input    id="soc_lib" name="pk_article.etabBean.pk_etab.soc_bean.soc_lib"   size="30"  type="text"   value="${searchBean.pk_article.etabBean.pk_etab.soc_bean.soc_lib}"         />
		        </td>
		      </tr>
		      
		       <tr>
		        <td width="150px"><label>${etab_id}</label></td>
		        <td    >
		        <script >$(function() {LoadOtherAutocompletesAjax("soc_id","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","possede_date_peremption");});</script>
		        <input  id="pk_etab.etab_id" name="pk_article.etabBean.pk_etab.etab_id"  size="7"  type="text"  value="${searchBean.pk_article.etabBean.pk_etab.etab_id}"         />
		        <input  id="etab_lib" name="pk_article.etabBean.etab_lib"   type="text"  size="30"  value="${searchBean.pk_article.etabBean.etab_lib}"         />
		        </td>
		      </tr>
     
  
  
   
    <tr>  
   <td width="7%"><label>${sitcod}</label></td>  
   <td width="93%"  >  
    <script type="text/javascript">
 
   $(document).ready(function() {
			 
		selectOptionvalue("sitcod","${searchBean.bean_sitcod.data_id}");
			 
		})
    
   </script>
   <select id="sitcod" name="bean_sitcod.data_id"       >
   <option value="" >----select-----</option> 
     <c:forEach var="etat_bean"    items="${listUsr_etat}"    >
   <option  value="${etat_bean.data_id}">${etat_bean.data_libelle}</option>
   </c:forEach>
   
   </select>
     
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"  autoScroll="false"    >
<jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
