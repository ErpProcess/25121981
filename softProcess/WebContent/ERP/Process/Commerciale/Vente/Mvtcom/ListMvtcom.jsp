<script >  
 var mapColumsbean=[  
  { "sTitle": "Full name"       ,"sName": "full_name"        ,"sWidth": "500"   , "bSortable": "true"   },  
  { "sTitle": "Compte"          ,"sName": "compte"           ,"sWidth": "600"   , "bSortable": "true"   },
 
  
  { "sTitle": "File"         ,"sName": "file_id"              ,"sWidth": "400"       , "bSortable": "true"  ,
	  "mRender": function (data, type, full) { 
		  if( data !=""  ||  data !=null )
		  return '<a   href="#"  onclick="doPrintMvt(\''+data+'\');return false;"       >file</a>';
		  else  return "" } 
  },
  
  ];
 
 function doPrintMvt(fileId){
		doGenerateMethodeAjaxWithReturn(fileId, 'POST','${tmlx.urlAjax}','i$_ACT_LOAD_FILE_BASE64','text',false);
 }
 
 
 var  PdfBanqueProcess;
 function doGenerateMethodeAjaxWithReturn(fileId, TYPE_P_jQuery,URL_P_jQuery,HAction_P_jQuery,dataType_P_jQuery,async_P_jQuery){
     var formData = new FormData();
 	 formData.append('file_id', fileId); 
 	formData.append('HiddenAction', HAction_P_jQuery); 
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
 <jsp:include  page="${context_path}/dataGridSetting/dataGridConfig-v1.jsp" />   
