package ERP.Process.Commerciale.Vente.Parametrage.caisse.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Vente.Parametrage.caisse.model.CaisseBean;
import ERP.Process.Commerciale.Vente.Parametrage.caisse.template.caisseTemplate;
import ERP.Process.Commerciale.Vente.Parametrage.caisse.service.caisseService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class caisseActionManager extends caisseTemplate {
     private caisseService  servicecaisse;
@Autowired
public void setServicecaisse(caisseService servicecaisse) {
    this.servicecaisse = servicecaisse;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(CaisseBean searchBean)throws Throwable {
		try {
			List listDataSrv = servicecaisse.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(CaisseBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            servicecaisse.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(CaisseBean beanUpBean) {	 
	 	try {
	 servicecaisse.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(CaisseBean beanDelBean) {
    try {
     servicecaisse.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
