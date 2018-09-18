<%@include file="/Aceuil/esProcess.jsp" %>
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">

var lumsbean=[ 
	    
	    {    "sTitle":"date"     ,"sName": "pk.echean_date"                      ,"bSortable": true   },       
	  
	  
	    {    "sTitle":"Montant"     ,"sName": "echean_montant"        ,"bSortable": true      ,"sClass" : "alignRight"  ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }  }, 
	         
	  
	    {   "sTitle":"Mode"     , "sName": "echeanMode.data_libelle"          ,"bSortable": true     },
	         
	    
	    {    "sTitle":"Piece"     ,"sName": "num_piece_ech"                   ,"bSortable": true      },
	          
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
 




 

 
</script>


<ext:body  >
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >
  
   <ext:panel  border="false"    bodyStyle="background: none;"       >
   
        <ext:toolbar         toolbarType="bbar"   id="babbour"  > 
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
           <script  >$(function() {  loadSelectAjax("reg_modXX","list_mode_reglment","data_id","data_libelle","${detailBean.mode.data_id}",true);  })</script>
        <select  id="reg_modXX"  name="mode.data_id"       libre    style="width: 180px;"      nextelement="reg_nbr_echeance"        required="required"   >
        </select></td>
        <td  >${num_piece}</td>
        <td  ><input id="num_piece" name="num_piece"     type="text"    size="20"       maxlength="30"        value="${detailBean.num_piece}"    nextelement="reg_nature" /></td>
      </tr>
      
      
      <tr>
        <td  ><label>${fact_date}</label></td>
        <td  ><fmt:formatDate pattern="dd/MM/yyyy"        value="${detailBean.fact_frs.fact_date}"      var="searchat_datefac"/>
          <input id="fact_date" name="fact_frs.fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefac}"    nextElement="fact_date_edition"              />        </td>
        <td  >${reg_nature}</td>
        <td  >
        <script  >$(function() {  loadSelectAjax("reg_natureXX","list_nature_reglement","data_id","data_libelle","${detailBean.nature.data_id}",false);  })</script>
        <select  id="reg_natureXX" name="nature.data_id"   libre   style="width:180px;"      nextelement="reg_nbr_echeance" > </select> </td>
        <td  ><label>${reg_nbr_echeance}</label></td>
        <td  ><input id="reg_nbr_echeance" name="reg_nbr_echeance"     type="number"    min="0"    width="10px"       value="${detailBean.reg_nbr_echeance}"    nextelement="montant_facture" /></td>
      </tr>
      <tr>
        <td  ><label>${montant_facture}</label></td>
        <td   ><input id="montant_facture" name="montant_facture"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.fact_frs.total_facture}"    nextelement="montant_avance" /></td>
        <td   ><label>${montant_avance}</label></td>
        <td   ><input id="montant_avance" name="montant_avance"       type="montant3"    size="17"       maxlength="17"        value="${detailBean.fact_frs.avance_montant_achat}"    nextelement="montant_restant"></td>
        <td   ><label>Net à payer</label></td>
        <td   ><input id="montant_restant" name="montant_restant"     type="montant3"    size="17"       maxlength="17"        value="${detailBean.fact_frs.net_a_payer}"    nextelement="num_piece"></td>
      </tr>
      
      
    </table>
      </ext:panel>
      <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Echéance paiement facture"  height="300"> 
	 <table id="gridEcheance" class="display" width="100%" ></table>
	</ext:panel>  
  </ext:panel>
</ext:body>
 

