
<script type="text/javascript">

 
var otab_otra;
var otab_otra2;
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
	     success: function(data){ 
	          if (typeof doExcuteFnAfterGrid !== 'undefined' && typeof doExcuteFnAfterGrid === 'function' 
	           &&  ( $(element).attr('type')!= undefined  &&   $(element).attr('type')!="checkbox"   )  )    doExcuteFnAfterGrid( data );
	      }
      });      
}
function doEnvoiDataV2(element,value_id_de_la_ligne){

	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"":"checked";
	var attrerio = $(element).attr('isboolean');
	if (typeof attrerio !== typeof undefined && attrerio !== false) {
	   element.value=element.checked==false?false:true;
	 }
	var idToSendo=value_id_de_la_ligne ;
	var LEvalue=element.value;
	var name_column=element.name;
    var hashmap ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGen["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL_def, 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){ 
	   if (typeof doExcuteFnAfterGrid !== 'undefined' && typeof doExcuteFnAfterGrid === 'function' 
	           &&  ( $(element).attr('type')!=undefined  &&   $(element).attr('type')!="checkbox"   ) )    doExcuteFnAfterGrid( data );
	       
	       }
      });      
}
function doEnvoiDataV2_Corr(element,elment2,value_id_de_la_ligne){
 	    
   var idToSendo=value_id_de_la_ligne ;
   var LEvalue=$('input[id="'+element+'"]').val();
   var name_column=$('input[id="'+element+'"]').attr("name");
	 
    var hashmap ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGen["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url:  urlsUpdateURL_def, 
	     data: hashmap,
	     dataType: 'text', 
	     success: function(data){
	     	
	     	  var LEvalue2     =$('input[id="'+elment2+'"]').val();
			  var name_column2 =$('input[id="'+elment2+'"]').attr("name");
 	     
		      var hashmap2 ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue2,"sNameColumn" :name_column2,"sNameList":mapEditableGen["list"]};
			jQuery.ajax({ 
			     type: 'POST',  
			     url: urlsUpdateURL_def, 
			     data:hashmap2,
			     dataType: 'text', 
			     success: function(data){
			     
			     
			     jQuery.ajax({ 
			       type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_ACTUALISER_LIST_CORR',
	               dataType: 'text', 
	               success: function(data){ }});
	               
			       }
		      }); 
			   
           }
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
				      
				      if ( aData[3] != "A$$$$$$" )
				      {
				      alert("ss");
				        //$('td:eq(3)', nRow).html( '<b>A</b>' );
				      }
				 },
				 
                "iDisplayLength": 7,   
                "bServerSide": true,
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"],
                "bProcessing": true,
                "bAutoWidth": false,
                "aaSorting": [[ 4, "desc" ]],
                "scrollY": 150,
                "scrollCollapse": true,
                "paging": false,
                "jQueryUI": true,
                "sPaginationType": "full_numbers",
                "bDestroy": true,
                //"sDom": 'Rplfrti',
                //"sDom": 'Rlfrtip',
                //"dom": '<lf<t>ip>',
                //"sDom": 'frl<"toolbar">tip',
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
	                             if (mapDone["otab"] != undefined){
	                                mapDone["otab"].fnAdjustColumnSizing(); 
	                                otab_otra=mapDone["otab"];
	                             }
	                                mapDone["otab"]=undefined;
	                             if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
	                             if( typeof(FnLoadSelectAjax) == 'function' ){FnLoadSelectAjax(json);}
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
                $("div.toolbar").html('<b>wajdi</b>');
                
                
    }          
                
}

