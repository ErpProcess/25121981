 <%@include file="/Aceuil/esProcess.jsp" %>
  <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
  
  <script type="text/javascript">
height_tabbJQuey="auto";
contenu_toolbarJQuey="";
width_tabbJQuey="100%";
 
mapEditableGen = {             "otab"   :oTable,
                               "table"  :"GRID_SAISIE_DETAIL_RETOUR",
                               "list"   :"list_REtourVente_resultat",
                               "id_name":"pk.detv.pk.fkcode_barre.pk.code_barre",
                               "url"    :"${urlloadDataTableAjax}",
                               "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
                               "mapCol" :[ 
									  
									                  
										   {      "sTitle":"Référence"    ,"sName": "pk.detv.pk.fkcode_barre.pk.code_barre"   ,"sWidth": "10%"    }, 
										         
										   {      "sTitle":"Désignation"    ,"sName": "pk.detv.pk.fkcode_barre.designation_libelle"    ,"sWidth": "30%"   },       
										    
										   {      "sTitle":"Qte.Facturée"    ,"sName": "pk.detv.quantite_confirmer"                    , "sWidth": "5%"    ,"bSearchable": true  , "bSortable": true,"bVisible": true  },
										                 
										   {      "sTitle":"Qte.Avoir"    ,"sName": "quantite_retourne"                             , "sWidth": "5%"    },
										  
										    {     "sTitle":"Unite"    , "sName": "pk.detv.pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "10%"     ,"bSearchable": true },
										           
										           
										   {      "sTitle":"Prix U"    , "sName": "pk.detv.tarif.tarif_unit_vente"   ,"sWidth": "10%"    ,"sClass" : "alignRight"       , "bSortable": "true" 
	                                              , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	                                    
	                                     {        "sTitle":"TVA" , "sName": "tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	                                          }, 
	                                          
	                                          
	                                          
	                                       {      "sTitle":"Total H T" , "sName": "montant_ht_retour"    ,"sWidth": "10%"     ,"sClass" : "alignRight"     , "bSortable": "true" ,"bVisible": true  
	                                              ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
										         
									 
	                                            ]
 
                               };
 


 
                           
                                   

</script>
  
<script type="text/javascript">
  
 

function getSuiv(){

var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_AVOIR_VENTE','text',false);
  
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
}

 
 
</script>
 
   
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"       title="Critere de recherche"   collapsible="true"    >
 
 
    <ext:toolbar         toolbarType="bbar"   > 
        <ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   onClick="getSuiv()"   id="btnnext"  ></ext:toolbar.button> 
        </ext:toolbar>
        
        
          
 <table width="100%"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
   <tr>  
   <td width="8%"><label>${fact_id}</label></td>  
   <td width="18%"  >  
   <input id="fact_clt_id"   name="factclient.fact_clt_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.factclient.fact_clt_id}"    nextElement="clt_id"    autofocus         />  </td>  
   <td width="10%"  >Avoir N&deg; </td>
   <td width="64%"  ><input id="avoir_id"   name="avoir_id"     type="text"    size="20"       maxlength="20"        value="${detailBean.avoir_id}"    nextelement="clt_id"    autofocus="autofocus"></td>
   </tr>   
   <tr>  
   <td width="8%"><label>${clt_id}</label></td>  
   <td width="18%"  >  
   <input id="clt_id"   name="factclient.client.clt_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.factclient.client.clt_id}"    nextElement="fact_date"              />  </td>  
   <td width="10%"  >date Avoir </td>
   <td width="64%"  >
      
  <fmt:formatDate pattern="dd/MM/yyyy"            value="${detailBean.avoir_date}"      var="searchat_avoir_date"/>
   <input id="avoir_date"   name="avoir_date"     type="datepicker"    size="13"       maxlength="13"        value="${searchat_avoir_date}"                />  

</td>
   </tr>   
   <tr>  
    <td width="8%"><label>${fact_date}</label></td>  
     <td width="18%"  >  
      <fmt:formatDate pattern="dd/MM/yyyy"       value="${detailBean.factclient.fact_date}"      var="searchat_datefac"/>
      <input id="fact_date" name="factclient.fact_date"     type="text"    size="13"      readonly="readonly"  libre       value="${searchat_datefac}"            />
     </td>
    <td width="10%"  >type Avoir </td>
   <td width="64%"  >
   <script type="text/javascript">  </script>
    <script  >$(function() {  loadSelectAjax("type_avoir_idX","list_avoir_type","type_avoir_id","type_avoir_lib","${detailBean.typeAvoir.type_avoir_id}",true);  })</script>
   <select id="type_avoir_idX"   name="typeAvoir.type_avoir_id"         >  </select></td>
   </tr>   
 </table>   
</ext:panel>

    <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="avoir facture"  height="300"> 
	 <table id="GRID_SAISIE_DETAIL_RETOUR" class="display" width="100%" ></table>
	</ext:panel>  

 
</ext:panel>
</ext:body>
