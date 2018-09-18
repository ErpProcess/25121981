package ERP.Process.Commerciale.Vente.Facture_comp_client.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Vente.Facture_comp_client.model.Facture_comp_clientBean;
import ERP.Process.Commerciale.Vente.Facture_comp_client.template.Facture_comp_clientTemplate;
import ERP.Process.Commerciale.Vente.Facture_comp_client.service.Facture_comp_clientService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class Facture_comp_clientActionManager extends Facture_comp_clientTemplate {
     private Facture_comp_clientService  serviceFacture_comp_client;
@Autowired
public void setServiceFacture_comp_client(Facture_comp_clientService serviceFacture_comp_client) {
    this.serviceFacture_comp_client = serviceFacture_comp_client;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(Facture_comp_clientBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceFacture_comp_client.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(Facture_comp_clientBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceFacture_comp_client.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(Facture_comp_clientBean beanUpBean) {	 
	 	try {
	 serviceFacture_comp_client.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(Facture_comp_clientBean beanDelBean) {
    try {
     serviceFacture_comp_client.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
