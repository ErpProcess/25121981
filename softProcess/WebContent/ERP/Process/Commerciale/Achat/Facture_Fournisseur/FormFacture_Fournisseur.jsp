 <%@include file="/Aceuil/esProcess.jsp" %>  
 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
width_tabbJQuey="100%";
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_DETAIL_FACTURE_FRS",
                               "list"   :"list_data_detaille_fact_frs",
                               "id_name":"pk.fkCode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									       
									       {      "sName": "to_check"     ,"sWidth": "2%"   ,"bSortable": true  , "bVisible": false   , "mRender": function( data, type, full){
									              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk.code_barre'+full[2]+'"   >';}}, 
									                  
									       
									                   
										   {      "sTitle":"Code"   ,"sName": "pk.fkCode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sTitle":"Designation"    ,"sName": "pk.fkCode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										                 
										   {      "sTitle":"Qte"   ,"sName": "quantite"                             , "sWidth": "5%"       },   
										  
										   {      "sTitle":"Unite"   ,"sName": "pk.fkCode_barre.pk.ar_bean.unitBean.unite_lib"                    ,"sWidth": "10%" ,"bSearchable": true },
										           
										           
										   {      "sTitle":"Prix U"    , "sName": "tarif_unit_achat"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },
	                                      
	                                       {        "sTitle":"TVA" , "sName": "tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	                                          },             
	                                    
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_achat"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	                                            ]
 
                               };
       
$(document).ready(function (){
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  ); 
});                                                                                         
 
 
 
 
								                        
 </script>
 
 
 
  <ext:body  >  
  <ext:panel     border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
    <ext:panel  border="false"    bodyStyle="background: none;"        >
    
    
 <table width="88%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
   <tr>  
   <td width="10%"><label>${fact_id}</label></td>  
   <td width="50%"  > 
   <input id="fact_frs_id" name="fact_frs_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.fact_frs_id}"    nextElement="frs_id"    libre readonly="readonly"         />  </td>  
   <td width="17%"  >N&deg; Facture Imprim&eacute;e &nbsp;</td>
   <td    >
   <input id="doc_id_interne" name="doc_id_interne"     type="text"    size="20"       maxlength="20"       value="${detailBean.myFile.doc_id_interne}"     /> 
</td>
   </tr>   
   <tr>  
   <td   ><label>${four_id}</label></td>  
   <td  >  
   <input id="frs_id" name="frs.frs_id"  type="text"    size="10"       maxlength="10"    value="${detailBean.frs.frs_id}"    nextElement="fact_date"              /> 
   <input id="frsref" name="frsref"      type="text"    size="30"       maxlength="10"    value="${detailBean.frs.frsref}"    nextElement="fact_date"              />  </td>   
   <td   > <label id="labelTelecharger">T&eacute;l&eacute;charger document </label>   </td>
   <td  >
   
<script>

var files = [];
$(document).on("change","#fileLoader",function(event) { files=event.target.files; processUpload();})


function processUpload(){
           
             var oMyForm = new FormData();
             oMyForm.append("file", files[0]);
             oMyForm.append("HiddenAction", "i$_ACT_UPLOADER");
             oMyForm.append("doc_id_interne",  $('#doc_id_interne').val() );
             
            
             $.ajax({dataType : "text",
                    url : "${tmlx.urlAjax}",
                    data : oMyForm,
                    type : "POST",
                    enctype: "multipart/form-data",
                    processData: false, 
                    contentType:false,
                    scriptCharset: "utf-8",
        			async: false,
        			cache: false,
        			timeout: 600000,
                    success : function(result) {
                        alert(result);
                    },
                    error : function(result){
                        alert(result);
                    }
                });
     }
          
  function afficher(){
		$('#myformToServeur').find('input[name="HiddenAction"]').val("i$_ACT_AFFICHE_DOC");
		$('#myformToServeur').attr('target', '_blank');
		$("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
		$("#myformToServeur").submit();
  }
  
  
$(document).ready(function () {
       $('#myLinkFile').hide();
	   $('#fileLoader').hide();
	   $('#labelTelecharger').hide();
	   
	    
	 if("${detailBean.fact_frs_id}" !="" &&  "${detailBean.fact_frs_id}" !=null && "${detailBean.fact_frs_id}" !=undefined && 
	    "${detailBean.myFile.file_name}" !="" ){
		$('#myLinkFile').show(); 
		$('#labelTelecharger').show(); 
	 }else{
	 
		if("${bs.fct_id}"=="14"){  
		$('#fileLoader').show();
		$('#labelTelecharger').show();  
		}   
	 }
 });
</script>
   <input  id="fileLoader"  type="file" name="file"       />    
   <a      id="myLinkFile"  href="javascript:afficher();"  ><c:out value="${detailBean.myFile.file_name}"></c:out></a>
   </td>
   </tr>   
   <tr>  
   <td  ><label>${fact_date}</label></td>  
   <td colspan="3"  >  
   <fmt:formatDate pattern="dd/MM/yyyy"       value="${detailBean.fact_date}"      var="searchat_datefac"/>
   <input id="fact_date" name="fact_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefac}"    nextElement="fact_date_edition"              />  </td>  
   </tr>   
   <tr>  
   <td  ><label>${fact_date_edition}</label></td>  
   <td colspan="3"  >  
   <fmt:formatDate pattern="dd/MM/yyyy"       value="${detailBean.fact_date_edition}"      var="searchat_datefacEdition"/>
   <input id="fact_date_edition" name="fact_date_edition"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_datefacEdition}"    nextElement="tva_id"              />  </td>  
   </tr>   
   
      <tr>  
  <td  ><label>${avance_montant_achat}</label></td>  
		   <td colspan="3"   >  
   <input id="avance_montant_achat"     name="avance_montant_achat"      type="montant3"    size="50"    libre   readonly="readonly"      maxlength="50"        value="${detailBean.avance_montant_achat}"    nextElement="btValidx"              />		  </td>  
		   </tr>  
 </table>   
</ext:panel>
  <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille Facture"    > 
	          
	        
			    <table id="GRID_DETAIL_FACTURE_FRS" class="display" width="100%"      >
			     
			      <tfoot>
				    <tr><td   height="50px" colspan="10" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								 
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
				    
			       <c:forEach var="i" begin="1" end="8">
		                    <tr align="right"> 
								<td colspan="3"></td>
								<td   ></td>
								<td colspan="3" ></td>
								<td colspan="3"></td>
						    </tr>
					</c:forEach>	
				 </tfoot>
			    </table>
			    
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
					     
					     
					     for (var p = listTva.length ; p<6; p++) {
					      var foot = {} ;
					        foot["0"] = "deletio";
			             	footX["UU"+p]=foot;
					     } 
					     
					     
					    for (var x = 0; x <listTotal.length; x++) {
					     var foot ={} ;
			             foot[listTotal[x].td1] = listTotal[x].value1;
			             foot[listTotal[x].td2] = listTotal[x].value2;
			             footX["BB"+x]=foot;
					     }   
					        
				    return  footX; 
				}
               </script>
               
			      
			    
	         </ext:panel>
	         
	         </ext:panel>	         
</ext:body>
