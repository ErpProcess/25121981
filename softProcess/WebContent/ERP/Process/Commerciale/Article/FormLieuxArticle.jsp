 <%@include file="/Aceuil/esProcess.jsp" %>  
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 
 <script type="text/javascript">
 contenu_toolbarJQuey="";
 height_tabbJQuey="auto";
 mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_LOT",
                               "list"   :"lisfS",
                               "id_name":"pk.lieu.depot_id",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									      {  "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									      {   "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[3]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[3]+'")          >';}},
									      { "sTitle": "Etablissment"           ,"sName": "pk.lieu.fk_etab_Bean.etab_lib"                         ,"sWidth": "5%"   , "bSortable": "true"   },         
										  { "sTitle": "code"                   ,"sName": "pk.lieu.depot_id"                         ,"sWidth": "5%"   , "bSortable": "true"   }, 
										  { "sTitle": "libelle"                ,"sName": "pk.lieu.depot_libelle"                       ,"sWidth": "10%"   , "bSortable": "true"   },
	                                            ]
 
                               };
    
 
$(document).ready(function (){	 
	LoadOtherAutocompletesAjax("etab_lib","i$_ACT_LOAD_ETAB_P_CLT","pk_etab.etab_id","etab_lib","pk_article.ar_id");
	LoadOtherAutocompletesAjax("pk_etab.etab_id","i$_ACT_LOAD_ARTICLE_CLT","pk_article.ar_id","ar_libelle","pk.code_barre");
	LoadOtherAutocompletesAjax("pk_article.ar_id","i$_ACT_LOAD_REFE_CLT","pk.code_barre","designation_libelle","depot_id");
});

function getSuiv(){
if(!teste_required()) return ;

 var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_LIEUX','json',false);

   
$('input[id="pk_etab.etab_id"]').autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#etab_lib").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});   
$('input[id="pk_etab.etab_id"]').attr("readonly","readonly");
$("#etab_lib").attr("readonly","readonly");



$('input[id="pk_article.ar_id"]').autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#ar_libelle").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});   
$('input[id="pk_article.ar_id"]').attr("readonly","readonly");
$("#ar_libelle").attr("readonly","readonly");





$('input[id="pk.code_barre"]').autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#designation_libelle").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});   
$('input[id="pk.code_barre"]').attr("readonly","readonly");
$("#designation_libelle").attr("readonly","readonly");




Ext.getCmp('btnnext').disable(); 
Ext.getCmp('btValidx').enable(); 

Ext.get('GRID_LOT_CHOIX').setStyle('display', 'block');
 
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
 
}



</script>
 
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 
 
 
 <ext:toolbar         toolbarType="bbar"     > 
        <ext:toolbar.button  text=" Suivant  >> "          onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
 

		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"    width="100%"     >  
		    
		  <tr>  
		      
			 <td  ><label>Etablissment</label></td>  
		      <td  colspan="3" >  
		      <input id="pk_etab.etab_id"  name="pk.ref.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id"       type="text"    size="15"     value="${detailBean.pk.ref.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id}"        required            />
			  <input id="etab_lib" name="pk.ref.pk.ar_bean.pk_article.etabBean.etab_lib"     type="text"    size="30"     value="${detailBean.pk.ref.pk.ar_bean.pk_article.etabBean.etab_lib}"       required              />	
			 </td>  
		    </tr>  
		   
		   
		   
		   <tr>  
		      <td  ><label>Code</label></td>  
		      <td   >  
		      <input id="pk_article.ar_id" name="pk.ref.pk.ar_bean.pk_article.ar_id"     type="text"    size="15"           value="${detailBean.pk.ref.pk.ar_bean.pk_article.ar_id}"        required            />
			  <input id="ar_libelle" name="pk.ref.pk.ar_bean.ar_libelle"     type="text"    size="30"              value="${detailBean.pk.ref.pk.ar_bean.ar_libelle}"       required              />		
			  </td>  
			  
			  <td  ><label>Référence</label></td>  
		      <td   >  
		      <input id="pk.code_barre" name="pk.ref.pk.code_barre"     type="text"    size="15"           value="${detailBean.pk.ref.pk.code_barre}"        required            />
			  <input id="designation_libelle" name="pk.ref.designation_libelle"     type="text"    size="30"              value="${detailBean.pk.ref.designation_libelle}"       required              />		
			  </td>  
			  
			  
		    </tr>  
		   
		 </table>
	   

 
</ext:panel>
<ext:panel    title="Liste des Lots"  id="GRID_LOT_CHOIX"   border="false"   bodyStyle="background: none;"     style="display:none;"   >
  <table id="GRID_LOT" class="display" width="100%"     ></table>
 </ext:panel>
</ext:panel>
</ext:body>
