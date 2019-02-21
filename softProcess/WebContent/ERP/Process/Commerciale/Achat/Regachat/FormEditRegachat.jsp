<%@include file="/Aceuil/esProcess.jsp" %>
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">
 
 

				
function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=list_des_echeances",'i$_ACT_VERIF_LIST','text',false);
if(retournX!="" &&  $("#reg_nature").val()=="fa"  )
return "Veuillez Remplir au moin une échéance";
else
return "";
}				
				
 
function ADD(){

 lib_required="requiredx";
 if(!teste_required()) return ;
 lib_required="required";
 
 
 var resultat = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','json',false);
  //var resultat=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_GRID_ECHEAN','json',false);
  $("#montant_recu").val(resultat["montantRecu"] ) ;
  $("#montant_restant").val(resultat["montantRestant"] );
 otab_otra.fnAdjustColumnSizing();
 $('input[id="echeanDate"]').val('');               
 $('#echeanMontant').val('');
 $('#pieceNumHeader').val('');
 $('#modeRegHeader').val('');
 $('#etatRegHeader').val('');
}  
 

function Delete_ROW(){
  var resultat = doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_DELETE_ROW','json',false);
  $("#montant_recu").val(resultat["montantRecu"] ) ;
  $("#montant_restant").val(resultat["montantRestant"] );
  otab_otra.fnAdjustColumnSizing();
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
var lumsbean=[ 

        {     "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false },
                  
	    {     "sName": "to_check"                           , "sWidth": "1%"      ,"bSortable": true     , "mRender": function( data, type, full){
			 return  '<input  type="checkbox"   value="'+data+'"   id=to_check'+full[0]+'   name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[0]+'")     >';}},
	    
	    
	    {     "sName": "pk.echean_date"                      ,"bSortable": true    ,"mRender": function( data, type, full){  
	          return '<input   type="datepicker"  size="70"  id=echean_date'+full[0]+'    name=pk.echean_date           value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")        nextElement=echean_montant'+full[0]+'     >';}},       
	  
	  
	    {     "sName": "echean_montant"        ,"bSortable": true     ,"mRender": function( data, type, full){  
	          return '<input   type="montant3"  size="15"  id=echean_montant'+full[0]+'   name=echean_montant    value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")         nextElement=reg_mod'+full[0]+'  >';}}, 
	         
	  
	    {    "sName": "echMode.mod_id"                       ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<select   type="select"    id=ech_Mode'+full[0]+'       name=echMode.mod_id        onchange=doEnvoiDataV2(this,"'+full[0]+'")   ></select>';}},
	         
	    
	    {    "sName": "num_piece_ech"                   ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<input   type="text"   size="10"   id=num_piece_ech'+full[0]+'     name=num_piece_ech         value="'+data+'"   onblur=doEnvoiDataV2(this,"'+full[0]+'")       nextElement=echean_date'+full[5]+'           >'; }},
	    
	    {    "sName": "etatRegEch.data_id"                       ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<select     type="select"      id=etat_Reg_Ech'+full[0]+'       name=etatRegEch.data_id        onchange=doEnvoiDataV2(this,"'+full[0]+'")        ></select>';}},
	         
	              
	    {    "sName": "indx_row_next"     ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
	          
	  ]; 
mapEditableGen = {"otab":oTable,"table":"gridEcheance","list":"list_des_echeances","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};

function loadGridEcheance(){
 height_tabbJQuey="auto";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );	
}


function doExcuteFnAfterGrid( dataSS ){

  var resultat=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
  $("#montant_recu").val(resultat["montantRecu"] ) ;
  $("#montant_restant").val(resultat["montantRestant"] );
 	otab_otra.fnAdjustColumnSizing();
 } 
 
function FnLoadSelectAjax(objeJson){

 var data=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_SELECT','json',false);
 
 
			          for ( var q=0  ; q<objeJson.aaData.length ; q++ ) {
			          
				 		  var idModeSelect = "ech_Mode"+objeJson.aaData[q][0];     
				 		  var valueMode    = objeJson.aaData[q][4];
				 		  $('option', '#'+idModeSelect).remove();
			              var $optionMode = $('#'+idModeSelect);
			              $optionMode.append('<option value=""    >----Selectionnez----</option>');
			              for (var h = 0; h <data.modeRegList.length; h++) {
			              var  sel='';
			              if( data.modeRegList[h].keyx==valueMode){
			                 sel=' selected ';
			              }
			              $optionMode.append('<option value="'+  data.modeRegList[h].keyx +'"      '+sel+'      >'+ data.modeRegList[h].valuex +'</option>');
			             }  
			             
			              var idEtatEch="etat_Reg_Ech"+objeJson.aaData[q][0];     
				 		  var valueEtat=objeJson.aaData[q][6];
				 		  $('option', '#'+idEtatEch).remove();
			              var $optionEtat = $('#'+idEtatEch);
			              $optionEtat.append('<option value=""    >----Selectionnez----</option>');
			              for (var h = 0; h <data.etatEchList.length; h++) {
			              var    selEtat='';
			              if( data.etatEchList[h].keyx==valueEtat){
			                    selEtat=' selected ';
			              }
			              $optionEtat.append('<option value="'+  data.etatEchList[h].keyx +'"      '+selEtat+'      >'+ data.etatEchList[h].valuex +'</option>');
			             }  
			             
			             
			             
			          
                      }    
	   
}


 

 
</script>


<ext:body  >
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
  
   <ext:panel  border="false"    bodyStyle="background: none;"       >
   
        <ext:toolbar         toolbarType="bbar"   > 
        <ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> </ext:toolbar>
        
    <table width="100%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >
      <tr>
        <td width="10%"><label>${fact_frs_id}</label></td>
        <td  width="30%"  ><input id="fact_frs_id" name="fact_frs.fact_frs_id"    libre   readonly="readonly"   type="text"      size="20"       maxlength="20"        value="${detailBean.fact_frs.fact_frs_id}"    nextElement="frs_id"              />        </td>
        <td    >${reg_id}</td>
        <td    ><input id="reg_frs_id" name="reg_frs_id"   libre="libre" readonly="readonly"   type="text"    size="20"       maxlength="20"        value="${detailBean.reg_frs_id}"    nextelement="fact_id"></td>
        <td    >${reg_date}</td>
        <td    >
        <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.reg_date}"   var="dateforInput"/>   
        <input id="reg_date" name="reg_date"     type="datepicker"    size="13"       maxlength="13"        value="${dateforInput}"    nextelement="reg_nbr_echeance" /></td>
      </tr>
     
      <tr>
        <td  ><label>${frs_id}</label></td>
        <td  > <input id="frs_id"  name="fact_frs.frs.frs_id"     libre   readonly="readonly"    type="text"    size="10"       maxlength="10"        value="${detailBean.fact_frs.frs.frs_id}"    nextElement="fact_date"              />
               <input id="frsref"  name="fact_frs.frs.frsref"     libre   readonly="readonly"    type="text"    size="22"       maxlength="10"        value="${detailBean.fact_frs.frs.frsref}"    nextElement="fact_date"              />        </td>
        <td  >${reg_mod}</td>
        <td  >
        <script>  
        
                     function convertStringToNumber(paramStringNbr){
			if(paramStringNbr=="" ||  paramStringNbr=="NaN"  || paramStringNbr==null   || paramStringNbr==undefined    ) return 0;
			var mntRetour = paramStringNbr.replace(/ /g, '');
			return mntRetour;
			}
			function  CalculMontantRestant(){
			   var montantFacture  = convertStringToNumber($("#montant_facture").val());
	           var montantAvance   = convertStringToNumber($("#montant_avance").val());
	           var montantRecu     = convertStringToNumber($("#montant_recu").val());
	           var sommeRecuAvance = montantAvance+montantRecu;
	           var montantRestant  = montantFacture-sommeRecuAvance;
	           var montantRestantString  = montantRestant.toString();
	           $("#montant_restant").val(formatNumberJs(montantRestantString,3) );
			}
			
           function getMontantRestant(valNature){
          
           if(valNature=="co"){
           $("#montant_recu").val( $("#montant_facture").val() );
           $("#montant_recu").attr('readonly',false);
           Ext.get('RET_GRID').hide();
           CalculMontantRestant();
           }
           if(valNature=="fa"){
           $("#montant_recu").attr('readonly',true);
           Ext.get('RET_GRID').show();
           loadGridEcheance();
           }  
     
          
           
           }
        
        
        $(function() {  loadSelectAjax("reg_modXX","list_mode_reglment","mod_id","mod_libelle","${detailBean.modReg.mod_id}",true);  })</script>
        <select    id="reg_modXX"  name="modReg.mod_id"     required     style="width: 180px;"      nextelement="num_piece"        ></select>
        
       </td>
        <td  >${num_piece}</td>
        <td  ><input id="num_piece" name="num_piece"     type="text"    size="20"       maxlength="30"        value="${detailBean.num_piece}"    nextelement="reg_nature" /></td>
      </tr>
      
      
      <tr>
        <td  ><label>${fact_date}</label></td>
        <td  ><fmt:formatDate pattern="dd/MM/yyyy"        value="${detailBean.fact_frs.fact_date}"      var="searchat_datefac"/>
          <input id="fact_date" name="fact_frs.fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefac}"    nextElement="fact_date_edition"              />        </td>
        <td  >${reg_nature}</td>
        <td  >
        <script  >
        $(function() {  loadSelectAjax("reg_nature","list_nature_reglement","data_id","data_libelle","${detailBean.nature.data_id}",false);  })</script>
        <select  id="reg_nature" name="nature.data_id"      style="width:180px;"      nextelement="reg_nbr_echeance" > </select> </td>
        <td  ><label>${reg_nbr_echeance}</label></td>
        <td  ><input id="reg_nbr_echeance" name="reg_nbr_echeance"     type="number"    min="0"    width="10px"       value="${detailBean.reg_nbr_echeance}"    nextelement="montant_facture" /></td>
      </tr>
      <tr>
        <td  ><label>${montant_facture}</label></td>
        <td   ><input id="montant_facture" name="montant_facture"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.fact_frs.total_facture}"    nextelement="montant_avance" /></td>
        <td   ><label>${montant_avance}</label></td>
        <td   ><input id="montant_avance" name="montant_avance"       type="montant3"    size="17"       maxlength="17"        value="${detailBean.fact_frs.avance_montant_achat}"    nextelement="montant_restant"></td>
        <td   ><label>Montant Reçu</label></td>
        <td   ><input id="montant_recu" name="montant_recu"  onblur="CalculMontantRestant();"      type="montant3"    size="17"       maxlength="17"        value="${detailBean.montant_recu}"    nextelement="num_piece"></td>
      </tr>
      
       <tr>
        <td colspan="6" ><br><label>Solde restant à payer</label> <input id="montant_restant" name="montant_restant"     type="montant3"    size="17"     libre readonly="readonly"   maxlength="17"        value="${detailBean.montant_restant}"    nextelement="num_piece"></td>
      </tr>
      
    </table>
      </ext:panel>
      <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Echéance paiement facture"  height="300"> 
	 <table id="gridEcheance" class="display" width="100%"   >
	 <thead   >
					<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
						<th></th>
						<th></th>
						<th><input   type="datepicker"       id="echeanDate"            name="echeanDate"                  requiredx ></th>
						<th><input   type="montant3"         id="echeanMontant"        name="echeanMontant"              requiredx ></th>
						<th>
						<script  >
						$(function() {  
        loadSelectAjax("modeRegHeader","list_mode_reglment","mod_id","mod_libelle","bidand2",true);  })</script>
        <select  id="modeRegHeader" name="modeRegHeader"   requiredx      > </select> 
						
						
						</th>
		                <th ><input  type="number"     id="pieceNumHeader"       name="pieceNumHeader"       style="width: 93%;"    ></th>
						 
						
						
						<th >
						<script  >$(function() {loadSelectAjax("etatRegHeader","list_etat_reglment_ech","data_id","data_libelle","bidand",true);  })</script>
        <select  id="etatRegHeader" name="etatRegHeader"     requiredx    > </select> 
</th>
						<th></th>
					</tr>
					 <tr> 
						<th></th>
						<th>X</th>
						<th>Date</th>
						<th>Montant</th>
						<th>Mode</th>
						<th>piece</th>
						<th>Etat</th>
						<th></th>
				    </tr>
				 </thead>
 
	 </table>
	</ext:panel>  
  </ext:panel>
</ext:body>
<script>

Ext.onReady(function(){  try {	   
      <c:if test="${not empty list_des_echeances}">       
       Ext.get('RET_GRID').setStyle('display', 'block'); 
       loadGridEcheance();  
        </c:if>           
        } catch(e){   		}	         });             
          </script>   
          
