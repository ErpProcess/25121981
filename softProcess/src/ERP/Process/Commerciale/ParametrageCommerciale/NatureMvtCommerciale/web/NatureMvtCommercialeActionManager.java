package ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.template.NatureMvtCommercialeTemplate;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.service.NatureMvtCommercialeService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class NatureMvtCommercialeActionManager extends NatureMvtCommercialeTemplate {
     private NatureMvtCommercialeService  serviceNatureMvtCommerciale;
@Autowired
public void setServiceNatureMvtCommerciale(NatureMvtCommercialeService serviceNatureMvtCommerciale) {
    this.serviceNatureMvtCommerciale = serviceNatureMvtCommerciale;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(NatureMvtCommercialeBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceNatureMvtCommerciale.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(NatureMvtCommercialeBean detailBean) throws Throwable {
     try {
            serviceNatureMvtCommerciale.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(NatureMvtCommercialeBean beanUpBean) {	 
	 	try {
	 serviceNatureMvtCommerciale.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(NatureMvtCommercialeBean beanDelBean) {
    try {
     serviceNatureMvtCommerciale.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
