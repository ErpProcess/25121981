<%@    include file="/Aceuil/esProcess.jsp"     %>
 
 
<script  type="text/javascript">

var ListeAjaxData  ="listAttributeEntite";
var urlsAjaxSource ="${tmlx.urlAjax}?HiddenAction=i$_ACT_PREPARER_ENTITE&nameList="+ListeAjaxData;
var urlsUpdateURL  ="${tmlx.urlAjax}?HiddenAction=i$_ACT_UPDATE_EDITABLE_TABLE&nameList="+ListeAjaxData;
var urlsDeleteURL  ="${tmlx.urlAjax}?HiddenAction=i$_ACT_DELETE_ROW_EDITABLE_TABLE_AJAX&nameList="+ListeAjaxData;
var urlsAddURL     ="${tmlx.urlAjax}?HiddenAction=i$_ACT_LOAD_EDITABLE_TABLE_AJAX&nameList="+ListeAjaxData;


$(document).ready(function (){
LoadAutoCompletAjax("sousmod_id","sousmod_action","usr_date_naiss","listSousModuleForCree"); 
});

function onChange_select(param_val){
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


 
function doEnvoiData(element){

	if($(element).attr('type')=="checkbox"){
	    element.value=element.checked==false?"no":"yes";
	 }
	var idToSendo=$(element).attr('idRow') ;
	var LEvalue=element.value;
	var name_column=element.name;
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL, 
	     data:'id='+idToSendo+'&value='+LEvalue+'&name_column='+name_column,
	     dataType: 'text', 
	     success: function(data){   }
      });      
}

 
 
var sNameBean=[
               {  "sTitle": "rowxi" ,"sName": "indx_row"     ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false },
               {  "sTitle": "en"    ,"sName": "id_entite"    ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false },
               
               {  "sTitle": "Form Model"     ,"sName": "to_check_form"     ,"sWidth": "2%"     ,"bSortable": false    , "mRender": function( data, type, full){
                  var is_checked=full[2]=='yes'?'checked':'';
                  return  '<input idRow='+full[1]+' type=\'checkbox\'   id=to_check_form'+full[0]+' name=to_check_form  '+is_checked+'      onclick=doEnvoiData(this)    value='+data+'    >';
               }},
               
               {  "sTitle": "Filter Model"     ,"sName": "to_check_filter"     ,"sWidth": "2%"     ,"bSortable": false    , "mRender": function( data, type, full){
                  var is_checked=full[3]=='yes'?'checked':'';
                  return  '<input idRow='+full[1]+' type=\'checkbox\'  id=to_check_filter'+full[0]+' name=to_check_filter   '+is_checked+'   onclick=doEnvoiData(this)    value='+data+'    >';
               }},
               
               {  "sTitle": "Liste Model"     ,"sName": "to_check_list"     ,"sWidth": "2%"     ,"bSortable": false    , "mRender": function( data, type, full){
                  var is_checked=full[4]=='yes'?'checked':'';
                  return  '<input idRow='+full[1]+' type=\'checkbox\'  id=to_check_list'+full[0]+' name=to_check_list   '+is_checked+'       onclick=doEnvoiData(this)    value='+data+'    >';
               }},
                      
			   {  "sTitle": "Ent_id"       ,"sName": "pk_web_root.ent_id"       ,"sWidth": "10%" },
			   {  "sTitle": "Langue"       ,"sName": "pk_web_root.lang_id"      ,"sWidth": "5%"    },	
               {  "sTitle": "Colonne"      ,"sName": "column_name"              ,"sWidth": "15%"  },
               {  "sTitle": "type_name"    ,"sName": "type_name"                ,"sWidth": "5%"   },
               {  "sTitle": "Ent_Libelle"  ,"sName": "ent_libelle"              ,"sWidth": "30%"   ,"mRender": function( data, type, full){  
	               return '<input idRow='+full[1]+' type=\'text\'  id=ent_libelle'+full[0]+'   name=ent_libelle   value='+data+'    onblur=doEnvoiData(this)      nextElement=ent_abrv'+full[0]+'  />';
	              }},
               {  "sTitle": "Abrv"         ,"sName": "ent_abrv"                 ,"sWidth": "20%", "mRender": function( data, type, full){
	               return '<input idRow='+full[1]+' type=\'text\'  id=ent_abrv'+full[0]+'   name=ent_abrv     value='+data+'        onblur=doEnvoiData(this)      nextElement=lang_id'+full[9]+'        />';
	            }},
	           {  "sTitle": "next" ,"sName": "indx_row_next"     ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
               ];
var sNameBEdit=[ 
				null,
				null,
				null,
				null,
				null,
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
	                          $("#container").hide();
	                          mayBox_al("Aucune Résulat","")   ;
	                          }else{ 
	                          fnCallback(json);
	                           $("#container").show();
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
Ext.getCmp('tabpWebRoot').setActiveTab('panelWebroot2'); 
       
}
</script>

<div style="width: 90%;margin: 30px 30px 30px"></div>
<ext:body>
  <ext:panel  renderTo="ThePageJsp"        bodyStyle="background: none;"        border="false"               >
  
     <ext:tabPanel  id="tabpWebRoot"   bodyStyle="background: none;"    style="width: 90%;margin: 30px 30px 30px;"   border="true"    activeTab="panelWebroot1"   >
       <ext:panel   title="Création du Répertoire-Entite"     bodyStyle="background: none;"   id="panelWebroot1"  > 
        <table    class="tableStyleContent"    cellpadding="5" cellspacing="10"     border="0"  id="tblData"     >
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>
	        <td width="100%" colspan="6"    align="center" >&nbsp;<span id="errmsg"  style="color: red;font-weight: bold;"></span> </td>
	      </tr>
	      <!-- ******************************************************************************************************************************************** -->
	       <tr>  
	        <td width="10%" ><label>Chemin Project</label></td>
	        <td width="7%" ><input id="cheminProject"  name="cheminProject"       type="text"     size="30"   nextElement="usr_date_naiss"       autofocus   required   />
	        </td>
	        <td width="70%">
	        </td>
	      </tr>
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>  
	        <td width="10%" ><label>Entite.action</label></td>
	        <td width="70%" colspan="2">
	        <input id="sousmod_id"  name="sousmod_id"          type="text"     size="10"    nextElement="usaiss"       autofocus   required   />
	        <input id="sousmod_action" name="sousmod_action"       type="text"     size="70"  nextElement="usaiss"                 required  />
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
	      
	      <tr>  
	        <td width="10%" > </td>
	        <td width="70%" colspan="2">
	        <ext:button text="Suivant"  onClick="loadDataFromServerInit()"></ext:button>
	        </td>
	      </tr>
	    </table>
      </ext:panel>
     <ext:panel  title="Détaille Fichier Jsp"   bodyStyle="background: none;"   id="panelWebroot2"  autoScroll="true" >
     <div id="container">
		           <div  id="topy" class="x-panel-header"  style="height: 15px;width: 100%;"   >
				         <div  style="margin-top: -4px;"> Detail Libelle Par Langue</div>
		            </div>
		            <div id="demo_jui">
				        <table id="gridColon" class="display" width="100%" ></table>
				    </div>
	            </div>
     </ext:panel>
   </ext:tabPanel>
    
   
   
     
     
  </ext:panel>
</ext:body>
