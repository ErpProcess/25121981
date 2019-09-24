<%@taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"     %>
<!DOCTYPE html>
<html   lang="en-US">
<head>
<title>${bs.fct_libelle} - ${bs.sousmod_libelle}</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords"    content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link   rel="icon"   href="<%=request.getContextPath()%>/img/process.gif"   type="image/gif"  >

<link    href="<%=request.getContextPath()%>/ext-3.0.0/resources/css/ext-all.css"     rel="stylesheet"       type="text/css"     media="all"    />

<script  src="<%=request.getContextPath()%>/ext-3.0.0/adapter/ext/ext-base.js"        type="text/javascript" ></script>
<script  src="<%=request.getContextPath()%>/ext-3.0.0/ext-all.js"                     type="text/javascript" ></script>
<script  src="<%=request.getContextPath()%>/ext-3.0.0/Ext.ux.PanelCollapsedTitle.js"  type="text/javascript" ></script>


<script  src="<%=request.getContextPath() %>/jQuery/jquery1.7.js"                          type="text/javascript"></script>
<script  src="<%=request.getContextPath() %>/jQuery/dataTable/jquery.dataTables.min1.7.js" type="text/javascript"></script>
<script  src="<%=request.getContextPath() %>/jQuery/jquery-1.5.2.min.js"                type="text/javascript"  ></script>
<script  src="<%=request.getContextPath() %>/jQuery/jquery.form.js"                     type="text/javascript"  ></script>	
<script  src="<%=request.getContextPath() %>/jQuery/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
<script  src="<%=request.getContextPath() %>/jQuery/jquery-ui-1.8.11.custom.min.js"     type="text/javascript"></script>
<script  src="<%=request.getContextPath() %>/jQuery/jquery-1.8.2.min.js"                type="text/javascript"  ></script> 



<script src="<%=request.getContextPath() %>/jQuery/dataTable/jquery.dataTables.js"     type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/jQuery/dataTable/ColReorderWithResize.js"  type="text/javascript"></script>
 
<script src="<%=request.getContextPath() %>/jQuery/dataTable/ColumnFilterWidgets.js"           type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/jQuery/dataTable/jquery.dataTables.rowGrouping.js" type="text/javascript"></script>


<script src="<%=request.getContextPath()%>/jQuery/dataTable/jquery.jeditable.js"           type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jQuery/dataTable/jquery.dataTables.editable.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jQuery/dataTable/jquery.jeditable.checkbox.js"  type="text/javascript"  ></script>

 
<!-- 
<script src="<%=request.getContextPath()%>/jQuery/jquery-dateFormat.js"  type="text/javascript"  ></script>
<script src="<%=request.getContextPath()%>/jQuery/jquery-dateFormat.min.js"  type="text/javascript"  ></script>
 -->
<script src="<%=request.getContextPath()%>/jQuery/dateFormat.js"  type="text/javascript"  ></script>
<script src="<%=request.getContextPath()%>/jQuery/dateFormat.min.js"  type="text/javascript"  ></script>
 

 
<style  >
input[readonly],select[readonly]{
    background: #eee;
    cursor:no-drop;
}

select[readonly] option{
    display:none;
}


</style>
<script>
	 var contexPath = "<%=request.getContextPath() %>";
	 var baseURL = window.location.protocol + '\/\/' + window.location.host + '\/';
	 var UrlServerListeCorrlee="${urlloadListeAuto}";
	 var urls_Generic_def     ="${baseAjaxUrl}/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action";
	 
	 var UrlServerListeComboSelect="${baseAjaxUrl}/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action?HiddenAction=i$_ACT_LOAD_SELECT";
	 
	 var urlos="${urlloadDataTableAjax}" ;
	 var urlSmodule="${tmlx.url}";
	 var baseAjaxUrl="${baseAjaxUrl}";
	 var gaiSelected =[];
     var oTable;
     var MyMessageBoxo="${messageError}";
     var custumMessageBoxo="${custmessageError}";
     
      
     
     var urlsAjaxSource_def ="${baseAjaxUrl}/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action?HiddenAction=i$_ACT_LOAD_EDITABLE_TABLE_AJAX";
     var urlsUpdateURL_def  ="${baseAjaxUrl}/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action?HiddenAction=i$_ACT_UPDATE_EDITABLE";
     var urlsDeleteURL_def  ="${baseAjaxUrl}/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action?HiddenAction=i$_ACT_DELETE_ROW_EDITABLE_TABLE_AJAX";
     var urlsAddURL_def     ="${baseAjaxUrl}/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action?HiddenAction=i$_ACT_LOAD_EDITABLE_TABLE_AJAX";
     
     
     /*****************************************************Config Editable Data Grid JQuery****************************************************/
     var  config_header_foot_tableJQuey ='<"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lf<"toolbar_es">r>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>';
     var  contenu_toolbarJQuey          ='<b><input  id="b1"  type="button"  value="+"    onclick="ADD()"   style="font-size: 16px;width: 40px;text-align:center;" >&nbsp;&nbsp;&nbsp;<input  type="button"  value="-"       onclick="Delete_ROW()"                  style="font-size: 16px;width: 40px;"  ></b>';
     var  nbr_ligneJQuey=7;
     var  height_tabbJQuey=240;
     var  width_tabbJQuey="100%";
     var  afficher_mess_emptyJQuey=false;
     var  height_dTble=350;
     var  windowLongeur=900;//$(window).height();
     
     var  tyPePaRam="false";
     
     /******************************************************************************************************************************************/
	 var dataGridConfig_width="100%";
	 var doubleclickGrid=true;
	  
      /******************************************************************************************************************************************/
      
     var  path_Rootac="${pathRootac}";
     var  tml_disabled="${tml.disabled}";
     var  tml_idReadonly="${tml.idReadonly}";
     var  tml_readonly="${tml.readonly}";
     var  bs_fct_id="${bs.fct_id}"; 
     var  cham_videRouge="${_chamvideRouge}"; 
     var  cham_obligatoire="${_chamobligatoire}"; 
     var  bs_pack_libelle="${bs.pack_libelle}"; 
     var  bs_mod_libelle="${bs.mod_libelle}"; 
     var  bs_sousmod_libelle="${bs.sousmod_libelle}";
     var  bs_patternDecimalFormat="${bs.patternDecimalFormat}";
     
     
 function addPourcentage(valeurPer){
 
  if( valeurPer==undefined || valeurPer==null ||   valeurPer==""  ||   valeurPer==NaN  )return "";
  var vel=parseFloat(valeurPer);
  if(vel==0   ||   vel==NaN )  return "";  else  return   vel+"%";
 }
     
