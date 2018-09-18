<%@    include file="/Aceuil/esProcess.jsp"     %>

<ext:body     >
<ext:panel  renderTo="ThePageJsp"   border="false" >
<div   class="divStyleContent"  >  
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"   >
			<tr >
				<td>
				<ext:panel  title="Critere de recherche"  border="true"    autoScroll="true"  collapsible="true" >
				 
				<table cellspacing="10" cellpadding="0" align="center" border="0" style="width: 100%; background: #F1F1F1;">
						<tr>
							<td width="7%"><label>code</label></td>
							<td width="93%"  >
							 <input id="lang_id" name="lang_id"   type="text" size="20"  value="${searchBean.lang_id}"/>
							</td>
						</tr>
						
						<tr>
							<td  ><label>langue</label></td>
							<td   >
							 <input id="lang_libelle" name="lang_libelle"   type="text" size="60"  value="${searchBean.lang_libelle}"/>
							</td>
						</tr>
						
						<tr>
							<td  ><label>Abrv</label></td>
							<td    >
							 <input id="lang_abrv" name="lang_abrv"   type="text" size="60"  value="${searchBean.lang_abrv}"/>
							</td>
						</tr>
						 
				</table>
				</ext:panel>
				 
			</td>
		</tr>
</table>

<ext:panel  title="Liste des Langues" border="false"  autoScroll="true" > <jsp:include page="ListLangue.jsp"></jsp:include>  </ext:panel>
		
</div>
</ext:panel>
</ext:body>










