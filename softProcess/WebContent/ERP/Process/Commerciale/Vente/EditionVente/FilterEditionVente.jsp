<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
width_tabbJQuey="170%";
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_DETAIL_FACTURE_CLIENT",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX"
                               };
       function doDisplayTableData (){
    	   
    	   if(!teste_required()) return ; 
    	   $("#sedK").mask("Veuillez Patientez...");
    	  var resp= doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_AJAX_FETCH','json',false);
    	  Object.keys(resp.listcolonne).forEach(function(key) {
    		   if( resp.listcolonne[key].hasOwnProperty('formatMnt2') ){
    			   resp.listcolonne[key]['mRender']= function (data, type, full) {   return  formatNumberJsXC(data,2);   } 
    		   }
    		   if( resp.listcolonne[key].hasOwnProperty('formatMnt3') ){
    			   resp.listcolonne[key]['mRender']= function (data, type, full) {   return  formatNumberJsXC(data,3);   } 
    		   }
    		});
    	 
    	  mapEditableGen["mapCol"] = resp.listcolonne;
    	  mapEditableGen["id_name"]= resp.nameColIdGrid;
    	  mapEditableGen["list"]= resp.list;
    	  Ext.getCmp('btPrintPdfx').enable();
    	  Ext.getCmp('RET_GRID').show();
    	  document.getElementById("GRID_DETAIL_FACTURE_CLIENT").style.display="block";
    	  $('#GRID_DETAIL_FACTURE_CLIENT').css('display','block');
    	  $('#RET_GRID').css('display','block');
    	     
    	  LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
    	  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  ); 
    	  $("#sedK").unmask();  
       }
       
                                                                                        							                        
 </script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"  id="sedK"   title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
 
	   <tr>
	     <td  width="7%" ><label>${_datedebut} </label></td>  
	     <td   width="10%"  > 
	     <input id="date_debut"             compareTo="date_fin"      name="date_debut"    required    type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_debut}"    nextElement="date_fin"              /></td>
	     <td width="5%"  ><label>${_datefin}</label></td>
	     <td width="78%"   >
	     <input id="date_fin"        comparedTo="date_debut"      name="date_fin"   required   type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_fin}"    nextElement="clt_id"              /></td>
	   </tr>
	   
   
 </table>   
</ext:panel>
 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille vente"   draggable="true"  style="display:none;"  >
      <table id="GRID_DETAIL_FACTURE_CLIENT" class="display" width="100%"      ></table>
</ext:panel>


</ext:panel>
</ext:body>
    
