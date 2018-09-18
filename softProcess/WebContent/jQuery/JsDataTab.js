

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

      
            $("#companies").dataTable({
                "bServerSide": true,
                "sAjaxSource": urlsAjaxSource,
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true,
                "aoColumns": sNameBean
         }).makeEditable({
                sDeleteURL :urlsDeleteURL,
         		sAddURL:urlsAddURL ,
         		sUpdateURL: urlsUpdateURL,
        	    "aoColumns":sNameBeanEditable 
        	              });
     
}

/*********************************************************************************************************************/




 
/*********************************************************************************************************************/


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

