 
<script type="text/javascript">

$(document).ready(function () {
 
   

 

var   srfsSource=baseAjaxUrl+"/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Spoor/root.action";





 $("#basbousa").html('');
     $.ajax( {
	 "dataType": 'json',
	 "type": 'POST',
	 "url": srfsSource,
	 "data": 'HiddenAction=i$_ACT_AJAX_GET_DONNE_FORMULAIRE',  
 
	 "success":function(aData){
	  document.getElementById("usd").value= aData.myliste[0].usr_id;
	  document.getElementById("nomm").value= aData.myliste[0].usr_nom;
	  document.getElementById("predd").value= aData.myliste[0].usr_pre;
	  var vhs=""+$("#usd").val()+"&nbsp;/&nbsp;"+$("#nomm").val()+"&nbsp;&nbsp;"+$("#predd").val();
	  
	  
	  document.getElementById("prf_id").value= aData.myliste[0].prf_id;
	  document.getElementById("prf_libelle").value= aData.myliste[0].prf_libelle;
	  
	  $("#basbousa").html(vhs);
	  $("#basbousa2").html($("#prf_libelle").val());
	 
	  
	  //document.getElementById("fct_id").value= aData.myliste[0].fct_id;
	  document.getElementById("fct_libelle").value= aData.myliste[0].fct_libelle;
	  document.getElementById("sousmod_id").value= aData.myliste[0].sousmod_id;
	  document.getElementById("sousmod_libelle").value= aData.myliste[0].sousmod_libelle;
	   $("#basbousa3").html($("#sousmod_libelle").val());
	  }
	 });
});

function resetss(){
alert();
$("#fct_id").val('');
$("#date1").val('');
$("#date2").val('');
$("#op_id").val('');
document.getElementById("ListWindowSpoorED").style.display="none";
}
</script> 
 <ext:body>

<ext:panel  title="Critere de  recherche"  renderTo="SWXD"  border="false"          >
<div   id="SWXD"   ></div>
    
<ext:toolbar  renderTo="SWXqsqsqsqsqsdfzeD"   >
 <ext:toolbar.button   text="Reinialiser"   type="reset"  onClick="resetss()"></ext:toolbar.button>
 <ext:toolbar.separator></ext:toolbar.separator> 
 <ext:toolbar.button   text="chercher"   onClick="cherche()"></ext:toolbar.button>
 </ext:toolbar>
     <div   id="SWXqsqsqsqsqsdfzeD"   ></div>
    
   
    <form  id="myformTrace" >
 <table  width="100%"   >
			<tr >
				<td>
				    
				     <table cellspacing="10" cellpadding="0" align="center" border="0" style="width: 100%; background: #F1F1F1;">
						<tr>
							<td width="7%">Utilisateur</td>
							<td width="20%" >
							<div id="basbousa"></div>
							<input id="usd" name="spoorPKBean.usr_login"   type="hidden" size="20"  value=""/>
							</td>
							<td width="5%">profil</td>
							<td width="20%"  >
							<div id="basbousa2"></div>
							 <input id="nomm" name="nomm"   type="hidden" size="20"  value=""/>
							 <input id="predd" name="predd"   type="hidden" size="20"  value=""/></td>
							<td width="4%"></td>
							<td width="20%"  ><input id="prf_id" name="prf_id"   type="hidden" size="20"  value=""/>
							<input id="prf_libelle" name="prf_libelle"   type="hidden" size="20"  value=""/></td>
						</tr>
						<tr>
						<td width="7%">Entité</td>
						<td colspan="5" ><div id="basbousa3"></div></td>
						</tr>
						
						<tr>
							<td width="7%">Code Opération</td>
							<td width="20%" >
							<div id="basbousa"></div>
							<input id="op_id" name="op_id"   type="text" size="20"  value=""/>
							</td>
							
							<td width="5%">Fonction</td>
							<td width="20%"  >
							<div id="basbousa2"></div>
							 <input id="fct_id" name="fct_id"   type="text" size="20"  value=""/>
							 <input id="fct_libelle" name="fct_libelle"   type="hidden" size="20"  value=""/>
							</td>
							<td width="4%"></td>
							<td width="20%"  ></td>
						</tr>
						
						<tr>
							<td width="7%">date debut </td>
							<td width="20%"  >
							<input id="date1"      name="date1"   type="datepicker" size="20"  value=""/>
							</td>
							
							<td width="4%">date fin</td>
							<td  colspan="3" > 
							<input id="sousmod_id" name="sousmod_id"   type="hidden" size="20"  value=""/>
							<input id="sousmod_libelle" name="sousmod_libelle"   type="hidden" size="20"  value=""/>
							<input id="date2"      name="date2"   type="datepicker" size="20"  value=""/>
							
							</td>
						</tr>
						
	   						
	
					 
				</table>
				 
			</td>
		</tr>
</table>

<div   id="ListWindowSpoorED"  >
 <jsp:include page="ListWindowSpoor.jsp"></jsp:include>
</div>
  
 </form>
 </ext:panel>
 
 </ext:body>

 



