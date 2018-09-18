<%@include file="/Aceuil/esProcess.jsp" %>  
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
<script type="text/javascript">
mapEditableGen = 
 	   {     "otab"   :oTable,
             "table"  :"GRID_article_lot_detaille",
             "list"   :"list_detaille_mvt_lot",
             "id_name":"indx_row",
             "url"    :"${urlloadDataTableAjax}",
             "action" :"i$_ACT_LOAD_EDITABLE_TABLE_AJAX",
             "mapCol" :[ 
                        {     "sTitle": "Prix U"         ,    "sName": "indx_row"      ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
                        {     "sTitle": "date mvt "      ,    "sName": "date_mvt_serie"     			 ,"sWidth": "10%"    }, 
					    {     "sTitle": "nature Mvt"     ,    "sName": "pk.nat_mvt.nature_mvt_libelle"       ,"sWidth": "10%"   }, 
					    {     "sTitle": "code"           ,    "sName": "pk.document_com_id"   			  ,"sWidth": "10%"   }, 
					    {     "sTitle": "Code Tarif"     ,    "sName": "tarif_operation_id" 		 ,"sWidth": "10%"   }, 
					     
  						{     "sTitle": "Qte mvt"        ,    "sName": "quantite_operation" 		  ,"sWidth": "10%"   }, 
						{     "sTitle": "P.U.A"          ,    "sName": "tarif_unit_achat"             ,"sWidth": "10%"     ,"sClass" : "alignRight"   ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}   }, 
	                    {     "sTitle": "P.U.V"          ,    "sName": "tarif_unit_vente"             ,"sWidth": "10%"     ,"sClass" : "alignRight"   ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}    }, 
	                    
					    {     "sTitle": "Mnt H.T"        ,    "sName": "montant_ht_operation"         ,"sWidth": "10%"   ,"sClass" : "alignRight"   ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}   }, 
					    {     "sTitle": "Mnt T.V.A"      ,    "sName": "montant_tva_operation"         ,"sWidth": "10%"  ,"sClass" : "alignRight"   ,"mRender": function (data, type, full) {return formatNumberJs(data,3);}   }, 
										         
					    ]
	 }      
