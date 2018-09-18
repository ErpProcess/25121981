function  LoadAutoCompletGrid(Xcode,Xlibelle,XnextFocus,namelistAjax,codeFocu,id_RowGridvf){

$("#"+codeFocu).autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        async: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
               success: function(data) { 
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                     $("#"+codeFocu).blur();
                      console.log( textStatus);
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $("#"+codeFocu).val(ui.item.value);
                //$("#"+codeFocu).autocomplete( "disable" );
                //if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                //alert(XnextFocus);
                //alert(id_RowGridvf.aaData[0][0]);
                jQuery.ajax({  
                           type: 'POST',  
                           url: urlsUpdateURL, 
                           data:'hid_act=updateListCorr&idTosendsd='+id_RowGridvf+'&value='+ui.item.value+'&name_column='+Xcode,
                           dataType: 'text', 
			               success: function(data){
			                    $("#"+codeFocu).unmask();   
			                   if(XnextFocus!=null)   
				                 $('input[id="'+XnextFocus+'"]').focus();  
				                 else  
				                 $("#"+codeFocu).blur();
			                }
		             });
                
               
              
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $("#"+codeFocu).autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+Xcode+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",   $("#"+codeFocu).val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                   
                    .append( "<a >"+
                               "<div   style='height: 12px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.value+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 100px;'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };
            
            
                
     
} 




function  LoadAutoCompletAjax(Xcode,Xlibelle,XnextFocus,namelistAjax){


    
$('input[id="'+Xlibelle+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee, 
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "bylabel",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
                success: function(data) { 
                response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }));
            },
                error: function(jqXHR, textStatus, errorThrown){
                     $('input[id="'+Xlibelle+'"]').blur();
                      console.log( textStatus);
                }
            });
        },  
        
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode+'"]').val(ui.item.value);
                $('input[id="'+Xlibelle+'"]').val(ui.item.label);
                $('input[id="'+Xlibelle+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);} 
                if(XnextFocus!=null)  $('input[id="'+XnextFocus+'"]').focus();  else  $('input[id="'+Xlibelle+'"]').blur();
               
                //window.location="#"; //location to go when you select an item
            },
        focus: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xlibelle+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val("");
                //$('input[id="'+Xlibelle+'"]').val(ui.item.label);
     
            }    
    }).focus(function() {
         $('input[id="'+Xlibelle+'"]').autocomplete( "enable" );
         $(this).autocomplete("search", $('input[id="'+Xlibelle+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
    
	    var  styleLibelle="position: relative;top: -6px;";
	    var  styleCode="position: relative;top: -33.5px;margin-left: 250px;";
	    var  styleLeA="width:98%;";
	    var  styleLeDiv="height: 12px;";
	    
	    /*if(item.value=="Code"){
	        styleLibelle="position: relative;top: -6px;;background-color:#c3dde0;";
	        styleCode="position: relative;top: -33.5px;margin-left: 250px;background-color:#c3dde0;width:auto;display:inline-block;";
	        styleLeA="width:100%;";
	        styleLeDiv="height: 12px;position: fixed;z-index: 99999;";
	    }*/
	    
 
    
       return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a style='"+styleLeA+"' >"+
                               "<div   style='"+styleLeDiv+"'>"+
                                 "<div style='"+styleLibelle+"'  >"+item.label+"</div>"+
                                 "<div style='"+styleCode+"'  >"+item.value+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
            };
$('input[id="'+Xcode+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                //data: request
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                }
                
                ,
                success: function(data) { 
                	
                  if(    $('input[id="'+Xcode+'"]').val().length == 0   ){
                	 
                  }else{
                
                  }
                	  
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                     $('input[id="'+Xcode+'"]').blur();
                      console.log( textStatus); //fn_display_er_ajax(jqXHR);
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode+'"]').val(ui.item.value);
                $('input[id="'+Xlibelle+'"]').val(ui.item.label);
                $('input[id="'+Xcode+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                
                if(XnextFocus!=null)  $('input[id="'+XnextFocus+'"]').focus();  else  $('input[id="'+Xcode+'"]').blur();
              
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $('input[id="'+Xcode+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+Xcode+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",   $('input[id="'+Xcode+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
    
        var  styleCode="position: relative;top: -6px;";
	    var  styleLibelle="position: relative;top: -33.5px;margin-left: 150px;";
	    var  styleLeA="width:98%;";
	    var  styleLeDiv="height: 12px;";
	    
                return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                   
                    .append( "<a  style='"+styleLeA+"' >"+
                               "<div   style='"+styleLeDiv+"'>"+
                                 "<div style='"+styleCode+"'  >"+item.value+"</div>"+
                                 "<div style='"+styleLibelle+"'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };
            
            
                
     
} 





/**********************************************************LoadAutoCompletAjax(Xcode,Xlibelle,XnextFocus,namelistAjax,margincode,maginlib)********************************************************/


function  LoadAutoCompletAjax_with_marGin(Xcode,Xlibelle,XnextFocus,namelistAjax,margincode,marginlib){



    
$('input[id="'+Xlibelle+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee, 
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "bylabel",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
                success: function(data) {
                //$('input[id="'+Xcode+'"]').val("");
                //$('input[id="'+Xlibelle+'"]').val(""); 
               
                response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }));
            },
                error: function(jqXHR, textStatus, errorThrown){
                     $('input[id="'+Xlibelle+'"]').blur();
                      console.log( textStatus); //fn_display_er_ajax(jqXHR);
                }
            });
        },  
        
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode+'"]').val(ui.item.value);
                $('input[id="'+Xlibelle+'"]').val(ui.item.label);
                $('input[id="'+Xlibelle+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);} 
                if(XnextFocus!=null)  $('input[id="'+XnextFocus+'"]').focus();  else  $('input[id="'+Xlibelle+'"]').blur();
               
                //window.location="#"; //location to go when you select an item
            },
        focus: function(event, ui) {
                event.preventDefault();
                
                 
               
                $('input[id="'+Xlibelle+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val("");
                //$('input[id="'+Xlibelle+'"]').val(ui.item.label);
     
            }    
    }).focus(function() {
         $('input[id="'+Xlibelle+'"]').autocomplete( "enable" );
         
          
          
          
         $(this).autocomplete("search", $('input[id="'+Xlibelle+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
    
	    var  styleLibelle="position: relative;top: -6px;width:auto;";
	    var  styleCode="position: relative;top: -33.5px;margin-left:"+margincode+"px;width:auto;";
	    var  styleLeA="width:98%;";
	    var  styleLeDiv="height: 14px;";
	    
	    /*if(item.value=="Code"){
	        styleLibelle="position: relative;top: -6px;;background-color:#c3dde0;";
	        styleCode="position: relative;top: -33.5px;margin-left: 250px;background-color:#c3dde0;width:auto;display:inline-block;";
	        styleLeA="width:100%;";
	        styleLeDiv="height: 12px;position: fixed;z-index: 99999;";
	    }*/
	    
 
    
       return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a style='"+styleLeA+"' >"+
                               "<div   style='"+styleLeDiv+"'>"+
                                 "<div style='"+styleLibelle+"'  >"+item.label+"</div>"+
                                 "<div style='"+styleCode+"'  >"+item.value+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
            };
$('input[id="'+Xcode+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                //data: request
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                }
                
                ,
                success: function(data) { 
                	
                  if(    $('input[id="'+Xcode+'"]').val().length == 0   ){
                	 
                  }else{
                
                  }
                	  
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                      $('input[id="'+Xcode+'"]').blur();
                      console.log( textStatus); //fn_display_er_ajax(jqXHR);
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode+'"]').val(ui.item.value);
                $('input[id="'+Xlibelle+'"]').val(ui.item.label);
                $('input[id="'+Xcode+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                
                if(XnextFocus!=null)  $('input[id="'+XnextFocus+'"]').focus();  else  $('input[id="'+Xcode+'"]').blur();
              
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $('input[id="'+Xcode+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+Xcode+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",   $('input[id="'+Xcode+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
    
        
	    
	    
	    var  styleCodeX="position: relative;top: -6px;width:auto;";
	    var  styleLibelleX="position: relative;top: -33.5px;margin-left:"+marginlib+"px;width:auto;";
	    var  styleLeAX="width:auto;";
	    var  styleLeDivX="height: 15px;";
	    
                return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                   
                    .append( "<a  style='"+styleLeAX+"' >"+
                               "<div   style='"+styleLeDivX+"'>"+
                                 "<div style='"+styleCodeX+"'  >"+item.value+"</div>"+
                                 "<div style='"+styleLibelleX+"'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };
            
            
                
     
} 
/********************************************************************************************************************************************/






/********************************************************************************************************************************************/
/********************************************************************************************************************************************/
/********************************************************************************************************************************************/


 
function  LoadOtherAutocompletesAjax(XItemValue2,hidAction2,Xcode2,Xlibelle2,XnextFocus2){


 
$('input[id="'+Xcode2+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: baseAjaxUrl+urlSmodule+"?HiddenAction="+hidAction2, 
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    codeInput:document.getElementById(XItemValue2).value,
                    fieldcode:Xcode2,
                    fieldlabel:Xlibelle2
                },
                success: function(data) { 
                response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode2],
                        label: obj[Xlibelle2]
                    };
                }));
            },
                error: function(jqXHR, textStatus, errorThrown){
                   
                     $('input[id="'+Xcode2+'"]').blur();
                      console.log( textStatus);
                      
                   
                }
            });
        },  
        
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode2+'"]').val(ui.item.value);
                $('input[id="'+Xlibelle2+'"]').val(ui.item.label);
                $('input[id="'+Xcode2+'"]').autocomplete( "disable" );
                
                if( typeof(onChange_select) == 'function' ){
                     $('input[id="'+Xcode2+'"]').blur();
                      onChange_select(Xcode2);
                 }else{
                     if(XnextFocus2!=null)  $('input[id="'+XnextFocus2+'"]').focus();  else   $('input[id="'+Xcode2+'"]').blur(); 
                 }
              
               
                //window.location="#"; //location to go when you select an item
            },
        focus: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode2+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+Xcode2+'"]').autocomplete( "enable" );
         $(this).autocomplete("search", $('input[id="'+Xcode2+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a>"+
                               "<div  style='height: 15px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.value+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 200px;'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
            };
            
$('input[id="'+Xlibelle2+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: baseAjaxUrl+urlSmodule+"?HiddenAction="+hidAction2, 
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "bylabel",
                    codeInput:document.getElementById(XItemValue2).value,
                    fieldcode:Xcode2,
                    fieldlabel:Xlibelle2
                },
                success: function(data) { 
                response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode2],
                        label: obj[Xlibelle2]
                    };
                }));
            },
                error: function(jqXHR, textStatus, errorThrown){
                    $('input[id="'+Xlibelle2+'"]').blur();
                     alert(jqXHR.responseText);
                      
                     
                }
            });
        },  
        
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xcode2+'"]').val(ui.item.value);
                $('input[id="'+Xlibelle2+'"]').val(ui.item.label);
                $('input[id="'+Xlibelle2+'"]').autocomplete( "disable" );
                
                
                 if( typeof(onChange_select) == 'function' ){
                      $('input[id="'+Xlibelle2+'"]').blur();
                      onChange_select(Xcode2);
                 }else{
                     if(XnextFocus2!=null)  $('input[id="'+XnextFocus2+'"]').focus();  else   $('input[id="'+Xlibelle2+'"]').blur(); 
                 }
                 
                
                
                
                
               
                //window.location="#"; //location to go when you select an item
            },
        focus: function(event, ui) {
                event.preventDefault();
                $('input[id="'+Xlibelle2+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+Xlibelle2+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",  $('input[id="'+Xlibelle2+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a>"+
                               "<div  style='height: 15px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.label+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 200px;'  >"+item.value+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
            };

}  



