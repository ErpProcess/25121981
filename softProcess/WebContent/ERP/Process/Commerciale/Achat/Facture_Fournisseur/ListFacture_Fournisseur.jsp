<script>  
 var mapColumsbean=[  
  { "sTitle": "${fact_id}"       ,"sName": "fact_frs_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${four_id}"       ,"sName": "frs.frsref"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${fact_date}"       ,"sName": "fact_date"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
   
  
  { "sTitle": "Total TTC"         ,"sName": "total_facture"      ,"sWidth": "15%"    ,"sClass" : "alignRight"   , "bSortable": "true"  ,
	  "mRender": function (data, type, full) {   return formatNumberJsXC(data,3); }  },
	  
	  
	  
  { "sTitle": "Etat.Reg"                  ,"sName":"etat_reg.data_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },   
  { "sTitle": "${mode_op}"                 ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  ];
</script>
<jsp:include page="${context_path}/dataGridSetting/dataGridConfig.jsp" />

<table id="${nameGrid}" class="display" style="width: 100%;">
	<tfoot>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</tfoot>
</table>

<script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL_GRID','text',false);
				        var firstitems  = {"2":"Total"    ,"3":json   };
				        var items       = {"A":firstitems };
				    return  items; 
				}
 </script>
