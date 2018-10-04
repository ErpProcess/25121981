
<script type="text/javascript">

    
var oTablePr;
var mapEditableGenPrs = {      "otab"   :oTablePr,
                               "table"  :"GRID_SAISIE_PRESATATION",
                               "list"   :"list_editable_prestation",
                               "id_name":"fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV4(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[2]+'"   >';}}, 
									                  
										   {      "sName": "fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "fkcode_barre.designation_libelle"   ,"sWidth": "40%"    }, 
										   
										   {      "sName": "quantite"           ,   "bSortable": true       , "sWidth": "25%"        ,"mRender": function( data, type, full){  
										          return '<input   type="number"      style="width:70px;"     id=quantite'+full[0]+'       name=quantite        value="'+data+'"       onblur=doEnvoiDataV4(this,"'+full[2]+'")     nextElement="quantite'+full[8]+'"     >'; }},   
										    
										           
										   {      "sTitle":"Prix U"    , "sName": "tarifVente.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": true 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    }, 
	                                      
	                                       {      "sTitle":"Total TTC" , "sName": "montant_ttc_vente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    }, 
	                                                             
										   {      "sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };                    
function doEnvoiDataV4(element,value_id_de_la_ligne){
	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"":"checked";
	var attrerio = $(element).attr('isboolean');
	if (typeof attrerio !== typeof undefined && attrerio !== false) {
	   element.value=element.checked==false?false:true;
	 }
	var idToSendo=value_id_de_la_ligne ;
	var LEvalue=element.value;
	var name_column=element.name;
    var hashmap ={"sNameId":mapEditableGenPrs["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGenPrs["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL_def, 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){ 
	     
	         if ( $(element).attr('type')!=undefined  &&   $(element).attr('type')!="checkbox" ) {
	         
	         var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE_PRESTATION','json',false);
		       $('#'+mapEditableGenPrs["table"]+' tbody tr').each(function () {
		          var qsdqsqd   = $(this).find('td:eq(1)').html() ;
		          var QteNew    = "Qte"+qsdqsqd;
		          var erreurX   = "erreur"+qsdqsqd;
			      $(this).find('td:eq(9)').html(json[qsdqsqd]) ;
			      $(this).find('td:eq(4)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
			      var xcvvv=json[erreurX];
			      xcvvv=xcvvv.trim();
			      if(   xcvvv  !="" &&  xcvvv.length>0 ){
			           alertExt("",json[erreurX],"4");
			      }
			      
			   });
	         }
	       }
      });      
}


var otab_otraPrestation;
function  LoadDataEditableFromServerPrestation( varoTable23,  mapDone  , afficher_mess_empty  ,  
nombre_deligne   ,  heightTable  ,  widthTable  ,  ssdomm  , nameDivToolBar , elmeTollbar  ){  
     
   if ( mapDone["otab"] != undefined ) {
	} else{
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
   
                 "fnFooterCallback": function( nRow, aData, iStart, iEnd, aiDisplay ) {
			   
			   if (typeof doLoaderDataFooter3 !== 'undefined' && typeof doLoaderDataFooter3 === 'function'){    
			                var items= doLoaderDataFooter2( nRow, aData, iStart, iEnd );
			                   var EE=nRow;
			                   for (var keyZ in items) {
				                   var ITEM=items[keyZ];
				                   for (var key in ITEM) {
				                      if( ITEM[key] =="deletio"){
				                       $(EE).hide();
				                      }else{
				                       $('td:eq('+key+')', EE).html( '<b>'+ITEM[key]+'</b>' );
				                      }
	                               }
	                               
	                               EE=$(EE).next();  
                               }
                      }  
                        
			    },
				"oLanguage": { "sUrl": "<%=request.getContextPath() %>/jQuery/dataTable/langue/French.json"},
				//"fnInitComplete": function () {    $("div.toolbar_es").html(elmeTollbar);   },
				"fnInitComplete": function () {    $("div."+nameDivToolBar).html(elmeTollbar);   },
                "iDisplayLength": nombre_deligne,   
                "bServerSide": true,
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"],
                "bProcessing": true,
                "bAutoWidth": false,
                //"aaSorting": [[ 4, "desc" ]],
                "oScroller": { "loadingIndicator": true},
                "sScrollY": heightTable,
                "sScrollX": widthTable,
                "sScrollXInner": widthTable,
                "scrollCollapse": true,
                "paging":         true,
                "jQueryUI": false,
                "sPaginationType": "full_numbers",
                "bDestroy": true,
                //"sDom": 'Rplfrti',
                //"sDom": 'Rlfrtip',
                //"dom": '<lf<t>ip>',
                "sDom": ssdomm,
                "oColReorder": {"headerContextMenu": true},
                
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
	                            if(!afficher_mess_empty){
	                               fnCallback(json);
	                               if (mapDone["otab"] != undefined){
	                                   mapDone["otab"].fnAdjustColumnSizing(); 
	                                   otab_otraPrestation=mapDone["otab"];
	                                }
	                               mapDone["otab"]=undefined;
	                            }else{
		                               if(count==0){
		                                fnCallback(json);
			                          }else{ 
			                            fnCallback(json);
			                            if (mapDone["otab"] != undefined){
	                                     mapDone["otab"].fnAdjustColumnSizing(); 
	                                     }
	                                     mapDone["otab"]=undefined;
			                          }
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
