package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.service.ProfileService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.template.ProfileTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class ProfileActionManager extends ProfileTemplate {
	 
	private static final long serialVersionUID = 1L;
	private ProfileService serviceProfile;

	@Autowired
	public void setServiceProfile(ProfileService serviceProfile) {
		this.serviceProfile = serviceProfile;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(ProfileBean searchBean) throws Throwable {
		try {
			List listDataSrv = serviceProfile.dofetchDatafromServer(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			displayException(e, HTML_CONTENT_TYPE);
		}
		return null;
	}

	public ModelAndView doAddData(ProfileBean detailBean) throws Throwable {
		try {
			serviceProfile.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			 throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(ProfileBean beanUpBean) {
		try {
			serviceProfile.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean); 
	        throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(ProfileBean beanDelBean) {
		try {
			serviceProfile.doDeleteRowData(beanDelBean);
		    remove_row_from_list(LIST_DATA);
		    removeObjectModel(FORM_BEAN);
		    throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
