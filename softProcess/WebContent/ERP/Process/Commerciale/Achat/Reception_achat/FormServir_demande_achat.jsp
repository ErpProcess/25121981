<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


<script type="text/javascript">
height_tabbJQuey="auto";
function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=list_editable_recep_achat",'i$_ACT_VERIF_LIST','text',false);
return  retournX ==""?"":"Veillez Remplir le détaille de la réception";

}


width_tabbJQuey="100%";
$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","num_lot","list_article_recp_achat","400","500");
 
});
/*function loadLesListeCorrlee(objeJson){
 
      for ( var q=0  ; q<objeJson.aaData.length ; q++ ) {
 		  var inde=objeJson.aaData[q][2];
 		  var idInputcode_barre=objeJson.aaData[q][2];
 		  var idInputnum_lot="num_lot"+inde;
          var idInputdate_peremption="date_peremption"+inde;
          var idInputquantite="pk.quantite"+inde;
          LoadOtherAutocompletesAjax_grid(idInputcode_barre,"i$_ACT_LOAD_LOT_ARTICLE",idInputnum_lot,idInputdate_peremption,"num_lot","date_peremption",idInputquantite,false);
      }  
      
}*/


$(document).ready(function () {
 if( "${bs.fct_id}"=="16" || "${bs.fct_id}"=="3"  ){
    $("#tblData").find("select:not([libre])").attr("disabled",true);
    $("#tblData").find("input[idonly]").attr("readonly",true);	
    $("#tblData").find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly",true);
    LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","achat_obs","list_depot_stock","250","100");  
 } else {
 LoadAutoCompletAjax_with_marGin("frs_id","frsref","depot_id","list_fournisseur_recep_achat","250","100"); 
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
									       
									       {      "sName": "to_check"      ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[0]+'")       nextElement="pk.code_barre'+full[0]+'"   >';}}, 
									                  
										   {      "sName": "pk.fkCode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "pk.fkCode_barre.designation_libelle"    ,"sWidth": "30%"   },   
										       
										          
										   {      "sWidth": "10%"  , "sName": "date_utilisation"    ,"mRender": function( data, type, full){  
										          return '<input   type="datepicker"      compareTo="date_peremption'+full[2]+'"     comparedateSys="dateSys"     id=date_utilisation'+full[2]+' name=date_utilisation  value="'+data+'"     onblur=doEnvoiDataV2(this,"'+full[2]+'")       >'; }}, 
										          
										   {      "sWidth": "10%"  , "sName": "date_peremption"    ,  "bVisible": true  ,"mRender": function( data, type, full){  
										          return '<input   type="datepicker"      comparedTo="date_utilisation'+full[2]+'"   comparedateSys="dateSys"   id=date_peremption'+full[2]+'  name=date_peremption  value="'+data+'"      onblur=doEnvoiDataV2(this,"'+full[2]+'")        >'; }},         
										     
										    
										   {      "sWidth": "10%"  , "sName": "quantite_demander"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },
										              
										                 
										   {      "sWidth": "10%"  , "sName": "quantite"    ,"mRender": function( data, type, full){  
										          return '<input   type="number"  size="7"      id=quantite'+full[2]+'    name=quantite    value="'+data+'"    style="width: 70px;"   onblur=doEnvoiDataV2(this,"'+full[2]+'")     nextElement="pk.quantite'+full[10]+'"     >'; }},   
										  
										   {      "sWidth": "10%"  , "sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"  ,"bSearchable": true },
										           
										           
										   {      "sTitle":"Prix_U_A"  , "sName": "tarif.tarif_unit_article"      ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Mnt_T_H_A" , "sName": "montant_ht_achat"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };
    
                       
                               
function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
            var QteNew="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
            
	      $(this).find('td:eq(9)').html(json[qsdqsqd]) ;
	      $(this).find('td:eq(6)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	    
	      if(  json[erreurX]  !=""){
	           mayBox_al(json[erreurX] ,"xx");
	      }
	      
	   });
 
 }    
                           
                                                                                         
$(document).ready(function () {
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});
 
 
  
 

 



function ADD(){

    var  testeInto=true;      
    var  testecheko=false;      
      $(":input[requiredx]").each(function (cnt, item) {                     
        var $myFormxx = $("#myformToServeur");
          if(!$(item).val()) {  $(item).css('border-color', 'red'); testeInto=false; }else{ $(item).css('border-color', ''); }
          if($(item).is("input[type=radio]")) {  $(item).addClass("ppd"); 
          if($(item).is(":checked"))  { $(item).addClass("ppd");    } else{    $(item).addClass("pp");   testecheko=true;      }} 
          if ($myFormxx[0].checkValidity()) { testeInto=true;} 
        });
        if(!testeInto){
      var frss="${_chamvideRouge}";
     Ext.MessageBox.show({
           title:frss,
           msg: "${_chamobligatoire}",
           buttons: Ext.Msg.OK,
           fn: showResult,
           animEl: 'mb4',
           icon: Ext.MessageBox.WARNING
       });
  }else{
  
 
  jQuery.ajax({ type: 'POST',  
	                url: '${tmlx.urlAjax}', 
	                data:{  'date_peremption':$('input[id="date_peremption"]').val()   ,  'num_lot':$('input[id="num_lot"]').val(),'HiddenAction':'i$_ACT_ADD_ROW','nameList':'list_editable_recep_achat','ar_id':$('input[id="pk.code_barre"]').val(),'designation':$('#designation_libelle').val(),'quantite':$('#quantite').val()},
	                dataType: 'text', 
	                success: function(data){
				    LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
                    
	               $('input[id="pk.code_barre"]').val('');
	               $('input[id="num_lot"]').val('');
	               $('input[id="date_peremption"]').val('');
	               $('#designation_libelle').val('');
	               $('#quantite').val('');
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
	                LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
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
	             
					   LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
					   config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
	               
	               
	               
	               
                      },
                 error: function (request, status, error) {
                         alert(request.responseText);
                  } 
    });
}   
  
 
								                        
 </script>
 
  
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"       >
   
   <table class="tableStyleContent"  cellpadding="1" cellspacing="1"  width="100%"    >
   <tr>

   
    <td  width="50%" >
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblDcccata"   width="100%"  >  
		   <tr>  
		   <td width="20%"><label>${dem_achat_id}</label></td>  
		   <td width="80%"  >  
		   <input id="dem_achat_id"  name="dem_achat.dem_achat_id"     type="text"    size="15"       maxlength="15"     libre  readonly="readonly"        value="${detailBean.dem_achat.dem_achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${dem_date}</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.dem_achat.dem_date}"   var="detailBeandm_date"/>
		   <input id="dem_date" name="dem_achat.dem_date"     type="text"    size="15"       maxlength="15"    libre    value="${detailBeandm_date}"     libre  readonly="readonly"              />  
		  </td>  
		   </tr>  
		   
		   <tr>  
		   <td width="7%"><label>${frs_id}</label></td>  
		   <td width="93%"  >  
		    <input id="frs_id" name="dem_achat.frsBean.frs_id"       libre    readonly="readonly"      type="text"     size="10"             value="${detailBean.dem_achat.frsBean.frs_id}"         nextElement="depot_id"   required    /> 
			<input id="frsref" name="dem_achat.frsBean.frsref"       libre    readonly="readonly"      type="text"     size="30"             value="${detailBean.dem_achat.frsBean.frsref}"          nextElement="depot_id"   required    />
		  </td>  
		   </tr>   
		    
		   <tr>  
		   <td  ><label>Devise</label></td>  
		   <td    >
		   
		 <script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",true); })</script>
		        <select   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select> 
		  </td>  
		   </tr>   
		   
		    
		    
		   
		 </table> 
    
    
    
    
    </td>
    
    
    
        <td width="50%">
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >  
		   <tr>  
		   <td width="20%"><label>${achat_id}</label></td>  
		   <td width="80%"  >  
		 
		   <input id="achat_id" name="achat_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${achat_libelle}</label></td>  
		   <td   >  
		   <input id="achat_libelle" name="achat_libelle"     type="text"    size="15"       maxlength="15"    libre    value="${detailBean.achat_libelle}"    nextElement="achat_date"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${achat_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat_date}"   var="detailachat_date"/> 
		   <input id="achat_date" name="achat_date"     type="text"    size="13"       maxlength="13"        value="${detailachat_date}"    nextElement="frs_id"      readonly="readonly"         />  
		  </td>  
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td    >  
					   <input id="depot_id" name="depot.depot_id"    libre  type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="btValidx"                  required    />  
					   <input id="depot_libelle" name="depot.depot_libelle"  libre    type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="btValidx"   required     />
		 
		  </td>  
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${achat_obs}</label></td>  
		   <td   >  
		   <input id="achat_obs" name="achat_obs"     type="text"    size="50"    libre   maxlength="50"        value="${detailBean.achat_obs}"    nextElement="btValidx"              />  
		  </td>  
		   </tr>   
		 </table> 
    
    
    
     </td>
   </tr>
   
   </table>
     
		
	 </ext:panel>
	 <ext:tabPanel  activeTab="RET_GRID"  title="Détaille Achat"  border="true"  style="padding:5px 5px 5px 5px;" >
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Achat"    > 
	          
	        
			    <table id="GRID_SAISIE_DETAIL_AHCAT" class="display" width="100%" >
			    
			      <thead   >
					<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
					
						<th></th>
						<th><input  type="checkbox"   id="Cheked_unCheked"       name="Cheked_unCheked"          onclick="doCheked_unCheked(this)"     ></th>
						<th><input  type="text"       id="pk.code_barre"         name="pk.code_barre"            style="width: 200px;"    requiredx ></th>
						<th><input  type="text"       id="designation_libelle"   name="designation_libelle"      style="width: 300px;"    requiredx ></th>
						<th><input  type="text"       id="num_lot"               name="num_lot"                  style="width: 70px;"     requiredx ></th>
						<th><input  type="text"       id="date_peremption"       name="date_peremption"          style="width: 70px;"     requiredx   readonly="readonly"  > </th>
		   <th colspan="2" ><input  type="number"     id="quantite"              name="quantite"                 style="width: 70px;"    nextElement="unite"     requiredx ></th>
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
						<th>Lot</th>
						<th>Date.Lot</th>
						<th>Qté.DEM</th>
						<th>Qté.SRV</th>
						<th>Unite</th>
						<th>P.U.HT</th>
						<th>Mnt.T.HT</th>
						<th></th>
				    </tr>
				 </thead>
				 
				 <tbody></tbody>
			    
			    </table>
	         </ext:panel>
     </ext:tabPanel>
		 
		   
</ext:panel>
</ext:body>
