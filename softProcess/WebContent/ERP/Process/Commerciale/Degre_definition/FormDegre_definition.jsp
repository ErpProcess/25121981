<%@include file="/Aceuil/esProcess.jsp" %> 
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">

var  type_Loder="C";
var lumsbean=[ 
       {      "sTitle": "sss"        , "sName": "indx_row"       ,"bSearchable": false  , "bSortable": false,"bVisible": false }, 
       
       {      "sTitle": "X"        , "sName": "to_check"     ,"sWidth": "2%"     ,"bSortable": true     , "mRender": function( data, type, full){
              return  '<input  type="checkbox"    idRow="'+full[3]+'"   value="'+data+'" id=to_check'+full[0]+'    name=to_check      '+data+'   onclick="doEnvoiData(this)"      >';}},
              
       {      "sTitle": "ordre"        , "sWidth": "15%"  ,"sName": "ordre"   ,"mRender": function( data, type, full){  
	          return '<input   type="text"    idRow="'+full[3]+'"   size="15"      id=ordre'+full[0]+'    name=ordre    value="'+data+'"     onblur="doEnvoiData(this)"   >';}},          
                  
	   {      "sTitle": "code"        , "sWidth": "10%"    ,"sName": "carac_id"   ,"mRender": function( data, type, full){  
	          return '<input   type="text"  size="15"     id=carac_id'+full[0]+'          name=carac_id      value='+data+'  readonly   style="background:none;border:none;"  >';}}, 
	         
	   {      "sTitle": "Libelle"        , "sWidth": "30%"  ,"sName": "carac_libelle"   ,"mRender": function( data, type, full){  
	          return '<input   type="text"  size="70"      id=carac_libelle'+full[0]+'    name=carac_libelle    value="'+data+'"     readonly    style="background:none;border:none;"  >';}}, 
	  
	    
	                       
	          
	         ]; 
mapEditableGen= {"otab":oTable,"table":"grid_degre","list":"list_des_carcteristique","id_name":"carac_id","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};

Ext.onReady(function(){Ext.getCmp('btValidx').disable();});
 
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
	           data: {HiddenAction:'i$_ACT_LOAD_CRACTERISTIQUE',TYPE_LOAD:type_Loder,'ID_AR':$('#ar_id').val()},
	           dataType: 'text', 
	           success: function(data){
	           contenu_toolbarJQuey="";
	           Ext.getCmp('bthn').disable();
	           //Ext.getCmp('btValidx').disable();
	           Ext.getCmp('btValidx').enable();
	           LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
               config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
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

 
 
 
<ext:panel  border="false"    bodyStyle="background: none;"        >  

<ext:toolbar         toolbarType="bbar"   > 
<ext:toolbar.button  text=" Suivant  >> "   style="margin-left:999px;"  id="bthn"  onClick="loader_CaracTeristique()"  ></ext:toolbar.button> </ext:toolbar>

<table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     > 
 
   
  
   <tr>  
   <td width="7%"><label>${ar_id}</label></td>  
   <td width="93%"  > 
   <script type="text/javascript"  >
   $(document).ready(function (){
   
    if( "${bs.fct_id}"=="15"  ){
    LoadAutoCompletAjax("pk_article.ar_id","ar_libelle",null,"list_des_article");
    }
    
    if( "${bs.fct_id}"=="2"  ||  "${bs.fct_id}"=="3"  || "${bs.fct_id}"=="13"  ){
     type_Loder="M";
     loader_CaracTeristique(type_Loder);
    }
    
    });
   </script> 
   <input id="pk_article.ar_id" name="pkBean.art_Bean.pk_article.ar_id"               type="text"    size="15"      maxlength="15"        value="${detailBean.pkBean.art_Bean.pk_article.ar_id}"         autofocus   required     />  
   <input id="ar_libelle" name="pkBean.art_Bean.ar_libelle"     type="text"    size="15"       maxlength="15"        value="${detailBean.pkBean.art_Bean.ar_libelle}"                  />
  </td>  
   </tr>   
  
 </table>  
 </ext:panel>
<ext:panel   id="RET_GRID"   bodyStyle="background: none;"   title="code a barre " > 
	<table id="grid_degre" class="display" width="100%" >
	</table>
</ext:panel>



</ext:panel>
</ext:body>
