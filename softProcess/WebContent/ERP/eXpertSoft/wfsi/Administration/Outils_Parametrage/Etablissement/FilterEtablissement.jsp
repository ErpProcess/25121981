<%@    include file="/Aceuil/esProcess.jsp"     %>

<ext:body     >
<ext:panel  renderTo="ThePageJsp"   border="false" >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"   >
			<tr >
				<td>
				<ext:panel  title="Critere de recherche"  border="true"    autoScroll="true"  collapsible="true" >
				 
				<table cellspacing="10" cellpadding="0" align="center" border="0" style="width: 100%; background: #F1F1F1;">
						<tr>
							<td width="7%"><label>code</label></td>
							<td width="93%"  >
							 <input id="pk_etab.etab_id" name="pk_etab.etab_id"   type="text" size="20"  value="${searchBean.pk_etab.etab_id}"/>
							</td>
						</tr>
						
						<tr>
							<td  ><label>Libelle</label></td>
							<td   >
							 <input id="etab_lib" name="etab_lib"   type="text" size="60"  value="${searchBean.etab_lib}"/>
							</td>
						</tr>
						
						<tr>
							<td  ><label>Ordre</label></td>
							<td    >
							 <input id="etab_ordre" name="etab_ordre"   type="text" size="60"  value="${searchBean.etab_ordre}"/>
							</td>
						</tr>
						
						<tr>
							<td  ><label>type</label></td>
							<td    >
							 <script  >$(function() {loadSelectAjax("typeX","list_etab_type","data_id","data_libelle","${searchBean.type.data_id}",true); })</script>
		                 <select     id="typeX"  name="type.data_id"   style="width: 180px;"  ></select>
							</td>
						</tr>
						 
						 
						
		                 
		                 
				</table>
				</ext:panel>
				 
			</td>
		</tr>
</table>
<ext:panel  title="Liste des ${bs.sousmod_libelle}s" border="false" ><jsp:include page="${LIST_VIEW}.jsp"></jsp:include></ext:panel>
</ext:panel>
</ext:body>










