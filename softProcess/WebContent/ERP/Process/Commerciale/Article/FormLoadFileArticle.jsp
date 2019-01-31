<%@    include file="/Aceuil/esProcess.jsp"     %>
 <script type="text/javascript">
    $(document).ready(function () {  selectOptionvalue("fam_id","${searchBean.fam_art.fam_id}");  })
    var files = [];
	$(document).on("change","#fileLoader",function(event) { files=event.target.files; processUpload();})
	
	
	function processUpload(){
	           
	             var oMyForm = new FormData();
	             oMyForm.append("file", files[0]);
	             oMyForm.append("HiddenAction", "i$_ACT_UPLOADER");
	             oMyForm.append("doc_id_interne",  $('#doc_id_interne').val() );
	             
	            
	             $.ajax({dataType : "text",
	                    url : "${tmlx.urlAjax}",
	                    data : oMyForm,
	                    type : "POST",
	                    enctype: "multipart/form-data",
	                    processData: false, 
	                    contentType:false,
	                    scriptCharset: "utf-8",
	        			async: false,
	        			cache: false,
	        			timeout: 600000,
	                    success : function(result) {
	                        alert(result);
	                    },
	                    error : function(result){
	                        alert(result);
	                    }
	                });
	     }
   </script>
   
  <ext:body       >  
  <ext:panel  border="false"    bodyStyle="background: none;"         renderTo="ThePageJsp"   >  
 
 <table class="tableStyleContent"  cellpadding="5" cellspacing="10"  id="tblData"      >  
  <tr>  
   <td width="7%"><label>${fam_id}</label></td>  
   <td width="93%"  >  
   <select id="fam_id" name="fam_art.fam_id"  nextElement="sitcod"    >
    <option  value="">----select-----</option>
     <c:forEach var="famille_arBean"    items="${list_FamilleArticle}"    >
   <option  value="${famille_arBean.fam_id}">${famille_arBean.fam_lib}</option>
   </c:forEach>
   </select>
  </td> 
   </tr>   
   
    <tr>  
   <td width="7%"><label>File</label></td>  
   <td width="93%"  >  
   <input  id="fileLoader"  type="file" name="file"       />    
  </td> 
   </tr>   
  
 </table>   
</ext:panel>
 
</ext:body>
     
