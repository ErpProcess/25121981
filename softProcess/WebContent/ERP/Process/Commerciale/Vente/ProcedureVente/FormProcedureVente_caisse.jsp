<%@include file="/Aceuil/esProcess.jsp" %> 
<script type="text/javascript">

config_header_foot_tableJQuey ='<"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lf<"toolbar_es">r>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>';
contenu_toolbarJQuey          ='';
height_tabbJQuey="300px";
width_tabbJQuey="100%";
</script>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


<script type="text/javascript">
var mapEditableGen = {            
		"otab"   :oTable,
        "table"  :"GRID_SAISIE_DETAIL_VENTE",
        "list"   :"list_editable_proVente",
        "id_name":"pk.fkcode_barre.pk.code_barre",
        "url"    :"${urlloadDataTableAjax}",
        "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
        "mapCol" :[ 
				       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false , "bVisible": false }, 
				       
				       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
				              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[2]+'"   >';}}, 
				                  
				       {      "sName": "pk.fkcode_barre.pk.code_barre"      , "bVisible": false   }, 
					         
					   {      "sName": "info"    ,"sWidth": "40%"   , "bSortable": true  ,"bSearchable": true , "bVisible": true  },   
					   
					   {      "sTitle":"Prix U"    , "sName": "tarif.tarif_unit_vente_tt"   ,"sWidth": "5%"    ,"sClass" : "alignRight"       , "bSortable": true 
                           , "mRender":    function (data, type, full) {
                        	   return '<input   type="montant3"      style="width:100%;"     id=tarif_unit_vente_tt'+full[0]+'       name=tarif.tarif_unit_vente_tt        value="'+formatNumberJsXC(data,3)+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")      >'; 
                          	   } 
					   },
					                 
					   {      "sName": "quantite"           ,   "bSortable": true       , "sWidth": "5%"        ,"mRender": function( data, type, full){  
					          return '<input   type="number"      style="width:70px;"     id=quantite'+full[0]+'       name=quantite        value="'+data+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")        >'; }},   

                       {     "sName": "montant_ttc_vente"       ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
                             ,"mRender":    function (data, type, full) {   if( $("#devX").val()=="191"  ||  $("#devX").val()=="192")   return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },         
                           
					   {     "sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
                         ]
};


function  loadgrid(){
	 
	if(otab_otra!=null &&   otab_otra!= undefined)
		 otab_otra.fnAdjustColumnSizing();


  } 

function doLoaderDataFooter( nRow,aData, iStart, iEnd){
    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
    if(json===null  || json===undefined ) return;
    var	listTva= json.list_tva ;
    var	listTotal = json.list_total ;
    $('#vente_remise_alacaisse').val(json.vente_remise_alacaisse);
    $('#vente_remise').val(json.vente_remise);
    $('#vente_mnt_net_a_payer').val(json.vente_mnt_net_a_payer);
    $('#vente_mnt_brute_ht').val(json.vente_mnt_brute_ht);
    $('#vente_mnt_net_ht').val(json.vente_mnt_net_ht);
    $('#vente_mnt_tva').val(json.vente_mnt_tva);
    $('#vente_mnt_total').val(json.vente_mnt_total);
    $('#montant_vente_recu').val(json.montant_vente_recu);
    $('#montant_vente_rendu').val(json.montant_vente_rendu);
   // $('#vente_mnt_total_reel').val(json.vente_mnt_total_reel);
    
     var footX ={} ;
     if(listTva!=null &&  listTva!= undefined ){
	     for (var h = 0; h <listTva.length; h++) {
	     var foot ={} ;
		        if(h==0){
		         foot[listTva[h].td1] = listTva[h].value1;
		         footX["AA"+h]=foot;
		         continue;
                }
             	foot[listTva[h].td1] = listTva[h].value1;
             	foot[listTva[h].td2] = listTva[h].value2;
             	foot[listTva[h].td3] = listTva[h].value3;
             	footX["AA"+h]=foot;
	     }
	     
	     
	     for (var p = listTva.length ; p<6; p++) {
	      var foot = {} ;
	        foot["0"] = "";
         	footX["UU"+p]=foot;
	     } 
	     
    }  
 if(listTotal!=null &&  listTotal!= undefined ){   
	 for (var x = 0; x <listTotal.length; x++) {
	     var foot ={} ;
         foot[listTotal[x].td1] = listTotal[x].value1;
         foot[listTotal[x].td2] = listTotal[x].value2;
         footX["BB"+x]=foot;
	     }   
 }
    
        
return  footX; 
}


$(document).ready(function () {


 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
		 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
if(custumMessageBoxo!=""  &&  custumMessageBoxo !="Facturation effectu�e avec succ�s"  &&  custumMessageBoxo !="Confirmation effectu�e avec succ�s"  ){

var messageBoxx='Confirmer';

if("${bs.fct_id}"=="27")
messageBoxx='Facturer';

Ext.MessageBox.show({
           title:'Enregistrement Valider',
           msg: custumMessageBoxo,
           buttons: {ok:messageBoxx,no:'Retour'}  ,
           fn: getActionBox,
           animateTarget: 'mb4',
           icon: Ext.MessageBox.QUESTION
       });
}  

if(custumMessageBoxo!=""  &&  custumMessageBoxo =="Confirmation effectu�e avec succ�s"){

	Ext.MessageBox.show({
        title:'Imprimer Bon de Livraison',
        msg: custumMessageBoxo,
        buttons: {ok:'Imprimer BL',no:'Retour'}  ,
        fn: getActionImprimerBL,
        animateTarget: 'mb4',
        icon: Ext.MessageBox.QUESTION
    });
	}  





if(custumMessageBoxo!="" &&  custumMessageBoxo=="Facturation effectu�e avec succ�s"){
	Ext.MessageBox.show({
        title:'Imprimer Facture',
        msg: custumMessageBoxo,
        buttons: {ok:'Imprimer',no:'Retour'}  ,
        fn: getActionImprimer,
        animateTarget: 'mb4',
        icon: Ext.MessageBox.QUESTION
    });
	
}

});

function getActionImprimer(btn){
 
     if( btn=="no" ){
         var hidvente="i$_ACT_RESET_FORM";
	 $("#myformToServeur").find('input[name="HiddenAction"]').val(hidvente);
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit();
     }else{
         var url = contexPath+"${tmlx.url}?HiddenAction=i$_ACT_PRINT_FACTURE_FROM_VENTE";
     	 genericPdfProcess(url);  
     }
}


function getActionImprimerBL(btn){
	 
    if( btn=="no" ){
        var hidvente="i$_ACT_RESET_FORM";
	 $("#myformToServeur").find('input[name="HiddenAction"]').val(hidvente);
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
    $("#myformToServeur").submit();
    }else{
        var url = contexPath+"${tmlx.url}?HiddenAction=i$_ACT_PRINT_PDF_DETAILLE";
    	 genericPdfProcess(url);  
    }
}


var numrReserveFact=null;
function getNumFact(GnumoFact){
	numrReserveFact=GnumoFact;
}

function getActionBox(btn){
    
      var hidvente="i$_ACT_COMMIT";
      
      
     if("${bs.fct_id}"=="27") {
    	 hidvente="i$_ACT_FACTURER"; 
    	 var  verifNumFac =doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_VERIF_LIST_FACT','text',false);

		  if(verifNumFac!="" &&  verifNumFac.startsWith("�") )  {
			  var tabOfNumeroFact = verifNumFac.split("�");
	 
			  var maselectFact='<select onchange="getNumFact(this.value);" >';
			  for (var z = 0; z < tabOfNumeroFact.length ; z++) {
				 if(tabOfNumeroFact[z]!==null &&  tabOfNumeroFact[z]!==undefined    &&   tabOfNumeroFact[z]!== ""  ){
					 maselectFact+= '<option  value="'+tabOfNumeroFact[z]+'">'+tabOfNumeroFact[z]+'</option>';
					 if(numrReserveFact==null)numrReserveFact=tabOfNumeroFact[z];
			       }
			   }
			  maselectFact+='</select>';
			  Ext.MessageBox.show({
		      title:'INFO',
		      msg: ' Voulez vous Choisir un Num�ro D�ja Supprim� : '+maselectFact,
		      buttons: {ok:'Ancien Num�ro',no:'Nouveau Num�ro'}  ,
		      fn: function (btn){
		    	  
		    	  if (btn == 'ok') actFactoData(numrReserveFact); else actFactoData(null);
		  	   },
		      animateTarget: 'mb4',
		      icon: Ext.MessageBox.QUESTION
		 });
			  return;  
			  }

     }
     if( btn=="no" ){ 
    	 hidvente="i$_ACT_RESET_FORM";
     }
     
     $("#ssSQZ_father").mask("Veuillez Patientez...");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val(hidvente);
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 
}


function actFactoData(nfact){
	  
	 $("#ssSQZ_father").mask("Veuillez Patientez...");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val("i$_ACT_FACTURER");
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}"+"?numiosFacture="+nfact);
     $("#myformToServeur").submit(); 
}

function control_de_liste(){
// 	 if(otab_otra2) otab_otra2.fnAdjustColumnSizing();
  if(otab_otra) otab_otra.fnAdjustColumnSizing();
// 	 if(otab_otraPrestation) otab_otraPrestation.fnAdjustColumnSizing();
 
var  verifNum =doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_VERIF_LIST','text',false);
return verifNum;
}

             
$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","quantiteX","list_article_proVente","500","250");
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","vente_obs","list_depot_stock","250","100");  
});                                                                                         
 
 
 
 
function onChange_select(Xcode2d){
if(   Xcode2d=="depot_id"     ) return;
 
   
	if(Xcode2d=="designation_libelle"  ||   Xcode2d=="pk.code_barre"  ){
		//var value_enstock=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_STOCK','text',false);
		//$('input[id="quantite_stock"]').val(value_enstock);
	}  else if(Xcode2d=="codeFocuso"   ||  Xcode2d=="XnextFocuso"  ){
	
	  //var  stock_fourniture=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_STOCK_FOURNITURE','text',false);
		//$('input[id="quantite_stockxc"]').val(stock_fourniture);
	
	}
	
	
}


