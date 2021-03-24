<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">


$(document).ready(function () {
if(custumMessageBoxo!=""){
Ext.MessageBox.show({
           title:'Enregistrement Valider',
           msg: custumMessageBoxo,
           buttons: {ok:'Confirmer',no:'Retour'}  ,
           fn: showResultVente,
           animateTarget: 'mb4',
           icon: Ext.MessageBox.QUESTION
       });
}  
});

function showResultVente(btn){
     $("#ssSQZ_father").mask("Veuillez Patientez...");
     var hidvente="i$_ACT_COMMIT";
     if( btn=="no" ) hidvente="i$_ACT_RESET_FORM";
	 $("#myformToServeur").find('input[name="HiddenAction"]').val(hidvente);
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 
}


function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=list_editable_proVente",'i$_ACT_VERIF_LIST','text',false);
return  retournX ==""?"":"Veillez Remplir le détaille";

}
height_tabbJQuey="auto";
width_tabbJQuey="100%";
 
mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_VENTE",
                               "list"   :"list_editable_proVente",
                               "id_name":"pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true     , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[0]+'"   >';}}, 
									                  
										   {      "sName": "pk.fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "pk.fkcode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										          
										  
										    
										   {      "sName": "quantite_demander"                    , "sWidth": "5%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },           
										                 
										   {      "sName": "quantite"                             , "sWidth": "5%"     ,"mRender": function( data, type, full){  
										          return '<input   type="number"    style="width:70px;"     id=quantite'+full[2]+'    name=quantite    value="'+data+'"       onblur=doEnvoiDataV2(this,"'+full[2]+'")     nextElement="quantite'+full[2]+'"     >'; }},   
										  
										   
										   {      "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  },             
										           
										   {      "sTitle":"Prix U"    , "sName": "tarif.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"remise"     , "sName": "taux_remise_ligne"     ,"sWidth": "7%"    ,"sClass" : "alignCenter"    , "bSortable": true    
	                                              , "mRender": function (data, type, full) {  return addPourcentage(data);}  ,"bVisible": true    }, 
	                                              
	                                              
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_vente"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										    
										   {      "sTitle":"Total TTC" , "sName": "montant_ttc_vente"    ,"sWidth": "20%"     ,"sClass" : "alignRight"     , "bSortable": true ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },  
	                                              
	                                                   
										   {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };
    
                       
                              
function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
     
      
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
            var QteNew="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
	      $(this).find('td:eq(9)').html(json[qsdqsqd]) ;
	      //$(this).find('td:eq(4)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	      var xcvvv=json[erreurX];
	      xcvvv=xcvvv.trim();
	      if(   xcvvv  !="" &&  xcvvv.length>0 ){
	           alertExt("",json[erreurX],"4");
	      }
	      
	   });
    otab_otra.fnAdjustColumnSizing();
 
 }  
function  loadgrid(){
	 otab_otra.fnAdjustColumnSizing();
 }    
     
                           
$(document).ready(function (){
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","quantite","list_article_proVente","250","100"); 
LoadAutoCompletAjax_with_marGin("depot_id","depot_libelle","vente_obs","list_depot_stock","250","100");  
if( "${bs.fct_id}"=="16" || "${bs.fct_id}"=="3"  ){
    $("#tblData").find("select:not([libre])").attr("disabled",true);
    $("#tblData").find("input[idonly]").attr("readonly",true);	
    $("#tblData").find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly",true);
    
 } else {
    LoadAutoCompletAjax_with_marGin("clt_id","clt_lib","depot_id","list_client_for_vente","250","100"); 
 }  
 
});                                                                                         
 
 



function ADD(){

 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";
 
 var res_add = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
 otab_otra.fnAdjustColumnSizing();
 $('input[id="pk.code_barre"]').val('');               
 $('#designation_libelle').val('');
 $('#quantite_stock').val('');
 $('#quantiteX').val('1');
    
}


function Delete_ROW(){
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW',
	               dataType: 'text', 
	               success: function(data){
	               
	                otab_otra.fnAdjustColumnSizing();
                      },
                   error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
}

