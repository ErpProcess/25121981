<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
Ext.onReady(function(){
Ext.getCmp('btValidx').disable();
		});
		
var mapEditableGen_Vente;
var oTableVente;
$(document).ready(function (){
    
    LoadAutoCompletAjax("pk_article.ar_id","ar_libelle",null,"list_article_tarification");
    LoadOtherAutocompletesAjax("pk_article.ar_id","i$_ACT_LOAD_COD_BARRE","pk.code_barre","designation_libelle","type_trf_id");
});

function doEnvoiDataV3(element,value_id_de_la_ligne){

	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"":"checked";
	var idToSendo=value_id_de_la_ligne ;
	var LEvalue=element.value;
	var name_column=element.name;
	
	
	
	
 
    var hashmap ={"prix_unit_achat":$('#prix_unit_achat').val(),
                  "HiddenAction":   "i$_ACT_UPDATE_EDITABLE",
                  "sNameId":        mapEditableGen_Vente["id_name"], 
                  "sValueId":       idToSendo,
                  "sDataValue":     LEvalue,
                  "sNameColumn" :   name_column,
                  "sNameList":      mapEditableGen_Vente["list"]};
                  
	jQuery.ajax({ 
	     type: 'POST',  
	     url: '${tmlx.urlAjax}', 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){  
	     
	     LoadDataEditableFromServer_toolbar( mapEditableGen_Vente  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
         config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
  
	      },
	    
         error: function (request, status, error) {
           fn_display_er_ajax(request);
         } 
         
      });      
}
var   nature_coef="";
var   nature_marge="";
var   nature_prix_vente="";

