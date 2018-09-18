
 
<ext:body>
<ext:panel      border="false"     renderTo="dialog"  >
<ext:toolbar    >
 <ext:toolbar.button   text="Reinialiser"   type="reset"  onClick="resetss()"></ext:toolbar.button>
 <ext:toolbar.separator></ext:toolbar.separator> 
 <ext:toolbar.button   text="chercher"   onClick="cherche()"></ext:toolbar.button>
</ext:toolbar>
 
         <table  width="100%"   >
			<tr >
				<td>
				   <table cellspacing="10" cellpadding="0" align="center" border="0" style="width: 100%; background: #F1F1F1;">
						<tr>
							<td width="7%"><label>${clt_id}xxxxxxxxxxxxxxx</label> </td>
							<td width="20%" >
							<div id="basbousa"></div>
							<input id="usd" name="spoorPKBean.usr_login"   type="hidden" size="20"  value=""/>
							</td>
							<td width="5%"><label>profil</label></td>
							<td width="20%"  >
							<div id="basbousa2"></div>
							 <input id="nomm" name="nomm"   type="hidden" size="20"  value=""/>
							 <input id="predd" name="predd"   type="hidden" size="20"  value=""/></td>
							<td width="4%"></td>
							<td width="20%"  ><input id="prf_id" name="prf_id"   type="hidden" size="20"  value=""/>
							<input id="prf_libelle" name="prf_libelle"   type="hidden" size="20"  value=""/></td>
						</tr>
						<tr>
						<td width="7%"><label>Entité</label></td>
						<td colspan="5" ><div id="basbousa3"></div></td>
						</tr>
						
						<tr>
							<td width="7%"><label>Code Opération</label></td>
							<td width="20%" >
							<div id="basbousa"></div>
							<input id="op_id" name="op_id"   type="text" size="20"  value=""/>
							</td>
							
							<td width="5%"><label>Fonction</label></td>
							<td width="20%"  >
							<div id="basbousa2"></div>
							 <input id="fct_id" name="fct_id"   type="text" size="20"  value=""/>
							 <input id="fct_libelle" name="fct_libelle"   type="hidden" size="20"  value=""/>
							</td>
							<td width="4%"></td>
							<td width="20%"  ></td>
						</tr>
						
						<tr>
							<td width="7%"><label>date debut </label></td>
							<td width="20%"  >
							<input id="date1"      name="date1"   type="datepicker" size="20"  value=""/>
							</td>
							
							<td width="4%"><label>date fin</label></td>
							<td  colspan="3" > 
							<input id="sousmod_id" name="sousmod_id"   type="hidden" size="20"  value=""/>
							<input id="sousmod_libelle" name="sousmod_libelle"   type="hidden" size="20"  value=""/>
							<input id="date2"      name="date2"   type="text" size="20"  value=""/>
							
							</td>
						</tr>
						
	   						
	
					 
				</table>
				 
			</td>
		</tr>
</table>

 
 
 </ext:panel>
 
  
 </ext:body>
 



