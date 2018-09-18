<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
  
  
<script  src="<%=request.getContextPath() %>/jQuery/dataTables.rowsGroup.js"                type="text/javascript"  ></script> 
<script type="text/javascript">
 

var otREserv;
var maptableAjax = {"table":"${nameGrid}","list":"${nameList}","url":"${tmlx.urlAjax}","action":"${action_fetch_ajax_global}","mapCol":mapColumsbean};
var mapGen=        {"table":"${nameGrid}","list":"${nameList}","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_DATA_TABLE_AJAX","mapCol":mapColumsbean};
var mapGenEE=      {"table":"${nameGrid}","list":"${nameList}","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_DATA_BACK","mapCol":mapColumsbean}; 

function  LoadDataFromServer(mapDone){   
// alert('A');
if(dataGridConfig_width== undefined) dataGridConfig_width="100%"; 

  oTable = $('#'+mapDone['table']).dataTable( {
    
                "fnFooterCallback": function( nRow, aData, iStart, iEnd, aiDisplay ) {
			          
			          if (typeof doLoaderDataFooter !== 'undefined' && typeof doLoaderDataFooter === 'function'){    
			              var items= doLoaderDataFooter( nRow, aData, iStart, iEnd );
			                for (var key in items) {
                               $('td:eq('+key+')', nRow).html( '<b>'+items[key]+'</b>' );
                          }
                      }  
                        
			    },
				"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				      
				      if ( aData[3] == "A$$$$$$" )
				      {
				        $('td:eq(3)', nRow).html( '<b>A</b>' );
				      }
				 },
                "sPaginationType": "full_numbers",
                       
                "iDisplayLength":10, 
                "bProcessing": true, 
                "bServerSide": true,
                "aoColumns":mapDone['mapCol'],
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"],
                "fnServerData": function ( sSource, aoData, fnCallback ) {
                 var formFilter= aoData.concat( $("#myformToServeur").serializeArray() );
	                   $.ajax( {
	                   "dataType": 'json',
	                   "type": "POST",
	                   "url": sSource,
	                   "data": formFilter,  
	                   "success" : function(json){
	                          var count = Object.keys(json.aaData).length; 
	                         $("#PanelSwing").unmask(); 
	                           Ext.getCmp('btValidx').disable();   
	                          if(count==0){
	                           // $("#RET_GRID").hide();
	                            Ext.get('RET_GRID').setStyle('display', 'none');
	                            Ext.getCmp('btPrintPdfx').disable();
	                            Ext.getCmp('btPrintPdfx').disable();
	                            Ext.getCmp('btExportXlsx').disable();
	                            mayBox_al("Aucune Résulat","")   ;
	                          }else{ 
	                            fnCallback(json);
	                            
	                            if (oTable != undefined){
	                              otREserv=oTable;
	                           
	                               var oSettin = oTable.fnSettings(); 
 								   // alert( oSettin.sAjaxSource );
	                               oSettin.sAjaxSource=mapGen["url"]+"?HiddenAction="+mapGen["action"]+"&nameList="+mapGen["list"];
	                                
                                   oTable.fnAdjustColumnSizing(); 
	                            }
	                            oTable=undefined;
	                         
	                            Ext.get('RET_GRID').setStyle('display', 'block');
	                            
	                            //alert(Ext.getCmp('btPrintPdfx'));
	                             Ext.getCmp('btPrintPdfx').enable();
	                             Ext.getCmp('btExportXlsx').enable();
	                             
	                           // $('#btPrintPdfx').prop('disabled',false);
	                            //$('#btExportXlsx').prop('disabled',false);
	                            //Ext.get('RET_GRID').setStyle('display', 'block');
	                          
	                            //$("#RET_GRID").show();
	                          }  
	                          } ,
	                          
	                   "error": function (e) {
	                   
	                    mayBox_al(" Erreur  Serveur ! Contacter Administration ","ss");
	                         
	                    }
	                   
	                   
	                   });
	               },
                "sScrollY": ($(window).height() - height_dTble),
                "sScrollX": "100%",
                "sScrollXInner": dataGridConfig_width,
			    "sScrollYInner": "100%",
			    "bScrollCollapse": true,
                "jQueryUI": true,
                "bDestroy": true, 
                "rowsGroup": [2],
                "autoWidth": false,
                "bAutoWidth": false,
                "bJQueryUI": true,
                "oLanguage": { "sUrl": "<%=request.getContextPath() %>/jQuery/dataTable/langue/French.json"}
                });//.rowGrouping({sGroupBy:"pk.fkcode_barre.designation_libelle", bHideGroupingColumn: false}); 
                	 
        
               
}
 
 	
 	
 	
 	
 	function  LoadDataFromServerXX(mapDone){   
 
if(dataGridConfig_width== undefined) dataGridConfig_width="100%"; 

  oTable = $('#'+mapDone['table']).dataTable( {
    
                "fnFooterCallback": function( nRow, aData, iStart, iEnd, aiDisplay ) {
			          
			          if (typeof doLoaderDataFooter !== 'undefined' && typeof doLoaderDataFooter === 'function'){    
			              var items= doLoaderDataFooter( nRow, aData, iStart, iEnd );
			                for (var key in items) {
                               $('td:eq('+key+')', nRow).html( '<b>'+items[key]+'</b>' );
                          }
                      }  
                        
			    },
				"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				      
				      if ( aData[3] == "A$$$$$$" )
				      {
				        $('td:eq(3)', nRow).html( '<b>A</b>' );
				      }
				 },
                "sPaginationType": "full_numbers",
              
                "iDisplayLength":10, 
                "bProcessing": true, 
                "bServerSide": true,
                "aoColumns":mapDone['mapCol'],
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"],
                "fnServerData": function ( sSource, aoData, fnCallback ) {
                 var formFilter= aoData.concat( $("#myformToServeur").serializeArray() );
	                   $.ajax( {
	                   "dataType": 'json',
	                   "type": "POST",
	                   "url": sSource,
	                   "data": formFilter,  
	                   "success" : function(json){
	                          var count = Object.keys(json.aaData).length; 
	                         $("#PanelSwing").unmask(); 
	                           Ext.getCmp('btValidx').disable();   
	                          if(count==0){
	                           // $("#RET_GRID").hide();
	                            Ext.get('RET_GRID').setStyle('display', 'none');
	                             
	                            Ext.getCmp('btPrintPdfx').disable();
	                            Ext.getCmp('btExportXlsx').disable();
	                            //mayBox_al("Aucune Résulat","")   ;
	                          }else{ 
	                            fnCallback(json);
	                            
	                            if (oTable != undefined){
	                                otREserv=oTable;
	                                 
	                                 
	                                
	                               //oTable.fnPageChange( 5 );
	                                //alert(json.iaa);
	                               //var totalRows = oTable.fnGetData().length;
	                                
	                               
	                                
	                               var numberS = 0;
	                               var bideya=parseInt(json.iaa) ;
	                               var toulha=parseInt(json.ibb) ;
	                              
	                                 numberS = Math.ceil(bideya / toulha);
	                                 
                                   //oTable.fnAdjustColumnSizing(); 
                                    var oSettin = oTable.fnSettings(); 
 								   // alert( oSettin.sAjaxSource );
	                               oSettin.sAjaxSource=mapGen["url"]+"?HiddenAction="+mapGen["action"]+"&nameList="+mapGen["list"];
	                               oSettin._iDisplayLength =toulha;
                                   oTable.fnPageChange( numberS );
                                   
	                            }
	                            oTable=undefined;
	                         
	                            Ext.get('RET_GRID').setStyle('display', 'block');
	                            
	                            //alert(Ext.getCmp('btPrintPdfx'));
	                             Ext.getCmp('btPrintPdfx').enable();
	                             Ext.getCmp('btExportXlsx').enable();
	                             
	                           // $('#btPrintPdfx').prop('disabled',false);
	                            //$('#btExportXlsx').prop('disabled',false);
	                            //Ext.get('RET_GRID').setStyle('display', 'block');
	                          
	                            //$("#RET_GRID").show();
	                          }  
	                          } ,
	                          
	                   "error": function (e) {
	                        mayBox_al(" Erreur  Serveur ! Contacter Administration ","ss");
	                    }
	                   
	                   
	                   });
	               },
                "sScrollY": ($(window).height() - height_dTble),
                "sScrollX": "100%",
                "sScrollXInner": dataGridConfig_width,
			    "sScrollYInner": "100%",
			    "bScrollCollapse": true,
                "jQueryUI": true,
                "bDestroy": true, 
                "autoWidth": false,
                "bAutoWidth": false,
                "bJQueryUI": true,
                "oLanguage": { "sUrl": "<%=request.getContextPath() %>/jQuery/dataTable/langue/French.json"}
                });//.rowGrouping({sGroupBy:"lang_libelle", bHideGroupingColumn: false}); 
                	 
        
               
}
 	
 	 
 



