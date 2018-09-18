<script >
contenu_toolbarJQuey = "";
var oTableBb;
width_tabbJQuey      = "100%";
var sss_mapEditableGen = {     "otab"   :oTableBb,
                               "table"  :"list_serie_article_S",
                               "list"   :"list_serie_proVente",
                               "id_name":"pk.num_serie",
                               "url"    :ji_oooo,
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {    "sTitle":"Prix U" ,   "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {    "sTitle":"source" ,  "sName": "nature_mvt.nature_mvt_libelle"                    ,"sWidth": "10%" ,"bSearchable": true },
									       
									       {    "sTitle":"Réf.src" ,  "sName": "mvt_com_id"                    ,"sWidth": "10%" ,"bSearchable": true },
									       
									       
									       {    "sTitle":"date Lot" ,  "sName": "date_serie"   ,"sWidth": "10%"    },
									       
									       
									       {    "sTitle":"Lot"      ,  "sName": "pk.num_serie"   ,"sWidth": "10%"    },
									       
									                  
										   {    "sTitle":"réference" ,  "sName": "fkCode_barre.pk.code_barre"   ,"sWidth": "10%",  "bVisible": false   }, 
										   
										         
										    {   "sTitle":"Désignation" ,   "sName": "fkCode_barre.designation_libelle"    ,"sWidth": "30%" ,"bVisible": false  }, 
										   
										   
										   {   "sTitle":"Qte dispo" ,   "sName": "quantite"    ,"sWidth": "10%"   },  
										        
										          
										   {   "sTitle":"Qte choisie" ,   "sName": "quantite_choisi"   ,"sWidth": "10%"    },
										  
										   {    "sTitle":"Unite" ,  "sName": "fkCode_barre.pk.ar_bean.unitBean.unite_lib"                     ,"sWidth": "10%" ,"bSearchable": true },
										 
										    
	                                            ]
 
                 };
    
function doEnvoiDataV3(element,value_id_de_la_ligne){


 
	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"":"checked";
	var idToSendo=value_id_de_la_ligne ;
	
	
	
	var LEvalue=element.value;
	var name_column=element.name;
	
		  
 
    var hashmap ={"sNameId":sss_mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":sss_mapEditableGen["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL_def, 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){ 
	   if (typeof doExcuteFnAfterGrid !== 'undefined' && typeof doExcuteFnAfterGrid === 'function' 
	           &&  ( $(element).attr('type')!=undefined  &&   $(element).attr('type')!="checkbox"   ) )    doExcuteFnAfterGrid( data );
	       
	       }
      });      
}  
                           
$(document).ready(function (){
   $('#A').html('<label>'+ae.depot+'</label>');
   $('#B').html(ae.venteId);
   $('#C').html(ae.ref);
   $('#D').html(ae.lib);
   $('#E').html('<label>'+ae.Qte+'</label>');
var ssheight_tabbJQuey="auto";
LoadDataEditableGridWindow( sss_mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , ssheight_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});
</script> 
<ext:body>
<ext:panel     bodyStyle="background: none;"    border="false"      title="Traitement vente"      renderTo="sxcc"      >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >  <tR><td>
<table width="100%" style="background-color: white;">
 <tr>  
   <Td width="80px"><label > Magazin N°:    </label>    </Td> <td width="150px"> <div id="A"></div> </td>
   <Td width="70px"><label > Vente N°:</label> </Td> <td  width="150px"  ><div id="B"></div></Td>   
   <Td  width="50px" > <label>Réf: </label> </Td> <td><div id="C"></div></Td>  
   <Td width="50px"><label>Article:</label> </Td> <td     > <div id="D"></div> </Td>  
   <Td  width="200px" ><label>Quantité à servir :</label>    </Td>  <td><div id="E"></div></td>
  </tr>
</table>
<bR>
<table id="list_serie_article_S" class="display" width="100%" > </table>
<tR><td>
</table>
</ext:panel>
</ext:body>
<div  id="sxcc" ></div>



 
	
	
	 
	
	 
	
	
	 
	
 










