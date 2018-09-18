<%@ include file="/Aceuil/esProcess.jsp"      %>

 




<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import>
 



<script type="text/javascript" > 




function control_de_liste(){
var     retournX = doGenerate_methode_ajaxWithReturn('POST',urls_Generic_def+"?nameList=listGridEditableDevis",'i$_ACT_VERIF_LIST','text',false);
return  retournX ==""?"":"Veillez Remplir le détaille  ";

}



var lumsbean=[ 
       {      "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {      "sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true     , "mRender": function( data, type, full){
              return  '<input  type="checkbox" value="'+data+'"   id=to_check'+full[2]+' name=to_check    '+data+'   onclick=doEnvoiDataV2(this,"'+full[2]+'")       nextElement="pk_article.ar_id'+full[0]+'"   >';}}, 
                  
	   {      "sWidth": "10%"    ,"sName": "pk.code_barre.pk.code_barre"  }, 
	         
	   {      "sWidth": "30%"  ,"sName": "pk.code_barre.designation_libelle"     },       
	          
	   {      "sWidth": "20%"  , "sName": "quantite"    ,"mRender": function( data, type, full){  
	          return '<input   type="number"  size="7"    min="1"   style="width:50px;"   id=quantite'+full[2]+'    name=quantite    value="'+data+'"      onblur=doEnvoiDataV2(this,"'+full[2]+'")    nextElement=unite'+full[0]+'    >'; }},   
	  
	  
	     {      "sName": "pk.code_barre.pk.ar_bean.unitBean.unite_lib"       ,"sWidth": "10%"     ,"bSearchable": true },
	    
	    
	    
	  {      "sTitle":"Prix_U "  , "sName": "tarif.tarif_unit_vente"      ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" 
	            , "mRender": function (data, type, full) {return formatNumberJs(data,3);}  ,"bVisible": true   },     
	                                    
	  {      "sTitle":"Mnt_T_H " , "sName": "montant_ht_vente"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	            ,"mRender": function (data, type, full) {return  formatNumberJs(data,3);  }    },        
	                                              
	   {      "sTitle":"TVA" , "sName": "tarif.tvaBean.tva_libelle"  ,"sClass" : "alignRight"   ,"sWidth": "7%"    , "bSortable": "true" ,"bVisible": true  
	    },                                                 
	                                                    
	   {     "sTitle": "Codes",   "sTitle": "next"    ,"sName": "indx_row_next"        ,"bSearchable": false  , "bSortable": false,"bVisible": false },       
	         ]; 
mapEditableGen= {"otab":oTable,"table":"gridd_devis_vente","list":"listGridEditableDevis","id_name":"pk.code_barre.pk.code_barre","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};
 
 

 

function ADD(){

   lib_required="requiredx";

   if(!teste_required()) return;

    lib_required="required";
 
  jQuery.ajax({ type: 'POST',  
	               url: '${tmlx.urlAjax}', 
	               data:{'HiddenAction':'i$_ACT_ADD_ROW',
	                     'nameList':'listGridEditableDevis',
	                     'ar_id':$('input[id="pk.code_barre"]').val(),
	                     'designation':$('#designation_libelle').val(),
	                     'quantite':$('#quantite').val(),
	                     'clt_id':$('#clt_id').val(),
	                     'observation':$('#observation').val()},
	               dataType: 'text', 
	               success: function(data){
				     height_tabbJQuey="auto";
					 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
					 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
	               	 $('input[id="pk.code_barre"]').val('');
	                 $('#designation_libelle').val('');
	                 $('#quantite').val('1');
	                 $('#observation').val('');
	                 //$('input[id="pk.code_barre"]').blur();
	                 //$('input[id="pk.code_barre"]').focus();
                    },
                  error: function (request, status, error) {
                         alert(request.responseText);
                   } 
    });
  
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

$(document).ready(function () {

LoadAutoCompletAjax("clt_id","clt_lib","dev_lib","list_client_devis");  
LoadAutoCompletAjax("pk.code_barre","designation_libelle","quantite","list_article_devVente"); 
 
 //LoadOtherAutocompletesAjax("clt_id","i$_ACT_LOAD_TARIF_CLIENT",,,"");

if("${bs.fct_id}"=="3"){
controlsur_le_client();
//Ext.getCmp('btnnext').disable(); 
} 

 
});



function controlsur_le_client(){


$("#clt_id").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});


 $("#clt_lib").autocomplete({ 
    maxResults: 10,
    source: function(request, response) {
        var results = $.ui.autocomplete.filter(src, request.term);
        response(results.slice(0, this.options.maxResults));
    }
});

  
  $('#clt_id').attr("readonly","readonly");
  $('#clt_lib').attr("readonly","readonly");
 


}



