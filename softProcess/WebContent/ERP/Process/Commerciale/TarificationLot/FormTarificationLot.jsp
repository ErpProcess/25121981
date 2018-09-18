<%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
$(document).ready(function (){
    LoadOtherAutocompletesAjax("pk_article.ar_id","i$_ACT_LOAD_COD_BARRE","pk.code_barre","designation_libelle","type_trf_id");
	LoadAutoCompletAjax_with_marGin("pk.num_serie","detaille_serie","depot_id","list_lot_article_fetch","600","250");
	LoadOtherAutocompletesAjax("pk.num_serie","i$_ACT_LOAD_DEPOT_LOT","depot_id","depot_libelle","type_trf_id");
});

function onChange_select(Xcode2X){
  

if(Xcode2X=="pk.num_serie"){
   var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_INFO_COUT','json',false);
   $("#codetarif").val(json["codetarif"]);
   $("#tarif_unit_achat").val(json["tarif_unit_achat"]);
}
}

function getSuiv(){
if(!teste_required()) return ;

 var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_INFO_ARTICLE','json',false);

$('input[id="pk.code_barre"]').autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#designation_libelle").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});   
$('input[id="pk.code_barre"]').attr("readonly","readonly");
$("#designation_libelle").attr("readonly","readonly");
Ext.getCmp('btnnext').disable(); 
Ext.get('suiteTArif').setStyle('display', 'block');
 
}
</script>

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
  
   
  <ext:panel  border="false"    bodyStyle="background: none;"         >
  <ext:toolbar         toolbarType="bbar"     > 
        <ext:toolbar.button  text=" Suivant  >> "          onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
        
  <table class="tableStyleContent"  cellpadding="5" cellspacing="10"   border="0"   > 
    <tr> 
   <td  ><label>${ar_id}</label></td>  
     <td>
   <input id="pk_article.ar_id" name="pk.vente.fkCode_barre.pk.ar_bean.pk_article.ar_id"     type="hidden"              />
   <input id="pk.code_barre" name="pk.vente.fkCode_barre.pk.code_barre"     type="text"    size="10"       maxlength="100"        value="${detailBean.pk.vente.fkCode_barre.pk.code_barre}"    nextElement="ar_id"        required      />
   </td>
   <td>
   <input id="designation_libelle" name="pk.vente.fkCode_barre.designation_libelle"     type="text"    size="55"       maxlength="100"        value="${detailBean.pk.vente.fkCode_barre.designation_libelle}"    nextElement="ar_id"          required    />
   </td> 
    
   </tr> 
   
     
  </table>  
  </ext:panel>     
  <ext:panel  border="false"    bodyStyle="background: none;"         id="suiteTArif"  >      
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   > 
 
   <tr>  
    <td width="7%"><label>Lot</label></td>  
    <td width="93%"  >  
    <input id="pk.num_serie"   name="pk.lot.pk.num_serie"         type="text"    size="20"          value="${detailBean.pk.lot.pk.num_serie}"         autofocus         />
    <input id="detaille_serie" name="detaille_serie"       type="text"    size="50"               value=""           />  
  </td>  
   </tr> 
   <tr>  
   <td  ><label>coût Primitive</label></td>  
   <td   >  
    <input id="codetarif" name="codetarif"    type="text"    size="10"                               libre  readonly="readonly"  /> 
    <input id="tarif_unit_achat" name="tarif_unit_achat"    type="montant3"    size="17"             libre  readonly="readonly"    /> 
  </td>
    </tr> 
    
    <tr>  
   <td width="7%"><label>Magazin</label></td>  
   <td width="93%"  >  
   <input id="depot_id"   name="pk.lot.pk.depot.depot_id"         		type="text"    size="20"       maxlength="50"        value="${detailBean.pk.lot.pk.depot.depot_id}"    autofocus   />
   <input id="depot_libelle" name="pk.lot.pk.depot.depot_libelle"       type="text"    size="50"       maxlength="50"        value="${detailBean.pk.lot.pk.depot.depot_libelle}"           />  
  </td>  
   </tr>   
    <tr>  
   <td width="9%"><label>${date_trf}</label></td>  
   <td   colspan="6"  >
	   <table width="100%"  border="0"> 
	   <tr>
	   <td  width="15%">
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.pk.vente.date_trf}"   var="WW"/>
	      <input id="date_trf" name="pk.vente.date_trf"   type="datepicker"    size="13"       maxlength="13"       value="${WW} "         />
	   </td> 
	   <td  width="4%"   ></td>   
	   <td ></td>
	   <td width="76%"  ></td></tr>    
	   </table>  
  </td> 
</tr>

  
 
   <tr> 
   
   <tr>
    <td   width="9%" ><label>${type_trf_id}</label></td>  
   <td    width="18%"  >
    <script type="text/javascript">
	    $(document).ready(function() {
	     loadSelectAjax("type_trf_id","list_tyList","type_trf_id","type_trf_lib","${detailBean.pk.vente.groupe.type_trf_id}",false);
	    });
    </script> 
   <select   id="type_trf_id" name="pk.vente.groupe.type_trf_id"        required      libre      nextElement="coef_trf"           ></select>  
  </td>  
  
   <td  width="2%%"  ><label>${tva_id}</label></td>  
   <td  width="67%" colspan="2" > 
	   <script type="text/javascript">
		    $(document).ready(function() {
		     loadSelectAjax("tva_id","list_tvList","tva_id","tva_libelle","${detailBean.pk.vente.tvaBean.tva_id}",false);
		    });
	    </script> 
   <select   id="tva_id"   name="pk.vente.tvaBean.tva_id"           libre      nextElement="coef_trf"           ></select> 
  </td>  
  </tr>
     
  <tr>
  
   <td ><label>${coef_trf}</label></td>  
   <td  colspan="2" >  
   <input id="coef_trf" name="pk.vente.coef_trf"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.pk.vente.coef_trf}"    nextElement="prix_unit_vente"              />  
   </td> 
  
  
  </tr>
  <tr>
  
   <td  ><label>${prix_unit_vente}</label></td>  
   <td   >  
   <input id="tarif_unit_vente" name="pk.vente.tarif_unit_vente"       type="montant3"    size="17"       maxlength="17"        value="${detailBean.pk.vente.tarif_unit_vente}"    nextElement="taux_remise"              />  
  </td> 
  
   <td  ><label>${taux_remise}</label></td>  
   <td   colspan="2"  >  
   <input id="taux_remise" name="pk.vente.taux_remise"     type="text"    size="17"       maxlength="17"        value="${detailBean.pk.vente.taux_remise}"    nextElement="tva_id"              />  
  </td> 
  
  </tr>
 </table>   
</ext:panel>
</ext:panel>
</ext:body>
