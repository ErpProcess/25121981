package ERP.Process.Commerciale.Parametrage.TypeFacture.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Parametrage.TypeFacture.model.TypeFactureBean;
import ERP.Process.Commerciale.Parametrage.TypeFacture.template.TypeFactureTemplate;
import ERP.Process.Commerciale.Parametrage.TypeFacture.service.TypeFactureService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class TypeFactureActionManager extends TypeFactureTemplate {
     private TypeFactureService  serviceTypeFacture;
@Autowired
public void setServiceTypeFacture(TypeFactureService serviceTypeFacture) {
    this.serviceTypeFacture = serviceTypeFacture;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(TypeFactureBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceTypeFacture.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(TypeFactureBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceTypeFacture.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(TypeFactureBean beanUpBean) {	 
	 	try {
	 serviceTypeFacture.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(TypeFactureBean beanDelBean) {
    try {
     serviceTypeFacture.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