$(function() {
    $('input[id="pk.code_barre"]').focus(function() {
     $('input[id="quantite_stock"]').val('');
    }) 
});



function clearQuantite(){
	$('#quantiteX').val('');
} 


function addQuantite(qteParam){
	 var qte= $('#quantiteX').val();
	 const stringObj =  new String(qte)+new String(qteParam);
	 $('#quantiteX').val(stringObj);    
} 


function addProduit(produitId,produitlib){
	 $('input[id="pk.code_barre"]').val(produitId);  
	 $('#designation_libelle').val(produitlib);
	  lib_required="requiredx";
	 if(!teste_required()) return ;
	 lib_required="required"; 

	 
	 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
	  otab_otra.fnAdjustColumnSizing();
	 $('input[id="pk.code_barre"]').val('');               
	 $('#designation_libelle').val('');
	 $('#quantiteX').val('1');
	    
 }  
 
function ADD(){

 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";

 
 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
  otab_otra.fnAdjustColumnSizing();
 $('input[id="pk.code_barre"]').val('');               
 $('#designation_libelle').val('');
 $('#quantiteX').val('1');
    
}  

function Delete_ROW(){
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW',
	               dataType: 'text', 
	               success: function(data){
	               otab_otra.fnAdjustColumnSizing();
                      },
                   error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
}

