<%@    include file="/Aceuil/esProcess.jsp"     %>
<c:set var="pathRoot" value="<%=request.getContextPath() %>" ></c:set>
 
 
<ext:body >
<ext:panel  renderTo="ThePageJsp"   border="false"           >
<div   class="divStyleContent"  >  



<table class="tableStyleContent"  cellpadding="5" cellspacing="10"    >
			<tr  >
				<td>
				<ext:panel  title="Critere de recherche"  border="true"    autoScroll="true"  collapsible="true" >
				 
				<table cellspacing="10" cellpadding="3" align="center" border="0" style="width: 100%; background: #F1F1F1;">
				
				       <tr>
							<td width="10%">  <label> Type Langue </label></td>
							<td width="20%"  >
								 <select name="idLiblleBean.lang_id"  id="idLiblleBean.lang_id"           >
				                   <option value="">---veuillez choisir La lanque---</option>
				                   <c:forEach var="langueBean" varStatus="soduter" items="${listLangue}" >
				                   <option value="${langueBean.lang_id}"   >${langueBean.lang_libelle}</option>
				                   </c:forEach>
				                  </select>
                  
							</td>
							<td  > <label>Code Libelle</label> </td>
							<td >  <input id="idLiblleBean.lib_id" name="idLiblleBean.lib_id"   type="text" size="20"  value=""/>  </td>
						</tr>
						
						<tr>
							<td ><label>Libelle</label>  </td>
							<td ><input id="lib_libelle" name="lib_libelle"   type="text" size="30"  value=""/> </td>
							<td width="5%"><label>Abrv</label></td>
							<td width="60%"  >
							 <input id=" lib_abrv" name=" lib_abrv"   type="text"    value=""/>
							</td>
						</tr>
				</table>
				</ext:panel>
				 
			</td>
		</tr>
</table>

<ext:panel  title="Liste des libelles"   >
<jsp:include page="ListLibelle.jsp"></jsp:include>
</ext:panel>
</div>
</ext:panel>
</ext:body>