function  LoadOtherAutocompletesAjax_grid(recapVal,hidActionServeur,codIn,libgrid,XcodeBean,Xlibellebean,XnextFocusGrid,isInput){
        

  var ElmmmInput;
  if(isInput)
   ElmmmInput=$('input[id="'+recapVal+'"]').val();
    else
   ElmmmInput=recapVal;


$('input[id="'+codIn+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: baseAjaxUrl+urlSmodule+"?HiddenAction="+hidActionServeur, 
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    codeInput:                ElmmmInput ,
                    fieldcode:XcodeBean,
                    fieldlabel:Xlibellebean
                },
                success: function(data) { 
                response($.map(data, function(obj) {
                    return {
                        value: obj[XcodeBean],
                        label: obj[Xlibellebean]
                    };
                }));
            },
                error: function(jqXHR, textStatus, errorThrown){
                   
                    
                      console.log( textStatus);
                      
                   
                }
            });
        },  
        
       
        
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codIn+'"]').val(ui.item.value);
                $('input[id="'+libgrid+'"]').val(ui.item.label);
                $('input[id="'+codIn+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(codIn);}
                if(XnextFocusGrid!=null)  $('input[id="'+XnextFocusGrid+'"]').focus();  else  $('input[id="'+codIn+'"]').blur();
               
                
            },
        focus: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codIn+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+codIn+'"]').autocomplete( "enable" );
         $(this).autocomplete("search", $('input[id="'+codIn+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a>"+
                               "<div  style='height: 15px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.value+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 200px;'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
            };
            
       
            
$('input[id="'+libgrid+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: baseAjaxUrl+urlSmodule+"?HiddenAction="+hidActionServeur, 
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "bylabel",
                    codeInput:         ElmmmInput,
                    fieldcode:XcodeBean,
                    fieldlabel:Xlibellebean
                },
                success: function(data) { 
                response($.map(data, function(obj) {
                    return {
                        value: obj[XcodeBean],
                        label: obj[Xlibellebean]
                    };
                }));
            },
                error: function(jqXHR, textStatus, errorThrown){
                      console.log( textStatus);
                        //fn_display_er_ajax(jqXHR);
                     
                }
            });
        },  
          
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codIn+'"]').val(ui.item.value);
                $('input[id="'+libgrid+'"]').val(ui.item.label);
                $('input[id="'+libgrid+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(codIn);}
                if(XnextFocusGrid!=null)  $('input[id="'+XnextFocusGrid+'"]').focus();  else  $('input[id="'+libgrid+'"]').blur();
               
                //window.location="#"; //location to go when you select an item
            },
        focus: function(event, ui) {
                event.preventDefault();
                $('input[id="'+libgrid+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+libgrid+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",  $('input[id="'+libgrid+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a>"+
                               "<div  style='height: 15px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.label+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 200px;'  >"+item.value+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
            };
   
}  
 
 
 

