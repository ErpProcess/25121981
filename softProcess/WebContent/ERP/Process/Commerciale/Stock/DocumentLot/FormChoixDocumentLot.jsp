<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 
<script type="text/javascript">


function control_de_liste(){
return doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_VERIF_LIST','text',false);
}


$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","depot_id","list_des_articles","600","250");
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","xxx","list_des_depot","600","250");
loadSelectAjax("type_trf_idX","list_tyList","type_trf_id","type_trf_lib","${detailBean.vente.groupe.type_trf_id}",false);
});




 contenu_toolbarJQuey="";
 height_tabbJQuey="auto";
 mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_LOT",
                               "list"   :"list_des_lots_for_select",
                               "id_name":"pk.num_serie",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									      {  "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									      { "sTitle": "choisir" , "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[3]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[3]+'")          >';}}, 
									                  
										  { "sTitle": "date lot"                  ,"sName": "date_serie"                         ,"sWidth": "5%"   , "bSortable": true    }, 
									 
										  { "sTitle": "${num_lot}"                ,"sName": "pk.num_serie"                       ,"sWidth": "10%"   , "bSortable": true   },
										  
										  { "sTitle": "is_readonly"                ,"sName": "is_readonly"                       ,"sWidth": "10%"   , "bSortable": true ,"bVisible": false    },
										  
										  { "sTitle": "P.U.A"                     ,"sName": "tarif.tarif_unit_article"       ,"sClass" : "alignRight"    ,"sWidth": "25%"  ,"sClass" : "alignRight" 
											          , "mRender": function (data, type, full) {return formatNumberJs(data,3);}     },
											               
										  
										  { "sTitle": "Prix.U.V"                  ,"sName": "vente.tarif_unit_vente"          ,"sClass" : "alignRight"       ,"sWidth": "10%"   , "bSortable": true ,
										      "mRender": function (data, type, full) {return formatNumberJs(data,3);}     },
										  
										   
										  { "sTitle": "Nouvelle.Prix"             ,"sName": "nvente.tarif_unit_vente"                        ,"sWidth": "10%"      , "mRender": function( data, type, full){
										   
										     var heydd=formatNumberJs(data,3);
									         return  '<input  type="montant3"     value="'+heydd+'"   id=nvente.tarif_unit_vente'+full[3]+'      '+full[4]+'       name=nvente.tarif_unit_vente      onblur=doEnvoiDataV2(this,"'+full[3]+'")       >';}},
										  
										  
										  { "sTitle": "Qte.disp"                  ,"sName": "quantite"                           ,"sWidth": "5%"   , "bSortable": true   },
										  
										  
										  { "sTitle": "Ordre"                     ,"sName": "serie_ordre"                        ,"sWidth": "7%"  , "mRender": function( data, type, full){
									              return  '<input  type="number"     value="'+data+'"   id=serie_ordre'+full[3]+'   style="width:50px;"       name=serie_ordre       onblur=doEnvoiDataV2(this,"'+full[3]+'")          >';}},
										   
										  { "sTitle": "Mode"                      ,"sName": "etat.data_libelle"                  ,"sWidth": "5%"   , "bSortable": true   } ,
										           
										  {  "sTitle": "Bloquée"                 , "sName": "serie_bloque"     ,"sWidth": "2%"   ,"bSortable": true    }
										  						  
										     //{  "sTitle": "Bloquée"  , "sName": "serie_bloque"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
										     //             var chekio = data=="true"?"checked":"";
									         //    return  '<input   isboolean   type="checkbox"     '+chekio+'    value="'+data+'"   id=serie_bloque'+full[3]+'    name=serie_bloque      onclick=doEnvoiDataV2(this,"'+full[3]+'")          >';}},
	                                         ]
 
                               };



    
 


function getSuiv(){
if(!teste_required()) return ;

 var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_LOT_ARTICLE','json',false);

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

$('input[id="depot_id"]').autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});
 $("#depot_libelle").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});   
$('input[id="depot_id"]').attr("readonly","readonly");
$("#depot_libelle").attr("readonly","readonly");
$("#type_trf_idX").attr("readonly","readonly");



Ext.getCmp('btnnext').disable(); 
Ext.getCmp('btValidx').enable(); 
Ext.get('GRID_LOT_CHOIX').setStyle('display', 'block');

 
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
var choixModePrix=json.choixModePrix;
var choix_Lot=json.choix_Lot;		 
loadSelectAjax("mode_choix_prix_venteX","list_mode_prix","data_id","data_libelle",choixModePrix,false);  
loadSelectAjax("mode_choix_lotx","list_choix_lotx","data_id","data_libelle",choix_Lot,false);     
}



</script>
 
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 
 
 
 <ext:toolbar         toolbarType="bbar"     > 
        <ext:toolbar.button  text=" Suivant  >> "          onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
 
 <table  width="100%"><tr><td  width="50%">
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"    width="100%"     >  
		   
		   
		  
		     <tr>  
		   <td  ><label>Article</label></td>  
		   <td   >  
		   
		  <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="15"           value="${detailBean.fkCode_barre.pk.code_barre}"        required    autofocus    />
<input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="30"              value="${detailBean.fkCode_barre.designation_libelle}"       required            />		  </td>  
		   </tr>  
		   
		  
		   
		 
		   <tr>  
		   <td    ><label>${type_trf_id}</label></td>  
   <td      >
   <select   id="type_trf_idX" name="vente.groupe.type_trf_id"        required      libre      nextElement="coef_trf"           ></select>  
  </td>  
		   </tr>   
		  
		 </table>
	   </td><td  width="50%"  >	 
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  width="100%"     >  
		    
		    
		   
		   
		   <tr>  
		   <td ><label>depot</label></td>  
		   <td   > 
		   <input id="depot_id" name="pk.depot.depot_id"     type="text"    size="10"             value="${detailBean.pk.depot.depot_id}"     required  />
		   <input id="depot_libelle" name="pk.depot.depot_libelle"     type="text"    size="30"             value="${detailBean.pk.depot.depot_libelle}"      required   />
		  </td>  
		   </tr>   
		  
		    <tr>  
		   <td  ><label>date serie</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_serie}"   var="detailBeandateisatdion"/> 
		   <input id="date_serie" name="date_serie"     type="text"    size="10"            value="${detailBeandateisatdion}"             />  
		  </td>  
		   </tr>   
		
		      
		 </table>
	</td></tr>
</table>
 
</ext:panel>
<ext:panel    title="Liste des Lots"  id="GRID_LOT_CHOIX"   border="false"   bodyStyle="background: none;"     style="display:none;"   >
	<table  class="tableStyleContent"  cellpadding="5" cellspacing="5"    >
		<tr>
		  <td > 
		        <label>Nature Prix de vente</label> </td>
		        <td > 
		           <select  id="mode_choix_prix_venteX"   name="fkCode_barre.pk.ar_bean.mode.data_id"            style="width: 200px;"               ></select>   
		        </td>
		        <td ><label>Choix du lot</label></td>
		        <td > 
		        <select  id="mode_choix_lotx"    name="fkCode_barre.pk.ar_bean.choix.data_id"            style="width: 200px;"             ></select>   
		        </td>
		       
		</tr>
	</table>
	        
  <table id="GRID_LOT" class="display" width="100%"     ></table>
 </ext:panel>
</ext:panel>
</ext:body>
