<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pathRootac" value="<%=request.getContextPath() %>" ></c:set>

<style>
.pp{
    height: 1.2em;
    width: 1.2em;
    vertical-align: middle;
    margin: 0 0.4em 0.4em 0;
    border: 1px solid red;
    box-shadow:inset 1px 1px 0 red, 0 1px 1px  red;}
</style>

<style>
.ppd{height: 1.2em;
    width: 1.2em;
    vertical-align: middle;
    margin: 0 0.4em 0.4em 0;}
</style>

 
<script type="text/javascript">

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

function showResult(btn){
        Ext.example.msg('Button Click', 'You clicked the {0} button', btn);
};
   
Ext.onReady(function(){
    
    Ext.fly('info').dom.value = Ext.MessageBox.INFO;
    Ext.fly('question').dom.value = Ext.MessageBox.QUESTION;
    Ext.fly('warning').dom.value = Ext.MessageBox.WARNING;
    Ext.fly('error').dom.value = Ext.MessageBox.ERROR;
     
});


function doValidAction() {

     var  testeInto=true;      
     var  testecheko=false;      

     $(":input[required]").each(function (cnt, item) {                     
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
                  $(item).addClass("ppd");    
                } else{    
                  $(item).addClass("pp");  
                  testecheko=true;      
               }
           } 
          
         if ($myFormxx[0].checkValidity()) {                
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
			           fn: showResult,
			           animEl: 'mb4',
			           icon: Ext.MessageBox.WARNING
			       });
		  }else{
		       $("#ssSQZ_father").mask("Veuillez Patientez...");
			   $("#myformToServeur").find('input[name="HiddenAction"]').val("${tml.act_doValid}");
			   $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
			    
			   $("#myformToServeur").submit(); 
			  
		  }

}


function doResetAction() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("${tml.act_doReset}");	
    $('#myformToServeur').attr('target', '_self');
    $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
      $("#ssSQZ_father").mask("Veuillez Patientez...");
	$("#myformToServeur").submit();
	
}
	
function doRetourAction() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("${tml.act_doRetour}");	
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
	  $("#ssSQZ_father").mask("Veuillez Patientez...");
	$("#myformToServeur").submit();
	}
	
 
	
function doGetFirst() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_GET_FIRST");
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
	  $("#ssSQZ_father").mask("Veuillez Patientez...");
	$("#myformToServeur").submit();
}

function doGetPrevieu() { 
    $('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_GET_PREVIOUS"); 
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
	  $("#ssSQZ_father").mask("Veuillez Patientez...");
	$("#myformToServeur").submit();
} 

function doGetNext() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_GET_NEXT");
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
	  $("#ssSQZ_father").mask("Veuillez Patientez...");
	$("#myformToServeur").submit();
}

function doGetLast() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_GET_LAST");
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
	  $("#ssSQZ_father").mask("Veuillez Patientez...");
	$("#myformToServeur").submit();
} 



