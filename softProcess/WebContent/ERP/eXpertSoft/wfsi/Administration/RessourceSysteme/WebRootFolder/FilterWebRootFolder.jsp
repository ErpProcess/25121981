<%@    include file="/Aceuil/esProcess.jsp"     %>
<ext:body  >
<ext:panel  border="false"    bodyStyle="background: none;"   title="Critere de recherche"   renderTo="ThePageJsp"   >
<ext:panel  border="false"    bodyStyle="background: none;"       >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >
			 
						<tr>
							<td width="7%"><label>code</label></td>
							<td width="93%"  >
							 <input id="usr_id" name="usr_id"     type="text" size="20"  value="${searchBean.usr_id}"/>
							</td>
						</tr>
						<tr>
							<td><label>Nom</label></td>
							<td>
							 <input id="usr_nom" name="usr_nom"   type="text" size="20"  value="${searchBean.usr_nom}"/>
							</td>
						</tr>
						
						<tr>
							<td  ><label>Prenom</label></td>
							<td    >
							 <input id="usr_pre" name="usr_pre"   type="text" size="20"  value="${searchBean.usr_pre}"/>
							</td>
						</tr>
			 
				 
			 
</table>
</ext:panel>
<ext:panel    title="${nameList}"          id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   >
<jsp:include  page="${LIST_VIEW}.jsp" />
</ext:panel>
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


 






