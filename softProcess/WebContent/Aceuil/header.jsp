<%@include file="/Aceuil/taglib_includes.jsp" %>
<!DOCTYPE html>
<html class="no-js">
<head>
<link rel="icon" href="/images/animated_favicon1.gif" type="image/gif">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>${bs.fct_libelle} - ${bs.sousmod_libelle}</title>
</head>
<body  id="ssSQZ_father"  >
 
<ext:body     >
  <ext:viewport  layout="border"  hideBorders="true" id="ggsgIPg"  bufferResize="true"  cls="rr"  >
    <ext:panel region="south"       margins="0 0 0 3"     border="false"   > </ext:panel>
    <ext:panel region="east"        margins="0 0 0 0"     border="false"  width="0"      collapsible="false"  > </ext:panel>
    <ext:panel region="north"       margins="0 3 0 3"     border="true"   width="200"   height="70"   split="true"  >
<%--       <jsp:include page="MenusHorizentale.jsp" ></jsp:include> --%>
    </ext:panel>
    <ext:panel region="west"      margins="0 3 0 3"  autoScroll="yes"   border="true"    width="175"   collapsible="true"  
	  title=" ProcessERP Explorer"   id="hyuE"       split="true"   collapseMode="true"   hideCollapseTool="true"
	  plugins="[Ext.ux.PanelCollapsedTitle]"
	   >
      <div  class="x-toolbar" style="height: 700px;"  >
<%--         <jsp:include page="MenuVerticale.jsp"  ></jsp:include> --%>
      </div>
    </ext:panel>
    <ext:panel  region="center" margins="4 3 3 0"  border="true"   autoScroll="false"   bodyStyle="background:#EEEFE7;"   id="PanelSwing"     >
       <form  id="myformToServeur"  method="post"  name="myformToServeur"     >
	        <div id="toolbarBttn"      style="margin: 5px 10px 0px 10px;position: static;display:${disMenuX.toolbarBttn};" >
	           <input  type="hidden"  id="HiddenAction"  name="HiddenAction"  value=""       >
	        </div> 
	           <c:set var="styloAc" value="overflow: auto; max-height: 420px;border: 1px solid #99bbe8;margin: 0px 10px 10px 10px;background-color:#f8fcfc;"  />
               <c:if test="${disMenuX.toolbarBttn=='none'}">
               <c:set var="styloAc" value=""  />
               </c:if>
	        <div id="ThePageJsp"  style="${styloAc}" > 
	        <jsp:include page="MenuDesActionsExtJS.jsp"  />
	        <span id="errmsg"  style="color: red;font-weight: bold;margin-left: 45%;"></span> 
	      </div> 
       </form>   
    </ext:panel>
  </ext:viewport>
</ext:body>
<script type="text/javascript">
Ext.onReady(function(){
var primecontact = Ext.getCmp('hyuE');
primecontact.collapse();
		});
</script>
</body>
</html>




<!--   <font style="font-size: 8px;margin-bottom: 50px;" >Powred by eXpertSoft© 2014</font>  -->
