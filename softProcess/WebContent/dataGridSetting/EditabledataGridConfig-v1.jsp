<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<script type="text/javascript">

function doEnvoiData(element){

	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"":"checked";
	var idToSendo=$(element).attr('idRow') ;
	var LEvalue=element.value;
	var name_column=element.name;
    var hashmap ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGen["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL_def, 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){  if (typeof doExcuteFnAfterGrid !== 'undefined' && typeof doExcuteFnAfterGrid === 'function')    doExcuteFnAfterGrid( data );  }
      });      
}

function  LoadDataEditableFromServer(mapDone){  

$("#PanelSwing").mask("Waiting..."); 
if ( mapDone["otab"] != undefined ) {
	 mapDone["otab"].fnReloadAjax();
	} else{
	
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
    			
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
				 
                "iDisplayLength": 7,   
                "bServerSide": true,
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"],
                "bProcessing": true,
                "bAutoWidth": false,
                "aaSorting": [[ 4, "desc" ]],
                "scrollY": "200px",
                "scrollCollapse": true,
                "paging": false,
                "jQueryUI": true,
                "sPaginationType": "full_numbers",
                "bDestroy": true,
                //"sDom": 'Rplfrti',
                //"sDom": 'Rlfrtip',
                "oColReorder": {"headerContextMenu": false},
                //"oLanguage": { "sUrl": "js/fr_FR.txt" },
                "aoColumns":mapDone['mapCol'], 
                "bJQueryUI": true,
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
	                          if(count==0){
	                            Ext.get('RET_GRID').setStyle('display', 'none');
	                            Ext.getCmp('btPrintPdfx').disable();
	                            Ext.getCmp('btExportXlsx').disable();
	                            mayBox_al("Aucune Résulat","")   ;
	                          }else{ 
	                            fnCallback(json);
	                            Ext.get('RET_GRID').setStyle('display', 'block');
	                            Ext.getCmp('btPrintPdfx').enable();
	                            Ext.getCmp('btExportXlsx').enable();
	                          }  
	                          } ,
	                          
	                   "error": function (e) {
	                          alert("Erreur  Serveur! Contacter Administration ");
	                    }
	                   
	                   
	                   });
	                   },
	            "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			          if(jQuery.inArray(aData[0], gaiSelected) != -1 ){
				            $(nRow).addClass('row_selected');
			              }
			               return nRow;
		               },
                });
                
    }          
                
}
</script>