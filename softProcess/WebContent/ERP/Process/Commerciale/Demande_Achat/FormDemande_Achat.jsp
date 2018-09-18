<%@ include file="/Aceuil/esProcess.jsp"      %>

<!-- 
 <script type="text/javascript">
 config_header_foot_tableJQuey ='<"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lf<"toolbar_es">r>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>';
 contenu_toolbarJQuey          ='<b><input  type="button"  value="+"   onclick="ADD()"  style="font-size: 12px;width: 20px;" >&nbsp;&nbsp;&nbsp;<input  type="button"  value="-"  onclick="Delete_ROW()"   style="font-size: 12px;width: 20px;"  ></b>';
</script>

 -->


<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 



<script type="text/javascript" > 

function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=listGridEditable_demande_achat",'i$_ACT_VERIF_LIST','text',false);
return  retournX ==""?"":"Veillez Remplir le détaille de la commande";

}



var lumsbean=[ 
       {      "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {      "sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true     , "mRender": function( data, type, full){
              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[0]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[0]+'")       nextElement="pk_article.ar_id'+full[0]+'"   >';}}, 
                  
	   {      "sWidth": "10%"    ,"sName": "pk_det_dem_achat.fkCode_barre.pk.code_barre"   ,"mRender": function( data, type, full){ 
	          return '<input   type="text"  id="pk.code_barre'+full[0]+'"      name="pk_det_dem_achat.fkCode_barre.pk.code_barre"    style="width: 100%;"     value="'+data+'"    idc="pk.code_barre'+full[0]+'"   onblur=doEnvoiDataV2_Corr($(this).attr("idc"),$("#designation_libelle'+full[0]+'").attr("id"),"'+full[0]+'")     >';}}, 
	         
	   {      "sWidth": "30%"  ,"sName": "pk_det_dem_achat.fkCode_barre.designation_libelle"   ,"mRender": function( data, type, full){
	          return '<input   type="text"  size="70"    id="designation_libelle'+full[0]+'"  name="pk_det_dem_achat.fkCode_barre.designation_libelle"  value="'+data+'"    idc="pk.code_barre'+full[0]+'"   onblur=doEnvoiDataV2_Corr($(this).attr("idc"),$("#designation_libelle'+full[0]+'").attr("id"),"'+full[0]+'")     >';}},       
	          
	   {      "sWidth": "20%"  , "sName": "quantite"    ,"mRender": function( data, type, full){  
	          return '<input   type="text"  size="7"      id=quantite'+full[0]+'    name=quantite    value="'+data+'"      onblur=doEnvoiDataV2(this,"'+full[0]+'")    nextElement=unite'+full[0]+'    >'; }},   
	  
	   {      "sWidth": "20%"  , "sName": "unitBean.unite_lib"      },
	    
	   {      "sWidth": "20%"  , "sName": "observation"       ,"mRender": function( data, type, full){  
	           return '<input   type="text"  id=observation'+full[0]+'  name=observation    onblur=doEnvoiDataV2(this,"'+full[0]+'")     nextElement="pk_article.ar_id'+full[7]+'"  value="'+data+'" >'; }},
	         
	   {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	         ]; 
mapEditableGen= {"otab":oTable,"table":"griddem_achat","list":"listGridEditable_demande_achat","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
 

function doEnvoiDataV2_Corr(element,elment2,value_id_de_la_ligne){
 	    
	var idToSendo=value_id_de_la_ligne ;
	var LEvalue=$('input[id="'+element+'"]').val();
	var name_column=$('input[id="'+element+'"]').attr("name");
	
	 
 	    
    var hashmap ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":mapEditableGen["list"]};
	jQuery.ajax({ 
	     type: 'POST',  
	     url:  urlsUpdateURL_def, 
	     data: hashmap,
	     dataType: 'text', 
	     success: function(data){
	     	
	     	  var LEvalue2     =$('input[id="'+elment2+'"]').val();
			  var name_column2 =$('input[id="'+elment2+'"]').attr("name");
 	     
		      var hashmap2 ={"sNameId":mapEditableGen["id_name"], "sValueId": idToSendo,"sDataValue":LEvalue2,"sNameColumn" :name_column2,"sNameList":mapEditableGen["list"]};
			jQuery.ajax({ 
			     type: 'POST',  
			     url: urlsUpdateURL_def, 
			     data:hashmap2,
			     dataType: 'text', 
			     success: function(data){
			     
			     
			     jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_ACTUALISER_LIST_CORR',
	               dataType: 'text', 
	               success: function(data){ }});
	               
			       }
		      }); 
			   
           }
      }); 
      
              
	 
	 
	 
}

 

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
	               data:{'HiddenAction':'i$_ACT_ADD_ROW','nameList':'listGridEditable_demande_achat','ar_id':$('input[id="pk.code_barre"]').val(),'designation':$('#designation_libelle').val(),'quantite':$('#quantite').val(),'observation':$('#observation').val()},
	               dataType: 'text', 
	               success: function(data){
				   LoadDataEditableFromServer_toolbar( mapEditableGen  ,afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  
					, width_tabbJQuey  ,  config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
	               $('input[id="pk.code_barre"]').val('');
	               $('#designation_libelle').val('');
	               $('#quantite').val('1');
	               $('#observation').val('');
	               $('input[id="pk.code_barre"]').blur();
	               $('input[id="pk.code_barre"]').focus();
                   },
                  error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
  } 
}  


