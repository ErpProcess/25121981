<%@    include file="/Aceuil/esProcess.jsp"     %>
<ext:body >
<ext:panel  renderTo="ThePageJsp"     bodyStyle="background: rgb(247, 247, 247);"       >
<div class="divStyleContent"   >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"     >


		
		 <tr>
             <td width="8%"   >
             <label>Langue</label> </td>
	         <td width="22%"    > 
                 <input   name="idLiblleBean.lang_id"  id="idLiblleBean.lang_id"  type="text" 
                   value="${detailBean.idLiblleBean.lang_id}"   readonly="readonly"   required="required"   />
	           </td>
               <td width="10%"></td>
               <td width="60%"></td>
         </tr>
         
         <tr>
             <td width="8%"   >
             <label> Code Libellé</label> </td>
	         <td width="22%"    > 
                 <input  id="idLiblleBean.lib_id"     readonly="readonly"   
                 name="idLiblleBean.lib_id" type="text"   value="${detailBean.idLiblleBean.lib_id}"  maxlength="3" />
	           </td>   
               <td width="10%"><label > Type Libelle</label></td>
               <td width="60%">
               <script type="text/javascript">$(document).ready(function () { LoadAutoCompletAjax("type_lib_id","type_libelle","listType"); });</script>
               <input id="type_lib_id" name="type_lib_id"       type="text" size="100"  value="${detailBean.type_lib_id}"  style="width: 200px;"   />
               <input id="type_libelle" name="type_libelle"    type="text" size="100"  value=""  style="width: 200px;"   />
               </td>
         </tr>
         
         <tr>
             <td width="8%"   >
             <label>Libelle</label> </td>
	         <td width="22%"    > 
                 <input   name="lib_libelle"  id="lib_libelle"  type="text" 
                   value="${detailBean.lib_libelle}"    />
	         </td>
             <td width="10%">Abreveation</td>
             <td width="60%">
                <input   name="lib_abrv"  id="lib_abrv"  type="text" 
                   value="${detailBean.lib_abrv}"    />
             
             </td>
         </tr>
        
        
        
        
         
          </table>
          </div>
  	  </ext:panel>
</ext:body>





 









