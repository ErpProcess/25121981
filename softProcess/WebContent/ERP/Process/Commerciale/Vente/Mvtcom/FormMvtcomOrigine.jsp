 <%@include file="/Aceuil/esProcess.jsp" %>  
 


<style>
.cssInput {
background-color: transparent;border: 0px;-webkit-box-shadow: none;-moz-box-shadow: none;box-shadow: none;position:absolute;
}
</style>

<ext:body  >  
<ext:panel  border="false"    bodyStyle="background: none;"    renderTo="ThePageJsp"   >  


 <script>
 function doResetAjaxMvt(){
	 $('#myformToServeur').trigger("reset");
 }
 const img = new Image();
 img.onload = function() {
   //alert(this.width + 'x' + this.height);
 }
 img.src = '${pathRootac}/img/Scre.png';
</script>

<input  type="text"  id="id" name="id"    value="${detailBean.id}"   />
<div  id="clickme"   style="width: 1216px; height:867px; background-image:url(${pathRootac}/img/Scre.png); position:relative;" >

 

<input  type="text" id="agence" name="additionalInfo.agence"    maxlength="15"   style="left:1007px; top:43px; border:1px;width:130px;height:20px;font-size:15px;background-color: transparent;border: 0px;-webkit-box-shadow: none;-moz-box-shadow: none;box-shadow: none;position:absolute;"  
class="cssInput"   value="${detailBean.map.agence}"  /> 


<input  type="text" id="compte"  required  name="compte"    
style="left:240px; top:200px;width:500px;height:25px;font-size:15px;background-color: transparent;border: 0px;-webkit-box-shadow: none;-moz-box-shadow: none;box-shadow: none;position:absolute;"  
class="cssInput" 
value="${detailBean.compte}"  /> 


 
<input  type="text" id="additionalInfo.beneficier"   required  name="additionalInfo.beneficier" 
style="left:200px; top:245px;width:590px;height:25px;font-size:15px;background-color: transparent;border: 0px;-webkit-box-shadow: none;-moz-box-shadow: none;box-shadow: none;position:absolute;"   
class="cssInput"      value="${detailBean.map.beneficier}"   /> 

<input  type="text" id="full_name"  required  name="full_name"    
style="left:240px; top:287px;width:500px;height:25px;font-size:15px;background-color: transparent;border: 0px;-webkit-box-shadow: none;-moz-box-shadow: none;box-shadow: none;position:absolute;"  
class="cssInput" 
value="${detailBean.full_name}"  /> 


</div>


<script>
$("#clickme").mousemove(function(event){            
     var X = event.pageX - $(this).offset().left;
     var Y = event.pageY - $(this).offset().top;
     // $(".cordn").text("(" + X + "," + Y + ")");
     console.log("xCoor : " + X + " ; yCoor : " + Y + ".");
   }); 
