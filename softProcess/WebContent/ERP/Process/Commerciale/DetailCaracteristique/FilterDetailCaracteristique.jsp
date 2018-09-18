 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${carac_id}</label></td>  
   <td width="93%"  >  
   
       
       
      <script type="text/javascript">
    $(document).ready(function() {
     loadSelectAjax("carac_id","list_Carac_for_det_car","carac_id","carac_libelle","${searchBean.pk_det_carac.bean_carac.carac_id}",true);
    });
   </script> 
   
   <select   id="carac_id" name="pk_det_carac.bean_carac.carac_id" autofocus         nextElement="det_carac_id"  ></select>   
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${det_carac_id}</label></td>  
   <td width="93%"  >  
   <input id="det_carac_id"   name="pk_det_carac.det_carac_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.pk_det_carac.det_carac_id}"    nextElement="det_carac_libelle"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${det_carac_libelle}</label></td>  
   <td width="93%"  >  
   <input id="det_carac_libelle"   name="det_carac_libelle"     type="text"    size="100"       maxlength="250"        value="${searchBean.det_carac_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
