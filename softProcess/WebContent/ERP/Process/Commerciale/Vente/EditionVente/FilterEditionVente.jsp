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
	     <td  width="7%" ><label>${_datedebut} </label></td>  
	     <td   width="10%"  > 
	     <input id="date_debut"             compareTo="date_fin"      name="date_debut"        type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_debut}"    nextElement="date_fin"              /></td>
	     <td width="5%"  ><label>${_datefin}</label></td>
	     <td width="78%"   >
	     <input id="date_fin"        comparedTo="date_debut"      name="date_fin"     type="datepicker"    size="13"       maxlength="13"        value="${searchBean.date_fin}"    nextElement="clt_id"              /></td>
	   </tr>
	   
   
 </table>   
</ext:panel>
 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille vente"    >
      <table id="GRID_DETAIL_FACTURE_CLIENT" class="display" width="100%"      >
      <tfoot>
          <tr>
            <td   height="50px" colspan="8" ></td>
          </tr>
            <tr  >
              <td colspan="4"  ></td>
              <td ></td>
              <td ></td>
              <td ></td>
              <td ></td>
            </tr>
        </tfoot>
      </table>
      <script type="text/javascript">
// 					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
// 					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
// 					    var	listTva= json.list_tva ;
// 					    var	listTotal = json.list_total ;
// 					    for (var x = 0; x <listTotal.length; x++) {
// 					     var foot ={} ;
// 			             foot[listTotal[x].td1] = listTotal[x].value1;
// 			             foot[listTotal[x].td2] = listTotal[x].value2;
// 			             footX["BB"+x]=foot;
// 					     }   
					        
// 				    return  footX; 
// 				}
        </script>
</ext:panel>


</ext:panel>
</ext:body>
    