function doCheked_unCheked(element){
		var res="";
		if($(element).attr('type')=="checkbox"){
	    element.value=element.checked==false?"":"checked";
	    res=element.checked==false?"":"checked";
	    }
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_Cheked_unCheked&statucheked='+res,
	               dataType: 'text', 
	               success: function(data){
	             
					  otab_otra.fnAdjustColumnSizing();
	               
                      },
                 error: function (request, status, error) {
                         alert(request.responseText);
                  } 
    });
}   
  

function getSuivant(panelName){
    
if(!teste_required()) return ;
 
$("#choixPanel").val(panelName);
  
var jsonText=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_TARIF_CLIENT','json',false);

let htmlData='';

for (let w = 0; w < 10; w++) {
	htmlData=htmlData+'<b><input  id="'+w+'"  type="button"  value="'+w+'"    onclick="addQuantite(this.id)"   style="font-size: 16px;text-align:center;width: 40px;" >  </b>';
}

if(jsonText!=null)
jsonText.forEach(obj => {
   htmlData=htmlData+'<b><input  id="'+obj.pk.code_barre+'"  type="button"  value="'+obj.designation_libelle+'"    onclick="addProduit(this.id,this.value)"   style="font-size: 16px;text-align:center;" >  </b>';
});


	
$("#galleryArticle").html(htmlData) ;	
 
$('#GRID_SAISIE_DETAIL_VENTE').css('display','block');
 
   
if(panelName=='article'){
	if(otab_otra!=null &&   otab_otra!= undefined)
	 otab_otra.fnAdjustColumnSizing();
	else
	  LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
	 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
}

} 

                              
function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
     
      
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
            var QteNew="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
	      $(this).find('td:eq(9)').html(json[qsdqsqd]) ;
	      //$(this).find('td:eq(4)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	      var xcvvv=json[erreurX];
	      xcvvv=xcvvv.trim();
	      if(   xcvvv  !="" &&  xcvvv.length>0 ){
	           alertExt("",json[erreurX],"4");
	      }
	      
	   });
    otab_otra.fnAdjustColumnSizing();
 
 } 							                        
 </script>
 
 
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
  
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"       style="height: 100%;"    >





   
 <table   cellpadding="2" cellspacing="2" id="tblData" width="100%" border="1" >
 
 
 <tr> <td   rowspan="8"  colspan="4"  width="15%"  >
           
            <script>
                $(function () {
                    loadSelectAjax("fam_idX", "listFamArticleOfvente", "fam_id", "fam_lib", "", true);
                    $("#fam_idX").css("height", parseInt($("#fam_idX option").length) * 20);
                });
            </script>
 
            <select id="fam_idX" name="fam_id" onchange="getSuivant('article')" style="width: 250px;" nextElement="artyp"   multiple="multiple"     ></select>
 
 
 </td></tr>
    <tr  valign="top"  >
        <td width="35%" colspan="4">
             <b> 
