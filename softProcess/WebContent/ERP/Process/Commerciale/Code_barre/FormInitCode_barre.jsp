 <%@include file="/Aceuil/esProcess.jsp" %>  
 <c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 
  <style>
	.tabledegree_code_barr {
	width:90%;
	border-top:1px solid #e5eff8;
	border-bottom:1px solid #e5eff8;
	border-right:1px solid #e5eff8;
	margin:1em auto;
	border-collapse:collapse;
	}
	.tddegree_code_barr {
	color:#678197;
	border-top:1px solid #e5eff8;
	border-bottom:1px solid #e5eff8;
	border-left:1px solid #e5eff8;
	padding:.3em 1em;
	text-align:center;
	}
	
	.koljjs {
background:#f4f9fe;
text-align:center;
font:bold 0.7em/2em "Century Gothic","Trebuchet MS",Arial,Helvetica,sans-serif;
color:#66a3d3;
}

 
</style>

<script type="text/javascript" > 
$(document).ready(function (){
LoadAutoCompletAjax("pk_article.ar_id","ar_libelle",null,"list_des_article");
});
var lumsbean=[ 
       {      "sTitle": "sss"        , "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {      "sTitle": "sw"        , "sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true     , "mRender": function( data, type, full){
              return  '<input  type="checkbox" value="'+data+'" id=to_check'+full[0]+' name=to_check  '+data+'         nextElement=code_barre'+full[0]+' >';}}, 
                  
	   {      "sTitle": "${code_barre}"        , "sWidth": "10%"    ,"sName": "pk.code_barre"   ,"mRender": function( data, type, full){  
	          return '<input   type="text"   size="20"     id="code_barre'+full[0]+'"          name="pk.code_barre"      value="'+data+'"    >';}}, 
	         
	   {      "sTitle": "${designation}"        , "sWidth": "30%"  ,"sName": "designation"   ,"mRender": function( data, type, full){  
	          return '<input   type="text"  size="70"      id=designation'+full[0]+'    name=designation    value="'+data+'"      >';}},       
	         
	          
	         ]; 
