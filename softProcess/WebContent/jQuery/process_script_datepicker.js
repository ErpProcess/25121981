var today_process = this_Day();


$(document).ready(function(){
UR_Start();
showdate();
today_process = this_Day();
$(":input[type=datepicker]").each(function (cnt, item) {
var ELTsXDxcvfcc=$(item).val(); 

 if( ELTsXDxcvfcc !==null &&  ELTsXDxcvfcc!=="" &&  ELTsXDxcvfcc!== undefined ){
 $(item).val(DateFormat.format.date(ELTsXDxcvfcc, "dd/MM/yyyy"));
 }


if ( $(item).is('[readonly="readonly"]') ||    $(item).is('[readonly]')     ) { $(item).datepicker('disable'); return; }
 
$(item).datepicker({ 
    altField: "#datepicker",
    closeText: 'Fermer',
    prevText: 'Précédent',
    nextText: 'Suivant',
    currentText: 'Aujourd\'hui',
    firstDay: 1 ,
    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
    monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
    dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
    weekHeader: 'Sem.',
    changeMonth: true,
    changeYear: true,
    dateFormat: 'dd/mm/yy',
    onClose : function( date, datepicker ){
    
     
     var deuxieme_date = $(item).attr('compareTo');
     var premier_date  = $(item).attr('comparedTo');
     var dateSys_date  = $(item).attr('comparedateSys');
       
   
     
     if(   dateSys_date !=undefined    &&     dateSys_date=="dateSys"   ){ 
     
	   var  val_deux_date    = today_process;   
	   var  retournMessage   = comparDatepicker(date,val_deux_date);
	     
	     if(  retournMessage!="" && date!=""  ){
	       
	        $(item).val('');
	        $(item).focus();
	         alertExt("",retournMessage,"3");
	   }
      }
      
      
     
     if(  premier_date !=undefined   &&  premier_date!="dateSys"    ){ 
     
	    var val_premier_date=$("#"+premier_date).val();   
	    var  retournMessage=comparDatepicker(date, val_premier_date);
	      if(retournMessage!="" && date!=""  && val_premier_date!="" ){  
	       
	         $(item).val('');
	         $(item).focus();
	          alertExt("",retournMessage,"3");
	   } 
	   
      }
     
     if(  deuxieme_date !=undefined    &&  deuxieme_date=="dateSys"    ){ 
     
	   var val_deuxiem_date = today_process;  
	   
	 
	       
	       
	   var  retournMessage  = comparDatepicker(date,val_deuxiem_date);
	   
	       
	      
	     if(retournMessage!="" && date!=""){
	         
	        $(item).val('');
	        $(item).focus();
	        
	        alertExt("",retournMessage,"3");
	   }
	   
      }
      
     if(   deuxieme_date !=undefined   &&   deuxieme_date!="dateSys"      ){ 
     
        var val_deuxiem_date =$("#"+deuxieme_date).val();   
	    var  retournMessage =  comparDatepicker(val_deuxiem_date,date);
	      if(retournMessage!="" && date!=""  && val_deuxiem_date!="" ){  
	         
	        $(item).val('');
	        $(item).focus();
	        alertExt("",retournMessage,"3");
	   } 
     }
 
     }
 });
});

$('.dataTable tbody td input[type=datepicker]').live('focus', function(e) {
var ELTsXDxcvfcc2=$(this).val();  

if( ELTsXDxcvfcc2 !==null &&  ELTsXDxcvfcc2!=="" &&  ELTsXDxcvfcc2!== undefined ){
 $(item).val(DateFormat.format.date(ELTsXDxcvfcc2, "dd/MM/yyyy"));
 }
 
if ( $(this).is('[readonly="readonly"]') ||    $(this).is('[readonly]')     ) { $(this).datepicker('disable'); return; }

 
$(this).datepicker({ 
    altField: "#datepicker",
    closeText: 'Fermer',
    prevText: 'Précédent',
    nextText: 'Suivant',
    currentText: 'Aujourd\'hui',
    firstDay: 1 ,
    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
    monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
    dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
    weekHeader: 'Sem.',
    changeMonth: true,
    changeYear: true,
    dateFormat: 'dd/mm/yy',
     
    onClose : function( date, datepicker ){
    
     
     var deuxieme_date = $(this).attr('compareTo');
     
     var premier_date  = $(this).attr('comparedTo');
     
     var dateSys_date  = $(this).attr('comparedateSys');
       
     
     if(   dateSys_date=="dateSys"  &&  dateSys_date !=undefined      ){ 
     
	   var  val_deux_date = today_process;   
	   var  retournMessage   =comparDatepicker(date,val_deux_date);
	     if(  retournMessage!="" && date!=""  ){
	        $(this).val('');
	        $(this).focus();
	        alertExt("",retournMessage,"3");
	         
	         
	       
	   }
      }
      
      
     if(   premier_date !=undefined  &&   premier_date!="dateSys"    ){ 
     
	    var  val_premier_date=$("#"+premier_date).val();   
	    var  retournMessage=comparDatepicker(date, val_premier_date);
	      if(retournMessage!="" && date!=""  && val_premier_date!="" ){  
	        
	         $(this).val('');
	         $(this).focus();
	          alertExt("",retournMessage,"3");
	   } 
	   
      }
      
      
     
     if(  deuxieme_date !=undefined   &&  deuxieme_date=="dateSys"        ){ 
     
	   var  val_deuxiem_date = today_process;   
	   var  retournMessage   = comparDatepicker(date,val_deuxiem_date);
	   
	     if(  retournMessage!="" && date!=""  ){
	         
	        $(this).val('');
	        $(this).focus();
	        alertExt("",retournMessage,"3");
	   }
      }
      
     if(  deuxieme_date!="dateSys"  &&  deuxieme_date !=undefined  ){ 
     
        var  val_deuxiem_date  = $("#"+deuxieme_date).val();   
	    var  retournMessage    = comparDatepicker(val_deuxiem_date,date);
	      if(retournMessage!="" && date!=""  && val_deuxiem_date!="" ){  
	        
	        $(this).val('');
	        $(this).focus();
	        alertExt("",retournMessage,"3");
	   } 
     }
    
	   $(this).blur();
  
     }
     
});
});
});






