package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.service.FonctionService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.template.FonctionTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service.SousPackageService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class ActionFonctionManager extends FonctionTemplate {

	
	
	private FonctionService          serviceFonction;
	private SousPackageService            serviceSousPackage;
	
	@Autowired
	public void setServiceFonction(FonctionService serviceFonction) {
		this.serviceFonction = serviceFonction;
	}
	@Autowired
	public void setServiceSousPackage(SousPackageService serviceSousPackage) {
		this.serviceSousPackage = serviceSousPackage;
	}
	
	  
	
	
	public ModelAndView doInitServletAction() {
		try {
	        setObjectValueModel("listSouPakforFonction", serviceSousPackage.getSousPackList(new SousPackageBean()));
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
	public ModelAndView doFetchData(FonctionBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceFonction.getFonctionList(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			displayException(e, HTML_CONTENT_TYPE);
		}
		return null;

	}

	public ModelAndView doAddData(FonctionBean detailBean) throws Throwable {
		try {
			 serviceFonction.CreateFonction(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	 

	public ModelAndView doUpdateData(FonctionBean beanUpBean) {
		try {
			serviceFonction.doUpdateRowData(beanUpBean);
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(FonctionBean beanDelBean) {
		try {
			serviceFonction.doDeleteRowData(beanDelBean); 
			throwNewException("Reussite delete");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	


	

}
