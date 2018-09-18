<%@    include file="/Aceuil/esProcess.jsp"     %>
<script type="text/javascript">
$(document).ready(function (){
LoadAutoCompletAjax("sousmod_id","sousmod_libelle","pk_entite_admin.ent_id","sousModuleListio");
LoadOtherAutocompletesAjax("sousmod_id","i$_ACT_GET_ID_COLUNMS","pk_entite_admin.ent_id","ent_libelle",null);
});
</script>
<ext:body  >
<ext:panel  border="false"    bodyStyle="background: none;"   title="Critere de recherche"   renderTo="ThePageJsp"   >
<ext:panel  border="false"    bodyStyle="background: none;"       >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >
			 
				
				   <tr>
					 <td width="10%">  <label> Type Langue </label></td>
					 <td >
						 <select name="pk_entite_admin.lang_id"  id="pk_entite_admin.lang_id"   nextElement="sousmod_id"        >
		                   <option value="">---veuillez choisir La lanque---</option>
		                   <c:forEach var="langueBean" varStatus="soduter" items="${listLangueEntite}" >
		                   <option value="${langueBean.lang_id}"   >${langueBean.lang_libelle}</option>
		                   </c:forEach>
		                  </select>
				      </td>
					 
				</tr>
					 <tr>
					   <td width="10%">  <label> Entite</label></td>
					   <td   >
					 	 <input id="sousmod_id"       name="sousmod_id"        nextElement="pk_entite_admin.ent_id"      type="text"     size="5"    value=""      />
	                     <input id="sousmod_libelle"  name="sousmod_libelle"   nextElement="pk_entite_admin.ent_id"      type="text"     size="30"   value=""     />
					   </td>
					 </tr>
					 <tr>
					   <td width="10%">  <label> Colonne</label></td>
					   <td   >
					 	 <input id="pk_entite_admin.ent_id"       name="pk_entite_admin.ent_id"        nextElement="pk_entite_admin.ent_id"      type="text"     size="5"    value=""      />
	                     <input id="ent_libelle"  name="ent_libelle"   nextElement="pk_entite_admin.ent_id"      type="text"     size="30"   value=""     />
					   </td>
					 </tr>	
						 
			 
</table>
</ext:panel>
<ext:panel  title="${nameList}"  id="RET_GRID"   
border="false"   bodyStyle="background: none;" 
 style="display:none;" >
 <jsp:include page="${LIST_VIEW}.jsp" /></ext:panel>
 
</ext:panel>
</ext:body>
<script>
Ext.onReady(function(){
    try {	
      <c:if test="${not empty dataListAajx}">
         Ext.get('RET_GRID').setStyle('display', 'block');
     </c:if>
	    } catch(e){
	    
		}	
});
</script>









