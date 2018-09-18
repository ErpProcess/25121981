package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.template.TVATemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class TVAActionManager extends TVATemplate {
     private TVAService  serviceTVA;
@Autowired
public void setServiceTVA(TVAService serviceTVA) {
    this.serviceTVA = serviceTVA;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(TVABean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceTVA.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(TVABean detailBean) throws Throwable {
     try {
            serviceTVA.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(TVABean beanUpBean) {	 
	 	try {
	 serviceTVA.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(TVABean beanDelBean) {
    try {
     serviceTVA.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
