/*****************************************************DO GENERATE  METHODE  AJAX ****************************************************************/


function doGenerate_methode_ajax(TYPE_P_jQuery,URL_P_jQuery,HAction_P_jQuery,dataType_P_jQuery,async_P_jQuery,fn_response){		 

     $('#myformToServeur').find('input[name="HiddenAction"]').val(HAction_P_jQuery);
 	 var form_all_data  = {};
	  $.each($("#myformToServeur").serializeArray(), function (i, field) {
	    form_all_data[field.name] = field.value || "";
	  });
	   
    jQuery.ajax({ 
               type: TYPE_P_jQuery,  
	           url:  URL_P_jQuery, 
	           data: form_all_data,
	           dataType: dataType_P_jQuery, 
	           async: async_P_jQuery,
	           success: function(data){
	             fn_response(data);
               },
	           error: function (request, status, error) {
	             fn_display_er_ajax(request);
	           } 
        });
}
/*******************************************************************************************************************************************/	

function doGenerate_methode_ajaxWithReturn(TYPE_P_jQuery,URL_P_jQuery,HAction_P_jQuery,dataType_P_jQuery,async_P_jQuery){
    var Sddddddddd;
 		 
     $('#myformToServeur').find('input[name="HiddenAction"]').val(HAction_P_jQuery);
 	 var form_all_data  = {};
	  $.each($("#myformToServeur").serializeArray(), function (i, field) {
	    form_all_data[field.name] = field.value || "";
	  });
	  
	   
	   
    jQuery.ajax({ 
               type: TYPE_P_jQuery,  
	           url:  URL_P_jQuery, 
	           data: form_all_data,
	           dataType: dataType_P_jQuery, 
	           async: async_P_jQuery,
	           success: function(data){
	             Sddddddddd=data;
               },
	           error: function (request, status, error) {
	             fn_display_er_ajax(request);
	           } 
        });
        
        return Sddddddddd;
}



function fnAjax_WithReturn(TYPE_P_jQuery,URL_P_jQuery,HAction_P_jQuery,dataType_P_jQuery,async_P_jQuery,dataExTEND){
    var Sddddddddd;
 		 
     $('#myformToServeur').find('input[name="HiddenAction"]').val(HAction_P_jQuery);
 	 var form_all_data  = {};
	  $.each($("#myformToServeur").serializeArray(), function (i, field) {
	    form_all_data[field.name] = field.value || "";
	  });
	   if(dataExTEND != null)
	   $.extend(form_all_data, dataExTEND);
	   
    jQuery.ajax({ 
               type: TYPE_P_jQuery,  
	           url:  URL_P_jQuery, 
	           data: form_all_data,
	           dataType: dataType_P_jQuery, 
	           async: async_P_jQuery,
	           success: function(data){
	             Sddddddddd=data;
               },
	           error: function (request, status, error) {
	             fn_display_er_ajax(request);
	           } 
        });
        
        return Sddddddddd;
} 

/*******************************************************************************************************************************************/


function loadSelectAjax(id_region,name_list,codx,libx,data_index_sel,select_vide){
        
    		$.ajax({
			        url: UrlServerListeComboSelect,
			        data: {"nameList_select":name_list ,"fieldcode":codx,"fieldlabel":libx},  
			        dataType: "json", 
			        async: false,
			        type: "POST",
			        success: function(data) {
			        
			              $('option', '#'+id_region).remove();
			              var $regions = $('#'+id_region);
			              var  myMod=bs_fct_id;
			             
			        	 if(select_vide) 
			        	 $regions.append('<option value=""   >----Select---</option>');
			             /*if( myMod !="5"  &&  myMod !="1"   &&    $regions.attr('idonly') != undefined ){
			             var  bidan="";
			             }else{
			             
			             }*/
			             
			             
			             for (var h = 0; h <data.myliste.length; h++) {
			              var  sel='';
			             
			              
			              //if( myMod !="5"  &&  myMod !="1"    &&  data.myliste[h].keyx != data_index_sel  &&    $regions.attr('idonly') != undefined ) continue;
			              
			              //if(  $regions.attr('idonly') != undefined ) continue;
			              
			              if( data.myliste[h].keyx==data_index_sel){
			                 sel=' selected ';
			              }
			              
			              $regions.append('<option value="'+  data.myliste[h].keyx +'"      '+sel+'      >'+ data.myliste[h].valuex +'</option>');
			             }  
			            
			            
			        }
            });
}

 


 
      
      
function selectOptionvalue(theselecto,varbean){
var selectuu = document.getElementById(theselecto);
  for(var i = 0;i < selectuu.options.length;i++){
    if(selectuu.options[i].value == varbean )
              selectuu.options[i].selected = true; 
   }
}	

function checkedRadioGroup(IdradioGroup,Valuebean){
 
  var radioGroup = document.getElementsByName(IdradioGroup);
  var n=radioGroup.length;
	for (var i=0;i<n;i++){
	
		if (radioGroup[i].value==Valuebean){
			radioGroup[i].checked=true;
		}else{
		    radioGroup[i].checked=false;
		}
	}
}
 