
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head> 
 <meta http-equiv="content-type" content="text/html; charset=UTF-8">

     
    
   
    
 
  <!-- 
        <link href="media2/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media2/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media2/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
       
        <link href="media2/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        
        
       
        
        
        <script src="scripts2/jquery-1.5.2.min.js" type="text/javascript"></script>
        <script src="scripts2/jquery.dataTables.js" type="text/javascript"></script>
        <script src="scripts2/jquery-ui-1.8.13.custom.min.js" type="text/javascript"></script>
        <link href="scripts2/jquery-ui-1.8.13.custom.css" rel="stylesheet" type="text/css" media="all" />
        
       
        <script src="scripts2/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts2/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts2/jquery.jeditable.js" type="text/javascript"></script>
        <script src="scripts2/jquery.validate.js" type="text/javascript"></script>
        <script src="scripts2/jquery-ui.js" type="text/javascript"></script>
         -->
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="scripts2/jquery-ui-1.8.13.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link href="scripts2/datatable2.css" rel="stylesheet" type="text/css" media="all" />
         
        <script src="<%=request.getContextPath()%>/scripts2/jquery-1.4.4.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts2/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts2/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts2/jquery.jeditable.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts2/jquery.validate.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts2/jquery-ui.js" type="text/javascript"></script>
        
        
        
        <script type="text/javascript">
	    var contexPath = "<%=request.getContextPath() %>";
	    var baseURL = window.location.protocol + '\/\/' + window.location.host + '\/';
        </script>
        
        <script type="text/javascript">
        
        
        var  urlLoa=baseURL+"eXpertSoft";
        
        var  urlLoadDassta=urlLoa+"/jquery/datatables/viewss.action";
        
        $(document).ready(function () {
        
      
            $("#companies").dataTable({
                "bServerSide": true,
                "sAjaxSource": urlLoadDassta,
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true,
                "aoColumns": [
                              {  "sName": "ID",
                                 "bSearchable": false,
                                 "bSortable": false,
                                 "bVisible": false
                                     },
                      { "sName": "COMPANY_NAME" },
                      { "sName": "ADDRESS" },
                      { "sName": "TOWN" }
                     ]
         }).makeEditable({
                sDeleteURL :urlLoa+"/jquery/datatables/delete.action",
         		sAddURL:urlLoa+"/jquery/datatables/add.action" ,
         		sUpdateURL: urlLoa+"/jquery/datatables/update.action",
        	    "aoColumns": [
        	                  {
        	                      
        	                      
        	                      indicator: 'Saving...',
        	                      tooltip: 'Click to select town',
        	                      loadtext: 'loading...',
        	                      type: 'text',
        	                      onblur: 'submit'
        	                  },
        	                  {
        	                      
        	                      
        	                      indicator: 'Saving...',
        	                      tooltip: 'Click to select town',
        	                      loadtext: 'loading...',
        	                      type: 'text',
        	                      onblur: 'submit'
        	                  },//null for read-only columns
        	                  {
        	                      indicator: 'Saving...',
        	                      tooltip: 'Click to select town',
        	                      loadtext: 'loading...',
        	                      type: 'select',
        	                      onblur: 'submit',
        	                      data: '{"London":"London","Liverpool":"Liverpool","Portsmouth": "Portsmouth","Edinburgh":"Edinburgh", "Blackburn":"Blackburn","Kent":"Kent","Essex":"Essex","Oxon":"Oxon","Lothian":"Lothian", "West Sussex":"West Sussex","Lanarkshire":"Lanarkshire", "Birmingham":"Birmingham","East Sussex":"East Sussex","Surrey":"Surrey"}'
        	                  }
        	              ]
        	              });
        });
        </script>
    </head>
    <body id="dt_example">
        <div id="container">
            <div id="demo_jui">
            <button id="btnAddNewRow" value="Ok">+</button> 
    		<button id="btnDeleteRow" value="cancel">-</button>
		        <table id="companies" class="display">
		            <thead>
		                <tr>
		                	<th>ID</th>
		                    <th>Company name</th>
		                    <th>Address</th>
		                    <th>Town</th>
		                </tr>
		            </thead>
		            <tbody>
		          
		            </tbody>
		        </table>
		    </div>
            
            
            <form id="formAddNewRow" action="#" title="Add new company">
			    <input type="hidden" id="id" name="id" value="-1" rel="0" />
			    <label for="name">Name</label><input type="text" name="name" id="name" class="required" rel="1" />
			    <br />
			    <label for="name">Address</label><input type="text" name="address" id="address" rel="2" />
			    <br />
			    <label for="name">Postcode</label><input type="text" name="postcode" id="postcode"/>
			    <br />
			    <label for="name">Town</label><input type="text" name="town" id="town" rel="3"/>
			    <br />
			    <label for="name">Country</label><select name="country" id="country">
			                                        <option value="1">Serbia</option>
			                                        <option value="2">France</option>
			                                        <option value="3">Italy</option>
			                                        </select>   
			    <br />         
			</form>

        </div>
    </body>
</html>
