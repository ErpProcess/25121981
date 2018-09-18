<%@    include file="/Aceuil/esProcess.jsp"     %>
<c:set var="pathRoot" value="<%=request.getContextPath() %>" ></c:set>
  
<script type="text/javascript">

var  urlsUpdateURL_Libell  ="${tmlx.urlAjax}?HiddenAction=i$_ACT_UPDATE_GRID";
var  namedataListAjax="&nameList=listlibLangue";
var  nameupListAjax="&sNameList=listlibLangue";

$(document).ready(function () { LoadAutoCompletAjax("type_lib_id","type_libelle",null,"listType"); });
var sNameBean=[{  "sName": "idLiblleBean.lang_id",
                  "bSearchable": false,
                  "bSortable": false,
                  "bVisible": true },
                  { "sName": "langlibelle" },
                  { "sName": "lib_libelle" },
                  { "sName": "lib_abrv" } ];
                  
var sNameBeanEditable=[ null,
        	                  {
        	                      indicator: 'Saving...',
        	                      tooltip: 'Click to select town',
        	                      loadtext: 'loading...',
        	                      event: 'click',
        	                      type: 'textarea',
        	                      onblur: 'submit'
        	                  },
        	                  {
        	                      indicator: 'Saving...',
        	                      tooltip: 'Click to select abrv',
        	                      loadtext: 'loading...',
        	                      type: 'text',
        	                      onblur: 'submit'
        	                  },
        	                  {
        	                      indicator: 'Saving...',
        	                      tooltip: 'Click to select town',
        	                      loadtext: 'loading...',
        	                      type: 'select',
        	                      onblur: 'submit',
        	                      data: '{"London":"London","Liverpool":"Liverpool","Portsmouth": "Portsmouth","Edinburgh":"Edinburgh", "Blackburn":"Blackburn","Kent":"Kent","Essex":"Essex","Oxon":"Oxon","Lothian":"Lothian", "West Sussex":"West Sussex","Lanarkshire":"Lanarkshire", "Birmingham":"Birmingham","East Sussex":"East Sussex","Surrey":"Surrey"}'
        	                  }
];                     
$(document).ready(function () {
           oTable= $("#companies").dataTable({
                "bServerSide": true,
                "sAjaxSource": urlsAjaxSource_def+namedataListAjax,
                "bProcessing": true,
                 "sPaginationType": "full_numbers",
                //"sDom": 'Rlfrtip',
                 "oColReorder": {"headerContextMenu": false},
                "bJQueryUI": true,
                "scrollY":"100px",
                "aoColumns": sNameBean
         }).makeEditable({
                sDeleteURL :urlsDeleteURL_def+namedataListAjax,
         		//sAddURL:urlsAddURL_def+namedataListAjax ,
         		sUpdateURL: urlsUpdateURL_Libell+nameupListAjax,
        	    "aoColumns":sNameBeanEditable 
       });
});
 
 
 
 
function  isadd(){

		 
var url ="${tmlx.urlAjax}?HiddenAction=i$_ACT_ADD_ROW_EDITABLE_TABLE_AJAX";  
	    $.ajax({ 
	           url: url,  
	           success: function(data)
	           {
		         if(oTable!=undefined){
					var oSettings = oTable.fnSettings();
					oSettings.sAjaxSource=urlsAddURL;
					oTable.fnDraw();
			   }
	               
	             }
});

} 
</script>
<ext:body >
<ext:panel  renderTo="ThePageJsp"     bodyStyle="background: rgb(247, 247, 247);"       >
 
          <table class="tableStyleContent"  cellpadding="5" cellspacing="10"     >
	         <tr>
	             <td width="8%"   >
	             <label> Code Libellé</label> </td>
		           <td width="22%"    > 
	                 <input  id="idLiblleBean.lib_id"   name="idLiblleBean.lib_id" type="text"    required  value="${detailBean.idLiblleBean.lib_id}"    />
		           </td>   
	               <td width="10%"><label > Type Libelle</label></td>
	               <td width="60%">
	               <input id="type_lib_id" name="type_lib_id"       type="text" size="7"  value="${detailBean.type_lib_id}"     />
	               <input id="type_libelle" name="type_libelle"     type="text" size="15"  value=""     />
	               </td>
	        </tr> 
          </table>
          
          <ext:body>
          <ext:panel     renderTo="RET"    height="500"    >
	          <div id="container">
		           <div  id="topy" class="x-panel-header"  style="height: 15px;width: 100%;"   >
				         <div  style="margin-top: -4px;">  Detail Libelle Par Langue &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
				             <button id="btnAddNewRow" value="Ok" type="button"  onclick="isadd()"  style="margin-left: 80%; " disabled="disabled"  >+</button> 
				    	     <button id="btnDeleteRow" value="cancel"  type="button"   disabled="disabled" >-</button>
				         </div>
		            </div>
		            <div id="demo_jui">
				        <table id="companies" class="display" width="100%">
				            <thead>
				                <tr>
				                    <th  width="50px;">ID</th>
				                    <th  width="150px;" >Langue</th>
				                	<th  width="200px;" >Libellé</th>
				                    <th  width="300px;">abrveation</th>
				                </tr>
				            </thead>
				        </table>
				    </div>
	           </div>
          </ext:panel> 
        </ext:body>
        <div  id="RET"   ></div>
    
  	  </ext:panel>
</ext:body>





 









