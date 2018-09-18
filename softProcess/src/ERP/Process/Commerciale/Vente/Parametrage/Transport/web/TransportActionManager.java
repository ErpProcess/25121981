package ERP.Process.Commerciale.Vente.Parametrage.Transport.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Vente.Parametrage.Transport.model.TransportBean;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.template.TransportTemplate;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.service.TransportService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class TransportActionManager extends TransportTemplate {
     private TransportService  serviceTransport;
@Autowired
public void setServiceTransport(TransportService serviceTransport) {
    this.serviceTransport = serviceTransport;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(TransportBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceTransport.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(TransportBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceTransport.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(TransportBean beanUpBean) {	 
	 	try {
	 serviceTransport.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(TransportBean beanDelBean) {
    try {
     serviceTransport.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
