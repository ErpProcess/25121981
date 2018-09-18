package ERP.Process.Commerciale.Stock.MvtExceptionnel.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Stock.MvtExceptionnel.model.MvtExceptionnelBean;
import ERP.Process.Commerciale.Stock.MvtExceptionnel.template.MvtExceptionnelTemplate;
import ERP.Process.Commerciale.Stock.MvtExceptionnel.service.MvtExceptionnelService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class MvtExceptionnelActionManager extends MvtExceptionnelTemplate {
     private MvtExceptionnelService  serviceMvtExceptionnel;
@Autowired
public void setServiceMvtExceptionnel(MvtExceptionnelService serviceMvtExceptionnel) {
    this.serviceMvtExceptionnel = serviceMvtExceptionnel;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(MvtExceptionnelBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceMvtExceptionnel.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(MvtExceptionnelBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceMvtExceptionnel.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(MvtExceptionnelBean beanUpBean) {	 
	 	try {
	 serviceMvtExceptionnel.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(MvtExceptionnelBean beanDelBean) {
    try {
     serviceMvtExceptionnel.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
