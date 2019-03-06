 <%@include file="/Aceuil/esProcess.jsp" %>  
  <script  type="text/javascript">
$(document).ready(function (){

LoadAutoCompletAjax("prf_id","prf_libelle","usr_etat","listProfile_for_utlisateur");
LoadAutoCompletAjax("soc_id","soc_lib","pk_etab.etab_id","listSocioTa");
LoadOtherAutocompletesAjax("soc_id","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","usr_obs");
        
});
</script>

  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
  
   
   <tr>
        <td  ><label>${soc_id}</label></td>
        <td  ><input id="soc_id"   name="fk_etab_Bean.pk_etab.soc_bean.soc_id"     type="text"  size="7"   value="${searchBean.fk_etab_Bean.pk_etab.soc_bean.soc_id}"           maxlength="3"       />
              <input id="soc_lib"  name="soc_lib"    type="text"             value="${searchBean.fk_etab_Bean.pk_etab.soc_bean.soc_lib}"  maxlength="3"      />
        </td>
        <td ><label >${etab_id}</label></td>
        <td ><input id="pk_etab.etab_id"  name="fk_etab_Bean.pk_etab.etab_id" type="text"      size="7"    value="${searchBean.fk_etab_Bean.pk_etab.etab_id}"  maxlength="7" />
          <input    id="etab_lib" name="etab_lib" type="text"     size="15"                     value="${searchBean.fk_etab_Bean.etab_lib}"  maxlength="25" />
        </td>
      </tr>
      
  <tr>
   <td ><label >Profile</label></td>
        <td >
        <input id="prf_id" name="profile.prf_id" type="text"     value="${searchBean.profile.prf_id}"      size="7"               maxlength="4"         />
        <input id="prf_libelle" name="profile.prf_libelle" type="text"     value="${searchBean.profile.prf_libelle}"     size="22"            />
        </td>
   </tr>
        
   <tr>  
   <td width="7%"><label>${user_list}</label></td>  
   <td width="93%"  >  
   <input id="user_list"   name="user_list"     type="text"    size="2147483647"       maxlength="2147483647"        value="${searchBean.user_list}"    nextElement="btValidx"              />  
  </td>  
   </tr>   
 </table>   
</ext:panel>
<ext:panel    title="${nameList}"  id="RET_GRID"   border="false"   bodyStyle="background: none;"     style="display:none;"   ><jsp:include  page="${LIST_VIEW}.jsp" /></ext:panel>
</ext:panel>
</ext:body>
<script>Ext.onReady(function(){  try {	         <c:if test="${not empty dataListAajx}">        Ext.get('RET_GRID').setStyle('display', 'block');    </c:if>           } catch(e){   		}	         });               </script>        
