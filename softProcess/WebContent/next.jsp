<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c"      %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"         prefix="fmt"    %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"   %>
<%@taglib uri="http://www.springframework.org/tags"      prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"     %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Process</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/deep/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/deep/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/deep/dist/css/adminlte.min.css">
 
<script>

var contexPath = "<%=request.getContextPath() %>";
var baseURL = window.location.protocol + '\/\/' + window.location.host + '\/';


function  getLangueSystem(){
	document.getElementById("baseUrlProject").value=baseURL;
    var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_GET_LANG";  
	 
	$.ajax({ 
    type: 'POST', 
    url: url, 
    data:  "valResPassage=vide", 
    dataType: 'json',
    success: function (data) {
    /* var trHTML = '<tr>';
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
     $('#records_table').append(trHTML);*/
     $('#usr_id').focus();
    },
    error: function (result) {
        
     }
});
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
	    alert(frss);
  }else{
      document.getElementById("baseUrlProject").value=baseURL;
	  var URLO ="/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action";
	           $("#myform").find('input[name="HiddenAction"]').val("i$_ACT_LOAD_APP"); 
	           $("#myform").attr("action",contexPath+URLO);
	           $("#myform").submit(); 
             
  }

}
 

	 

 function  validAuthen(){
	    var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_GET_USER";  
	   jQuery.ajax({
	           type: "POST", 
	           url: url,
	           data:  "usrLogin="+$('#usr_login').val()+"&password="+$('#usr_pwd').val(),  
	           dataType: 'text',
	           async: false, 
	           success: function(data)
	           {
		           var  daddou=data;
		           
		           
		           if(daddou=="Erreur de mise a jour systeme"){
		        	   $('#usr_login').val('') ;
	                   $('#usr_login').val('') ;
	                   document.getElementById("btnSave").style.display="none";
		        	   alert(  "Erreur de mise Ã  jour systÃ¨me");
	                  
		        	   
		           }else if(daddou=="looooadapplication"){
	            	 
	              
	                }else if( daddou=="loginGhalit" ){
	                
	                
	                   alert(  "  check Login ! !");
	                   $('#usr_login').val('') ;
	                   $('#usr_login').focus() ;
	                   document.getElementById("nomprenom").value="";
	                   document.getElementById('usr_pwd').value="";
	                 
	                }else if(daddou=="passwordGhalit" ){
	                  alert(  daddou+" !");
	                	 
	                }else if( daddou=="lezim  login"  ){ 
	                  
	                document.getElementById('usr_login').focus() ;
	                }else{
	               
	                document.getElementById("nomprenom").value=daddou;
	                document.getElementById('usr_pwd').focus() ;
	                }
	               
	           }
	         });
	
	 


}   
 
   
</script> 
  
</head>
<body class="hold-transition login-page">
<div class="login-box"  id="intWin">
  <!-- /.login-logo -->
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
      <a href="../../index2.html" class="h1"><b>Process</b>Ges</a>
    </div>
    <div class="card-body">
      <p class="login-box-msg">Sign in to start your session</p>
 
      

      <form  id="myform" method="post"  name="myform"  autocomplete="off"  >
      <input  type="hidden"  name="HiddenAction"  id="HiddenAction"  value="doLoadApp.action"  style=" " >
        <div class="input-group mb-3">
          <input   class="form-control"  placeholder="username"
          
          autocomplete="off"    id="usr_login"  name="usr_login"   required      
                           onBlur="validAuthen()"   value=""   Type="text"  maxlength="4" 
          
          >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        
          <div class="input-group mb-3">
           <input class="form-control"  autocomplete="off"  id="nomprenom"  name="nomprenom"  type="text"   readonly="readonly" 
                     value=""     >
                    <input   id="baseUrlProject"  name="baseUrlProject"  type="hidden"   value=""  >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        
        
        
        <div class="input-group mb-3">
    
          
          
                   <input id="usr_pwd"  autocomplete="off"   class="form-control" placeholder="Password" 
                    name="usr_pwd"       type="password"  value=""  
                    >
          
          
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">
                Remember Me
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="submit" class="btn btn-primary btn-block"  onclick="doWelcome()" >Sign In</button>
          </div>
          <!-- /.col -->
        </div>
      </form>
 

      <!--  -p class="mb-1">
        <a href="forgot-password.html">I forgot my password</a>
      </p>
      <p class="mb-0">
        <a href="register.html" class="text-center">Register a new membership</a>
      </p-->
      
    </div>
    <!-- /.card-body -->
  </div>
  <!-- /.card -->
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/deep/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/deep/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/deep/dist/js/adminlte.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	getLangueSystem(); 
});

</script>
</body>
</html>
