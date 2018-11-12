<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


<script type="text/javascript">

function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=list_editable_recep_achat",'i$_ACT_VERIF_LIST','text',false);
return  retournX ==""?"":"Veillez Remplir le détaille de la réception";
}


$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","quantite","list_article_recp_achat","400","500");
//LoadOtherAutocompletesAjax("pk.code_barre","i$_ACT_LOAD_LOT_ARTICLE","num_lot","date_peremption","quantite");
});
$(document).ready(function () {
 if("${bs.fct_id}"=="3"){
 
    $("#tblData").find("select:not([libre])").attr("disabled",true);
    $("#tblData").find("input[idonly]").attr("readonly",true);	
    $("#tblData").find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly",true);
    
 } else {
 LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","avance_montant_achat","list_depot_stock","250","100");
 LoadAutoCompletAjax_with_marGin("frs_id"  ,"frsref","depot_id","list_fournisseur_recep_achat","250","100");

 }   
 
});  

 mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_AHCAT",
                               "list"   :"list_editable_recep_achat",
                               "id_name":"pk.fkCode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
								 {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
								 {      "sName": "to_check"                           , "sWidth": "1%"      ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")     >';}}, 
									                  
								 {      "sName": "pk.fkCode_barre.pk.code_barre"          , "sWidth": "20%"      , "bSortable": true    , "bSearchable": true      }, 
										         
								 {      "sName": "pk.fkCode_barre.designation_libelle"     , "sWidth": "30%"     , "bSortable": true    , "bSearchable": true     },
										          
							     {      "sWidth": "10%"  , "sName": "date_utilisation"      , "sWidth": "5%"    , "bSortable": true    , "bSearchable": true      ,"mRender": function( data, type, full){  
										          return '<input   type="datepicker"      compareTo="date_peremption'+full[2]+'"     comparedateSys="dateSys"     id=date_utilisation'+full[2]+' name=date_utilisation  value="'+data+'"     onblur=doEnvoiDataV2(this,"'+full[2]+'")       >'; }}, 
										          
								 {      "sWidth": "10%"  , "sName": "date_peremption"      , "sWidth": "4%"     , "bSortable": true    , "bSearchable": true       ,"mRender": function( data, type, full){  
										 return '<input   type="datepicker"      comparedTo="date_utilisation'+full[2]+'"   comparedateSys="dateSys"         id=date_peremption'+full[2]+'  name=date_peremption  value="'+data+'"      onblur=doEnvoiDataV2(this,"'+full[2]+'")        >'; }},         
										                 
								 {      "sWidth": "10%"  , "sName": "quantite"              , "sWidth": "10%"    , "bSortable": true    , "bSearchable": true      ,"mRender": function( data, type, full){  
										          return '<input   type="number"       id=quantite'+full[2]+'    name=quantite    value="'+data+'"    style="width: 90px;"        onblur=doEnvoiDataV2(this,"'+full[2]+'")     nextElement="unite_id'+full[2]+'"     >'; }},   
										  
								 {      "sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"    , "sWidth": "10%"       , "bSortable": true    , "bSearchable": true      },
										           
										           
								 {      "sTitle":"Prix U"  , "sName": "tarif.tarif_unit_article"   , "sWidth": "10%"       , "bSortable": true    , "bSearchable": true      ,"sClass" : "alignRight" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                              
	                             {      "sTitle":"Mnt_T_H_A" , "sName": "montant_ht_achat"  ,"sClass" : "alignRight"     , "sWidth": "10%"        , "bSortable": true    , "bSearchable": true   
	                                              ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true    },        
										         
								 {      "sTitle": "Codes" ,   "sTitle": "next"     ,"sName": "indx_row_next"         ,"bVisible": false },
										          
	                                            ]
 
                               };
 
 
 

function ADD(){

    var  testeInto=true;      
    var  testecheko=false;      
      $(":input[requiredx]").each(function (cnt, item) {                     
        var $myFormxx = $("#myformToServeur");
          if(!$(item).val()) {  $(item).css('border-color', 'red'); testeInto=false; }else{ $(item).css('border-color', ''); }
          if($(item).is("input[type=radio]")) {  $(item).addClass("ppd"); 
          if($(item).is(":checked"))  { $(item).addClass("ppd");    } else{    $(item).addClass("pp");   testecheko=true;      }} 
        });
        if(!testeInto){
      var frss="${_chamvideRouge}";
     Ext.MessageBox.show({
           title:frss,
           msg: "${_chamobligatoire}",
           buttons: Ext.Msg.OK,
          // fn: showResult,
           animEl: 'mb4',
           icon: Ext.MessageBox.WARNING
       });
  }else{
  
 
  jQuery.ajax({ type: 'POST',  
	                url: '${tmlx.urlAjax}', 
	                data:{  
	                'ar_id':$('input[id="pk.code_barre"]').val(),
	                'designation':$('#designation_libelle').val(),
	                'date_peremptionx':$('input[id="date_peremptionx"]').val()   ,  
	                'date_utilisationx':$('input[id="date_utilisationx"]').val(),
	                'HiddenAction':'i$_ACT_ADD_ROW',
	                'nameList':'list_editable_recep_achat',
	                'quantite':$('#quantite').val()},
	                dataType: 'text', 
	                success: function(data){
	                otab_otra.fnAdjustColumnSizing();
	               $('input[id="pk.code_barre"]').val('');
	               $('input[id="date_utilisationx"]').val('');
	               $('input[id="date_peremptionx"]').val('');
	               $('#designation_libelle').val('');
	               $('#quantite').val('1');
	               $('input[id="pk.code_barre"]').blur();
	               $('input[id="pk.code_barre"]').focus();
                   },
                  error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
  } 
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

 
 
 function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
      
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
            var QteNew="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
            
	      $(this).find('td:eq(8)').html(json[qsdqsqd]) ;
	      $(this).find('td:eq(5)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	    
	      if(  json[erreurX]  !=""){
	           mayBox_al(json[erreurX] ,"xx");
	      }
	      
	   });
       otab_otra.fnAdjustColumnSizing();
 } 
 
  

  
 
								                        
 </script>
 
 
                
               	 <script>
function getSuiv(){
if(!teste_required()) return ;
  
  $("#frs_id").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#frsref").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});


$("#depot_id").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#depot_libelle").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});



$('#frs_id').attr("readonly","readonly");
$('#frsref').attr("readonly","readonly");
$('#depot_id').attr("readonly","readonly");
$('#depot_libelle').attr("readonly","readonly");
$('#avance_montant_achat').attr("readonly","readonly");
Ext.getCmp('btnnext').disable(); 
Ext.get('RET_GRID').setStyle('display', 'block');
$('#GRID_SAISIE_DETAIL_AHCAT').css('display', 'block');
var valr=fnAjax_WithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_TARIF','text',false,null);

height_tabbJQuey="auto";
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
} 
	 
	 
	 </script>
  
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"     height="235"  >  
   
 
    <ext:toolbar         toolbarType="bbar"     > 
        <ext:toolbar.button  text=" Suivant  >> "          onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
   
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   > 
		  
		   <tr>  
		      <td width="10%"><label>${achat_id}</label></td>  
		      <td width="40%"  >  
		      <input id="achat_id" name="achat_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		      </td> 
		      <td width="10%"><label>${achat_libelle}</label></td>  
		      <td width="40%"  >  
		      <input id="achat_libelle" name="achat_libelle"     type="text"    size="46"       maxlength="200"        value="${detailBean.achat_libelle}"    nextElement="frs_id"       autofocus       />  
		      </td> 
		    </tr> 
		    
		    <tr>  
		      <td width="10%"><label>${achat_date}</label></td>  
		      <td width="40%"  >
		       <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat_date}"   var="detailachat_date"/>   
		      <input id="achat_date" name="achat_date"     type="datepicker"    size="15"       maxlength="15"        value="${detailachat_date}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		      </td> 
		      <td width="10%"><label>${frs_id}</label></td>  
		      <td width="40%"  >  
                     <input id="frs_id" name="frsBean.frs_id"         type="text"    size="10"       maxlength="10"     value="${detailBean.frsBean.frs_id}"          nextElement="depot_id"   required    /> 
			         <input id="frsref" name="frsBean.frsref"        type="text"    size="30"          value="${detailBean.frsBean.frsref}"          nextElement="depot_id"   required    />
		      </td> 
		    </tr> 
		  
		    <tr>  
		      <td width="10%"><label>avance_montant_achat</label></td>  
		      <td width="40%"  >
		         <input id="avance_montant_achat" name="avance_montant_achat"     type="montant3"    size="45"    libre   maxlength="50"        value="${detailBean.avance_montant_achat}"    nextElement="achat_obs"              />  
		      </td> 
		      <td width="10%"><label>${depot_id}</label></td>  
		      <td width="40%"  >  
                   <input id="depot_id" name="depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="avance_montant_achat"                  required    />  
				   <input id="depot_libelle" name="depot.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="avance_montant_achat"   required     />
		      </td> 
		    </tr> 
		    <tr>  
		      <td ><label>${achat_obs}</label></td>  
		      <td     >   
		        <textarea   id="achat_obs" name="achat_obs"   rows="3" cols="47"      onblur="getSuiv()"  >${detailBean.achat_obs}</textarea>  
		      </td>  
		      
		      <td ><label>Devise</label></td>  
		      <td     >   
		        <script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",false); })</script>
		        <select   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  libre  ></select>
		                  
		      </td>  
		      
		   </tr>  
		 </table> 
		 
		 
	 </ext:panel>
	 
	 

	 
	         <ext:panel      id="RET_GRID"   bodyStyle="background: none;"    border="false"    title="Détaille reception article"     style="display: none;"    > 
	         
			    <table id="GRID_SAISIE_DETAIL_AHCAT" class="display" width="99%"  style="display: none;" >
			      <thead   >
					<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
						<th></th>
						<th   ><input  type="checkbox"    id="Cheked_unCheked"     name="Cheked_unCheked"      onclick="doCheked_unCheked(this)"     ></th>
						<th><input  type="text"        id="pk.code_barre"       name="pk.code_barre"        style="width: 200px;"    requiredx ></th>
						<th><input  type="text"        id="designation_libelle" name="designation_libelle"  style="width: 300px;"    requiredx ></th>
						<th><input  type="datepicker"  id="date_utilisationx"   name="date_utilisationx"    compareTo ="date_peremptionx"    comparedateSys="dateSys"         nextElement="date_peremption"  ></th>
						<th><input  type="datepicker"  id="date_peremptionx"    name="date_peremptionx"     comparedTo="date_utilisationx"       comparedateSys="dateSys"         nextElement="quantite"    > </th>
						<th><input  type="number"      id="quantite"            name="quantite"             min="1"    value="1"  style="width: 100px;"     requiredx          ></th>
						<th><input  type="hidden"      id="unite_ids"           name="unite"   ></th>
						 
						<th></th>
						<th></th>
						<th></th>
					</tr>
					 <tr> 
						<th></th>
						<th   ></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>date Util.</th>
						<th></th>
						<th>Qté</th>
						<th>Unite</th>
						 
						<th>Prix.U</th>
						<th>Montant.H.T</th>
						<th></th>
				    </tr>
				 </thead>
			    
			     <tfoot>
				   <tr align="right"> 
						<td colspan="6"></td>
						<td align="left"></td>
						<td  colspan="2" ></td>
						<td></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td colspan="6"></td>
						<td align="left"></td>
						<td  colspan="2" ></td>
						<td></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td colspan="6"></td>
						<td align="left"></td>
						<td  colspan="2" ></td>
						<td></td>
						<td></td>
				    </tr>
				    
				    <tr align="right" > 
						<td colspan="6"></td>
						<td align="left"></td>
						<td  colspan="2" ></td>
						<td></td>
						<td></td>
				    </tr>
				    
				    
				 </tfoot>
			    </table>
			    <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    
					    var qtessS    =json["UU"];
                        var tot_h_tvaS=json["k"];
			            var tot_tvaS  =json["o"];
			            var tot_GenS  =json["p"];
			            var tot_avance  =json["avance"];  //json["avance"];
			            
				        var firstitems  = {"0":"Total Qté"    ,"1":qtessS   ,"2":"Total.H.T"  ,"3":tot_h_tvaS  };
				        var secondItems = {"2":"Total TVA"    ,"3":tot_tvaS };
				        var secondItem2 = {"2":"A compte"     ,"3":tot_avance };
				        var TroisIemIte = {"2":"Total Général","3":tot_GenS };
				        var items       = {"A":firstitems,"B":secondItems,"C":secondItem2,"D":TroisIemIte};
				    return  items; 
				}
               </script>
               

	         </ext:panel>
    
		 
		   
</ext:panel>
</ext:body>
