package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.service.Data_entite_simpleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.template.Data_entite_simpleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import com.google.gson.JsonIOException;

public class Data_entite_simpleManager extends Data_entite_simpleTemplate {

	private Data_entite_simpleService  serviceData_entite_simple;

	@Autowired
	public void setServiceData_entite_simple(Data_entite_simpleService serviceData_entite_simple) {
		this.serviceData_entite_simple = serviceData_entite_simple;
	}
 
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Data_entite_simpleBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceData_entite_simple.dofetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(Data_entite_simpleBean detailBean) throws Throwable {
		try {
			 serviceData_entite_simple.doCreateRowData(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	public ModelAndView doRetourToHome(Data_entite_simpleBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(Data_entite_simpleBean beanUpBean) {
		JsonResponse res = new JsonResponse();
		Data_entite_simpleBean pSysBean = (Data_entite_simpleBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceData_entite_simple.doUpdateRowData(beanUpBean);
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listoo = (List) getObjectValueModel(LIST_DATA);
			listoo.remove(Integer.parseInt(indexo));
			listoo.add(beanUpBean);
			setObjectValueModel(LIST_DATA, listoo);

			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(Data_entite_simpleBean beanDelBean) {
		JsonResponse res = new JsonResponse();

		Data_entite_simpleBean pSysBean = (Data_entite_simpleBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceData_entite_simple.doDeleteRowData(beanDelBean);
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listoo = (List) getObjectValueModel(LIST_DATA);
			listoo.remove(Integer.parseInt(indexo));
			setObjectValueModel(LIST_DATA, listoo);
			throwNewException("Reussite delete");
			
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	
	

}
