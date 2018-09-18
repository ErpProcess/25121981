 <%@include file="/Aceuil/esProcess.jsp" %>
 
<script type="text/javascript">
var  nameListAjax   ="&nameList=list_Grid_Editable_For_Annuler";
var  urlsUpdateURL  ="${tmlx.urlAjax}?HiddenAction=i$_ACT_UPDATE_EDITABLE_TABLE_AJAX"+nameListAjax;

function doEnvoiData(element){

	if($(element).attr('type')=="checkbox")
	    element.value=element.checked==false?"no":"yes";
	 
	var idToSendo=$(element).attr('idRow') ;
	var LEvalue=element.value;
	var name_column=element.name;
    var hashmap ={"sNameId":"pk.fcBean.fct_id", "sValueId": idToSendo,"sDataValue":LEvalue,"sNameColumn" :name_column,"sNameList":"list_Grid_Editable_For_Annuler"};
	jQuery.ajax({ 
	     type: 'POST',  
	     url: urlsUpdateURL, 
	     data:hashmap,
	     dataType: 'text', 
	     success: function(data){   }
      });      
}

var sNameBean=[
               {  "sTitle": "N"  ,"sName": "indx_row"    ,"sWidth": "10%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false },
               {  "sTitle": "X"  ,"sName": "to_check"    ,"sWidth": "2%"     ,"bSortable": true     , "mRender": function( data, type, full){
                  var is_checked=full[1]=='yes'?'checked':'';
                  return  '<input idRow='+full[2]+' type=\'checkbox\'   id=to_check'+full[0]+' name=to_check   '+is_checked+'    onclick=doEnvoiData(this)    value='+data+'    >';}},
               {  "sTitle": "Id"            ,"sName": "pk.fcBean.fct_id"        ,"sWidth": "20%"    ,"bSearchable": false  , "bSortable": true,"bVisible": true },
			   {  "sTitle": "Libelle"       ,"sName": "pk.fcBean.fct_libelle"   ,"sWidth": "30%"    ,"bSearchable": true  ,  "bSortable": true,"bVisible": true},
			   
			   {  "sTitle": "Ordre"  ,"sName": "ordre"              ,"sWidth": "30%"   ,"bSearchable": true  ,  "bSortable": true,"bVisible": true    },
	               
	           {  "sTitle": "view_smfct_action"  ,"sName": "view_smfct_action"   ,"sWidth": "30%"   },
	                   
	           {  "sTitle": "next" ,"sName": "indx_row_next"     ,"sWidth": "1%"    ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
	             
               ]; 
$(document).ready(function () {
loadDataFromServerInit();
$('#gridColonSUP tbody tr').live('click', function () {
$(this).toggleClass('row_selected');
} );
});
 

  
        	    
function loadDataFromServerInit(){

$("#PanelSwing").mask("Waiting...");  
if (oTable != undefined) { oTable.fnReloadAjax();  } else {
oTable= $("#gridColonSUP").dataTable({
                "bServerSide": true,
                "sAjaxSource": urlsAjaxSource_def+nameListAjax,
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "oColReorder": {"headerContextMenu": true},
                "bJQueryUI": true,
                "scrollY":"100px",
                //"sDom": "Rlfrtip",
                "aoColumns": sNameBean,
                "fnServerData": function ( sSource, aoData, fnCallback ) {
                 var formFilter= aoData.concat( $("#myformToServeur").serializeArray() );
	                   $.ajax({
	                   "dataType": 'json',
	                   "type": "POST",
	                   "url": sSource,
	                   "data": formFilter,  
	                   "success" : function(json){  
	                          var count = Object.keys(json.aaData).length; 
	                          $("#PanelSwing").unmask();  
	                          if(count==0){
	                          $("#container").hide();
	                          mayBox_al("Aucune Résulat","")   ;
	                          }else{ 
	                          fnCallback(json);
	                           $("#container").show();
	                          }  
	                          } ,
	                          
	                   "error": function (e) {
	                          alert("Erreur  Serveur! Contacter Administration ");
	                    }
	                   });
	                   }
         });/*.makeEditable({  
                sDeleteURL :urlsDeleteURL_def+nameListAjax,
         		sAddURL    :urlsAddURL_def+nameListAjax ,
         		sUpdateURL :urlsUpdateURL,
        	    "aoColumns":sNameBEdit 
       });*/

       
       }
}

</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
		 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
		   <tr>  
		   <td width="7%"><label>${sousmod_id}</label></td>  
		   <td width="93%"  >  
		   <input id="sousmod_id" name="pk.soumBean.sousmod_id"             libre    readonly="readonly"  type="text"    size="5"    value="${detailBean.sousmod_id}"    nextElement="fct_id"    autofocus   required     />  
		   <input id="sousmod_libelle" name="pk.soumBean.sousmod_libelle"   libre    readonly="readonly"  type="text"    size="20"    value="${detailBean.sousmod_libelle}"    nextElement="fct_id"              />
		  </td>  
		   </tr>  
		 </table>
	 
          <div id="container">
			  <div  id="topy" class="x-panel-header"  style="height: 15px;width: 100%;"   >
			     <div  style="margin-top: -4px;">Annuler Affectation fonction- sous module</div> 
			  </div>
		      <div id="demo_jui"> <table id="gridColonSUP" class="display" width="100%" ></table></div>
	      </div>  
</ext:panel>
</ext:body>
