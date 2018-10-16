<%@ page language="java" import="java.util.*"   contentType="text/html; charset=UTF-8"  %>
<c:set var="pathRootac" value="<%=request.getContextPath() %>" ></c:set>
<!DOCTYPE html>
<html class="no-js">
<head>
<link rel="icon" href="<%=request.getContextPath()%>/img/process.gif" type="image/gif">
<style>
/* Paste this css to your style sheet file or under head tag */
/* This only works with JavaScript,  if it's not present, don't show loader */
.no-js #loader { display: none;  }
.js #loader { display: block; position: absolute; left: 100px; top: 0; }
.se-pre-con {
	position: fixed;
	left: 0px; 
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url("<%=request.getContextPath()%>/jQuery/images/loader-64x/Preloader_2.gif") center no-repeat #fff;
}
</style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/ext-3.0.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/styleIndex.css" />
<script  src="<%=request.getContextPath() %>/ext-3.0.0/adapter/ext/ext-base.js"  type="text/javascript"   ></script>
<script  src="<%=request.getContextPath() %>/ext-3.0.0/ext-all.js"               type="text/javascript"   ></script>
<script  src="<%=request.getContextPath() %>/jQuery/jquery1.7.js"                type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/jQuery/jquery.loadmask.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=request.getContextPath() %>/jQuery/jquery.loadmask.js'></script>
<script src="<%=request.getContextPath() %>/jQuery/modernizr.js"></script>
<script>

 	//paste this code under head tag or in a seperate js file.
	// Wait for window load
