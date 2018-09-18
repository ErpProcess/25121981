 <%@include file="/Aceuil/esProcess.jsp" %>
<script type="text/javascript">

$(document).ready(function (){
LoadAutoCompletAjax("lang_id","lang_libelle","pack_id","listLangueEntite");
LoadAutoCompletAjax("pack_id","pack_libelle","spack_id","listPackageSys");
LoadOtherAutocompletesAjax("pack_id","i$_ACT_LOAD_SOUSPACK","spack_id","spack_libelle","mod_id");
LoadOtherAutocompletesAjax("spack_id","i$_ACT_LOAD_MODULE","mod_id","mod_libelle","sousmod_id");
LoadOtherAutocompletesAjax("mod_id","i$_ACT_LOAD_SOUS_MODULE","sousmod_id","sousmod_libelle",null);
});

function onChange_select(param_val){
if(param_val=="pack_id"){  
document.getElementById("spack_id").value="";
document.getElementById("spack_libelle").value="";
document.getElementById("mod_id").value="";
document.getElementById("mod_libelle").value="";
document.getElementById("sousmod_id").value="";
document.getElementById("sousmod_libelle").value="";
document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";

}else if(param_val=="spack_id"){  

document.getElementById("mod_id").value="";
document.getElementById("mod_libelle").value="";
document.getElementById("sousmod_id").value="";
document.getElementById("sousmod_libelle").value="";
document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";

}else if(param_val=="mod_id"){  

document.getElementById("sousmod_id").value="";
document.getElementById("sousmod_libelle").value="";
document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";

}else if(param_val=="sousmod_id"){  

document.getElementById("table_name").value="";
document.getElementById("table_schem").value="";
  var url ="${tmlx.urlAjax}?HiddenAction=i$_ACT_LOAD_TAB_SCHEM";  
		$.ajax({ 
	    type: 'POST', 
	    url: url, 
	    data:  "vapToSend="+document.getElementById(param_val).value, 
	    dataType: 'json',
	    success: function (data) {
	       
		    document.getElementById("table_name").value=data.myliste[0].table;
		    document.getElementById("table_schem").value=data.myliste[0].schema;
		    document.getElementById("ent_libelleent_abrv").style.display="block";
		    document.getElementById("ent_libelleent_abrv_entent_id").style.display="block";
		    
	    },
	   error: function (result) {
	        alert("Taper Sur Enter");
	    }
	});

}
}

</script>

<ext:body>

  <ext:panel    renderTo="ThePageJsp"          bodyStyle="background: none;"             border="false"       >
	    <table class="tableStyleContent"     cellpadding="5" cellspacing="10"            border="0"        id="tblData"     >
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>
	        <td width="100%" colspan="4"    align="center" >&nbsp;<span id="errmsg"  style="color: red;font-weight: bold;"></span> </td>
	      </tr>
	      
	        <!-- ******************************************************************************************************************************************** -->
	      <tr>  
	        <td width="20%" ><label>Langue</label></td>
	        <td width="29%"  colspan="3">  
	        <input id="lang_id"       name="pk_entite_admin.lang_id"         type="text"       size="5"    value=""            autofocus     />
	        <input id="lang_libelle"  name="pk_entite_admin.lang_libelle"    type="text"      size="30"   value=""         />
	        </td>
	     </tr>
	      
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>  
	        <td width="20%" ><label>Package Systeme </label></td>
	        <td width="29%" >
	        <input id="pack_id"       name="pack_id"         type="text"    size="5"    value=""                  />
	        <input id="pack_libelle"  name="pack_libelle"    type="text"       size="30"   value=""         />
	        </td>
	        <td width="8%"><label >Sous Package  </label></td>
	        <td width="27%"> 
	        	 <input id="spack_id"     name="spack_id"             type="text"     size="5"    value=""            />
	             <input id="spack_libelle"  name="spack_libelle"       type="text"     size="30"   value=""         />
	        </tr>
	      <!-- ******************************************************************************************************************************************** -->
	        <tr>  
	        <td width="20%" ><label>Module </label></td>
	        <td width="29%" >
	         <input id="mod_id"       name="mod_id"                 type="text"        size="5"       value=""               />
	         <input id="mod_libelle"  name="mod_libelle"            type="text"        size="30"      value=""          />
	        </td>
	        <td width="8%"><label >Sous Module  </label></td>
	        <td width="27%"> 
	        	 <input id="sousmod_id"       name="sousmod_id"        nextElement="btValidx"      type="text"     size="5"    value=""      />
	             <input id="sousmod_libelle"  name="sousmod_libelle"   nextElement="btValidx"      type="text"     size="30"   value=""     />
	        </td>
	      </tr>
	      
	       <tr>  
	        <td width="20%" ><label>Schema </label></td>
	        <td width="29%" >
	         <input id="table_schem"  name="table_schem"    type="text"     size="30"   value=""             />
	        </td>
	        <td width="8%"><label >Table</label></td>
	        <td width="27%"> 
	          <input id="table_name"  name="table_name"    type="text"     size="30"   value=""          />
	        </td>
	      </tr>
	      
	        <tr    >  
	        <td width="20%" ><label>Code </label></td>
	        <td width="29%" >
	         <input id="pk_entite_admin.ent_id"       name="pk_entite_admin.ent_id"     type="text"     size="30"   value=""             />
	        </td>
	        <td width="8%"><label ></label></td>
	        <td width="27%"> 
	          
	        </td>
	      </tr>
	      
	       
	       
	      
	      
	      
	      
	      
	       <tr    >  
	        <td width="20%" ><label>libelle</label></td>
	        <td width="29%" >
	         <input id="ent_libelle"  name="ent_libelle"       type="text"     size="30"   value=""       required      />
	        </td>
	        <td width="8%"><label >Abréation</label></td>
	        <td width="27%"> 
	          <input id="ent_abrv"  name="ent_abrv"    type="text"     size="30"   value=""          />
	        </td>
	      </tr>
	    </table>
  </ext:panel>
</ext:body>
