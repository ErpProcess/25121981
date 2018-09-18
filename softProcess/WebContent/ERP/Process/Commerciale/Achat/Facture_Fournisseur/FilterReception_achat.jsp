 <%@include file="/Aceuil/esProcess.jsp" %>  
 


<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">
height_tabbJQuey="auto";
width_tabbJQuey="100%";
contenu_toolbarJQuey="";
 
$(document).ready(function () {
LoadAutoCompletAjax_with_marGin("frs_id"  ,"frsref","depot_id","list_fournisseur_facture_frs","250","100");
$('#recpetion_article-Grid tbody tr').live('dblclick', function () {
      
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
                               "table"  :"recpetion_article-Grid",
                               "list"   :"List_data_achat_fact",
                               "id_name":"achat_id",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									     
 
	
	
										         
  {     "sTitle": "X"    ,    "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true    ,"bVisible": true , "mRender": function( data, type, full){
     return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[1]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[1]+'")     >';}}, 
  { "sTitle": "${achat_id}"       ,"sName": "achat_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${achat_date}"     ,"sName": "achat_date"                   ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_time}"     ,"sName": "time_cre"                        ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${achat_libelle}"  ,"sName": "achat_libelle"                   ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${frs_id}"         ,"sName": "frsBean.frsref"                  ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${depot_id}"       ,"sName": "depot.depot_libelle"          ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${mode_op}"        ,"sName": "modeBean.fct_libelle"            ,"sWidth": "10%"   , "bSortable": "true"   },
  						         
										         
									    
	                                            ]
 
 };
                               
                            
                                

function DisplayTableE(){

 
if(!teste_required()) return ;

Ext.getCmp('btValidx').disable();  
Ext.getCmp('btCommit').enable();  

Ext.getCmp('btPrintPdfx').enable();
Ext.getCmp('btExportXlsx').enable();
Ext.get('RET_GRID').setStyle('display', 'block');
 
 
			                            
var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_FETCH_TO_FACTURE','text',false);
  
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );

}
function  doResetAjaxEE(){
     $('#myformToServeur').trigger("reset");
     
    
    
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
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="10%"><label>${achat_id}</label></td>  
   <td width="93%"  >  
   <input id="achat_id"   name="achat_id"     type="text"    size="15"       maxlength="15"        value="${searchBean.achat_id}"    nextElement="dem_achat_id"    autofocus         />  
  </td>  
   </tr> 
   
  <tr>  
   <td width="10%"><label>${achat_date} debut </label></td>  
   <td width="93%"  >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.achat_date}"   var="searchat_date"/>
	     <input id="achat_date"       name="achat_date"        type="datepicker"    size="13"       maxlength="13"        value="${searchat_date}"    nextElement="achat_date2"              /></td>
	     <td><label>${achat_date} fin</label></td>
	     <td><input id="achat_date2"   name="achat_date2"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.achat_date2}"    nextElement="frs_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   <tr>  
   <td width="10%"><label>${frs_id}</label></td>  
   <td width="93%"  >  
   <input id="frs_id" name="frsBean.frs_id"         type="text"    size="10"       maxlength="10"     value="${searchBean.frsBean.frs_id}"          nextElement="depot_id"     required   /> 
   <input id="frsref" name="frsBean.frsref"        type="text"    size="30"          value="${searchBean.frsBean.frsref}"          nextElement="depot_id"                      required />
  </td>  
   </tr>   
   
 </table>   
</ext:panel>


 


<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"   style="display:none;"        >

<table id="recpetion_article-Grid"   class="display"  style="width: 100%;"  ><thead></thead><tbody></tbody>  </table> 

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