</script>
<script src="<%=request.getContextPath() %>/jQuery/process_script_alert.js"          type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/jQuery/process_script_control_saisie.js"          type="text/javascript"     ></script>
<script src="<%=request.getContextPath() %>/jQuery/process_script_datepicker.js"            type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/jQuery/process_script_number_boolean.js"        type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/jQuery/process_script_methode_ajax.js"          type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/jQuery/process_script_ExtWindow.js"          type="text/javascript" ></script>


<script src="<%=request.getContextPath() %>/jQuery/ExpertSoft.js"             type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/jQuery/Autocomplet.js"            type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/jQuery/jquery-ui.min.js"          type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/jQuery/JsFilterDataTableAjax.js"  type="text/javascript"></script>

<link   href="<%=request.getContextPath() %>/jQuery/jquery.loadmask.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/jQuery/jquery.loadmask.js"                    type='text/javascript' ></script>
<script src="<%=request.getContextPath() %>/jQuery/modernizr.js"></script>
<script src="<%=request.getContextPath() %>/jQuery/jquery.mask.min.js"            type="text/javascript"     ></script>
 
<script type="text/javascript">
 $(window).load(function() {
   $(".se-pre-con").fadeOut("slow");
});

var numrReserve=null;

function getNum(Gnumo){
	numrReserve=Gnumo;
}
function doValidAction() {
	numrReserve=null;
    if(!teste_required()) return ;
    if (typeof control_de_liste !== 'undefined' && typeof control_de_liste === 'function'){
		  var retour= control_de_liste();
		  
		  if(retour!="" &&  !retour.startsWith("©") )  {  alertExt("Erreur",retour,"4"); return;    }
		  
		  
		  if(retour!="" &&  retour.startsWith("©") )  {
			  var tabOfNumero = retour.split("©");
	 
			  var maselect='<select onchange="getNum(this.value);" >';
			  for (var z = 0; z < tabOfNumero.length ; z++) {
				 if(tabOfNumero[z]!==null &&  tabOfNumero[z]!==undefined    &&   tabOfNumero[z]!== ""  ){
					 maselect+= '<option  value="'+tabOfNumero[z]+'">'+tabOfNumero[z]+'</option>';
					 if(numrReserve==null)numrReserve=tabOfNumero[z];
			       }
			   }
			  maselect+='</select>';
			  Ext.MessageBox.show({
		      title:'Enregistrement',
		      msg: ' Choisir un Numéro de vente déja Supprimé : '+maselect,
		      buttons: {ok:'Ancien Numéro',no:'Nouveau Numéro'}  ,
// 		      fn:  getActionbtnuik(buttonId,numrf) ,
		      //fn: showResultText.createDelegate(this, tabOfNumero, true),
		      fn: function (btn){
		    	  if (btn == 'ok') shf(numrReserve); else shf(null);
		  	   },
		      animateTarget: 'mb4',
		      icon: Ext.MessageBox.QUESTION
		 });
			  return;  
			  }
    }  
     $("#ssSQZ_father").mask("Veuillez Patientez...");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val("${tml.act_doValid}");
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 
     
}

 
function shf(fdfff){
	 $("#ssSQZ_father").mask("Veuillez Patientez...");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val("${tml.act_doValid}");
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}"+"?numios="+fdfff);
     $("#myformToServeur").submit(); 
}



function doCommitAction() {
    
     $("#ssSQZ_father").mask("Veuillez Patientez...");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val("${tml.act_doCommit}");
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 
     
}
 
 

