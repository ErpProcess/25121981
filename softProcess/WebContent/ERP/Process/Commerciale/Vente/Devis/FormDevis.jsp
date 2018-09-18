<%@ include file="/Aceuil/esProcess.jsp"      %>
 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 


  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   autoScroll="false"    >  
  
	 <ext:panel     border="false"    bodyStyle="background: none;"      >
	 
	 
	 


	  <table class="tableStyleContent"  cellpadding="5" cellspacing="5"  id="tblData"  border="0"  style="margin-top: 10px;"   >
	  
	    <tr>  
		   <td width="10%"><label>${devis_id}</label></td>  
		   <td width="90%"  >  
		  
		   <input id="devis_id" name="devis_id"    style=""  type="text"  size="15"               value="${detailBean.devis_id}"         libre  readonly="readonly"         />  
		   </td>  
	    </tr>
	    
	    <tr>  
		   <td width="10%"><label>${dev_date}</label></td>  
		   <td width="90%"  >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.dev_date}"   var="WW"/>
		   <input id="dev_date" name="dev_date"          type="text"          size="7"  maxlength="10"    value="${WW}"   nextElement="clt_id"           required    />  
		   </td>  
	    </tr>  
	   
	    <tr>  
	       <td ><label>${clt_id}</label></td>  
	       <td  >  
	       <input id="clt_id"  name="client.clt_id"           type="text"     size="10"          value="${detailBean.client.clt_id}"            nextElement="dev_lib"   required    /> 
	       <input id="clt_lib" name="client.clt_lib"          type="text"    size="30"           value="${detailBean.client.clt_lib}"          nextElement="dev_lib"   required    />    
	       </td>  
	    </tr>  
	    
	     <tr>  
	       <td ><label>${xccc}</label></td>  
	       <td  >  
	       <input id="dev_lib" name="dev_lib"          type="text"     size="100"          value="${detailBean.dev_lib}"            nextElement="dev_obs"        /> 
	       </td>  
	    </tr>  
	    
	     <tr>  
	       <td ><label>Remise</label></td>  
	       <td  >  
	       <input id="dev_remise" name="dev_remise"          type="montant3"     size="100"          value="${detailBean.dev_remise}"            nextElement="dev_obs"        /> 
	       </td>  
	    </tr>  
	    
	    
	    
	   <tr>  
	      <td ><label>${dev_obs}</label></td>  
	      <td   >  
	      <input id="dev_obs" name="dev_obs"       type="text"    size="100"       maxlength="50"        value="${detailBean.dev_obs}"   nextElement="ar_id"     /> 
	     </td>  
	    </tr> 
	    
	    
	   </table> 
	 
	 </ext:panel>
  

	<ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="détaille devis" > 
	
	<script type="text/javascript" > 





 
var lumsbean=[ 
       {      "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {     "sTitle":"Pr" , "sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true , "bVisible": false      , "mRender": function( data, type, full){
              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk_article.ar_id'+full[0]+'"   >';}}, 
                  
	   {      "sTitle":"${_code_barre}" , "sWidth": "10%"    ,"sName": "pk.code_barre.pk.code_barre"  }, 
	         
	   {      "sTitle":"${designation_libelle}" , "sWidth": "30%"   ,"sName": "pk.code_barre.designation_libelle"     },       
	          
	   {      "sTitle":"${quantite}"  , "sWidth": "10%"   , "sName": "quantite"   },   
	   
	   {       "sTitle":"${unite_lib}"  ,"sName": "pk.code_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "10%"     ,"bSearchable": true },
	  
	 
	    
	   {      "sTitle":"${observation}"  , "sWidth": "10%"  , "sName": "observation"      },
	    
	    
	   {      "sTitle":"Prix U ", "sName": "tarif.tarif_unit_vente"      ,"sClass" : "alignRight"   ,"sWidth": "20%"    , "bSortable": "true" 
	            , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	   {      "sTitle":"Prix total" , "sName": "montant_ht_vente"  ,"sClass" : "alignRight"   ,"sWidth": "20%"    , "bSortable": "true" ,"bVisible": true  
	            ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },   
	            
	   {      "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	           },                     
	                                                    
	   {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	         ]; 
mapEditableGen= {"otab":oTable,"table":"gridd_devis_vente","list":"listGridEditableDevis","id_name":"pk.code_barre.pk.code_barre","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
   

 
$(document).ready(function () {
 height_tabbJQuey="auto";
 contenu_toolbarJQuey="";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );

});


 
</script> 

	
			    
			     <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					 
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var	listTva= json.list_tva ;
					    var	listTotal = json.list_total ;
					    
					     
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
					     
					     for (var z = listTva.length ; z <6; z++) {
					      var foot ={} ;
					        foot["0"] = "deletio";
			             	footX["UU"+z]=foot;
					     } 
					     
					     
					    for (var x = 0; x <listTotal.length;x++) {
					     var foot ={} ;
			             foot[listTotal[x].td1] = listTotal[x].value1;
			             foot[listTotal[x].td2] = listTotal[x].value2;
			             footX["BB"+x]=foot;
					     }   
					        
				    return  footX; 
				}
               </script>
               
                <table      id="gridd_devis_vente" class="display"  width="100%"     >
	             
	             <tfoot>
	             
	              <tr><td   height="50px" colspan="10" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td colspan="3" ></td>
								<td ></td>
								<td ></td>
								<td ></td> 
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="5">
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
			    
			    
			    
	</ext:panel> 
 
</ext:panel>
</ext:body>