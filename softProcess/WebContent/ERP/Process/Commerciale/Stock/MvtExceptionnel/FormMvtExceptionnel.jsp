 <%@include file="/Aceuil/esProcess.jsp" %>  
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
width_tabbJQuey="100%";

mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_RETOUR",
                               "list"   :"list_editable_RetourVente",
                               "id_name":"fkCode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "3%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[0]+'"   >';}}, 
									                  
										   {      "sTitle":"Référence"    ,"sName": "fkCode_barre.pk.code_barre"   ,"sWidth": "12%"    }, 
										         
										   {      "sTitle":"Désignation"    ,"sName": "fkCode_barre.designation_libelle"    ,"sWidth": "20%"   },       
										    
										   {      "sTitle":"Qte.SRV"    ,"sName": "quantite_en_stock"                    , "sWidth": "6%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },
										                 
										   {      "sTitle":"Qte.RET"    ,"sName": "quantite_retourne"     , "sWidth": "10%"    
										         ,"mRender": function( data, type, full){    if("${bs.fct_id}"=="33")  return  data ; else  
										          return '<input   type="number"   min="0"   style="width:70px;"     id=quantite'+full[2]+'    name=quantite    value="'+data+'"  onblur=doEnvoiDataV2(this,"'+full[2]+'")     nextElement="quantite'+full[9]+'"     >'; }},
										  
										   {      "sTitle":"Cause"    , "sName": "cause.nature_incident_id"   ,"sWidth": "22%"           , "bSortable": "true" 
	                                               ,"mRender": function( data, type, full){  
										          return '<select  id="Cause_ID'+full[2]+'"    name=cause.nature_incident_id     onchange=doEnvoiDataV2(this,"'+full[2]+'")        ></select>'; }   },
										          
										   {      "sTitle":"Stock"    , "sName": "sens.data_id"   ,"sWidth": "13%"           , "bSortable": "true" 
	                                               ,"mRender": function( data, type, full){ 
	                                              
										          return '<select  id="senseOp'+full[2]+'"    name=sens.data_id     onchange=doEnvoiDataV2(this,"'+full[2]+'")        ></select>'; }   },           
										    
										   
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
 }  
                           
$(document).ready(function (){

LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
});                                     

</script>

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${mvt_excep_date}</label></td>  
   <td width="93%"  >  
 
   
   
   <input id="mvt_excep_date" name="mvt_excep_date"   
     type="datepicker"    size="13"       maxlength="13"       
      value="${detailBean.mvt_excep_date}"    nextElement="mvt_excep_obs"    autofocus   required     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${mvt_excep_obs}</label></td>  
   <td width="93%"  >  
   <input id="mvt_excep_obs" name="mvt_excep_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.mvt_excep_obs}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
 
  <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille retour vente"    > 
	   <table id="GRID_SAISIE_DETAIL_RETOUR" class="display" width="100%" >
	   </table>
  </ext:panel> 
  
  
</ext:panel>
</ext:body>
