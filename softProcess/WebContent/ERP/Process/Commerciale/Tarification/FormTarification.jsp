<%@include file="/Aceuil/esProcess.jsp" %>
<script type="text/javascript">
$(document).ready(function (){
    LoadOtherAutocompletesAjax("pk_article.ar_id","i$_ACT_LOAD_COD_BARRE","pk.code_barre","designation_libelle","type_trf_id");
    loadSelectAjax("tva_id","list_tvList","tva_id","tva_libelle","${detailBean.tvaBean.tva_id}",false);
	loadSelectAjax("type_trf_idX","list_tyList","type_trf_id","type_trf_lib","${detailBean.groupe.type_trf_id}",false);
	loadSelectAjax("mode_cal_puvX","list_mode_cal_puv","data_id","data_libelle","${detailBean.bean_cal.data_id}",true);
});

function control_de_liste(){

if("${bs.fct_id}"=="28")
return doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_VERIF_LIST','text',false);
else
return "";
}

function getSuiv(){
 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";

 var json  = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_INFO_COUT','json',false);
 if(json.tarif_unit_achat==""  &&  (json.datetarif_achat=="" || json.datetarif_achat == undefined)  ) {
 //$('input[id="marge_vente').attr("readonly","readonly") ;
 $('input[id="coef_trf').attr("readonly","readonly") ;
 }else{
 //$('input[id="marge_vente').removeAttr('readonly');
 $('input[id="coef_trf').removeAttr('readonly');
 }
var loula=json.tvaArticle;
loadSelectAjax("tva_id","list_tvList","tva_id","tva_libelle",loula,false);
$('input[id="tva').val(json.tvaArticle);
$('input[id="codetarif').val(json.codetarif);
$('input[id="datetarif_achat').val(json.date_unit_achat);
$('input[id="tarif_unit_achat').val(json.tarif_unit_achat);
$('input[id="date_trf').val(json.date_actu);





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

 Ext.get('bbar_calcul').setStyle('display', 'block');  
 
//$('input[id="tarif_unit_vente').focus(); 
}

function getParam (paramio){
   
    
    $('input[id="coef_trf"]').val('');
	$('input[id="marge_vente"]').val('');
	$('input[id="tarif_vente_remise"]').val('');
	$('input[id="valeur_remise"]').val('');
	$('input[id="tarif_unit_vente"]').val('');
	$('input[id="valeur_de_laTva"]').val('');
	$('input[id="tarif_unit_vente_tt"]').val('');
    
     
  if(paramio=="coef"){
    $('input[id="coef_trf"]').attr('readonly', 'readonly');
	$('input[id="marge_vente"]').attr('readonly', 'readonly');
	$('input[id="tarif_vente_remise"]').attr('readonly', 'readonly');
	$('input[id="valeur_remise"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente"]').attr('readonly', 'readonly');
	$('input[id="valeur_de_laTva"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente_tt"]').attr('readonly', 'readonly');
    $("#coef_trf").removeAttr('readonly');
    } 
    
    if(paramio=="htv"){
    $('input[id="coef_trf"]').attr('readonly', 'readonly');
	$('input[id="marge_vente"]').attr('readonly', 'readonly');
	$('input[id="tarif_vente_remise"]').attr('readonly', 'readonly');
	$('input[id="valeur_remise"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente"]').removeAttr('readonly');
	$('input[id="valeur_de_laTva"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente_tt"]').attr('readonly', 'readonly');
    $("#coef_trf").attr('readonly', 'readonly');
    } 
    
    if(paramio=="mrg"){
    $('input[id="coef_trf"]').attr('readonly', 'readonly');
	$('input[id="marge_vente"]').removeAttr('readonly');
	$('input[id="tarif_vente_remise"]').attr('readonly', 'readonly');
	$('input[id="valeur_remise"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente"]').attr('readonly', 'readonly');
	$('input[id="valeur_de_laTva"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente_tt"]').attr('readonly', 'readonly');
    $("#coef_trf").attr('readonly', 'readonly');
    } 
    
    if(paramio=="ttc"){
    $('input[id="coef_trf"]').attr('readonly', 'readonly');
	$('input[id="marge_vente"]').attr('readonly', 'readonly');
	$('input[id="tarif_vente_remise"]').attr('readonly', 'readonly');
	$('input[id="valeur_remise"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente"]').attr('readonly', 'readonly');
	$('input[id="valeur_de_laTva"]').attr('readonly', 'readonly');
	$('input[id="tarif_unit_vente_tt"]').removeAttr('readonly');
    $("#coef_trf").attr('readonly', 'readonly');
    } 
          
    var codTarif=$('input[id="codetarif"]').val();  
	var tarifUni=$('input[id="tarif_unit_achat"]').val();  
          
    if(codTarif=="" &&  tarifUni==""){
      $('input[id="coef_trf"]').attr('readonly', 'readonly');
	  $('input[id="marge_vente"]').attr('readonly', 'readonly');
    }     
    
   
 }
 
 
 function calculate(){
 
    var varios= $('#mode_cal_puvX').val();
    if(varios=="") {
      alertExt(""," veuillez Entrer le mode de calcul ","4");
      return;
      }
    $('input[id="inputFocus"]').val(varios);
    var json  = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_INFO_VENTE','json',false);
	$('input[id="coef_trf"]').val(json.coef_trf);
	$('input[id="tarif_unit_achat"]').val(json.tarif_unit_achat);
	$('input[id="marge_vente"]').val(json.margeBenefice);
	$('input[id="tarif_vente_remise"]').val(json.tarif_vente_remise);
	$('input[id="valeur_remise"]').val(json.valeur_remise);
	$('input[id="tarif_unit_vente"]').val(json.tarif_unit_vente);
	$('input[id="valeur_de_laTva"]').val(json.valeur_de_laTva);
	$('input[id="tarif_unit_vente_tt"]').val(json.tarif_vente_tt);
   }
   
   
   
