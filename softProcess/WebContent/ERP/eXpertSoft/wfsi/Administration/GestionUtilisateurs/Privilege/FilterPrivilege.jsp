<%@    include file="/Aceuil/esProcess.jsp"     %>
 
<script type="text/javascript">
 $(document).ready(function (){
LoadAutoCompletAjax("prf_id","prf_libelle","","listProfilFor_privilege"); 
});
</script>

  
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <ext:panel  border="false"    bodyStyle="background: none;"    title="Critere de recherche"   collapsible="true"    >  
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"     >  
   <tr>  
   <td width="7%"><label>${prf_id}</label></td>  
   <td width="93%"  >  
   <input id="prf_id" name="pkPriv.pfrBean.prf_id"     type="text" size="7"  value="${searchBean.pkPriv.pfrBean.prf_id}"     nextElement="fct_id"    autofocus   required      />  
   <input id="prf_libelle" name="pkPriv.pfrBean.prf_libelle"     type="text" size="20"  value="${searchBean.pkPriv.pfrBean.prf_libelle}"     nextElement="fct_id"    autofocus   required      />
  </td>  
   </tr>   
   
   
 </table>   
</ext:panel>


</ext:panel>
</ext:body>