function  LoadAutoCompletGridV2(Xcode,Xlibelle,namelistAjax,codeFocu,XnextFocus,idInputNextFocus){


$('input[id="'+codeFocu+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
               success: function(data) { 
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                     
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codeFocu+'"]').val(ui.item.value);
                $('input[id="'+XnextFocus+'"]').val(ui.item.label);
                $('input[id="'+codeFocu+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(codeFocu);} 
                
                if(idInputNextFocus!=null)   $('input[id="'+idInputNextFocus+'"]').focus();  
				   else  
                $('input[id="'+codeFocu+'"]').blur();
                
                //if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                //alert(XnextFocus);
                //alert(id_RowGridvf.aaData[0][0]);
                /*jQuery.ajax({  
                           type: 'POST',  
                           url: urlsUpdateURL, 
                           data:'hid_act=updateListCorr&idTosendsd='+id_RowGridvf+'&value='+ui.item.value+'&name_column='+Xcode,
                           dataType: 'text', 
			               success: function(data){
			                    $("#"+codeFocu).unmask();   
			                   if(XnextFocus!=null)   
				                 $('input[id="'+XnextFocus+'"]').focus();  
				                 else  
				                 $("#"+codeFocu).blur();
			                }
		             });*/
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $('input[id="'+codeFocu+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+codeFocu+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",  $('input[id="'+codeFocu+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                   
                    .append( "<a >"+
                               "<div   style='height: 12px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.value+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 100px;'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };
/**********************************************************************************************************************/

$('input[id="'+XnextFocus+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "bylabel",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
               success: function(data) { 
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                      
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codeFocu+'"]').val(ui.item.value);
                $('input[id="'+XnextFocus+'"]').val(ui.item.label);
                $('input[id="'+XnextFocus+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(codeFocu);} 
                if(idInputNextFocus!=null)   $('input[id="'+idInputNextFocus+'"]').focus();  
				   else 
                $('input[id="'+XnextFocus+'"]').blur();
                //if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                //alert(XnextFocus);
                //alert(id_RowGridvf.aaData[0][0]);
                /*jQuery.ajax({  
                           type: 'POST',  
                           url: urlsUpdateURL, 
                           data:'hid_act=updateListCorr&idTosendsd='+id_RowGridvf+'&value='+ui.item.value+'&name_column='+Xcode,
                           dataType: 'text', 
			               success: function(data){
			                    $("#"+codeFocu).unmask();   
			                   if(XnextFocus!=null)   
				                 $('input[id="'+XnextFocus+'"]').focus();  
				                 else  
				                 $("#"+codeFocu).blur();
			                }
		             });*/
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $('input[id="'+XnextFocus+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+XnextFocus+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",   $('input[id="'+XnextFocus+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                   
                    .append( "<a >"+
                               "<div   style='height: 12px;'>"+
                                 "<div style='position: relative;top: -6px;'  >"+item.label+"</div>"+
                                 "<div style='position: relative;top: -33.5px;margin-left: 250px;'  >"+item.value+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };

/*********************************************************************************************************************/            
            
            
} 

function  LoadAutoCompletGridV2_withMG(Xcode,Xlibelle,namelistAjax,codeFocu,XnextFocus,idInputNextFocus,widthCod,widthLib){

 
$('input[id="'+codeFocu+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
          
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "byId",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
               success: function(data) { 
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                     
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codeFocu+'"]').val(ui.item.value);
                $('input[id="'+XnextFocus+'"]').val(ui.item.label);
                $('input[id="'+codeFocu+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(codeFocu);} 
                
                if(idInputNextFocus!=null)   $('input[id="'+idInputNextFocus+'"]').focus();  
				   else  
                $('input[id="'+codeFocu+'"]').blur();
                
                //if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                //alert(XnextFocus);
                //alert(id_RowGridvf.aaData[0][0]);
                /*jQuery.ajax({  
                           type: 'POST',  
                           url: urlsUpdateURL, 
                           data:'hid_act=updateListCorr&idTosendsd='+id_RowGridvf+'&value='+ui.item.value+'&name_column='+Xcode,
                           dataType: 'text', 
			               success: function(data){
			                    $("#"+codeFocu).unmask();   
			                   if(XnextFocus!=null)   
				                 $('input[id="'+XnextFocus+'"]').focus();  
				                 else  
				                 $("#"+codeFocu).blur();
			                }
		             });*/
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $('input[id="'+codeFocu+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+codeFocu+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",  $('input[id="'+codeFocu+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
                var  styleCodeX="position: relative;top: -6px;width:auto;";
	    var  styleLibelleX="position: relative;top: -33.5px;margin-left:"+widthLib+"px;width:auto;";
	    var  styleLeAX="width:auto;";
	    var  styleLeDivX="height: 15px;";
	    
                return $( "<li    ></li>" )
                    .data( "item.autocomplete", item )
                   
                    .append( "<a  style='"+styleLeAX+"' >"+
                               "<div   style='"+styleLeDivX+"'>"+
                                 "<div style='"+styleCodeX+"'  >"+item.value+"</div>"+
                                 "<div style='"+styleLibelleX+"'  >"+item.label+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };
/**********************************************************************************************************************/

 


$('input[id="'+XnextFocus+'"]').autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 0,
        autoFocus: true,
        cacheLength: 0,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: UrlServerListeCorrlee,  
                dataType: "json",
                data: {
                    term : request.term,
                    typeSearch : "bylabel",
                    leContenue:"ssssssss",
                    nameListAutocompletAjax:namelistAjax,
                    fieldcode:Xcode,
                    fieldlabel:Xlibelle
                },
               success: function(data) { 
               response($.map(data, function(obj) {
                    return {
                        value: obj[Xcode],
                        label: obj[Xlibelle]
                    };
                }
                ));
                	
                	
            },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                     
                }
            });
        },
        select: function(event, ui) {
                event.preventDefault();
                $('input[id="'+codeFocu+'"]').val(ui.item.value);
                $('input[id="'+XnextFocus+'"]').val(ui.item.label);
                $('input[id="'+XnextFocus+'"]').autocomplete( "disable" );
                if( typeof(onChange_select) == 'function' ){onChange_select(codeFocu);} 
                if(idInputNextFocus!=null)   $('input[id="'+idInputNextFocus+'"]').focus();  
				   else 
                $('input[id="'+XnextFocus+'"]').blur();
                //if( typeof(onChange_select) == 'function' ){onChange_select(Xcode);}
                //alert(XnextFocus);
                //alert(id_RowGridvf.aaData[0][0]);
                /*jQuery.ajax({  
                           type: 'POST',  
                           url: urlsUpdateURL, 
                           data:'hid_act=updateListCorr&idTosendsd='+id_RowGridvf+'&value='+ui.item.value+'&name_column='+Xcode,
                           dataType: 'text', 
			               success: function(data){
			                    $("#"+codeFocu).unmask();   
			                   if(XnextFocus!=null)   
				                 $('input[id="'+XnextFocus+'"]').focus();  
				                 else  
				                 $("#"+codeFocu).blur();
			                }
		             });*/
            },
        focus: function(event, ui) {
                event.preventDefault();
               // alert("1");
              
                $('input[id="'+XnextFocus+'"]').autocomplete( "enable" );
                //$('input[id="'+Xcode+'"]').val(ui.item.value);
                //$('input[id="'+Xlibelle+'"]').val("");
            }    
    }).focus(function() {
         $('input[id="'+XnextFocus+'"]').autocomplete( "enable" );
         $(this).autocomplete("search",   $('input[id="'+XnextFocus+'"]').val());
    }).data( "autocomplete" )._renderItem = function( ul, item ) {
              
                    
                   
                    var  styleLibelle="position: relative;top: -6px;width:auto;";
	    var  styleCode="position: relative;top: -33.5px;margin-left:"+widthCod+"px;width:auto;";
	    var  styleLeA="width:98%;";
	    var  styleLeDiv="height: 14px;";
	    
	    /*if(item.value=="Code"){
	        styleLibelle="position: relative;top: -6px;;background-color:#c3dde0;";
	        styleCode="position: relative;top: -33.5px;margin-left: 250px;background-color:#c3dde0;width:auto;display:inline-block;";
	        styleLeA="width:100%;";
	        styleLeDiv="height: 12px;position: fixed;z-index: 99999;";
	    }*/
	    
  
    
       return $( "<li ></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a style='"+styleLeA+"' >"+
                               "<div   style='"+styleLeDiv+"'>"+
                                 "<div style='"+styleLibelle+"'  >"+item.label+"</div>"+
                                 "<div style='"+styleCode+"'  >"+item.value+"</div>"+
                               "</div>"+
                             "</a>" )
                    
                    .appendTo( ul );
                    
            };

/*********************************************************************************************************************/            
       
            
} 


