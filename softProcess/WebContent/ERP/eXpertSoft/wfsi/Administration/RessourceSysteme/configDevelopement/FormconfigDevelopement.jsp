 <%@include file="/Aceuil/esProcess.jsp" %>  
 
 <script  type="text/javascript">
$(document).ready(function (){

LoadAutoCompletAjax("prf_id","prf_libelle","usr_etat","listProfile_for_utlisateur");
LoadAutoCompletAjax("soc_id","soc_lib","pk_etab.etab_id","listSocioTa");
LoadOtherAutocompletesAjax("soc_id","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","usr_obs");
LoadAutoCompletAjax("sousmod_id","sousmod_libelle","fct_id","listSousModuleForAfection");        
});
</script>


  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >
   
   <tr>  
   <td width="7%"><label>${config_id}</label></td>  
   <td width="93%"  >  
   <input id="config_id" name="config_id"     type="text"    size="10"       maxlength="10"        value="${detailBean.config_id}"    nextElement="soc_id"    autofocus   required     />  
  </td>  
   </tr>   
  
  
   <tr>  
   <td width="7%"><label>${soc_id}</label></td>  
   <td width="93%"  >  
  <input id="soc_id"   name="fk_etab_Bean.pk_etab.soc_bean.soc_id"     type="text"  size="10"   value="${detailBean.fk_etab_Bean.pk_etab.soc_bean.soc_id}"          />
              <input id="soc_lib"  name="soc_lib"    type="text"         size="40"      value="${detailBean.fk_etab_Bean.pk_etab.soc_bean.soc_lib}"         />  
  </td>  
   </tr> 
   
   <tr>  
   <td width="7%"><label>${etab_id}</label></td>  
   <td width="93%"  >  
 <input id="pk_etab.etab_id"  name="fk_etab_Bean.pk_etab.etab_id" type="text"      size="10"    value="${detailBean.fk_etab_Bean.pk_etab.etab_id}"  maxlength="7" />
          <input    id="etab_lib" name="etab_lib" type="text"        size="40"                   value="${detailBean.fk_etab_Bean.etab_lib}"  maxlength="25" />
  </td>  
   </tr>  
   
   <tr>
   <td ><label >Profile</label></td>
        <td >
        <input id="prf_id" name="profile.prf_id" type="text"     value="${detailBean.profile.prf_id}"      size="7"               maxlength="4"         />
        <input id="prf_libelle" name="profile.prf_libelle" type="text"     value="${detailBean.profile.prf_libelle}"     size="22"            />
        </td>
   </tr>
   
 
   
    <tr>  
		   <td width="7%"><label>${sousmod_id}</label></td>  
		   <td width="93%"  >  
		   <input id="sousmod_id" name="sousMod.sousmod_id"     type="text"    size="5"    value="${searchBean.sousMod.sousmod_id}"      autofocus         />  
		   <input id="sousmod_libelle" name="sousMod.sousmod_libelle"     type="text"    size="20"    value="${searchBean.sousMod.sousmod_libelle}"                  />
		  </td>  
		   </tr> 
   
      
   <tr>  
   <td width="7%"><label>${user_list}</label></td>  
   <td width="93%"  >  
   <input id="user_list" name="user_list"     type="text"    size="2147483647"       maxlength="2147483647"        value="${detailBean.user_list}"    nextElement="json_properties"              />  
  </td>  
   </tr>   
   <tr>  
   <td width="7%"><label>${json_properties}</label></td>  
   <td width="93%"  >  
  <textarea id="json_properties"  name="json_properties"  cols="100"   rows="20"    autocomplete="off">${detailBean.json_properties}</textarea>      
  </td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
