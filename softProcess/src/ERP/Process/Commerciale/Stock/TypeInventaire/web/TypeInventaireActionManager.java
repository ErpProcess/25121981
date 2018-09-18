package ERP.Process.Commerciale.Stock.TypeInventaire.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.Process.Commerciale.Stock.TypeInventaire.template.TypeInventaireTemplate;
import ERP.Process.Commerciale.Stock.TypeInventaire.service.TypeInventaireService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class TypeInventaireActionManager extends TypeInventaireTemplate {
     private TypeInventaireService  serviceTypeInventaire;
@Autowired
public void setServiceTypeInventaire(TypeInventaireService serviceTypeInventaire) {
    this.serviceTypeInventaire = serviceTypeInventaire;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(TypeInventaireBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceTypeInventaire.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(TypeInventaireBean detailBean) throws Throwable {
     try {
            serviceTypeInventaire.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(TypeInventaireBean beanUpBean) {	 
	 	try {
	 serviceTypeInventaire.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(TypeInventaireBean beanDelBean) {
    try {
     serviceTypeInventaire.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