$(document).ready(function () {
contenu_toolbarJQuey="";
LoadDataEditable_AUTO_height( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});                         
       </script>
       
  <ext:body  >
    <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
    
   
   
    <ext:panel  border="false"    bodyStyle="background: none;"         >  
    
    <table  width="100%"><tr><td  width="50%">
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"    width="100%"     >  
		   <tr>  
		   <td width="20%"><label>${num_lot}</label></td>  
		   <td   >  
		   <input id="pk.num_serie" name="pk.num_serie"     type="text"    size="25"       maxlength="25"        value="${detailBean.pk.num_serie}"    nextElement="date_fabrication"    autofocus   required     />  
		  </td>  
		   </tr>   
		   
		    <tr>  
		   <td  ><label>date serie</label></td>  
		   <td   >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_serie}"   var="detailBeandateisatdion"/> 
		   <input id="date_serie" name="date_serie"     type="text"    size="10"            value="${detailBeandateisatdion}"              />  
		  </td>  
		   </tr>   
		   
		   
		   
		   <tr>  
		   <td  ><label>${date_fabrication}</label></td>  
		   <td   > 
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_fabrication}"   var="detailBeandate_fabrication"/>  
		   <input id="date_fabrication" name="date_fabrication"     type="text"    size="13"       maxlength="13"        value="${detailBeandate_fabrication}"    nextElement="date_utilisation"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${date_utilisation}</label></td>  
		   <td    >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_utilisation}"   var="detailBeandate_utilisation"/> 
		   <input id="date_utilisation" name="date_utilisation"     type="text"    size="13"       maxlength="13"        value="${detailBeandate_utilisation}"    nextElement="date_peremption"              />  
		  </td>  
		   </tr>   
		   <tr>  
		   <td  ><label>${date_peremption}</label></td>  
		   <td  > 
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.date_peremption}"   var="detailBeandate_peremption"/> 
		   <input id="date_peremption" name="date_peremption"     type="text"    size="13"       maxlength="13"        value="${detailBeandate_peremption}"                />  
		  </td>  
		   </tr>    
		 </table>
	   </td><td  width="50%"  >	 
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  width="100%"     >  
		   <tr>  
		   <td width="20%"><label>Article</label></td>  
		   <td    >  
		   <input id="fkCode_barre.pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="10"                value="${detailBean.fkCode_barre.pk.code_barre}"       />  
		   <input id="fkCode_barre.pk.code_barre" name="fkCode_barre.pk.code_barre"     type="text"    size="30"             value="${detailBean.fkCode_barre.designation_libelle}"       />
		  </td>  
		   </tr>   
		   <tr>  
		   <td ><label>depot</label></td>  
		   <td   > 
		   <input id="depot_id" name="pk.depot.depot_id"     type="text"    size="10"             value="${detailBean.pk.depot.depot_id}"       />
		   <input id="depot_libelle" name="pk.depot.depot_libelle"     type="text"    size="30"             value="${detailBean.pk.depot.depot_libelle}"       />
		  </td>  
		   </tr>   
		  
		   <tr>  
		   <td ><label>source</label></td>  
		   <td   > 
		    <input id="nature_mvt_libelle" name="nature_mvt.nature_mvt_libelle"     type="text"    size="10"             value="${detailBean.nature_mvt.nature_mvt_libelle}"                /> 
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="30"              value="${detailBean.mvt_com_id}"                />  
		  </td>  
		   </tr> 
		   
		   <tr>  
		   <td ><label>Tarif.Achat</label></td>  
		   <td   > 
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="10"               value="${detailBean.tarif.tarif_unit_article}"  /> 
		    <span  style="float: right;">
		    <label>Qte.Initiale</label>
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="5"               value="${detailBean.quantite_init}"                />
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    </span>   
		  </td>  
		   </tr> 
		   
		    <tr>  
		   <td ><label>Qte.dispo</label></td>  
		   <td   > 
		    <input id="quantite" name="quantite"     type="text"    size="10"               value="${detailBean.quantite}"  /> 
		    <span  style="float: right;">
		    <label>Mode.actuelle</label>
		    <input id="mvt_com_id" name="etat.data_id"       type="hidden"    size="5"               value="${detailBean.etat.data_id}"                />
		    <input id="dataLib" name="etat.data_libelle"     type="text"    size="5"               value="${detailBean.etat.data_libelle}"                />
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    </span>   
		  </td>  
		   </tr> 
		   
		  <tr>  
		   <td ><label>Tarif.Vente</label></td>  
		   <td   > 
		    <input id="mvt_com_id" name="mvt_com_id"     type="text"    size="20"               value=""                /> 
		     <span  style="float: right;">
		     <label>Série bloquée</label> 
		     <input  isboolean  id="serie_bloque"              name="serie_bloque"     type="checkbox"        value="${detailBean.serie_bloque}"   />
		        &nbsp;&nbsp;&nbsp;&nbsp;
		    </span>  
		     &nbsp;&nbsp;&nbsp;&nbsp;
		    <input     id="selected"      name="selected"     type="hidden"        value="${detailBean.selected}"   />
		    Ordre<input     id="serie_ordre"         name="serie_ordre"  size="5"   type="text"   readonly  libre      value="${detailBean.serie_ordre}"   />
		  </td>  
		  </tr> 
		      
		 </table>
	</td></tr>
</table>	 
		 
		 
</ext:panel>

       <ext:panel   id="RET_GRID"   bodyStyle="background: none;"    border="false"      title="Liste des articles"    >
       <table id="GRID_article_lot_detaille" class="display" width="99%" >
       <tfoot>
				   <tr align="right"> 
						<td colspan="4"></td>
						<td align="center"></td>
						<td ></td>
						<td  align="right"></td>
						<td  align="right"  ></td>
				    </tr>
				    <tr align="right"> 
						<td colspan="6"></td>
						<td colspan="2" align="center"  ></td>
				    </tr>
				 </tfoot>
			    </table>
			    <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var qtessS    =json["tq"];
                        var tot_h_tvaS=json["tht"];
			            var tot_tvaS  =json["ttva"];
			            var tot_GenS  =json["tg"];
				        var firstitems  = {"0":"Total Qté"    ,"1":qtessS   ,"3":tot_h_tvaS  ,"4":tot_tvaS  };
				        var TroisIemIte = {"0":"Total Général","1":tot_GenS };
				        var items       = {"A":firstitems ,"D":TroisIemIte};
				    return  items; 
				}
               </script> 
       </ext:panel>

</ext:panel>
</ext:body>