function doGetExcel() {
$('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_EXPORT_XLS");
$('#myformToServeur').attr('target', '_self');
$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
  $("#ssSQZ_father").mask("Veuillez Patientez...");
$("#myformToServeur").submit();
}

function doCloseWindowPDFAndDeleteFile(lefileToDelete) {
             $("#ssSQZ_father").mask("Waiting..."); 
	var url = contexPath+"${tmlx.url}?HiddenAction=i$_ACT_DELETE_FILE_PDF";  
	 	    $.ajax({
	           type: "POST", 
	           url: url,
	           data:  "pathFileToDelete="+lefileToDelete,   
	           success: function(data)
	             {
	             $("#ssSQZ_father").unmask();  
	            }
	         });  
}

function doPrintPDF(lefileToDelete) {
 $("#ssSQZ_father").mask("Waiting..."); 
	var url = contexPath+"${tmlx.url}?HiddenAction=i$_ACT_PRINT_FILE_PDF";  
	 	    $.ajax({
	           type: "POST", 
	           url: url,
	           data:  "pathFileToDelete="+lefileToDelete,   
	           success: function(data)
	             {
	            $("#ssSQZ_father").mask("Waiting...");  
	            }
	         });  
}

function doGetPdf() {
    var url = contexPath+"${tmlx.url}?HiddenAction=i$_ACT_PRINT_PDF"; 
      
	    $.ajax({
	           type: "POST", 
	           url: url,
	           data:  "username=vide", 
	           dataType: 'text',  
	           success: function(data)
	             {
		          Ext.onReady(function() {
		          
		          //alert(data);
		           var doSwitch = function(btn) {
    
       doPrintPDF(data);
    }
		          
								new Ext.Window({
									  width        : 1200,
									  height       : 500,
									  maximizable: true,
									  minimizable :true,
									  autoScroll:true,
									  maxHeight: 500,
									  modal   : true,
									   buttons : [
            {
                text    : 'Print',
                handler : doSwitch
            }
        ],
									  items: {
									            xtype: 'component',
									            autoEl: {
									                tag: 'iframe',
									                style: 'height: 100%; width: 100%; border: none',
									                src: '${pathRootac}/temp/'+data
									            }
									        },
									  title        : 'PDF-Imprimer',
									  border       : true,
									  closable: true,
									  listeners: {close:function(){ doCloseWindowPDFAndDeleteFile(data);} }
								}).show();

                           });
	               
	               }
	         });         
      
       }


 

 





function ControlDisplayTableE(){
    var  testeInto=true;      
    var  testecheko=false;      
      $(":input[required]").each(function (cnt, item) {                     
        var $myFormxx = $("#myformToServeur");
          if(!$(item).val()) {  $(item).css('border-color', 'red'); testeInto=false; }else{ $(item).css('border-color', ''); }
          if($(item).is("input[type=radio]")) {  $(item).addClass("ppd"); 
          if($(item).is(":checked"))  { $(item).addClass("ppd");    } else{    $(item).addClass("pp");   testecheko=true;      }} 
          if ($myFormxx[0].checkValidity()) { testeInto=true;} 
        });
        if(!testeInto){
      var frss="   Vous devez entrer le(s) champs vide(s)";
     Ext.MessageBox.show({
           title:frss,
           msg: '   Certains champs sont obligatoires pour que le formulaire soit pris en compte ',
           buttons: Ext.Msg.OK,
           fn: showResult,
           animEl: 'mb4',
           icon: Ext.MessageBox.WARNING
       });
  }else{
  document.getElementById("${nameGrid}").style.display="block";
  LoadDataFromServer(maptableAjax);
  }
}


function getDetailRowFromServer(indexRowselected) {
        $("#myformToServeur").attr("action",contexPath+"${tmlx.url}?HiddenAction=i$_ACT_SELECT_ROW&theIdRow="+indexRowselected);
        $('#myformToServeur').attr('target', '_self');
          $("#ssSQZ_father").mask("Veuillez Patientez...");
	    $("#myformToServeur").submit();
}

function  doResetAjaxData(){

    document.getElementById("${nameGrid}").style.display="none";
    Ext.getCmp('btValidx').enable();
	if (oTable != undefined) {
	oTable.iDisplayStart = -2;
	oTable.fnClearTable(); 
	oTable.fnDestroy(); 
	oTable=undefined;
	} 
}



	function doGetTrace() {
Ext.onReady(function() {
new Ext.Window({
  width        : 1200,
  height       : 500,
  maximizable: true,
  minimizable :true,
  autoScroll:true,
  maxHeight: 500,
  modal   : true,
  closeAction: 'hide',
  items: {
            xtype: 'component',
            autoEl: {
                tag: 'iframe',
                style: 'height: 100%; width: 100%; border: none',
                src: '${pathRootac}/WindowSpoor/FilterWindowSpoor.jsp'
            }
        },
  title        : 'Trace - &nbsp;${bs.pack_libelle}&nbsp;.&nbsp;${bs.mod_libelle}&nbsp;.&nbsp;${bs.sousmod_libelle}',
  border       : true,
   closable: true,
   listeners: {close:function(){ doLoadTraceData(data);} }
}).show();

});

 


} 





function doGetHelp() {
Ext.onReady(function() {
new Ext.Window({
  width        : 1200,
  height       : 500,
  maximizable: true,
  minimizable :true,
  autoScroll:true,
  maxHeight: 500,
  modal   : true,
  closeAction: 'hide',
  items: {
            xtype: 'component',
            autoEl: {
                tag: 'iframe',
                style: 'height: 100%; width: 100%; border: none',
                src: '${pathRootac}/Aceuil/user.pdf'
            }
        },
  title        : 'help',
  border       : true,
   closable: true,
   listeners: {close:function(){ doLoadTraceData(data);} }
}).show();

});

 


} 



 

function bonjour() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_LOAD_Mod_SM");
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?sousPackId=${bs.spack_id}");
	$("#myformToServeur").submit();
}
  
