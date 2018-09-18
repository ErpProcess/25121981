package ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.service.Nature_incident_mvt_retourService;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.template.Nature_incident_mvt_retourTemplate;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;


public class Nature_incident_mvt_retourManager extends Nature_incident_mvt_retourTemplate {
     private Nature_incident_mvt_retourService  serviceNature_incident_mvt_retour;
	@Autowired
	public void setServiceNature_incident_mvt_retour(Nature_incident_mvt_retourService serviceNature_incident_mvt_retour) {
	    this.serviceNature_incident_mvt_retour = serviceNature_incident_mvt_retour;
	} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(Nature_incident_mvt_retourBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceNature_incident_mvt_retour.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(Nature_incident_mvt_retourBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceNature_incident_mvt_retour.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(Nature_incident_mvt_retourBean beanUpBean) {	 
	 	try {
	 serviceNature_incident_mvt_retour.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(Nature_incident_mvt_retourBean beanDelBean) {
    try {
     serviceNature_incident_mvt_retour.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
