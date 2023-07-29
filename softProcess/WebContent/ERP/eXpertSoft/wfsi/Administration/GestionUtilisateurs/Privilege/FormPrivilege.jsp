<%@    include file="/Aceuil/esProcess.jsp"     %>

<script type="text/javascript">
var oTable1;
var oTable2;
var oTable3;
var oTable4;

var mapEditableGen;
var map2;
var map3;
var map4;
/*******************************************************************************************************************************************/
var mapColumsbean=[
               {  "sTitle": "N"     ,"sName": "indx_row"     ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false },
               {  "sTitle": "X"     ,"sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true    , "mRender": function( data, type, full){
                  return  '<input idRow='+full[2]+' type=\'checkbox\'   id=to_check'+full[0]+' name=to_check   '+data+'    onclick=doUpdateCell_S(this,mapEditableGen)    value='+data+'    >';}},
               {  "sTitle": "Id"            ,"sName": "pack_id"           ,"sWidth": "20%"    ,"bSearchable": false  , "bSortable": true,"bVisible": true },
			   {  "sTitle": "Libelle"       ,"sName": "pack_libelle"      ,"sWidth": "30%"    ,"bSearchable": true   , "bSortable": true,"bVisible": true},
	           {  "sTitle": "next"          ,"sName": "indx_row_next"     ,"sWidth": "1%"     ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
               ];
mapEditableGen  = {"otab":oTable1,"table":"gridPackages","list":"listGridEditable_Package","id_name":"pack_id","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":mapColumsbean};
 

