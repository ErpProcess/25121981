$(document).ready(function () {


 
$("#tblData").find("select:not([libre])").attr("disabled",tml_disabled=="true" ?true :false);
$("#tblData").find("input[idonly]").attr("readonly",tml_idReadonly=="true" ?true :false);	 
$("#tblData").find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly",tml_readonly=="true" ?true :false);


if( bs_fct_id=="3") {
   $("#tblData").find("input[idonly]").attr("readonly","readonly");
}
});    

var  lib_required="required";

function teste_required(){

     var  testeInto=true;      
     var  testecheko=false;  
     var  teste_List_not_empty=true; 
     var  retour_Teste_List="";
   
     

  $(":input["+lib_required+"]").each(function (cnt, item) {                     
          var $myFormxx = $("#myformToServeur");
        
           if(!$(item).val()) {
                $(item).css('border-color', 'red');
                testeInto=false; 
             }else{
               $(item).css('border-color', '');
             }
             
          if($(item).is("input[type=radio]")) {
             $(item).addClass("ppd"); 
             if($(item).is(":checked")){ 
                  $(item).addClass("btnradio_checked");    
                } else{    
                  $(item).addClass("btnradioUnchecked");  
                  testecheko=true;      
               }
           } 
          // alert("--------------------*******-"+$myFormxx[0].checkValidity()); 
          
        /* if ($myFormxx[0].checkValidity()) {                
             testeInto=true;        
          } */
          
        });
        
        if(!testeInto) {  alertExt(cham_videRouge,cham_obligatoire,"4");  return;  }
        
        return  testeInto;
        
}


function fn_display_er_ajax(jqXHR_Gen){

     var  display_er_ajax ="";
		 /*if ( jqXHR_Gen.status == 0) {
			  display_er_ajax = "Not connect.\n Verify Network.";
	      } else if ( jqXHR_Gen.status == 404) {
			  display_er_ajax = "Requested page not found. [404]";
		  } else if ( jqXHR_Gen.status == 500) {
		      display_er_ajax = jqXHR_Gen.responseText;
		  } else if(   jqXHR_Gen.status == 200){
			 //document.location = contexPath+"/index.jsp";
			  display_er_ajax = jqXHR_Gen.responseText;
		 }*/
		 
		 display_er_ajax = jqXHR_Gen.responseText;
		 
	 Ext.MessageBox.show({
		           title:"Enregistrement Valider",
		           msg: display_er_ajax ,
		           //buttons: Ext.MessageBox.YES,
		           buttons: Ext.Msg.OK,
		           width:600,
		           animEl: "mb4",
		           icon: Ext.MessageBox.INFO
      });
}



 
$(document).ready(function () {
 


$('body').on('keydown', 'input, select, textarea', function(e) {
var self = $(this)
  , form = self.parents('form:eq(0)')
  , focusable
  , next
  , prev
  ;

if (e.shiftKey  && e.keyCode == 13 ) {
  
     focusable =   form.find('input,a,select,button,textarea').filter(':visible');
     prev = focusable.eq(focusable.index(this)-1); 
    
     if (prev.length) {
         prev.focus();
     } else {
     
      //doValidAction();
        
    }
     return false;
  
} else if (e.keyCode == 13  &&  !e.shiftKey ) {
    focusable = form.find('input,a,select,button,textarea').filter(':visible');
   
     //next = focusable.eq(focusable.index(this)+1);
    
     
     var inputType = $(this).attr('nextElement');

    // $('#'+inputType).focus(); 
     
     if(inputType=="btValidx"){
        doValidAction();
         //$('[id="'+inputType+'"]').focus();  
     }else{
       $('[id="'+inputType+'"]').focus();  
     }
    //if (next.length) {
       // next.focus();
   // } else {
       // 
    //}
    return false;
}
});                
                      
});



