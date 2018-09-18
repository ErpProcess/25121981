 <%@include file="/Aceuil/esProcess.jsp" %>
<script type="text/javascript">

var ListeAjaxData  ="listPreparerEntiteAdmin";
var urlsAjaxSource ="${tmlx.urlAjax}?HiddenAction=i$_ACT_PREPARER_LIB_ENTITE&nameList="+ListeAjaxData;
var urlsUpdateURL  ="${tmlx.urlAjax}?HiddenAction=i$_ACT_UPDATE_EDITABLE_TABLE_AJAX&nameList="+ListeAjaxData;
var urlsDeleteURL  ="${tmlx.urlAjax}?HiddenAction=i$_ACT_DELETE_ROW_EDITABLE_TABLE_AJAX&nameList="+ListeAjaxData;
var urlsAddURL     ="${tmlx.urlAjax}?HiddenAction=i$_ACT_LOAD_EDITABLE_TABLE_AJAX&nameList="+ListeAjaxData;

$(document).ready(function (){
$("#RET").hide();
LoadAutoCompletAjax("lang_id","lang_libelle","pack_id","listLangueEntite");
LoadAutoCompletAjax("pack_id","pack_libelle","spack_id","listPackageSys");
LoadOtherAutocompletesAjax("pack_id","i$_ACT_LOAD_SOUSPACK","spack_id","spack_libelle","mod_id");
LoadOtherAutocompletesAjax("spack_id","i$_ACT_LOAD_MODULE","mod_id","mod_libelle","sousmod_id");
LoadOtherAutocompletesAjax("mod_id","i$_ACT_LOAD_SOUS_MODULE","sousmod_id","sousmod_libelle",null);
});

function onChange_select(param_val){
if(param_val=="pack_id"){  
document.getElementById("spack_id").value="";
document.getElementById("spack_libelle").value="";
document.getElementById("mod_id").value="";
document.getElementById("mod_libelle").value="";
document.getElementById("sousmod_id").value="";
document.getElementById("sousmod_libelle").value="";
document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";

}else if(param_val=="spack_id"){  

document.getElementById("mod_id").value="";
document.getElementById("mod_libelle").value="";
document.getElementById("sousmod_id").value="";
document.getElementById("sousmod_libelle").value="";
document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";

}else if(param_val=="mod_id"){  

document.getElementById("sousmod_id").value="";
document.getElementById("sousmod_libelle").value="";
document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";

}else if(param_val=="sousmod_id"){  

document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";
  var url ="${tmlx.urlAjax}?HiddenAction=i$_ACT_LOAD_TAB_SCHEM";  
		$.ajax({ 
	    type: 'POST', 
	    url: url, 
	    data:  "vapToSend="+document.getElementById(param_val).value, 
	    dataType: 'json',
	    success: function (data) {
		    document.getElementById("table_name").value=data.myliste[0].table;
		    document.getElementById("table_schem").value=data.myliste[0].schema;
	    },
	   error: function (result) {
	        alert("Taper Sur Enter");
	    }
	});

}
}

function do_checkRow(idRow,element){
	var cheko=element.checked==false?"no":"yes";
	jQuery.ajax({ type: 'POST',  
	              url: urlsUpdateURL, 
	              data:'hid_act=tochek&idTosend='+idRow+'&val_cheked='+cheko,
	              dataType: 'text', 
	              success: function(data){   }
		        });
}


function loadLesListeCorrlee(objeJson){
 for ( var i=0, ien=objeJson.aaData.length ; i<ien ; i++ ) {
 		var indexo=objeJson.aaData[i][0];
        var ojEncours="lang_id"+indexo;
        var nextFocuS="ent_libelle"+indexo;
        var idRowGridsx=objeJson.aaData[i][1];
        LoadAutoCompletGrid("lang_id","lang_libelle",nextFocuS,"listLangueEntite",ojEncours,idRowGridsx);
      }
}

