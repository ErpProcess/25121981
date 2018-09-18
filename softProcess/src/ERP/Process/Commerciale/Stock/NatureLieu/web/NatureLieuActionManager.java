package ERP.Process.Commerciale.Stock.NatureLieu.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Stock.NatureLieu.model.NatureLieuBean;
import ERP.Process.Commerciale.Stock.NatureLieu.template.NatureLieuTemplate;
import ERP.Process.Commerciale.Stock.NatureLieu.service.NatureLieuService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class NatureLieuActionManager extends NatureLieuTemplate {
     private NatureLieuService  serviceNatureLieu;
@Autowired
public void setServiceNatureLieu(NatureLieuService serviceNatureLieu) {
    this.serviceNatureLieu = serviceNatureLieu;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(NatureLieuBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceNatureLieu.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(NatureLieuBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceNatureLieu.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(NatureLieuBean beanUpBean) {	 
	 	try {
	 serviceNatureLieu.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(NatureLieuBean beanDelBean) {
    try {
     serviceNatureLieu.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