</script>

<ext:body  >
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
    <ext:panel  border="false"    bodyStyle="background: none;"         >
      <ext:toolbar         toolbarType="bbar"     >
        <ext:toolbar.button  text=" Suivant  >> "          onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button>
      </ext:toolbar>
      <table class="tableStyleContent"  cellpadding="5" cellspacing="10"   border="0"  align="center" >
        <tr>
          <td width="54"  ><label>Tarif_id</label></td>
          <td colspan="2"><input id="tarif_vente_id" name="tarif_vente_id"     type="text"   readonly="readonly"   libre   value="${detailBean.tarif_vente_id}"      />
          </td>
        </tr>
        <tr>
          <td  ><label>${ar_id}</label></td>
          <td width="180"><input id="inputFocus" name="inputFocus"     type="hidden"        value=""      />
            <input id="pk_article.ar_id" name="fkCode_barre.pk.ar_bean.pk_article.ar_id"     type="hidden"              />
            <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"       size="30"  value="${detailBean.fkCode_barre.pk.code_barre}"         requiredx      />
          </td>
          <td width="376"><input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="55"   value="${detailBean.fkCode_barre.designation_libelle}"          requiredx    />
          </td>
        </tr>
      </table>
    </ext:panel>
    <ext:panel  border="false"    bodyStyle="background: none;"     style="display:none;"   id="suiteTArif"    >
    
      
      
      
      <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"  align="center"  >
        <tr>
          <td    width="15%" ><label>Lot</label></td>
          <td    width="22%"  ><input id="num_serie" name="num_serie"     type="text"         libre  readonly="readonly"   size="22"   value="${detailBean.num_serie}" />
          </td>
          <td    ><label> </label></td>
          <td   ><label>Depot</label></td>
          <td      ><input id="depot.depot_id" name="depot.depot_id"          libre  readonly="readonly"   type="text"    size="7"   value="${detailBean.depot.depot_id}" />
            <input id="depot.depot_libelle" name="depot.depot_libelle"     libre  readonly="readonly"   type="text"    size="25"   value="${detailBean.depot.depot_libelle}" />
          </td>
        </tr>
        <tr>
          <td     ><label>${type_trf_id}</label></td>
          <td     >
          <select   id="type_trf_idX" name="groupe.type_trf_id"        required      libre      nextElement="coef_trf"           ></select>
          </td>
          <td    ><label> </label></td>
          <td   ><label>${date_trf} de vente</label></td>
          <td      ><fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_trf}"   var="WW"/>
            <input id="date_trf"   name="date_trf"   comparedateSys="dateSys"  type="datepicker"    size="13"      required      value="${WW}"         />
          </td>
        </tr>
        
        <tr>
          <td    ><label>Date Coût  Primitive </label></td>
          <td>
             <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.cout.date_prim_trf}"   var="searchat_date"/>
             <input id="datetarif_achat"    name="cout.date_prim_trf"   size="7"  type="text"  value="${searchat_date}"  libre  readonly="readonly" />
           
          </td>
          <td ><label></label></td>
          <td ><label>Coût Primitive</label></td>
          <td ><input id="codetarif"         name="cout.tarif_prim_id"        size="10"  type="hidden"    value="${detailBean.cout.tarif_prim_id}"       libre  readonly="readonly"  />
               <input id="tarif_unit_achat"  name="cout.tarif_unit_article"   size="20"  type="text"   style="text-align: right;"  value="${detailBean.cout.tarif_unit_article}"  libre  readonly="readonly"    />
          </td>
        </tr>
        
        
        <tr>
          <td ><label>Mode de calcul</label></td>
          <td> <select  id="mode_cal_puvX"   name="bean_cal.data_id"  required   style="width: 200px;"  onchange="getParam(this.value)" ></select>  </td>
          <td >    </td>
          <td ><label>Devise</label></td>
          <td >
          	 <script  >
          	 function getDevise(thisValue){
          	  
          	  	
          	           	
          	 if( thisValue=="191"  ||  thisValue=="192"  ){
          	  $(":input[type=montant3]").each(function (cnt, item) {
          	  			$(item).removeClass( "money2" );  
          	            $(item).removeClass( "money_2" );   
						var nbrX=2;
						var ELTs=$(item).val();  
						var text=ELTs; 
						var regex = new RegExp(",", 'g');
						text = text.replace(regex, '');
						var SSSS  =Math.round(text * Math.pow(10,nbrX)) / Math.pow(10,nbrX);
						var numS = new Number(SSSS);
					    var fix = numS.toFixed(nbrX);
						$(item).val(fix); 
						$(item).addClass( "money_2" );
						if($(item).val()=="0.000"  ||  $(item).val()=="0.00"   || $(item).val()=="0.0"  )
						$(item).val("");
				});
				
          	 }else{
          	 
          	           	$(":input[type=montant3]").each(function (cnt, item) { 
          	           	$(item).removeClass( "money2" );  
          	            $(item).removeClass( "money_2" ); 
					    var nbrX=3;
						var ELTs=$(item).val();  
						var text=ELTs; 
						var regex = new RegExp(",", 'g');
						text = text.replace(regex, '');
						var SSSS  =Math.round(text * Math.pow(10,nbrX)) / Math.pow(10,nbrX);
						var numS = new Number(SSSS);
					    var fix = numS.toFixed(nbrX);
						$(item).val(fix); 
						$(item).addClass( "money2" );
						if($(item).val()=="0.000"  ||  $(item).val()=="0.00"   || $(item).val()=="0.0"  )
						$(item).val("");
				});
				
          	 }
          	 
          	 }
          	 $(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",true); })</script>
		     <select  onchange="getDevise(this.value)"  required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select>
          </td>
        </tr>
        
        
        <tr>
          <td ><label>Coef.</label>
            <input id="coef_trf" name="coef_trf"     readonly="readonly"        type="number"   style="width: 90px;float: right;"         value="${detailBean.coef_trf}"    nextElement="marge_vente"            />
            <label>%</label>
          </td>
          <td   ><label>Marge</label>
            <input id="marge_vente" name="marge_vente"  libre   readonly="readonly"    type="montant3"    size="15"        value="${detailBean.marge_vente}"               />
          </td>
          <td    style="background-color: #fafafa;text-align: center;text-align: center;"><label style="font-size: 30px;color: green;"> + </label></td>
          <td  ><label> ${prix_unit_vente}</label></td>
          <td   ><input id="tarif_unit_vente" name="tarif_unit_vente"    required    type="montant3"    size="20"              value="${detailBean.tarif_unit_vente}"    nextElement="taux_remise"              />
          </td>
        </tr>
        <tr>
          <td  ><label>${taux_remise}</label>
            <input id="taux_remise" name="taux_remise" type="number"    style="width: 60px;float: right;"  value="${detailBean.taux_remise}"   nextElement="valeur_remise"              />
            <label>%</label>
          </td>
          <td   ><label>Valeur</label>
            <input id="valeur_remise"   name="valeur_remise"   size="15"     type="montant3"      value=""    readonly="readonly"   libre  >
          </td>
          <td    style="background-color: #fafafa;text-align: center;"><label style="font-size: 30px;color: red;"> - </label>
          </td>
          <td  ><label> ${prix_unit_vente}.H.T.NET </label></td>
          <td  ><input id="tarif_vente_remise" name="tarif_vente_remise"   required   libre   readonly="readonly"    type="montant3"    size="20"               value="${detailBean.tarif_vente_remise}"                    />
          </td>
        </tr>
        <tr>
          <td  ><label>${tva_id}</label>
            <select   id="tva_id"   name="tvaBean.tva_id"       required    libre      nextElement="coef_trf"   style="float: right;"       >
            </select></td>
          <td   ><label>Valeur</label>
            <input id="valeur_de_laTva"   name="valeur_de_laTva"   size="15"     type="montant3"      value=""    readonly="readonly"   libre  >
          </td>
          <td     style="background-color: #fafafa;text-align: center;"><label style="font-size: 30px;color: green;"> + </label></td>
          <td  ><label>${prix_unit_vente}.T.T</label>
          </td>
          <td  ><input id="tarif_unit_vente_tt" name="tarif_unit_vente_tt"  required   type="montant3"  size="20"    nextElement="tarif_unit_vente_tt"  value="${detailBean.tarif_unit_vente_tt}"   />
          </td>
        </tr>
      </table>
    </ext:panel>
    
    <ext:toolbar         toolbarType="bbar"     style="display:none;"   id="bbar_calcul"     >
        <ext:toolbar.button  text="Calculer"          onClick="calculate()"   id="btnnextXc"  ></ext:toolbar.button>
      </ext:toolbar>
      
      
      
  </ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	    
    
 <c:if test="${bs.fct_id !=28  }">    
 Ext.getCmp('btnnext').disable();   
 Ext.get('suiteTArif').setStyle('display', 'block');  
  
 
 
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
 getDevise("${detailBean.devise.dev_id}"); 
 calculate('brute'); 
 
  </c:if>           
  } catch(e){   	
  	}	         
  	});           
</script>
