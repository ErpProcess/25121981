<script >  
 var mapColumsbean=[  
  { "sTitle": "${vente_id}"       ,"sName": "vente_id"                     ,"sWidth": "10%"   , "bSortable": "true"   },  
  { "sTitle": "${vente_date}"        ,"sName": "vente_date"                     ,"sWidth": "15%"   , "bSortable": "true"   },
  { "sTitle": "Heure"              ,"sName": "time_cre"                     ,"sWidth": "15%"   , "bSortable": "true"   },   
  { "sTitle": "${clt_id}"         ,"sName": "client.clt_lib"                     ,"sWidth": "50%"   , "bSortable": "true"   },
  { "sName": "devise.dev_id"     ,"bVisible": false   }, 
  { "sTitle": "Total TTC"         ,"sName": "vente_mnt_total"      ,"sWidth": "20%"    ,"sClass" : "alignRight"   , "bSortable": "true"  ,
	  "mRender": function (data, type, full) {   if( full[4]=="191"  ||  full[4]=="192") return  formatNumberJsXC(data,2); else  return formatNumberJsXC(data,3); }  },    
  { "sTitle": "${_mode}"          ,"sName": "modeBean.fct_libelle"                     ,"sWidth": "20%"   , "bSortable": "true"   },  
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
			 var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL_GRID','text',false);
			 var firstitems  = {"3":"Total"    ,"4":json   };
			 var items       = {"A":firstitems };
			 return  items; 
			 }
 </script>