function doCommitAction() {

    if(!teste_required()) return ;
    if (typeof  control_de_liste !== 'undefined' && typeof  control_de_liste === 'function') {
		  var retour_Teste_List    = control_de_liste();
		   if(retour_Teste_List!="")  {  alertExt("Erreur",retour_Teste_List,"4");  return;    }
    }  
    
     $("#ssSQZ_father").mask(" Veuillez Patientez... ");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val("${tml.act_doCommit}");
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 
     
     
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
$('#myformToServeur').find('input[name="HiddenAction"]').val("${tml.act_doExcel}");
$('#myformToServeur').attr('target', '_self');
$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
$("#myformToServeur").submit();
}

function doCloseWindowPDFAndDeleteFile(lefileToDelete) {
             $("#ssSQZ_father").mask("Veuillez Patientez..."); 
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
	            $("#ssSQZ_father").mask("Veuillez Patientez...");  
	            }
	         });  
}


var win_genericPdfProcess;

function genericPdfProcess(url_geric_process){

	$.ajax({
	           type: "POST", 
	           url: url_geric_process,
	           data:  "username=vide", 
	           dataType: 'text',  
	           success: function(data){
	            
		             
					 
						  //var doSwitch = function(btn) {doPrintPDF(data);}
						  win_genericPdfProcess =  new Ext.Window({
									  width        : 1200,
									  height       : 500,
									  maximizable: true,
									  minimizable :true,
									  autoScroll:false,
									  closeAction: 'hide',
									  maxHeight: 500,
									  modal   : true,
									 // buttons : [{text: 'Print',handler : doSwitch}],
									  items: {
									            xtype: 'component',
									            autoEl: {
									                tag: 'iframe',
									                style: 'height: 100%; width: 100%; border: none',
									                src:   '${pathRootac}/temp/'+data
									            }
									        },
									  title        : 'PDF-Imprimer',
									  border       : true,
									  closable: true,
									  listeners: {close:function(){ doCloseWindowPDFAndDeleteFile(data);} }
								}).show();
						 
					  

                  
	               
	        }
	}); 

}


function doGetPdf() {
    var url = contexPath+"${tmlx.url}?HiddenAction=${tml.act_doPdf}";
	genericPdfProcess(url);     
}



function ControlDisplayTableE(){

    if(!teste_required()) return ;
    if (typeof control_de_liste !== 'undefined' && typeof control_de_liste === 'function'){
		  var retour_Teste_List  = control_de_liste();
		  if(retour_Teste_List!="")  {  alertExt("Erreur",retour_Teste_List,"4"); return;    }
    }  
  document.getElementById("${nameGrid}").style.display="block";
  $("#PanelSwing").mask("Veuillez Patientez...");
  LoadDataFromServer(maptableAjax);
}


function getDetailRowFromServer(indexRowselected) {
  
        $("#myformToServeur").attr("action",contexPath+"${tmlx.url}?HiddenAction=i$_ACT_SELECT_ROW&theIdRow="+indexRowselected);
        $('#myformToServeur').attr('target', '_self');
        $("#ssSQZ_father").mask("Veuillez Patientez...");
	    $("#myformToServeur").submit();
}


  function effacerWX () {
  $(':input','#myformToServeur')
   .not(':button, :submit, :reset, :hidden')
   .val('')
   .removeAttr('checked')
   .removeAttr('selected');
    
}



function  doResetAjaxData(){

     $('#myformToServeur').trigger("reset");
     effacerWX() ;
   



     $('#${nameGrid}').css('display','none');
     $('#RET_GRID').css('display','none');

     
    
    Ext.getCmp('btValidx').enable();
    Ext.getCmp('btPrintPdfx').disable(); 
	Ext.getCmp('btExportXlsx').disable();
	if (oTable != undefined) {
		oTable.fnClearTable();
		oTable.fnDraw();
		oTable.fnDestroy();
	} 
}


 

