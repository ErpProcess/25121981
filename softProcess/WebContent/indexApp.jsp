<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib tagdir="/WEB-INF/tags/ext"                     prefix="ext"    %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c"      %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"         prefix="fmt"    %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"   %>
<%@taglib uri="http://www.springframework.org/tags"      prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"     %>
<c:set var="pathRoot"    value="<%=request.getContextPath()%>" ></c:set>
<c:set var="pathRootxx"  value="<%=request.getContextPath()%>" ></c:set>
<c:set var="pathRootac"  value="<%=request.getContextPath()%>" ></c:set>
<!DOCTYPE html>
<html class="no-js">

<head>	 
	    <meta charset='UTF-8'>
	    <link rel="icon" href="<%=request.getContextPath()%>/img/process.gif" type="image/gif">
 	   
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/ext-3.0.0/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/styleIndex.css" />
		<script  src="<%=request.getContextPath() %>/ext-3.0.0/adapter/ext/ext-base.js"  type="text/javascript"   ></script>
		<script  src="<%=request.getContextPath() %>/ext-3.0.0/ext-all.js"               type="text/javascript"   ></script>
		<script src="<%=request.getContextPath() %>/jQuery/jquery1.7.js" type="text/javascript"></script>


<link href="<%=request.getContextPath() %>/jQuery/jquery.loadmask.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=request.getContextPath() %>/jQuery/jquery.loadmask.js'></script>

<script src="<%=request.getContextPath() %>/jQuery/modernizr.js"></script>
		 
<script>

	 var contexPath = "<%=request.getContextPath() %>";
	 var baseURL = window.location.protocol + '\/\/' + window.location.host + '\/';
	 var baseURL2 = window.location.protocol + '\/\/' + window.location.host;
 

function  fifoA(){
 
 	     var url = contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?HiddenAction=i$_ACT_LOAD_APPX";  
 		 
		$.ajax({ 
	      type: 'POST', 
	      url: url, 
	      data:  "valResPassage=vide", 
	      dataType: 'text',
	      success: function (data) {
	     
	      
	     
	     if(data=="fini"){
	     window.location=baseURL2+contexPath+"/next.jsp";
	     }else{
	     
	      var valueAction = data.split('�');
	      var url_re_action = valueAction[0];
		  var data_ac = valueAction[1];
	      var urluu=contexPath+url_re_action;
              
          var formInit=document.forms.actionhori;
          formInit.data_action.value=data_ac;
          formInit.HiddenAction.value="i$_ACT_INIT_SERVLET";
          formInit.action=urluu;
          formInit.submit();
	       
	      
	    }
	   
	     
	    },
	    error: function (result) {
	        
	     }
	}); 

}
 
</script>

</head>	  
<script>
Ext.onReady(function(){
    try {		
          fifoA(); 
          
	    } catch(e){
		}	
});
</script>
 
     <form  id="actionhori"  method="post"    > 
       <input  type="hidden"  name="HiddenAction"  id="HiddenAction"   value="" > 
       <input  type="hidden"  name="data_action"   id="data_action"    value="" > 
     </form>




