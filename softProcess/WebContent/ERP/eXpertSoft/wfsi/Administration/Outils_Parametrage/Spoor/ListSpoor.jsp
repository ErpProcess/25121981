

<script type="text/javascript">
var  mapColumsbean=[ 
						{"sName": "lang_id", "bSortable": "true"  , "sType": "numeric-comma" ,"asSorting": "asc" },
						{"sName": "lang_libelle", "bSortable": "true"},
						{"sName": "lang_obs", "bSortable": "true"},
						{"sName": "lang_abrv", "bSortable": "true"},
					];
 					
</script>
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" /> 
<table id="displayDataGrid"   class="display"  style="width: 100%;"  >	
<thead>
			<tr>
				<th>Code</th>
				<th>Libelle</th>
				<th>Adresse</th>
				<th>Ordre</th>
			</tr>
		</thead>
</table>
 