$(document).ready(function () {

     
    $('#'+maptableAjax["table"]+' tbody tr').live('dblclick', function () {
      
     
		      var aData = otREserv.fnGetData( this );
		      var iId = aData[0];
		       
		       if ( jQuery.inArray(iId, gaiSelected) == -1 ){
			        gaiSelected[gaiSelected.length++] = iId;
		         }else{
			        gaiSelected = jQuery.grep(gaiSelected, function(value) {
				    return value != iId;} );
		         }
		        
		   var indexRowSel = otREserv.fnGetPosition( this );
		   
		   $(this).toggleClass('row_selected');
		    if(!doubleclickGrid) return;
		   getDetailRowFromServer(indexRowSel);
} );
    
});
</script>

<table id="${nameGrid}"   class="display"  style="width: 100%;"  ><thead></thead><tbody></tbody>  </table> 

<script>
Ext.onReady(function(){
    try {	
       $('#RET_GRID').css('display','none');
       $('#${nameGrid}').css('display','none');
     <c:if test="${not empty dataListAajx}">
       
        Ext.getCmp('btPrintPdfx').enable();
	    Ext.getCmp('btExportXlsx').enable();
	    
	    $('#RET_GRID').css('display','block');
	    $('#${nameGrid}').css('display','block');
	    
        LoadDataFromServerXX(mapGenEE);

    </c:if>
	    } catch(e){
	    
		}	
});
</script>

