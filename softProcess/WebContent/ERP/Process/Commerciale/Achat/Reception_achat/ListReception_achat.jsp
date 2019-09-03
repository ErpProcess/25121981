<script >  
 var mapColumsbean=[  
  
  { "sTitle": "demande"           ,"sName": "demande_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_id}"       ,"sName": "achat_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${achat_date}"     ,"sName": "achat_date"                   ,"sWidth": "10%"   , "bSortable": "true"   },
  { "sTitle": "${achat_time}"     ,"sName": "time_cre"                        ,"sWidth": "10%"   , "bSortable": "true"   }, 
  { "sTitle": "${frs_id}"         ,"sName": "frsBean.frsref"                  ,"sWidth": "10%"   , "bSortable": "true"   },  
  
  
  { "sTitle": "Total"       ,"sName": "achat_mnt_total"     ,"sClass" : "alignRight"   ,"sWidth": "15%"   , "bSortable": "true"   ,
	   "mRender": function (data, type, full) {return formatNumberJs(data,3);}  },
	   
	   
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"             ,"sWidth": "5%"   , "bSortable": "true"   },
     
  ];
</script> 
 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" />

<table id="${nameGrid}"   class="display"  style="width: 100%;"  > 
			    <tfoot>
		                    <tr  > 
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
						    </tr>
				 </tfoot>
			    </table>
			    
			     <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL_GRID','json',false);
					    var totMntList     =json["totMntList"];
				        var firstitems  = {"5":"Total"    ,"5":totMntList   };
				        var items       = {"A":firstitems };
				    return  items; 
				}
               </script>
