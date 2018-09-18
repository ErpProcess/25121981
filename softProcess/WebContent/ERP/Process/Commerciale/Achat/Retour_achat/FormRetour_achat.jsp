<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


<script type="text/javascript">
width_tabbJQuey="100%";
contenu_toolbarJQuey="";
height_tabbJQuey="auto";
$(document).ready(function () {
    $("#tblData").find("select:not([libre])").attr("disabled",true);
    $("#tblData").find("input[idonly]").attr("readonly",true);	
    $("#tblData").find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly",true);  
});

 mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_AHCAT",
                               "list"   :"list_editable_retour_achat",
                               "id_name":"pk.det_re.pk.fkCode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       
									              
									                  
										   {      "sName": "pk.det_re.pk.fkCode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "pk.det_re.pk.fkCode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										          
										  
										                 
										   {      "sWidth": "10%"  , "sName": "pk.det_re.quantite"  ,"sClass" : "alignCenter"    },
										          
										   {      "sWidth": "10%"  , "sName": "quantite_retourne"    ,"sClass" : "alignCenter"      },      
										  
										    
	                                              
	                                              
	                                         {      "sTitle":"Prix U"    , "sName": "pk.det_re.tarif.tarif_unit_article"   ,"sWidth": "5%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                         {      "sTitle":"Total H T" , "sName": "montant_ht_retour"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },                      
										         
										  
										           
	                                            ]
 
                               };



 
 
$(document).ready(function () {
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});

 



 


							                        
 </script>
 
  
  <ext:body  >  
  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
  
   <ext:panel  border="false"    bodyStyle="background: none;"       >
   
   <table class="tableStyleContent"  cellpadding="1" cellspacing="1"  width="100%"    >
   <tr>

   
    <td  width="50%" >
    
      <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblDcccata"   width="100%"  >  
		   <tr>  
		   <td width="20%"><label>${dem_achat_id}</label></td>  
		   <td width="80%"  >  
		   <input id="dem_achat_id"  name="achat.dem_achat.dem_achat_id"     type="text"    size="15"       maxlength="15"    
		    libre  readonly="readonly"        value="${detailBean.achat.dem_achat.dem_achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${dem_date}</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat.dem_achat.dem_date}"   var="detailBeandm_date"/>
		   <input id="dem_date" name="achat.dem_achat.dem_date"     type="text"    size="15"       maxlength="15"    libre    value="${detailBeandm_date}"     libre  readonly="readonly"              />  
		  </td>  
		   </tr>  
		   
		   <tr>  
		   <td width="7%"><label>${frs_id}</label></td>  
		   <td width="93%"  >  
		    <input id="frs_id" name="achat.frsBean.frs_id"       libre  readonly="readonly"      type="text"     size="10"            value="${detailBean.achat.frsBean.frs_id}"          nextElement="depot_id"   required    /> 
			<input id="frsref" name="achat.frsBean.frsref"       libre  readonly="readonly"      type="text"    size="30"             value="${detailBean.achat.frsBean.frsref}"          nextElement="depot_id"   required    />
		  </td>  
		   </tr>   
		    
		  
		    
		    
		   
		 </table> 
    
    
    
    
    </td>
    
    
    
        <td width="70%">
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"    >  
		   <tr>  
		   <td width="20%"><label>${achat_id}</label></td>  
		   <td width="90%"  >  
		 
		   <input id="achat_id" name="achat.achat_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.achat.achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td width="10%"><label>${achat_libelle}</label></td>  
		   <td width="90%"  >  
		   <input id="achat_libelle" name="achat.achat_libelle"     type="text"    size="15"       maxlength="15"    libre    value="${detailBean.achat.achat_libelle}"    nextElement="achat_date"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td width="10%"><label>${achat_date}</label></td>  
		   <td width="90%"  >
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat.achat_date}"   var="detailachat_date"/> 
		   <input id="achat_date" name="achat.achat_date"     type="text"    size="13"       maxlength="13"        value="${detailachat_date}"    nextElement="frs_id"              />  
		  </td>  
		   </tr>   
		   
		   <tr>  
		   <td width="10%"><label>${depot_id}</label></td>  
		   <td width="90%"  >  
					   <input id="depot_id" name="achat.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.achat.depot.depot_id}"    nextElement="btValidx"                  required    />  
					   <input id="depot_libelle" name="achat.depot.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.achat.depot.depot_libelle}"    nextElement="btValidx"   required     />
		 
		    </td>  
		   </tr> 
		    
		    
		   <tr>  
		   <td width="10%"><label>${retour_date}</label></td>  
		   <td width="90%"  > 
		     <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.retour_date}"   var="detailBeanRetour_date"/>  
			 <input id="retour_date" name="retour_date"     type="text"    size="10"                value="${detailBeanRetour_date}"    nextElement="btValidx"                  required    />  
		    </td>  
		   </tr> 
		   
		   
		  
		 </table> 
    
    
    
     </td>
   </tr>
   
   </table>
     
		
	 </ext:panel>
	 
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille retour achat"    > 
	          
	        
			    <table id="GRID_SAISIE_DETAIL_AHCAT" class="display" width="100%" >
			    
			      <thead   >
					 <tr> 
						 
						<th>Référence</th>
						<th>Désignation</th>
						<th>Qté.Reçue</th>
						<th>Qté.retournée</th>
						<th>P.U.HT</th>
						<th>Mnt.T.HT</th>
				    </tr>
				 </thead>
				 
				 <tbody></tbody>
			    
			    </table>
	         </ext:panel>
		 
		   
</ext:panel>
</ext:body>