function doEnvoiData(idToSendo,name_column,LEvalue){
	jQuery.ajax({ type: 'POST',  
	              url: urlsUpdateURL, 
	              data:'hid_act=ghy&id='+idToSendo+'&value='+LEvalue+'&name_column='+name_column,
	              dataType: 'text', 
	              success: function(data){   }
		        });      
}

 
var sNameBean=[
               {  "sTitle": "rowxi" ,"sName": "indx_row"     ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false },
               {  "sTitle": "en"    ,"sName": "id_entite"    ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false },
               {  "sTitle": "X"     ,"sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": false    , "mRender": function( data, type, full){
                  var is_checked=full[2]=='yes'?'checked':'';
                  return  '<input idRow='+full[1]+' type=\'checkbox\'  id=to_check'+full[0]+' name=to_check'+full[0]+'  '+is_checked+'   onclick=do_checkRow($(this).attr(\'idRow\'),this)    value='+data+'    >';
                      }},
			   {  "sTitle": "Ent_id"       ,"sName": "pk_entite_admin.ent_id"   ,"sWidth": "10%"  , "bSortable": false  },
			   
			   {  "sTitle": "Langue"       ,"sName": "pk_entite_admin.lang_id"  ,"sWidth": "5%"   , "bSortable": true, "mRender": function( data, type, full){
                  return  '<input idRow='+full[1]+' type=\'text\'  id=lang_id'+full[0]+'   name=lang_id'+full[0]+'       value='+data+'       />';}   
               },	
			   
               {  "sTitle": "Colonne"      ,"sName": "column_name"              ,"sWidth": "15%"  },
               {  "sTitle": "type_name"    ,"sName": "type_name"                ,"sWidth": "5%"   },
               
               {  "sTitle": "Ent_Libelle"  ,"sName": "ent_libelle"              ,"sWidth": "30%"   ,"mRender": function( data, type, full){  
	               return '<input idRow='+full[1]+' type=\'text\'  id=ent_libelle'+full[0]+'   name=ent_libelle   value='+data+'   onblur=doEnvoiData($(this).attr(\'idRow\'),$(this).attr(\'name\'),this.value)  nextElement=ent_abrv'+full[0]+'  />';
	              }},
	                      
               {  "sTitle": "Abrv"         ,"sName": "ent_abrv"                 ,"sWidth": "20%", "mRender": function( data, type, full){
	               return '<input idRow='+full[1]+' type=\'text\'  id=ent_abrv'+full[0]+'   name=ent_abrv     value='+data+'   onblur=doEnvoiData($(this).attr(\'idRow\'),$(this).attr(\'name\'),this.value)         nextElement=lang_id'+full[9]+'        />';
	            }},
	              {  "sTitle": "next" ,"sName": "indx_row_next"     ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
               ];
var sNameBEdit=[ 
				null,
				null,
				{type: 'checkbox', onblur: 'submit'},
                null, 
                {indicator: 'Saving...', tooltip: 'Click to edit ', loadtext: 'loading...', event: 'help', type: 'text', onblur: 'submit'},
                 null,
                 null,
        	    {indicator: 'Saving...', tooltip: 'Click to edit ', loadtext: 'loading...', event: 'help', type: 'text', onblur: 'submit'},
        	    {indicator: 'Saving...', tooltip: 'Click to edit ', loadtext: 'loading...', event: 'help', type: 'text', onblur: 'submit'},
        	     null
        	    ]; 
        	    
$(document).ready(function () {
   $('#gridColon tbody tr').live('click', function () {
		   $(this).toggleClass('row_selected');
} );
});
 

  
        	    
function loadDataFromServerInit(){
$("#PanelSwing").mask("Waiting...");  
if (oTable != undefined) { oTable.fnReloadAjax();  } else {
oTable= $("#gridColon").dataTable({
                "bServerSide": true,
                "sAjaxSource": urlsAjaxSource,
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "oColReorder": {"headerContextMenu": true},
                "bJQueryUI": true,
                "scrollY":"100px",
                //"sDom": "Rlfrtip",
                "aoColumns": sNameBean,
                "fnServerData": function ( sSource, aoData, fnCallback ) {
                 var formFilter= aoData.concat( $("#myformToServeur").serializeArray() );
	                   $.ajax({
	                   "dataType": 'json',
	                   "type": "POST",
	                   "url": sSource,
	                   "data": formFilter,  
	                   "success" : function(json){  
	                          var count = Object.keys(json.aaData).length; 
	                          $("#PanelSwing").unmask();  
	                          if(count==0){
	                          $("#RET").hide();
	                          mayBox_al("Aucune Résulat","")   ;
	                          }else{ 
	                          fnCallback(json);
	                          loadLesListeCorrlee(json);
	                           $("#RET").show();
	                          }  
	                          } ,
	                          
	                   "error": function (e) {
	                          alert("Erreur  Serveur! Contacter Administration ");
	                    }
	                   });
	                   }
         }).makeEditable({  
                sDeleteURL :urlsDeleteURL,
         		sAddURL    :urlsAddURL ,
         		sUpdateURL :urlsUpdateURL,
        	    "aoColumns":sNameBEdit 
       });
       
       }
}

 
 


function loadDataFromServer(){
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
  loadDataFromServerInit();
  }
}
 