function Delete_ROW(){
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:'HiddenAction=i$_ACT_DELETE_ROW',
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
	               data:'HiddenAction=i$_ACT_Cheked_unCheked&statucheked='+res,
	               dataType: 'text', 
	               success: function(data){
	               LoadDataEditableFromServer(mapEditableGen);
                      },
                 error: function (request, status, error) {
                         alert(request.responseText);
                  } 
    });
}   

 

function loadLesListeCorrlee(objeJson){

 for ( var i=0, ien=objeJson.aaData.length ; i<ien ; i++ ) {
 		var indexo=objeJson.aaData[i][0];
        var idInputCode="pk.code_barre"+indexo;
        var idInputlabel="designation_libelle"+indexo;
        var idInputNextFocus="quantite"+indexo;
        LoadAutoCompletGridV2("pk.code_barre","designation_libelle","list_article_dem_achatGrid",idInputCode,idInputlabel,idInputNextFocus);
      }

      
}
$(document).ready(function () {
 LoadAutoCompletAjax("frs_id","frsref","dem_obs","list_fournisseur_d_achat");        
 height_tabbJQuey="auto";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});

</script> 
 
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   autoScroll="false"    >  
  
	 <ext:panel       bodyStyle="background: none;height:150px;"   collapsible="true"     >
	  <table class="tableStyleContent"  cellpadding="5" cellspacing="5"  id="tblData"  border="0"  style="margin-top: 10px;"   >
	  
	    <tr>  
		   <td width="10%"><label>${dem_achat_id}</label></td>  
		   <td width="90%"  >  
		  
		   <input id="dem_achat_id" name="dem_achat_id"    style=""  type="text"  size="12"               value="${detailBean.dem_achat_id}"         libre  readonly="readonly"         />  
		   </td>  
	    </tr>
	    
	    <tr>  
		   <td width="10%"><label>${dem_date}</label></td>  
		   <td width="90%"  >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.dem_date}"   var="WW"/>
		   <input id="dem_date" name="dem_date"   readonly="readonly"   libre   type="datepicker"     CompareTo="dateSys"    size="7"  maxlength="10"    value="${WW}"   nextElement="four_id"           required    />  
		   </td>  
	    </tr>  
	   
	    <tr>  
	       <td ><label>${four_id}</label></td>  
	       <td  >  
	       <input id="frs_id" name="frsBean.frs_id"         type="text"    size="10"       maxlength="10"     value="${detailBean.frsBean.frs_id}"          nextElement="dem_obs"   required    /> 
	       <input id="frsref" name="frsBean.frsref"        type="text"    size="30"          value="${detailBean.frsBean.frsref}"          nextElement="dem_obs"   required    />    
	       </td>  
	    </tr>  
	    
	   <tr>  
	      <td ><label>${observation}</label></td>  
	      <td   >  
	      <input id="dem_obs" name="dem_obs"       type="text"    size="50"       maxlength="50"        value="${detailBean.dem_obs}"   nextElement="ar_id"     /> 
	     </td>  
	    </tr> 
	   </table>  
	 </ext:panel>
  

	<ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="Détaille Demande Achat" > 
	 <table id="griddem_achat" class="display" width="100%" >
	  <thead   >
		<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
			<th></th>
			<th><input  type="checkbox"   id="Cheked_unCheked"  name="Cheked_unCheked"    onclick="doCheked_unCheked(this)"     value=""     ></th>
			<script type="text/javascript">
			$(document).ready(function (){
			    LoadAutoCompletAjax_with_marGin("pk.code_barre","designation_libelle","quantite","list_article_dem_achat","400","500");
			});
			</script>
			<th><input  type="text"     id="pk.code_barre"          name="pk.code_barre"         size="25"  value=""                           requiredx ></th>
			<th><input  type="text"     id="designation_libelle"    name="designation_libelle"   size="80"  value=""                           requiredx ></th>
			<th><input  type="number"   id="quantite"    min="1"     step="1"   name="quantite"     size="7"  value="1"  style="width: 70px;"    nextElement="unite"     requiredx ></th>
			<th> 
			<input   id="unite"  name="unite"   type="hidden" />   
			</th>
			<th><textarea id="observation"   name="observation"  rows="" cols=""  onblur="ADD()"  nextElement="pk.code_barre" ></textarea></th>
			<th></th>
		</tr>
		 <tr> 
			<th></th>
			<th>X</th>
			<th>code</th>
			<th>${ar_id}</th>
			<th>${quantite}</th>
			<th>${unite}</th>
			<th>${observation}</th>
			<th></th>
	    </tr>
	 </thead>
	 <tbody></tbody>
	
	</table>
	</ext:panel>

</ext:panel>
</ext:body>