var fn_response       =function(data_Return) {  

  $('#date_trf').val(data_Return.datex);
  $('#tva_id').val(data_Return.tvax);
  $('#prix_unit_achat').val(data_Return.p_u_achat); 
  $('#modex').val(data_Return.modex); 
  
  if(data_Return.mode_ids=="c") { nature_coef=""; nature_marge="readonly"; nature_prix_vente="readonly";}
  if(data_Return.mode_ids=="m") { nature_coef="readonly"; nature_marge=""; nature_prix_vente="readonly";}
  if(data_Return.mode_ids=="s") { nature_coef="readonly"; nature_marge="readonly"; nature_prix_vente="";}
  
  
  config_header_foot_tableJQuey='rt<"bottom"flp><"clear">';
  contenu_toolbarJQuey="";
  var  ooodsdsds="100px";
  var  width_tabbJQuey2="100%";
  
  LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , ooodsdsds  , width_tabbJQuey2  , 
  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
  
  LoadDataEditableFromServer_toolbar( mapEditableGen_Vente  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
};

function getHistorique(){

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
doGenerate_methode_ajax('POST','${tmlx.urlAjax}','i$_ACT_LOAD_SUIVANT'  ,'json',true,fn_response); 
Ext.getCmp('btValidx').enable();
Ext.getCmp('TosuivTarif').disable();
  }



}
</script>
  <ext:body  >
    
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"      style="margin:5px 5px 5px 5px;"   >  
  
   
   
   
    
   
   
	      <ext:panel  border="true"    bodyStyle="background: none;"       title="Selection"   height="185"   style="margin:5px 5px 5px 5px;width:46%;"   >
	        <ext:toolbar      toolbarType="bbar"  xtype="tbfill"    >  
	        <ext:toolbar.button   id="TosuivTarif"  text=" Suivant [dernier date de tarification]"   onClick="getHistorique()"    > </ext:toolbar.button>    
	        </ext:toolbar>
		   <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   > 
		   
		    
			   <tr>  
	            <td width="17%"><label>${ar_id} </label></td>  
			    <td width="83%" >
				   <table>
				    <tr>
				     <td ><input id="pk_article.ar_id" name="fkCode_barre.pk.ar_bean.pk_article.ar_id"     type="text"    size="10"       maxlength="25"        value="${detailBean.fkCode_barre.pk.ar_bean.pk_article.ar_id}"    nextElement="pk.code_barre"             /></td> 
				     <td><input  id="ar_libelle"       name="fkCode_barre.pk.ar_bean.ar_libelle"           type="text"    size="25"       maxlength="25"        value="${detailBean.fkCode_barre.pk.ar_bean.ar_libelle}"    nextElement="pk.code_barre"               /></td>   
				    </tr>    
				  </table>  
			   </td> 
	          </tr>
	          
	          
	          <tr>  
	            <td width="17%"><label>Méthode prix</label></td>  
			    <td width="83%" >
				   <table>
				    <tr>
				     <td ><input id="methode_prix" name="methode_prix"     type="checkbox"    value="X"             /></td> 
				     <td>Appliquer le meme Prix d'article sur toutes les code a Barres </td>   
				    </tr>    
				  </table>  
			   </td> 
	          </tr>
	          
	          
	          <tr> 
			    <td  ><label>${code_barre}</label></td>  
			    <td     >  
			    <table><tr> <td>
			    <input id="pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="10"       maxlength="100"        value="${detailBean.fkCode_barre.pk.code_barre}"    nextElement="TosuivTarif"            required  />
			    </td>
			    <td>
			    <input id="designation_libelle" name="fkCode_barre.designation_libelle"     type="text"    size="45"       maxlength="100"        value="${detailBean.fkCode_barre.designation_libelle}"    nextElement="TosuivTarif"          required    />
			    </td> </tr> </table>  
			   </td>  
	          </tr>   
		   </table>  
	    </ext:panel>
		 <ext:panel  title="Historique Tarification"     bodyStyle="background: none;"  border="true"  height="185"   style="margin:-189px 5px 5px 49%;width:48%;padding-right:5%;"  > 
	       <script type="text/javascript">
               mapEditableGen= {"otab"   :oTable,
                               "table"  :"grid_dernier_tarif",
                               "list"   :"listmaxTarification",
                               "id_name":"indx_row",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
                                        {"sName": "groupe.type_trf_lib"      ,"sWidth": "10%"   , "bSortable": "true"   },
									    {"sName": "date_trf"      ,"sWidth": "10%"   , "bSortable": "true"   },  
									    {"sName": "cout.tarif_unit_article"  ,"sWidth": "30%"   , "bSortable": "true"   },  
									    {"sName": "coef_trf"         ,"sWidth": "10%"   , "bSortable": "true"   },  
									    {"sName": "tarif_unit_vente"  ,"sWidth": "30%"   , "bSortable": "true"   },  
									    {"sName": "taux_remise"      ,"sWidth": "10%"   , "bSortable": "true"   },  
									    {"sName": "tvaBean.tva_id"  ,"sWidth": "10%"   , "bSortable": "true"   },
									    ]
                               };
			 </script> 
			 <table id="grid_dernier_tarif" class="display" width="100%" >
				 <thead>
				     <tr> 
				        <th>Type</th> 
						<th>${date_trf}</th>
						<th>${prix_unit_achat}</th>
						<th>${coef_trf}</th>
						<th>${prix_unit_vente}</th>
						<th>${taux_remise}</th>
						<th>${tva_id}</th>
				    </tr>
				</thead>
			 </table>
		</ext:panel>
		
		<ext:panel title="Editer Tarification Achat"   style="margin:5px 5px 5px 5px;width:46%;"   height="185"  bodyStyle="background: none;" border="true"  >
			<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  border="0"  >
			 <tr>
			   <td  width="30%" > <label>${date_trf}</label>  </td>
			   <td  width="70%" >
			    <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_trf}"   var="WW"/>
	            <input id="date_trf" name="date_trf"   type="datepicker"    size="13"       maxlength="13"       value="${WW} "    nextElement="code_barre"       required     />
			   </td>
			</tr>
			<tr>
				<td ><label>${tva_id}</label></td>  
				 <td > 
					   <script type="text/javascript">
						    $(document).ready(function() {
						     loadSelectAjax("tva_id","list_tvList","tva_id","tva_libelle","${detailBean.tvaBean.tva_id}",true);
						    });
					    </script> 
				   <select   id="tva_id" name="tvaBean.tva_id"           libre      nextElement="prix_unit_achat"           ></select> 
				   
				   
				  </td>  
			 </tr>
			 <tr>  
					   <td><label>${prix_unit_achat}</label></td>  
					   <td >  
					   <input id="prix_unit_achat" name="cout.tarif_unit_article"   class="money2"   type="montant"    size="17"     
					     maxlength="17"        value="${detailBean.cout.tarif_unit_article}"    nextElement="modex"    
					        onblur="doEnvoiDataV3(this,'UIJ987654ù$$$')"        />  
					        
					   </td>
			  </tr>
			  
			  <tr>  
				       <td><label>${mode_calcul}</label></td>  
					   <td >  
					   <input id="modex"   name="modex"      type="text"    size="17"     maxlength="17"        value=""    libre     readonly="readonly"           />  
					   </td>
			  </tr>
			  
			  
				
				
		   </table>
	   </ext:panel> 
	   <ext:panel  title="Editer Tarification Vente Par Cathégorie" border="true"  bodyStyle="background: none;"  height="185"  style="margin:-189px 5px 5px 49%;width:48%;"  >
	   		 <script type="text/javascript">
	   		    
 
 

               mapEditableGen_Vente= {"otab"   :oTableVente,
                               "table"  :"grid_Vente",
                               "list"   :"listmaxTarific",
                               "id_name":"groupe.type_trf_id",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
                                        { "sTitle": "sw" ,   "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false },
                                         
                                        {  "sTitle": "X" ,     "sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true  , "bVisible": false     , "mRender": function( data, type, full){
                                              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV3(this,"'+full[2]+'")           >';}},
                                              
                                        { "sTitle": "sw" , "sName": "groupe.type_trf_id"       ,"sWidth": "10%"   , "bSortable": "true" ,"bVisible": false   }, 
                                        
                                        { "sTitle": "Type" , "sName": "groupe.type_trf_lib"       ,"sWidth": "10%"   , "bSortable": "true"   }, 
                                        
									    { "sTitle": "${coef_trf}" , "sName": "coef_trf"         ,"sWidth": "10%"   , "bSortable": "true"  ,"mRender": function( data, type, full){  var DTy=formatNumberJs(data,3); 
	                                           return '<input   type="text"  size="7"  style="width:60px;"    id=coef_trf'+full[2]+'      name=coef_trf    '+nature_coef+'    value="'+DTy+'"     onblur=doEnvoiDataV3(this,"'+full[2]+'")   nextElement=marge_vente'+full[2]+'  >'; }},
	                                           
	                                     { "sTitle": "${marge_vente}" , "sName": "marge_vente"         ,"sWidth": "10%"   , "bSortable": "true"  ,"mRender": function( data, type, full){  var DTy=formatNumberJs(data,3); 
	                                           return '<input   type="text"  size="7"          id=marge_vente'+full[2]+'      name=marge_vente    '+nature_marge+'      value="'+DTy+'"     onblur=doEnvoiDataV3(this,"'+full[2]+'")   nextElement=tarif_unit_vente'+full[2]+'  >'; }},       
	                                              
									    { "sTitle": "${prix_unit_vente}" , "sName": "tarif_unit_vente"  ,"sWidth": "30%"   , "bSortable": "true"  ,"mRender": function( data, type, full){  var DTy=formatNumberJs(data,3); 
	                                        return '<input   type="text"  size="7"           id=tarif_unit_vente'+full[2]+'   '+nature_prix_vente+'    name=tarif_unit_vente      value="'+DTy+'"     onblur=doEnvoiDataV3(this,"'+full[2]+'")   nextElement=taux_remise'+full[2]+'  >'; }},
	                                        
	                                          
									    { "sTitle": "${taux_remise}" , "sName": "taux_remise"      ,"sWidth": "10%"   , "bSortable": "true"   ,"mRender": function( data, type, full){  
	                                        return '<input   type="number"  size="7"   style="width:40px;"    id=taux_remise'+full[2]+'      name=taux_remise        value="'+data+'"     onblur=doEnvoiDataV3(this,"'+full[2]+'")      >'; }},  
									    ]
                               };
			 </script> 
			 <table id="grid_Vente" class="display" width="100%" ></table>
		</ext:panel>
	
</ext:panel>

</ext:body>
       
