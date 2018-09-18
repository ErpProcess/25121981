<%@include file="/Aceuil/esProcess.jsp" %>
<c:import url="${context_path}/dataGridSetting/EditabledataGridConfig.jsp"></c:import> 
<script type="text/javascript">

var lumsbean=[ 
			   {      "sTitle": "Caractéristique"    , "sWidth": "10%"    ,"sName": "pk_det_codBare.bean_det_carac.pk_det_carac.bean_carac.carac_libelle"  ,"bSearchable": true  , "bSortable": true,"bVisible": true   }, 
			         
			   {      "sTitle": "Définition"        , "sWidth": "90%"   ,"sName": "pk_det_codBare.bean_det_carac.det_carac_libelle"  ,"bSearchable": true  , "bSortable": true,"bVisible": true    },       
	         ]; 
mapEditableGen= {"otab":oTable,"table":"grid_det_code_barre","list":"select_list_carac_det_code_barre","id_name":"indx_row","url":"${urlloadDataTableAjax}","action":"i$_ACT_LOAD_EDITABLE_TABLE_AJAX","mapCol":lumsbean};

$(document).ready(function (){
contenu_toolbarJQuey="";
height_tabbJQuey="100px";
LoadDataEditableFromServer_toolbar( mapEditableGen  , afficher_mess_emptyJQuey  ,  nbr_ligneJQuey  , height_tabbJQuey  , width_tabbJQuey  , 
config_header_foot_tableJQuey  ,  contenu_toolbarJQuey  );
});

</script> 

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   > 
  
  <ext:panel    bodyStyle="background: none;"  > 
	 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
	  
	   <tr>  
	   <td width="7%"><label>${ar_id}</label></td>  
	   <td width="93%"  >  
	   <input id="ar_id" name="pk.ar_bean.pk_article.ar_id"   libre readonly="readonly"   type="text"    size="20"           value="${detailBean.pk.ar_bean.pk_article.ar_id}"    nextElement="designation"              />  
	  </td>  
	   </tr> 
	   
	    <tr>  
	   <td width="7%"><label>Référence</label></td>  
	   <td width="93%"  >  
	   <input id="code_barre" name="pk.code_barre"     type="text"   libre readonly="readonly"  size="20"               value="${detailBean.pk.code_barre}"    nextElement="ar_id"    autofocus   required     />  
	  </td>  
	   </tr>   
	   
	     
	   <tr>  
	   <td width="7%"><label>${designation}</label></td>  
	   <td width="93%"  >  
	   <input id="designation" name="designation"     type="hidden"    size="150"              value="${detailBean.designation}"    nextElement="btValidx"              />  
	   <input id="designation_libelle" name="designation_libelle"     type="text"    size="75"              value="${detailBean.designation_libelle}"    nextElement="btValidx"              />
	  </td>  
	   </tr>   
	 </table>
 </ext:panel>
 
 <ext:panel      bodyStyle="background: none;"   title="Cractéristique article-code barre"   > 
	<table id="grid_det_code_barre" class="display" width="100%" >
	</table>
</ext:panel>
     
</ext:panel>
</ext:body>
