
$(document).ready(function($){

$('.mone').maskx('### ### ##'+bs_patternDecimalFormat, {reverse: false});
//$('.money2').maskx('### ###,##'+bs_patternDecimalFormat, {reverse: true});
//$('.money2').maskx('### ### ##'+bs_patternDecimalFormat, {reverse: true});
$('.money2').maskx('### ### ### ### ### ### ##'+bs_patternDecimalFormat, {reverse: true});
$('.money_2').maskx('### ### ### ### ### ### ##0.00', {reverse: true});

$('.dataTable tbody td input[type=montant3]').on('keypress', function(e) {

var nbrvv=3;

if(bs_patternDecimalFormat=="0.000") nbrvv=3;
if(bs_patternDecimalFormat=="0.00") nbrvv=2;
if(bs_patternDecimalFormat=="0.0") nbrvv=1;

 
	var text=$(this).val();  
	/*var regex = new RegExp(",", 'g');
	text = text.replace(regex, '');
	var SSSS  =Math.round(text * Math.pow(10,nbrX)) / Math.pow(10,nbrX);
	var numS = new Number(SSSS);
    var fix  = numS.toFixed(nbrX);
	$(this).val(fix); */
	$(this).addClass( "money2" );
	if($(this).val()=="0.000"  ||  $(this).val()=="0.00"   || $(this).val()=="0.0"  )
	$(this).val("");
	
});

$(":input[type=montant3]").each(function (cnt, item) {  
var nbrX=3;
if(bs_patternDecimalFormat=="0.000") nbrX=3;
if(bs_patternDecimalFormat=="0.00") nbrX=2;
if(bs_patternDecimalFormat=="0.0") nbrX=1;

	var ELTs=$(item).val();  
	var text=ELTs; 
	var regex = new RegExp(",", 'g');
	text = text.replace(regex, '');
	var SSSS  =Math.round(text * Math.pow(10,nbrX)) / Math.pow(10,nbrX);
	var numS = new Number(SSSS);
    var fix = numS.toFixed(nbrX);
	$(item).val(fix); 
	$(item).addClass( "money2" );
	if($(item).val()=="0.000"  ||  $(item).val()=="0.00"   || $(item).val()=="0.0"  )
	$(item).val("");
});

 




$(":input[isboolean]").each(function (cnt, item) {

 if(  $(item).attr('type')=='checkbox'    ){  
	var ELTsXCC=$(item).val();
	

	if(ELTsXCC=="true")
	 $(item).attr('checked', true);
	 
	 if(ELTsXCC=="false")
     $(item).removeAttr('checked');
     
     	 
   }    
});

$(":input[isboolean]").on('change', function(){
if(  $(this).attr('type')=='checkbox'     ){
   if($(this).is(":checked")) {
      $(this).val("true");
      return;
   }else{
     $(this).val("false");
      return;
   }
    }
    
     
});


$(":input[type=int]").keypress(function (e) {
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)  ) {
       $("#errmsg").html("numeric").show().fadeOut("slow");
          return false;
    }
    
});

$(":input[type=double]").keypress(function (e) {

    
     if (e.which == 46  ) {
   	 return ;
   	 }
   	 
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)  ) {
   
      
      $("#errmsg").html("numeric").show().fadeOut("slow");
          return false;
    }
    
});


$(":input[type=number]").keypress(function (e) {

   
     if (e.which == 46  ) {
   	 return ;
   	 }
   	 
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)  ) {
   
      
      $("#errmsg").html("numeric").show().fadeOut("slow");
          return false;
    }
   
});
 
});

function convertStringToNumber(paramStringNbr){

if(paramStringNbr=="" ||  paramStringNbr=="NaN"  || paramStringNbr==null   || paramStringNbr==undefined    ) return 0;
  
 var mntRetour = paramStringNbr.replace(/ /g, '');
 
 return mntRetour;
           
}
function formatNumberJsXC(nubr,precis){
    var  textX = nubr; 
	var regex = new RegExp(",", 'g');
	var  text = textX.replace(regex, '');
	var  SSSS = Math.round(text * Math.pow(10,precis)) / Math.pow(10,precis);
	var  numS = new Number(SSSS);
    var  fix  = numS.toFixed(precis);
    
    if(fix==0) 
        return "";
	 else
        return ReplaceNumberWithCommas(fix);

}

function formatNumberJs(nubr,precis){


var nbrX=3;
if(bs_patternDecimalFormat=="0.000") nbrX=3;
if(bs_patternDecimalFormat=="0.00")  nbrX=2;
if(bs_patternDecimalFormat=="0.0")   nbrX=1;


    var  textX = nubr; 
	var regex = new RegExp(",", 'g');
	var  text = textX.replace(regex, '');
	var  SSSS = Math.round(text * Math.pow(10,nbrX)) / Math.pow(10,nbrX);
	var  numS = new Number(SSSS);
    var  fix  = numS.toFixed(nbrX);
    
    if(fix==0) 
        return "";
	 else
        return ReplaceNumberWithCommas(fix);

}
function ReplaceNumberWithCommas(yourNumber) {
    //Seperates the components of the number
    var n= yourNumber.toString().split(".");
    //Comma-fies the first part
   // n[0] = n[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
     n[0] = n[0].replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    //Combines the two sections
    var Gu =n.join("."); 
    
    if(Gu=="0.000" || Gu=="0.00" || Gu=="0.0"  )
    return "";
    else
    return Gu;
}




 