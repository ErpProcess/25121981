<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<script type="text/javascript">
var otREserv;

var maptableAjax = {"table":"${nameGrid}","list":"${nameList}","url":"${tmlx.urlAjax}","action":"${action_fetch_ajax_global}","mapCol":mapColumsbean};
var mapGen=        {"table":"${nameGrid}","list":"${nameList}","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_DATA_TABLE_AJAX","mapCol":mapColumsbean};
 

function  LoadDataFromServer(mapDone){

alert(mapDone["list"])
     $("#PanelSwing").mask("Waiting..."); 
if (oTable != undefined) {
	 oTable.fnReloadAjax();
	} else{
    oTable = $('#'+mapDone['table']).dataTable( {
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
                                  oTable.fnAdjustColumnSizing(); 
	                            }
	                            oTable=undefined;
	                            Ext.get('RET_GRID').setStyle('display', 'block');
	                            Ext.getCmp('btPrintPdfx').enable();
	                            Ext.getCmp('btExportXlsx').enable();
	                            //$("#RET_GRID").show();
	                          }  
	                          } ,
	                   "error": function (e) {
	                          alert("Erreur  Serveur! Contacter Administration ");
	                    }
	                   
	                   
	                   });
	               },
                "sScrollY": ($(window).height() - height_dTble),
                "sScrollX": "100%",
                "sScrollXInner": "100%",
			    "sScrollYInner": "110%",
			    "bScrollCollapse": true,
                "jQueryUI": true,
                "bDestroy": true,
                "autoWidth": false,
                "bAutoWidth": false,
                "bJQueryUI": true
                });//.rowGrouping({sGroupBy:"lang_libelle", bHideGroupingColumn: false});
                
   
                
    }          
}
 
 
</script>
