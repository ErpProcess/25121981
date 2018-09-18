 <%@include file="/Aceuil/esProcess.jsp" %>  
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 
 <script type="text/javascript">
 contenu_toolbarJQuey="";
 height_tabbJQuey="auto";
 mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_LOT",
                               "list"   :"list_des_lots_for_select",
                               "id_name":"pk.num_serie",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" : [ 
									                  
										  { "sTitle": "date lot"                  ,"sName": "date_serie"                         ,"sWidth": "5%"   , "bSortable": "true"    }, 
									 
										  { "sTitle": "${num_lot}"                ,"sName": "pk.num_serie"                       ,"sWidth": "10%"   , "bSortable": "true"   },
										  
										  { "sTitle": "P.U.A"                     ,"sName": "tarif.tarif_unit_article"       ,"sClass" : "alignRight"    ,"sWidth": "25%"  ,"sClass" : "alignRight" 
											          , "mRender": function (data, type, full) {return formatNumberJs(data,3);}     },
											               
										  
										  { "sTitle": "Prix.U.V"                  ,"sName": "vente.tarif_unit_vente"          ,"sClass" : "alignRight"       ,"sWidth": "10%"   , "bSortable": "true" ,
										      "mRender": function (data, type, full) {return formatNumberJs(data,3);}     },
										      
										      
										      
										   { "sTitle": "Nouvelle.Prix"                   ,"sName": "nvente.tarif_unit_vente"          ,"sClass" : "alignRight"       ,"sWidth": "10%"   , "bSortable": "true" ,
										      "mRender": function (data, type, full) {return formatNumberJs(data,3);}     },     
										  
										   
										 
										  
										  
										  { "sTitle": "Qte.disp"                  ,"sName": "quantite"                           ,"sWidth": "5%"   , "bSortable": "true"   },
										  
										  
										  { "sTitle": "Ordre"                     ,"sName": "serie_ordre"                        ,"sWidth": "7%"  , "bSortable": "true"  },
										  
										   
										  { "sTitle": "Mode"                      ,"sName": "etat.data_libelle"                  ,"sWidth": "5%"   , "bSortable": "true"   } ,
										           
										   
	                                         ]
 
                               };
    
 
$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","depot_id","list_des_articles","600","250");
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","xxx","list_des_depot","600","250");
loadSelectAjax("type_trf_idX","list_tyList","type_trf_id","type_trf_lib","${searchBean.vente.groupe.type_trf_id}",false);

    $('#'+mapEditableGen["table"]+' tbody tr').live('dblclick', function () {
       
		      var aData = otab_otra.fnGetData( this );
		      var iId = aData[0];
		       
		       if ( jQuery.inArray(iId, gaiSelected) == -1 ){
			        gaiSelected[gaiSelected.length++] = iId;
		         }else{
			        gaiSelected = jQuery.grep(gaiSelected, function(value) {
				    return value != iId;} );
		         }
		        
		   var indexRowSel = otab_otra.fnGetPosition( this );
		   
		   $(this).toggleClass('row_selected');
		     if(!doubleclickGrid) return;
		   
		   if("${bs.fct_id}"=="34"){
		   getDetailRowFromServer(indexRowSel);
		   }
} );

 
});


 
function Annuler(){

if(!teste_required()) return ;
var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_SUPP_CHOIX_LOT','json',false);
}




function ControlDisplayTableE(){

 if(!teste_required()) return ;
var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_GET_LOT_ARTICLE','json',false);
var choixModePrix=json.choixModePrix;
var choix_Lot=json.choix_Lot;		 
loadSelectAjax("mode_choix_prix_venteX","list_mode_prix","data_id","data_libelle",choixModePrix,false);  
loadSelectAjax("mode_choix_lotx","list_choix_lotx","data_id","data_libelle",choix_Lot,false); 
Ext.getCmp('btValidx').disable(); 
Ext.get('RET_GRID').setStyle('display', 'block');
if(json.ret=="4")
Ext.get('btnnext').setStyle('display', 'block');

LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
 
}



</script>
 
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 
 
 
 
 
 <table  width="100%"><tr><td  width="50%">
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"    width="100%"     >  
		   
		   
		  
		     <tr>  
		   <td  ><label>Article</label></td>  
		   <td   >  
		  <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="15"                value="${searchBean.fkCode_barre.pk.code_barre}"        required    autofocus    />
<input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="30"              value="${searchBean.fkCode_barre.designation_libelle}"       required            />		  </td>  
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
		   <input id="depot_id" name="pk.depot.depot_id"     type="text"    size="10"             value="${searchBean.pk.depot.depot_id}"     required  />
		   <input id="depot_libelle" name="pk.depot.depot_libelle"     type="text"    size="30"             value="${searchBean.pk.depot.depot_libelle}"      required   />
		  </td>  
		   </tr>   
		  
		    <tr>  
		   <td  ><label>date serie</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${searchBean.date_serie}"   var="searchBeandateisatdion"/> 
		   <input id="date_serie" name="date_serie"     type="text"    size="10"            value="${searchBeandateisatdion}"              />  
		  </td>  
		   </tr>   
		
		      
		 </table>
	</td></tr>
</table>
 
</ext:panel>


        
        
<ext:panel    title="Liste des Lots"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   >

    <table  class="tableStyleContent"  cellpadding="5" cellspacing="5"    >
		<tr>
		  <td > 
		        <label>Nature Prix de vente</label> </td>
		        <td > 
		          <select  id="mode_choix_prix_venteX"   name="fkCode_barre.pk.ar_bean.mode.data_id"   readonly  style="width: 200px;" ></select>   
		        </td>
		        <td ><label>Choix du lot</label></td>
		        <td > 
		        <select  id="mode_choix_lotx"             name="fkCode_barre.pk.ar_bean.choix.data_id"  readonly  style="width: 200px;" ></select>   
		        </td>
		       
		</tr>	      
	</table>
	
  <table id="GRID_LOT" class="display" width="100%"     ></table>
 </ext:panel>
 <ext:toolbar         toolbarType="bbar"    id="btnnext"  style="float:right;display:none;"    > 
 <ext:toolbar.button  text="Supprimer"          onClick="Annuler()"        ></ext:toolbar.button> </ext:toolbar>
 
    
</ext:panel>
</ext:body>
