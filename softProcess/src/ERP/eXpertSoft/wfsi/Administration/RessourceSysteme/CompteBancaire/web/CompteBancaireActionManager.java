package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.template.CompteBancaireTemplate;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.service.CompteBancaireService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class CompteBancaireActionManager extends CompteBancaireTemplate {
     private CompteBancaireService  serviceCompteBancaire;
	@Autowired
	public void setServiceCompteBancaire(CompteBancaireService serviceCompteBancaire) {
	    this.serviceCompteBancaire = serviceCompteBancaire;
	} 
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(CompteBancaireBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceCompteBancaire.doFetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(CompteBancaireBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceCompteBancaire.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(CompteBancaireBean beanUpBean) {	 
		 	try {
		 serviceCompteBancaire.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(CompteBancaireBean beanDelBean) {
	    try {
	     serviceCompteBancaire.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
