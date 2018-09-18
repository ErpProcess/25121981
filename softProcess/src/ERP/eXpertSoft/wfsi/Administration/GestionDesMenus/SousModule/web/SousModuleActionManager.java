package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

 
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.service.ModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service.SousModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.template.SousModuleTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service.SousPackageService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class SousModuleActionManager extends SousModuleTemplate {

	
	
	private SousModuleService          serviceSousModule;
	private ModuleService            serviceModule;
	
	@Autowired
	public void setServiceSousModule(SousModuleService serviceSousModule) {
		this.serviceSousModule = serviceSousModule;
	}
	@Autowired
	public void setServiceModule(ModuleService serviceModule) {
		this.serviceModule = serviceModule;
	}
	
	
	
	
	  
	
	
	public ModelAndView doInitServletAction() {
		try {
			
		
		
			setObjectValueModel("listModulForSouModulCre", serviceModule.getModuleList());
		
			
		
			
		
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 

			if (bs.getFct_id().equals("1")) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
 

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(SousModuleBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceSousModule.getSousModuleList(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			displayException(e, HTML_CONTENT_TYPE);
		}
		return null;

	}

	public ModelAndView doAddData(SousModuleBean detailBean) throws Throwable {
		try {
			 serviceSousModule.CreateSousModel(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	 

	public ModelAndView doUpdateData(SousModuleBean beanUpBean) {
		try {
			serviceSousModule.doUpdateRowData(beanUpBean);
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(SousModuleBean beanDelBean) {
		try {
			serviceSousModule.doDeleteRowData(beanDelBean);
			throwNewException("Reussite delete");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	


	

}
