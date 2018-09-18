package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.service.SpoorService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.util.SpoorTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class ActionSpoorManager extends SpoorTemplate {
	
	 private SpoorService serviceSpoor;

	@Autowired
	public void setSpoorService(SpoorService serviceSpoor) {
		this.serviceSpoor = serviceSpoor;
	}

	

 
	

 @SuppressWarnings("unchecked")
 public ModelAndView doFetchDataAjax(SpoorBean  searchBean ) throws Throwable, Throwable{
  
			try {
				List listDataSrv = serviceSpoor.getListDataServer(searchBean);
				setObjectValueModel(LIST_DATA, listDataSrv);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
			} catch (JsonIOException e) {
				e.printStackTrace();
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		 
}


	
 @SuppressWarnings("unchecked")
 public ModelAndView doFetchTraceParModule(SpoorBean  searchBean ) throws Throwable, Throwable{
  
			try {
				BeanSession bs =(BeanSession) getObjectValueModel(BEAN_SESSION);
				searchBean.setSous_mod(bs.getSousmod_id());
				List listDataSrv = serviceSpoor.getListDataServer(searchBean);
				setObjectValueModel(LIST_DATA, listDataSrv);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
			} catch (JsonIOException e) {
				e.printStackTrace();
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		 
} 				
 
			 @SuppressWarnings("unchecked")
			 public ModelAndView doFetchdata_formulaire_TraceParModule(   ) throws Throwable, Throwable{
			  
						try {
							 JsonObject json      = new JsonObject();
							 JsonArray  listData = new JsonArray();
							 BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
							 JsonObject  element = new JsonObject();
							 element.addProperty("usr_id",bs.getUsr_id());
							 element.addProperty("usr_nom",bs.getUsr_nom());
							 element.addProperty("usr_pre",bs.getUsr_pre());
							 
							 element.addProperty("prf_id",bs.getPrf_id());
							 element.addProperty("prf_libelle",bs.getPrf_libelle());
							 
							 element.addProperty("fct_id",bs.getFct_id());
							 element.addProperty("fct_libelle",bs.getFct_libelle());
							 
							 element.addProperty("sousmod_id",bs.getSousmod_id());
							 element.addProperty("sousmod_libelle",bs.getSousmod_libelle());
							 
							 
							   
							 
							 
							 listData.add(element);
							 json.add("myliste", listData);
							 getResponse().setContentType("application/json");      
							 getResponse().getWriter().write(json.toString());
						} catch (Exception e) {
							getResponse().setStatus(500);
							getResponse().setContentType(HTML_CONTENT_TYPE);
							getResponse().getWriter().print(e.getMessage());
						}
						return null;
					 
			} 
	

	
	 
 

	public ModelAndView doAddData(SpoorBean beanInsert ) {

	try {
		if (serviceSpoor.CreateRowData(beanInsert)) 
			 
		 throwNewException("Insertion Reussit");
		 else  
		 throwNewException("Echec d'insertion"); 
		 
		 
	  } catch (Exception e) {
		displayException(e); 
	  }
		 return getViewAdd(FORM_VIEW);
	}
	
	 
	
	
 



	public ActionSpoorManager() {
		super();
	}

	 

 
	 

}
