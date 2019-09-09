<%@include file="/Aceuil/esProcess.jsp" %> 
<script type="text/javascript">
height_tabbJQuey="auto";
width_tabbJQuey="100%";
var  contenu_toolbarJQuey2    ='<b><input  id="b2" type="button"  value="+"    onclick="ADD_Fourniture()"   style="font-size: 16px;width: 40px;text-align:center;" >&nbsp;&nbsp;&nbsp;<input  type="button"  value="-"       onclick="Delete_ROW_Fourniture()"  style="font-size: 16px;width: 40px;"  ></b>';
var  config_header_foot_tableJQuey2 ='<"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lf<"toolbar_fourniture">r>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>';

var  contenu_toolbarJQuey3    ='<b><input  id="b2" type="button"  value="+"    onclick="ADD_Prestation()"  style="font-size: 16px;width: 40px;text-align:center;" >&nbsp;&nbsp;&nbsp;<input  type="button"  value="-"        onclick="Delete_ROW_Service()"     style="font-size: 16px;width: 40px;"  ></b>';
var  config_header_foot_tableJQuey3 ='<"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lf<"toolbar_Service">r>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>';

</script>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<c:import url="EditablePrestation.jsp"></c:import>


<script type="text/javascript">


 
var mapEditableGen = {            
		"otab"   :oTable,
        "table"  :"GRID_SAISIE_DETAIL_VENTE",
        "list"   :"list_editable_proVente",
        "id_name":"pk.fkcode_barre.pk.code_barre",
        "url"    :"${urlloadDataTableAjax}",
        "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
        "mapCol" :[ 
				       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
				       
				       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
				              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[2]+'"   >';}}, 
				                  
					   {      "sName": "pk.fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
					         
					   {      "sName": "info"    ,"sWidth": "30%"   },   
				   
					    
					   
   			           {      "sName": "quantite_en_stock"        , "sWidth": "3%"     ,"bSearchable": true  , "bSortable": true,"bVisible": true  },           
					                 
					   {      "sName": "quantite"           ,   "bSortable": true       , "sWidth": "5%"        ,"mRender": function( data, type, full){  
					          return '<input   type="number"      style="width:70px;"     id=quantite'+full[0]+'       name=quantite        value="'+data+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")        >'; }},   
					  
					   {      "sName": "unite"   ,"sWidth": "5%"     ,"bSearchable": true ,  "bVisible": false   },
					    
					   {      "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignCenter"  ,"sWidth": "5%"   , "bSortable": true ,"bVisible": true  },           
					           
					   {      "sTitle":"Prix U"    , "sName": "tarif.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": true 
                           , "mRender":    function (data, type, full) {
//                         	   if( $("#devX").val()=="191"  ||  $("#devX").val()=="192") 
//                         		   return  formatNumberJsXC(data,2); 
//                         	   else  
//                         		   return formatNumberJsXC(data,3); 

                        	   return '<input   type="montant3"      style="width:100%;"     id=tarif_unit_vente'+full[0]+'       name=tarif.tarif_unit_vente        value="'+formatNumberJsXC(data,3)+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")      >'; 
                          	   } 
					   
					         
					   },
                           
                       {      "sTitle":"remise"     , "sName": "taux_remise_ligne"     ,"sWidth": "7%"    ,"sClass" : "alignCenter"    , "bSortable": true    
                           , "mRender": function (data, type, full) {  return addPourcentage(data);}  ,"bVisible": true    },           
                 
                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
                           ,"mRender":    function (data, type, full) {    if( $("#devX").val()=="191"  ||  $("#devX").val()=="192")  return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
                   
                       {      "sTitle":"Total TTC" , "sName": "montant_ttc_vente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
                           ,"mRender":    function (data, type, full) {   if( $("#devX").val()=="191"  ||  $("#devX").val()=="192")   return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },         
					    {      "sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
                         ]
};
                               
$(document).ready(function () {
 

	
 //Ext.getCmp("RET_GRID").show();
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




function getActionBox(btn){
     $("#ssSQZ_father").mask("Veuillez Patientez...");
      var hidvente="i$_ACT_COMMIT";
     if("${bs.fct_id}"=="27") hidvente="i$_ACT_FACTURER"; 
    
     if( btn=="no" ) hidvente="i$_ACT_RESET_FORM";
	 $("#myformToServeur").find('input[name="HiddenAction"]').val(hidvente);
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 
}

function control_de_liste(){
// 	 if(otab_otra2) otab_otra2.fnAdjustColumnSizing();
  if(otab_otra) otab_otra.fnAdjustColumnSizing();
// 	 if(otab_otraPrestation) otab_otraPrestation.fnAdjustColumnSizing();
 
var  verifNum =doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_VERIF_LIST','text',false);
return verifNum;
}

    
var oTable23;
var oTable24;
var mapEditableGen2 = {        "otab"   :oTable23,
                               "table"  :"GRID_SAISIE_FOURNITURE_VENTE",
                               "list"   :"list_editable_fournitureVente",
                               "id_name":"fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV3(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[2]+'"   >';}}, 
									                  
										   {      "sName": "fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "info"    ,"sWidth": "30%"   },   
										       
										   
				          			       {      "sName": "quantite_en_stock"        , "sWidth": "5%"     ,"bSearchable": true  , "bSortable": true,"bVisible": true  },           
										                 
										   {      "sName": "quantite"           ,   "bSortable": true       , "sWidth": "5%"        ,"mRender": function( data, type, full){  
										          return '<input   type="number"      style="width:70px;"     id=quantite'+full[0]+'       name=quantite        value="'+data+'"       onblur=doEnvoiDataV3(this,"'+full[2]+'")     nextElement="quantite'+full[12]+'"     >'; }},   
										  
										 
										    
										   {      "sTitle":"Mvt" , "sName": "isVente"  ,"sClass" : "alignCenter"  ,"sWidth": "5%"      , "bSortable": true ,"bVisible": true  },           
										           
										   {      "sTitle":"Prix U"    , "sName": "tarifVente.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": true 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    }, 
	                                      
	                                       {      "sTitle":"Total TTC" , "sName": "montant_ttc_vente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },       

	                                       {    "sTitle":"Mvt" , "sName": "isVente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                           ,"mRender": function (data, type, full) {return   data=="false"?"Dépense":"Vente"   }    }, 
	                                                  
										   {      "sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };
 


function doEnvoiDataV3(element,value_id_de_la_ligne){
	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"":"checked";
	var attrerio = $(element).attr('isboolean');
	if (typeof attrerio !== typeof undefined && attrerio !== false) {
	   element.value=element.checked==false?false:true;
	 }
	var idToSendo=value_id_de_la_ligne ;
	var LEvalue=element.value;
	var name_column=element.name;
    var hashmap ={"sNameId":mapEditableGen2["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGen2["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL_def, 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){ 
	         if ( $(element).attr('type')!=undefined  &&   $(element).attr('type')!="checkbox" ) {
	         var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE_FOUNITURE','json',false);
		       otab_otra2.fnAdjustColumnSizing();
	         }
	       }
      });      
} 
 
 
                             
  
 
 
                           
$(document).ready(function (){
$('input[id="quantite_stock"]').val('');
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","quantiteX","list_article_proVente","500","250");
LoadAutoCompletGridV2_withMG("pk.code_barre","designation_libelle","list_article_vente_fourniture","codeFocuso","XnextFocuso","quantite_stockxc","500","250");
LoadAutoCompletGridV2_withMG("pk.code_barre","designation_libelle","list_article_vente_service","codeFocusPrestation","XnextFocusPrestation","quantitePrestation","500","250")
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","vente_obs","list_depot_stock","250","100");  
});                                                                                         
 
 
 
 
function onChange_select(Xcode2d){
if(   Xcode2d=="depot_id"     ) return;
 
   
	if(Xcode2d=="designation_libelle"  ||   Xcode2d=="pk.code_barre"  ){
		var value_enstock=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_STOCK','text',false);
		$('input[id="quantite_stock"]').val(value_enstock);
	}  else if(Xcode2d=="codeFocuso"   ||  Xcode2d=="XnextFocuso"  ){
	
	  var  stock_fourniture=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_STOCK_FOURNITURE','text',false);
		$('input[id="quantite_stockxc"]').val(stock_fourniture);
	
	}
	
	
}


$(function() {
    $('input[id="pk.code_barre"]').focus(function() {
     $('input[id="quantite_stock"]').val('');
    }) 
});



 
function ADD(){

 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";

 
 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
  otab_otra.fnAdjustColumnSizing();
 $('input[id="pk.code_barre"]').val('');               
 $('#designation_libelle').val('');
 $('#quantite_stock').val('');
 $('#quantiteX').val('1');
    
}  


function ADD_Fourniture(){

  //alert(otab_otra2);
 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW_FOURNIURE','text',false);

 otab_otra2.fnAdjustColumnSizing();
 otab_otra.fnAdjustColumnSizing();
 $('input[id="codeFocuso"]').val('');               
 $('#XnextFocuso').val('');
 $('#quantite_stockxc').val('');
 $('#quantiteXx').val('1');
    
} 

 



 function Delete_ROW_Fourniture(){  
 
   jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW_FOURNIURE',
	               dataType: 'text', 
	               success: function(data){
	               otab_otra2.fnAdjustColumnSizing();
                      },
                   error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
    
 }

 function Delete_ROW_Service(){ 
	 
	   jQuery.ajax({ type: 'POST',  
		               url: '${tmlx.urlAjax}', 
		               data:'HiddenAction=i$_ACT_DELETE_ROW_SERVICE',
		               dataType: 'text', 
		               success: function(data){
		            	   otab_otraPrestation.fnAdjustColumnSizing();
	                      },
	                   error: function (request, status, error) {
	                         alert(request.responseText);
	                   } 
	    });
	    
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
  
var jsonText=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_TARIF_CLIENT','text',false);
 
  
// $("#clt_id").autocomplete({ 
//     maxResults: 10,
//     source: function(request, response) {
//         var results = $.ui.autocomplete.filter(src, request.term);
//         response(results.slice(0, this.options.maxResults));
//     }
// });


// $("#clt_lib").autocomplete({ 
//     maxResults: 10,
//     source: function(request, response) {
//         var results = $.ui.autocomplete.filter(src, request.term);
//         response(results.slice(0, this.options.maxResults));
//     }
// });

// $("#depot_id").autocomplete({ 
//     maxResults: 10,
//     source: function(request, response) {
//         var results = $.ui.autocomplete.filter(src, request.term);
//         response(results.slice(0, this.options.maxResults));
//     }
// });

// $("#depot_libelle").autocomplete({ 
//     maxResults: 10,
//     source: function(request, response) {
//         var results = $.ui.autocomplete.filter(src, request.term);
//         response(results.slice(0, this.options.maxResults));
//     }
// });

 // $('#devX').attr("readonly","readonly");
//   $('#clt_id').attr("readonly","readonly");
//   $('#clt_lib').attr("readonly","readonly");
//   $('#depot_id').attr("readonly","readonly");
//   $('#depot_libelle').attr("readonly","readonly");
  $('#GRID_SAISIE_DETAIL_VENTE').css('display','block');
  Ext.getCmp('sdsfgrgrgpll').show();
//   Ext.getCmp('btnnext').disable();
 // document.getElementById('btnnext').disabled=true;
     
   
   
//Ext.getCmp('RET_GRID').setTitle(' Détaille Vente ');
    
if(panelName=='article'){
	if(otab_otra!=null &&   otab_otra!= undefined)
	 otab_otra.fnAdjustColumnSizing();
	else
	  LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
	 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
}

if(panelName=='fourniture'){
     if(otab_otra2!=null &&  otab_otra2!=undefined)
	 otab_otra2.fnAdjustColumnSizing();
     else
     LoadDataEditableFromServer_toolbarV22( oTable24, mapEditableGen2  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
     config_header_foot_tableJQuey2   ,"toolbar_fourniture" ,contenu_toolbarJQuey2  );
 
}

if(panelName=='prestation'){
	  if(otab_otraPrestation!=null &&  otab_otraPrestation!=undefined)
	    otab_otraPrestation.fnAdjustColumnSizing();
	  else
	 LoadDataEditableFromServerPrestation( oTable24, mapEditableGenPrs  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
	 config_header_foot_tableJQuey3   ,"toolbar_Service" ,contenu_toolbarJQuey3  );
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
 
  
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"       title="&nbsp;&nbsp;&nbsp;"   collapsible="true"  height="350"  >
   
        <ext:toolbar         toolbarType="bbar"   > 
        <ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   onClick="getSuivant('article')"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
    
     <table class="tableStyleContent"  cellpadding="4" cellspacing="4"  id="tblData"   width="100%"  border="0" >  
		   <tr>  
		   <td width="10%"><label><input id="choixPanel" name="choixPanel"  type="hidden"     >     ${vente_id}</label></td>  
		   <td  width="39%" >  
		   <input id="vente_id" name="vente_id"     type="text"    size="15"             value="${detailBean.vente_id}"    nextElement="vente_libelle"       libre     readonly="readonly"        />		  </td>  
		   <td width="13%"  >  </td>
		   <td width="6%"  ></td>
		   <td width="10%"  ><label>Remise.caisse </label>
		   <input id="taux_remise_alacaisse" name="taux_remise_alacaisse"      style="width: 50px;"     type="number"    min="0"  max="100"       libre="libre"      value="${detailBean.taux_remise_alacaisse}"    nextelement="designation_libelle"  onblur="getSuivant('article');" />
		   <label>%</label>
		   </td>
		   <td width="18%" align="right"    ><input id="vente_remise_alacaisse" name="vente_remise_alacaisse"   style="height: 30px;font-size: 18px;"     type="montant3"    size="17"    libre      readonly="readonly"       value="${detailBean.vente_remise_alacaisse}"   ></td>
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${vente_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.vente_date}"   var="detailavente_date"/> 
		   <input id="vente_date" name="vente_date"     type="datepicker"    size="13"    libre            maxlength="13"        value="${detailavente_date}"    nextElement="depot_id"     required       />		  </td>  
		   <td       >&nbsp;</td>
		   <td ><label></label></td>
		   <td ><label>Total.remise </label></td>
		   <td align="right" ><input id="vente_remise" name="vente_remise"    style="height: 30px;font-size: 18px;"      type="montant3"    size="17"    libre      readonly="readonly"      value="${detailBean.vente_remise}" /></td>
		   </tr>   
		   
		    <tr>  
		   <td width="10%"><label>${clt_id}</label></td>  
		   <td  > 	
		   <script  >  $(function() { loadSelectAjax("clt_idx","list_client_for_vente","clt_id","clt_lib","${detailBean.client.clt_id}",true);})</script>
		   <select  id="clt_idx"      name="client.clt_id"           style="width: 180px;"               ></select>
			 </td>  
		   <td  >&nbsp;</td>
		   <td  >&nbsp;</td>
	       <td  ><label>Total T.T.C</label> </td>
	       <td align="right"  ><input id="vente_mnt_total" name="vente_mnt_total"     type="montant3"    size="17"    libre  readonly="readonly"           value="${detailBean.vente_mnt_total}"    nextelement="designation_libelle"   style="height: 30px;font-size: 18px;" /></td>
	       </tr> 
		   
		    <tr>  
		   <td width="10%"><label>${vente_libelle}</label></td>  
		   <td  >  
		    <input id="vente_libelle" name="vente_libelle"           type="text"     size="45"             value="${detailBean.vente_libelle}"         nextElement="depot_id"   autofocus     />		  </td>  
		   <td  >&nbsp;</td>
		   <td  >&nbsp;</td>
	       <td  ><label>${avance_montant_vente}</label></td>
	       <td align="right"  ><input id="avance_montant_vente" name="avance_montant_vente"        type="montant3"    size="17"    libre="libre"   maxlength="50"        value="${detailBean.avance_montant_vente}"   style="height: 30px;font-size: 18px;"     nextelement="vente_remise"     onblur="getSuivant('article');" /></td>
	       </tr> 
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td    >  
					   <input id="depot_id" name="depot.depot_id"    libre  type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="vente_obs"                  required    />  
					   <input id="depot_libelle" name="depot.depot_libelle"  libre    type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="vente_obs"   required     />		  </td>  
		   <td    >&nbsp;</td>
		   <td    >&nbsp;</td>
		   <td    ><label> Net.a payer </label></td>
		   <td align="right"    ><input id="vente_mnt_net_a_payer" name="vente_mnt_net_a_payer"     type="montant3"    size="17"     libre  readonly="readonly"    maxlength="15"     style="height: 30px;font-size: 18px;"     value="${detailBean.vente_mnt_net_a_payer}"       /></td>
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${cmd_obs}</label></td>  
		   <td   >  
		   <input id="vente_obs" name="vente_obs"     type="text"    size="45"    libre         value="${detailBean.vente_obs}"       nextelement="quantiteX"       />		  </td>  
		   <td   >&nbsp;</td>
		   <td   >&nbsp;</td>
		   <td   >  <label> Montant réçu</label></td>
		   <td align="right"   ><input id="montant_vente_recu" name="montant_vente_recu"     type="montant3"    style="height: 30px;font-size: 18px;"  size="17"           value="${detailBean.montant_vente_recu}"    nextelement="montant_vente_rendu"     onblur="getSuivant('article');"   /></td>
		   </tr> 
		   
		    <tr>  
		   <td  ><label>Famille</label></td>  
		   <td   > 
		   <script  >  $(function() { loadSelectAjax("fam_idX","listFamArticleOfvente","fam_id","fam_lib","",true);})</script>
		   <select  id="fam_idX"      name="fam_id"           style="width: 180px;"      nextElement="artyp"            ></select>
		   
		   </td>  
		   <td   >&nbsp;</td>
		   <td   >&nbsp;</td>
		   <td   >  </td>
		   <td    > </td>
		   </tr> 
		   
		    <tr>  
		   <td ><label>devise</label></td>  
		      <td     >   
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
		        <select onchange="getDevise(this.value)"   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select>
		      </td>   
		   <td   align="right">&nbsp;</td>
	       <td   >&nbsp;</td>
	       <td   ><label> Montant rendu </label> </td>
	       <td align="right"   ><input id="montant_vente_rendu" name="montant_vente_rendu"    libre  readonly="readonly"    value="${detailBean.montant_vente_rendu}"   type="montant3"  style="height: 30px;font-size: 18px;"   size="17"            nextelement="null"   /></td>
	       </tr> 
		 </table> 
    </ext:panel>
	 
	 
	 <ext:tabPanel   border="false"        id="sdsfgrgrgpll"   activeTab="RET_GRID"  >
	         <ext:panel   id="RET_GRID"      bodyStyle="background: none;"    onActivate="getSuivant('article')"  height="500"    border="false"      title="Détaille vente"    >
			    <table id="GRID_SAISIE_DETAIL_VENTE" class="display" width="100%"    >
			  
			      <thead   >
			      
					<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
					
						<th></th>
						<th width="5px"><input   type="checkbox"   id="Cheked_unCheked"       name="Cheked_unCheked"                onclick="doCheked_unCheked(this)"     ></th>
						<th><input   type="text"       id="pk.code_barre"         name="code_barreX"           style="width: 95%;"        requiredx ></th>
						<th><input   type="text"       id="designation_libelle"   name="designation_libelle"   style="width: 95%;"        requiredx ></th>
						<th ><input  type="number"     id="quantite_stock"        name="quantite_stock"        style="width: 93%;"        libre readonly="readonly"        ></th>
		                <th  colspan="2"><input  type="number"     id="quantiteX"             name="quantiteX"              min="1"    value="1"    style="width: 93%;"              requiredx > 
						<input  type="hidden"     id="next_Focus"            style="width: 10%;"    name="next_Focus"    libre readonly="readonly"        ></th> 
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					 <tr> 
						<th></th>
						<th></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>Stock</th>
						<th>Qté</th>
						<th>Unite</th>
						<th>T.V.A</th>
						<th>P.U.V</th>
						<th>Remise</th>
						<th>T.H.T</th>
						<th></th>
				    </tr>
				 </thead>
				<tfoot>
				    <tr><td   height="50px" colspan="11" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
								<td ></td>
								<td ></td>
								 
								<td colspan="2"></td>
								 
								<td colspan="3" ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="8">
		                    <tr align="right"> 
								<td colspan="4"></td>
								<td   ></td>
								<td colspan="3" ></td>
								<td ></td>
								<td  ></td>
								<td colspan="2" ></td>
								 
						    </tr>
					</c:forEach>
						
				 </tfoot>
			    </table>
			    
			     <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var	listTva= json.list_tva ;
					    var	listTotal = json.list_total ;
					    $('#vente_remise_alacaisse').val(json.vente_remise_alacaisse);
					    $('#vente_remise').val(json.vente_remise);
					    $('#vente_mnt_net_a_payer').val(json.vente_mnt_net_a_payer);
					    $('#vente_mnt_total').val(json.vente_mnt_total);
					    
					    $('#montant_vente_recu').val(json.montant_vente_recu);
					    $('#montant_vente_rendu').val(json.montant_vente_rendu);
					     var footX ={} ;
					     
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
					     
					     
					    for (var x = 0; x <listTotal.length; x++) {
					     var foot ={} ;
			             foot[listTotal[x].td1] = listTotal[x].value1;
			             foot[listTotal[x].td2] = listTotal[x].value2;
			             footX["BB"+x]=foot;
					     }   
					        
				    return  footX; 
				}
				
				 
				
               </script>
			    
			    
	         </ext:panel>
	         
	         <ext:panel   id="RET_GRID_PRESTATION"    onActivate="getSuivant('prestation')"      bodyStyle="background: none;"    border="false"  height="400"  hideCollapseTool="true"  title="Prestation"    >
	         
	          <script>$(function() {loadSelectAjax("devXYZ","list_devise","dev_id","dev_libelle","${detailSrvBean.deviseSrv.dev_id}",true); })</script>
		      <label>Devise</label><select    required   id="devXYZ"  name="deviseSrv.dev_id"   style="width: 180px;"  ></select>
	          <table id="GRID_SAISIE_PRESATATION" class="display" width="100%"    >
			      <thead   >
			       <tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
					
						<th></th>
						<th><input   type="checkbox"   id="Cheked_unCheked1"               name="Cheked_unCheked"      ></th>
						<th><input   type="text"       id="codeFocusPrestation"    requiredPrestation        name="code_barreService"       style="width: 95%;"        requiredy ></th>
						<th><input   type="text"       id="XnextFocusPrestation"   requiredPrestation        name="XnextFocusPrestation"    style="width: 95%;"        requiredy ></th>
		                <th><input  type="number"      id="quantitePrestation"     requiredPrestation        name="quantiteService"       min="1"    value="1"    style="width: 150px;"              requiredx ></th>
						<th><select   id="isVentePrestation"  name="isVentePrestation"   > <option  value="true"  > Vente </option><option value="false">Dépense </option>  </select> </th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					
					 <tr> 
						<th></th>
						<th></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>Qté</th>
						<th>P.U.V</th>
						<th>T.H.T</th>
						<th></th>
						<th></th>
				    </tr>
				    
				 </thead>
				 </table>
	         </ext:panel>
	         <ext:panel   id="RET_GRIDX"   bodyStyle="background: none;"  onActivate="getSuivant('fourniture')"  border="false"   height="400"   hideCollapseTool="true"  title="Fourniture de vente"    >
	            <script>$(function() {loadSelectAjax("devXY","list_devise","dev_id","dev_libelle","${detailFrnBean.deviseFr.dev_id}",true); })</script>
		        <label>Devise</label><select    required   id="devXY"  name="deviseFr.dev_id"   style="width: 180px;"  ></select>
	         <table id="GRID_SAISIE_FOURNITURE_VENTE" class="display" width="100%"   >
			      <thead   >
			        <tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
					
						<th></th>
						<th><input   type="checkbox"   id="Cheked_unCheked1"      name="Cheked_unCheked"      ></th>
						<th><input   type="text"       id="codeFocuso"            name="code_barreFurniture"           style="width: 95%;"        requiredy ></th>
						<th><input   type="text"       id="XnextFocuso"           name="XnextFocuso"   style="width: 95%;"        requiredy ></th>
						<th ><input  type="number"     id="quantite_stockxc"      name="quantite_stock_fourniture"        style="width: 93%;"             ></th>
		                <th  colspan="2"><input  type="number"     id="quantiteXx"            name="quantiteFourniture"      min="1"    value="1"    style="width: 93%;"              requiredx ></th>
						<th><select   id="isVente"  name="isVente"   ><option  value="true"  > Vente </option> <option value="false">Dépense </option>  </select>   </th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					 <tr> 
						<th></th>
						<th></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>Stock</th>
						<th>Qté</th>
						<th> </th>
						<th>Dépense</th>
						<th>P.U.V</th>
						<th>T.H.T</th>
						<th></th>
						<th></th>
				    </tr>
				 </thead>
			    <tfoot>
		                    <tr  > 
								<td colspan="8" align="right"></td>
								<td ></td>
								<td ></td>
								<td ></td>
								 
					  </tr>
				 </tfoot>
			    </table>
			     <script type="text/javascript">
					function doLoaderDataFooter2( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL_FOURNITURE','json',false);
					    var totMntTTC     =json["totMntTTC"];
                        var totMntHT      =json["totMntHT"];
				        var firstitems  = {"0":"Total"    ,"1":totMntHT   ,"2":totMntTTC   };
				        var items       = {"A":firstitems };
				    return  items; 
				}
               </script>	
	         </ext:panel>
	         
	         
	         
	         
   	 </ext:tabPanel>
</ext:panel>
</ext:body>
<script type="text/javascript">
//Ext.getCmp("tab").child('#id').tab.hide()
Ext.onReady(function(){
Ext.getCmp('sdsfgrgrgpll').show();
//var tabPanel = Ext.getCmp('sdsfgrgrgpll');
//tabPanel.hideTabStripItem("RET_GRIDX"); // with tab id
 
});
</script>