<input  type="button"  value="X"       onclick="clearQuantite()"         
style="font-size: 16px;width: 40px;"  >
</b>



           <label>${clt_id}</label> 
            <script>
                $(function () {
                    loadSelectAjax("clt_idx", "list_client_for_vente", "clt_id", "clt_lib", "${detailBean.client.clt_id}", true);
                });
            </script>
            <select id="clt_idx" name="client.clt_id" style="width: 180px;"></select>
            <div id="galleryArticle"></div>
        </td>
        <td width="50%" rowspan="8"  valign="top" >
        <label><input id="choixPanel" name="choixPanel" type="hidden" /> ${vente_id}</label>
        <input id="vente_id" name="vente_id" type="text" size="15" value="${detailBean.vente_id}" nextElement="vente_libelle" libre readonly="readonly" />
        <label>${vente_date}</label>
         <fmt:formatDate pattern="dd/MM/yyyy" value="${detailBean.vente_date}" var="detailavente_date" />
            <input id="vente_date" name="vente_date" type="datepicker" size="13" libre maxlength="13" value="${detailavente_date}" nextElement="depot_id" required />
            <select   style="display: none;"   onchange="getDevise(this.value)" required id="devX" name="devise.dev_id" style="width: 180px;"></select>
            
             <table id="GRID_SAISIE_DETAIL_VENTE" class="display"     > 
			      <thead   >      
					<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
						<th></th>
						<th width="5px"><input   type="checkbox"   id="Cheked_unCheked"       name="Cheked_unCheked"   ></th>
					    <th   ></th>
						<th><input   type="hidden"       id="pk.code_barre"         name="code_barreX"      requiredx          >
						<input   type="text"         id="designation_libelle"   name="designation_libelle"   style="width: 90%;"        requiredx ></th>
		                <th ><input  type="number"     id="quantiteX"             name="quantiteX"     min="1"    value="1"    style="width: 90%;"  requiredx > 
						<th></th>
						<th  align="right">
						<input  id="b1"  type="button"  value="+"    onclick="ADD()"   style="font-size: 16px;width: 40px;text-align:center;" > 
						<input  type="button"  value="-"       onclick="Delete_ROW()"         style="font-size: 16px;width: 40px;"  >
						</th>
						<th></th>
					</tr>
					 <tr> 
						<th></th>
						<th></th>
						<th>code</th>
						<th>D�signation</th>
						<th>Prix.U</th>
						<th>Qt�</th>
						<th>Total</th>
						<th></th>
				    </tr>
				 </thead>
				<tfoot  id="footTotal" style="display: none;"  ></tfoot></table>
				
		 
       
            <label style="display: none;">Remise </label>
            <input   
                id="taux_remise_alacaisse"
                name="taux_remise_alacaisse"
                style="width: 40px;display: none;"
                type="number"
                min="0"
                max="100"
                libre="libre"
                value="${detailBean.taux_remise_alacaisse}"
                nextelement="designation_libelle"
                onblur="getSuivant('article');"
            />
            <label style="display: none;"  >%</label>
            <input id="vente_remise_alacaisse"  style="display: none;"   name="vente_remise_alacaisse" type="montant3" size="10" libre readonly="readonly"   value="${detailBean.vente_remise_alacaisse}" />
            <input id="vente_remise"            style="display: none;"   name="vente_remise" type="montant3" size="10" libre readonly="readonly" value="${detailBean.vente_remise}" />
             <br>
         <label> Net.A Payer</label> 
         <input id="vente_mnt_net_a_payer" name="vente_mnt_net_a_payer" type="montant3" size="17" libre readonly="readonly" maxlength="15" value="${detailBean.vente_mnt_net_a_payer}" />
          <br>
		   <label> Montant r��u</label> 
		   <input id="montant_vente_recu" name="montant_vente_recu"     type="montant3"    style="height: 30px;font-size: 18px;"  size="17"      libre     value="${detailBean.montant_vente_recu}"    nextelement="montant_vente_rendu"     onblur="loadgrid();"   /> 
		   <label> Montant rendu </label> 
	       <input id="montant_vente_rendu" name="montant_vente_rendu"    libre  readonly="readonly"    value="${detailBean.montant_vente_rendu}"   type="montant3"  style="height: 30px;font-size: 18px;"   size="17"            nextelement="null"   /> 
        </td>
    </tr>

    

    <tr  valign="top"   >
        <td  colspan="4"> 
         <label>${depot_id}</label> 
            <input id="depot_id" name="depot.depot_id" libre type="text" readonly="readonly" size="4" maxlength="10" value="${detailBean.depot.depot_id}" nextElement="vente_obs" required />
            <input id="depot_libelle" name="depot.depot_libelle" libre type="text" size="21" maxlength="10" value="${detailBean.depot.depot_libelle}" nextElement="vente_obs" required />
       </td>
    </tr>
    <tr style="display: none;">
        <td><label>${vente_libelle}</label></td>
        <td>
            <input id="vente_libelle" name="vente_libelle" type="text" size="17" value="${detailBean.vente_libelle}" nextElement="depot_id" autofocus />
        </td>

        <td>
            <label>${avance_montant_vente}</label>
        </td>

        <td>
            <input id="avance_montant_vente" name="avance_montant_vente" type="montant3" size="17" libre="libre" maxlength="50" value="${detailBean.avance_montant_vente}" nextelement="vente_remise" onblur="getSuivant('article');" />
        </td>
    </tr>

    <tr style="display: none;" >
        <td><label>Total.HT.Net</label></td>
        <td>
            <input id="vente_mnt_brute_ht" name="vente_mnt_brute_ht" type="montant3" style="display: none;" size="15" libre readonly="readonly" value="${detailBean.vente_mnt_ht}" />
            <input id="vente_mnt_net_ht" name="vente_mnt_net_ht" type="montant3" size="15" libre readonly="readonly" value="" />
        </td>
        <td><label>Total.Tva </label></td>
        <td>
            <input id="vente_mnt_tva" name="vente_mnt_tva" type="montant3" size="15" libre readonly="readonly" value="${detailBean.vente_mnt_tva}" />
        </td>
    </tr>
    
    
    

    <tr>
        <td  colspan="4"  ><button value="Espace" >Espace</button>  
        
             <input id="espace" name="espace"   size="10"  libre readonly="readonly"  value="" />
        
         <label>Table</label> 
        
            <input id="table" name="table"    size="10" libre readonly="readonly" value="" />
        </td>
   </tr>
   
    
</table>
 
    </ext:panel>           
	          
</ext:panel>

</ext:body>
 
