package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

 
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service.PackageSysService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service.SousPackageService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.util.SousPackageTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class SousPackageActionManager extends SousPackageTemplate {
     
	
	
	
	private SousPackageService serviceSousPackage;
	
	private PackageSysService servicePackageSys;
	 
	
	@Autowired
	public void setServicePackageSys(PackageSysService servicePackageSys) {
		this.servicePackageSys = servicePackageSys;
	}


	@Autowired
	public void setSousPackageService(SousPackageService serviceSousPackage) {
		this.serviceSousPackage = serviceSousPackage;
	}
	   
	
	public ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		setObjectValueModel("listPackgefromPack", servicePackageSys.getPackageSysList(new PackageSysBean()));
		try {

			if (bs.getFct_id().equals(GenericActionBean.Fn_Creer)
					
					
					||bs.getFct_id().equals(GenericActionBean.Fn_Nouveau ) ) {
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
public ModelAndView doFetchData(SousPackageBean beanSearch)throws Throwable {
	try {
		List listDataSrv = serviceSousPackage.getSousPackList(beanSearch);
		setObjectValueModel(LIST_DATA, listDataSrv);
		setObjectValueModel(SEARCH_BEAN, beanSearch);
		AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	} catch (Exception e) {
		displayException(e, HTML_CONTENT_TYPE);
	}
	return null;

}
	 
	 
 
	
	public ModelAndView doAddData(SousPackageBean beanInsert) throws Throwable {
		try {
			serviceSousPackage.CreatePackSystem(beanInsert);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	

	 
	
 

	
	
	public ModelAndView doUpdateData(SousPackageBean updateBean) {
		try {
			serviceSousPackage.doUpdateRowData(updateBean);
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(SousPackageBean deleteBean) {
		try {
			serviceSousPackage.doDeleteRowData(deleteBean);
			throwNewException("Reussite delete");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	

	

}
