package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.service.PaysvilleposteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.template.PaysvilleposteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class PaysvilleposteManager extends PaysvilleposteTemplate {

	private PaysvilleposteService  servicePaysvilleposte;

	@Autowired
	public void setServicePaysvilleposte(PaysvilleposteService servicePaysvilleposte) {
		this.servicePaysvilleposte = servicePaysvilleposte;
	}
 
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(PaysvilleposteBean searchBean)throws Throwable {
		try {
			List listDataSrv = servicePaysvilleposte.dofetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(PaysvilleposteBean detailBean) throws Throwable {
		try {
			 servicePaysvilleposte.doCreateRowData(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	public ModelAndView doRetourToHome(PaysvilleposteBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(PaysvilleposteBean beanUpBean) {
		JsonResponse res = new JsonResponse();
		PaysvilleposteBean pSysBean = (PaysvilleposteBean) getObjectValueModel(FORM_BEAN);
		try {
			servicePaysvilleposte.doUpdateRowData(beanUpBean);
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

	public ModelAndView doDeleteData(PaysvilleposteBean beanDelBean) {
		JsonResponse res = new JsonResponse();

		PaysvilleposteBean pSysBean = (PaysvilleposteBean) getObjectValueModel(FORM_BEAN);
		try {
			servicePaysvilleposte.doDeleteRowData(beanDelBean);
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