function doCheked_unCheked(element){
		var res="";
		if($(element).attr('type')=="checkbox"){
	    element.value=element.checked==false?"":"checked";
	    res=element.checked==false?"":"checked";
	    }
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_Cheked_unCheked&statucheked='+res,
	               dataType: 'text', 
	               success: function(data){
	             
					 otab_otra.fnAdjustColumnSizing();
	               
	               
                      },
                 error: function (request, status, error) {
                         alert(request.responseText);
                  } 
    });
}   
  
 
								                        
 </script>
 
  
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"   height="300"    >
   
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
		   <input id="taux_remise_alacaisse" name="taux_remise_alacaisse"      style="width: 50px;"     type="number"    min="0"  max="100"       libre="libre"          value="${detailBean.taux_remise_alacaisse}"    nextelement="designation_libelle"  onblur="loadgrid();" />
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
	       <td align="right"  ><input id="avance_montant_vente" name="avance_montant_vente"        type="montant3"    size="17"    libre="libre"   maxlength="50"        value="${detailBean.avance_montant_vente}"   style="height: 30px;font-size: 18px;"     nextelement="vente_remise"     onblur="loadgrid();" /></td>
	       </tr> 
		   
		   <tr>  
		   <td  ><label>${depot_id}</label></td>  
		   <td colspan="2"    >  
					   <input id="depot_id" name="depot.depot_id"    libre  type="text"    size="10"       maxlength="10"        value="${detailBean.depot.depot_id}"    nextElement="vente_obs"                  required    />  
					   <input id="depot_libelle" name="depot.depot_libelle"  libre    type="text"    size="30"       maxlength="10"        value="${detailBean.depot.depot_libelle}"    nextElement="vente_obs"   required     />		  </td>  
		   <td    >&nbsp;</td>
		   <td    >&nbsp;</td>
		   <td    ><label> Net.a payer </label></td>
		   <td align="right"    ><input id="vente_mnt_net_a_payer" name="vente_mnt_net_a_payer"     type="montant3"    size="17"     libre  readonly="readonly"    maxlength="15"     style="height: 30px;font-size: 18px;"     value="${detailBean.vente_mnt_net_a_payer}"       /></td>
		   </tr> 
		    
		   <tr>  
		   <td  ><label>${cmd_obs}</label></td>  
		   <td colspan="2"   >  
		   <input id="vente_obs" name="vente_obs"     type="text"    size="45"    libre         value="${detailBean.vente_obs}"     onblur="getSuiv();"   nextelement="quantiteX"       />		  </td>  
		   <td ><label>devise</label></td>  
		   <td ><script  >$(function() {loadSelectAjax("devX","list_devise","dev_id","dev_libelle","${detailBean.devise.dev_id}",true); })</script>
		        <select  libre="libre"  required   id="devX"  name="devise.dev_id"   style="width: 180px;"  ></select>
		   </td>  
		   <td   >  <label> Montant réçu</label></td>
		   <td align="right"   ><input id="montant_vente_recu" name="montant_vente_recu"     type="montant3"    style="height: 30px;font-size: 18px;"  size="17"      libre     value="${detailBean.montant_vente_recu}"    nextelement="montant_vente_rendu"     onblur="loadgrid();"   /></td>
		   </tr> 
		   
		    <tr>  
		   <td  ><label></label></td>  
		   <td colspan="2"  >&nbsp;</td>  
		   <td   align="right">&nbsp;</td>
	       <td   >&nbsp;</td>
	       <td   ><label> Montant rendu </label> </td>
	       <td align="right"   ><input id="montant_vente_rendu" name="montant_vente_rendu"    libre  readonly="readonly"    value="${detailBean.montant_vente_rendu}"   type="montant3"  style="height: 30px;font-size: 18px;"   size="17"            nextelement="null"   /></td>
	       </tr> 
		 </table> 
     
		
	 </ext:panel>
	 
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille vente"    > 
	          
	        
			    <table id="GRID_SAISIE_DETAIL_VENTE" class="display" width="100%" >
			    
			      <thead   >
					<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
					
						<th></th>
						<th><input  type="checkbox"   id="Cheked_unCheked"       name="Cheked_unCheked"                onclick="doCheked_unCheked(this)"     ></th>
						<th><input  type="text"       id="pk.code_barre"         name="code_barreX"         style="width: 95%;"        requiredx ></th>
						<th><input  type="text"       id="designation_libelle"   name="designation_libelle"   style="width: 95%;"        requiredx ></th>
					    <th></th>
		                <th ><input  type="number"     id="quantiteX"              name="quantiteX"              style="width: 93%;"         nextElement="unite"     requiredx ></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						
					</tr>
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
				 
					 
			<tfoot>
				    <tr><td   height="50px" colspan="11" >  </td> </tr>  
				  
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
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="7">
		                    <tr align="right"> 
								<td colspan="4"></td>
								<td   ></td>
								<td colspan="3" ></td>
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
					        foot["0"] = "";
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
               </script>
               
	         </ext:panel>
     
		 
		   
</ext:panel>
</ext:body>
