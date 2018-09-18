 <%@include file="/Aceuil/esProcess.jsp" %>  
 
    <script type="text/javascript">$(document).ready(function (){
loadSelectAjax("nature_mvt_id","list_mvt_commerciale_Stock","nature_mvt_id","nature_mvt_libelle","${searchBean.pk.nat_mvt.nature_mvt_id}",true);
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
   <td width="7%"><label>${date_mvt_stock}</label></td>  
   <td width="93%"  >  
   <input id="date_mvt_serie"   name="date_mvt_serie"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_mvt_serie}"    nextElement="document_com_id"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${document_com_id}</label></td>  
   <td width="93%"  >  
   <input id="document_com_id"   name="pk.document_com_id"     type="text"    size="25"       maxlength="25"        value="${searchBean.pk.document_com_id}"    nextElement="inv_date"              />  
  </td>  
   </tr>   
   
       
    
  <tr>  
   <td width="9%"><label>Article</label></td>  
   <td width="93%"  >  
    <input      type="text"     name="pk.serieBean.fkCode_barre.pk.code_barre"                id="pk.code_barre"         value="${searchBean.pk.serieBean.fkCode_barre.pk.code_barre}"         style="width: 150px;"      >
    <input      type="text"     name="pk.serieBean.fkCode_barre.designation_libelle"          id="designation_libelle"   value="${searchBean.pk.serieBean.fkCode_barre.designation_libelle}"         style="width: 300px;"      >
  </td>  
   </tr>   
  
    
     <tr>  
   <td width="9%"><label>${depot_id}</label></td>  
   <td width="93%"  >
  
   <input      id="depot_id"   name="depot.depot_id"     type="text"    size="10"       maxlength="10"    style="width: 150px;"    value="${searchBean.depot.depot_id}"                />
   <input      id="depot_libelle"   name="depot.depot_libelle"     type="text"    size="10"      style="width: 300px;"     maxlength="10"        value="${searchBean.depot.depot_libelle}"               />  
  </td>  
   </tr>  
  
   <tr>  
   <td width="7%"><label>${nature_mvt_id}</label></td>  
   <td width="93%"  > 
   
   <select  id="nature_mvt_id"   name="pk.nat_mvt.nature_mvt_id"   nextElement="montant_ht"      ></select> 
    
  </td>  
   </tr>   
  
    
     
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