function doCommitAction(){

$('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_ADD_WIDTH_LIST");	

 
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
  
   $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
   $("#myformToServeur").submit(); 
             
  }

 
}
</script>

<ext:body>

  <ext:panel    renderTo="ThePageJsp"          bodyStyle="background: none;"             border="false"       >
	    <table class="tableStyleContent"     cellpadding="5" cellspacing="10"            border="0"        id="tblData"     >
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>
	        <td width="100%" colspan="4"    align="center" >&nbsp;<span id="errmsg"  style="color: red;font-weight: bold;"></span> </td>
	      </tr>
	      
	        <!-- ******************************************************************************************************************************************** -->
	      <tr>  
	        <td width="20%" ><label>Langue</label></td>
	        <td width="29%"  colspan="3">  
	        <input id="lang_id"       name="pk_entite_admin.lang_id"         type="text"       size="5"    value=""       required    autofocus     />
	        <input id="lang_libelle"  name="pk_entite_admin.lang_libelle"    type="text"      size="30"   value=""         />
	        </td>
	        </tr>
	      
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>  
	        <td width="20%" ><label>Package Systeme </label></td>
	        <td width="29%" >
	        <input id="pack_id"       name="pack_id"         type="text"    size="5"    value=""       required          />
	        <input id="pack_libelle"  name="pack_libelle"    type="text"       size="30"   value=""         />
	        </td>
	        <td width="8%"><label >Sous Package  </label></td>
	        <td width="27%"> 
	        	 <input id="spack_id"     name="spack_id"             type="text"     size="5"    value=""     required      />
	             <input id="spack_libelle"  name="spack_libelle"       type="text"     size="30"   value=""         />
	        </tr>
	      <!-- ******************************************************************************************************************************************** -->
	        <tr>  
	        <td width="20%" ><label>Module </label></td>
	        <td width="29%" >
	         <input id="mod_id"       name="mod_id"                 type="text"        size="5"       value=""               />
	         <input id="mod_libelle"  name="mod_libelle"            type="text"        size="30"      value=""          />
	        </td>
	        <td width="8%"><label >Sous Module  </label></td>
	        <td width="27%"> 
	        	 <input id="sousmod_id"       name="sousmod_id"        nextElement="btValidx"      type="text"     size="5"    value=""      />
	             <input id="sousmod_libelle"  name="sousmod_libelle"   nextElement="btValidx"      type="text"     size="30"   value=""     />
	        </td>
	      </tr>
	      
	       <tr>  
	        <td width="20%" ><label>Schema </label></td>
	        <td width="29%" >
	         <input id="table_schem"  name="table_schem"    type="text"     size="30"   value=""             />
	        </td>
	        <td width="8%"><label >Table</label></td>
	        <td width="27%"> 
	          <input id="table_name"  name="table_name"    type="text"     size="30"   value=""          />
	        </td>
	      </tr>
	      
	      
	    </table>
	    
     <ext:body>
          <ext:panel     renderTo="RET"   border="false"     >
	          <div id="container">
		           <div  id="topy" class="x-panel-header"  style="height: 15px;width: 100%;"   >
				         <div  style="margin-top: -4px;"> Detail Libelle Par Langue &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
				             <button id="btnAddNewRow" value="Ok" type="button"  onclick="isadd()"  style="margin-left: 80%; " disabled="disabled"  >+</button> 
				    	     <button id="btnDeleteRow" value="cancel"  type="button"   disabled="disabled" >-</button>
				         </div>
		            </div>
		            <div id="demo_jui">
				        <table id="gridColon" class="display" width="100%" ></table>
				    </div>
	            </div>
          </ext:panel> 
          
        </ext:body>
       <div  id="RET"   ></div>
  </ext:panel>
</ext:body>
