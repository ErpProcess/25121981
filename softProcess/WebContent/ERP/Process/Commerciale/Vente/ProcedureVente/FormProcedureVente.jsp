<%@include  file="/Aceuil/esProcess.jsp"  %> 
<c:import   url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<c:import url="EditablePrestation.jsp"></c:import>
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
										         
										   {      "sName": "info"    ,"sWidth": "25%"  ,"bSearchable": true  , "bSortable": true ,"bVisible": true  },       
										    
										   {      "sName": "quantite_demander"                    , "sWidth": "5%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },           
										                 
										   {      "sName": "quantite"                             , "sWidth": "5%"     ,"bSearchable": true  , "bSortable": true   },   
										  
										   {      "sName": "pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "5%"     ,"bSearchable": true },
										   
										  // {      "sTitle":"Méthode"    , "sName": "methode_s"   ,"sWidth": "5%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                       //       , "mRender": function (data, type, full) {  return '<a  href=javascript:migaX("'+full[2]+'")   >'+data+'</a>';}  ,"bVisible": true   },     
										    
										   {        "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	    										},       
										           
										   {      "sTitle":"Prix U"    , "sName": "tarif.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender":    function (data, type, full) {   if( "${detailBean.devise.dev_id}"=="191"  ||  "${detailBean.devise.dev_id}"=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },     
	                                    
	                                       {      "sTitle":"Remise"     , "sName": "tarif.taux_remise"     ,"sWidth": "5%"    ,"sClass" : "alignCenter"    , "bSortable": "true"    
	                                              , "mRender": function (data, type, full) {  return addPourcentage(data);}  ,"bVisible": true    },
	                                              
	                                              
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "15%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                               , "mRender":    function (data, type, full) {   if( "${detailBean.devise.dev_id}"=="191"  ||  "${detailBean.devise.dev_id}"=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },
	                                       
	                                       {      "sTitle":"Total T T" , "sName": "montant_ttc_vente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              , "mRender":    function (data, type, full) {   if( "${detailBean.devise.dev_id}"=="191"  ||  "${detailBean.devise.dev_id}"=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },                
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },
										    
										           
	                                            ]
 
                 };

var oTable23;
var oTable24;
var mapEditableGen2 = {        "otab"   :oTable23,
                               "table"  :"GRID_SAISIE_FOURNITURE_VENTE",
                               "list"   :"list_editable_fournitureVente",
                               "id_name":"fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
 									                  
										   {      "sName": "fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "fkcode_barre.designation_libelle"    ,"sWidth": "30%"   },   
										                 
										   {      "sName": "quantite"           ,   "bSortable": true       , "sWidth": "5%"       },   
										    
										   {      "sTitle":"TVA" , "sName": "tarifVente.tvaBean.tva_libelle"  ,"sClass" : "alignCenter"  ,"sWidth": "5%"      , "bSortable": true ,"bVisible": true  },           
										           
										   {      "sTitle":"Prix U"    , "sName": "tarifVente.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": true 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    }, 
	                                      
	                                       {      "sTitle":"Total TTC" , "sName": "montant_ttc_vente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },                
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
   
   <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"  >  
		   <tr>  
		   <td width="10%"><label>${vente_id}</label></td>  
		   <td  width="19%" >  
		   <input id="vente_id" name="vente_id"     type="text"    size="15"             value="${detailBean.vente_id}"    nextElement="vente_libelle"        libre   readonly="readonly"        />		  </td>  
		   <td  width="9%" ><label>${cmd_id}</label></td>
		   <td width="16%"  > 
		       <a href="" id="dialog-link"  style="display: none;">Click here for lightbox dialog</a>
		       <input id="cmd_id"  name="commande.cmd_id"     type="text"    size="15"           libre="libre"  readonly="readonly"        value="${detailBean.commande.cmd_id}"    nextelement="achat_libelle">
		        </td>
		   <td width="6%"  >&nbsp;</td>
		   <td width="14%"  ><label>Remise.caisse </label>
		   <input id="taux_remise_alacaisse" name="taux_remise_alacaisse"      style="width: 50px;"     type="number"    min="0"  max="100"          value="${detailBean.taux_remise_alacaisse}"    nextelement="designation_libelle"    />
		   <label>%</label>		   </td>
		   <td width="18%" align="right"    ><input id="vente_remise_alacaisse" name="vente_remise_alacaisse"   style="height: 30px;font-size: 18px;"     type="montant3"    size="17"    libre      readonly="readonly"       value="${detailBean.vente_remise_alacaisse}"   ></td>
		   </tr>   
		   
		   <tr>  
		   <td  ><label>${vente_date}</label></td>  
		   <td    >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.vente_date}"   var="detailavente_date"/> 
		   <input id="vente_date" name="vente_date"     type="datepicker"    size="13"    libre            maxlength="13"        value="${detailavente_date}"    nextElement="depot_id"     required       />		  </td>  
		   <td    ><label>${cmd_date}</label></td>
		   <td       >
		     <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.commande.cmd_date}"    var="detailBeancmd_date"/>
		     <input id="cmd_date" name="commande.cmd_date"     type="text"    size="15"       maxlength="15"         value="${detailBeancmd_date}"     libre="libre"  readonly="readonly">
		    </td>
		   <td ></td>
		   <td ><label>Total.remise </label></td>
		   <td align="right" ><input id="vente_remise" name="vente_remise"    style="height: 30px;font-size: 18px;"      type="montant3"    size="17"    libre      readonly="readonly"      value="${detailBean.vente_remise}" /></td>
		   </tr>   
		   
		    <tr>  
		   <td width="10%"><label>${clt_id}</label></td>  
		   <td colspan="2"  >  
		    <input id="clt_id" name="client.clt_id"           type="text"     size="10"             value="${detailBean.client.clt_id}"         nextElement="vente_libelle"   required    /> 
			<input id="clt_lib" name="client.clt_lib"        type="text"      size="30"             value="${detailBean.client.clt_lib}"        nextElement="vente_libelle"   required    />		  </td>  
		   <td  ><label> FIFO 
		       <input id="teste_fifo1"  name="teste_fifo"     type="radio"     size="50"      onchange="vidoff(this.value)"         value="fifo">
&nbsp; &nbsp; &nbsp;
		        LIFO
                <input id="teste_fifo2"  name="teste_fifo"     type="radio"     size="50"      onchange="vidoff(this.value)"         value="lifo">
                <script type="text/javascript">
			      $(document).ready(function (){
			      
			      if("${detailBean.fifo}"=="true"){
			      $('#teste_fifo1').attr('checked', true);
			      $('#teste_fifo2').removeAttr('checked');
			      }
			      
			      if("${detailBean.fifo}"=="false"){
			      $('#teste_fifo2').attr('checked', true);
			      $('#teste_fifo1').removeAttr('checked');
			      }
			      
			      });
		        function vidoff(this_value){
		        if(this_value=="fifo")  $('#fifo').val("true");
		        if(this_value=="lifo")  $('#fifo').val("false");
		        //alert($('#fifo').val());
		        }
		        </script>
                <input id="fifo"  name="fifo"      type="hidden"         value="${detailBean.fifo}">
		   </label></td>
		   <td  >&nbsp;</td>
	       <td  ><label>Total T.T.C</label> </td>
	       <td align="right"  ><input id="vente_mnt_total" name="vente_mnt_total"     type="montant3"    size="17"    libre  readonly="readonly"           value="${detailBean.vente_mnt_total}"    nextelement="designation_libelle"   style="height: 30px;font-size: 18px;" /></td>
	       </tr> 
		   
		    <tr>  
		   <td width="10%"><label>${vente_libelle}</label></td>  
		   <td colspan="2"  >  
		    <input id="vente_libelle" name="vente_libelle"           type="text"     size="45"             value="${detailBean.vente_libelle}"         nextElement="depot_id"   autofocus     />		  </td>  
		   <td  >&nbsp;</td>
		   <td  >&nbsp;</td>
	       <td  ><label>${avance_montant_vente}</label></td>
	       <td align="right"  ><input id="avance_montant_vente" name="avance_montant_vente"        type="montant3"    size="17"     maxlength="50"        value="${detailBean.avance_montant_vente}"   style="height: 30px;font-size: 18px;"     nextelement="vente_remise"      /></td>
	       </tr> 
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td colspan="2"    >  
					   <input id="depot_id" name="depot.depot_id"       type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="vente_obs"                  required    />  
					   <input id="depot_libelle" name="depot.depot_libelle"       type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="vente_obs"   required     />		  </td>  
		   <td    >&nbsp;</td>
		   <td    >&nbsp;</td>
		   <td    ><label> Net.a payer </label></td>
		   <td align="right"    ><input id="vente_mnt_net_a_payer" name="vente_mnt_net_a_payer"     type="montant3"    size="17"     libre  readonly="readonly"    maxlength="15"     style="height: 30px;font-size: 18px;"     value="${detailBean.vente_mnt_net_a_payer}"       /></td>
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${cmd_obs}</label></td>  
		   <td colspan="2"   >  
		   <input id="vente_obs" name="vente_obs"     type="text"    size="45"              value="${detailBean.vente_obs}"        nextelement="quantiteX"       />		  </td>  
		   <td   >
		   		</td>
		   <td   ></td>
		   <td   >  <label> Montant réçu</label></td>
		   <td align="right"   ><input id="montant_vente_recu" name="montant_vente_recu"     type="montant3"    style="height: 30px;font-size: 18px;"  size="17"           value="${detailBean.montant_vente_recu}"    nextelement="montant_vente_rendu"       /></td>
		   </tr> 
		   
		    <tr>  
		   <td  ><label>Devise</label></td>  
		   <td colspan="2"  >
<script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",true); })</script>
		        <select   required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select>

</td>  
		   <td   align="right">&nbsp;</td>
	       <td   >&nbsp;</td>
	       <td   ><label> Montant rendu </label> </td>
	       <td align="right"   ><input id="montant_vente_rendu" name="montant_vente_rendu"    libre  readonly="readonly"    value="${detailBean.montant_vente_rendu}"   type="montant3"  style="height: 30px;font-size: 18px;"   size="17"     nextelement="null"   /></td>
	       </tr> 
		 </table> 
     
		
    </ext:panel>
    
     <ext:tabPanel   border="true"    activeTab="RET_GRID"    id="sdsfgrgrgpll"  >
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"     border="false"      title="Détaille vente"    > 
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
						<th>Remise</th>
						<th>T.H.T</th>
						<th>Total T T</th>
						<th></th>
				    </tr>
				 </thead>
				 
				 
			    <tfoot>
				    <tr><td   height="50px" colspan="12" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
								<td ></td>
								<td ></td>
								 
								<td colspan="2"></td>
								 
								<td colspan="3" ></td>
								
								<td ></td>
								<td ></td> 
								<td ></td> 
								<td ></td>
								<td ></td> 
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="7">
		                    <tr align="right"> 
								<td colspan="4"></td>
								<td   ></td>
								<td colspan="4" ></td>
								
								<td ></td>
								<td ></td> 
								<td ></td>
								<td ></td> 
						    </tr>
					</c:forEach>	
				 </tfoot>
			    </table>
			    
			     <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					 
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var	listTva= json.list_tva ;
					    var	listTotal = json.list_total ;
					    
					    $('#vente_remise_alacaisse').val(json.vente_remise_alacaisse);
					    $('#vente_remise').val(json.vente_remise);
					    $('#vente_mnt_net_a_payer').val(json.vente_mnt_net_a_payer);
					    $('#vente_mnt_total').val(json.vente_mnt_total);
					    $('#montant_vente_recu').val(json.montant_vente_recu);
					    $('#montant_vente_rendu').val(json.montant_vente_rendu);
					     
					     var footX ={} ;
					     
					     for (var h = 0; h <listTva.length; h++) {
					     var foot ={} ;
						        if(h==0){
						         foot[listTva[h].td1] = listTva[h].value1;
						         footX["AA"+h]=foot;
						         continue;
				                }
				             	foot[listTva[h].td1] = listTva[h].value1;
				             	foot[listTva[h].td2] = listTva[h].value2;
				             	foot[listTva[h].td3] = listTva[h].value3;
				             	footX["AA"+h]=foot;
					     }
					     
					     
					     for (var p = listTva.length ; p<6; p++) {
					      var foot = {} ;
					        foot["0"] = "deletio";
			             	footX["UU"+p]=foot;
					     } 
					     
					     
					    for (var x = 0; x <listTotal.length; x++) {
					     var foot ={} ;
			             foot[listTotal[x].td1] = listTotal[x].value1;
			             foot[listTotal[x].td2] = listTotal[x].value2;
			             footX["BB"+x]=foot;
					     }   
					        
				    return  footX; 
				}
					
					
				function loadMyTab(myTab){
				
LoadDataEditableFromServer_toolbarV22( oTable24, mapEditableGen2  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey ,"toolbar_es",  contenu_toolbarJQuey  );

				}
				
				function loadMyTabPrestation(myTab){
					
					 LoadDataEditableFromServerPrestation( oTable24, mapEditableGenPrs  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
							 config_header_foot_tableJQuey   ,"toolbar_Service" ,contenu_toolbarJQuey  );

									}
				
               </script>
 </ext:panel>
<ext:panel   id="RET_GRIDX"   bodyStyle="background: none;"  onActivate="loadMyTab('RET_GRIDX')"   title="Fourniture de vente" height="350" >
           <table id="GRID_SAISIE_FOURNITURE_VENTE" class="display" width="100%"   >
			      <thead   >
					 <tr> 
						<th>Référence</th>
						<th>Désignation</th>
						<th>Qté</th>
						<th>Unite</th>
						<th>T.V.A</th>
						<th>P.U.V</th>
						<th>T.H.T</th>
				    </tr>
				 </thead>
				 <tfoot>
		                    <tr  > 
								<td colspan="5" align="right"></td>
								<td ></td>
								<td ></td>
					  </tr>
				 </tfoot>
			    </table>
			     <script type="text/javascript">
					function doLoaderDataFooter2( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL_FOURNITURE','json',false);
					    var totMntTTC     =json["totMntTTC"];
                        var totMntHT      =json["totMntHT"];
				        var firstitems  = {"0":"Total"    ,"1":totMntHT   ,"2":totMntTTC   };
				        var items       = {"A":firstitems };
				    return  items; 
				}
               </script>	
</ext:panel>

<ext:panel   id="RET_GRID_PRESTATION"       onActivate="loadMyTabPrestation('RET_GRID_PRESTATION')"      bodyStyle="background: none;"    border="false"   height="200"   hideCollapseTool="true"  title="Prestation"    >
	         
	          <table id="GRID_SAISIE_PRESATATION" class="display" width="100%"   >
			      <thead   >
					 <tr> 
						<th></th>
						<th></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>Qté</th>
						<th>P.U.V</th>
						<th>T.H.T</th>
						<th></th>
				    </tr>
				 </thead>
				 </table>
	         </ext:panel>
 
</ext:tabPanel>
		 
		   
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


