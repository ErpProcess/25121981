 <%@include file="/Aceuil/esProcess.jsp" %>  
<script type="text/javascript">
 
 function goToPage(pageload){
 
     $('#next_Focus').val(pageload);
     $("#ssSQZ_father").mask("Veuillez Patientez...");
	 $("#myformToServeur").find('input[name="HiddenAction"]').val("i$_ACT_LOAD_VIEW");
	 $("#myformToServeur").attr("action",contexPath+"${tmlx.url}");
     $("#myformToServeur").submit(); 

 }

</script>
  <ext:body  >  
  <ext:panel  border="false"    bodyStyle="background: none;"      renderTo="ThePageJsp"   >  
 <table width="915"  cellpadding="5" cellspacing="10" class="tableStyleContent"  id="tblData"     >  
 
   <tr>  
   <td width="45%"><label><input type="hidden"  value=""  id="next_Focus"  name="next_Focus"  > <a  href="">  Chiffre d'affaire par jour  </a></label></td>  
   <td width="55%"  ><a  href="javascript:goToPage('ListPositionParProduit');">Chiffre d'affaire par produit</a> </td>  
   </tr> 
  
   
   <tr>  
   <td width="45%"><label> <a  href="">  Chiffre d'affaire par mois</a></label></td>  
   <td width="55%"  ><label> <a  href=""> Chiffre d'affaire par Client</a></label></td>  
   </tr> 
   
    <tr>  
   <td width="45%"><label> <a  href="">  Chiffre d'affaire par année</a></label></td>  
   <td width="55%"  ><label> <a  href=""> Chiffre d'affaire par Lieu</a></label></td>  
   </tr> 
   
   
   <tr>  
   <td width="45%"><label> <a  href=""></a></label></td>  
   <td width="55%"  ><label> <a  href=""> Chiffre d'affaire par Etablissement</a></label></td>  
   </tr>   
 </table>   
</ext:panel>
</ext:body>
