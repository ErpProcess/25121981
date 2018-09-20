package ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.service.ModeReglementService;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.template.ModeReglementTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
public class ModeReglementActionManager extends ModeReglementTemplate {
     private ModeReglementService  serviceModeReglement;
@Autowired
public void setServiceModeReglement(ModeReglementService serviceModeReglement) {
    this.serviceModeReglement = serviceModeReglement;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(ModeReglementBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceModeReglement.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(ModeReglementBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceModeReglement.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(ModeReglementBean beanUpBean) {	 
	 	try {
	 serviceModeReglement.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(ModeReglementBean beanDelBean) {
    try {
     serviceModeReglement.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
