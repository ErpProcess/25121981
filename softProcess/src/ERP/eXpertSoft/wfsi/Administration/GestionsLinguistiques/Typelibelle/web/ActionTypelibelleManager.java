package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.service.TypelibelleService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.util.TypelibelleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.MessageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

public class ActionTypelibelleManager extends TypelibelleTemplate {

	 
	
	private TypelibelleService serviceTypelibelle;
	
	@Autowired
	public void setServiceTypelibelle(TypelibelleService serviceTypelibelle) {
		this.serviceTypelibelle = serviceTypelibelle;
	}

	

	 
	

 @SuppressWarnings("unchecked")
 public ModelAndView doFetchDataAjax(TypelibelleBean  searchBean ) throws Throwable, Throwable{
 
		try {
			List  listPackge= serviceTypelibelle.getListDataServer(searchBean);
			setObjectValueModel(LIST_DATA, listPackge);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			JsonObject jsonResponse = new JsonObject(); 
			JsonArray data = new JsonArray();
			String nameVector=getRequest().getParameter("nameList");
			JQueryDataTableParamModel parameter = AjaxDataTablesUtility.getParam(getRequest());
			List listOfmyDataTwo=AjaxDataTablesUtility.JQueryDefinePlaguinDataTable(parameter,listPackge,jsonResponse,data);
			setObjectValueModel("IndeXo"+nameVector, listOfmyDataTwo);
			jsonResponse.add(JSON_DATA_RESPONSE, data);
			getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(jsonResponse.toString());
			} catch (JsonIOException e) {
				e.printStackTrace();
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		 
}


	
	 
	
	 
 
	 
	public ModelAndView doAddData(TypelibelleBean beanInsert ) {

		String tr="";
		if (serviceTypelibelle.CreateRowData(beanInsert)) {
			tr="Insertion Reussite";
		} else {
			tr="Insertion non Reussite";
		}
		 setObjectValueModel(MESSAGERROR, tr);
		 return getViewAdd((PAGE_FORM));
	}

	public ModelAndView doRetourToHome(TypelibelleBean pack) {
		return getHome();
		 
	}

	 

	public ModelAndView doUpdateData(TypelibelleBean beanUpBean) {
		JsonResponse res = new JsonResponse();
		TypelibelleBean   pSysBean  =(TypelibelleBean) getObjectValueModel(FORM_BEAN);
		 
		if (serviceTypelibelle.UpdateRowData(beanUpBean)) {
			res.setStatus("SUCCESS");
			res.setResult("Insertion Réussite");
		} else {
			res.setStatus("FAIL");
			res.setResult("Insertion Fausse");
		}

		String indexo = (String) getObjectValueModel(INDEX_ROW);
		List listoo = (List) getObjectValueModel(LIST_DATA);
		listoo.remove(Integer.parseInt(indexo));
		listoo.add(beanUpBean);
		setObjectValueModel(LIST_DATA, listoo);
		return getViewList_Ajax((PAGE_FILTER) );
	}

	public ModelAndView doDeleteData(TypelibelleBean beanDelBean) {
		JsonResponse res = new JsonResponse();
		
		TypelibelleBean   pSysBean  =(TypelibelleBean) getObjectValueModel(FORM_BEAN);
	 

		if (serviceTypelibelle.DeleteRowData(beanDelBean)) {
			res.setStatus("SUCCESS");
			res.setResult("Insertion Réussite");
		} else {
			res.setStatus("FAIL");
			res.setResult("Insertion Fausse");
		}

		String indexo = (String) getObjectValueModel(INDEX_ROW);
		List listoo = (List) getObjectValueModel(LIST_DATA);
		listoo.remove(Integer.parseInt(indexo));
		setObjectValueModel(LIST_DATA, listoo);
		return getViewList_Ajax((PAGE_FILTER) );
		
	}
	
 



	 

 
	 

}
