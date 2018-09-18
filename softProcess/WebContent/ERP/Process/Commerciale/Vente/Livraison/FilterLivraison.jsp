 <%@include file="/Aceuil/esProcess.jsp" %>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${liv_id}</label></td>  
   <td width="93%"  >  
   <input id="liv_id"   name="liv_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.liv_id}"    nextElement="liv_libelle"    autofocus         />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${liv_libelle}</label></td>  
   <td width="93%"  >  
   <input id="liv_libelle"   name="liv_libelle"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.liv_libelle}"    nextElement="liv_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${liv_date}</label></td>  
   <td width="93%"  >  
   <input id="liv_date"   name="liv_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.liv_date}"    nextElement="liv_type"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${liv_type}</label></td>  
   <td width="93%"  >  
   <input id="liv_type"   name="liv_type"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.liv_type}"    nextElement="trans_id"              />  
  </td>  
   </tr>   
  <tr>  
   <td width="7%"><label>${trans_id}</label></td>  
   <td width="93%"  >  
   <script type="text/javascript">     
   $(document).ready(function () {
LoadAutoCompletAjax_with_marGin("trans_id"  ,"trans_libelle","liv_date","list_transport_liv","250","100");
});
   
    </script>
   <input id="trans_id" name="trans.trans_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.trans.trans_id}"    nextElement="btValidx"              />
   <input id="trans_libelle" name="trans.trans_libelle"     type="text"    size="10"       maxlength="10"        value="${detailBean.trans.trans_libelle}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
