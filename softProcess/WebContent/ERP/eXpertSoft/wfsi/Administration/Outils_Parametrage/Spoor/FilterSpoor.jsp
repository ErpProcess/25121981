<%@    include file="/Aceuil/esProcess.jsp"     %>

<script type="text/javascript">
function ControlDisplayTableE(){LoadDataFromServer();} 
</script>
<script src="<%=request.getContextPath()%>/jQuery/JsFilterDataTableAjax.js"  type="text/javascript"></script>
<ext:body     >
<ext:panel  renderTo="ThePageJsp"   border="false"           >
<div   class="divStyleContent"  >  






<table class="tableStyleContent"  cellpadding="5" cellspacing="10"   >
			<tr >
				<td>
				<ext:panel  title="Critere de recherche"  border="true"    autoScroll="true"  collapsible="true" >
				 
				<table cellspacing="3" cellpadding="3" align="center" border="0" style="width: 100%; background: #F1F1F1;">
						<tr>
							<td width="10%"><label>langue</label></td>
							<td width="60%"  >
							 <input id="lang_libelle" name="lang_libelle"   type="text" size="60"  value="${gLangueSearchBean.lang_libelle}"/>
							 <input id="titkg" name="wddd"   type="spiner"    style="width: 250px;"    value=""/>
							</td>
						</tr>
						 
				</table>
				</ext:panel>
				 
			</td>
		</tr>
</table>

			<ext:panel  title="Liste des Langues"   >
<%-- 			<jsp:include page="ListLangue.jsp"></jsp:include> --%>
			</ext:panel>
			
			
			
			
</div>
</ext:panel>
</ext:body>