function  LoadDataEditableFromServer_toolbar(   mapDone  , afficher_mess_empty  ,  nombre_deligne   ,  heightTable  ,  widthTable  ,  ssdomm  ,  elmeTollbar  ){  

  
 
    //$("#PanelSwing").mask("Waiting...");
     
     
   if ( mapDone["otab"] != undefined ) {
 	   // mapDone["otab"].fnReloadAjax();
	   
	} else{
	 
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
    
  
   
                  "fnFooterCallback": function( nRow, aData, iStart, iEnd, aiDisplay ) {
			   
			   if (typeof doLoaderDataFooter !== 'undefined' && typeof doLoaderDataFooter === 'function'){    
			                var items= doLoaderDataFooter( nRow, aData, iStart, iEnd );
			                 
			              
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
				"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				      
				       if ( aData[3] != "A$$$$$$" )
				      {
				      alert("ss");
				        //$('td:eq(3)', nRow).html( '<b>A</b>' );
				      } 
				 },
				"oLanguage": { "sUrl": "<%=request.getContextPath() %>/jQuery/dataTable/langue/French.json"},
				"fnInitComplete": function () {    $("div.toolbar_es").html(elmeTollbar);   },
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
	                           //$("#PanelSwing").unmask();
	                           
	                            
	                            
	                            
	                            
	                            if(!afficher_mess_empty){
	                               fnCallback(json);
	                               if (mapDone["otab"] != undefined){
	                                  mapDone["otab"].fnAdjustColumnSizing(); 
	                                  otab_otra=mapDone["otab"];
	                                }
	                               mapDone["otab"]=undefined;
	                               if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
	                               if( typeof(FnLoadSelectAjax) == 'function' ){FnLoadSelectAjax(json);}
	                               
	                               
	                            }else{
		                               if(count==0){
		                                fnCallback(json);
			                            //Ext.get('RET_GRID').setStyle('display', 'none');
			                            //Ext.getCmp('btPrintPdfx').disable();
			                            //Ext.getCmp('btExportXlsx').disable();
			                            //mayBox_al("Aucune Résulat","")   ;
			                          }else{ 
			                            fnCallback(json);
			                            if (mapDone["otab"] != undefined){
	                                     mapDone["otab"].fnAdjustColumnSizing(); 
	                                     }
	                                     mapDone["otab"]=undefined;
			                            
	                                   
			                            if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
			                            if( typeof(FnLoadSelectAjax) == 'function' )   {FnLoadSelectAjax(json);}
			                            //Ext.get('RET_GRID').setStyle('display', 'block');
			                            //Ext.getCmp('btPrintPdfx').enable();
			                            //Ext.getCmp('btExportXlsx').enable();
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


function  LoadDataEditableFromServer_toolbarV22( varoTable23,  mapDone  , afficher_mess_empty  ,  
nombre_deligne   ,  heightTable  ,  widthTable  ,  ssdomm  , nameDivToolBar , elmeTollbar  ){  
     
   if ( mapDone["otab"] != undefined ) {
	} else{
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
   
                 "fnFooterCallback": function( nRow, aData, iStart, iEnd, aiDisplay ) {
			   
			   if (typeof doLoaderDataFooter2 !== 'undefined' && typeof doLoaderDataFooter2 === 'function'){    
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
	                                   otab_otra2=mapDone["otab"];
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

function  LoadDataEditableGridWindow(   mapDone  , afficher_mess_empty  ,  nombre_deligne   ,  heightTable  ,  widthTable  ,  ssdomm  ,  elmeTollbar  ){  


     
   if ( mapDone["otab"] != undefined ) {
	    mapDone["otab"].fnReloadAjax();
	   
	} else{
	 
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
     
				"oLanguage": { "sUrl": "<%=request.getContextPath() %>/jQuery/dataTable/langue/French.json"},
				"fnInitComplete": function () {    $("div.toolbar_es").html(elmeTollbar);   },
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
	                            fnCallback(json);
	                            if (mapDone["otab"] != undefined){
	                             mapDone["otab"].fnAdjustColumnSizing(); 
	                              otab_otra=mapDone["otab"];
	                             }
	                            mapDone["otab"]=undefined;
	                          } ,
	                          
	                   "error": function (e) {
	                          alertExt("","Erreur  Serveur! Contacter Administration ","3");
	                    }});},
	                    
	              "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			          if(jQuery.inArray(aData[0], gaiSelected) != -1 ){
				            $(nRow).addClass('row_selected');
			              }
			               return nRow;
		            },
                });
                   
    }       
                
}


function  LoadDataEditable_AUTO_height(   mapDone  , afficher_mess_empty  ,  nombre_deligne   ,  heightTable  ,  widthTable  ,  ssdomm  ,  elmeTollbar  ){  

    //$("#PanelSwing").mask("Waiting..."); 
   if ( mapDone["otab"] != undefined ) {
	    mapDone["otab"].fnReloadAjax();
	} else{
	 
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
    "fnFooterCallback": function( nRow, aData, iStart, iEnd, aiDisplay ) {
			          
			   if (typeof doLoaderDataFooter !== 'undefined' && typeof doLoaderDataFooter === 'function'){    
			                var items= doLoaderDataFooter( nRow, aData, iStart, iEnd );
			                
			                   var EE=nRow;
			                   for (var keyZ in items) {
				                   var ITEM=items[keyZ];
				                   for (var key in ITEM) {
	                               $('td:eq('+key+')', EE).html( '<b>'+ITEM[key]+'</b>' );
	                               }
	                               EE=$(EE).next();  
                               }
                      }  
                        
			    },
				"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				      
				      if ( aData[3] == "A$$$$$$" )
				      {
				        $('td:eq(3)', nRow).html( '<b>A</b>' );
				      }
				 },
                "iDisplayLength": nombre_deligne,   
                "bServerSide": true,
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"],
                "bProcessing": true,
                "bAutoWidth": false,
                //"aaSorting": [[ 4, "desc" ]],
                "oScroller": { "loadingIndicator": true},

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
	                            
	                            
	                            
	                            
	                            if(!afficher_mess_empty){
	                            
	                                fnCallback(json);
		                            if (mapDone["otab"] != undefined){
		                                mapDone["otab"].fnAdjustColumnSizing(); 
		                                }
	                                mapDone["otab"]=undefined;
	                                if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
	                                if( typeof(FnLoadSelectAjax) == 'function' ){FnLoadSelectAjax(json);}
	                                Ext.get('RET_GRID').setStyle('display', 'block');
	                               
	                            }else{
		                               if(count==0){
			                            Ext.get('RET_GRID').setStyle('display', 'none');
			                            Ext.getCmp('btPrintPdfx').disable();
			                            Ext.getCmp('btExportXlsx').disable();
			                            mayBox_al("Aucune Résulat","")   ;
			                          }else{ 
			                            fnCallback(json);
			                            if (mapDone["otab"] != undefined){
	                                     mapDone["otab"].fnAdjustColumnSizing();
	                                     otab_otra=mapDone["otab"]; 
	                                     }
	                                     mapDone["otab"]=undefined;
			                            
			                            if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
			                             if( typeof(FnLoadSelectAjax) == 'function' ){FnLoadSelectAjax(json);}
			                            Ext.get('RET_GRID').setStyle('display', 'block');
			                            Ext.getCmp('btPrintPdfx').enable();
			                            Ext.getCmp('btExportXlsx').enable();
			                          }
	                            }
	                             
                                
      
	                          
	                          
	                            
	                          } ,
	                          
	                   "error": function (e) {
	                          alertExt("","Erreur  Serveur! Contacter Administration ","4");
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
                $("div.toolbar_es").html(elmeTollbar);      
    }       
                
}


