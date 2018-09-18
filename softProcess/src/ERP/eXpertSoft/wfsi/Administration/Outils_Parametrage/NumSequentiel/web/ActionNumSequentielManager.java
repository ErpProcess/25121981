package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSequentielBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.service.NumSequentielService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.template.NumSequentielTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class ActionNumSequentielManager extends NumSequentielTemplate {

	
	private static final long serialVersionUID = -6128181364810583850L;
	private NumSequentielService  serviceNumSequentiel;

	@Autowired
	public void setServiceNumSequentiel(NumSequentielService serviceNumSequentiel) {
		this.serviceNumSequentiel = serviceNumSequentiel;
	}
 
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(NumSequentielBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceNumSequentiel.dofetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(NumSequentielBean detailBean) throws Throwable {
		try {
			 serviceNumSequentiel.doCreateRowData(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	public ModelAndView doRetourToHome(NumSequentielBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(NumSequentielBean beanUpBean) {
		JsonResponse res = new JsonResponse();
		NumSequentielBean pSysBean = (NumSequentielBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceNumSequentiel.doUpdateRowData(beanUpBean);
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

	public ModelAndView doDeleteData(NumSequentielBean beanDelBean) {
		JsonResponse res = new JsonResponse();

		NumSequentielBean pSysBean = (NumSequentielBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceNumSequentiel.doDeleteRowData(beanDelBean);
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