function bonjour() {
    $('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_LOAD_Mod_SM");
    $('#myformToServeur').attr('target', '_self');
	$("#myformToServeur").attr("action",contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action?sousPackId=${bs.spack_id}");
	$("#myformToServeur").submit();
}
   
function getInitActions(fct_id , fct_libelle,view_smfct_action , sousmod_id ,sousmod_libelle ,indexLisSouMod,ixlismodul,thePathServlet,jkkkkd){
       
 
             var url_request_action="";
             
            
            if(view_smfct_action=="" ||  view_smfct_action==null  ||  view_smfct_action.length<12  ){
               url_request_action=thePathServlet;
            }else{
               url_request_action=view_smfct_action;
            }
            
            
       
            var data =fct_id+"¤"+fct_libelle+"¤"+url_request_action+"¤"+sousmod_id+"¤"+sousmod_libelle+"¤"+indexLisSouMod+"¤"+ixlismodul;
         
                          
            var urluu=contexPath+url_request_action;
              
            var formInit=document.forms.actionhori;
           
        
            formInit.data_action.value=data;
            formInit.HiddenAction.value="i$_ACT_INIT_SERVLET";
            formInit.action=urluu;
            $("#ssSQZ_father").mask("Veuillez Patientez...");
            formInit.submit();
       }   
  
       
       
       function doInitUpateCompte(){  
        var redirectrr = contexPath+'/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/doInitCompte.action';
        var foorr=document.forms.actionhori;
        foorr.action=redirectrr;
        doActionLoad("actionhori");
        foorr.submit();
       }
       
       
       

function   closedApp(){
		 var redirectrr = contexPath+'/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action';
		 var  foorr=document.forms.actionhori;
		      foorr.HiddenAction.value="i$_ACT_EXIST_APP";
              foorr.action=redirectrr;
    
        Ext.onReady(function(){
			Ext.Msg.show({
				title:'Question',
				msg: 'Etes-vous sur  de quitter ?',
				buttons: Ext.Msg.YESNO,
				fn: function (btn){
					if (btn == 'yes'){
						 foorr.submit();
						}
					if (btn == 'no')
					var oo="";
				},
				icon: Ext.MessageBox.QUESTION
			});
       }) ;
		    }   
    
    function getModules(sPackId,pack){
	        var data= pack+"&="+sPackId;   
	        
	        var foorr=document.forms.myhrefaction;
	        foorr.data_for_module.value=data;
	        var url=contexPath+"/ERP/eXpertSoft/wfsi/Administration/GestionAuthentification/root.action";
	        foorr.action=url;
	        $("#ssSQZ_father").mask("Veuillez Patientez...");
            foorr.submit();
       }
 

	   var FFF;
function doGetTrace() {
	
	 

	  var TTTTT_PICASSSSO = new Ext.Window({
		   width        : 1200,
        autoShow: true, 
        height       : 500,
        maximizable: true,
        minimizable :true,
        autoScroll:true,
        maxHeight: 500,
        border       : true,
        closeAction: 'hide',
        modal   : true,
        iconCls: 'information', 
        layout: 'fit',
		autoLoad : {  
  				 url : path_Rootac+'/WindowSpoor/FilterWindowSpoor.jsp',  
   				 scripts: true  
  		 },
		  title:'Trace - &nbsp;'+bs_pack_libelle+'&nbsp;.&nbsp;'+bs_mod_libelle+'&nbsp;.&nbsp;'+bs_sousmod_libelle,  
		  border       : true,
		  closable: true,
		  listeners     :{
		
                 'close':function(win){
                         alert('bye');
                  },
                 'hide':function(win){
               
                  win.close();
                  TTTTT_PICASSSSO.destroy();
                  
                  }
          }
		}).show();
	 
  
    
}




function migaXWXXWW(dssss) {

   var is_list="ListSerie_article_disponible.jsp";
   
   if( "${bs.fct_id}"=="2"    )
   is_list="ListSerie_article_disponible_cons.jsp";
   
   var sdffdffg = {id_articlo: dssss, name2: 'value2'};
   ae=fnAjax_WithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_SERIE','json',false,sdffdffg);
      var FFF;
	  var TTTTT_PICASSSSO = new Ext.Window({
		width        :1200,
        autoShow     :true, 
        height       :300,
        maximizable  :true,
        minimizable  :true,
        autoScroll   :true,
        border       :true,
        closeAction  :'hide',
        modal        :true,
        session      :true,
        iconCls      :'information', 
        layout       :'fit',
        closable     : true,
        title:"&nbsp;${bs.pack_libelle}&nbsp;.&nbsp;${bs.mod_libelle}&nbsp;.&nbsp;${bs.sousmod_libelle}", 
		autoLoad     :{  
  				 url : is_list, 
   				 scripts: true  
   				      },
		listeners     :{
		
                 'close':function(win){
                         alert('bye');
                  },
                 'hide':function(win){
                  TTTTT_PICASSSSO.destroy();
                  TTTTT_PICASSSSO=FFF;
                  doExcuteFnAfterGridX();
                  }
          }
		}).show();						

}




</script>
<style  >

.x-btn-text-icon .x-btn-icon-small-left .x-btn-text{
    background-position: 0 center;
	background-repeat: no-repeat;
    padding-left:18px;
    height:16px;
}
.x-btn-text-iconXX  {
    background-position: 0 center;
	background-repeat: no-repeat;
    padding-left:18px;
    height:30px;
    font-size: 20px;
    text-align: right;
    font-weight: bold;
}

</style>

 
		
</head>
<body  id="ssSQZ_father"     >
   <div class="se-pre-con"></div>  
<ext:body     >
<ext:viewport  layout="border"    id="ggsgIPg"  bufferResize="true"  cls="rr"       monitorResize="true"  autoDestroy="false" >
    <ext:panel region="south"       margins="0 0 0 3"     border="false"   > </ext:panel>
    <ext:panel region="east"        margins="0 0 0 0"     border="false"  width="0"      collapsible="true"  > </ext:panel>
<!-- ******************************************************* Panel nord***************************************************************************-->
    <ext:panel region="north"       margins="0 3 3 3"     border="true"   width="200"   height="70"   split="false"      >
     <form  id="actionhori"   method="post"    > 
       <input  type="hidden"  name="HiddenAction"  id="HiddenAction"   value="" > 
       <input  type="hidden"  name="data_action"   id="data_action"    value="" > 
      
	   <table width="100%"    border="0"  cellspacing="0" cellpadding="0"  class="tabbed_area99"     > 
			
			  
			<tr>
				<td  colspan="3" >
				<div  id="topy" class="x-panel-header"  style="height: 17px;width: 98.8%;position: fixed;z-index: 2222;margin-top: -5px;"   >
				  <a href="${pathRoot}/saas/index.jsp" >Process</a>  &nbsp;©&nbsp;&nbsp;
				  <font color="black"  style="font-size: 11px;"> ${bs.pack_libelle}&nbsp; .::.&nbsp; ${bs.mod_libelle} > ${bs.sousmod_libelle}</font> 
	          
		       
				
				<div style="float: right;color: black;">
				${bs.soc_lib}&nbsp;-&nbsp;${bs.etab_lib}
				&nbsp;-&nbsp;
				 <font   id="theDateTime"          face="Verdana, Arial, Helvetica, sans-serif"   style="color: black;font-size: 11px;"></font>
		        <font   color="black"  >-</font> 
		        <font   id="uro"  face="Verdana, Arial, Helvetica, sans-serif"   style="font-size: 11px;color: black;"></font>
		        
				</div></div>
				</td>
			 </tr> 
			   
		    <tr>
	            <td  width="14%"  class="tabbed_area99"    > 
	             <div style="position:absolute;top: 30%;width: 14%;left: 0.5%;"  >
	               <font style="color: white;margin-left: 2px;font-size: 13px;margin-bottom: -20px;">
	               <img src="${pathRoot}/css/icons/fam/user.gif"  >${utilBean.usr_pre}&nbsp;&nbsp;${utilBean.usr_nom}</font>
	             </div>
	           </td>
		       <td   rowspan="2"   width="82.5%"  >
		             <c:set    var="contt"  value="tab"   /> 
		             <div id="tabbed_box_1" class="tabbed_box"    style="position:absolute;top: 30%;left: 14%;width: 100%;"     > 
		             <div class="tabbed_area"     >
		     
		             <ul class="tabs">
		               <c:forEach var="moduleBean"  varStatus="outers" items="${listModule}"    >
			               <c:if  test="${outers.index==bs.indexModule}"  >
			                 <c:set    var="lemm"  value="tab active"   ></c:set>
			               </c:if>
			               <c:if    test="${outers.index!=bs.indexModule}"  >
			                 <c:set    var="lemm"  value="tab"   ></c:set>
			               </c:if>
			               <font style="font-size: 1px;"><c:out value="${outers.index}"/></font> 
			               <li><a href="#" title="content_${outers.index}"  class="${lemm}" > 
			                <c:out value="${moduleBean.mod_libelle}"/></a>
			               </li>
		              </c:forEach>
		            </ul>
		    	    <c:forEach var="moduleBean2"  varStatus="indxM" items="${listModule}"    >
		    	    <ext:body> 	
		              <ext:toolbar           id="tent_${indxM.index}"      renderTo="content_${indxM.index}"     >
		    	       <c:forEach  var="sousModulebean"   varStatus="indxSm" items="${moduleBean2.listsmodule}" >
		    	           <ext:toolbar.separator  />
						   <ext:toolbar.button  text="${sousModulebean.sousmod_libelle}"  cls="x-btn-text-icon" icon="${pathRoot}/css/icons/fam/Module.png" >
						        <c:if test="${fn:length(sousModulebean.listf) > 0}">
					              <ext:menu >
						      	     <c:forEach var="fcBean" varStatus="soduter" items="${sousModulebean.listf}">
						      	        <ext:menu.item     
		                                     text="${fcBean.fct_libelle}"  
		                                     icon="${pathRoot}${fcBean.fct_icon}" 
						      	             onClick="getInitActions('${fcBean.fct_id}','${fcBean.fct_libelle}','${fcBean.view_smfct_action}',
						      	                                          '${sousModulebean.sousmod_id}','${sousModulebean.sousmod_libelle}',
						      	                                          '${indxSm.index}','${indxM.index}','${sousModulebean.sousmod_action}',this)"  >
						      	         </ext:menu.item>
						               </c:forEach>
					               </ext:menu>
					            </c:if>
						   </ext:toolbar.button>
						</c:forEach>
					    <ext:toolbar.separator  /> 
					  </ext:toolbar>
		            </ext:body>
		              <c:if  test="${indxM.index==bs.indexModule}"  >
		                <div id="content_${indxM.index}" class="content"     style="display: block;"   ></div>
		                <!-- id="${fcBean.fct_id}£${fcBean.fct_libelle}£${sousModulebean.sousmod_id}£${sousModulebean.sousmod_libelle}£${indxSm.index}£${indxM.index}£${sousModulebean.sousmod_action}" -->
		              </c:if>
		              <c:if    test="${indxM.index!=bs.indexModule}"  >
		                 <div id="content_${indxM.index}" class="content"  style="display: none;"  ></div>
		             </c:if>
		        </c:forEach>
		              <!--    < % System.out.println( "dddddd= "+pageContext.findAttribute("bs.indexModule") ); %>  -->  
		              <c:if test="${fn:length(listModule) > 0}">
		              <div class="x-toolbar" style="height: 22px;display:none;"  ></div>
		              </c:if>
		             
		              <c:if    test="${fn:length(listModule)== 0}"  >
		                 <div class="x-toolbar" style="height: 22px;position:absolute;margin-top: 2.0%;width: 100%;"  > </div>
		              </c:if>
		         </div>
		     </div>  
		   </td>
		   <td    class="tabbed_area99"  align="center"     height="42px;"  valign="top"  >   
		    
		   </td>
	 </tr>
     <tr>
         <td > 
             <ext:body> 	 
                <ext:toolbar   id="sqhjS" renderTo="sdcvfrdx"    >   
				    <ext:toolbar.button  id="ddZopgiZzze" text="${bs.prf_libelle}"    />
			    </ext:toolbar>
			 </ext:body>
			 <div  style="position:absolute;top: 63%;width: 14%;" id="sdcvfrdx"   ></div>
          </td>
          <td >
              <ext:body> 	 
                  <ext:toolbar   id="oiuut" renderTo="wwAQ"       >   
				      <ext:toolbar.separator  />
				       <ext:toolbar.button   cls="x-btn-text-icon"    onClick="closedApp()"    icon="${pathRoot}/css/icons/fam/connect.gif" /> 
				       
				        
				        
				   </ext:toolbar>
			  </ext:body>
              <div  id="wwAQ"  style="position:absolute;top: 63%;width: 14%;"> </div>
         </td>
       </tr>
  </table>
</form>
</ext:panel>


<!-- ******************************************************* Panel Gauche**********************************************************************************-->


    <ext:panel region="west"      margins="0 0 5 3"  autoScroll="yes"   border="true"    width="130"   collapsible="true"  
	  title="Explorer"   id="hyuE"       split="true"   collapseMode="true"   hideCollapseTool="false"
	  plugins="[Ext.ux.PanelCollapsedTitle]">
	  
        <div  class="x-toolbar"  style="height: 1400px;"  > 
        <form  id="myhrefaction"   method="post"   > 
          <input  name="HiddenAction"  id="HiddenAction"  value="i$_ACT_LOAD_Mod_SM"   type="hidden" > 
          <input  name="data_for_module"  id="data_for_module"  value=""   type="hidden" >   
		  <div id="wrapper"  > 
<!-- 			  <ul class="menu"    > -->
			          <c:forEach var="packbean"  varStatus="outer" items="${listPackgeSousPack}"    >
<%-- 					      <c:choose> --%>
<%-- 						      <c:when test="${not empty packbean.racourci_soupack}"> --%>
<%-- 						        <li class="item${outer.index}"  onclick="getModules('${packbean.racourci_soupack}','${packbean.pack_id}');" > --%>
						        
<!-- 						        <a href='#'   style="text-decoration: none;"> -->
<%-- 						       <c:out value="${packbean.pack_libelle}"/>   --%>
						         
<!-- 						           </a>  -->
<!-- 							   <ul>  -->
							  
<!-- 					          </ul>  -->
<!-- 					          </li>  -->
<%-- 						      </c:when> --%>
<%-- 						    <c:otherwise> --%>
<%-- 						        <li class="item${outer.index}"><a href='#' style="text-decoration: none;" > <c:out value="${packbean.pack_libelle}"/>   </a>  --%>
							   <ul  class="menu"   >   <!-- <ul   >   -->
							   <c:forEach  var="sousPackbean"  varStatus="souterX" items="${packbean.list_sous_mod}" >
							      <li class="item${souterX.index}"  onclick="getModules(${sousPackbean.spack_id},'${packbean.pack_id}');" style="cursor: pointer;"  >
							      <a  name="" style="text-decoration: none;" >
							       <c:out value="${sousPackbean.spack_libelle}"/>  </a>
							       </li>
							 </c:forEach> 
					          </ul> 
<!-- 					          </li>  -->
<%-- 						    </c:otherwise> --%>
<%-- 						</c:choose> --%>
			         </c:forEach>
<!-- 			   </ul> -->
	      </div>
      </form>
   </div>
</ext:panel>

 
 
    
<!-- ******************************************************* Panel center**********************************************************************-->
    <ext:panel  region="center" margins="0 3 15 0"  border="true"   autoScroll="false"   bodyStyle="background:white;"   id="PanelSwing"     >
    <!-- background:#EEEFE7;  -->
    
       <form  id="myformToServeur"  method="post"  name="myformToServeur"     >
	        <div id="toolbarBttn"      style="margin: 5px 10px 0px 10px;position: static;display:${disMenuX.toolbarBttn};" >
	           <input  type="hidden"  id="HiddenAction"  name="HiddenAction"  value=""       >
	           
		    
		    
		      <!-- 
				        <div class="x-panel-header"  id="pippa" 
				         style="position:  fixed;top: 11.5%;text-align: center;left:38%;z-index: 1;color:white;width: 180px;border: none;
				          background: #636d76;display:${disMenuX.toolbarBttn};"  > ${bs.fct_libelle} - ${bs.sousmod_libelle}      
				          </div> -->
				          
	        </div> 
	           <c:set var="styloAc" value="max-height: 200px;overflow: auto;border: 1px solid #99bbe8;margin: 0px 10px 10px 10px;background-color:#FEFCFF;"  /><!-- background-color:#f8fcfc; -->
               <c:if test="${disMenuX.toolbarBttn=='none'}">
               <c:set var="styloAc" value=""  />
               </c:if>
	           <div id="ThePageJsp"  style="${styloAc}" > 
				 
				
	              <ext:body  > 	   
	                  <ext:panel     renderTo="toolbarBttn"  
	                  
	                  bodyStyle="background-color:#616975;"
	                   
	                   border="true"   collapsible="false"    
	                    style="background-color:#616975;" 
	                   
	                    >
		                  <ext:toolbar   id="Wqs"         style="background-color:#616975;"  >   
		                 
					       <c:if test="${fn:length(sousModulebean.listf)> 0}">  
				              <c:forEach var="fcBean" varStatus="inFct" items="${sousModulebean.listf}">
				                <ext:toolbar.button   
				                         icon="${pathRootac}${fcBean.fct_icon}" 
				                         cls="x-btn-text-icon"  text="${fcBean.fct_libelle}"
						                 onClick="getInitActions('${fcBean.fct_id}','${fcBean.fct_libelle}','${fcBean.view_smfct_action}',
								      	                         '${sousModulebean.sousmod_id}','${sousModulebean.sousmod_libelle}',
								      	                         '${bs.indexSousModule}','${bs.indexModule}','${sousModulebean.sousmod_action}',this)"/>  
								 <ext:toolbar.separator/>
				              </c:forEach>
				              
				                
				           </c:if>
				            <div style="background-color:#616975;padding-left: 3%;font-weight: bold;color:#fff;padding-top: 0.5%;top: -50px;padding-bottom: 5px;font-size: 15px;font-family: arial;"  >${bs.fct_libelle} ${bs.prefix_sousmod_libelle_title} ${bs.sousmod_libelle_title}</div>
				          </ext:toolbar>
				          
		                  <ext:toolbar   id="WqsSED"      style="background-color:#f8fcfc;float:right;margin-right:0.5;"  >            
							 	              
							<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-first.gif"   cls="x-btn-text-icon"    disabled="${tml.btfirst}"          onClick="${tml.fctdoFisrt}"  />  
							<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-prev.gif"    cls="x-btn-text-icon"    disabled="${tml.btPrevious}"      onClick="${tml.fctdoPrevieus}"   />
							<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-next.gif"    cls="x-btn-text-icon"    disabled="${tml.btNext}"    onClick="${tml.fctdoNext}"   />  
							<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/page-last.gif"    cls="x-btn-text-icon"    disabled="${tml.btLast}"    onClick="${tml.fctdoLast}"   />
							
							
							<ext:toolbar.button    cls="x-btn-text-icon"    text="&nbsp;"    id="ESPACE_UN"     minWidth="20"  disabled="true" />
							
							<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/Hint.gif"         cls="x-btn-text-icon"      disabled="${tml.btTrace}"   onClick="doGetHelp()"   text="${_labelhelp}"       />
							<ext:toolbar.separator />  
							<ext:toolbar.button    icon="${pathRootac}/css/icons/fam/Notes.gif"        cls="x-btn-text-icon"      disabled="${tml.btAide}"    onClick="doGetTrace()"  text="${_labeltrace}"       />
							<ext:toolbar.separator />  
							<ext:toolbar.button   text="Print"  icon="${pathRootac}/css/icons/fam/pdf.png"          cls="x-btn-text-iconXX"    disabled="${tml.btPdf}"   
							      onClick="${tml.fctdoPdf}"     id="btPrintPdfx"  >
						     </ext:toolbar.button>
						     
	                       
						   <ext:toolbar.button   id="methodPRint"     onClick="changePrint()"     text="${bs.formatPrintIcon}"    ></ext:toolbar.button>
						   
							<script>
							
							function messageBoxTimeOut(title,body,time){
								var mb = Ext.MessageBox.show({
								    title:title,
								    msg: '&nbsp;&nbsp;&nbsp;&nbsp;'+body+'&nbsp;&nbsp;&nbsp;&nbsp;',
								    icon: Ext.Msg.INFO,
								    closable: true,
								    fn:function(btn) { } // singleton
								});
								setTimeout(function(){mb.hide();}, time);
							}
							function changePrint( ){
								 
								if(Ext.getCmp('methodPRint').getText()=="&#9650;&#9660;"){
									var json1= doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}?newFormat=paysage','i$_ACT_CHANGE_FORMAT_PRINT','text',false);
									if(json1=="paysage"){ 
									Ext.getCmp('methodPRint').setText("&#9668;&#9658;");
									messageBoxTimeOut("",json1,500);
									}else{
										Ext.Msg.alert('','error');
									}
									return null;
								}
								
								if(Ext.getCmp('methodPRint').getText()=="&#9668;&#9658;"){
									var json2= doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}?newFormat=portrait','i$_ACT_CHANGE_FORMAT_PRINT','text',false);
									if(json2=="portrait") {
										Ext.getCmp('methodPRint').setText("&#9650;&#9660;");
										messageBoxTimeOut("",json2,500);
									}else{
										Ext.Msg.alert('','error');
									}
									
									return null;
								}
								
							}
							 
							</script>
							<ext:toolbar.separator />
							
							<ext:toolbar.button   text="Excel"   icon="${pathRootac}/css/icons/fam/Excel.gif"        cls="x-btn-text-iconXX"    disabled="${tml.btExcel}"   onClick="${tml.fctdoExcel}"     id="btExportXlsx" />
							
							<ext:toolbar.button    cls="x-btn-text-icon"    text="&nbsp;"   id="ESPACE_DEUX"      minWidth="20"  disabled="true" />
								
							<ext:toolbar.button    cls="x-btn-text-iconXX"    text="${tml.libelleCommit}"      icon="${pathRootac}/css/icons/fam/icon_commit.png"     id="btCommit"      disabled="${tml.commit}"         onClick="${tml.fctdoCommit}"     />
							<ext:toolbar.separator/>  
							<ext:toolbar.button    cls="x-btn-text-iconXX"    text="${tml.libelleRollback}"    icon="${pathRootac}/css/icons/fam/icon_rollback.png"   id="btRollback"    disabled="${tml.rollback}"       onClick="${tml.fctdoRollback}"  />  				        
					
								
							 <ext:toolbar.separator />
							<ext:toolbar.button    cls="x-btn-text-iconXX"    text="${tml.libelleAction}"  icon="${pathRootac}/css/icons/fam/${tml.iconAction}"       minWidth="150"         type="submit"    id="btValidx"    onClick="${tml.fctdoValid}"  disabled="${tml.btValid}"     />  
							<ext:toolbar.separator />
							<ext:toolbar.button    cls="x-btn-text-iconXX"    text="Réinitialiser"           icon="${pathRootac}/css/icons/fam/gtk-cancel.png"              type="button"    id="btReset"    disabled="${tml.btReset}"   onClick="${tml.fctdoReset}"   />   
							<ext:toolbar.separator/>
							<ext:toolbar.button    cls="x-btn-text-iconXX"    text="Retour"                 icon="${pathRootac}/css/icons/fam/Redo.png"               type="button"     id="btRetour"      disabled="${tml.btRetour}"  onClick="${tml.fctdoRetour}" /> 
	                     </ext:toolbar>
	                  </ext:panel>
	            </ext:body>
	            <div id="errmsg"  style="color: red;font-weight: bold;margin-left: 45%;"></div> 
	            
				<div id="errmsgssss"  style="color: red;font-weight: bold;margin-left: 3%;font-size: 12px;">
	             <c:if test="${not empty detailBean}"> 
	             <c:catch var="CatchNullPointerException">
	             <c:if test="${not empty detailBean.modeBean }"> 
	             <c:if test="${not empty detailBean.modeBean.fct_libelle}"> 
				 <p  style="margin-top: 5px;" >  <font color="black">Mode:</font>   ${detailBean.modeBean.fct_libelle}     
				 <font  style="margin-r: 5px;float: right;" >  
	              <c:if test="${not empty detailBean}"> 
	              <c:if test="${not empty detailBean.date_cre}"> 
	              <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_cre}"    var="detailBeaSxccvncmd_date"/>
				  <font color="black">Crée le:</font>    ${detailBeaSxccvncmd_date}  
				  </c:if>
				  </c:if>
				  <c:if test="${not empty detailBean}"> 
	              <c:if test="${not empty detailBean.date_mod}"> 
	              <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_mod}"    var="dedmodifierd_date"/>
				  <font color="black">  ----  Modifié le:</font>    ${dedmodifierd_date}  
				  </c:if>
				  </c:if>
				   </font>
				  </p>
				
				 </c:if>
				 </c:if>
				 
				 </c:catch>
				  </c:if>
				 
				</div> 
	            
	          </div> 
           </form>   
</ext:panel>
    <!-- ******************************************************* END**********************************************************************-->
</ext:viewport>
</ext:body>
<script type="text/javascript">
Ext.onReady(function(){
 if("${bs.formatPrint}"=="portrait"){
			Ext.getCmp('methodPRint').setText("&#9650;&#9660;");
 }else{
	 Ext.getCmp('methodPRint').setText("&#9668;&#9658;");
 }
var primecontact = Ext.getCmp('hyuE');
primecontact.expand();
var dffff_win=$(window).height() - 195;
dffff_win=dffff_win+"px";
document.getElementById("ThePageJsp").style.maxHeight=dffff_win; 
});
</script>
</body>
</html>