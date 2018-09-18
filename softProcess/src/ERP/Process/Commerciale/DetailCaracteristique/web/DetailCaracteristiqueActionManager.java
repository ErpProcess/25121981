package ERP.Process.Commerciale.DetailCaracteristique.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Caracteristique.service.CaracteristiqueService;
import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.Process.Commerciale.DetailCaracteristique.service.DetailCaracteristiqueService;
import ERP.Process.Commerciale.DetailCaracteristique.template.DetailCaracteristiqueTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
public class DetailCaracteristiqueActionManager extends DetailCaracteristiqueTemplate {
     private DetailCaracteristiqueService  serviceDetailCaracteristique;
@Autowired
public void setServiceDetailCaracteristique(DetailCaracteristiqueService serviceDetailCaracteristique) {
    this.serviceDetailCaracteristique = serviceDetailCaracteristique;
} 


@Autowired
CaracteristiqueService  serviceCaracteristique;



public    ModelAndView doInitServletAction() {

	setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
	setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
	removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	try {
		 
		setObjectValueModel(LIST_CARACTERISTIQUE, serviceCaracteristique.dofetchDatafromServer(new CaracteristiqueBean()));
		if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
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
public ModelAndView doFetchData(DetailCaracteristiqueBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceDetailCaracteristique.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(DetailCaracteristiqueBean detailBean) throws Throwable {
     try {
            serviceDetailCaracteristique.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(DetailCaracteristiqueBean beanUpBean) {	 
	 	try {
	 serviceDetailCaracteristique.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(DetailCaracteristiqueBean beanDelBean) {
    try {
     serviceDetailCaracteristique.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
