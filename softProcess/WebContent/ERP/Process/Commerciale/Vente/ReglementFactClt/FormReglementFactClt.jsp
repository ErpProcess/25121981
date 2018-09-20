<%@include file="/Aceuil/esProcess.jsp" %>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">

var lumsbean=[ 

        {   "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
	    
	    {    "sTitle":"date"     ,"sName": "pk.echean_date"  ,"bSortable": true    },       
	  
	    {    "sTitle":"Montant"     ,"sName": "echean_montant"        ,"bSortable": true         }, 
	  
	    {   "sTitle":"Mode"     , "sName": "echMode.data_libelle"                       ,"bSortable": true    },
	         
	    {    "sTitle":"Piece"     ,"sName": "num_piece_ech"                   ,"bSortable": true    },
	    
	    {    "sTitle":"Etat"     ,"sName": "etatRegEch.data_libelle"                   ,"bSortable": true    },
	         
	   
	          
	  ]; 
mapEditableGen = {"otab":oTable,"table":"gridEcheance","list":"list_des_echeances","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};



$(document).ready(function () {

if("${detailBean.reg_nbr_echeance}"!=""){
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
}

});
function getSuiv(){

  var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_GRID_ECHEAN','text',false);
  if(json=="oui"){
 
  }else{
   alertExt("","le nombre de fois  d échéance est null","1");
  }
 
}

 
function FnLoadSelectAjax(objeJson){
    loadSelectAjaxX(objeJson);
}




function loadSelectAjaxX(objeJson){
        
    		$.ajax({
			        url: UrlServerListeComboSelect,
			        data: {"nameList_select":"list_mode_reglment" ,"fieldcode":"data_id","fieldlabel":"data_libelle"},  
			        dataType: "json", 
			        async: false,
			        type: "POST",
			        success: function(data) {
			        
			          for ( var q=0  ; q<objeJson.aaData.length ; q++ ) {
			          
				 		  var id_region="reg_modx"+objeJson.aaData[q][0];
				 		  var idInputcode_barre=objeJson.aaData[q][3];
				 		  
				 		  $('option', '#'+id_region).remove();
			              var $regions = $('#'+id_region);
			              
			              for (var h = 0; h <data.myliste.length; h++) {
			              var  sel='';
			              
			               
			              if( data.myliste[h].keyx==idInputcode_barre){
			                 sel=' selected ';
			              }
			              $regions.append('<option value="'+  data.myliste[h].keyx +'"      '+sel+'      >'+ data.myliste[h].valuex +'</option>');
			             }  
			          
                      }    
	  
			        }
            });
}

 
</script>
<ext:body  >
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
  
   <ext:panel  border="false"    bodyStyle="background: none;"       >
        
    <table width="100%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >
      <tr>
        <td width="10%"><label>${fact_id}</label></td>
        <td  width="30%"  ><input id="fact_clt_id" name="factclient.fact_clt_id"    libre   readonly="readonly"   type="text"      size="20"    
           maxlength="20"        value="${detailBean.factclient.fact_clt_id}"    nextElement="clt_id"              />       
           
          
          <input id="fact_ref_id" name="factclient.fact_ref_id"    libre   readonly="readonly"   type="text"      size="20"       maxlength="20"   
             value="${detailBean.factclient.fact_ref_id}"                />  
           
            </td>
        <td    >${reg_id}</td>
        <td    ><input id="reg_id" name="reg_id"   libre="libre" readonly="readonly"   type="text"    size="20"       maxlength="20"        value="${detailBean.reg_id}"    nextelement="fact_id"></td>
        <td    >${reg_date}</td>
        <td    >
         <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.reg_date}"   var="dateforInput"/>   
        <input id="reg_date" name="reg_date"     type="datepicker"    size="13"       maxlength="13"        value="${dateforInput}"    nextelement="reg_nbr_echeance" /></td>
      </tr>
      <tr>
        <td  ><label>${clt_id}</label></td>
        <td  > <input id="clt_id"  name="factclient.client.clt_id"     libre   readonly="readonly"    type="text"    size="10"       maxlength="10"        value="${detailBean.factclient.client.clt_id}"    nextElement="fact_date"              />
               <input id="clt_lib" name="factclient.client.clt_lib"    libre   readonly="readonly"    type="text"    size="22"       maxlength="10"        value="${detailBean.factclient.client.clt_lib}"    nextElement="fact_date"              />        </td>
        <td  >${reg_mod}</td>
        <td  >
        
        
        <script  >$(function() {  loadSelectAjax("reg_modXX","list_mode_reglment","mod_id","mod_libelle","${detailBean.modReg.mod_id}",true);  })</script>
        <select    id="reg_modXX"  name="modReg.mod_id"     required     style="width: 180px;"      nextelement="num_piece"        ></select>
        
        
        </td>
        <td  >${num_piece}</td>
        <td  ><input id="num_piece" name="num_piece"     type="text"    size="20"       maxlength="30"        value="${detailBean.num_piece}"    nextelement="reg_nature" /></td>
      </tr>
      <tr>
        <td  ><label>${fact_date}</label></td>
        <td  ><fmt:formatDate pattern="dd/MM/yyyy"        value="${detailBean.factclient.fact_date}"      var="searchat_datefac"/>
          <input id="fact_date" name="factclient.fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefac}"    nextElement="fact_date_edition"              />        </td>
        <td  >${reg_nature}</td>
        <td  >
        <script  >$(function() {  loadSelectAjax("reg_nature","list_nature_reglement","data_id","data_libelle","${detailBean.nature.data_id}",false);  })</script>
         <select id="reg_nature" name="nature.data_id"            required="required"        nextelement="reg_nbr_echeance" > </select> </td>
        <td  ><label>${reg_nbr_echeance}</label></td>
        <td  ><input id="reg_nbr_echeance" name="reg_nbr_echeance"     type="number"    min="0"    width="10px"       value="${detailBean.reg_nbr_echeance}"    nextelement="montant_facture" /></td>
      </tr>
      <tr>
        <td  ><label>${montant_facture}</label></td>
        <td   ><input id="montant_facture" name="montant_facture"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.montant_facture}"    nextelement="montant_avance" /></td>
        <td   ><label>${montant_avance}</label></td>
        <td   ><input id="montant_avance" name="montant_avance"       type="montant3"    size="17"       maxlength="17"        value="${detailBean.montant_avance}"    nextelement="montant_restant"></td>
        
        
        <td ><label>Montant Reçu</label></td>
        <td ><input id="montant_recu" name="montant_recu"     onblur="getMontantRestant();"    type="montant3"    size="17"    value="${detailBean.montant_recu}"    nextelement="montant_recu" /></td>
      </tr>
      
      
       <tr>
        <td ><label>Solde restant à payer</label></td>
        <td colspan="5" ><input id="montant_restant" name="montant_restant"     type="montant3"    size="17"     libre readonly="readonly"   maxlength="17"        value="${detailBean.montant_restant}"    nextelement="num_piece"></td>
        
      </tr>
      
    </table>
      </ext:panel>
      <c:if test="${not empty list_des_echeances}">
         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Echéance paiement facture"  height="300"> 
	     <table id="gridEcheance" class="display" width="100%" ></table>
	     </ext:panel> 
	 </c:if> 
  </ext:panel>
</ext:body>
