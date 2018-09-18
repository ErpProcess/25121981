 <%@include file="/Aceuil/esProcess.jsp"   %> 
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript" > 

var lumsbean=[ 

        {  "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false },
        
        {  "sName": "to_check"      ,"sWidth": "2%"     ,"bSortable": true     , "mRender": function( data, type, full){
              return  '<input  type="checkbox"         id=to_check'+full[0]+'   name=to_check    '+data+'         value="'+data+'"   onclick=doEnvoiDataV2(this,"'+full[0]+'")                                             >';}}, 
                  
	    {   "sName": "data_id"        ,"bSortable": true     ,"mRender": function( data, type, full){  
	          return '<input   type="text"  size="15"  id=data_id'+full[0]+'   name=data_id    value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")         nextElement=data_libelle'+full[0]+'  >';}}, 
	         
	    {   "sName": "data_libelle"                      ,"bSortable": true    ,"mRender": function( data, type, full){  
	          return '<input   type="text"  size="70"  id=data_libelle'+full[0]+'    name=data_libelle           value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")        nextElement=data_ordre'+full[0]+'     >';}},       
	  
	    {   "sName": "data_ordre"                       ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<input   type="text"   size="7"    id=data_ordre'+full[0]+'       name=data_ordre           value="'+data+'"    onblur=doEnvoiDataV2(this,"'+full[0]+'")        nextElement=obs_det_entite'+full[0]+' >';}},
	    
	    {   "sName": "obs_det_entite"                   ,"bSortable": true      ,"mRender": function( data, type, full){  
	         return '<input   type="text"   size="20"   id=obs_det_entite'+full[0]+'  name=obs_det_entite         value="'+data+'"   onblur=doEnvoiDataV2(this,"'+full[0]+'")       nextElement=ar_id'+full[6]+'           >'; }},
	         
	    {   "sName": "indx_row_next"     ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
	          
	  ]; 
mapEditableGen = {"otab":oTable,"table":"gridEntite_commerciale","list":"list_detail_entite_for_cree","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
 

function Delete_ROW(){
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW&nameList=list_detail_entite_for_cree',
	               dataType: 'text', 
	               success: function(data){
	               LoadDataEditableFromServer(mapEditableGen);
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
	               data:'HiddenAction=i$_ACT_Cheked_unCheked&nameList=list_detail_entite_for_cree&statucheked='+res,
	               dataType: 'text', 
	               success: function(data){
	               LoadDataEditableFromServer(mapEditableGen);
                      },
                      error: function (request, status, error) {
                         alert(request.responseText);
                     } 
    });
}   
$(document).ready(function () {
 LoadDataEditableFromServer_toolbar( mapEditableGen  ,afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  
, width_tabbJQuey  ,  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});


function ADD(){

    var  testeInto=true;      
    var  testecheko=false;      
      $(":input[requiredx]").each(function (cnt, item) {                     
        var $myFormxx = $("#myformToServeur");
          if(!$(item).val()) {  $(item).css('border-color', 'red'); testeInto=false; }else{ $(item).css('border-color', ''); }
          if($(item).is("input[type=radio]")) {  $(item).addClass("ppd"); 
          if($(item).is(":checked"))  { $(item).addClass("ppd");    } else{    $(item).addClass("pp");   testecheko=true;      }} 
          if ($myFormxx[0].checkValidity()) { testeInto=true;} 
        });
        if(!testeInto){
      var frss="${_chamvideRouge}";
     Ext.MessageBox.show({
           title:frss,
           msg: "${_chamobligatoire}",
           buttons: Ext.Msg.OK,
           fn: showResult,
           animEl: 'mb4',
           icon: Ext.MessageBox.WARNING
       });
  }else{
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_ADD_ROW&nameList=list_detail_entite_for_cree&data_idx='+$(data_idx).val()+'&data_libellex='+$(data_libellex).val()+'&data_ordrex='+$(data_ordrex).val()+'&obs_det_entitex='+$(obs_det_entitex).val(),
	               dataType: 'text', 
	               success: function(data){
				 LoadDataEditableFromServer_toolbar( mapEditableGen  ,afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  
, width_tabbJQuey  ,  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
	               $(data_idx).val('');
	               $(data_libellex).val('');
	               $(data_ordrex).val('');
	               $(obs_det_entitex).val('');
	               $(data_idx).focus();
                      },
                  error: function (request, status, error) {
                         alert(request.responseText);
                     } 
    });
  } 
}
</script> 
 
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 
 
  <ext:panel       bodyStyle="background: none;height:80px;"   collapsible="true"     >
	  <table class="tableStyleContent"  cellpadding="5" cellspacing="5"  id="tblData"  border="0"  style="margin-top: 10px;"   >
	      <tr>  
		   <td width="7%"><label>${code_entite}</label></td>  
		   <td width="40%"  >  
		   <input id="code_entite" name="code_entite"     type="text"    size="20"       maxlength="20"    value="${detailBean.code_entite}"    nextElement="libelle_entite"    autofocus   required     />  
		  </td> 
		   <td width="4%"  rowspan="2"  valign="top" >  
		  <label>Observation</label> 
		  </td>
		  <td width="50%"  rowspan="2"  valign="top" >  
		  <textarea  rows="3" cols="60"   id="obs_entite"  name="obs_entite"  nextElement="data_idx"   >${detailBean.obs_entite}</textarea>
		  </td>
		   
		   </tr>   
		   <tr>  
		   <td  ><label>${libelle_entite}</label></td>  
		   <td   >  
		   <input id="libelle_entite" name="libelle_entite"     type="text"    size="50"       maxlength="50"        value="${detailBean.libelle_entite}"    nextElement="obs_entite"              />  
		  </td>  
		   </tr>   
	   </table>  
	 </ext:panel>
 
 <ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Détaille Etite" > 
	 <table id="gridEntite_commerciale" class="display" width="100%" >
	  <thead   >
		<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
			<th></th>
			<th><input  type="checkbox" id="Cheked_unCheked"     name="Cheked_unCheked"             value=""   onclick="doCheked_unCheked(this)"          ></th>
			<th><input  type="text"     id="data_idx"            name="data_idx"         size="7"   value=""    nextElement="data_libellex"   requiredx ></th>
			<th><input  type="text"     id="data_libellex"       name="data_libellex"    size="80"  value=""    nextElement="data_ordrex"     requiredx ></th>
			<th><input  type="number"   id="data_ordrex"         name="data_ordrex"      size="7"   value=""    nextElement="obs_det_entitex" requiredx       style="width: 70px;" ></th>
			<th><input  type="text"     id="obs_det_entitex"     name="obs_det_entitex"  size="7"   value=""    nextElement="data_idx"        onblur="ADD()"  ></th>
			<th></th>
		</tr>
		<tr> 
			<th></th>
			<th>X</th>
			<th>${data_id}</th>
			<th>${data_libelle}</th>
			<th>${data_ordre}</th>
			<th>observation</th>
			<th></th>
	    </tr>
	 </thead>
	 <tbody></tbody>
	
	</table>
	</ext:panel>  
</ext:panel>
</ext:body>
