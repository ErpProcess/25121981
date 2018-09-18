package ERP.Process.Commerciale.Type_tarification.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.template.Type_tarificationTemplate;
import ERP.Process.Commerciale.Type_tarification.service.Type_tarificationService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class Type_tarificationActionManager extends Type_tarificationTemplate {
     private Type_tarificationService  serviceType_tarification;
@Autowired
public void setServiceType_tarification(Type_tarificationService serviceType_tarification) {
    this.serviceType_tarification = serviceType_tarification;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(Type_tarificationBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceType_tarification.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(Type_tarificationBean detailBean) throws Throwable {
     try {
            serviceType_tarification.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(Type_tarificationBean beanUpBean) {	 
	 	try {
	 serviceType_tarification.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(Type_tarificationBean beanDelBean) {
    try {
     serviceType_tarification.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
