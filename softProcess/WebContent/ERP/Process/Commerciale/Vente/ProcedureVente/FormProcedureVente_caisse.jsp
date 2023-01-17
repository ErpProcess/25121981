<%@include file="/Aceuil/esProcess.jsp"%>
<style>



.buttonClv {
	background-color: #4CAF50;
	border: none;
	color: #FFFFFF;
	padding: 25px 30px 20px 25px;
	text-align: center;
	-webkit-transition-duration: 0.4s;
	transition-duration: 0.4s;
	text-decoration: none;
	font-size: 16px;
	cursor: pointer;
	margin: 5px 5px 5px 5px;
}

.btnArticle {
	background-color: #008CBA;
	border: none;
	width: 80px;
	height: 80px;
	color: #FFFFFF;
	padding-left: 5px;
	white-space: initial;
	text-align: left;
	-webkit-transition-duration: 0.4s;
	transition-duration: 0.4s;
	text-decoration: none;
	font-size: 12px;
	cursor: pointer;
	margin: 5px 5px 5px 5px;
}

.buttonClv:hover {
	background-color: #4CAF50; /* Green */
	color: white;
}
</style>




<script type="text/javascript">
contenu_toolbarJQuey ="";
height_tabbJQuey="300px;
width_tabbJQuey="100%";
</script>
<c:import
	url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


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
				              return  '<img   width="25"  style="cursor: pointer;margin-right:7px;margin-left:3px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAyVBMVEUAAAD0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0QjX0Qzb0Qzb0QjX0QjX0QjX0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0Qzb0QjX0Qzb0QjX0Qzb1TkL1VEn1Vkr1TED1T0P6sKv8x8P2YFX0QTT2Zlz8zMn6p6H1Sz/1WEz8zsr////8y8f2X1T2ZVv80s/8w7/1Ukb919T8yMX2XlP92df8z8z2XVL80c380c71Vkv8zcn2ZVr8xcD1U0f1TUD6qaP1TD+9OvL7AAAAG3RSTlMAAAITJSQxhL3WB2XaZAbt7NkwA4O8u4Jj62IAHDtBAAAAAWJLR0Qsut1xqwAAAAlwSFlzAADsOAAA7DgBcSvKOAAAAAd0SU1FB+YJCQkFH4N/vfsAAADCSURBVBjTRY/nEoIwEISTi5QooKDYLnaxRsXe6/s/lIfouL/uvpmd3WWMk0BkDAF0MBK9pmVLaVsmJITzbM5BRULH9YhwyOUbTSLYahdcIGA6nW6vH0WD4WjsB5wVLZxM9Wy+iPVyhSVgwlY4iHW81pstqlAwQyoVLXZa7w+RUtL4g+M8BV/Laa1nfVRlwcDC84X811jf7liBJPbxXG7/sRyqhdcqLVarJ8W45/ppdb/upWMgKIVShpXgM+473/jNfwNQQRYTYldNwwAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAyMi0wOS0wOVQwOTowNToyMyswMDowMDOF4D0AAAAldEVYdGRhdGU6bW9kaWZ5ADIwMjItMDktMDlUMDk6MDU6MjMrMDA6MDBC2FiBAAAARnRFWHRzb2Z0d2FyZQBJbWFnZU1hZ2ljayA2LjcuOC05IDIwMTYtMDYtMTYgUTE2IGh0dHA6Ly93d3cuaW1hZ2VtYWdpY2sub3Jn5r80tgAAABh0RVh0VGh1bWI6OkRvY3VtZW50OjpQYWdlcwAxp/+7LwAAABh0RVh0VGh1bWI6OkltYWdlOjpoZWlnaHQANTEywNBQUQAAABd0RVh0VGh1bWI6OkltYWdlOjpXaWR0aAA1MTIcfAPcAAAAGXRFWHRUaHVtYjo6TWltZXR5cGUAaW1hZ2UvcG5nP7JWTgAAABd0RVh0VGh1bWI6Ok1UaW1lADE1MjE4Mjg5NzYlBL7wAAAAE3RFWHRUaHVtYjo6U2l6ZQAxNS4zS0JC0BX05AAAAER0RVh0VGh1bWI6OlVSSQBmaWxlOi8vLi91cGxvYWRzLzU2L01FN21WeEwvMTM4MC92Y3Njb25mbGljdGluZ185MzQ5Ny5wbmfH+vO6AAAAAElFTkSuQmCC" id=to_check'+full[3]+'  name=to_check       onclick=deleteRowCaisse("'+full[3]+'") >';}},
				              
				       {      "sName": "quantite"           ,   "bSortable": true       , "sWidth": "5%"        ,"mRender": function( data, type, full){  
						          return '<input   type="text"       id=quantite'+full[3]+'       name=quantite        value="'+data+'"       onblur=doEnvoiDataV2(this,"'+full[3]+'")        >'; }},         
				                  
				       {      "sName": "pk.fkcode_barre.pk.code_barre"      , "bVisible": false   }, 
					         
					   {      "sName": "info"    ,"sWidth": "40%"   , "bSortable": true  ,"bSearchable": true , "bVisible": true  },   
					   
					   /*{      "sTitle":"Prix U"    , "sName": "tarif.tarif_unit_vente_tt"   ,"sWidth": "5%"    ,"sClass" : "alignRight"       , "bSortable": true 
                           , "mRender":    function (data, type, full) {
                        	   return '<input   type="montant3"      style="width:100%;"     id=tarif_unit_vente_tt'+full[3]+'       name=tarif.tarif_unit_vente_tt        value="'+formatNumberJsXC(data,3)+'"       onblur=doEnvoiDataV2(this,"'+full[3]+'")      >'; 
                          	   } 
					   },*/
					   
					    {     "sName": "tarif.tarif_unit_vente_tt"       ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
                           ,"mRender":    function (data, type, full) {   if( $("#devX").val()=="191"  ||  $("#devX").val()=="192")   return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },         
					                 
					

                       {     "sName": "montant_ttc_vente"       ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
                             ,"mRender":    function (data, type, full) {   if( $("#devX").val()=="191"  ||  $("#devX").val()=="192")   return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },         
                           
					   {     "sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
                         ]
};


function  loadgrid(){
	 
	if(otab_otra!=null &&   otab_otra!= undefined)
		 otab_otra.fnAdjustColumnSizing();


}

function  saveDataAJAX(){
	 
 var response=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_AJAX','text',false);

  if(response!=="" && response!==null) {
	  otab_otra.fnAdjustColumnSizing();
	  alertExt("",response,"1");
  }
	  
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

if(custumMessageBoxo!=""  &&  custumMessageBoxo !="Facturation effectuée avec succès"  &&  custumMessageBoxo !="Confirmation effectuée avec succès"  ){

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

if(custumMessageBoxo!=""  &&  custumMessageBoxo =="Confirmation effectuée avec succès"){

	Ext.MessageBox.show({
        title:'Imprimer Bon de Livraison',
        msg: custumMessageBoxo,
        buttons: {ok:'Imprimer BL',no:'Retour'}  ,
        fn: getActionImprimerBL,
        animateTarget: 'mb4',
        icon: Ext.MessageBox.QUESTION
    });
	}  





if(custumMessageBoxo!="" &&  custumMessageBoxo=="Facturation effectuée avec succès"){
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

		  if(verifNumFac!="" &&  verifNumFac.startsWith("©") )  {
			  var tabOfNumeroFact = verifNumFac.split("©");
	 
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
		      msg: ' Voulez vous Choisir un Numéro Déja Supprimé : '+maselectFact,
		      buttons: {ok:'Ancien Numéro',no:'Nouveau Numéro'}  ,
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

	 if($('#quantiteX').val()===""){
		 $('#quantiteX').val('1');
	 }
	 
	 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
	  otab_otra.fnAdjustColumnSizing();
	 $('input[id="pk.code_barre"]').val('');               
	 $('#designation_libelle').val('');
	 $('#quantiteX').val('');
	    
 }  
 
function ADD(){

 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";
 
 if($('#quantiteX').val()===""){
	 $('#quantiteX').val('1');
 }

 
 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
  otab_otra.fnAdjustColumnSizing();
 $('input[id="pk.code_barre"]').val('');               
 $('#designation_libelle').val('');
 $('#quantiteX').val('');
    
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

function deleteRowCaisse(idArticle){
	  jQuery.ajax({ type: 'POST',  
		               url: '${tmlx.urlAjax}', 
		               data:'HiddenAction=i$_ACT_DELETE_ROW_CAISSE&idArticleToRemove='+idArticle,
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
var htmlDataGalleryArticle='';
if(jsonText!=null)
jsonText.forEach(obj => {
	htmlDataGalleryArticle=htmlDataGalleryArticle+'<b><input  id="'+obj.pk.code_barre+'"   class="btnArticle"   type="button"  value="'+obj.designation_libelle+'"    onclick="addProduit(this.id,this.value)"    >  </b>';
});

$("#galleryArticle").html(htmlDataGalleryArticle) ;	
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


<script>
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

  
		        
<style>
.ulCaisse
{
margin:0px;
padding:0px;
list-style-type:none;
height:500px;
overflow:scroll;
-webkit-backface-visibility: hidden; 
backface-visibility: hidden;  
}
.var_nav
{
position:relative;
background:#ccc; 
width:150px;
height:50px;
margin-bottom:5px;
}
.link_bg {
    width: 50px;
    height: 50px;
    position: absolute;
    background: #cccccc00;
    /* background: #E01B6A; */
    color: #fff;
    z-index: 2;
}
.link_bg i
{
 position:relative;
}
.link_title
{
position:absolute;
width:100%;
z-index:3;
color:#fff;
}
.link_title:hover .icon
{
-webkit-transform:rotate(360deg);
-moz-transform:rotate(360deg);
-o-transform:rotate(360deg);
-ms-transform:rotate(360deg);
transform:rotate(360deg);  
}
.var_nav:hover .link_bg
{
width:100%;
background:#E01B6A;
-webkit-transition: all 0.3s ease-in-out;
-moz-transition: all 0.3s ease-in-out;
-o-transition: all 0.3s ease-in-out;
-ms-transition: all 0.3s ease-in-out;
transition: all 0.3s ease-in-out;  
}
.var_nav:hover a
{
font-weight:bold;
text-decoration: none; 
-webkit-transition:all .5s ease-in-out;
-moz-transition:all .5s ease-in-out; 
-o-transition:all .5s ease-in-out; 
-ms-transition:all .5s ease-in-out;
 transition:all .5s ease-in-out;  
}
.icon
{
position:relative;
width:50px;
height:20px;
text-align:center;
color:#fff;
-webkit-transition:all .5s ease-in-out;
-moz-transition:all .5s ease-in-out; 
-o-transition:all .5s ease-in-out; 
-ms-transition:all .5s ease-in-out;   
float:left;
transition:all .5s ease-in-out;   
float:left;  
}
.icon i{top:18px;position:relative;}
.aCaisse {
    display: block;
    position: absolute;
    float: left;
    font-family: arial;
    /* color: #fff; */
    text-decoration: none;
    width: 100%;
    height: 50px;
    text-align: center;
    /* margin-left: 13px; */
}
.spanCaisse
{
margin-top:15px;
display:block;
}
</style>

<ext:body>


	<ext:panel border="false" bodyStyle="background: none;"
		renderTo="ThePageJsp">






		<ext:panel border="false" bodyStyle="background: none;">

			<%--         <ext:toolbar         toolbarType="bbar"   >  --%>
			<%--         <ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   onClick="getSuivant('article')"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar> --%>

			<table cellpadding="2" cellspacing="2" id="tblData" width="100%" border="1">


				<tr>
					<td rowspan="8" colspan="4" width="10%" valign="top">
					
					    <label style="display: none;">${depot_id}</label>
						<input id="depot_id" name="depot.depot_id" type="hidden"
						readonly="readonly" value="${detailBean.depot.depot_id}"  />
						<input id="depot_libelle" name="depot.depot_libelle" libre
					type="hidden"   
						value="${detailBean.depot.depot_libelle}" required /> 
				<script>
				
				function loadFamilleArticle(id_region,name_list,codx,libx,data_index_sel,select_vide){
			        
		    		$.ajax({
					        url: UrlServerListeComboSelect,
					        data: {"nameList_select":name_list ,"fieldcode":codx,"fieldlabel":libx},  
					        dataType: "json", 
					        async: false,
					        type: "POST",
					        success: function(data) {
					             var $regions = $('#'+id_region);
					             for (var h = 0; h <data.myliste.length; h++) {
					              var htmlDivfam='<li class="var_nav">' +
					                  '<div class="link_bg"></div>'+
					                  '<div class="link_title">'+
					                    '<div class=icon > '+
					                 //   '<i ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cup-hot" viewBox="0 0 16 16">'+
					              //' <path fill-rule="evenodd" d="M.5 6a.5.5 0 0 0-.488.608l1.652 7.434A2.5 2.5 0 0 0 4.104 16h5.792a2.5 2.5 0 0 0 2.44-1.958l.131-.59a3 3 0 0 0 1.3-5.854l.221-.99A.5.5 0 0 0 13.5 6H.5ZM13 12.5a2.01 2.01 0 0 1-.316-.025l.867-3.898A2.001 2.001 0 0 1 13 12.5ZM2.64 13.825 1.123 7h11.754l-1.517 6.825A1.5 1.5 0 0 1 9.896 15H4.104a1.5 1.5 0 0 1-1.464-1.175Z"/>'+
					             // ' <path d="m4.4.8-.003.004-.014.019a4.167 4.167 0 0 0-.204.31 2.327 2.327 0 0 0-.141.267c-.026.06-.034.092-.037.103v.004a.593.593 0 0 0 .091.248c.075.133.178.272.308.445l.01.012c.118.158.26.347.37.543.112.2.22.455.22.745 0 .188-.065.368-.119.494a3.31 3.31 0 0 1-.202.388 5.444 5.444 0 0 1-.253.382l-.018.025-.005.008-.002.002A.5.5 0 0 1 3.6 4.2l.003-.004.014-.019a4.149 4.149 0 0 0 .204-.31 2.06 2.06 0 0 0 .141-.267c.026-.06.034-.092.037-.103a.593.593 0 0 0-.09-.252A4.334 4.334 0 0 0 3.6 2.8l-.01-.012a5.099 5.099 0 0 1-.37-.543A1.53 1.53 0 0 1 3 1.5c0-.188.065-.368.119-.494.059-.138.134-.274.202-.388a5.446 5.446 0 0 1 .253-.382l.025-.035A.5.5 0 0 1 4.4.8Zm3 0-.003.004-.014.019a4.167 4.167 0 0 0-.204.31 2.327 2.327 0 0 0-.141.267c-.026.06-.034.092-.037.103v.004a.593.593 0 0 0 .091.248c.075.133.178.272.308.445l.01.012c.118.158.26.347.37.543.112.2.22.455.22.745 0 .188-.065.368-.119.494a3.31 3.31 0 0 1-.202.388 5.444 5.444 0 0 1-.253.382l-.018.025-.005.008-.002.002A.5.5 0 0 1 6.6 4.2l.003-.004.014-.019a4.149 4.149 0 0 0 .204-.31 2.06 2.06 0 0 0 .141-.267c.026-.06.034-.092.037-.103a.593.593 0 0 0-.09-.252A4.334 4.334 0 0 0 6.6 2.8l-.01-.012a5.099 5.099 0 0 1-.37-.543A1.53 1.53 0 0 1 6 1.5c0-.188.065-.368.119-.494.059-.138.134-.274.202-.388a5.446 5.446 0 0 1 .253-.382l.025-.035A.5.5 0 0 1 7.4.8Zm3 0-.003.004-.014.019a4.077 4.077 0 0 0-.204.31 2.337 2.337 0 0 0-.141.267c-.026.06-.034.092-.037.103v.004a.593.593 0 0 0 .091.248c.075.133.178.272.308.445l.01.012c.118.158.26.347.37.543.112.2.22.455.22.745 0 .188-.065.368-.119.494a3.198 3.198 0 0 1-.202.388 5.385 5.385 0 0 1-.252.382l-.019.025-.005.008-.002.002A.5.5 0 0 1 9.6 4.2l.003-.004.014-.019a4.149 4.149 0 0 0 .204-.31 2.06 2.06 0 0 0 .141-.267c.026-.06.034-.092.037-.103a.593.593 0 0 0-.09-.252A4.334 4.334 0 0 0 9.6 2.8l-.01-.012a5.099 5.099 0 0 1-.37-.543A1.53 1.53 0 0 1 9 1.5c0-.188.065-.368.119-.494.059-.138.134-.274.202-.388a5.446 5.446 0 0 1 .253-.382l.025-.035A.5.5 0 0 1 10.4.8Z"/></svg> </i> '+
					               ' </div> <a  class="aCaisse"  href="javascript:MyFunction(\''+data.myliste[h].keyx +'\');"     ><span class="spanCaisse" >'+data.myliste[h].valuex+'</span></a></div></li>' 
					               $regions.append(htmlDivfam);
					             }  
					        }
		            });
		}
				function MyFunction(famCode){
					$("#fam_id").val(famCode);
					getSuivant('article');
				}
                $(function () {
                    loadFamilleArticle("familleDiv", "listFamArticleOfvente", "fam_id", "fam_lib", "", true);
                });
            </script>  					 
   <input id="fam_id" name="fam_id"  type="hidden"  >   				 
<nav>
  <UL class="ulCaisse" id="familleDiv">
   <!--  >li class="var_nav">
      <div class="link_bg"></div>
      <div class="link_title">
        <div class=icon> 
        <i >
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cup-hot" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M.5 6a.5.5 0 0 0-.488.608l1.652 7.434A2.5 2.5 0 0 0 4.104 16h5.792a2.5 2.5 0 0 0 2.44-1.958l.131-.59a3 3 0 0 0 1.3-5.854l.221-.99A.5.5 0 0 0 13.5 6H.5ZM13 12.5a2.01 2.01 0 0 1-.316-.025l.867-3.898A2.001 2.001 0 0 1 13 12.5ZM2.64 13.825 1.123 7h11.754l-1.517 6.825A1.5 1.5 0 0 1 9.896 15H4.104a1.5 1.5 0 0 1-1.464-1.175Z"/>
  <path d="m4.4.8-.003.004-.014.019a4.167 4.167 0 0 0-.204.31 2.327 2.327 0 0 0-.141.267c-.026.06-.034.092-.037.103v.004a.593.593 0 0 0 .091.248c.075.133.178.272.308.445l.01.012c.118.158.26.347.37.543.112.2.22.455.22.745 0 .188-.065.368-.119.494a3.31 3.31 0 0 1-.202.388 5.444 5.444 0 0 1-.253.382l-.018.025-.005.008-.002.002A.5.5 0 0 1 3.6 4.2l.003-.004.014-.019a4.149 4.149 0 0 0 .204-.31 2.06 2.06 0 0 0 .141-.267c.026-.06.034-.092.037-.103a.593.593 0 0 0-.09-.252A4.334 4.334 0 0 0 3.6 2.8l-.01-.012a5.099 5.099 0 0 1-.37-.543A1.53 1.53 0 0 1 3 1.5c0-.188.065-.368.119-.494.059-.138.134-.274.202-.388a5.446 5.446 0 0 1 .253-.382l.025-.035A.5.5 0 0 1 4.4.8Zm3 0-.003.004-.014.019a4.167 4.167 0 0 0-.204.31 2.327 2.327 0 0 0-.141.267c-.026.06-.034.092-.037.103v.004a.593.593 0 0 0 .091.248c.075.133.178.272.308.445l.01.012c.118.158.26.347.37.543.112.2.22.455.22.745 0 .188-.065.368-.119.494a3.31 3.31 0 0 1-.202.388 5.444 5.444 0 0 1-.253.382l-.018.025-.005.008-.002.002A.5.5 0 0 1 6.6 4.2l.003-.004.014-.019a4.149 4.149 0 0 0 .204-.31 2.06 2.06 0 0 0 .141-.267c.026-.06.034-.092.037-.103a.593.593 0 0 0-.09-.252A4.334 4.334 0 0 0 6.6 2.8l-.01-.012a5.099 5.099 0 0 1-.37-.543A1.53 1.53 0 0 1 6 1.5c0-.188.065-.368.119-.494.059-.138.134-.274.202-.388a5.446 5.446 0 0 1 .253-.382l.025-.035A.5.5 0 0 1 7.4.8Zm3 0-.003.004-.014.019a4.077 4.077 0 0 0-.204.31 2.337 2.337 0 0 0-.141.267c-.026.06-.034.092-.037.103v.004a.593.593 0 0 0 .091.248c.075.133.178.272.308.445l.01.012c.118.158.26.347.37.543.112.2.22.455.22.745 0 .188-.065.368-.119.494a3.198 3.198 0 0 1-.202.388 5.385 5.385 0 0 1-.252.382l-.019.025-.005.008-.002.002A.5.5 0 0 1 9.6 4.2l.003-.004.014-.019a4.149 4.149 0 0 0 .204-.31 2.06 2.06 0 0 0 .141-.267c.026-.06.034-.092.037-.103a.593.593 0 0 0-.09-.252A4.334 4.334 0 0 0 9.6 2.8l-.01-.012a5.099 5.099 0 0 1-.37-.543A1.53 1.53 0 0 1 9 1.5c0-.188.065-.368.119-.494.059-.138.134-.274.202-.388a5.446 5.446 0 0 1 .253-.382l.025-.035A.5.5 0 0 1 10.4.8Z"/>
</svg>
        
        </i>
        </div>
        <a  class="aCaisse" href="#"><span class="spanCaisse" >About Us</span></a>
      </div>
   </li-->
   
   <!-- li class="var_nav">
      <div class="link_bg"></div>
      <div class="link_title">
        <div class=icon> 
        <i class="icon-lightbulb icon-2x"></i>
        </div>
        <a href="#" class="aCaisse" ><span class="spanCaisse" >Ideas</span></a>
      </div>
   </li>
   <li class="var_nav">
      <div class="link_bg"></div>
      <div class="link_title">
        <div class=icon> 
        <i class="icon-wrench icon-2x"></i>
        </div>
        <a href="#" class="aCaisse" ><span class="spanCaisse">Services</span></a>
      </div>
   </li>
   <li class="var_nav">
      <div class="link_bg"></div>
      <div class="link_title">
        <div class=icon> 
        <i class="icon-briefcase icon-2x"></i>
        </div>
      <a href="#" class="aCaisse" ><span class="spanCaisse" >Marketing</span></a>
      </div>
   </li -->
   
  </UL>
</nav>
						
						
						</td>
				</tr>


				<tr valign="top">
					<td width="50%" colspan="4">
						 
<input
						id="choixPanel" name="choixPanel" type="hidden" /> <label>${vente_id}</label>
						<input id="vente_id" name="vente_id" type="text" size="15"
						value="${detailBean.vente_id}" nextElement="vente_libelle" libre
						readonly="readonly" /> <label>${vente_date}</label> <fmt:formatDate
							pattern="dd/MM/yyyy" value="${detailBean.vente_date}"
							var="detailavente_date" /> <input id="vente_date"
						name="vente_date" type="datepicker" size="13" libre maxlength="13"
						value="${detailavente_date}" nextElement="depot_id" required /> <select
						style="display: none;" onchange="getDevise(this.value)" required
						id="devX" name="devise.dev_id" style="width: 180px;"></select> 
	<script>
                $(function () {
                    loadSelectAjax("clt_idx", "list_client_for_vente", "clt_id", "clt_lib", "${detailBean.client.clt_id}", true);
                    var htmlDataClavierNumerique=$("#clavierNumerique").html();
                    var dataClv='';
                    for (let w = 0; w < 10; w++) {
                    	dataClv=dataClv+'<b><input  id="'+w+'"  type="button"  value="'+w+'"  class="buttonClv"   onclick="addQuantite(this.id)"   style="font-size: 16px;text-align:center;width: 40px;" >  </b>';
                    }
                    $("#clavierNumerique").html(dataClv+htmlDataClavierNumerique) ;	
                });
            </script> <select id="clt_idx" name="client.clt_id" style="width: 180px;  float: left;"    ></select>
						<br> 
						<div id="clavierNumerique"></div>


						<div id="galleryArticle" style="overflow-y: scroll; height: 100%; width : 100%;text-align: center;"></div>
 

					</td>
					<td width="40%" rowspan="8" valign="top">
						<div>
								<label>QTE&nbsp;&nbsp;&nbsp;</label> <input type="number"
								id="quantiteX" name="quantiteX" value="" style="width: 60px;">
								
									 
									 
					
								 
								 
								<br>
								<label>REF&nbsp;&nbsp;&nbsp;</label> <input type="text" id="pk.code_barre" size="15"
								name="code_barreX" requiredx> <br>
								<label>DESG</label>
								<input
								type="text" id="designation_libelle" size="40"
								name="designation_libelle" requiredx>
								  <input id="b1" type="button" value="+" onclick="ADD()" style="font-size: 16px; width: 40px; text-align: center;">
								  <input type="button" value="X" style="font-size: 16px; width: 40px;" onclick="clearQuantite()"  >
								
												

						</div>
						<table id="GRID_SAISIE_DETAIL_VENTE" class="display">
							<thead>
								<tr>
									<th></th>
									<th></th>
									<th>Qté</th>
									<th>Désignation</th>
									<th>Désignation</th>
									<th>Prix.U</th>
									<th>Total</th>
									<th></th>
								</tr>
							</thead>
							<tfoot id="footTotal" style="display: none;">
								<tr>
									<td height="20px" colspan="12"></td>
								</tr>
								<c:forEach var="p" begin="1" end="5">
									<tr>
										<td></td>
										<td></td>
										<td></td>

										<td colspan="2"></td>

										<td colspan="3"></td>

									</tr>
								</c:forEach>

								<c:forEach var="i" begin="1" end="8">
									<tr align="right">
										<td colspan="4"></td>
										<td></td>
										<td colspan="3"></td>
										<td></td>
										<td></td>
										<td colspan="2"></td>

									</tr>
								</c:forEach>


							</tfoot>
						</table> <label style="display: none;">Remise </label> <input
						id="taux_remise_alacaisse" name="taux_remise_alacaisse"
						style="width: 40px; display: none;" type="number" min="0"
						max="100" libre="libre"
						value="${detailBean.taux_remise_alacaisse}"
						nextelement="designation_libelle" onblur="getSuivant('article');" />
						<label style="display: none;">%</label> <input
						id="vente_remise_alacaisse" style="display: none;"
						name="vente_remise_alacaisse" type="montant3" size="10" libre
						readonly="readonly" value="${detailBean.vente_remise_alacaisse}" />
						<input id="vente_remise" style="display: none;"
						name="vente_remise" type="montant3" size="10" libre
						readonly="readonly" value="${detailBean.vente_remise}" />
						<div style="float: right;">
							<label> Net.A Payer</label> <input id="vente_mnt_net_a_payer"
								name="vente_mnt_net_a_payer" type="montant3" size="17" libre
								readonly="readonly" maxlength="15"
								value="${detailBean.vente_mnt_net_a_payer}" />
						</div><label>Montant Reçu &nbsp;&nbsp;</label> <input
						id="montant_vente_recu" name="montant_vente_recu" type="montant3"
						style="font-size: 18px;" size="10" libre
						value="${detailBean.montant_vente_recu}"
						nextelement="montant_vente_rendu" onblur="loadgrid();" /> <br>
					<label>Montant a rendre</label> <input id="montant_vente_rendu"
						name="montant_vente_rendu" libre readonly="readonly"
						value="${detailBean.montant_vente_rendu}" type="montant3"
						style="font-size: 18px;" size="10" nextelement="null" /></td>
				</tr>



				<tr valign="top">
					<td colspan="4"></td>
				</tr>
				<tr style="display: none;">
					<td><label>${vente_libelle}</label></td>
					<td><input id="vente_libelle" name="vente_libelle" type="text"
						size="17" value="${detailBean.vente_libelle}"
						nextElement="depot_id" autofocus /></td>

					<td><label>${avance_montant_vente}</label></td>

					<td><input id="avance_montant_vente"
						name="avance_montant_vente" type="montant3" size="17"
						libre="libre" maxlength="50"
						value="${detailBean.avance_montant_vente}"
						nextelement="vente_remise" onblur="getSuivant('article');" /></td>
				</tr>

				<tr style="display: none;">
					<td><label>Total.HT.Net</label></td>
					<td><input id="vente_mnt_brute_ht" name="vente_mnt_brute_ht"
						type="montant3" style="display: none;" size="15" libre
						readonly="readonly" value="${detailBean.vente_mnt_ht}" /> <input
						id="vente_mnt_net_ht" name="vente_mnt_net_ht" type="montant3"
						size="15" libre readonly="readonly" value="" /></td>
					<td><label>Total.Tva </label></td>
					<td><input id="vente_mnt_tva" name="vente_mnt_tva"
						type="montant3" size="15" libre readonly="readonly"
						value="${detailBean.vente_mnt_tva}" /></td>
				</tr>




			</table>

		</ext:panel>

	</ext:panel>

</ext:body>

