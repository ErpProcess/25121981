<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">
height_tabbJQuey="auto";
width_tabbJQuey="100%";
contenu_toolbarJQuey="";
 
$(document).ready(function () {
LoadAutoCompletAjax_with_marGin("clt_id"  ,"clt_lib","","list_client_for_facture","250","100");
$('#ProcedureVente-Grid tbody tr').live('dblclick', function () {
      
		      var aData = otab_otra.fnGetData( this );
		      var iId = aData[0];
		       
		       if ( jQuery.inArray(iId, gaiSelected) == -1 ){
			        gaiSelected[gaiSelected.length++] = iId;
		         }else{
			        gaiSelected = jQuery.grep(gaiSelected, function(value) {
				    return value != iId;} );
		         }
		        
		   var indexRowSel = otab_otra.fnGetPosition( this );
		   
		   $(this).toggleClass('row_selected');
		 
		   getDetailRowFromServer(indexRowSel);
} );



});

mapEditableGen = {             "otab"    :oTable,
                               "table"  :"ProcedureVente-Grid",
                               "list"   :"procedurevente_list_facture",
                               "id_name":"vente_id",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									     
									       
									       {     "sTitle": "X"    ,    "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true    ,"bVisible": true , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[1]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[1]+'")     >';}}, 
									                  
										   {     "sTitle": "${vente_id}"    ,    "sName": "vente_id"   ,"sWidth": "10%"    }, 
										         
										   {     "sTitle": "${vente_date}"    ,    "sName": "vente_date"    ,"sWidth": "25%"   },       
										    
										   {      "sTitle": "${clt_id}"    ,  "sName": "client.clt_lib"                    , "sWidth": "35%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },           
										                 
										   {      "sTitle": "${_mode}"    ,  "sName": "modeBean.fct_libelle"                             , "sWidth": "5%"     ,"bSearchable": true  , "bSortable": true   },   
										         
									    
	                                            ]
 
 };
                               
                            
                               

function DisplayTableE(){

if(!teste_required()) return ;

Ext.getCmp('btValidx').disable();  
Ext.getCmp('btCommit').enable();  

Ext.getCmp('btPrintPdfx').enable();
Ext.getCmp('btExportXlsx').enable();
Ext.get('RET_GRID').setStyle('display', 'block');
 
			                            
var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_FETCH_AJAX_VENTE','text',false);
  
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );

}
function  doResetAjaxEE(){
     $('#myformToServeur').trigger("reset");
     effacerWX() ;
    
    
     Ext.get('RET_GRID').setStyle('display', 'none');
     
      
 
    Ext.getCmp('btCommit').disable();  
    Ext.getCmp('btValidx').enable();
    Ext.getCmp('btPrintPdfx').disable(); 
	Ext.getCmp('btExportXlsx').disable();
	if (oTable != undefined) {
 
 
oTable.fnClearTable();
oTable.fnDraw();
oTable.fnDestroy();


	} 
}


 
 
  
                           
 

</script>

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel   border="false"    bodyStyle="background: none;"      title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"  ><label>${vente_id}</label></td>  
   <td width="90%"  >  
   <input id="vente_id"   name="vente_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.vente_id}"    nextElement="cmd_id"    autofocus         />  
  </td>  
   </tr> 
   
   
    <tr>  
   <td  ><label>${cmd_id}</label></td>  
   <td   >  
   <input id="cmd_id"   name="commande.cmd_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.commande.cmd_id}"    nextElement="vente_libelle"              />  
  </td>  
   </tr> 
     
   <tr>  
   <td  ><label>${vente_libelle}</label></td>  
   <td   >  
   <input id="vente_libelle"   name="vente_libelle"     type="text"    size="15"       maxlength="15"        value="${searchBean.vente_libelle}"    nextElement="achat_date"              />  
  </td>  
   </tr>   
  <tr>  
   <td ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.vente_date}"   var="searchat_date"/>
	     <input id="vente_date"             compareTo="vente_date2"      name="vente_date"   autocomplete="off"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="vente_date2"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	      <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.vente_date2}"   var="searchvente_date2"/>
	     <input id="vente_date2"        comparedTo="vente_date"      name="vente_date2"   autocomplete="off"      type="datepicker"    size="13"       maxlength="13"        value="${searchvente_date2}"    nextElement="clt_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   <tr>  
   <td  ><label>${clt_id}</label></td>  
   <td   >  
   <input id="clt_id" name="client.clt_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.client.clt_id}"          nextElement="depot_id"     required     /> 
   <input id="clt_lib" name="client.clt_lib"        type="text"    size="30"          value="${searchBean.client.clt_lib}"          nextElement="depot_id"                   required  />
  </td>  
   </tr>   
   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"   style="display:none;"        >

<table id="${nameGrid}"   class="display"  style="width: 100%;"  ><thead></thead><tbody></tbody>  </table> 

</ext:panel>
</ext:panel>
</ext:body>
<script>
Ext.onReady(function(){  
try {	    
var btnTemp = document.getElementById('ESPACE_TROIS');
btnTemp.style.width='2px'; 
<c:if test="${not empty dataListAajx}">       
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  ); 
Ext.get('RET_GRID').setStyle('display', 'block');    

</c:if>           
} catch(e){
}	         
});
</script>        
