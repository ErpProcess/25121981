<%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
   $(document).ready(function(){
     loadSelectAjax("tva_id","list_tva_List","tva_id","tva_libelle","${detailBean.tvaBean.tva_id}",true);
     loadSelectAjax("mode_calculX","list_mode_calcul_cout_unit","data_id","data_libelle","${detailBean.mode_cal.data_id}",true);
     loadSelectAjax("grp_prim_trf_id","listGrpTarificationPrtvArticle","grp_prim_trf_id","grp_trf_lib","${detailBean.groupe.grp_prim_trf_id}",false);
     LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","grp_prim_trf_id","list_article_TarificationPrtvArticle","400","500");
   });
   function getParam (paramio){
    $("#tarif_unit_article").val('');
    $("#tarif_unit_ttc").val('');
    $("#valeur_tva").val('');
  if(paramio=="TT"){
    $("#tarif_unit_article").attr('readonly', 'readonly');
    $("#tarif_unit_ttc").removeAttr('readonly');
    }else{
    $("#tarif_unit_ttc").attr('readonly', 'readonly');
    $("#tarif_unit_article").removeAttr('readonly');
    }
 
 }
 
   function  calulate_pu(){
    var hkeya= $('#mode_calculX').val();
    
    
    if(hkeya=="") {
      alertExt("","Entrer le mode ","4");
      return;
      }
    $('input[id="inputFocus"]').val(hkeya);
    var json  = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCULATE_PU','json',false);
	$('input[id="tarif_unit_article"]').val(json.tarif_unit_article);
	$('input[id="valeur_tva"]').val(json.valeur_tva);
	$('input[id="tarif_unit_ttc"]').val(json.tarif_unit_ttc);
   }
   
</script>	    
	    
<ext:body  >  
<ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"    >  
<ext:toolbar         toolbarType="bbar"    > 
        <ext:toolbar.button  text="Calculer"      onClick="calulate_pu()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
      
        
<table width="58%"  border="0"     cellpadding="5"        cellspacing="10"      class="tableStyleContent"     id="tblData"   > 
 
<tr>
<td width="17%"><label>Tarif Id</label></td>
<td>

<input id="inputFocus" name="inputFocus"   type="hidden"  value="" >
<input id="tarif_prim_id" name="tarif_prim_id"   type="text"    size="13"       maxlength="13"       value="${detailBean.tarif_prim_id}"    libre    readonly="readonly"     />
</td>
</tr>
<tr>  
   <td width="17%"><label>Date Tarif Achat</label></td>  
   <td      >
   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_prim_trf}"   var="WW"/>
   <input id="date_prim_trf" name="date_prim_trf"    type="datepicker"      compareTo="dateSys"    size="13"       maxlength="13"       value="${WW}"    required      />     
   </td> 
</tr>


 <tr> 
	<td  ><label>référence</label></td>  
	<td   >  
     <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="10"       maxlength="100"        value="${detailBean.fkCode_barre.pk.code_barre}"    nextElement="ar_id"      required        />
     <input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="55"       maxlength="100"        value="${detailBean.fkCode_barre.designation_libelle}"    nextElement="ar_id"     required         />
	</td>  
 </tr>  
 
<tr> 
<td   width="17%" ><label>groupe Tarif</label></td>  
   <td    width="83%"  >
   <select   id="grp_prim_trf_id" name="groupe.grp_prim_trf_id"        required      libre              ></select>  
  </td>  
 
  </tr>
 
 <tr>  
<td  ><label>tva_id</label></td>  
<td   >  
 <select   id="tva_id"   name="tvaBean.tva_id"                required           ></select> 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  <label>Mode</label>
  <select   id="mode_calculX"   name="mode_cal.data_id"     onchange="getParam(this.value);"      required                 ></select>
</td>
</tr> 

 
  
<tr>  
<td  ><label>Tarif Unit.H.T</label></td>  
<td   >  
<input id="tarif_unit_article" name="tarif_unit_article"     type="montant3"    size="17"     libre  readonly="readonly"     value="${detailBean.tarif_unit_article}"      nextElement="valeur_tva"    required          />  
</td>
</tr>

<tr>  
<td  ><label>valeur Tva</label></td>  
<td   >  
<input id="valeur_tva" name="valeur_tva"     type="montant3"    size="17"         value="${detailBean.valeur_tva}"    libre  readonly="readonly"  nextElement="tarif_unit_ttc"        />  
</td>
</tr>


<tr>  
<td  ><label>Tarif Unit.TTC</label></td>  
<td   >  
<input id="tarif_unit_ttc" name="tarif_unit_ttc"     type="montant3"    size="17"      libre  readonly="readonly"   required     value="${detailBean.tarif_unit_ttc}"              nextElement="valeur_tva"       />  
</td>
</tr>


  
 </table>   
</ext:panel>
</ext:body>