</script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
    <script type="text/javascript">
        function Export() {
            html2canvas(document.getElementById('clickme'), {
          	  width: 1400,
        	  height: 1200,
        	  scrollX: 0,
        	  scrollY: 0,
                onrendered: function (canvas) {
                    var data = canvas.toDataURL('image/png');
                    var docDefinition = {
                            pageSize: {
                                width: 500,
                                height: 'auto'
                            },
                            content: [{
                                 image: data ,
                                 width: 500 
                                //height: height,
                            }],
                            pageSize: 'A4',
                            extend: 'pdfHtml5',
                            orientation: 'landscape',
                           // pageMargins: [ 5, 5, 5, 5 ],
                           // customize: function (doc) { doc.defaultStyle.fontSize = 1; } //2,3,4,etc doc.styles.tableHeader.fontSize = 1; //2, 3, 4, etc }
                        };
                    //pdfMake.createPdf(docDefinition).download("Table.pdf");
                    //pdfMake.createPdf(docDefinition).print({}, window.frames['printPdf']);
                    //pdfMake.createPdf(docDefinition).print();
                    pdfMake.createPdf(docDefinition).getBase64(function(encodedString) {
                    	//console.log(encodedString);
                    	doGenerateMethodeAjaxWithReturn(encodedString, 'POST','${tmlx.urlAjax}','i$_ACT_ADD','text',false);
                    });
                    
                }
            });
        }
        
        function validerMvt(){	 
        	 if(!teste_required()) return ;
        	 Export();
       	}
        
       
        var  PdfBanqueProcess;
        function doGenerateMethodeAjaxWithReturn(base64ImageContent, TYPE_P_jQuery,URL_P_jQuery,HAction_P_jQuery,dataType_P_jQuery,async_P_jQuery){
             var formData = new FormData();
             var addInfo = {};
             $('#myformToServeur').find('input[name="HiddenAction"]').val(HAction_P_jQuery);
        	  $.each($("#myformToServeur").serializeArray(), function (i, field) {
        	    var valField = field.value || "";
        	    formData.append(field.name, valField);
        	    if(field.name.indexOf("additionalInfo")>=0 ){
        	    	addInfo[field.name.split(/[.]/)[1]] = valField;
        	    }
        	  });
        	formData.append('additional_info', JSON.stringify(addInfo));
        	var blob = pdfBlobConversion(base64ImageContent, 'application/pdf');
        	formData.append('file', blob); 
            jQuery.ajax({ 
                       type: TYPE_P_jQuery,  
        	           url:  URL_P_jQuery, 
        	           data: formData,
        	           enctype: "multipart/form-data",
                       processData: false, 
                       contentType:false,
                       scriptCharset: "utf-8",
           		       async: false,
           			   cache: false,
           			   timeout: 600000,
        	        success: function(data){ 
    					 printPreview(data);
    					  $('#myformToServeur').trigger("reset");
                       },
        	           error: function (request, status, error) {
        	        	  // alert('error'+error); 
        	           } 
                }); 
        }
        
        function printPreview(data){
            var type = 'application/pdf';
            let blob = null;
            const blobURL = URL.createObjectURL( pdfBlobConversion(data, 'application/pdf'));
            PdfBanqueProcess =  new Ext.Window({
				  width        : 1200,
				  height       : 500,
				  maximizable: true,
				  minimizable :true,
				  autoScroll:false,
				  closeAction: 'hide',
				  maxHeight: 500,
				  modal   : true,
				  items: {
				            xtype: 'component',
				            autoEl: {
				                tag: 'iframe',
				                style: 'height: 100%; width: 100%; border: none',
				                src:  blobURL
				            }
				        },
				  title        : 'PDF-Imprimer',
				  border       : true,
				  closable: true,
				  listeners: {close:function(){ doCloseWindowPDFAndDeleteFile(data);} }
			}).show();
            
            /*const theWindow = window.open(blobURL);
           
            const theDoc = theWindow.document;
            const theScript = document.createElement('script');
            function injectThis() {
                window.print();
            }
            theScript.innerHTML = 'window.onload = ${injectThis.toString()};';
            theDoc.body.appendChild(theScript);*/
        }
      //converts base64 to blob type for windows
        function pdfBlobConversion(b64Data, contentType) {
              contentType = contentType || '';
              var sliceSize = 512;
              b64Data = b64Data.replace(/^[^,]+,/, '');
              b64Data = b64Data.replace(/\s/g, '');
              var byteCharacters = window.atob(b64Data);
              var byteArrays = [];

              for ( var offset = 0; offset < byteCharacters.length; offset = offset + sliceSize ) {
                var slice = byteCharacters.slice(offset, offset + sliceSize);

                var byteNumbers = new Array(slice.length);
                for (var i = 0; i < slice.length; i++) {
                  byteNumbers[i] = slice.charCodeAt(i);
                }

                var byteArray = new Uint8Array(byteNumbers);

                byteArrays.push(byteArray);
              }

              var blob = new Blob(byteArrays, { type: contentType });
              return blob;
            }
  
    </script>


    
</ext:panel>
</ext:body>
