<%@    include file="/Aceuil/esProcess.jsp"     %>

<ext:body >
<ext:panel  renderTo="ThePageJsp"     bodyStyle="background: rgb(247, 247, 247);"      >
<div class="divStyleContent"   >
<table class="tableStyleContent"  cellpadding="5" cellspacing="10"     >

         <tr>
             <td width="8%"  style="" >
             <label>${_cdlan}</label>   </td>
	         <td width="22%"    > 
                 <input id="lang_id" name="lang_id" type="text"  required="required"  value="${detailBean.lang_id}"  maxlength="3" />
	           </td>   
               <td width="10%"><label >Libelle</label></td>
               <td width="60%">
               <input id="lang_libelle" name="lang_libelle"      required="required"  type="text" size="100"  value="${detailBean.lang_libelle}"  style="width: 200px;"   />
               </td>
        </tr> 
        <tr valign="top" >
              <td  width="8%" >Abeveation</td> 
               <td width="22%" >
                  <input id="lang_abrv" name="lang_abrv"          required="required"  type="text"    value="${detailBean.lang_abrv}"  style="width: 200px;"   />
              </td>
              
		  </tr> 
		  <tr valign="top"  >
               <td  width="8%"  >Observation</td>  
               <td  width="92%"  colspan="3"    >
                   <textarea rows="10" cols="50"  name="lang_obs" id="lang_obs" style="width: 560px;"  >${detailBean.lang_obs}</textarea>
                </td>
          </tr> 
         
          </table>
          </div>
  	  </ext:panel>
</ext:body>





 