function  LoadDataEditableProcess( tyPePaRam , mapDone  , afficher_mess_empty  ,  nombre_deligne   ,  heightTable  ,  widthTable  ,  ssdomm  ,  elmeTollbar  ){  

    //$("#PanelSwing").mask("Waiting...");
   
   if ( mapDone["otab"] != undefined ) {
	    mapDone["otab"].fnReloadAjax();
	} else{
	
	 
	   
	
    mapDone["otab"] = $('#'+mapDone['table']).dataTable( {
                "iDisplayLength": nombre_deligne,   
                "bServerSide": true,
                "sAjaxSource": mapDone["url"]+"?HiddenAction="+mapDone["action"]+"&nameList="+mapDone["list"]+"&type_param="+tyPePaRam,
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
	                           //$("#PanelSwing").unmask();
	                           
	                            
	                            
	                            
	                            if(!afficher_mess_empty){
	                                fnCallback(json);
	                                if (mapDone["otab"] != undefined){
	                                     mapDone["otab"].fnAdjustColumnSizing(); 
	                                     }
	                                     mapDone["otab"]=undefined;
	                               if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
	                               if( typeof(FnLoadSelectAjax) == 'function' ){FnLoadSelectAjax(json);}
	                               Ext.get('RET_GRID').setStyle('display', 'block'); 
	                            }else{
		                               if(count==0){
			                            Ext.get('RET_GRID').setStyle('display', 'none');
			                            Ext.getCmp('btPrintPdfx').disable();
			                            Ext.getCmp('btExportXlsx').disable();
			                            mayBox_al("Aucune Résulat","")   ;
			                          }else{ 
			                            fnCallback(json);
			                            if (mapDone["otab"] != undefined){
	                                     mapDone["otab"].fnAdjustColumnSizing(); 
	                                     otab_otra=mapDone["otab"];
	                                     }
	                                     mapDone["otab"]=undefined;
			                            if( typeof(loadLesListeCorrlee) == 'function' ){loadLesListeCorrlee(json);}
			                            if( typeof(FnLoadSelectAjax) == 'function' ){FnLoadSelectAjax(json);}
			                            Ext.get('RET_GRID').setStyle('display', 'block');
			                            Ext.getCmp('btPrintPdfx').enable();
			                            Ext.getCmp('btExportXlsx').enable();
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
                $("div.toolbar_es").html(elmeTollbar);      
    }       
                
}
</script>
