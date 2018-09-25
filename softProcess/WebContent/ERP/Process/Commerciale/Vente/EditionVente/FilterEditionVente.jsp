<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
width_tabbJQuey="105%";
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_DETAIL_FACTURE_CLIENT",
                               "list"   :"listEditionVente",
                               "id_name":"pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX"
                               };
       function doDisplayTableData (){
    	  var resp= doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_AJAX_FETCH','json',false);
    	  Object.keys(resp.listcolonne).forEach(function(key) {
    		   if( resp.listcolonne[key].hasOwnProperty('formatMnt2') ){
    			   resp.listcolonne[key]['mRender']= function (data, type, full) {   return  formatNumberJsXC(data,2);   } 
    		   }
    		   if( resp.listcolonne[key].hasOwnProperty('formatMnt3') ){
    			   resp.listcolonne[key]['mRender']= function (data, type, full) {   return  formatNumberJsXC(data,3);   } 
    		   }
    		});
    	  mapEditableGen["mapCol"]=resp.listcolonne;
    	  LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
    	  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  ); 
       }
       
                                                                                        							                        
 </script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${edition_id}</label></td>  
   <td width="93%"  >  
   <input id="edition_id"   name="edition_id"     type="text"    size="10"       maxlength="10"        value="${searchBean.edition_id}"    nextElement="edition_libelle"    autofocus         />  
  </td>  
   </tr>   
  <tr>  
   <td colspan="1" ><label>${_datedebut} </label></td>  
   <td    >  
	   <table   >
	   <tr>
	     <td width="40%" > 
	     <input id="date_debut"             compareTo="date_fin"      name="date_debut"        type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_debut}"    nextElement="date_fin"              /></td>
	     <td><label>${_datefin}</label></td>
	     <td>
	     <input id="date_fin"        comparedTo="date_debut"      name="date_fin"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_fin}"    nextElement="clt_id"              /></td>
	   </tr>
	   </table>
  </td>  
   </tr>   
   
 </table>   
</ext:panel>
 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Facture"    >
      <table id="GRID_DETAIL_FACTURE_CLIENT" class="display" width="100%"      ></table>
</ext:panel>


</ext:panel>
</ext:body>
    
