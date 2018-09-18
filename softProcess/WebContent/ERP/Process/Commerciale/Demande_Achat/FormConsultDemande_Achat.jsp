<%@include file="/Aceuil/esProcess.jsp" %>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript" > 

     
function doPdf_detaille_demande_achat(){
	genericPdfProcess("${tmlx.urlAjax}?HiddenAction=i$_ACT_PRINT_PDF_detaille_demande_achat");
}

function doExcel_detaille_demande_achat(){
$('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_EXPORT_XLS_detaille_demande_achat");
$('#myformToServeur').attr('target', '_self');
$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
$("#myformToServeur").submit();
}	 
	 
var lumsbean=[           
	   {  "sTitle": "${ar_id}" ,  "sName": "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.ar_id"   ,"sWidth": "10%"   }, 
	   
	   {  "sTitle": "${code_barre}" ,   "sName": "pk_det_dem_achat.fkCode_barre.pk.code_barre"   ,"sWidth": "30%"  },
	   
	   {  "sTitle": "${designation}" ,   "sName": "pk_det_dem_achat.fkCode_barre.designation_libelle"   ,"sWidth": "30%"  },       
	          
	   {  "sTitle": "${quantite}" ,   "sName": "quantite"   ,"sWidth": "2%"  , "bSortable": true,"bVisible": true  },   
	  
	   {  "sTitle": "${unite}" ,   "sName": "unitBean.unite_lib"     ,"sWidth": "2%"   },
	    
	   {  "sTitle": "${observation}" ,   "sName": "observation"   ,"sWidth": "30%"   },
	         ]; 
var mapDone  = {"otab":oTable,"table":"griddem_achat","list":"listGridEditable_demande_achat","id_name":"id_entite","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
$(document).ready(function () {     
 
 
 
 contenu_toolbarJQuey="";
 height_tabbJQuey="auto";
 if("${bs.fct_id}"=="2"){doGenerate_methode_ajaxWithReturn('POST',"${tmlx.urlAjax}",'i$_ACT_LOAD_DETAILLE_CONSULT','text',false);}
 LoadDataEditableFromServer_toolbar( mapDone  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
});
 
     
</script>  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
	 <ext:panel   border="true"   bodyStyle="background: none;height:200px;"  >
	  <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"  border="0"   >
	  
	    
	    <tr>  
		   <td width="10%"><label>${dem_achat_id}</label></td>  
		   <td width="90%"  >  
		   <input id="dem_achat_id" name="dem_achat_id"    style=""  type="text"  size="15"       maxlength="10"        value="${detailBean.dem_achat_id}"                  />  
		   </td>  
	    </tr>
	    
	   
	    
	    <tr>  
		   <td width="10%"><label>${dem_date}</label></td>  
		   <td width="90%"  >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.dem_date}"   var="detailBeandem_date"/>
		   <input id="dem_date" name="dem_date"    style=""  type="datepicker"  size="7"       maxlength="10"        value="${detailBeandem_date}"                  />  
		   </td>  
	    </tr>  
	   
	    <tr>  
	       <td ><label>${four_id}</label></td>  
	       <td  >  
	       <input id="frs_id" name="frsBean.frs_id"        type="text"    size="10"       maxlength="10"     
	          value="${detailBean.frsBean.frs_id}"   nextElement="dem_obs"   required    /> 
	        <input id="frsref" name="frsBean.frsref"        type="text"    size="30"             
	          value="${detailBean.frsBean.frsref}"   nextElement="dem_obs"   required    />    
	       </td>  
	    </tr>    
	    
	   <tr>  
	      <td ><label>${observation}</label></td>  
	      <td   >  
	      <input id="dem_obs" name="dem_obs"         type="text"    size="50"       maxlength="50"        value="${detailBean.dem_obs}"        /> 
	     </td>  
	    </tr> 
	   </table>  
	 </ext:panel>
  

	<ext:panel  title="détaille Demande Achat " id="RET_GRID"   bodyStyle="background: none;"   > 
	 <table id="griddem_achat" class="display" width="100%" >
	</table>
	</ext:panel>

</ext:panel>
</ext:body>
