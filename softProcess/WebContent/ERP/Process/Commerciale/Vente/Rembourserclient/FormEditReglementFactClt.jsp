<%@include file="/Aceuil/esProcess.jsp" %>
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">

var lumsbean=[ 

        {   "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false },
                  
	    
	    {    "sTitle":"date"     ,"sName": "pk.echean_date"                      ,"bSortable": true    ,"mRender": function( data, type, full){  
	          return '<input   type="datepicker"  size="70"  id=echean_date'+full[0]+'    name=pk.echean_date           value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")        nextElement=echean_montant'+full[0]+'     >';}},       
	  
	  
	    {    "sTitle":"Montant"     ,"sName": "echean_montant"        ,"bSortable": true     ,"mRender": function( data, type, full){  
	          return '<input   type="montant3"  size="15"  id=echean_montant'+full[0]+'   name=echean_montant    value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")         nextElement=reg_mod'+full[0]+'  >';}}, 
	         
	  
	    {   "sTitle":"Mode"     , "sName": "echeanMode.data_id"                       ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<select       id=reg_modx'+full[0]+'       name=echeanMode.data_id        onchange=doEnvoiDataV2(this,"'+full[0]+'")        ></select>';}},
	         
	    
	    {    "sTitle":"Piece"     ,"sName": "num_piece_ech"                   ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<input   type="text"   size="10"   id=num_piece_ech'+full[0]+'  name=num_piece_ech         value="'+data+'"   onblur=doEnvoiDataV2(this,"'+full[0]+'")       nextElement=echean_date'+full[5]+'           >'; }},
	         
	    {   "sName": "indx_row_next"     ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
	          
	  ]; 
mapEditableGen = {"otab":oTable,"table":"gridEcheance","list":"list_des_echeances","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};

function getSuiv(){

  var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_GRID_ECHEAN','text',false);
  if(json=="oui"){
 height_tabbJQuey="auto";
 contenu_toolbarJQuey="";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
  }else{
   alertExt("","le nombre de fois  d échéance est null","1");
  }
 
}

 
function FnLoadSelectAjax(objeJson){

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
			              
			              $regions.append('<option value=""    >--selectionnez</option>');
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
   
        <ext:toolbar         toolbarType="bbar"   > 
        <ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
        
    <table width="100%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >
      <tr>
        <td width="10%"><label>Avoir N°</label></td>
        <td  width="30%"  ><input id="fact_clt_id" name="factclient.fact_clt_id"    libre   readonly="readonly"   type="text"      size="20"       maxlength="20"        value="${detailBean.factclient.fact_clt_id}"    nextElement="clt_id"              />        </td>
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
           <script  >$(function() {  loadSelectAjax("reg_modXX","list_mode_reglment","data_id","data_libelle","${detailBean.mode.data_id}",true);  })</script>
        <select  id="reg_modXX"  name="mode.data_id"           style="width: 180px;"      nextelement="reg_nbr_echeance"        required="required"   >
        </select></td>
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
        <select  id="reg_nature" name="nature.data_id"      style="width:180px;"      nextelement="reg_nbr_echeance" > </select> </td>
        <td  ><label>${reg_nbr_echeance}</label></td>
        <td  ><input id="reg_nbr_echeance" name="reg_nbr_echeance"     type="number"    min="0"    width="10px"       value="${detailBean.reg_nbr_echeance}"    nextelement="montant_facture" /></td>
      </tr>
      <tr>
        <td  ><label>${montant_facture}</label></td>
        
         
	
	
	 
        
        <td   ><input id="montant_facture" name="montant_facture"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.factclient.total_facture}"    nextelement="montant_avance" /></td>
        <td   ><label></label></td>
        <td   ><input id="montant_avance" name="montant_avance"       type="hidden"          nextelement="montant_restant"></td>
        <td   ><label>Net à rembourser </label></td>
        <td   ><input id="montant_restant" name="montant_restant"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.factclient.net_a_payer}"    nextelement="num_piece"></td>
      </tr>
    </table>
      </ext:panel>
      <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Echéance paiement facture"  height="300"> 
	 <table id="gridEcheance" class="display" width="100%" ></table>
	</ext:panel>  
  </ext:panel>
</ext:body>
