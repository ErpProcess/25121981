package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jquery.datatables.controller.EditableDataTableRequestParam;
import jquery.datatables.controller.DataEditableTablesParamUtility;
import jquery.datatables.model.Company;
import jquery.datatables.model.DataRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;


 

 

/**
 * Controller - Spring
 * 
 * @author Loiane Groner http://loianegroner.com (English) http://loiane.com
 *         (Portuguese)
 */
@Controller
public class datatablesAController  extends  GenericWeb   {
	
	@RequestMapping(value = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/Glibelle/update.action"  )
protected void doupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(getRequest().getParameter("id"));
		//int columnId = Integer.parseInt(getRequest().getParameter("columnId"));
		int columnPosition = Integer.parseInt(getRequest().getParameter("columnPosition"));
		//int rowId = Integer.parseInt(getRequest().getParameter("rowId"));
		String value = getRequest().getParameter("value");
		//String columnName = getRequest().getParameter("columnName");
		
		for(Company company: DataRepository.GetCompanies())
		{
			if(company.getId()==id)
			{
				switch (columnPosition)
	            {
	                case 0:
	                    company.setName(value);
	                    break;
	                case 1:
	                    company.setAddress(value);
	                    break;
	                case 2:
	                    company.setTown(value);
	                case 3:
	                    company.setTown(value);
	                    break;
	                default:
	                    break;
	            }
				getResponse().getWriter().print(value);
				return;
			}
		}
		getResponse().getWriter().print("Error - company cannot be found");
	}

	@RequestMapping(value = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/Glibelle/delete.action"  )
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(getRequest().getParameter("id"));
		for(Company c: DataRepository.GetCompanies())
		{
			if(c.getId()==id)
			{
				DataRepository.GetCompanies().remove(c);
				break;
			}
		}
	}



	@RequestMapping(value = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/Glibelle/add.action"  )
   protected void doPostAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = getRequest().getParameter("name");
		String address = getRequest().getParameter("address");
		String town = getRequest().getParameter("town");
		int country = Integer.parseInt(getRequest().getParameter("country"));
		
		Company c = new Company(name, address, town, country);
		DataRepository.GetCompanies().add(c);
		
		getResponse().getWriter().print(c.getId());
	}

	 

	
	
	@RequestMapping(value = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/Glibelle/viewss.action"  )
	protected void doGetet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final EditableDataTableRequestParam param = DataEditableTablesParamUtility.getParam(request);
		
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords;//value will be set when code filters companies by keyword
		JSONArray data = new JSONArray(); //data that will be shown in the table

		iTotalRecords = DataRepository.GetCompanies().size();
		List<Company> companies = new LinkedList<Company>();
		for(Company c : DataRepository.GetCompanies()){
			//Cannot search by column 0 (id)
			if(	
				param.bSearchable[1] &&  c.getName().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[2] &&  c.getAddress().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[3] &&  c.getTown().toLowerCase().contains(param.sSearchKeyword.toLowerCase()) 
				
				)
				
			{
				companies.add(c); // Add a company that matches search criterion
			}
		}
		iTotalDisplayRecords = companies.size();//Number of companies that matches search criterion should be returned
		
		
		Collections.sort(companies, new Comparator<Company>(){
			public int compare(Company c1, Company c2) {
				int result = 0;
				for(int i=0; i<param.iSortingCols; i++){
					int sortBy = param.iSortCol[i];
					if(param.bSortable[sortBy]){
						switch(sortBy){
							case 0:
								result =	0; //sort by id is not allowed
							break;
							case 1:
								result =	c1.getName().compareToIgnoreCase(c2.getName()) * 
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 2:
								result =	c1.getAddress().compareToIgnoreCase(c2.getAddress()) * 
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 3:
								result =	c1.getTown().compareToIgnoreCase(c2.getTown()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
						}
					}
					if(result!=0)
						return result;
					else
						continue;
				}
				return result;
			}
		});
		
		if(companies.size()< param.iDisplayStart + param.iDisplayLength)
			companies = companies.subList(param.iDisplayStart, companies.size());
		else
			companies = companies.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		try {
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("sEcho", sEcho);
			jsonResponse.put("iTotalRecords", iTotalRecords);
			jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
			
			for(Company c : companies){
				JSONArray row = new JSONArray();
				row.put(c.getId()).put(c.getName()).put(c.getAddress()).put(c.getTown());
				data.put(row);
			}
			jsonResponse.put("aaData", data);
			
			getResponse().setContentType("application/json");
			getResponse().getWriter().print(jsonResponse.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			getResponse().setContentType("text/html");
			getResponse().getWriter().print(e.getMessage());
		}
	
	}

}