</script>
 
 
 

   <!-- 
        <div class="x-panel-header"  id="pippa" 
         style="position:  fixed;top: 11.5%;text-align: center;left:38%;z-index: 1;color:white;width: 180px;border: none;
          background: #636d76;display:${disMenuX.toolbarBttn};"  > ${bs.fct_libelle} - ${bs.sousmod_libelle}      
          </div> -->
 
 
 
  <ext:body> 	   
         <ext:panel     renderTo="toolbarBttn"  title="${bs.pack_libelle}.::. ${bs.mod_libelle} > ${bs.sousmod_libelle} "  border="true"   collapsible="false"    >
   	 
          <ext:toolbar   id="Wqs"          >   
			
			 <c:if test="${fn:length(sousModulebean.listf)> 0}">  
		           <c:forEach var="fcBean" varStatus="inFct" items="${sousModulebean.listf}">
		           
		           <ext:toolbar.button   
		              icon="${pathRootac}${fcBean.fct_icon}" 
		              cls="x-btn-text-icon"  text="${fcBean.fct_libelle}"
		             
		              onClick="getInitActions('${fcBean.fct_id}','${fcBean.fct_libelle}','${fcBean.view_smfct_action}',
				      	                                          '${sousModulebean.sousmod_id}','${sousModulebean.sousmod_libelle}',
				      	                                          '${indxSm.index}','${indxM.index}','${sousModulebean.sousmod_action}',this)" 
				      	                         
		               />  <ext:toolbar.separator/>
		           
		           
		           </c:forEach>
                 
		           </c:if>
		           
		            <div 
		            style="background-color:#616975;padding-left: 3%;font-weight: bold;color:#fff;padding-top: 0.5%;top: -50px;padding-bottom: 5px;font-size: 15px;font-family: arial;"  
		    > ${bs.fct_libelle} - ${bs.sousmod_libelle}  </div>
		    </ext:toolbar>
		  
		    
		      <ext:toolbar   id="WqsSED"      style="background-color:#f8fcfc;float:right;"    >            
		            
          
         
 			     
<ext:toolbar.button      cls="x-btn-text-icon"    text="commit"    icon="${pathRootac}/css/icons/fam/icon_commit.png"    disabled="${tml.commit}"         onClick="${tml.fctdoCommit}"   style="margin-left:400"  />
 <ext:toolbar.separator/>  
<ext:toolbar.button      cls="x-btn-text-icon"    text="r.back"    icon="${pathRootac}/css/icons/fam/icon_rollback.png"  disabled="${tml.rollback}"       onClick="${tml.fctdoRollback}"  />  				        
				     
		              
		            
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-first.gif"   cls="x-btn-text-icon"    disabled="${tml.btfirst}"    style="margin-left:10"  onClick="${tml.fctdoFisrt}"  />  
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-prev.gif"    cls="x-btn-text-icon"    disabled="${tml.btPrevious}"      onClick="${tml.fctdoPrevieus}"   />

  
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-next.gif"    cls="x-btn-text-icon"    disabled="${tml.btNext}"    onClick="${tml.fctdoNext}"   />  
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-last.gif"    cls="x-btn-text-icon"    disabled="${tml.btLast}"    onClick="${tml.fctdoLast}"   />  
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/Hint.gif"         cls="x-btn-text-icon"    disabled="${tml.btTrace}"   onClick="doGetHelp()"    style="margin-left:10"    />  
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/Notes.gif"        cls="x-btn-text-icon"    disabled="${tml.btAide}"    onClick="doGetTrace()"  />  
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/pdf.png"          cls="x-btn-text-icon"    disabled="${tml.btPdf}"     onClick="doGetPdf()"     style="margin-left:20" id="btPrintPdfx"  />   
<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/Excel.gif"        cls="x-btn-text-icon"    disabled="${tml.btExcel}"   onClick="doGetExcel()"                          id="btExportXlsx"    /> 
  
  
 
		 
        
<ext:toolbar.button   icon="${pathRootac}/css/icons/fam/${tml.iconAction}"        cls="x-btn-text-icon"  
  
   text="${tml.libelleAction}"   type="submit"    id="btValidx"    
   onClick="${tml.fctdoValid}"  disabled="${tml.btValid}"   style="margin-left:30"       > </ext:toolbar.button>
   
    
				         
<ext:toolbar.separator/>
<ext:toolbar.button  text="Annuler"   icon="${pathRootac}/css/icons/fam/gtk-cancel.png"        cls="x-btn-text-icon"     type="button"    id="btReset"    disabled="${tml.btReset}"   onClick="${tml.fctdoReset}"    />   
<ext:toolbar.separator/>
<ext:toolbar.button  text="Retour"   icon="${pathRootac}/css/icons/fam/Redo.png"        cls="x-btn-text-icon"      type="button"     id="btRetour"  disabled="${tml.btRetour}"  onClick="${tml.fctdoRetour}" /> 
 
</ext:toolbar>
 
</ext:panel>
</ext:body>


 
 


 

 

 

 
 
   
  


