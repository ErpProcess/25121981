<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
width_tabbJQuey="100%";

mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_RETOUR",
                               "list"   :"list_editable_RetourVente",
                               "id_name":"pk.detv.pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "3%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[0]+'"   >';}}, 
									                  
										   {      "sTitle":"Référence"    ,"sName": "pk.detv.pk.fkcode_barre.pk.code_barre"   ,"sWidth": "12%"    }, 
										         
										   {      "sTitle":"Désignation"    ,"sName": "pk.detv.pk.fkcode_barre.designation_libelle"    ,"sWidth": "20%"   },       
										    
										   {      "sTitle":"Qte.SRV"    ,"sName": "pk.detv.quantite_confirmer"                    , "sWidth": "6%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },
										                 
										   {      "sTitle":"Qte.RET"    ,"sName": "quantite_retourne"     , "sWidth": "10%"    
										    ,"mRender": function( data, type, full){    if("${bs.fct_id}"=="33")  return  data ; else  
										          return '<input   type="number"   min="0"   style="width:70px;"     id=quantite_retourne'+full[2]+'    name=quantite_retourne    value="'+data+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")     nextElement="quantite'+full[9]+'"     >'; }},
										  
										   //{     "sTitle":"Unite"    , "sName": "pk.detv.pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "5%"     ,"bSearchable": true },
										   
										   
										   {      "sTitle":"Cause"    , "sName": "cause.nature_incident_id"   ,"sWidth": "22%"           , "bSortable": "true" 
	                                               ,"mRender": function( data, type, full){  
										          return '<select  id="Cause_ID'+full[2]+'"    name=cause.nature_incident_id     onchange=doEnvoiDataV2(this,"'+full[2]+'")        ></select>'; }   },
										          
										   {      "sTitle":"Stock"    , "sName": "sens.data_id"   ,"sWidth": "13%"           , "bSortable": "true" 
	                                               ,"mRender": function( data, type, full){ 
	                                              
										          return '<select  id="senseOp'+full[2]+'"    name=sens.data_id     onchange=doEnvoiDataV2(this,"'+full[2]+'")        ></select>'; }   },           
										           
										           
										   {      "sTitle":"Prix U"    , "sName": "pk.detv.tarif.tarif_unit_vente"   ,"sWidth": "5%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_retour"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										   
	                                            ]
 
                               };



function FnLoadSelectAjax(objeJson){

 var data=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_SELECT','json',false);

        for ( var q=0  ; q<objeJson.aaData.length ; q++ ) {
			          
				 		  var id_region="Cause_ID"+objeJson.aaData[q][2];
				 		  var idInputcode_barre=objeJson.aaData[q][6];
				 		  $('option', '#'+id_region).remove();
			              var $regions = $('#'+id_region);
			              $regions.append('<option value=""    >--selectionnez--</option>');
			              for (var h = 0; h <data.mylistec.length; h++) {
			              var  sel='';
			              if( data.mylistec[h].keyx==idInputcode_barre){
			                 sel=' selected ';
			              }
			              $regions.append('<option value="'+data.mylistec[h].keyx+'"      '+sel+'      >'+ data.mylistec[h].valuex +'</option>');
			             } 
			             
			             
			              var id_region="senseOp"+objeJson.aaData[q][2];
				 		  var idInputcode_barre=objeJson.aaData[q][7];
				 		  $('option', '#'+id_region).remove();
			              var $regions = $('#'+id_region);
			               if("${bs.fct_id}" !="33")  
			              $regions.append('<option value=""    >--selectionnez--</option>');
			              
			              for (var h = 0; h <data.mylistes.length; h++) {
			              var  sel='';
			              
			               
			                
			              if( data.mylistes[h].keyx==idInputcode_barre){
			                 sel=' selected ';
			              }
			              
			                if("${bs.fct_id}"=="33"  )  {
			                
			                if(sel==" selected ")
			                 $regions.append('<option value="'+  data.mylistes[h].keyx +'"      '+sel+'      >'+ data.mylistes[h].valuex +'</option>');
			                 
			                }else{
			                
			                  $regions.append('<option value="'+  data.mylistes[h].keyx +'"      '+sel+'      >'+ data.mylistes[h].valuex +'</option>');
			                  
			                }
			                
			            
			              
			              
			             } 
	
           }    

}

 


function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
     
      
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
           
            var QteNew ="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
            
            
            
          
             
	      $(this).find('td:eq(8)').html(json[qsdqsqd]) ;
	      $(this).find('td:eq(5)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	    
	      if(  json[erreurX]  !=""){
	           alertExt("",json[erreurX],"4");
	      }
	      
	   });
	  /* LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
       config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );*/
 
 }  
                           
$(document).ready(function (){

LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
});                                     

</script>


  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
 <ext:panel    bodyStyle="background: none;"    border="false"      >   
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${ret_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="ret_vente_id" name="ret_vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.ret_vente_id}"    nextElement="vente_id"    autofocus    readonly="readonly"  libre     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id" name="vente.vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.vente.vente_id}"    nextElement="ret_vente_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${ret_vente_date}</label></td>  
   <td width="93%"  >  
   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.ret_vente_date}"   var="dateInputString"/>   
   <input id="ret_vente_date" name="ret_vente_date"     type="datepicker"    size="13"       maxlength="13"        value="${dateInputString}"    nextElement="re_vente_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${re_vente_obs}</label></td>  
   <td width="93%"  >  
   <input id="re_vente_obs" name="re_vente_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.re_vente_obs}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>  
</ext:panel> 
 
  <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille retour vente"    > 
	   <table id="GRID_SAISIE_DETAIL_RETOUR" class="display" width="100%" >
	   </table>
  </ext:panel> 
  
</ext:panel>


</ext:body>
