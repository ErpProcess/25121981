package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service.PackageSysService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.util.PackageSysTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class ActionPackageSysManager extends PackageSysTemplate {
     
	
	
	
	private PackageSysService packageSysService;
	private GLangueService    gLangueService;
	

	@Autowired
	public void setPackageSysService(PackageSysService packageSysService) {
		this.packageSysService = packageSysService;
	}
	
	@Autowired
	public void setGLangueService(GLangueService langueService) {
		gLangueService = langueService;
	}

	 
	 

 


@SuppressWarnings("unchecked")
public ModelAndView doFetchData(PackageSysBean searchBean)throws Throwable {
	try {
		List listDataSrv = packageSysService.getPackageSysList(searchBean);
		setObjectValueModel(LIST_DATA, listDataSrv);
		setObjectValueModel(SEARCH_BEAN, searchBean);
		AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	} catch (Exception e) {
		displayException(e, HTML_CONTENT_TYPE);
	}
	return null;

}
	 
	 
 
	
	public ModelAndView doAddData(PackageSysBean beanInsert) throws Throwable {
		try {
			packageSysService.CreatePackSystem(beanInsert);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	

	 
 

	
	
	public ModelAndView doUpdateData(PackageSysBean pack) {
		try {
			packageSysService.UpdatePackSystem(pack);
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(PackageSysBean beanDelBean) {
		try {
			packageSysService.DeletePackSystem(beanDelBean);
			throwNewException("Reussite delete");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	

	

}
