package ERP.Process.Commerciale.Caracteristique.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Caracteristique.template.CaracteristiqueTemplate;
import ERP.Process.Commerciale.Caracteristique.service.CaracteristiqueService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class CaracteristiqueActionManager extends CaracteristiqueTemplate {
     private CaracteristiqueService  serviceCaracteristique;
@Autowired
public void setServiceCaracteristique(CaracteristiqueService serviceCaracteristique) {
    this.serviceCaracteristique = serviceCaracteristique;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(CaracteristiqueBean searchBean)throws Throwable {
		try {
			List <CaracteristiqueBean>listDataSrv = serviceCaracteristique.dofetchDatafromServer(searchBean);
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
public ModelAndView doAddData(CaracteristiqueBean detailBean) throws Throwable {
     try {
            serviceCaracteristique.doSaveCaracteristique(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(CaracteristiqueBean beanUpBean) {	 
	 	try {
	 serviceCaracteristique.doUpdateCaracteristique(beanUpBean); 
	 update_row_from_list(LIST_DATA, beanUpBean);
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(CaracteristiqueBean beanDelBean) {
    try {
     serviceCaracteristique.doDeleteCaracteristique(beanDelBean);
     remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
