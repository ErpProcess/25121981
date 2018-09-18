package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.util.GLangueTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.exportExcel;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class ActionGLangueManager extends GLangueTemplate {

	private GLangueService  serviceGLangue;

	@Autowired
	public void setServiceGLangue(GLangueService serviceGLangue) {
		this.serviceGLangue = serviceGLangue;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(GLangueBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceGLangue.dofetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(GLangueBean detailBean) throws Throwable {
		try {
			 serviceGLangue.doCreateRowData(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(GLangueBean detailBean) throws Throwable {

		try {
			GLangueBean beanOrgn = (GLangueBean) getObjectValueModel(FORM_BEAN);
			detailBean.setLang_id(beanOrgn.getLang_id());
			serviceGLangue.doUpdateRowData(detailBean);
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(GLangueBean detailBean) throws Throwable {
		try {
			GLangueBean orignBean = (GLangueBean) getObjectValueModel(FORM_BEAN);
			detailBean.setLang_id(orignBean.getLang_id());
			serviceGLangue.doDeleteRowData(detailBean);
			throwNewException("Reussite delete");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);

	}
	
	

}
