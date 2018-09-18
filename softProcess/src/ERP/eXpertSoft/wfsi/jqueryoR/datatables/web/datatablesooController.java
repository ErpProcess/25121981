package ERP.eXpertSoft.wfsi.jqueryoR.datatables.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.Company;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.DataRepository;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Controller - Spring
 * 
 * @author Loiane Groner http://loianegroner.com (English) http://loiane.com
 *         (Portuguese)
 */
@Controller
public class datatablesooController    {

	 
	
	@RequestMapping(value = "ERP/eXpertSoft/wfsi/jqueryoR/datatables/view1.action"  )
	public void pppp(HttpServletRequest request, HttpServletResponse response) {
		 
		JQueryDataTableParamModel param = AjaxDataTablesUtility.getParam(request);
		
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords; //value will be set when code filters companies by keyword
		JsonArray data = new JsonArray(); //data that will be shown in the table

		iTotalRecords = DataRepository.GetCompanies().size();
		List<Company> companies = new LinkedList<Company>();
		for(Company c : DataRepository.GetCompanies()){
			if(	c.getName().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getAddress().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getTown().toLowerCase().contains(param.sSearch.toLowerCase()))
			{
				companies.add(c); // add company that matches given search criterion
			}
		}
		iTotalDisplayRecords = companies.size(); // number of companies that match search criterion should be returned
		
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		
		Collections.sort(companies, new Comparator<Company>(){
			public int compare(Company c1, Company c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 1:
					return c1.getAddress().compareTo(c2.getAddress()) * sortDirection;
				case 2:
					return c1.getTown().compareTo(c2.getTown()) * sortDirection;
				}
				return 0;
			}
		});
		
		if(companies.size()< param.iDisplayStart + param.iDisplayLength) {
			companies = companies.subList(param.iDisplayStart, companies.size());
		} else {
			companies = companies.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		}
	
		try {
			JsonObject jsonResponse = new JsonObject();			
			//jsongetResponse().addProperty("sEcho", sEcho);
			//jsongetResponse().addProperty("iTotalRecords", iTotalRecords);
			//jsongetResponse().addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
			for(Company c : companies){
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(c.getName()));
				row.add(new JsonPrimitive(c.getAddress()));
				row.add(new JsonPrimitive(c.getTown()));
				data.add(row);
			}
			//jsongetResponse().add("aaData", data);
			
			//getResponse().setContentType("application/Json");
			try {
				//getResponse().getWriter().print(jsongetResponse().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (JsonIOException e) {
			e.printStackTrace();
			//getResponse().setContentType("text/html");
			try {
			//	getResponse().getWriter().print(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
	}
	
	
	 

	 

}
