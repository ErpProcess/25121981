<%@ include file="/Aceuil/esProcess.jsp"     %> 
<ext:body>

  <ext:panel    renderTo="ThePageJsp"          bodyStyle="background: none;"             border="false"         >
	   <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"   >
			 
		 
	        <!-- ******************************************************************************************************************************************** -->
	        <tr>  
	        <td width="20%" ><label>Langue</label></td>
	        <td width="29%"  colspan="3">  
	        <input id="lang_id"       name="pk_entite_admin.lang_id"         type="text"       size="5"    value="${detailBean.pk_entite_admin.lang_id}"            autofocus     />
	        <input id="lang_libelle"  name="pk_entite_admin.lang_libelle"    type="text"      size="30"    value=""         />
	        </td>
	        </tr>
	        
	        
	         <!-- ******************************************************************************************************************************************** -->
	        <tr>  
	        <td width="20%" ><label>Entite id</label></td>
	        <td width="29%"  colspan="3">  
	        <input id="ent_id"            name="pk_entite_admin.ent_id"    libre    idonly        type="text"       size="45"    value="${detailBean.pk_entite_admin.ent_id}"           />
	        </td>
	        </tr>
	        
	      
	      <!-- ******************************************************************************************************************************************** -->
	      <tr>  
	        <td width="20%" ><label>Package Systeme </label></td>
	        <td width="29%" >
	        <input id="pack_id"       name="pack_id"         type="text"    size="5"       value="${detailBean.pack_id}"              libre    idonly        />
	        <input id="pack_libelle"  name="pack_libelle"    type="text"       size="30"   value=""       libre    idonly      />
	        </td>
	        <td width="8%"><label >Sous Package  </label></td>
	        <td width="27%"> 
	        	 <input id="spack_id"     name="spack_id"             type="text"     size="5"    value="${detailBean.spack_id}"            libre    idonly      />
	             <input id="spack_libelle"  name="spack_libelle"       type="text"     size="30"   value=""         />
	        </tr>
	      <!-- ******************************************************************************************************************************************** -->
	        <tr>  
	        <td width="20%" ><label>Module </label></td>
	        <td width="29%" >
	         <input id="mod_id"       name="mod_id"                 type="text"        size="5"       value="${detailBean.mod_id}"        libre    idonly           />
	         <input id="mod_libelle"  name="mod_libelle"            type="text"        size="30"      value=""         libre    idonly     />
	        </td>
	        <td width="8%"><label >Sous Module  </label></td>
	        <td width="27%"> 
	        	 <input id="sousmod_id"       name="sousmod_id"            type="text"     size="5"    value="${detailBean.sousmod_id}"    libre    idonly       />
	             <input id="sousmod_libelle"  name="sousmod_libelle"         type="text"     size="30"   value=""     libre    idonly     />
	        </td>
	      </tr>
	      
	       <tr>  
	        <td width="20%" ><label>Schema </label></td>
	        <td width="29%" >
	         <input id="table_schem"  name="table_schem"    type="text"     size="30"   value="${detailBean.table_schem}"         libre    idonly        />
	        </td>
	        <td width="8%"><label >Table</label></td>
	        <td width="27%"> 
	          <input id="table_name"  name="table_name"    type="text"     size="30"   value="${detailBean.table_name}"      libre    idonly        />
	        </td>
	      </tr>
	      
	      <tr>  
	        <td width="20%" ><label>libelle </label></td>
	        <td width="29%" >
	         <input id="ent_libelle"  name="ent_libelle"    type="text"     size="30"   value="${detailBean.ent_libelle}"             />
	        </td>
	        <td width="8%"><label >Abréation</label></td>
	        <td width="27%"> 
	          <input id="ent_abrv"  name="ent_abrv"    type="text"     size="30"   value="${detailBean.ent_abrv}"          />
	        </td>
	      </tr>
						 
			 
</table>
  </ext:panel>
</ext:body>
