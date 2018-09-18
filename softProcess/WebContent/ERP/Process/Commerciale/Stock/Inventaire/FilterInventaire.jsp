 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${inv_id}</label></td>  
   <td width="93%"  >  
   <input id="inv_id"   name="pk.inv_id"     type="text"    size="20"       maxlength="20"        value="${searchBean.pk.inv_id}"    nextElement="inv_date"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${inv_date}</label></td>  
   <td width="93%"  >
   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.pk.inv_date}"   var="WW"/>   
   <input id="inv_date"   name="pk.inv_date"     type="datepicker"    size="13"       maxlength="13"        value="${WW}"    nextElement="type_inv_id"              />  
  </td>  
   </tr>   
    <tr>  
   <td  ><label>${type_inv_id}</label></td>  
   <td    >  
   <script type="text/javascript">
			$(document).ready(function (){
			    LoadAutoCompletAjax_with_marGin("type_inv_id","type_inv_libelle","depot_stock_id","list_TypeInventaire","100","200");
			});
   </script>
   <input id="type_inv_id"          name="typeInventaire.type_inv_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.typeInventaire.type_inv_id}"    nextElement="depot_stock_id"              />
   <input id="type_inv_libelle"     name="typeInventaire.type_inv_libelle"     type="text"    size="100"       maxlength="10"        value="${searchBean.typeInventaire.type_inv_libelle}"    nextElement="depot_stock_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td ><label>${depot_stock_id}</label></td>  
   <td   > 
   <script type="text/javascript">
			$(document).ready(function (){
			    LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle",null,"list_depot_stock","100","200");
			});
   </script> 
   <input id="depot_id" name="pk.depot_stocks.depot_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.pk.depot_stocks.depot_id}"    nextElement="btValidx"              />  
   <input id="depot_libelle" name="pk.depot_stocks.depot_libelle"     type="text"    size="10"       maxlength="10"        value="${searchBeanpk.pk.depot_stocks.depot_libelle}"    nextElement="btValidx"              />
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