function getSuiv(){

lib_required="requiredH";

if(!teste_required()) return;
lib_required="required";
   
var jsonText=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_LOAD_DETAIL','text',false);

controlsur_le_client();
$('#gridd_devis_vente').css('display','block');  
Ext.getCmp('RET_GRID').setTitle(' Détaille devis ');
loadGrid();

}
function loadGrid(){

    
 height_tabbJQuey="auto";
 LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
 config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
 
}



function doExcuteFnAfterGrid( dataSS ){

      var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_ACTUALISER_TABLE','json',false);
     
      
       $('#'+mapEditableGen["table"]+' tbody tr').each(function () {
          //var qsdqsqd = $(this).find('td:eq(1)').find(':input[type="text"]').eq(0).attr('value') ;
            var qsdqsqd = $(this).find('td:eq(1)').html() ;
            var QteNew="Qte"+qsdqsqd;
            var erreurX="erreur"+qsdqsqd;
            
	      //$(this).find('td:eq(6)').html(json[qsdqsqd]) ;
	      $(this).find('td:eq(3)').find(':input[type="number"]').eq(0).attr('value',json[QteNew]) ;
	    
	      if(  json[erreurX]  !=""){
	           mayBox_al(json[erreurX] ,"xx");
	      }
	      
	   });
	   LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
       config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
 
 }  
</script> 

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   autoScroll="false"    >  
  
	 <ext:panel     border="false"    bodyStyle="background: none;"      >
	 
	 
	 <ext:toolbar         toolbarType="bbar"   > 
<ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"   id="btnnext"   onClick="getSuiv()"   ></ext:toolbar.button> </ext:toolbar>


	  <table class="tableStyleContent"  cellpadding="5" cellspacing="5"  id="tblData"  border="0"  style="margin-top: 10px;"   >
	  
	    <tr>  
		   <td width="10%"><label>${devis_id}</label></td>  
		   <td width="90%"  >  
		  
		   <input id="devis_id" name="devis_id"    style=""  type="text"  size="15"               value="${detailBean.devis_id}"         libre  readonly="readonly"         />  
		   </td>  
	    </tr>
	    
	    <tr>  
		   <td width="10%"><label>${dev_date}</label></td>  
		   <td width="90%"  >  
		   <fmt:formatDate pattern="dd/MM/yyyy"  value="${detailBean.dev_date}"   var="WW"/>
		   <input id="dev_date" name="dev_date"      type="datepicker"   requiredx   CompareTo="dateSys"    size="7"  maxlength="10"    value="${WW}"   nextElement="clt_id"           required  requiredH  />  
		   </td>  
	    </tr>  
	   
	    <tr>  
	       <td ><label>${clt_id}</label></td>  
	       <td  >  
	       <input id="clt_id"  name="client.clt_id"    libre      type="text"     size="10"           value="${detailBean.client.clt_id}"            nextElement="dev_lib"   required  requiredH    /> 
	       <input id="clt_lib" name="client.clt_lib"   libre      type="text"     size="30"           value="${detailBean.client.clt_lib}"           nextElement="dev_lib"   required  requiredH    />    
	       </td>  
	    </tr>  
	    
	     <tr>  
	       <td ><label>${xccc}</label></td>  
	       <td  >  
	       <input id="dev_lib" name="dev_lib"          type="text"     size="100"           value="${detailBean.dev_lib}"            nextElement="dev_obs"        /> 
	       </td>  
	    </tr>  
	     <tr>  
	       <td ><label>Remise</label></td>  
	       <td  >  
	       <input id="dev_remise" name="dev_remise"          type="montant3"     size="100"          value="${detailBean.dev_remise}"            nextElement="dev_obs"     onblur="loadGrid()"   /> 
	       </td>  
	    </tr>  
	   <tr>  
	      <td ><label>${dev_obs}</label></td>  
	      <td   >  
	      <input id="dev_obs" name="dev_obs"       type="text"    size="100"        maxlength="50"        value="${detailBean.dev_obs}"   nextElement="ar_id"     /> 
	     </td>  
	    </tr> 
	   </table> 
	 
	 </ext:panel>
  

	<ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="&nbsp;" > 
	 <table      id="gridd_devis_vente" class="display"  width="100%"   style="display: none;"  >
	  <thead   >
		<tr style="border-color:#a9bfd3;background-color:#d0def0;"   >
			<th></th>
			<th><input  type="checkbox"   id="Cheked_unCheked"  name="Cheked_unCheked"    onclick="doCheked_unCheked(this)"     value=""     ></th>
			<th>
			<input      type="text"     id="pk.code_barre"          name="pk.code_barre"         size="20"  value=""                           requiredx ></th>
			<th><input  type="text"     id="designation_libelle"    name="designation_libelle"   size="40"  value=""                           requiredx ></th>
			<th><input  type="number"   id="quantite"               name="quantite"              size="7"   value="1" min="1"   style="width: 70px;"    nextElement="unite"     requiredx ></th>
			<th> 
			<input   id="unite"  name="unite"   type="hidden" />   
			</th>
			<th></th>
			<th></th>
			<th></th>
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
			 
			<th></th>
			<th></th>
	    </tr>
	 </thead>
	 <tbody></tbody>
	  <tfoot>
	             
	              <tr><td   height="50px" colspan="10" >  </td> </tr>  
				  
					<c:forEach var="p" begin="1" end="5">
		                    <tr  > 
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td ></td>
								<td colspan="3" ></td>
							 
								<td ></td>
								<td ></td> 
						    </tr>
					</c:forEach>
				    
			       <c:forEach var="i" begin="1" end="5">
		                    <tr align="right"> 
								<td colspan="4"></td>
								<td   ></td>
								<td colspan="3" ></td>
								 
								<td ></td>
								<td ></td> 
						    </tr>
					</c:forEach>	
				 </tfoot>
			    </table>
			    
			         <script type="text/javascript">
					function doLoaderDataFooter( nRow,aData, iStart, iEnd){
					 
					    var json=doGenerate_methode_ajaxWithReturn('POST','${tmlx.urlAjax}','i$_ACT_CALCUL_TOTAL','json',false);
					    var	listTva= json.list_tva ;
					    var	listTotal = json.list_total ;
					    
					     
					     var footX ={} ;
					     
					     for (var h = 0; h <listTva.length; h++) {
					     var foot ={} ;
						        if(h==0){
						         foot[listTva[h].td1] = listTva[h].value1;
						         footX["AA"+h]=foot;
						         continue;
				                }
				             	foot[listTva[h].td1] = listTva[h].value1;
				             	foot[listTva[h].td2] = listTva[h].value2;
				             	foot[listTva[h].td3] = listTva[h].value3;
				             	footX["AA"+h]=foot;
					     }
					     
					     
					     for (var p = listTva.length ; p<6; p++) {
					      var foot = {} ;
					        foot["0"] = "";
			             	footX["UU"+p]=foot;
					     } 
					     
					     
					    for (var x = 0; x <listTotal.length; x++) {
					     var foot ={} ;
			             foot[listTotal[x].td1] = listTotal[x].value1;
			             foot[listTotal[x].td2] = listTotal[x].value2;
			             footX["BB"+x]=foot;
					     }   
					        
				    return  footX; 
				}
               </script>
               
	</ext:panel> 
 
</ext:panel>
</ext:body>
  