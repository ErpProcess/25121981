


<script >
var  mapColumsbean=[ 
						{"sTitle": "code" , "sName": "idLiblleBean.lib_id", "bSortable": "true"  , "sType": "numeric-comma" ,"asSorting": "asc" },
						{"sTitle": "libelle" , "sName": "lib_libelle", "bSortable": "true"},
						{"sTitle": "abreviation" , "sName": "lib_abrv", "bSortable": "true"},
					];	
						
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" /> 


<div   id="divDataTable"    style="width: 100%;overflow: scroll;background-color: rgb(247, 247, 247);"   >
<table id="libelleGrid"     class="display"   style="width: 100%;overflow: scroll;background-color: rgb(247, 247, 247);"  >	
<thead >
		<tr>
			<th width="150px;" >code</th>
			<th width="350px;" >libelle</th>
			<th width="700px;" >abreation</th>
		</tr>
	</thead>

</table>
</div>




 


 
 















