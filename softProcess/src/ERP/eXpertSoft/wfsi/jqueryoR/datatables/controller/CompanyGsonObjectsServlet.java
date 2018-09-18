package ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller;

import java.io.IOException;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.Company;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.DataRepository;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

/**
 * CompanyServlet provides data to the JQuery DataTables
 */
public class CompanyGsonObjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CompanyGsonObjectsServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JQueryDataTableParamModel param = AjaxDataTablesUtility.getParam(request);
		
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords; //value will be set when code filters companies by keyword

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
		iTotalDisplayRecords = companies.size();// number of companies that match search criterion should be returned
		
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
			Gson gson = new Gson();
			//jsongetResponse().add("aaData", gson.toJsonTree(companies));
			
			//getResponse().setContentType("application/Json");
			//getResponse().getWriter().print(jsongetResponse().toString());
			
		} catch (JsonIOException e) {
			e.printStackTrace();
			//getResponse().setContentType("text/html");
			//getResponse().getWriter().print(e.getMessage());
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}