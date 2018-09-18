<script >  
dataGridConfig_width="110%"; 
doubleclickGrid=false;
 var mapColumsbean=[  
 
    {      "sTitle":"fournisseur"    ,"sName": "recep.frsBean.frsref"    ,"sWidth": "8%"   },
    
    {      "sTitle":"Bon Reception"  ,"sName": "recep.achat_id"    ,"sWidth": "9%"   },
    
    {      "sTitle":"Qté.Réçu"      ,"sName": "incident.pk.serieBean.quantite_init"    ,"sWidth": "4%"   },
    
    {      "sTitle":"Désignation"    ,"sName": "pk.detv.pk.fkcode_barre.designation_libelle"    ,"sWidth": "20%"   },       
										    
    {      "sTitle":"Qte.Vendu"    ,"sName": "pk.detv.quantite_confirmer"  , "sWidth": "4%"  },
										                 
	{      "sTitle":"Qte.Retour"        ,"sName": "quantite_retourne"      , "sWidth": "4%"    },
	
	{      "sTitle":"Prix.U.A"       , "sName": "incident.pk.serieBean.tarif.tarif_unit_article"    ,"sClass" : "alignRight"     , "mRender": function (data, type, full) {return formatNumberJs(data,3);}    ,"sWidth": "15%"     }, 
	
	{      "sTitle":"Total.H.T"      , "sName": "d_recep.montant_ht_achat"      ,"sClass" : "alignRight"    ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    ,"sWidth": "15%"   }   ,      
										  
	{     "sTitle":"Unite"           , "sName": "pk.detv.pk.fkcode_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "6%"     },
										    
    {     "sTitle":"cause"           , "sName": "cause.nature_incident_lib"       ,"sWidth": "30%"    },
										    
    {     "sTitle":"Stock"           , "sName": "sens.data_libelle"       ,"sWidth": "10%"     },
										           
   
	                                
	  
  ];
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />

<table id="${nameGrid}"   class="display"  style="width: 100%;"  > 
 
			    <tfoot>
				    <tr><td   height="50px" colspan="11" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
								<td ></td>
								<td ></td>
								 
								<td colspan="2"></td>
								 
								<td colspan="3" ></td>
								<td ></td>
								<td ></td>
								<td ></td> 
							 
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="7">
		                    <tr align="right"> 
								<td colspan="4"></td>
								<td   ></td>
								<td  ></td>
								<td ></td>
								<td ></td> 
								<td ></td> 
								<td ></td> 
								<td ></td>
						    </tr>
					</c:forEach>	
				 </tfoot>
			    </table>
			    
			     <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					 
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL_AFFIR','json',false);
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
 
       