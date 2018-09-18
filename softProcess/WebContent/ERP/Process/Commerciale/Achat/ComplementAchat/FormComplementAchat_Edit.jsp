<%@include file="/Aceuil/esProcess.jsp" %> 
 <script type="text/javascript">
 config_header_foot_tableJQuey ='<"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lf<"toolbar_es">r>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>';
 contenu_toolbarJQuey          ='<b><input  type="button"  value="+"   onclick="ADD()"  style="font-size: 12px;width: 20px;" >&nbsp;&nbsp;&nbsp;<input  type="button"  value="-"  onclick="Delete_ROW()"   style="font-size: 12px;width: 20px;"  ></b>';
</script>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>


<script type="text/javascript">
/*
function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=list_editable_comp_achat",'i$_ACT_VERIF_LIST','text',false);
 
return  retournX ==""?"":"Veillez Remplir le détaille du complement ";
}*/
$(document).ready(function (){
LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","num_lot","list_article_recp_achat","400","500");
LoadOtherAutocompletesAjax("pk.code_barre","i$_ACT_LOAD_LOT_ARTICLE","num_lot","date_peremption","quantite");
});



width_tabbJQuey="100%";

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
									                  
										   {      "sName": "pk.fkCode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sName": "pk.fkCode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										          
										   {      "sWidth": "10%"  , "sName": "lot.num_lot"    },   
										    
										   {      "sWidth": "10%"  , "sName": "lot.date_peremption" ,  "bVisible": true  },  
										   
										     
										    
										                 
										   {      "sWidth": "10%"  , "sName": "quantite"    },
										          
										   {      "sWidth": "10%"  , "sName": "quantite_ajouter"    , "mRender": function( data, type, full){
									              return  '<input  type="number"     id=quantite_ajouter'+full[1]+'  name=quantite_ajouter    value="'+data+'"    style="width:75px;"     onblur=doEnvoiDataV2(this,"'+full[1]+'")    >';} },      
										  
										   {      "sWidth": "10%"  , "sName": "unitBean.unite_lib"  },
										           
										           
										   {      "sTitle":"Prix_U_A"  , "sName": "prix_unit_achat"      ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                       {      "sTitle":"Mnt_T_H_A" , "sName": "montant_ht_achat"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
										    {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },
										           
	                                            ]
 
                               };



 
 
$(document).ready(function () {
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});

 
 



function ADD(){

 var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ADD_ROW','text',false);
  
}  


function Delete_ROW(){
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW',
	               dataType: 'text', 
	               success: function(data){
	                LoadDataEditable_AUTO_height( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
                      },
                   error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
}

function doCheked_unCheked(element){
		var res="";
		if($(element).attr('type')=="checkbox"){
	    element.value=element.checked==false?"":"checked";
	    res=element.checked==false?"":"checked";
	    }
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_Cheked_unCheked&statucheked='+res,
	               dataType: 'text', 
	               success: function(data){
	             
				   LoadDataEditable_AUTO_height( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
				   config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
	               
	               
	               
	               
                      },
                 error: function (request, status, error) {
                         alert(request.responseText);
                  } 
    });
} 





function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(0)').html() ;
            var QteNew="Qte"+qsdqsqd;
            var QteNew_Res="Res"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
            
          $(this).find('td:eq(5)').html(json[QteNew_Res]) ;
	      $(this).find('td:eq(9)').html(json[qsdqsqd]) ;
	      $(this).find('td:eq(6)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	    
	      if(  json[erreurX]  !=""){
	           mayBox_al(json[erreurX] ,"xx");
	      }
	      
	   });
 
 } 				                        
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
					   <input id="depot_libelle" name="achat.pk.depot.depot_libelle"     type="text"    size="30"       maxlength="10"        value="${detailBean.achat.pk.depot.depot_libelle}"    nextElement="btValidx"   required     />
		 
		    </td>  
		   </tr> 
		    
		    
		   <tr>  
		   <td width="10%"><label>${complet_date}</label></td>  
		   <td width="90%"  > 
		     <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.complet_date}"   var="detailBeanRetour_date"/>  
			 <input id="complet_date" name="complet_date"     type="text"    size="10"                value="${detailBeanRetour_date}"    nextElement="btValidx"                  required    />  
		    </td>  
		   </tr> 
		   
		   
		  
		 </table> 
    
    
    
     </td>
   </tr>
   
   </table>
     
		
	 </ext:panel>
	 <ext:tabPanel  activeTab="RET_GRID"  title="Détaille retour achat"  border="true"  style="padding:5px 5px 5px 5px;" >
	         <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Détaille retour achat"    > 
	          
	        
			    <table id="GRID_SAISIE_DETAIL_AHCAT" class="display" width="100%" >
			    
			      <thead   >
					 
					 <tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
						<th></th>
						<th>
						    <input  type="text"     id="pk.code_barre"         name="pk.fkCode_barre.pk.code_barre"            style="width: 100%;"    requiredx ></th>
						<th><input  type="text"     id="designation_libelle"   name="pk.fkCode_barre.designation_libelle"      style="width: 100%;"    requiredx ></th>
						
						<th><input  type="text"     id="num_lot"               name="lot.num_lot"      style="width: 70px;"    nextElement="quantite_ajouter"  requiredx ></th>
						
						<th><input  type="text"     id="date_peremption"       name="lot.date_peremption"      style="width: 70px;"    requiredx   readonly="readonly"  > </th>
						
						<th><input  type="number"   id="quantite_ajouterx"              name="quantite_ajouterx"     size="7"    style="width: 70px;"    nextElement="unite"     requiredx ></th>
						<th></th>
						<th></th>
						 
						<th></th>
						<th></th>
					</tr>
					
					 
					 
					 <tr> 
						<th></th>
						<th>Référence</th>
						<th>Désignation</th>
						<th>Lot</th>
						<th>Date.Lot</th>
					 
						<th>Qté.Reçue</th>
						<th>Qté.Ajouter</th>
						<th>Unite</th>
						<th>P.U.HT</th>
						<th>Mnt.T.HT</th>
						<th></th>
				    </tr>
				 </thead>
				 
				 <tbody></tbody>
			    
			    </table>
	         </ext:panel>
     </ext:tabPanel>
		 
		   
</ext:panel>
</ext:body>
