 <%@include file="/Aceuil/esProcess.jsp" %>  
 <script type="text/javascript">
   $(document).ready(function(){
     loadSelectAjax("tva_id","list_tva_List","tva_id","tva_libelle","${searchBean.tvaBean.tva_id}",true);
     loadSelectAjax("grp_prim_trf_id","listGrpTarificationPrtvArticle","grp_prim_trf_id","grp_trf_lib","${searchBean.groupe.grp_prim_trf_id}",false);
     LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","grp_prim_trf_id","list_article_TarificationPrtvArticle","400","500");
   });
</script>	
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   
   <tr>
<td width="9%"><label>Tarif Id</label></td>
<td>
<input id="tarif_prim_id" name="tarif_prim_id"   type="text"    size="13"       maxlength="13"       value="${searchBean.tarif_prim_id}"        nextElement="date_prim_trf"          />
</td>
</tr>
<tr>  
   <td width="9%"><label>Date Tarif Achat</label></td>  
   <td      >
   <input id="date_prim_trf" name="date_prim_trf"   type="datepicker"    size="13"       maxlength="13"       value="${searchBean.date_prim_trf}"         />     
   </td> 
</tr>
<tr> 
<td  ><label>référence</label></td>  
<td   >  
   <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="10"       maxlength="100"        value="${searchBean.fkCode_barre.pk.code_barre}"    nextElement="ar_id"              />
   <input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="55"       maxlength="100"        value="${searchBean.fkCode_barre.designation_libelle}"    nextElement="ar_id"              />
</td>  
</tr>   
<tr> 
<td   width="9%" ><label>groupe Tarif</label></td>  
   <td    width="18%"  >
   <select   id="grp_prim_trf_id" name="groupe.grp_prim_trf_id"        required      libre             ></select>  
  </td>  
 
  </tr>
 
 

     
<tr>  
<td  ><label>Tarif Unit</label></td>  
<td   >  
<input id="tarif_unit_article" name="tarif_unit_article"     type="montant3"    size="17"       maxlength="17"     value="${searchBean.tarif_unit_article}"    nextElement="type_trf_id"              />  
</td>
</tr>

  
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
