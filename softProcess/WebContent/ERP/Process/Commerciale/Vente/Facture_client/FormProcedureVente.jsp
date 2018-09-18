<%@include  file="/Aceuil/esProcess.jsp"  %> 
<c:import   url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
var ji_oooo="${urlloadDataTableAjax}";
var ae;
 
$(document).find("input[idonly]").attr("readonly","readonly");	 
$(document).find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly","readonly");

function doExcuteFnAfterGridX( ){

        var r_json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_METHODE','json',false);
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var code_list = $(this).find('td:eq(0)').html() ;
            //var QteNew ="Qte"+qsdqsqd;
            //var erreurX="erreur"+qsdqsqd;
	      $(this).find('td:eq(5)').html('  <a  href=javascript:migaX("'+code_list+'")   >'+r_json[code_list]+'</a> ') ;
	      //$(this).find('td:eq(4)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	   });
 }  
 
function migaX(dssss) {

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



contenu_toolbarJQuey = "";
width_tabbJQuey      = "100%";
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_VENTE",
                               "list"   :"list_editable_proVente",
                               "id_name":"pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true    ,"bVisible": false , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[0]+'")       nextElement="pk.code_barre'+full[0]+'"   >';}}, 
									                  
										   {      "sName": "pk.fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "pk.fkcode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										    
										   {      "sName": "quantite_demander"                    , "sWidth": "5%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },           
										                 
										   {      "sName": "quantite"                             , "sWidth": "5%"     ,"bSearchable": true  , "bSortable": true   },   
										  
										   {      "sName": "pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "10%"     ,"bSearchable": true },
										   
										    
										   {      "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  },       
										           
										   {      "sTitle":"Prix U"    , "sName": "tarif.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },
										    
										           
	                                            ]
 
                 };
    
  
                           
$(document).ready(function (){
height_tabbJQuey="auto";
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});
 													                        
 </script>
   
 
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"        >
   
   <table class="tableStyleContent"  cellpadding="1" cellspacing="1"  width="100%"    >
   <tr>

   
    <td  width="50%" >
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblDcccata"   width="100%"  >  
		   <tr>  
		   <td width="20%"><label>${cmd_id} <p><a href="" id="dialog-link"  style="display: none;">Click here for lightbox dialog</a></p></label></td>  
		   <td width="80%"  >  
		   <input id="cmd_id"  name="commande.cmd_id"     type="text"    size="15"       maxlength="15"     libre  readonly="readonly"        value="${detailBean.commande.cmd_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${cmd_date}</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.commande.cmd_date}"    var="detailBeancmd_date"/>
		   <input id="cmd_date" name="commande.cmd_date"     type="text"    size="15"       maxlength="15"         value="${detailBeancmd_date}"     libre  readonly="readonly"              />  
		  </td>  
		   </tr>  
		   
		   <tr>  
		   <td width="7%">
		   
		   
		 
		   
		   <label>
		   
		   
		   ${clt_id}</label></td>  
		   <td width="93%"  >  
		    <input id="clt_id" name="client.clt_id"       libre    readonly="readonly"      type="text"     size="10"             value="${detailBean.client.clt_id}"         nextElement="depot_id"   required    /> 
			<input id="clt_lib" name="client.clt_lib"     libre    readonly="readonly"      type="text"     size="30"             value="${detailBean.client.clt_lib}"          nextElement="depot_id"   required    />
		  </td>  
		   </tr> 
		 
		   <tr>  
		   <td  ><label>${cmd_libelle}</label></td>  
		   <td    >
		<input id="vente_libelle" name="vente_libelle"      libre    readonly="readonly"        type="text"     size="50"             value="${detailBean.vente_libelle}"         nextElement="depot_id"        /> 
		  </td>  
		   </tr>   
		    
		    <tr>  
		   <td  ><label></label></td>  
		   <td  >
		        
		  </td>  
		   </tr>   
		    
		   
		 </table> 
    
    
    
    
    </td>
    
    
    
        <td width="50%">
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >  
		   <tr>  
		   <td width="20%"><label>${vente_id}</label></td>  
		   <td width="80%"  >  
		 
		   <input id="vente_id" name="vente_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.vente_id}"    nextElement="vente_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${vente_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.vente_date}"   var="detailavente_date"/> 
		   <input id="vente_date" name="vente_date"     type="text"    size="13"       maxlength="13"        value="${detailavente_date}"    nextElement="depot_id"       libre    readonly="readonly"        />  
		  </td>  
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td    >  
					   <input id="depot_id" name="depot.depot_id"     libre    readonly="readonly"   type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="vente_obs"                  required    />  
					   <input id="depot_libelle" name="depot.depot_libelle"   libre    readonly="readonly"     type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="vente_obs"   required     />
		 
		  </td>  
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${cmd_obs}</label></td>  
		   <td   >  
		   <input id="vente_obs" name="vente_obs"     type="text"    size="45"       libre          maxlength="50"        value="${detailBean.vente_obs}"    nextElement="btValidx"              />  
		  </td>  
		   </tr>   
		   
		     <tr>  
		   <td  ><label>${avance_montant_vente}</label></td>  
		   <td   >  
		   <input id="avance_montant_vente" name="avance_montant_vente"       libre    readonly="readonly"    type="montant3"    size="45"        maxlength="50"        value="${detailBean.avance_montant_vente}"    nextElement="btValidx"              />  
		  </td>  
		   </tr>   
		    
		      <tr>  
		   <td  ><label>Remise</label></td>  
		   <td   >  
		   <input id="vente_remise" name="vente_remise"       libre    readonly="readonly"    type="montant3"    size="45"        maxlength="50"        value="${detailBean.vente_remise}"    nextElement="btValidx"              />  
		  </td>  
		   </tr>   
		   
		     
		 </table> 
    
    
    
     </td>
   </tr>
   
   </table>
     
		
	 </ext:panel>
	 
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille vente"     > 
	          
	        
			    <table id="GRID_SAISIE_DETAIL_VENTE" class="display" width="100%" >
			    
			      <thead   >
					 
					 <tr> 
						<th></th>
						<th></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>Qté.DEM</th>
						<th>Qté.SRV</th>
						<th>Unite</th>
						<th>T.V.A</th>
						<th>P.U.V</th>
						<th>T.H.T</th>
						<th></th>
				    </tr>
				 </thead>
				 
				 
			    
			    </table>
			    
			     
               
	         </ext:panel>
 
		 



</ext:panel>
</ext:body>
<script type="text/javascript">
$(document).ready(function() {
  		
		// Link to open the dialog
		$("#dialog-link").click( function(event) {
			$("#dialog").dialog("open");
			event.preventDefault();
		});
		
		// initialize the dialog
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,
		 
			 
			width        :1200,
        autoShow     :true, 
        height       :500,
        maximizable  :true,
        minimizable  :true,
        autoScroll   :true,
        maxHeight    :500,
        border       :true,
        closeAction  :'hide',
        modal        :true,
			buttons: [
				{
					text: "Close",
					click: function() {
						$(this).dialog("close");
					}
				}
			]
		});

  });
  
 

 </script>
<div id="dialog" title="Basic dialog"  style="padding: 0;"></div>
<c:import url="${context_path}/WindowSpoor/XXilterWindowSpoor.jsp"></c:import>