function comparDatepicker(sdate1,sdate2) {
 
var message="";
 
var fff=false;
var date1 = new Date();
date1.setFullYear(sdate1.substr(6,4));
date1.setMonth(sdate1.substr(3,2));
date1.setDate(sdate1.substr(0,2));
date1.setHours(0);
date1.setMinutes(0);
date1.setSeconds(0);
date1.setMilliseconds(0);
var dProcess=date1.getTime();

 
 
 
 
var date2 = new Date();
date2.setFullYear(sdate2.substr(6,4));
date2.setMonth(sdate2.substr(3,2));
date2.setDate(sdate2.substr(0,2));
date2.setHours(0);
date2.setMinutes(0);
date2.setSeconds(0);
date2.setMilliseconds(0);
var dProcess2=date2.getTime();
 
 
 
 
   
if(dProcess>dProcess2){  
  
 
     message="";
    
}else if(dProcess==dProcess2){

 
message="";
 
}else{
 
 
 
  
   if(sdate2==today_process)
    message=" Veuillez saisir une date supérieur ou egal a la date actuelle ";
    else
    message=" Erreur Date . Veuillez vérifier que la ' date début ' doit  inférieur ou égal  a la  ' date Fin '   ";
    
 
}
 
 
 
 return message;
 
}



function this_Day(){
var today_p = new Date();
var ddX = today_p.getDate();
var mmX = today_p.getMonth()+1; //January is 0!
var yyyyX = today_p.getFullYear();

if(ddX<10) {
    ddX='0'+ddX
} 

if(mmX<10) {
    mmX='0'+mmX
} 
today_p = ddX+'/'+mmX+'/'+yyyyX;
return today_p;
}


function showdate()
{

var currentTime = new Date() 
var month = currentTime.getMonth() +1

var day = currentTime.getDate() 
var year = currentTime.getFullYear() 
var hours = currentTime.getHours() 
var minutes = currentTime.getMinutes() 
var seconds = currentTime.getSeconds() 

if(month<10)
month="0"+month;

if(day<10)
day="0"+day;
var bbbbb=day + "/" + month + "/" + year ;	
document.getElementById('theDateTime').innerHTML=bbbbb;
}


function UR_Start() 
{
	UR_Nu = new Date;
	UR_Indhold = showFilled(UR_Nu.getHours()) + ":" + showFilled(UR_Nu.getMinutes()) + ":" + showFilled(UR_Nu.getSeconds());
	document.getElementById("uro").innerHTML = UR_Indhold;
	setTimeout("UR_Start()",1000);
}
function showFilled(Value) 
{
	return (Value > 9) ? "" + Value : "0" + Value;
}