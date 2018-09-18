package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.template.DeviseTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class DeviseActionManager extends DeviseTemplate {
     private DeviseService  serviceDevise;
@Autowired
public void setServiceDevise(DeviseService serviceDevise) {
    this.serviceDevise = serviceDevise;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(DeviseBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceDevise.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(DeviseBean detailBean) throws Throwable {
     try {
            serviceDevise.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(DeviseBean beanUpBean) {	 
	 	try {
	 serviceDevise.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(DeviseBean beanDelBean) {
    try {
     serviceDevise.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