$(window).load(function() {
		// Animate loader off screen
		$(".se-pre-con").fadeOut("slow");;
});

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
     
      doWelcome();
        
    }
  
} else if (e.keyCode == 13  &&  !e.shiftKey ) {
    focusable = form.find('input,a,select,button,textarea').filter(':visible');
    next = focusable.eq(focusable.index(this)+1);
    
    
    if (next.length) {
        next.focus();
    } else {
        doWelcome();
    }
    return false;
}
});
});

	
	 var contexPath = "<%=request.getContextPath() %>";
	 var baseURL = window.location.protocol + '\/\/' + window.location.host + '\/';
	 $(document).ready(function () {
		UR_Start();
		showdate();
		});
		
		  function showdate()
{
var currentTime = new Date() 
var month = currentTime.getMonth() +1

var day = currentTime.getDate() 
var year = currentTime.getFullYear() 
var hours = currentTime.getHours() 
var minutes = currentTime.getMinutes() 
var seconds = currentTime.getSeconds() 
 
var bbbbb=day + " / " + month + " / " + year ;
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
	 function doActionLoadSS(rforaire) {
        Ext.get(rforaire).on('click', function(){
        Ext.MessageBox.show({
           title: 'Please wait',
           msg: 'Loading items...',
           progressText: 'Initializing...',
           width:300,
           progress:true,
           closable:false,
           animateTarget: rforaire
       });

       // this hideous block creates the bogus progress
       var f = function(v){
            return function(){
                if(v == 12){
                    Ext.MessageBox.hide();
                    Ext.example.msg('Done', 'Your fake items were loaded!');
                }else{
                    var i = v/11;
                    Ext.MessageBox.updateProgress(i, Math.round(100*i)+'% completed');
                }
           };
       };
       for(var i = 1; i < 13; i++){
           setTimeout(f(i), i*500);
       }
    });
     
}
 
  
		function dd(){
			$("#intWin").mask("Veuillez Patientez...");
		}
		
		function dss(){
		$("#intWin").unmask();
		}
			 


function doWelcome() {
 
    var  testeInto=true;      
    var  testecheko=false;      
	    $(":input[required]").each(function (cnt, item) {
	        var $myFormxx = $("#myform");
	          if(!$(item).val()) {
	            $(item).css('border-color', 'red');
	            testeInto=false;      
	          }else{
	           $(item).css('border-color', '');
	          }
	        if ($myFormxx[0].checkValidity()) 
	          {                
	             testeInto=true;        
	          } 
	    });
    if(!testeInto){
     var frss="   Vous devez entrer le(s) champs vide(s)";
	     Ext.MessageBox.show({
	           title:frss,
	           msg: '   Certains champs sont obligatoires pour que le formulaire soit pris en compte ',
	           //buttons: Ext.MessageBox.YES,
	           buttons: Ext.Msg.OK,
	           animEl: 'mb4',
	           icon: Ext.MessageBox.WARNING
	       });
  }else{
      document.getElementById("baseUrlProject").value=baseURL;
	  var URLO ="/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action";
	           $("#myform").find('input[name="HiddenAction"]').val("i$_ACT_LOAD_APP"); 
	           $("#myform").attr("action",contexPath+URLO);
	           $("#intWin").mask("Veuillez Patientez...");
	           $("#cand1").mask("");
	           $("#myform").submit(); 
             
  }

}
 

	 

 function  validAuthen(){
	    var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_GET_USER";  
	    $("#intWin").mask("Veuillez Patientez..."); 
	   jQuery.ajax({
	           type: "POST", 
	           url: url,
	           data:  "usrLogin="+$('#usr_login').val()+"&password="+$('#usr_pwd').val(),  
	           dataType: 'text',
	           async: false, 
	           success: function(data)
	           {
		           var  daddou=data;
		           $("#intWin").unmask();   
		           
		           if(daddou=="Erreur de mise a jour systeme"){
		        	   $('#usr_login').val('') ;
	                   $('#usr_login').val('') ;
	                   document.getElementById("btnSave").style.display="none";
		        	   Ext.Msg.alert('Status', "Erreur de mise à jour système");
	                  
		        	   
		           }else if(daddou=="looooadapplication"){
	            	 
	              
	                }else if( daddou=="loginGhalit" ){
	                
	                
	                   Ext.Msg.alert('Status', " Vérifier le Login ! !");
	                   $('#usr_login').val('') ;
	                   $('#usr_login').focus() ;
	                   document.getElementById("nomprenom").value="";
	                   document.getElementById('usr_pwd').value="";
	                 
	                }else if(daddou=="passwordGhalit" ){
	                Ext.Msg.alert('Status', daddou+" !");
	                	 
	                }else if( daddou=="lezim  login"  ){ 
	                  
	                document.getElementById('usr_login').focus() ;
	                }else{
	               
	                document.getElementById("nomprenom").value=daddou;
	                document.getElementById('usr_pwd').focus() ;
	                }
	               
	           }
	         });
	
	 


}   


function  getSoc_ERP(){

		document.getElementById("baseUrlProject").value=baseURL;
	    var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_GET_SOC";  
	    $.ajax({
	           type: "POST", 
	           url: url,
	           data:  "username=vide",   
	           success: function(data)
	           {
		           var  daddou=data;
	                document.getElementById('usr_login').focus() ;
	                document.getElementById("Ste").innerHTML = "Stï¿½:"+daddou;
	               
	           }
	         });
	
	 

 
}  

function  SetterLangueSystem(langO){
 $('#myform').find('input[name="HiddenAction"]').val("i$_ACT_SetterLangueSystem"); 
var urooo="/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?ln="+langO; 
$("#myform").attr("action",contexPath+urooo);
$("#myform").submit();


}

function  fifoA(){
 
 
 
        document.getElementById("baseUrlProject").value=baseURL;
	    var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_LOAD_APPX";  
 		$("#intWin").mask("Veuillez Patientez..."); 
		$.ajax({ 
	      type: 'POST', 
	      url: url, 
	      data:  "valResPassage=vide", 
	      dataType: 'text',
	      success: function (data) {
	     
	     alert(data);
	      
	     $("#intWin").unmask(); 
	     if(data=="fini"){
	      //window.location=baseURL+contexPath+"/index.jsp";
	     
	      //$('#usr_id').focus();
	     }else{
	      window.location=baseURL+contexPath+data+"/root.action";
	     }
	   
	     
	    },
	    error: function (result) {
	        
	     }
	});
  

}
 


function  getLangueSystem(){
 
		document.getElementById("baseUrlProject").value=baseURL;
	    var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_GET_LANG";  
 		$("#intWin").mask("Veuillez Patientez..."); 
		$.ajax({ 
	    type: 'POST', 
	    url: url, 
	    data:  "valResPassage=vide", 
	    dataType: 'json',
	    success: function (data) {
	     var trHTML = '<tr>';
        for (var h = 0; h <data.myliste.length; h++) {
         
         var sty='';
         
          if("${bs.lang_id}"!=""){ 
         
	         if("${bs.lang_id}"==data.myliste[h].code){
	         sty='  style=\"background-color: red;\"   ';
	         }else{
	         sty="";
	         }
         }else if( "${bs.lang_id}"==""  &&     data.myliste[h].code=="fr"    ){
         
           sty='  style=\"background-color: red;\"   ';
         
         }else{
         
           sty=''; 
         }
         
          trHTML +='<td '+sty+'><a  href="#"  onclick="SetterLangueSystem(this.id)"    id='+data.myliste[h].code+'  ><sapn  style=\'font-size: x-small;font-weight: bold;\' >'+data.myliste[h].libelle+'</sapn></a></td>';
        }
          trHTML +='</tr>';
	     $('#records_table').append(trHTML);
	     $('#usr_id').focus();
	     $("#intWin").unmask(); 
	     
	     
	     
	       
	    },
	    error: function (result) {
	        
	     }
	});

	
	 


} 
 
 
   
</script>
<title>Process</title>
</head>
<body 
id="SSSSSSSSS"     
 
  style="background: url('${pathRootac}/img/blue.jpg') no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;"
   
   >
<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
</body>
<ext:body     >
  <ext:window   id="intWin"    
   title="Process Engineering &nbsp;&nbsp;-&nbsp;&nbsp; &nbsp;Créateur de Valeur"  
    draggable="false"  resizable="false"  closable="false"    footer="true"    width="530"  height="308"     >
    <form  id="myform" method="post"  name="myform"  >
      <input  type="hidden"  name="HiddenAction"  id="HiddenAction"  value="doLoadApp.action"  style=" " >
      <table style="background: url('${pathRootac}/img/blue.jpg');width: 100%;" border="0"  >
        <tr>
          <td  ><div class="login"  style="width: 160px;background: none;height: 13px;"  >&nbsp;</div></td>
          <td  ><div class="login"  id="Ste"  style="width: 270px;background: none;height: 13px;"  >&nbsp; </div></td>
        </tr>
        <tr>
          <td  width="30%"  style="background: url('${pathRootac}/img/blue.jpg');" >
          
          
          <div class="login"  style="width: 160px;background: none;"  >
         <img src="${pathRootac}/img/logo44E.png"  align="middle" style="position: relative;background: none;left: -50px;top: 20px;" />
        
                  
				         </div>
          </td>
          <td   width="70%"  ><div class="login"  style="width: 270px;background: none;height: 110px;margin-top: -2%;"  >
              <table   style="margin-left: -3%;margin-top: 2%;">
                <tr>
                  <td   ><font style="font-weight: bold;font-size: 11px;">.::
                    <c:choose>
                      <c:when test="${not empty _index_Login}">
                        <c:out value="${_index_Login}"></c:out>
                      </c:when>
                      <c:otherwise>Login</c:otherwise>
                    </c:choose>
                    </font></td>
                  <td><input    id="usr_login"  name="usr_login"   required      
                           onBlur="validAuthen()"   value=""   Type="text"  maxlength="4"    style="width: 70px;height: 22px;font-weight: bold;"   /></td>
                  <td></td>
                </tr>
                <tr>
                  <td   ><font style="font-weight: bold;font-size: 11px;">.::
                    <c:choose>
                      <c:when test="${not empty _index_Utilisateur}">
                        <c:out value="${_index_Utilisateur}"></c:out>
                      </c:when>
                      <c:otherwise>Utilisateur</c:otherwise>
                    </c:choose>
                    </font></td>
                  <td><input id="nomprenom"  name="nomprenom"  type="text"   readonly="readonly" 
                     value=""  style="width: 150px;height: 22px;background: none;font-weight: bold;"   >
                    <input id="baseUrlProject"  name="baseUrlProject"  type="hidden"   value=""  ></td>
                  <td></td>
                </tr>
                <tr>
                  <td  ><font style="font-weight: bold;font-size: 10.6px;">.::
                    <c:choose>
                      <c:when test="${not empty _index_Motdepasse}">
                        <c:out value="${_index_Motdepasse}"></c:out>
                      </c:when>
                      <c:otherwise>Mot de passe</c:otherwise>
                    </c:choose>
                    </font></td>
                  <td colspan="2"><input id="usr_pwd"    name="usr_pwd"  required  type="password"  value=""  style="width: 150px;height: 22px;font-weight: bold;"  ></td>
                </tr>
              </table>
            </div></td>
        </tr>
        <tr>
          <td  valign="top"><div class="login"  style="width: 160px;background: none;height: 5px;"  >
              <div style="margin-top: -2%;" ><font   id="uro"          face="Verdana, Arial, Helvetica, sans-serif"   style="font-size:10px;font-weight: bold;"></font><font   color="" >-</font><font   id="theDateTime"  face="Verdana, Arial, Helvetica, sans-serif"   style="font-size: 10px;font-weight: bold;"></font></div>
            </div></td>
          <td  align="center"      ><div class="login"  style="width:270px;background: none;height: 5px;"  >
              <button class="blue"   id="btnSave"  onclick="doWelcome()"   type="submit"  style="position: relative ;top: -200%;"  ><font style="font-size: 12px;">Connexion</font></button>
            </div></td>
        </tr>
        <tr>
          <td  valign="middle"  colspan="2"  align="center"  height="25px" ><font  style="font-weight: bold;color: gray;font-family: cursive;font-size: 10px;">Version 1.0(26/03/2016) Powred By Process Engineering - All rights reserved</font></td>
        </tr>
      </table>
    </form>
  </ext:window>
  <div id="cand1"   style="position: fixed;width: 100%;height: 10000px;">
    <div id="cand"   style="position: fixed;top: 75%;text-align: center;width: 99%;display: none;"></div>
  </div>
</ext:body>
<script>
Ext.onReady(function(){

    try {		
          Ext.getCmp("intWin").show();
            //fifoA(); 
            getLangueSystem(); 
          
          if("${messageError}"!=""){
		      Ext.MessageBox.show({
		           title:'Save Changes?',
		           msg: '${messageError}',
		        
		            buttons: Ext.Msg.OK,
		         
		           animEl: 'mb4',
		           icon: Ext.MessageBox.QUESTION
		       });
           }
	     //Ext.fly('info').dom.value = Ext.MessageBox.INFO;
	     //Ext.fly('question').dom.value = Ext.MessageBox.QUESTION;
	     //Ext.fly('warning').dom.value = Ext.MessageBox.WARNING;
	     //Ext.fly('error').dom.value = Ext.MessageBox.ERROR;
	    /*function showResult(btn){
	        Ext.example.msg('Button Click', 'You clicked the {0} button', btn);
	    };*/
	    /*function showResultText(btn, text){
	        Ext.example.msg('Button Click', 'You clicked the {0} button and entered the text "{1}".', btn, text);
	    }; */ 
	    } catch(e){
	    
		}	
});
</script>




