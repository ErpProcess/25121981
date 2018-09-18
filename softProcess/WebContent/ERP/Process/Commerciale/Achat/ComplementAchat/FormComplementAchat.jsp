<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


<script type="text/javascript">

/*function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=list_editable_retour_achat",'i$_ACT_VERIF_LIST','text',true);
return  retournX ==""?"":"Veillez Remplir le détaille de la retour ";
}*/



width_tabbJQuey="100%";
contenu_toolbarJQuey="";
$(document).ready(function () {
    $("#tblData").find("select:not([libre])").attr("disabled",true);
    $("#tblData").find("input[idonly]").attr("readonly",true);	
    $("#tblData").find("input:not([libre]),button:not([libre]),textarea:not([libre])").attr("readonly",true);  
});

 mapEditableGen = {            "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_AHCAT",
                               "list"   :"list_editable_comp_achat",
                               "id_name":"pk.fkCode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									       {      "sName": "indx_row"  ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
									                  
										   {     "sTitle": "${code_barre}"       , "sName": "pk.fkCode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {     "sTitle": "${designation_libelle}"       , "sName": "pk.fkCode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										          
										   {     "sTitle": "${num_lot}"       , "sName": "lot.num_lot" ,"sWidth": "10%"    },   
										   
										   
										    
										   {     "sTitle": "date Lot"       , "sName": "lot.date_peremption"  , "sWidth": "10%"  ,  "bVisible": true  }, 
										   
										    
										                 
										   {    "sTitle": "Qté Reçu"                ,"sName": "quantite"  , "sWidth": "10%"   },
										  
										   {    "sTitle": "Qté (+)"       ,"sName": "quantite_ajouter" , "sWidth": "10%"  },
										   
										   {   "sTitle": "${unite_lib}"       ,"sName": "unitBean.unite_lib" , "sWidth": "10%"  },
										           
										           
										   {      "sTitle":"Prix_U_A"  , "sName": "prix_unit_achat"      ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Mnt_T_H_A" , "sName": "montant_ht_achat"  ,"sClass" : "alignRight"   ,"sWidth": "8%"    , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },
										           
	                                            ]
 
                               };



 
 
$(document).ready(function () {
 LoadDataEditable_AUTO_height( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
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
			<input id="frsref" name="achat.frsBean.frsref"       libre  readonly="readonly"      type="text"    size="30"            value="${detailBean.achat.frsBean.frsref}"          nextElement="depot_id"   required    />
		  </td>  
		   </tr>   
		    
		  
		    
		    
		   
		 </table> 
    
    
    
    
    </td>
    
    
    
        <td width="70%">
    
     <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   width="100%"    >  
		   <tr>  
		   <td width="20%"><label>${achat_id}</label></td>  
		   <td width="90%"  >  
		 
		   <input id="achat_id" name="achat.pk.achat_id"     type="text"    size="15"       maxlength="15"        value="${detailBean.achat.pk.achat_id}"    nextElement="achat_libelle"        libre   readonly="readonly"      />  
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
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.achat.pk.achat_date}"   var="detailachat_date"/> 
		   <input id="achat_date" name="achat.pk.achat_date"     type="text"    size="13"       maxlength="13"        value="${detailachat_date}"    nextElement="frs_id"              />  
		  </td>  
		   </tr>   
		   
		   <tr>  
		   <td width="10%"><label>${depot_id}</label></td>  
		   <td width="90%"  >  
					   <input id="depot_id" name="achat.pk.depot.depot_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.achat.pk.depot.depot_id}"    nextElement="btValidx"                  required    />  
					   <input id="depot_libelle" name="achat.pk.depot_stocks.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.achat.pk.depot.depot_libelle}"    nextElement="btValidx"   required     />
		 
		    </td>  
		   </tr> 
		    
		    
		    <tr>  
		   <td width="10%"><label>${complet_id}</label></td>  
		   <td width="90%"  > 
		    
			 <input id="complet_id" name="complet_id"     type="text"    size="10"                value="${detailBean.complet_id}"    nextElement="complet_date"                  required    />  
		    </td>  
		   </tr> 
		   
		    
		   <tr>  
		   <td width="10%"><label>${complet_date}</label></td>  
		   <td width="90%"  > 
		     <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.complet_date}"   var="detailBeanRetour_date"/>  
			 <input id="complet_date" name="retour_date"     type="text"    size="10"                value="${detailBeanRetour_date}"    nextElement="btValidx"                  required    />  
		    </td>  
		   </tr> 
		   
		   
		  
		 </table> 
    
    
    
     </td>
   </tr>
   
   </table>
     
		
	 </ext:panel>
	 
	 
	 <ext:tabPanel  activeTab="RET_GRID"  title="Détaille retour achat"  border="true"  style="padding:5px 5px 5px 5px;" >
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille retour achat"    > 
	          
	          		
			    <table id="GRID_SAISIE_DETAIL_AHCAT" class="display"  width="100%" >
			   
			
				 <tfoot  style=" border-top: 10px solid white;"  >
				 
				   <tr align="right"     > 
						<td  ></td>
						<td  ></td>
						<td  ></td>
						<td></td>
						<td></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td  ></td>
						<td  ></td> 
						<td  ></td>
						<td></td>
						<td ></td>
						<td></td>
				    </tr>
				    <tr align="right" > 
						<td  ></td>
						<td  ></td> 
						<td   ></td>
						<td></td>
						<td></td>
						<td></td>
				    </tr>
				    
				      
				 </tfoot>
			    </table>
			    <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    
					    var qtessS    =json["UU"];
                        var tot_h_tvaS=json["k"];
			            var tot_tvaS  =json["o"];
			            var tot_GenS  =json["p"];
			            
			            var firstitemSSs  = {"0":""    };
			            
				        var firstitems  = {"0":"Total Qté retournée"    ,"1":qtessS   ,"3":"Total.H.T"  ,"4":tot_h_tvaS  };
				        var secondItems = {"3":"Total TVA"    ,"4":tot_tvaS };
				        var TroisIemIte = {"3":"Total Général","4":tot_GenS };
				        var items       = {  "A":firstitems,"B":secondItems,"C":TroisIemIte};
				    return  items; 
				}
               </script>
			    
	         </ext:panel>
     </ext:tabPanel>
		 
		   
</ext:panel>
</ext:body>