mapEditableGen= {"otab":oTable,"table":"gridcode_barre","list":"list_cod_barr_master","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};

 function loaderCodeBarre(){

 			 
 				
        jQuery.ajax({ type: 'POST',  
	           url: '${tmlx.urlAjax}', 
	           data: {HiddenAction:'i$_ACT_LOAD_CODE_BARR','ID_AR':$('[id="pk_article.ar_id"]').val(),'ID_LIB':$('[id="ar_libelle"]').val()},
	           dataType: 'text', 
	           async: false,
	           success: function(data){
	           contenu_toolbarJQuey="";
	           LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
               config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
                },
               error: function (request, status, error) {
                       alert(request.responseText);
                } 
        });
 }

 

function dochangerVal(element){
		var res="";
		if($(element).attr('type')=="checkbox"){
	    element.value=element.checked==false?"":"checked";
	    res=element.checked==false?"":"checked";
	    }else{
	    res=$(element).attr('value');
	    }
 
        jQuery.ajax({ type: 'POST',  
	           url: '${tmlx.urlAjax}', 
	           data:{HiddenAction:'i$_ACT_UPDATE_LIST_CARACT',id_elment:$(element).attr('id'),l_value:res},
	           dataType: 'text', 
	           success: function(data){
	           //LoadDataEditableFromServer(mapEditableGen);
	            
                },
               error: function (request, status, error) {  
                       alert(request.responseText);
                } 
        });
}   
</script>

 
  <ext:body  >  
  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
  
  <ext:panel  border="false"      > 
  <ext:toolbar  toolbarType="bbar"  >
    <ext:toolbar.button   text="Générer code Barre" style="float:right;"  onClick="loaderCodeBarre()"   ></ext:toolbar.button>
  </ext:toolbar>
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   
    
   <tr  >  
     <td width="7%"><label>${ar_id}</label></td>  
     <td width="93%"  >  
        <input id="pk_article.ar_id" name="pk.ar_bean.pk_article.ar_id"     type="text"    size="20"       maxlength="20"        value=""                    autofocus   required     />  
        <input id="ar_libelle" name="pk.ar_bean.ar_libelle"     type="text"    size="20"       maxlength="20"        value=""                  />
        
        <script type="text/javascript">
				        function loader_CaracTeristique(type_Loder){
				    var  testeInto=true;      
				    var  testecheko=false;      
				      $(":input[required]").each(function (cnt, item) {                     
				        var $myFormxx = $("#myformToServeur");
				          if(!$(item).val()) {  $(item).css('border-color', 'red'); testeInto=false; }else{ $(item).css('border-color', ''); }
				          if($(item).is("input[type=radio]")) {  $(item).addClass("ppd"); 
				          if($(item).is(":checked"))  { $(item).addClass("ppd");    } else{    $(item).addClass("pp");   testecheko=true;      }} 
				          if ($myFormxx[0].checkValidity()) { testeInto=true;} 
				        });
				        if(!testeInto){
				      var frss="   Vous devez entrer le(s) champs vide(s)";
				     Ext.MessageBox.show({
				           title:frss,
				           msg: '   Certains champs sont obligatoires pour que le formulaire soit pris en compte ',
				           buttons: Ext.Msg.OK,
				           fn: showResult,
				           animEl: 'mb4',
				           icon: Ext.MessageBox.WARNING
				       });
				  }else{
				  
				   
				  
				  jQuery.ajax({ type: 'POST',  
					           url: '${tmlx.urlAjax}', 
					           data: {HiddenAction:'i$_ACT_LOAD_CARACTER_ARTICLE',TYPE_LOAD:type_Loder,'ID_AR':$('[id="pk_article.ar_id"]').val()},
					           dataType: 'json',
					           success: function(data){
					           document.getElementById('btn_suivant').disabled=true
					              $('#tabdet_carac > tbody').html('');
					              var trHTML = '<tbody>';
							        for (var h = 0; h <data.myliste.length; h++) {
							          
							          trHTML +='<tr class="tddegree_code_barr"  >';
							          trHTML +='<td class="koljjs"  >'+data.myliste[h].libelle+'</td>';
							          
							           trHTML +='<td><table><tr>';
							             for (var g = 0; g <data.myliste[h].lis_det.length; g++) {
							                  trHTML +='<td    style="padding-right:90px;" ><input  type="checkbox"  id="'+data.myliste[h].code+'£'+data.myliste[h].lis_det[g].coded+'"   onclick="dochangerVal(this)"    value=""     >'+data.myliste[h].lis_det[g].libelled+'</td>';
							              }
							             trHTML +='</tr></table></td>';
							            
							         trHTML +='</tr>';
							        }
							        trHTML +='</tbody>';
							          
								   $('#tabdet_carac').append(trHTML);
					           
				                },
				               error: function (request, status, error) {
				                       alert(request.responseText);
				                } 
				        });
				  }
				}
        
        
        </script>
        <input    type="button"  id="btn_suivant"    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  
         value="suivant" onclick="loader_CaracTeristique('C');"  />
     </td>  
   </tr>  
   
      
    
   <tr>  
     <td colspan="2"><label>Liste Caractéristique   </label></td>  
       
   </tr>
   
   
    <tr>  
     <td colspan="2"> 
     
     <table  border="1"  style="background-color: white;width: 100%;"    class="tabledegree_code_barr"   id="tabdet_carac" >
        <thead> 
        
          <tr class="koljjs"     >
              <td      class="tddegree_code_barr"  width="15%" >Caractéristique</td>
              <td    width="80%"  class="tddegree_code_barr"  >Détaille Caractéristique</td>
		  </tr>
	    </thead>
	   
     </table>
     </td>  
   </tr>
 </table>  
 </ext:panel> 
 
<ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="code a barre " > 
	<table id="gridcode_barre" class="display" width="100%" >
	</table>
</ext:panel>
 
</ext:panel>
</ext:body>