/*******************************************************************************************************************************************/ 
var mapColumSousPAck=[
               {  "sTitle": "N"                   ,"sName": "indx_row"         ,"sWidth": "10%"    ,"bSearchable": false   , "bSortable": false  ,"bVisible": false },
               {  "sTitle": "Packge"              ,"sName": "packageSys.pack_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
               {  "sTitle": "X"                   ,"sName": "to_check"         ,"sWidth": "2%"     ,"bSortable": true      , "mRender": function( data, type, full){
                  return  '<input idRow='+full[3]+' type=\'checkbox\'   id=to_check'+full[0]+' name=to_check   '+data+'    onclick=doUpdateCell_S(this,map2)    value='+data+'    >';}},
               {  "sTitle": "SPack Id"            ,"sName": "spack_id"         ,"sWidth": "20%"    ,"bSearchable": false   , "bSortable": true   ,"bVisible": true },
			   {  "sTitle": "SPack Libelle"       ,"sName": "spack_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
			 
	           {  "sTitle": "next"                ,"sName": "indx_row_next"    ,"sWidth": "1%"     ,"bSearchable": false   , "bSortable": false  ,"bVisible": false }, 
               ]; 
map2   = {"otab":oTable2,"table":"gridSousPackages","list":"listGridEditable_SousmapPackage","id_name":"spack_id","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":mapColumSousPAck};

/*******************************************************************************************************************************************/
var mapColumModul=[
               {  "sTitle": "N"                   ,"sName": "indx_row"         ,"sWidth": "10%"    ,"bSearchable": false   , "bSortable": false  ,"bVisible": false },
               {  "sTitle": "Packge"              ,"sName": "sousPackBean.spack_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
               {  "sTitle": "X"                   ,"sName": "to_check"         ,"sWidth": "2%"     ,"bSortable": true      , "mRender": function( data, type, full){
                  return  '<input idRow='+full[3]+' type=\'checkbox\'   id=to_check'+full[0]+' name=to_check   '+data+'    onclick=doUpdateCell_S(this,map3)    value='+data+'    >';}},
               {  "sTitle": "code"                ,"sName": "mod_id"         ,"sWidth": "20%"    ,"bSearchable": false   , "bSortable": true   ,"bVisible": true },
			   {  "sTitle": "mod Libelle"         ,"sName": "mod_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
	           {  "sTitle": "next"                ,"sName": "indx_row_next"    ,"sWidth": "1%"     ,"bSearchable": false   , "bSortable": false  ,"bVisible": false }, 
               ]; 
map3   = {"otab":oTable3,"table":"gridmoduleSystem","list":"listGridEditable_Module_desouPak","id_name":"mod_id","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":mapColumModul};

/*******************************************************************************************************************************************/


var mapColumSousModul_fonction=[
               {  "sTitle": "N"                   ,"sName": "indx_row"        ,"bSearchable": false   , "bSortable": false  ,"bVisible": false },
               {  "sTitle": "idVirtuelle"         ,"sName": "id_entite"       ,"bSearchable": false   , "bSortable": false  ,"bVisible": false },
               {  "sTitle": "module"              ,"sName": "pk.soumBean.moduleBean.mod_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
               {  "sTitle": "X"                   ,"sName": "to_check"         ,"sWidth": "2%"     ,"bSortable": true      , "mRender": function( data, type, full){
                  return  '<input idRow='+full[1]+' type=\'checkbox\'   id=to_check'+full[0]+' name=to_check   '+data+'    onclick=doUpdateCell_S(this,map4)    value='+data+'    >';}},
               {  "sTitle": "code"                 ,"sName": "pk.soumBean.sousmod_id"         ,"sWidth": "20%"    ,"bSearchable": false   , "bSortable": true   ,"bVisible": false },
			   {  "sTitle": "sous module"          ,"sName": "pk.soumBean.sousmod_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
			   {  "sTitle": "cod_f"                ,"sName": "pk.fcBean.fct_id"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": false},
			   {  "sTitle": "Fonction"             ,"sName": "pk.fcBean.fct_libelle"    ,"sWidth": "30%"    ,"bSearchable": true    , "bSortable": true   ,"bVisible": true},
	           {  "sTitle": "next"                 ,"sName": "indx_row_next"    ,"sWidth": "1%"     ,"bSearchable": false   , "bSortable": false  ,"bVisible": false }, 
               ]; 
map4  = {"otab":oTable4,"table":"gridFct_smoduleSystem","list":"listGridEditable_Fct_smoduleSystem","id_name":"id_entite","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":mapColumSousModul_fonction};



function doUpdateCell_S(element,mapOoo){

		if($(element).attr('type')=="checkbox")
		    element.value=element.checked==false?"":"checked";
		var idToSendo=$(element).attr('idRow') ;
		var LEvalue=element.value;
		var name_column=element.name;
	    var hashmap ={"sNameId":mapOoo["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapOoo["list"]};
			jQuery.ajax({ 
			     type: 'POST',  
			     url: urlsUpdateURL_def, 
			     data:hashmap,
			     dataType: 'text', 
			     success: function(data){   }
		   });      
  } 
</script>
<c:import  url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp" /> 
<script type="text/javascript">
$(document).ready(function () { LoadDataEditableFromServer(mapEditableGen); }); 


function doActiveTabInterafce(idtabs){


			jQuery.ajax({ 
			     type: 'POST',  
			     url: "${tmlx.urlAjax}?HiddenAction="+idtabs, 
			     dataType: 'text', 
			     success: function(data){
				      if(data=="true"){
				        if(idtabs=="i$_ACT_LOAD_SOUSPACKAGE"){   LoadDataEditableFromServer(map2);  Ext.getCmp('panelWebroot1').setDisabled(true);    }
				        if(idtabs=="i$_ACT_LOAD_MODUL")    {     LoadDataEditableFromServer(map3);   Ext.getCmp('i$_ACT_LOAD_SOUSPACKAGE').setDisabled(true);    }
				        if(idtabs=="i$_ACT_LOAD_F_S_MODUL")    {     LoadDataEditableFromServer(map4);    Ext.getCmp('i$_ACT_LOAD_MODUL').setDisabled(true);   }
				        
				        Ext.getCmp('tabpWebRoot').setActiveTab(idtabs);
				      }else{
				        mayBox_al("Veuillez Cochez au moin Une Ligne","") ;
				        if(idtabs=="i$_ACT_LOAD_SOUSPACKAGE")   Ext.getCmp('tabpWebRoot').setActiveTab('panelWebroot1');
				        if(idtabs=="i$_ACT_LOAD_MODUL")         Ext.getCmp('tabpWebRoot').setActiveTab('i$_ACT_LOAD_SOUSPACKAGE');
				     }
			     }
			     });      
}
		
</script>

<ext:body  >  
<ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >

  
 <ext:panel  border="false"    bodyStyle="background: none;"     collapsible="true"    >  
	 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
	   <tr>  
	   <td width="7%"><label>${prf_id}</label></td>  
	   <td width="93%">  
	   <input id="prf_id"         name="pkPriv.pfrBean.prf_id"               type="text"   size="7"  value="${detailBean.pkPriv.pfrBean.prf_id}"        libre  readonly="readonly"  />  
	   <input id="prf_libelle"    name="pkPriv.pfrBean.prf_libelle"          type="text"   size="20"  value="${detailBean.pkPriv.pfrBean.prf_libelle}"  libre  readonly="readonly"   />
	   <div id="RET_GRID"></div> 
	   <div id="Privilege-Grid"></div>
	  </td>  
	   </tr>   
	 </table>   
</ext:panel>

<ext:tabPanel  id="tabpWebRoot"   bodyStyle="background: none;"    style="width: 90%;margin: 30px 30px 30px;"   border="true"  height="400"   activeTab="panelWebroot1"   >
	 <ext:panel   title="Liste des Packages "     bodyStyle="background: none;"   id="panelWebroot1"  > 
	 <table id="gridPackages" class="display" width="100%" ></table>  
	 </ext:panel>
	 <ext:panel   title="Liste Sous Packages "     bodyStyle="background: none;"  height="300"  id="i$_ACT_LOAD_SOUSPACKAGE"  onActivate="doActiveTabInterafce(this.id)" > 
		 <table id="gridSousPackages" class="display" width="100%" ></table>
	 </ext:panel>
	 
	 <ext:panel     title="Liste Module"     bodyStyle="background: none;" height="300"    id="i$_ACT_LOAD_MODUL"  onActivate="doActiveTabInterafce(this.id)" > 
		 <table id="gridmoduleSystem" class="display" width="100%" ></table>
	 </ext:panel>
	 
	  <ext:panel     title="Fonction-Sous Module"     bodyStyle="background: none;" height="300"   id="i$_ACT_LOAD_F_S_MODUL"  onActivate="doActiveTabInterafce(this.id)" > 
		 <table id="gridFct_smoduleSystem" class="display" width="100%" ></table>
	 </ext:panel>
	 
</ext:tabPanel> 


</ext:panel>
</ext:body>
