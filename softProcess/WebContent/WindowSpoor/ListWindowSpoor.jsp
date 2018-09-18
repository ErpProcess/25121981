 

<script >

var mapColumsbean=[
                   { "sTitle": "op_id"        ,"sName": "op_id"                 ,"sWidth": "15%"     , "bSortable": "true"   },
                   { "sTitle": "Id"           ,"sName": "spoorPKBean.sp_id"     ,"sWidth": "2%"      , "bSortable": "true" ,"bVisible": false   },
                   { "sTitle": "date"         ,"sName": "spoorPKBean.sp_date"   ,"sWidth": "20%"     , "bSortable": "true"   },
                   { "sTitle": "heur"         ,"sName": "spoorPKBean.sp_time"   ,"sWidth": "10%"     , "bSortable": "true"   },
				   { "sTitle": "Utilisateur"  ,"sName": "spoorPKBean.usr_login" ,"sWidth": "10%"     , "bSortable": "true"  ,"bVisible": false  },
				   { "sTitle": "Fonction"     ,"sName": "bFonction.fct_libelle" ,"sWidth": "15%"     , "bSortable": "true"   },
				   { "sTitle": "colonne"      ,"sName": "colonne"               ,"sWidth": "15%"     , "bSortable": "true"  ,"bVisible": false  },
				   { "sTitle": "Détaille"      ,"sName": "detail_op"             ,"sWidth": "30%"     , "bSortable": "true"    },
				   
				    ];		
</script> 
<script type="text/javascript">

var url__Spoored_Gn=baseAjaxUrl+"/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Spoor/root.action";
var maptableTrace_Spoored_Gn = {"table":"SpoorGrid","list":"SpoorList","url":url__Spoored_Gn,"action":"i$_ACT_AJAX_FETCH_PAR_MODULE","mapCol":mapColumsbean};

function  cherche(){
document.getElementById("ListWindowSpoorED").style.display="block";
var oTableTrace = $('#'+maptableTrace_Spoored_Gn['table']).dataTable( {
                "iDisplayLength": 7,   
                "bServerSide": true,
                "sAjaxSource": maptableTrace_Spoored_Gn["url"]+"?HiddenAction="+maptableTrace_Spoored_Gn["action"]+"&nameList="+maptableTrace_Spoored_Gn["list"],
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
                "aoColumns":maptableTrace_Spoored_Gn['mapCol'], 
                "bJQueryUI": true,
                "fnServerData": function ( sSource, aoData, fnCallback ) {
                 var formFilter= aoData.concat( $("#myformTrace").serializeArray() );
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
		               },
		               
		                    
                     
                });//.rowGrouping({sGroupBy:"lang_libelle", bHideGroupingColumn: false});
                
                
}
 
</script>
<table id="SpoorGrid"   class="display"  style="width: 100%;"    >	
<thead   >
		 
	</thead>
</table>
  
 




 
	
	
	 
	
	 
	
	
	 
	
 










