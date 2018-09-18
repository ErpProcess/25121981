package ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.template.TypeAvoirTemplate;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.service.TypeAvoirService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class TypeAvoirActionManager extends TypeAvoirTemplate {
     private TypeAvoirService  serviceTypeAvoir;
@Autowired
public void setServiceTypeAvoir(TypeAvoirService serviceTypeAvoir) {
    this.serviceTypeAvoir = serviceTypeAvoir;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(TypeAvoirBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceTypeAvoir.doFetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
 		} catch (Exception e) {
 			 getResponse().setStatus(500);
 			 e.printStackTrace();
 			 getResponse().setContentType(HTML_CONTENT_TYPE);
 			 getResponse().getWriter().print(e.getMessage());
}
return null;
}
public ModelAndView doAddData(TypeAvoirBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceTypeAvoir.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(TypeAvoirBean beanUpBean) {	 
	 	try {
	 serviceTypeAvoir.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(TypeAvoirBean beanDelBean) {
    try {
     serviceTypeAvoir.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
