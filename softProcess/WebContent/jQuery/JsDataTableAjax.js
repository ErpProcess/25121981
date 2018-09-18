

/*********************************************************************************************************************/
/*******************                        version1.0  Config dataTable                    **************************/
/*********************************************************************************************************************/



$.fn.dataTableExt.oApi.fnReloadAjax = function ( oSettings, sNewSource, fnCallback, bStandingRedraw )
{
        if ( sNewSource !== undefined && sNewSource !== null ) {
                oSettings.sAjaxSource = sNewSource;
        }

        // Server-side processing should just call fnDraw
        if ( oSettings.oFeatures.bServerSide ) {
                this.fnDraw();
                return;
        }

        this.oApi._fnProcessingDisplay( oSettings, true );
        var that = this;
        var iStart = oSettings._iDisplayStart;
        var aData = [];

        this.oApi._fnServerParams( oSettings, aData );

        oSettings.fnServerData.call( oSettings.oInstance, oSettings.sAjaxSource, aData, function(json) {
                /* Clear the old information from the table */
                that.oApi._fnClearTable( oSettings );

                /* Got the data - add it to the table */
                var aData =  (oSettings.sAjaxDataProp !== "") ?
                        that.oApi._fnGetObjectDataFn( oSettings.sAjaxDataProp )( json ) : json;

                for ( var i=0 ; i<aData.length ; i++ )
                {
                        that.oApi._fnAddData( oSettings, aData[i] );
                }
                
                oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();

                that.fnDraw();

                if ( bStandingRedraw === true )
                {
                        oSettings._iDisplayStart = iStart;
                        that.oApi._fnCalculateEnd( oSettings );
                        that.fnDraw( false );
                }

                that.oApi._fnProcessingDisplay( oSettings, false );

                /* Callback user function - for event handlers etc */
                if ( typeof fnCallback == 'function' && fnCallback !== null )
                {
                        fnCallback( oSettings );
                }
        }, oSettings );
};
/*********************************************************************************************************************/
  
    



  


/*********************************************************************************************************************/
$(document).ready(function () {  LoadDataFromServer();   });

var gaiSelected =[];
var oTable;

 
function  LoadDataFromServer(){


    oTable = $('#displayDataGrid').dataTable( {
                "iDisplayLength": 7,   
                "bServerSide": true,
                "sAjaxSource": urlLoadData,
                "bProcessing": true,
                "aaSorting": [[ 5, "desc" ]],
                "sPaginationType": "full_numbers",
                "aoColumns": mapColumsbean, 
                "bJQueryUI": true,
                "fnServerData": function ( sSource, aoData, fnCallback ) {
                 var formFilter= aoData.concat( $("#myformToServeur").serializeArray() );
	                   $.ajax( {
	                   "dataType": 'json',
	                   "type": "POST",
	                   "url": sSource,
	                   "data": formFilter,  
	                   "success": fnCallback});},
	            "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			          if(jQuery.inArray(aData[0], gaiSelected) != -1 ){
				            $(nRow).addClass('row_selected');
			              }
			               return nRow;
		               }
		                    
                     
                });
}

/*********************************************************************************************************************/
$('#displayDataGrid tbody tr').live('dblclick', function () {
        
		      var aData = oTable.fnGetData( this );
		      var iId = aData[0];
		       
		       if ( jQuery.inArray(iId, gaiSelected) == -1 ){
			        gaiSelected[gaiSelected.length++] = iId;
		         }else{
			        gaiSelected = jQuery.grep(gaiSelected, function(value) {
				    return value != iId;} );
		         }
		        
		   var indexRowSel = oTable.fnGetPosition( this );
		   
		   $(this).toggleClass('row_selected');
		   getDetailRowFromServer(indexRowSel);

} );



 
/*********************************************************************************************************************/
function getDetailRowFromServer(indexRowselected) {
        $("#myformToServeur").attr("action",urlselectRow+"?HiddenAction=doSelectRow.action&theIdRow="+indexRowselected);
	    $("#myformToServeur").submit();
} 

/*********************************************************************************************************************/	
/*    
function  doResetAjaxData(){

	if (oTable != undefined) {
	oTable.iDisplayStart = -2;
	oTable.fnClearTable(); 
	oTable.fnDestroy(); 
	oTable=undefined;
	} 
	document.getElementById('divDataTable').style.display="none";
}
*/
/* 
function ControlDisplayTable(){
		if("${display}"=="true"){
		    LoadDataFromServer();
		    
		 }else{
			if(oTable==undefined){
			document.getElementById('divDataTable').style.display="none";
			}else{
			document.getElementById('divDataTable').style.display="";
			}
		}
}
*/

