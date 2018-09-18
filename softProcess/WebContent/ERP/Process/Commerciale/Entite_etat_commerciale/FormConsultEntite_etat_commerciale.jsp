 <%@include file="/Aceuil/esProcess.jsp"   %> 
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript" > 

var lumsbean=[  
                  
	    {   "sTitle": "Codes",  "sName": "data_id"        ,"bSortable": true    ,"sWidth": "30%"     }, 
	         
	    {   "sTitle": "Codes",   "sName": "data_libelle"                     ,"bSortable": true    ,"sWidth": "50%"  },       
	  
	    {   "sTitle": "Codes",   "sName": "data_ordre"                       ,"bSortable": true    ,"sWidth": "10%"  },
	    
	    {   "sTitle": "Codes",   "sName": "obs_det_entite"                   ,"bSortable": true    ,"sWidth": "10%" },
	          
	  ]; 
var mapDone  = {"otab":oTable,"table":"gridEntite_commerciale","list":"list_detail_entite_for_cree","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
mapEditableGen=mapDone;


$(document).ready(function () {
LoadDataEditableFromServer(mapDone);
});

 
</script> 
 
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 
 
  <ext:panel       bodyStyle="background: none;height:80px;"   collapsible="true"     >
	  <table class="tableStyleContent"  cellpadding="5" cellspacing="5"  id="tblData"  border="0"  style="margin-top: 10px;"   >
	      <tr>  
		   <td width="7%"><label>${code_entite}</label></td>  
		   <td width="40%"  >  
		   <input id="code_entite" name="code_entite"     type="text"    size="20"       maxlength="20"    value="${detailBean.code_entite}"    nextElement="libelle_entite"    autofocus   required     />  
		  </td> 
		   <td width="4%"  rowspan="2"  valign="top" >  
		  <label>Observation</label> 
		  </td>
		  <td width="50%"  rowspan="2"  valign="top" >  
		  <textarea  rows="3" cols="60"   id="obs_entite"  name="obs_entite"  nextElement="data_idx"   >${detailBean.obs_entite}</textarea>
		  </td>
		   
		   </tr>   
		   <tr>  
		   <td  ><label>${libelle_entite}</label></td>  
		   <td   >  
		   <input id="libelle_entite" name="libelle_entite"     type="text"    size="50"       maxlength="50"        value="${detailBean.libelle_entite}"    nextElement="obs_entite"              />  
		  </td>  
		   </tr>   
	   </table>  
	 </ext:panel>
 
 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Détaille Etite" > 
	 <table id="gridEntite_commerciale" class="display" width="100%" >
	</table>
	</ext:panel>  
</ext:panel>
</ext:body>
