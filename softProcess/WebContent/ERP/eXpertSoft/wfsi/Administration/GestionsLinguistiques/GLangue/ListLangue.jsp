<script >
var mapColumsbean=[{ "sTitle": "Codes"        ,"sName": "lang_id"       , "bSortable": "true" , "sType": "numeric-comma" ,"asSorting": "asc" },
				   { "sTitle": "Libelle"      ,"sName": "lang_libelle"  , "bSortable": "true"   },
				   { "sTitle": "abrv"         ,"sName": "lang_abrv"     , "bSortable": "true"   },
				   { "sTitle": "observation"  ,"sName": "lang_obs"      , "bSortable": "true"   },];		
</script> 
<jsp:include  page="${context_path}/dataGridSetting/dataGridConfig.jsp" /> 
<div id="divDataTable"  style="width: 100%;overflow: scroll;background-color: rgb(247, 247, 247);"  >
<table id="langueGrid"   class="display"  style="width: 100%;"  >	
<thead >
		<tr>
			<th width="150px;" >code</th>
			<th width="350px;" >libelle</th>
			<th width="300px;" >abrv</th>
			<th width="300px;" >Observation</th>
		</tr>
	</thead>
	
</table>
</div>















