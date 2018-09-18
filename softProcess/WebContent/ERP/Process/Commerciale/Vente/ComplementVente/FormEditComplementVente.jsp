<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
width_tabbJQuey="100%";
 
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_COMPLEMENT",
                               "list"   :"list_editable_ComplementVente",
                               "id_name":"pk.detv.pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "3%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")          >';}}, 
									                  
										   {      "sTitle":"Référence"    ,"sName": "pk.detv.pk.fkcode_barre.pk.code_barre"   ,"sWidth": "12%"    }, 
										         
										   {      "sTitle":"Désignation"    ,"sName": "pk.detv.pk.fkcode_barre.designation_libelle"    ,"sWidth": "20%"   },       
										    
										   {      "sTitle":"Qte.SRV"    ,"sName": "pk.detv.quantite_confirmer"                    , "sWidth": "6%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },
										                 
										   {      "sTitle":"Qte.Ajouter"    ,"sName": "quantite_ajoute"                             , "sWidth": "10%"     ,"mRender": function( data, type, full){  
										          return '<input   type="number"   min="0"   style="width:70px;"     id=quantite_ajoute'+full[2]+'    name=quantite_ajoute    value="'+data+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")          >'; }},
										           
										   {      "sTitle":"Prix U"    , "sName": "pk.detv.tarif.tarif_unit_vente"   ,"sWidth": "5%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_complement"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										   
	                                            ]
 
                               };
 

 


function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
     
      
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
           
            var QteNew ="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
            
            
            
          
             
	      $(this).find('td:eq(7)').html(json[qsdqsqd]) ;
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
   <td width="7%"><label>${comp_vente_id}</label></td>  
   <td width="93%"  >  
   <input id="comp_vente_id" name="comp_vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.comp_vente_id}"    nextElement="vente_id"    autofocus    readonly="readonly"  libre     />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${vente_id}</label></td>  
   <td width="93%"  >  
   <input id="vente_id" name="vente.vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.vente.vente_id}"    nextElement="ret_vente_date"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${comp_vente_date}</label></td>  
   <td width="93%"  >  
   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.comp_vente_date}"   var="dateInputString"/>   
   <input id="comp_vente_date" name="comp_vente_date"     type="datepicker"    size="13"       maxlength="13"        value="${dateInputString}"    nextElement="re_vente_obs"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${comp_vente_obs}</label></td>  
   <td width="93%"  >  
   <input id="comp_vente_obs" name="comp_vente_obs"     type="text"    size="50"       maxlength="50"        value="${detailBean.comp_vente_obs}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>  
</ext:panel> 
 
  <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Complement vente"    > 
	   <table id="GRID_SAISIE_DETAIL_COMPLEMENT" class="display" width="100%" >
	   </table>
  </ext:panel> 
  
</ext:panel>


</ext:body>
