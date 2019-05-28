 <%@include file="/Aceuil/esProcess.jsp" %> 
   <script type="text/javascript">$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","depot_id","list_article_code_barre","400","500");
LoadAutoCompletAjax_with_marGin('depot_id','depot_libelle','num_lot','list_depot_stock','250','100');
LoadAutoCompletAjax_with_marGin('num_lot','date_peremption','btValidx','list_Lot','250','100');
});
</script> 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
 
 
 
 
 
   <tr>
   
   <td width="9%"><label>${date_stock} début</label></td>  
   <td width="93%"  >
    <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.pk.date_stock}"   var="searchBeanpkdate_stock"/>
    <input id="date_stock"   name="pk.date_stock"     type="datepicker"    size="13"       maxlength="13"        value="${searchBeanpkdate_stock}"    nextElement="depot_id"    autofocus    required     />
  </td>  
   </tr> 
   
 
   
    <tr>  
   <td width="9%"><label>Article</label></td>  
   <td width="93%"  >  
  
    <input      type="text"     id="pk.code_barre"                name="pk.fkCode_barre.pk.code_barre"         value="${searchBean.pk.fkCode_barre.pk.code_barre}"         style="width: 150px;"      >
    <input      type="text"     id="designation_libelle"          name="pk.fkCode_barre.designation_libelle"   value="${searchBean.pk.fkCode_barre.designation_libelle}"         style="width: 300px;"      >
  </td>  
   </tr>   
  
   <tr>  
   <td width="9%"><label>${depot_id}</label></td>  
   <td width="93%"  >
  
   <input      id="depot_id"   name="pk.depot.depot_id"     type="text"    size="10"       maxlength="10"    style="width: 150px;"    value="${searchBean.pk.depot.depot_id}"         required       />
   <input      id="depot_libelle"   name="pk.depot.depot_libelle"     type="text"    size="10"      style="width: 300px;"     maxlength="10"        value="${searchBean.pk.depot.depot_libelle}"    required           />  
  </td>  
   </tr>   
   

   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="ListStock_articleParDate.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
